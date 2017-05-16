package edu.ufl.alexgre.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

import edu.ufl.alexgre.project.huffmancoding.HuffmanEncoding;
import edu.ufl.alexgre.project.threestructure.BinaryHeap;
import edu.ufl.alexgre.project.threestructure.FourWayHeap;
import edu.ufl.alexgre.project.threestructure.Heap;
import edu.ufl.alexgre.project.threestructure.HuffmanNode;
import edu.ufl.alexgre.project.threestructure.pairingHeap;

public class TestBuildHuffmanTreeEfficiency {
	//static String input = "sample_input_small.txt";
	//static String input = "sample_input_large.txt";
	//static String input = "test2.txt";
	//static String input = "test3.txt";
	static String input = "test4.txt";
	static HuffmanEncoding he = new HuffmanEncoding();
	
	public static void main(String[] args) {
//		BufferedReader br = null;
//		BufferedWriter bw = null;
//		OutputStream os = null;
//		try {
		Hashtable<String, Integer> ht = he.generateFrequencyTableFromFile(input);
//		HuffmanEncoding hfe = new HuffmanEncoding();
//		hfe.printFrequencyTable(ht);
		
			for(int i = 1; i < 4; i++){
				//String inputFile = "sample_input_small.txt";
				//String inputFile = "sample_input_large.txt";
//				String[] str = input.split("\\.");
//				String codeTable = i + str[0] + "_code_table." + str[1]; 
//				bw = new BufferedWriter(new FileWriter(codeTable));
//				System.out.println(ht.size());
				long startTime = System.currentTimeMillis();
				//generate Huffman tree
				testTime(i, ht);
				long endTime = System.currentTimeMillis();
				
				switch (i) {
				case 1:
					System.out.println("Totle time for binary heap is " + (endTime - startTime) + " ms");
					break;
				case 2:
					System.out.println("Totle time for 4-way heap is " + (endTime - startTime) + " ms");
					break;
				case 3:
					System.out.println("Totle time for paring heap is " + (endTime - startTime) + " ms");	
					break;
				}
			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if(br != null){
//				try {
//					br.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			
////			if(bw != null){
////				try {
////					bw.close();
////				} catch (IOException e) {
////					e.printStackTrace();
////				}
////			}
////			
////			if(os != null){
////				try {
////					os.close();
////				} catch (IOException e) {
////					e.printStackTrace();
////				}
////			}
//		}
	}
	private static void testTime(int i, Hashtable<String, Integer> freqTable) {
		switch (i) {
		case 1:
			Heap heap = new BinaryHeap();
//			for(Iterator<Entry<String, Integer>> itr = freqTable.entrySet().iterator(); itr.hasNext();){
//				Entry<String, Integer> en = itr.next();
//				heap.add(heap.getNewHeapNodeInstance(en.getKey(), en.getValue(), null, null));
//			}
		//	System.out.println(heap);
		//	System.out.println(i + "Creating Huffman Trie...");
		//	heap.toHuffmanTrie();
			he.generateHuffmanCode(heap, freqTable);
			break;
			
		case 2:
			Heap heap1 = new FourWayHeap();
//			for(Iterator<Entry<String, Integer>> itr = freqTable.entrySet().iterator(); itr.hasNext();){
//				Entry<String, Integer> en = itr.next();
//				heap1.add(heap1.getNewHeapNodeInstance(en.getKey(), en.getValue(), null, null));
//			}
		//	System.out.println(heap1);
		//	System.out.println(i + "Creating Huffman Trie...");
		//	heap1.toHuffmanTrie();
			he.generateHuffmanCode(heap1, freqTable);
			break;
			
		case 3:
			Heap heap2 = new pairingHeap(); // very slow
//			for(Iterator<Entry<String, Integer>> itr = freqTable.entrySet().iterator(); itr.hasNext();){
//				Entry<String, Integer> en = itr.next();
//				heap2.add(heap2.getNewHeapNodeInstance(en.getKey(), en.getValue(), null, null));
//			}
		//	System.out.println(i + "Creating Huffman Trie...");
		//	heap2.toHuffmanTrie();
			he.generateHuffmanCode(heap2, freqTable);
			break;
		}
		
	}

}
