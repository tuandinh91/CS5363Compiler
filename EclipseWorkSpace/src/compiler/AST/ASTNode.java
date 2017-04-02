package compiler.AST;

import java.util.ArrayList;

public class ASTNode {
	
	 private String name;
	 private ArrayList<ASTNode> children = new ArrayList<>();
	 private ASTNode parent;
	 
	 public ASTNode() {
		 
	 }
	 public ASTNode(String name) {
		 this.name = name;
	 }	 
	 public ASTNode(ASTNode parent, String name) {
	     this.parent = parent;
	     this.name = name;	     
	 }
	 	 
	 public String getName() {
	     return this.name;
	 }
	 public void setName(String name) {
		 this.name = name;
	 }
	 
	 public ArrayList<ASTNode> getChildren() {
	     return children;
	 }
	 
	 public ASTNode getParent() {
	     return parent;
	 }
	 
}