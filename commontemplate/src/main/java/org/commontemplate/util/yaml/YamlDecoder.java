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

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.commontemplate.util.yaml.parser.SyntaxException;
import org.commontemplate.util.yaml.parser.YamlParser;
import org.commontemplate.util.yaml.parser.YamlParserEvent;
import org.commontemplate.util.yaml.util.Logger;


/**
 * YamlDecoder - The usage of YamlDecoder mirrors that of java.beans.XMLDecoder. You create a decoder, make some 
 * calls to readObject, and then close the decoder. In most cases you may find it is not necessary to access this
 * class directly as {@link Yaml} contain methods for the most common usages. The utility functions that
 * were previously in this class are now in {@link Yaml}.
 *
 */
public class YamlDecoder {

	BufferedReader in;
    SpecialTreatmentManager stManager = new SpecialTreatmentManager();
    YamlParser parser;
    YamlConfig config = YamlConfig.getDefaultConfig();
    
    /**
     * This constructor is for bootstrapping in YamlConfig.
     * @param filename
     * @param config
     * @throws FileNotFoundException
     */
    YamlDecoder(String filename, YamlConfig config) throws FileNotFoundException{
    	this(filename);
    	this.config = config;
    }
    
    /**
     * This constructor is for bootstrapping in YamlConfig.
     * @param in
     * @param config
     * @throws FileNotFoundException
     */
    YamlDecoder(InputStream in, YamlConfig config){
    	this(in);
    	this.config = config;
    }
    
    /**
     * Creates a YamlDecoder that reads from specified stream.
     * @param in InputStream to read from.
     */
	public YamlDecoder(InputStream in){
		this.in = new BufferedReader(new InputStreamReader(in));
	}
    
    /**
     * Creates a YamlDecoder that reads from specifed file.
     * @param file file to read from.
     * @throws FileNotFoundException
     */
    public YamlDecoder(File file) throws FileNotFoundException{
        this(new FileInputStream(file));
    }
    
    /**
     * Creates a YamlDecoder that reads from specifed file.
     * @param filename name of file to read from.
     * @throws FileNotFoundException
     */
    public YamlDecoder(String filename) throws FileNotFoundException{
        this(new File(filename));
    }
    
    /**
     * Reads one object from the Yaml stream.
     * @return the next object in the Yaml stream.
     * @throws EOFException - if the end of the Yaml stream has been reached.
     */
	public Object readObject() throws EOFException{
		try {
			JYamlParserEvent event = new JYamlParserEvent(createLogger(), this);
			if (parser == null){
                parser = new YamlParser(in,event);
                firstDocument(parser, event);
            }else{
                parser.setEvent(event);
                if (!nextDocument(parser, event))
                    throw new EOFException();
            }
			Object ret = event.getBean();
            if (ret == null)
                throw new YamlParserException("Document is empty.");
            return ret;
		} catch (EOFException e) {
            throw e;
        } catch (Exception e){
			throw new YamlParserException(e);
		}
	}
	
	public YamlStream asStreamOfType(Class clazz){
		return new Stream(clazz);
	}
	
	public YamlStream asStream(){
		return new Stream(Object.class);
	}
	
    Logger createLogger(){
        if (isSuppressWarnings())
            return new Logger(Logger.Level.NONE);
        else
            return new Logger();
    }
    
    void firstDocument(YamlParser p, YamlParserEvent event) throws Exception{
        try {
            while (p.comment(-1,false));
            
            if (!p.header())
               p.document_first();
            else
                p.value_na(-1);
        }catch (SyntaxException e)
        {
            event.error(e,e.line);
        }
    }
    
    boolean nextDocument(YamlParser p, YamlParserEvent event) throws Exception{
        try {
            return p.document_next();
        }catch (SyntaxException e)
        {
            event.error(e,e.line);
            return false;
        }
    }
    
    /**
     * Returns the next object from the Yaml stream given the expected type.
     * @param  The type of the object expected.
     * @param clazz the class representing the type of the object expected.
     * @return next object from the Yaml stream given the expected type.
     */
	public Object readObjectOfType(Class clazz) throws EOFException{
		try {
			JYamlParserEvent event = new JYamlParserEvent(clazz, createLogger(), this);
            if (parser == null){
                parser = new YamlParser(in,event);
                firstDocument(parser, event);
            }else{
                parser.setEvent(event);
                if (!nextDocument(parser, event))
                    throw new EOFException();
            }
			Object ret = event.getBean();
            if (ret == null)
                throw new YamlParserException("Document is empty.");
            return ret;
        } catch (EOFException e) {
            throw e;
        } catch (Exception e){
            throw new YamlParserException(e);
        }
	}
	
    
    /**
     * Closes this decoder instance.
     *
     */
	public void close(){
		try {
			in.close();
		} catch (IOException e) {
		}
	}
	
  
    private class Stream implements YamlStream{
        Class clazz;
       Object buffer;
        
        Stream(Class clazz){
            this.clazz = clazz;
            peek();
        }

        /**
         * @param decoder
         * @param clazz
         */
        private void peek() {
            try{
                if (clazz == Object.class)
                    buffer = readObject();
                else
                    buffer = readObjectOfType(clazz);
            }catch(EOFException e){
                close();
                buffer = null;
            }
        }
        
        /* (non-Javadoc)
         * @see java.util.Iterator#hasNext()
         */
        public boolean hasNext() {
            return buffer != null;
        }

        /* (non-Javadoc)
         * @see java.util.Iterator#next()
         */
        public Object next() {
            if (!hasNext())
                throw new NoSuchElementException();
           Object ret = buffer;
            peek();
            return ret;
        }

        /* (non-Javadoc)
         * @see java.util.Iterator#remove()
         */
        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported.");
            
        }
        
        /*
         * implement iterator
         */
        public Iterator iterator() {
            return this;
        }
        
    }

    /**
     * returns whether the suppress warnings option is on
     * @return whether the suppress warnings option is on
     */
	public boolean isSuppressWarnings() {
		return config.isSuppressWarnings();
	}

	/**
	 * sets the suppress warnings option
	 * @param suppressWarnings true for on; false for off.
	 */
	public void setSuppressWarnings(boolean suppressWarnings) {
		config.setSuppressWarnings(suppressWarnings);
	}

	SpecialTreatmentManager getStManager() {
		return stManager;
	}

	void setStManager(SpecialTreatmentManager stManager) {
		this.stManager = stManager;
	}

	/**
	 * returns the Jyaml configuration for this Decoder
	 * @return a YamlConfig
	 */
	public YamlConfig getConfig() {
		return config;
	}

	/**
	 * set the Jyaml configuration for this decoder
	 * @param config the config object to set
	 */
	public void setConfig(YamlConfig config) {
		this.config = config;
	}
}
