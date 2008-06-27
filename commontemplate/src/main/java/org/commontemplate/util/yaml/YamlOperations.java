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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * This interfaces contains all commonly used Yaml functions. {@link Yaml} contains all of these methods as 
 * static methods for convient access while {@link YamlConfig} implements this interface.
 *
 */
public interface YamlOperations {

	/**
	 * Loads one object from an input stream
	 * @param in the stream to read from
	 * @return the first object on the stream in Yaml format
	 */
    public Object load(InputStream in);

    /**
     * Loads one object from a file in Yaml format
     * @param file the file to read from
     * @return the first object in the file in Yaml format
     * @throws FileNotFoundException
     */
	public Object load(File file) throws FileNotFoundException;

	/**
	 * Loads one object from a string of Yaml text
	 * @param yamlText the text to read from
	 * @return the first object in the Yaml text
	 */
    public Object load(String yamlText);
    
    /**
     * Loads one object from an input stream of the specified type
     * @param  the specified type
     * @param in the stream to read from
     * @param clazz the class of the specified type
     * @return the first object in the stream in Yaml format
     */
    public Object loadType(InputStream in, Class clazz);
    
    /**
     * Loads one object from a file in Yaml format
     * @param  the specified type
     * @param file the file to read from
     * @param clazz the class of the specified type
     * @return the first object in the file in Yaml format
     * @throws FileNotFoundException
     */
    public Object loadType(File file, Class clazz) throws FileNotFoundException;
    	 
    /**
     * Loads one object from a file in Yaml format
     * @param  the specified type
     * @param yamlText the Yaml text
     * @param clazz the class of the specified type
     * @return the first object in the Yaml text
     */
    public Object loadType(String yamlText, Class clazz);

    /**
     * Loads the objects in input stream in Yaml format into a YamlStream, which is used to iterate the objects
     * in the input stream
     * @param in the stream to read from
     * @return a YamlStream for iterating the objects
     */
    public YamlStream loadStream(InputStream in);

    /**
     * Loads the objects in a file in Yaml format into a YamlStream, which is used to iterate the objects
     * in the file
     * @param file the file to read from
     * @return a YamlStream for iterating the objects
     * @throws FileNotFoundException
     */
    public YamlStream loadStream(File file)  throws FileNotFoundException;
    
    /**
     * Loads the objects in a Yaml text into a YamlStream, which is used to iterate the objects in the Yaml text
     * @param yamlText
     * @return a YamlStream for iterating the objects
     */
    public YamlStream loadStream(String yamlText);
    
    /**
     * Loads the objects of a specified type in an input stream in Yaml format into a YamlStream, which is used 
     * to iterate the objects in the input stream
     * @param  the specified type
     * @param in the stream to read from
     * @param clazz the class of the specified type
     * @return a YamlStream for iterating the objects
     */
    public  YamlStream loadStreamOfType(InputStream in, Class clazz);
    
    /**
     * Loads the objects of a specified type in a file in Yaml format into a YamlStream, which is used 
     * to iterate the objects in the file
     * @param  the specified type
     * @param file the file to read from
     * @param clazz the class of the specified type
     * @return a YamlStream for iterating the objects
     * @throws FileNotFoundException
     */
    public  YamlStream loadStreamOfType(File file, Class clazz) throws FileNotFoundException;
    
    /**
     * Loads the objects of a specified type in a  in Yaml format into a YamlStream, which is used 
     * to iterate the objects in the file
     * @param  the specified type
     * @param yamlText the text to read from
     * @param clazz the class of the specified type
     * @return a YamlStream for iterating the objects
     * @throws FileNotFoundException
     */
    public  YamlStream loadStreamOfType(String yamlText, Class clazz) throws FileNotFoundException;
    
    /**
     * Dumps an object to a file in Yaml format
     * @param obj the object to dump
     * @param file the file to dump to
     * @throws FileNotFoundException
     */
    public void dump(Object obj, File file) throws FileNotFoundException;

    /**
     * Dumps an object to a file in Yaml format
     * @param obj the object to dump
     * @param file the file to dump to
     * @param minimalOutput whether minimal output is on
     * @throws FileNotFoundException
     */
    public void dump(Object obj, File file, boolean minimalOutput) throws FileNotFoundException;

    /**
     * Dumps an object into Yaml format
     * @param obj the object to dump
     * @return a String in Yaml format representing the object
     */
    public String dump(Object obj);

    /**
     * Dumps an object into Yaml format
     * @param obj the object to dump
     * @param minimalOutput whether minimal output is on
     * @return a String in Yaml format representing the object
     */
    public String dump(Object obj, boolean minimalOutput);

    /**
     * Dumps a stream of objects specified with an iterator to a file in Yaml format, one document per object
     * @param iterator the iterator to read objects from
     * @param file the file to write to
     * @throws FileNotFoundException
     */
    public void dumpStream(Iterator iterator, File file) throws FileNotFoundException;

    /**
     * Dumps a stream of objects specified with an iterator to a file in Yaml format, one document per object
     * @param iterator the iterator to read objects from
     * @param file the file to write to
     * @param minimalOutput whether minimal output is on
     * @throws FileNotFoundException
     */
    public void dumpStream(Iterator iterator, File file, boolean minimalOutput) throws FileNotFoundException;
    
    /**
     * Dumps a stream of objects specified with an iterator to a String in Yaml format, one document per object
     * @param iterator the iterator to read objects from
     * @return a String in Yaml format representing the object
     */
    public String dumpStream(Iterator iterator);
    
    /**
     * Dumps a stream of objects specified with an iterator to a String in Yaml format, one document per object
     * @param iterator the iterator to read objects from
     * @param minimalOutput whether minimal output is on
     * @return a String in Yaml format representing the object
     */
    public String dumpStream(Iterator iterator, boolean minimalOutput);
	
}
