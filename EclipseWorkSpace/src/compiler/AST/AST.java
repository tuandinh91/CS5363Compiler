package compiler.AST;

import java.util.ArrayList;

import compiler.parserTree.PTreeNode;

public class AST {
	
	private ArrayList<String> sequence = new ArrayList<String>();
	private PTreeNode ptree = new PTreeNode();
	private int slength;
    private int spos;
	
	AST(ArrayList<String> sequence, PTreeNode ptree) {
		this.sequence = sequence;
		this.ptree = ptree;
		this.slength = sequence.size();
		spos = 0;
	}
	/*
	public ASTNode buildAST(String name) {
		
		ASTNode node = new ASTNode(name);
				
		/* program node 
		if (name.equals("program")){
			node.getChildren().add(buildAST("decl list"));
			node.getChildren().add(buildAST("stmt list"));
		}
		/* declarations nodes 
		if (name.equals("decl list")){
			for (int i = 0; i < this.slength; i++){
				if(this.sequence.get(i).equals("VAR")) {
					String tmpname = "decl:\'" + this.sequence.get(i+2) + "\':" + this.sequence.get(i+5);
					node.getChildren().add(new ASTNode(tmpname));
				}
			}
		}		
		/* statement list node 
		if (name.equals("stmt list")){
			
		}
		
		/* program node */
		/* program node */
		/* program node */
		/* program node */
		/* program node */
		/* program node */
		/* program node */
		/* program node */
		/* program node */
	
}
