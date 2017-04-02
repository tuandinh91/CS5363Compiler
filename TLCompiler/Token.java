package TLCompiler;

public class Token {

	public final String name;
	  public final String content;
	  

	  public Token(String name, String content)
	  {
	    super();
	    this.name = name;
	    this.content = content;
	  }
	  
	  public String toString(){
		  if (this.content == null)
			  return name;
		  else
			  return name + "("+content+")";
	  }
	  


}
