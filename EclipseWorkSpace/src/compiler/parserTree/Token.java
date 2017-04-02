package compiler.parserTree;

public class Token {
	
	private final String name;
	private final String content;
	
	public Token(String str) {
		if (str.indexOf("(") == -1) {
			this.name = str;
		    this.content = "";
		}
		else {
			this.name = str.substring(0, str.indexOf("("));
			this.content = str.substring(str.indexOf("(")+1, str.length()-1);
		}
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public String toSring() {
		if(this.content == "")
			return this.name;
		else
            return this.name + "(" + this.content + ")";
	}
	
}
