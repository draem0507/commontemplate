package org.commontemplate.util.yaml.parser;
public class SyntaxException extends Exception
{
	private static final long serialVersionUID = 5558455840467915604L;
		public int line;

        public SyntaxException() { super(); }
        public SyntaxException(String s) { super(s); }

        public SyntaxException(String s, int line)
        {
            super(s);
            this.line = line;
        }
}
