package edu.ufl.alexgre.project.threestructure;

import java.util.ArrayList;

public class FourWayHeap extends Heap{
	ArrayList<HuffmanNode> heap = new ArrayList<HuffmanNode>();
	private static final int dary = 4;

	@Override
	public void add(HuffmanNode node) {
		if(node == null)
			return;
		
		HuffmanNode fNode = node;
		heap.add(fNode);
		heafyUp(heap.size() - 1, fNode);
	}

	private void heafyUp(int index, HuffmanNode fNode) {
		for(; index > 0;){
			int pIndex = (index - 1) / dary;
			
			if(fNode.compareTo(heap.get(pIndex)) >= 0){
				break;
			}
			
			swap(heap, pIndex, index);
			index = pIndex;
		}
	}
	
	private void heafydown(int index, HuffmanNode fNode) {
		int size = heap.size();
		HuffmanNode cMin = null;
		
		for(;;){
			int cIndex = index * dary + 1;
			
			if(cIndex >= size)
				break;
			
			cMin = heap.get(cIndex);
			
			int increment = 0;
			for(int k = 1; k < dary; k++){
				if((cIndex + k) >= size)
					break;
		
				HuffmanNode curChild = heap.get(cIndex + k);
				if(cMin.compareTo(curChild) > 0){
					cMin = curChild;
					increment = k;
				}
			}
			
			cIndex = cIndex + increment;
			
			if(fNode.compareTo(cMin) < 0)
				break;
			
			swap(heap, index, cIndex);
			index  = cIndex;
		}
		
	}
	
	private void swap(ArrayList<HuffmanNode> heap, int pIndex, int cIndex){
		HuffmanNode temp = heap.get(pIndex);
		heap.set(pIndex, heap.get(cIndex));
		heap.set(cIndex, temp);
	}

	@Override
	public HuffmanNode deletMin() {
		int size = heap.size();
		
		if(size == 0)
			throw new UnderflowException("The heap is empty!");
		
		if(size == 1)
			return heap.remove(0);
		
		HuffmanNode min = heap.get(0);
		heap.set(0, heap.remove(size - 1));
		heafydown(0, heap.get(0));
		
		return min;
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
}
