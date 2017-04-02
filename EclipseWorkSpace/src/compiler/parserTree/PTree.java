package compiler.parserTree;

import java.util.ArrayList;

/**
 * 
 * @author Xue
 * This class is mainly about building a parser tree
 * The building order has been stored in astsequence
 */
public class PTree {
	
	private final ParsingTable grammar = new ParsingTable();
	private ArrayList<String> astsequence = new ArrayList<String>();
		
	public PTreeNode buildPTree(TokenStream ts, String name) {
		
		PTreeNode node = new PTreeNode(name);

		if (name.startsWith("<") ){ //non-terminals node
			if (ts.readThrough() == true) {
				System.out.println("TokenStream finished with half build tree, check your code.");
				return null;
			}
			String prod = this.grammar.getProduction(name, ts.readToken().getName());
			if (prod.equalsIgnoreCase("notfound")) {
				System.out.println("non-terminal not mtach, please check your code");
				return null;
			}else {
				this.astsequence.add(name);
				String[] namelist = prod.split("\\s");
				for (String childname : namelist) {
					node.getChildren().add(buildPTree(ts, childname));
				}
			}
		} else { // terminals node
			if (ts.readThrough() == true) {
				System.out.println("TokenStream finished with half build tree, check your code.");
				return null;
			}
			if (name.equals("epsilon")) {
				return node;
			}
			else if(ts.readToken().getContent().isEmpty()){
				if (name.equals(ts.readToken().getName())) {
					ts.popToken();
					this.astsequence.add(name);
					return node;
				} else {
					System.out.println("terminals not match, please check your code");
					return null;
				}
			} else { 
				if (name.equals(ts.readToken().getName())) {						
					PTreeNode var = new PTreeNode(ts.readToken().getContent());
					node.getChildren().add(var);
					ts.popToken();
					this.astsequence.add(name);
					this.astsequence.add(var.getName());
					return node;
				} else {
					System.out.println("terminals with var not match, please check your code");
					return null;
				}
			}					
		}
		
		return node;
	}
	
	public ArrayList<String> getASTSequence() {
		return this.astsequence;
	}
}
