package compiler.parserTree;

import java.util.ArrayList;

/**
 * 
 * @author Xue 
 * This class is the basic node of parser tree.
 *
 */
public class PTreeNode {
	
	 private String name;
	 private ArrayList<PTreeNode> children = new ArrayList<>();
	 private PTreeNode parent;
	 
	 public PTreeNode() {
		 
	 }
	 public PTreeNode(String name) {
		 this.name = name;
	 }	 
	 public PTreeNode(PTreeNode parent, String name) {
	     this.parent = parent;
	     this.name = name;	     
	 }
	 	 
	 public String getName() {
	     return this.name;
	 }
	 public void setName(String name) {
		 this.name = name;
	 }
	 
	 public ArrayList<PTreeNode> getChildren() {
	     return children;
	 }
	 
	 public PTreeNode getParent() {
	     return parent;
	 }
	 
}
