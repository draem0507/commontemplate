/*
 * Copyright (c) 2005, Yu Cheung Ho
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted 
 * provided that the following conditions are met:
 *
 *    * Redistributions of source code must retain the above copyright notice, this list of 
 *        conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above copyright notice, this list 
 *        of conditions and the following disclaimer in the documentation and/or other materials 
 *        provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR 
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND 
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS 
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, 
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF 
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.commontemplate.util.yaml;

import java.awt.Color;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


class SpecialTreatmentManager {
    
    Map treatmentTable = new HashMap();
    
    static class Holder{
        Class clazz;
        Map values = new HashMap();
        Holder(Class clazz){
            this.clazz = clazz;
        }
    }
    
    static class DefaultSpecialTreatment{
        Class clazz;
        String[] constructorFields;
        List removeFields = new ArrayList();
        
        DefaultSpecialTreatment(String classname){
            try {
                this.clazz = Class.forName(classname);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        
        public Object getHolder(){
            return new Holder(clazz);
        }
        
        public void setProperty(Object obj, String key, Object value){
            Holder holder = ((Holder)obj);
            holder.values.put(key, value);
        }
        
        public Object getRealObject(Object obj){
            Holder holder = (Holder)obj;
            return decode(holder.values);
        }
        
        public Class getPropertyType(Object obj, String key){
            try{
                Field field = clazz.getField(key);
                if (ReflectionUtil.isPublicMemberField(field))
                	return field.getType();
            }catch(Exception e){}
            PropertyDescriptor prop = ReflectionUtil.getPropertyDescriptor(clazz, key);
            if (prop != null)
                return prop.getPropertyType();
            else
                throw new YamlParserException("No field or property named " + key + " found for class " + clazz.getName());
            
        }
        
        public Object decode(Map map){
            ArrayList constructorArgsTypes = new ArrayList();
            ArrayList constructorArgs = new ArrayList();
            if (constructorFields != null)
            	for(int i=0; i<constructorFields.length; i++){
            		String fieldName = constructorFields[i];
                    Class type = null;
                    try{
                        Field field = clazz.getField(fieldName);
                        type = field.getType();
                    }catch (Exception e){
                        PropertyDescriptor prop = ReflectionUtil.getPropertyDescriptor(clazz, fieldName);
                        type = prop.getPropertyType();
                    }
                    constructorArgsTypes.add(type);
                    constructorArgs.add(map.get(fieldName));
                }
            Object newObject = ReflectionUtil.invokeConstructor(clazz, 
                    constructorArgsTypes.isEmpty() ? null : (Class[])constructorArgsTypes.toArray(new Class[]{}),
                    constructorArgs.isEmpty() ? null: constructorArgs.toArray());
            return newObject;
        }
        
        public void encode(Object obj, YamlEncoder enc, String indent){
            if (!enc.isMinimalOutput())
                enc.out.println("!" + clazz.getName());
            else
                enc.out.println();
            // for (String fieldname: constructorFields){
            for(int i=0; i<constructorFields.length; i++){
            	String fieldname = constructorFields[i];
                try {
                    
                    Class type = null;
                    Object value = null;
                    try{
                        Field field = clazz.getField(fieldname);
                        if (ReflectionUtil.isPublicMemberField(field)){
	                        type = field.getType();
	                        value = field.get(obj);
                        }
                    }catch (Exception e){}
                    if (type == null){
                        PropertyDescriptor prop = ReflectionUtil.getPropertyDescriptor(clazz, fieldname);
                        type = prop.getPropertyType();
                        value = prop.getReadMethod().invoke(obj, null);
                    }
                    enc.out.print(indent + fieldname + ": ");
                    if (type.isPrimitive())
                        enc.writeSimpleValue(value, type, enc.indent(indent));
                    else
                        enc.writeObject(value, enc.indent(indent), type);
                } catch (Exception e){
                    throw new RuntimeException(e);
                }
            }
        }
        
        public void traverseAndCount(Object obj, YamlEncoder enc){
            List props = ReflectionUtil.getPropertiesExcluding(removeFields, obj);
            List allFields = ReflectionUtil.getFieldsExcluding(removeFields, obj);
            List fields = new ArrayList(allFields);
            Iterator fieldit = allFields.iterator();
            // for (Field field: allFields)
            for(Field field; fieldit.hasNext();){
            	field = (Field)fieldit.next();
            	Iterator propit = props.iterator();
                // for (PropertyDescriptor prop: props)
            	for(PropertyDescriptor prop; propit.hasNext();){
            		prop = (PropertyDescriptor)propit.next();
                    if (field.getName().equals(prop.getName()))
                        fields.remove(field);
            	}
            }
            Iterator propit = props.iterator();
            // for (PropertyDescriptor prop: props){
            for(PropertyDescriptor prop; propit.hasNext();){
            	prop=(PropertyDescriptor)propit.next();
                if (!prop.getPropertyType().isPrimitive()){
                    Method readMethod = prop.getReadMethod();
                    try {
                        Object value = readMethod.invoke(obj, null);
                        enc.traverseAndCount(value);
                    } catch (Exception e){
                        throw new YamlParserException(e);
                    }
                }
            }
            fieldit = fields.iterator();
            // for (Field field: fields){
            for(Field field; fieldit.hasNext();){
            	field = (Field)fieldit.next();
                if (ReflectionUtil.isPublicMemberField(field) &&
                		!field.getType().isPrimitive()){
                	Object value = null;
                    try {
                        value = field.get(obj);
                    } catch (Exception e){
                        throw new YamlParserException("This shouldn't happen.");
                    }
                    
                    enc.traverseAndCount(value);
                }
            }   
        }
    }
    
    static class ColorSpecialTreatment extends DefaultSpecialTreatment{

        public ColorSpecialTreatment(String classname) {
            super(classname);
        }

        /* (non-Javadoc)
         * @see org.ho.yaml.SpecialTreatmentManager.DefaultSpecialTreatment#decode(java.util.Map)
         */
        public Object decode(Map map) {
            return new Color(
                    ((Number)map.get("red")).floatValue()/ 256, 
                    ((Number)map.get("green")).floatValue() / 256, 
                    ((Number)map.get("blue")).floatValue() / 256, 
                    ((Number)map.get("alpha")).floatValue() / 256);
        }

        
    }
    
    public SpecialTreatmentManager(){
        initialize();
    }
    
    void registerConstructor(String classname, String[] fields) {
        if (treatmentTable.containsKey(classname)){
            ((DefaultSpecialTreatment)treatmentTable.get(classname)).constructorFields = fields;
        }else{
            DefaultSpecialTreatment enc = new DefaultSpecialTreatment(classname);
            enc.constructorFields = fields;
            treatmentTable.put(classname, enc);
        }
    }
    
    void registerSpecialTreatment(String classname, DefaultSpecialTreatment specialTreatment){
        treatmentTable.put(classname, specialTreatment);
    }

    void removeProperty(String classname, String propertyname) {
        if (treatmentTable.containsKey(classname)){
            ((DefaultSpecialTreatment)treatmentTable.get(classname)).removeFields.add(propertyname);
        }else{
            DefaultSpecialTreatment enc = new DefaultSpecialTreatment(classname);
            enc.removeFields.add(propertyname);
            treatmentTable.put(classname, enc);
        }
    }
    
    public boolean needsSpecialTreatment(Object value){
        if (value instanceof Holder)
            return treatmentTable.containsKey(holderKey(value));
        else
            return treatmentTable.containsKey(value.getClass().getName());
    }
    
    public boolean needsSpecialTreatment(String classname){
        return treatmentTable.containsKey(classname);
    }
    
    public void encode(Object obj, YamlEncoder enc, String indent){
        ((DefaultSpecialTreatment)treatmentTable.get(obj.getClass().getName())).encode(obj, enc, indent);
    }
    
    public Class getPropertyType(Object obj, String key){
        return ((DefaultSpecialTreatment)treatmentTable.get(holderKey(obj))).getPropertyType(obj, key);
    }
    
    public void setProperty(Object obj, String key, Object value){
        ((DefaultSpecialTreatment)treatmentTable.get(holderKey(obj))).setProperty(obj, key, value);
    }
    
    public Object getHolder(String classname){
        return ((DefaultSpecialTreatment)treatmentTable.get(classname)).getHolder();
    }
    
    public Object getRealObject(Object holder){
        return ((DefaultSpecialTreatment)treatmentTable.get(holderKey(holder))).getRealObject(holder);
    }

    public String holderKey(Object holder){
        return ((Holder)holder).clazz.getName();
    }
    
    public void traverseAndCount(Object value, YamlEncoder enc){
        ((DefaultSpecialTreatment)treatmentTable.get(value.getClass().getName())).traverseAndCount(value, enc);
    }
    
    void initialize() {
        registerConstructor("java.awt.Point", new String[] { "x", "y" });
        registerConstructor("java.awt.Dimension", new String[] { "width",
                "height" });
        registerConstructor("java.awt.Rectangle", new String[] { "x", "y",
                "width", "height" });

        registerConstructor("java.awt.Font", new String[] { "name", "style",
                "size" });

        removeProperty("java.awt.Rectangle", "bounds");
        removeProperty("java.awt.Dimension", "size");
        removeProperty("java.awt.Point", "location");

        registerSpecialTreatment("java.awt.Color", new ColorSpecialTreatment("java.awt.Color"));
        registerConstructor("java.awt.Color", new String[] { "red", "green",
                "blue", "alpha" });
        registerConstructor("javax.swing.plaf.ColorUIResource", new String[]{"red", "green", "blue"});
    }



}
