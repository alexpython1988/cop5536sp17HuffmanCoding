package edu.ufl.alexgre.project.threestructure;

public class HuffmanNode implements Comparable<HuffmanNode>{
	public int freq;
	public String tag;
	public HuffmanNode left;
	public HuffmanNode right;
	
	public HuffmanNode(String tag, int freq, HuffmanNode left, HuffmanNode right) {
	this.tag = tag;
	this.freq = freq;
	this.left = left;
	this.right = right;
}
	
	public int compareTo(HuffmanNode other){
		return this.freq - other.freq;
	}
	
	public boolean isLeaf(){
		return (left == null && right  == null);
	}

	@Override
	public String toString() {
		return "HuffmanNode [freq=" + freq + ", tag=" + tag + "]";
	}
	
}
