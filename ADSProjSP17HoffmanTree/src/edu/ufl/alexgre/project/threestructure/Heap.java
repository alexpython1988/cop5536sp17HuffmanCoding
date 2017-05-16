package edu.ufl.alexgre.project.threestructure;

public abstract class Heap {
	
	public abstract void add(HuffmanNode node);
	
	public abstract HuffmanNode deletMin();
	
	public abstract int size();
	
	public abstract HuffmanNode getRoot();
	
	public HuffmanNode getNewHeapNodeInstance(String tag, int freq, HuffmanNode left, HuffmanNode right){
		return new HuffmanNode(tag, freq, left, right);
	}
	
	public HuffmanNode toHuffmanTrie(){
		for(; this.size() > 1;){
			HuffmanNode left = this.deletMin();
			HuffmanNode right = this.deletMin();
			HuffmanNode root = null;
			
			//root = new HuffmanNode(null, left.freq + right.freq, left, right);
			root = this.getNewHeapNodeInstance(null, left.freq + right.freq, left, right);
			this.add(root);
		}
		
		return this.getRoot();
	}
}
	
