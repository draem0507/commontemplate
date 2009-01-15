package org.commontemplate.util;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public final class XmlUtils {
    
    private XmlUtils() {}
    
    public static String toXml(Object bean) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        XMLEncoder xe = new XMLEncoder(bo);
        try {
            xe.writeObject(bean);
            xe.flush();
        } finally {
            xe.close();
        }
        return new String(bo.toByteArray());
    }

    public static String toXStream(Object bean) {
        return new XStream(new DomDriver()).toXML(bean);
    }

}
