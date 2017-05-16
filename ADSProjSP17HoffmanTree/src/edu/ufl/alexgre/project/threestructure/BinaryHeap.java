package edu.ufl.alexgre.project.threestructure;

import java.util.ArrayList;

public class BinaryHeap extends Heap{
	ArrayList<HuffmanNode> heap = new ArrayList<HuffmanNode>();
	
	@Override
	public void add(HuffmanNode node) {
		if(node == null)
			return;
		HuffmanNode bNode = node;
		heap.add(bNode);
		int index = heap.size() - 1;
		heapfyUpward(index, bNode);
	}

	@Override
	public HuffmanNode deletMin() {
		if(heap.size() == 0)
			throw new UnderflowException("The heap is empty!");
		
		if(heap.size() == 1)
			 return heap.remove(0);
		
		HuffmanNode oldRoot = heap.get(0);
		heap.set(0, heap.remove(heap.size() - 1));
		heapfyDownward(0, heap.get(0));
		
		return oldRoot;
	}
	

	private void heapfyUpward(int index, HuffmanNode bNode) {
		for(; index > 0;){
			int pIndex = (index - 1) / 2;
			if(bNode.compareTo(heap.get(pIndex)) >= 0){
				break;
			}
			
			swap(heap, index, pIndex);
			
			index = pIndex;
		}
		
	}
	
	private void heapfyDownward(int index, HuffmanNode node){
		for(; index < heap.size() / 2;){
			int cIndex = index * 2 + 1;
			
			if(cIndex < heap.size() - 1 && heap.get(cIndex).compareTo(heap.get(cIndex + 1)) > 0){
				cIndex++;
			}
			
			if(node.compareTo(heap.get(cIndex)) <= 0){
				break;
			}
			
			swap(heap, index, cIndex);
			
			index = cIndex;	
		}
	}
	
	private void swap(ArrayList<HuffmanNode> heap, int pIndex, int cIndex){
		HuffmanNode temp = heap.get(pIndex);
		heap.set(pIndex, heap.get(cIndex));
		heap.set(cIndex, temp);
	}
	
	@Override
	public int size() {
		return heap.size();
	}

	@Override
	public HuffmanNode getRoot() {
		return heap.size() == 0 ? null : heap.get(0);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for(HuffmanNode hn: heap){
			sb.append(hn.tag + " : " + hn.freq + " ");
		}
		
		sb.append("\n");
		
		return sb.toString();
	}
	
	
//	
//	public BinaryHeapNode getNewBinaryHeapNode(String tag, int freq, BinaryHeapNode left, BinaryHeapNode right){
//		return new BinaryHeapNode(tag, freq, left, right);
//	}
//
//	class BinaryHeapNode extends Node{
//		public String tag;
//		BinaryHeapNode left;
//		BinaryHeapNode right;
//		
//		public BinaryHeapNode(String tag, int freq, BinaryHeapNode left, BinaryHeapNode right) {
//			this.tag = tag;
//			this.freq = freq;
//			this.left = left;
//			this.right = right;
//		}
//		
//		public boolean isLeaf(){
//			return (left == null && right == null);
//		}
//	}

}
