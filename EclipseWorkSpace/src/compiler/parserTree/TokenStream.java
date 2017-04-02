package compiler.parserTree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * 
 * @author Tuan, Xue
 * This is token stream class, feel free to add attributes and methods
 *
 */
public class TokenStream {
    private LinkedList<Token> tokenstream;
    private int length;
    private int currentpos;
    private boolean readthrough;
    
    public TokenStream() {
        this.tokenstream = new LinkedList<Token>();
        this.length = 0;
        this.currentpos = 0;
        this.readthrough = false;
    }
    
    public void addToken(Token t) {
        this.tokenstream.add(t);
        length++;
    }
    
    public LinkedList<Token> getTokenStream() {
        return this.tokenstream;
    }    
    
    public int getLength() {
        return this.length;
    }
    
    public int getPosition() {
        return this.currentpos;
    }
    
    public void resetPosition() {
    	this.currentpos = 0;
    }
    
    public void movePosition(int step){
    	this.currentpos = this.currentpos + step;
    }
    
    public Token readToken() {
    	if (this.length - this.currentpos >= 1) {
    		return this.tokenstream.get(this.currentpos);
    	}
    	else {
    		System.out.println("Current Token Stream has been read through.");
    		readthrough = true;
    		return null;
    	}
    }
    
    public void popToken() {
    	currentpos++;
    }
    
    public boolean readThrough() {
    	return this.readthrough;
    }

    public void genTokenStream( String path ) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(path));
    	String line;
    	while ((line = br.readLine()) != null) {
    	    this.tokenstream.add(new Token(line));
    	    this.length++;
    	}
    	br.close();
    }

}
