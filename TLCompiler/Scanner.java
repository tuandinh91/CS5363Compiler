package TLCompiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
*
* @author Tuan Dinh, Xue Qin
*/
public class Scanner {
	public static HashMap<String, String> MULTIPLE_OPERATOR = new HashMap<String, String>();
	public static HashMap<String, String> SINGLE_OPERATOR = new HashMap<String, String>();
	public static HashMap<String, String> KEYWORDS = new HashMap<String, String>();
	public static HashMap<String, String> PROCEDURES = new HashMap<String, String>();
	public static TokenStream tokenStream = new TokenStream();
	public Scanner() {
		
	}
	
	public static void loadDictionary(){
		
		SINGLE_OPERATOR.put("(", "LP");
		SINGLE_OPERATOR.put(")", "RP");
		SINGLE_OPERATOR.put(":=", "ASGN");
		SINGLE_OPERATOR.put(";", "SC");
		
		MULTIPLE_OPERATOR.put("*", "MULTIPLICATIVE");
		MULTIPLE_OPERATOR.put("div", "MULTIPLICATIVE");
		MULTIPLE_OPERATOR.put("mod", "MULTIPLICATIVE");
		MULTIPLE_OPERATOR.put("+", "ADDITIVE");
		MULTIPLE_OPERATOR.put("-", "ADDITIVE");
		String[] list = {"=","!=","<",">","<=",">="};
		for (String s: list){
			MULTIPLE_OPERATOR.put(s, "COMPARE");
		}
		String[] key = {"if","then", "else", "begin", "end", "while", "do", "program", "var", "as", "int", "bool"};
		for (String s: key){
			KEYWORDS.put(s, s.toUpperCase());
		}
		
		PROCEDURES.put("writeInt", "WRITEINT");
		PROCEDURES.put("readInt", "READINT");
	}
	
	public static boolean isSingleOperator(String key){
		return SINGLE_OPERATOR.containsKey(key);
	}
	
	public static boolean isMultipleOperator(String key){
		return MULTIPLE_OPERATOR.containsKey(key);
	}

	
	public static boolean isKeyword(String key){
		return KEYWORDS.containsKey(key);
	}
	
	public static boolean isProcedure(String key){
		return PROCEDURES.containsKey(key);
	}
	
	public static boolean isComment(String key){
		return key.startsWith("%");
	}
	
	// The regular expression allows all natural numbers, 
	// but we are using 32-bit integers. 
	// Only 0 through 2147483647 are legal integer.
	public static boolean isNumber(String key){
		long num;
	    String regex = "\\d+";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(key);
	    if (matcher.matches()) {
	    	num = Long.valueOf(key);
	    	if (num < 2147483647 && num >= 0 )
	    		return true;
	    	else {
	    		System.err.println("Input number illegal, outside range from 0 to 2147483647");
	    		System.exit(1);
	    		return false;
	    	}
	    } else {
	    	return false;
	    }
	}
	
	public static boolean isBoollit(String key){
		if (key.equalsIgnoreCase("false") || key.equalsIgnoreCase("true"))
			return true;
		else
			return false;
	}
	
	public static boolean isIdent(String key){
	    String regex = "[a-zA-Z][a-zA-Z0-9]*";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(key);
	    return matcher.matches();
	}
	
	// Add extra space for all the operator in line.
	public static String addSpace(String oldLine){
		String newLine = new String();
		int newIndex = 0;
		int oldIndex = 0;
		
		oldLine = oldLine.replaceAll("\\(", " ( ");
		oldLine = oldLine.replaceAll("\\)", " ) ");
		oldLine = oldLine.replaceAll("\\:=", " := ");
		oldLine = oldLine.replaceAll("\\;", " ; ");
		oldLine = oldLine.replaceAll("\\*", " * ");
		oldLine = oldLine.replaceAll("\\+", " + ");
		oldLine = oldLine.replaceAll("\\-", " - ");
		oldLine = oldLine.replaceAll("\\<=", " <= ");
		oldLine = oldLine.replaceAll("\\>=", " >= ");
		oldLine = oldLine.replaceAll("\\!=", " != ");
		
		// add space for =,<,>
		newLine = oldLine;
		for (oldIndex = 0 ; oldIndex < oldLine.length(); ) {
			if(oldLine.substring(oldIndex, oldIndex + 1).equals("<")) {
				if ( (oldIndex + 1) < oldLine.length() ) {
					if (oldLine.substring(oldIndex + 1, oldIndex + 2).equals("=")){
					//find "<=", do nothing
						oldIndex++;
					}
					else {
						newLine = newLine.substring(0, newIndex) + " < " + oldLine.substring(oldIndex + 1);
						newIndex = newIndex + 3;
					}
				}
				else { // sentence end with <
					if (oldIndex == 0) { // sentence start with <
						newLine = " < ";
					}
					else { // sentence not start with <
						newLine = newLine.substring(0, newIndex) + " < ";
					}
					
				}				 
			}
			else if(oldLine.substring(oldIndex, oldIndex+1).equals(">")) {
				if ( (oldIndex + 1) < oldLine.length() ) {
					if (oldLine.substring(oldIndex + 1, oldIndex + 2).equals("=")){
					    //find ">=", do nothing
						oldIndex++;
					}
					else {
						newLine = newLine.substring(0, newIndex) + " > " + oldLine.substring(oldIndex + 1);
						newIndex = newIndex + 3;
					}
				}
				else { // sentence end with >
					if (oldIndex == 0) { // sentence start with >
						newLine = " > ";
					}
					else { // sentence not start with >
						newLine = newLine.substring(0,newIndex) + " > ";
					}
				}				 
			}
			else if(oldLine.substring(oldIndex, oldIndex + 1).equals("=")) {
				if ( (oldIndex -1) >= 0 ) { 
					if (oldLine.substring(oldIndex - 1, oldIndex).equals(":")){
					//find ":=", do nothing
					}
					else {
						newLine = newLine.substring(0, newIndex) + " = " + oldLine.substring(oldIndex + 1);
						newIndex = newIndex + 3;
					}
				}
				else { // sentence starts with =
					if ( (oldIndex + 1) >= oldLine.length()) {  // sentence end with =
						newLine = " = ";
					}
					else { // sentence not end with =
						newLine =  " = " + oldLine.substring(oldIndex + 1);
						newIndex = newIndex + 3;
					}
				}				 
			}
			else {
				newIndex++;
			}
			oldIndex++;
		}		
		return newLine;
	}
	

	public static void scan (String inputFilePath){
		
		loadDictionary();

		int line_num = 0;
		int token_num =0;
		
		
		try(BufferedReader br = new BufferedReader(new FileReader(inputFilePath+".tl"))) {
			
			
			//String tokenList = "";
			
		    for(String line; (line = br.readLine()) != null; ) {
		        // process the line.
				token_num = 0;
		    	line_num++;
				
				line = line.trim();
				if (line.equals(""))
					continue; //skip the empty line
				
				String[] list = addSpace(line).split("\\s+");
		    	
		    	for (String token: list){
					token_num++;
		    		if (isComment(token))
						break;
		    		else if (isSingleOperator(token))
		    			tokenStream.addToken(new Token(SINGLE_OPERATOR.get(token), null));  //print out SC, LP,RP
		    		else if (isBoollit(token))
		    			//boolit true/false	    
		    			tokenStream.addToken(new Token("boollit", token));
		    		else if (isNumber(token))
		    			//number 1-9
		    			tokenStream.addToken(new Token("num", token));
		    		else if (isKeyword(token))
		    			tokenStream.addToken(new Token(KEYWORDS.get(token), null));
		    		else if (isMultipleOperator(token))
		    			tokenStream.addToken(new Token(MULTIPLE_OPERATOR.get(token), token));    		
		    		else if (isProcedure(token))
		    			//its procedure
		    			tokenStream.addToken(new Token(PROCEDURES.get(token), null));    
		    		else if (isIdent(token))
		    			//its variable
		    			tokenStream.addToken(new Token("ident", token));   
		    		else {
		    			System.err.println("Illegal token found: " + token + ", line " +line_num +", token " +token_num);
			    		System.exit(1);
		    		}
		    			
		    			
		    	}
								
		    }			
			
			//put it to the file
			FileWriter writer = null;
			    try {
			        writer = new FileWriter(inputFilePath+".tok");
			        for (Token t : tokenStream.getTokens())
			        	writer.write(t + "\n");
			        writer.close();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			
		    // line is not visible here.
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 		
	}
}
