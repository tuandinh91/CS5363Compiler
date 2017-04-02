package compiler.typeCheck;

import java.util.HashMap;

import compiler.parserTree.*;

/**
 * @author Xue
 * This class is used to generate all the declarations in token stream.
 * They are stored in declmap with (var, type) format.
 */
public class DeclList {
	
	private final HashMap<String, String> declmap = new HashMap<String, String>();
	
	public DeclList(TokenStream ts) {
		ts.resetPosition();
		String var = "";
		while(!ts.readToken().getName().equals("BEGIN")) { 
			System.out.println(ts.readToken().getName());
			if(ts.readToken().getName().equals("VAR")) {
				ts.movePosition(1);
				var = ts.readToken().getContent();
				ts.movePosition(2);
				declmap.put(var, ts.readToken().getName());
			}
			ts.popToken();
		}
		ts.resetPosition();
	}
}
