package edu.ufl.alexgre.project.threestructure;

import java.util.ArrayList;

public class pairingHeap extends Heap{
	private PairingHeapNode root = null;
	private int size = 0;
	private ArrayList<PairingHeapNode> children = null;

	@Override
	public void add(HuffmanNode node) {
		if(node == null)
			return;
		
		if(root == null){
			root = (PairingHeapNode) node;
			size++;
			return;
		}
		
		root = meld(root, (PairingHeapNode) node);
		size++;
	}

	private PairingHeapNode meld(PairingHeapNode cRoot, PairingHeapNode node) {
		if(cRoot.compareTo(node) <= 0){
			if(cRoot.child == null)
				cRoot.child = node;
			else{
				node.next = cRoot.child;
				cRoot.child.prev = node;
				cRoot.child = node;
			}
			return cRoot;
		}else{
			if(node.child == null)
				node.child = cRoot;
			else{
				cRoot.next = node.child;
				node.child.prev = cRoot;
				node.child = cRoot;
			}
			return node;
		}
	}

	@Override
	public HuffmanNode deletMin() {
		if(root == null)
			throw new UnderflowException("The heap is empty!");
		
		if(size == 1){
			HuffmanNode oldRoot = root;
			root = null;
			size--;
			return oldRoot;
		}
		
		children = new ArrayList<PairingHeapNode>();
		PairingHeapNode start = root.child;
		for(; start != null;){
			children.add(start);
			start = start.next;
		}
		
		//System.out.println(children.size());
		HuffmanNode oldRoot = root;
		root = null;
		size--;
		root = twoPassMerge(children);
		return oldRoot;
	}

	private PairingHeapNode twoPassMerge(ArrayList<PairingHeapNode> children) {
		//recursion out of memory
//		if(children.size() == 1)
//			return children.get(0);
//		ArrayList<PairingHeapNode> newChildren = new ArrayList<PairingHeapNode>();		
//		for(int i = 0; i < children.size() - 1; i += 2){
//			newChildren.add(meld(children.get(i), children.get(i + 1)));
//		}
//		if(children.size() % 2 == 1)
//			newChildren.add(children.get(children.size() - 1));
//		return twoPassMerge(newChildren);
		
		//iteration	slow!!!
		for(; children.size() > 1;){
			PairingHeapNode first = children.remove(0);
			PairingHeapNode second = children.remove(0);
			first.reset();
			second.reset();
			children.add(meld(first, second));
			//System.out.println(children.size());
		}
		
		
		
		return children.get(0);
	}

//	private PairingHeapNode merge(PairingHeapNode first, PairingHeapNode second) {
//		
//		return null;
//	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public HuffmanNode getRoot() {
		return root;
	}
	
	@Override
	public String toString() {
		if(size == 0)
			return "Empty heap";
		
		StringBuffer sb = new StringBuffer();
		paringHeapToString(root, sb, 0, 0);
		return sb.toString();
	}

	private void paringHeapToString(PairingHeapNode r, StringBuffer sb, int flag, int level) {
		if(flag == 1)
			sb.append(level + "child: " + r.tag + " : " + r.freq + "\t");
		else if(flag == 2)
			sb.append(level + "next: " + r.tag + " : " + r.freq + "\t");
		else
			sb.append("root: " + r.tag + " : " + r.freq + "\t");
		if(r.child != null)
			paringHeapToString(r.child, sb, 1, level + 1);
		if(r.next != null)
			paringHeapToString(r.next, sb, 2, level);
	}
	
	@Override
	public PairingHeapNode getNewHeapNodeInstance(String tag, int freq, HuffmanNode left, HuffmanNode right){
		return new PairingHeapNode(tag, freq, left, right, null, null, null);
	}

	class PairingHeapNode extends HuffmanNode{
		PairingHeapNode child;
		PairingHeapNode prev;
		PairingHeapNode next;
		
		public PairingHeapNode(String tag, int freq, HuffmanNode left, HuffmanNode right, PairingHeapNode child,
				PairingHeapNode prev, PairingHeapNode next) {
			super(tag, freq, left, right);
			this.child = child;
			this.prev = prev;
			this.next = next;
		}

		public void reset() {
			this.prev = null;
			this.next = null;
		}
		
//		public PairingHeapNode(String tag, int freq, HuffmanNode left, HuffmanNode right) {
//			super(tag, freq, left, right);
//			this.child = null;
//			this.prev = null;
//			this.next = null;
//		}
	}
}
