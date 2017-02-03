import java.util.HashMap;
import java.util.Iterator;


public class TestMap {
	public static HashMap<String, String> OPERATOR = new HashMap<String, String>();
	public static HashMap<String, String> KEYWORDS = new HashMap<String, String>();
	public static HashMap<String, String> PROCEDURES = new HashMap<String, String>();
	public TestMap() {
		
	}
	
	public static void loadDictionary(){
		OPERATOR.put("(", "LP");
		OPERATOR.put(")", "RP");
		OPERATOR.put(":=", "ASGN");
		OPERATOR.put(";", "SC");
		OPERATOR.put("*", "MULTIPLICATIVE");
		OPERATOR.put("div", "MULTIPLICATIVE");
		OPERATOR.put("mod", "MULTIPLICATIVE");
		OPERATOR.put("+", "ADDITIVE");
		OPERATOR.put("-", "ADDITIVE");
		String[] list = {"=","!=","<",">","<=",">="};
		for (String s: list){
			OPERATOR.put(s, "COMPARE");
		}
		String[] key = {"if","then", "else", "begin", "end", "while", "do", "program", "var", "as", "int", "bool"};
		for (String s: key){
			KEYWORDS.put(s, s.toUpperCase());
		}
		
		PROCEDURES.put("writeInt", "WRITEINT");
		PROCEDURES.put("readInt", "READINT");
	}
	
	public static boolean isOperator(String key){
		return OPERATOR.containsKey(key);
	}

	
	public static boolean isKeyword(String key){
		return KEYWORDS.containsKey(key);
	}
	
	public static boolean isProcedure(String key){
		return PROCEDURES.containsKey(key);
	}
	
	public static void main(String[] args) {
		loadDictionary();
		String test = "while SQRT * SQRT <= N do ";
		String[] list = test.split("\\s");
	
		 
		   for (Object value : KEYWORDS.keySet()) {
			    System.out.println(value);
			}

		for (String token: list){
			if (isKeyword(token))
				System.out.println(KEYWORDS.get(token));
			else if (isOperator(token))
				System.out.println(OPERATOR.get(token) + "("+token+")");
			else if (isProcedure(token))
				System.out.println(PROCEDURES.get(token));
			else
				System.out.println("indent"+ "(" + token +")");
				
		}
		
		
		
	}
}
