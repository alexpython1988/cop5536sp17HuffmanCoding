package edu.ufl.alexgre.project.huffmancoding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

import edu.ufl.alexgre.project.threestructure.Heap;
import edu.ufl.alexgre.project.threestructure.HuffmanNode;

public class HuffmanEncoding {
	PrintWriter pw = null;
	String fileName = null;
	
	public HuffmanEncoding() {
		
	}
	
	public HuffmanEncoding(String file) {
		fileName = file.split("\\.")[0];
	}
	
	public  Hashtable<String, String> generateHuffmanCode(Heap heap, Hashtable<String, Integer> freqTable){
		for(Iterator<Entry<String, Integer>> itr = freqTable.entrySet().iterator(); itr.hasNext();){
			Entry<String, Integer> en = itr.next();
			heap.add(heap.getNewHeapNodeInstance(en.getKey(), en.getValue(), null, null));
		}
		System.out.println("Creating Huffman Trie...");
		HuffmanNode root = heap.toHuffmanTrie();
		
		System.out.println("Generating Huffman Code...");
//		StringBuffer sb = new StringBuffer();
//		StringBuffer sb1 = new StringBuffer();
//		System.out.println(genCodeForHeapLeaf(root, sb, sb1));
		Hashtable<String, String> codingTable = new Hashtable<String, String>(); 
		try {
			if(fileName == null)
				pw = new PrintWriter(new File("code_table.txt"));
			else 
				pw = new PrintWriter(new File(fileName + "_code_table.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String s = "";
		genCodeTable(root, s, " ", codingTable);
		
		pw.close();
//		printFrequencyTable1(codingTable);
		return codingTable;
	}
	
	//super slow!!!!!!
//	private String genCodeForHeapLeaf(HuffmanNode root, StringBuffer sb, StringBuffer sb1){
//		if(root.isLeaf()){
//			//System.out.println(sb.toString());
//			sb1.append(root.tag + " " + sb.toString() + "\n");
//		}else{
//			genCodeForHeapLeaf(root.left, sb.append("0"), sb1);
//			sb.deleteCharAt(sb.length() - 1);
//			genCodeForHeapLeaf(root.right, sb.append("1"), sb1);
//			sb.deleteCharAt(sb.length() - 1);
//		}
//		
//		return sb1.toString();
//	}

	private void genCodeTable(HuffmanNode root, String s, String bit, Hashtable<String, String> codingTable){
		if(root.isLeaf()){
			String output = root.tag + s + bit;
			codingTable.put(root.tag, s + bit);
			pw.println(output);
			return;
		}
		
		s = s + bit;
		genCodeTable(root.left, s, "0", codingTable);
		genCodeTable(root.right, s, "1", codingTable);
	}

//	private HuffmanNode generateHuffmanTree(Heap heap, Hashtable<String, Integer> freqTable){
//		for(Iterator<Entry<String, Integer>> itr = freqTable.entrySet().iterator(); itr.hasNext();){
//			Entry<String, Integer> en = itr.next();
//			heap.add(heap.getNewHeapNodeInstance(en.getKey(), en.getValue(), null, null));
//		}
//		
//		return heap.toHuffmanTrie();
//	}
	
	public Hashtable<String, Integer> generateFrequencyTableFromFile(String inputFile){
		Hashtable<String, Integer> ht = new Hashtable<String, Integer>();
		BufferedReader br = null;
	
		try{
			br = new BufferedReader(new FileReader(inputFile));
			
			String line = null;
			while((line = br.readLine()) != null){
				if(ht.containsKey(line)){
					ht.put(line, 1 + ht.get(line));
				}else{
					ht.put(line, 1);
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			CloseStream.close(br);
//			if(br != null){
//				try {
//					br.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
		}
		
		return ht;	
	}
	
	public void printFrequencyTable(Hashtable<String, Integer> ht){
		for(Iterator<Entry<String, Integer>> itr = ht.entrySet().iterator(); itr.hasNext();){
			Entry<String, Integer> en = itr.next();
			System.out.println(en.getKey() + " : " + en.getValue());
		}
	}
	
	public void printFrequencyTable1(Hashtable<String, String> ht){
		for(Iterator<Entry<String, String>> itr = ht.entrySet().iterator(); itr.hasNext();){
			Entry<String, String> en = itr.next();
			System.out.println(en.getKey() + " : " + en.getValue());
		}
	}
}
