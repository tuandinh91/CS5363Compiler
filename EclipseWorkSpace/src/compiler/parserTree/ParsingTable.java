package compiler.parserTree;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Xue 
 * This is the grammar table.
 *
 */
public class ParsingTable {
    private final HashMap<String, Integer> nonterminalmap = new HashMap<String, Integer>();
    private final ArrayList<HashMap<String, String>> productionmap = new ArrayList<HashMap<String, String>>();
    
    public ParsingTable() {
    	
    	// Map non-terminal to Array list index
    	nonterminalmap.put("<program>", 0);
    	nonterminalmap.put("<declarations>", 1);
    	nonterminalmap.put("<type>", 2);
    	nonterminalmap.put("<statementSequence>", 3);
    	nonterminalmap.put("<statement>", 4);
    	nonterminalmap.put("<assignment>", 5);
    	nonterminalmap.put("<rest>", 6);
    	nonterminalmap.put("<ifStatement>", 7);
    	nonterminalmap.put("<elseClause>", 8);
    	nonterminalmap.put("<whileStatement>", 9);
    	nonterminalmap.put("<writeInt>", 10);
    	nonterminalmap.put("<expression>", 11);
    	nonterminalmap.put("<expressionRecur>", 12);
    	nonterminalmap.put("<simpleExpression>", 13);
    	nonterminalmap.put("<simpleExpressionRecur>", 14);
    	nonterminalmap.put("<term>", 15);
    	nonterminalmap.put("<termRecur>", 16);
    	nonterminalmap.put("<factor>", 17);
    	
    	// Adding all possible productions to each non-terminal
    	//<program>
    	HashMap<String, String> tempmap1 = new HashMap<String, String>();
    	tempmap1.put("PROGRAM", "PROGRAM <declarations> BEGIN <statementSequence> END");
    	productionmap.add(tempmap1);
    	
    	//<declarations>
    	HashMap<String, String> tempmap2 = new HashMap<String, String>();
    	tempmap2.put("VAR", "VAR ident AS <type> SC <declarations>");
    	tempmap2.put("BEGIN", "epsilon");
    	productionmap.add(tempmap2);
    	
    	//<type>
    	HashMap<String, String> tempmap3 = new HashMap<String, String>();
    	tempmap3.put("INT", "INT");
    	tempmap3.put("BOOL", "BOOL");
    	productionmap.add(tempmap3);
    	
    	//<statementSequence>
    	HashMap<String, String> tempmap4 = new HashMap<String, String>();
    	tempmap4.put("END", "epsilon");
    	tempmap4.put("ident", "<statement> SC <statementSequence>");
    	tempmap4.put("SC", "<statement> SC <statementSequence>");
    	tempmap4.put("IF", "<statement> SC <statementSequence>");
    	tempmap4.put("WHILE", "<statement> SC <statementSequence>");
    	tempmap4.put("WRITEINT", "<statement> SC <statementSequence>");
    	tempmap4.put("ELSE", "epsilon");
    	productionmap.add(tempmap4);
    	
    	//<statement>
    	HashMap<String, String> tempmap5 = new HashMap<String, String>();
    	tempmap5.put("ident", "<assignment>");
    	tempmap5.put("IF", "<ifStatement>");
    	tempmap5.put("WHILE", "<whileStatement>");
    	tempmap5.put("WRITEINT","<writeInt>");
    	productionmap.add(tempmap5);
    	
    	//<assignment>
    	HashMap<String, String> tempmap6 = new HashMap<String, String>();
    	tempmap6.put("ident", "ident ASGN <rest>");
    	productionmap.add(tempmap6);
    	
    	//<rest>
    	HashMap<String, String> tempmap7 = new HashMap<String, String>();
    	tempmap7.put("ident", "<expression>");
    	tempmap7.put("READINT", "READINT");
    	tempmap7.put("num", "<expression>");
    	tempmap7.put("boollit", "<expression");
    	tempmap7.put("LP", "<expression>");
    	productionmap.add(tempmap7);
    	
    	//<ifStatement>
    	HashMap<String, String> tempmap8 = new HashMap<String, String>();
    	tempmap8.put("IF", "IF <expression> THEN <statementSequence> <elseClause> END");
    	productionmap.add(tempmap8);
    	
    	//<elseClause>
    	HashMap<String, String> tempmap9 = new HashMap<String, String>();
    	tempmap9.put("END", "epsilon");
    	tempmap9.put("ELSE", "ELSE <statementSequence>");
    	productionmap.add(tempmap9);
    	
    	//<whileStatement>
    	HashMap<String, String> tempmap10 = new HashMap<String, String>();
    	tempmap10.put("WHILE", "WHILE <expression> DO <statementSequence> END");
    	productionmap.add(tempmap10);
    	
    	//<writeInt>
    	HashMap<String, String> tempmap11 = new HashMap<String, String>();
    	tempmap11.put("WRITEINT", "WRITEINT <expression>");
    	productionmap.add(tempmap11);
    	
    	//<expression>
    	HashMap<String, String> tempmap12 = new HashMap<String, String>();
    	tempmap12.put("ident", "<simpleExpression> <expressionRecur>");
    	tempmap12.put("num", "<simpleExpression> <expressionRecur>");
    	tempmap12.put("boollit", "<simpleExpression> <expressionRecur>");
    	tempmap12.put("LP", "<simpleExpression> <expressionRecur>");
    	productionmap.add(tempmap12);
    	
    	//<expressionRecur>
    	HashMap<String, String> tempmap13 = new HashMap<String, String>();
    	tempmap13.put("END", "epsilon");
    	tempmap13.put("SC", "epsilon");
    	tempmap13.put("THEN", "epsilon");
    	tempmap13.put("ELSE", "epsilon");
    	tempmap13.put("DO", "epsilon");
    	tempmap13.put("RP", "epsilon");
    	tempmap13.put("COMPARE", "COMPARE <simpleExpression> <expressionRecur>");
    	productionmap.add(tempmap13);
    	
    	//<simpleExpression>
    	HashMap<String, String> tempmap14 = new HashMap<String, String>();
    	tempmap14.put("ident", "<term> <simpleExpressionRecur>");
    	tempmap14.put("num", "<term> <simpleExpressionRecur>");
    	tempmap14.put("boollit", "<term> <simpleExpressionRecur>");
    	tempmap14.put("LP", "<term> <simpleExpressionRecur>");
    	productionmap.add(tempmap14);
    	
    	//<simpleExpressionRecur>
    	HashMap<String, String> tempmap15 = new HashMap<String, String>();
    	tempmap15.put("END", "epsilon");
    	tempmap15.put("SC", "epsilon");
    	tempmap15.put("THEN", "epsilon");
    	tempmap15.put("ELSE", "epsilon");
    	tempmap15.put("DO", "epsilon");
    	tempmap15.put("RP", "epsilon");
    	tempmap15.put("COMPARE", "epsilon");
    	tempmap15.put("ADDITIVE", "ADDITIVE <term> <simpleExpressionRecur>");
    	productionmap.add(tempmap15);
    	
    	//<term>
    	HashMap<String, String> tempmap16 = new HashMap<String, String>();
    	tempmap16.put("ident", "<factor> <termRecur>");
    	tempmap16.put("num", "<factor> <termRecur>");
    	tempmap16.put("boollit", "<factor> <termRecur>");
    	tempmap16.put("LP", "<factor> <termRecur>");
    	productionmap.add(tempmap16);
    	
    	//<termRecur>
    	HashMap<String, String> tempmap17 = new HashMap<String, String>();
    	tempmap17.put("END", "epsilon");
    	tempmap17.put("SC", "epsilon");
    	tempmap17.put("THEN", "epsilon");
    	tempmap17.put("ELSE", "epsilon");
    	tempmap17.put("DO", "epsilon");
    	tempmap17.put("RP", "epsilon");
    	tempmap17.put("COMPARE", "epsilon");
    	tempmap17.put("ADDITIVE", "epsilon");
    	tempmap17.put("MULTIPLICATIVE", "MULTIPLICATIVE <factor> <termRecur>");
    	productionmap.add(tempmap17);
    	
    	//<factor>
    	HashMap<String, String> tempmap18 = new HashMap<String, String>();
    	tempmap18.put("ident", "ident");
    	tempmap18.put("num", "num");
    	tempmap18.put("boollit", "boollit");
    	tempmap18.put("LP", "LP <expression> RP");
    	productionmap.add(tempmap18);
    }
    
    public String getProduction(String nonterm, String term) {
    	int index;
    	String production = "";
    	
    	if (nonterminalmap.get(nonterm) != null) {
    		index = nonterminalmap.get(nonterm).intValue();
    		if(productionmap.get(index).get(term) != null) {
    			production = productionmap.get(index).get(term);
    		} else {
    			production = "notfound";
    		}
    	} else {
    		index = -1;
    		production = "notfound";
    	}   	
    	return production;   	
    }
    
}
