package compiler.main;

import compiler.scanner.Scanner;
import compiler.typeCheck.DeclList;

import java.io.FileNotFoundException;
import java.io.IOException;

import compiler.AST.*;
import compiler.parserTree.*;

/**
*
* @author Tuan Dinh, Xue Qin
*/

public class TLCompiler {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		String base = "";
		if (args[0]!= null && args[0].endsWith(".tl") || args[0].endsWith(".TL"))
			base = args[0].substring(0, args[0].length() - 3);
		Scanner.scan(base);
			
		TokenStream ts = new TokenStream();
		ts.genTokenStream(base);
		DeclList dl = new DeclList(ts);
	    PTree ptree = new PTree();
	    PTreeNode head = ptree.buildPTree(ts, "<program>");
	    
	}

}
