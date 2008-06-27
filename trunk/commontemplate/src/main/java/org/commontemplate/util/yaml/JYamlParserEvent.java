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

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.commontemplate.util.yaml.parser.YamlParserEvent;
import org.commontemplate.util.yaml.util.Logger;



class JYamlParserEvent extends YamlParserEvent {

	Stack stack = new Stack();
	Map aliasMap = new HashMap();
	
	public JYamlParserEvent(Logger logger, YamlDecoder decoder){
		stack.push(new NoneState(aliasMap, stack, decoder, logger));
	}
	
	public JYamlParserEvent(Class clazz, Logger logger, YamlDecoder decoder){
		this(logger, decoder);
		String classname = ReflectionUtil.className(clazz);
        ((State)stack.peek()).setDeclaredClassname(classname);
        if (decoder.getStManager().needsSpecialTreatment(classname))
        	((State)stack.peek()).setObj(decoder.getStManager().getHolder(classname));
        else if (!clazz.isArray() && !ReflectionUtil.isSimpleType(clazz))
	        try {
	            ((State)stack.peek()).setObj(clazz.newInstance());
	        } catch (Exception e){
	            throw new YamlParserException("Can't instantiate object of type " + clazz.getName());
	        }
        
	}
	
	
	public void content(String a, String b) {
		((State)stack.peek()).nextOnContent(a, b);
	}

	
	public void error(Exception e, int line) {
        throw new YamlParserException("Error near line " + line + ": " + e);
	}

	
	public void event(int c) {
		((State)stack.peek()).nextOnEvent(c);
	}

	
	public void property(String a, String b) {
		((State)stack.peek()).nextOnProperty(a, b);
	}

	public Object getBean(){
		return ((State)stack.peek()).getObj();
	}
	
}
