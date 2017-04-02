package TLCompiler;

import java.util.LinkedList;
/**
 * This is the class based on the lecture
 * @author Tuan 
 *
 */
public class TokenStream {
	public LinkedList<Token> tokens;
	public Token lookahead;
	public int pos;
	
	public TokenStream(){
		tokens = new LinkedList<Token>();
		pos =0;
	}
	
	public void addToken(Token t){
		tokens.add(t);
	}
	
	public LinkedList<Token> getTokens(){
		return tokens;
	}
}
