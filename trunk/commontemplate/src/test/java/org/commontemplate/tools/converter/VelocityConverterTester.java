package org.commontemplate.tools.converter;

import junit.framework.TestCase;

public class VelocityConverterTester extends TestCase {

	private VelocityConverter velocityConverter = new VelocityConverter();

	public void testVariable() throws Exception {
		super.assertEquals("aaa ${user} bbb", velocityConverter.convert("aaa $user bbb"));
		super.assertEquals("aaa ${user.name} bbb", velocityConverter.convert("aaa $user.name bbb"));
		super.assertEquals("aaa ${user.name(xxx, yyy)} bbb", velocityConverter.convert("aaa $user.name($xxx, $yyy) bbb"));
	}

	public void testLineDirective() throws Exception {
		super.assertEquals("aaa $# xxx \n bbb", velocityConverter.convert("aaa ## xxx \n bbb"));
		super.assertEquals("aaa $# xxx $# yyy \n bbb", velocityConverter.convert("aaa ## xxx ## yyy \n bbb"));
		super.assertEquals("aaa $# xxx \n bbb $# xxx \n ccc", velocityConverter.convert("aaa ## xxx \n bbb ## xxx \n ccc"));
		super.assertEquals("aaa $# xxx $# yyy \n bbb $# xxx \n ccc", velocityConverter.convert("aaa ## xxx ## yyy \n bbb ## xxx \n ccc"));
	}

	public void testBlockDirective() throws Exception {
		super.assertEquals("aaa $* xxx *$ bbb", velocityConverter.convert("aaa #* xxx *# bbb"));
		super.assertEquals("aaa $* xxx $* yyy *$ bbb", velocityConverter.convert("aaa #* xxx #* yyy *# bbb"));
		super.assertEquals("aaa $* xxx *$ bbb $* xxx *$ ccc", velocityConverter.convert("aaa #* xxx *# bbb #* xxx *# ccc"));
		super.assertEquals("aaa $* xxx $* yyy *$ bbb $* xxx *$ ccc", velocityConverter.convert("aaa #* xxx #* yyy *# bbb #* xxx *# ccc"));
	}

	public void testForeachDirective() throws Exception {
		super.assertEquals("aaa $for{user : users} bbb $end ccc", velocityConverter.convert("aaa #foreach($user in $users) bbb #end ccc"));
		super.assertEquals("aaa $for{user : users} bbb $end ccc", velocityConverter.convert("aaa # foreach ( $user in $users ) bbb # end ccc"));
		super.assertEquals("aaa $for{user : users} bbb $end{}ccc", velocityConverter.convert("aaa #foreach($user in $users) bbb #{end}ccc"));
		super.assertEquals("aaa $for{user : users} bbb $end{}ccc", velocityConverter.convert("aaa # foreach ( $user in $users ) bbb # { end }ccc"));
	}

	public void testParseDirective() throws Exception {
		super.assertEquals("aaa $include{\"xxx.vm\"} bbb", velocityConverter.convert("aaa #parse(\"xxx.vm\") bbb"));
		super.assertEquals("aaa $include{\"xxx.vm\"} bbb", velocityConverter.convert("aaa # parse ( \"xxx.vm\" ) bbb"));
		super.assertEquals("aaa $include{\"xxx.vm\"} bbb", velocityConverter.convert("aaa #{parse}(\"xxx.vm\") bbb"));
		super.assertEquals("aaa $include{\"xxx.vm\"} bbb", velocityConverter.convert("aaa # { parse } ( \"xxx.vm\" ) bbb"));

		super.assertEquals("aaa $include{xxx} bbb", velocityConverter.convert("aaa #parse($xxx) bbb"));
		super.assertEquals("aaa $include{xxx} bbb", velocityConverter.convert("aaa # parse ( $xxx ) bbb"));
		super.assertEquals("aaa $include{xxx} bbb", velocityConverter.convert("aaa #{parse}($xxx) bbb"));
		super.assertEquals("aaa $include{xxx} bbb", velocityConverter.convert("aaa # { parse } ( $xxx ) bbb"));
	}

	public void testIncludeDirective() throws Exception {
		super.assertEquals("aaa $display{\"xxx.txt\"} bbb", velocityConverter.convert("aaa #include(\"xxx.txt\") bbb"));
		super.assertEquals("aaa $display{\"xxx.txt\"} bbb", velocityConverter.convert("aaa # include ( \"xxx.txt\" ) bbb"));
		super.assertEquals("aaa $display{\"xxx.txt\"} bbb", velocityConverter.convert("aaa #{include}(\"xxx.txt\") bbb"));
		super.assertEquals("aaa $display{\"xxx.txt\"} bbb", velocityConverter.convert("aaa # { include } ( \"xxx.txt\" ) bbb"));

		super.assertEquals("aaa $display{xxx} bbb", velocityConverter.convert("aaa #include($xxx) bbb"));
		super.assertEquals("aaa $display{xxx} bbb", velocityConverter.convert("aaa # include ( $xxx ) bbb"));
		super.assertEquals("aaa $display{xxx} bbb", velocityConverter.convert("aaa #{include}($xxx) bbb"));
		super.assertEquals("aaa $display{xxx} bbb", velocityConverter.convert("aaa # { include } ( $xxx ) bbb"));
	}

	public void testStopDirective() throws Exception {
		super.assertEquals("aaa $stop bbb", velocityConverter.convert("aaa #stop bbb"));
		super.assertEquals("aaa $stop bbb", velocityConverter.convert("aaa # stop bbb"));
		super.assertEquals("aaa $stop{}bbb", velocityConverter.convert("aaa #{stop}bbb"));
		super.assertEquals("aaa $stop{}bbb", velocityConverter.convert("aaa # { stop }bbb"));
	}

	public void testMacroDirective() throws Exception {
		super.assertEquals("aaa $macro{button} bbb", velocityConverter.convert("aaa #macro(button) bbb"));
		super.assertEquals("aaa $macro{button} bbb", velocityConverter.convert("aaa # macro ( button ) bbb"));
		super.assertEquals("aaa $macro{button} bbb", velocityConverter.convert("aaa #macro(button $name $title) bbb"));
		super.assertEquals("aaa $macro{button} bbb", velocityConverter.convert("aaa # macro ( button $ name $ title ) bbb"));
		super.assertEquals("aaa $macro{button} bbb", velocityConverter.convert("aaa #{macro}(button) bbb"));
		super.assertEquals("aaa $macro{button} bbb", velocityConverter.convert("aaa # { macro } ( button ) bbb"));
		super.assertEquals("aaa $macro{button} bbb", velocityConverter.convert("aaa #{macro}(button $name $title) bbb"));
		super.assertEquals("aaa $macro{button} bbb", velocityConverter.convert("aaa # { macro } ( button $ name $ title ) bbb"));
	}

	public void testElseDirective() throws Exception {
		super.assertEquals("aaa $else bbb", velocityConverter.convert("aaa #else bbb"));
		super.assertEquals("aaa $else bbb", velocityConverter.convert("aaa # else bbb"));
		super.assertEquals("aaa $else{}bbb", velocityConverter.convert("aaa #{else}bbb"));
		super.assertEquals("aaa $else{}bbb", velocityConverter.convert("aaa # { else }bbb"));
	}

	/*public void testSetDirective() throws Exception {
		super.assertEquals("aaa #set{xxx.yyy = mmm.nnn(iii, jjj)} bbb", velocityConverter.convert("aaa #set(xxx.yyy = mmm.nnn($iii, $jjj)) bbb"));
		super.assertEquals("aaa #set{xxx.yyy = mmm.nnn(iii, jjj)} bbb", velocityConverter.convert("aaa # set ( xxx.yyy = mmm.nnn ($iii, $jjj) ) bbb"));
		super.assertEquals("aaa #set{xxx.yyy = mmm.nnn(iii, jjj)} bbb", velocityConverter.convert("aaa #{set}(xxx.yyy = mmm.nnn($iii, $jjj)) bbb"));
		super.assertEquals("aaa #set{xxx.yyy = mmm.nnn(iii, jjj)} bbb", velocityConverter.convert("aaa # { set } ( xxx.yyy = mmm.nnn ($iii, $jjj) ) bbb"));
	}*/

}
