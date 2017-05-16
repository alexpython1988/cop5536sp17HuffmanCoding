package edu.ufl.alexgre.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import com.sun.swing.internal.plaf.basic.resources.basic;

import edu.ufl.alexgre.project.huffmancoding.HuffmanEncoder;
import edu.ufl.alexgre.project.huffmancoding.HuffmanEncoding;
import edu.ufl.alexgre.project.threestructure.BinaryHeap;
import edu.ufl.alexgre.project.threestructure.FourWayHeap;
import edu.ufl.alexgre.project.threestructure.Heap;
import edu.ufl.alexgre.project.threestructure.HuffmanNode;
import edu.ufl.alexgre.project.threestructure.pairingHeap;


public class HuffmanEncodingTest {
	
	@Test
	public void decode() throws IOException{
		File f = new File("sample_input_small_encoded.bin");
		System.out.println(f.length());
		byte[] a = new byte[(int) f.length()];
		FileInputStream fis = new FileInputStream(f);
		int len = fis.read(a);
		System.out.println(len);
		BitSet bs = BitSet.valueOf(a);
		System.out.println(bs.length());
		fis.close();
		
		Boolean t1 = true;
		Boolean t2 = true;
		if(t1 == t2){
			System.out.println(1);
		}
		if(t1 == true){
			System.out.println(1);
		}
	}
	
	@Test
	public void byte2() throws IOException{
		FileInputStream fis = new FileInputStream("test1.txt_encoded.bin");
		
		byte[] a = new byte[10240000];
		int len = fis.read(a);
		
		
		System.out.println(len);
//		String s = "";
//		for(byte b: a){
//			s += String.format("%8s", Integer.toBinaryString((b + 256) % 256)).replace(' ', '0');
//		}
//		System.out.println(s);
		
		//System.out.println(a.length);
		
		BitSet bs = BitSet.valueOf(a);
		System.out.println(bs.length());
		
		StringBuffer sb = new StringBuffer();
		for(int k = 0; k < bs.length() - 1; k++){
			if(bs.get(k)){
				sb.append("1");
			}else{
				sb.append("0");
			}
		}
		System.out.println(sb.toString().substring(0, 1000));
		System.out.println(sb.toString().length());
		
//		byte[] masks = { -128, 64, 32, 16, 8, 4, 2, 1 };
//		StringBuilder builder = new StringBuilder();
//		for(byte b: a){
//		for (byte m : masks) {
//	        if ((b & m) == m) {
//	            builder.append('1');
//	        } else {
//	            builder.append('0');
//	        }
//	    }
//		}
//		
//		System.out.println(builder.toString());
		fis.close();
	}
	
	@Test
	public void byte1(){		
		//String s = "110011011010101010100101010101010101010101010010101011010101010101010101010101010100010101011111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111010010101111001010101010101001010101000101010010101011111111111111111111111111111111111111111111111111";
		String s = "01100100110010110101111101111101110010000";
//		byte i = Integer.valueOf(s, 2).byteValue();
//		System.out.println(i);
		
//		System.out.println(Byte.toString(i));
		System.out.println(s.length());
		BitSet bs = new BitSet(s.length());
		int i = 0;
//		for(Character c: s.toCharArray()){
		for(; i < s.length(); i++){
			if(s.charAt(i) == '1'){
				bs.set(i);
			}
			
			//i++;
		}
		System.out.println(bs.length());
		
		byte[] a = bs.toByteArray();
		BitSet bs1 = BitSet.valueOf(a);
		System.out.println(a.length);
		
		StringBuffer sb = new StringBuffer();
		for(int k = 0; k < bs1.length(); k++){
			if(bs1.get(k)){
				sb.append("1");
			}else{
				sb.append("0");
			}
		}
		
		System.out.println(sb.toString());
		
//		System.out.println(Integer.BYTES);
//		String s1 = Integer.toBinaryString(i);
//		StringBuffer sb  = new StringBuffer();
//		if(s1.length() < 8){
//			for(int k = 0; k < 8 - s1.length(); k++){
//				s1 = sb.append("0").toString() + s1;
//			}
//		}
//		System.out.println(s1);
		System.out.println(sb.toString().equals(s));	
		//System.out.println(Integer.bitCount(20));
	}
	
	@Test
	public void right() throws IOException{
		BufferedReader br1 = new BufferedReader(new FileReader("test_bin2.bin"));
		BufferedReader br = new BufferedReader(new FileReader("test_bin1.bin"));
		
		String line1 = br1.readLine();
		String line2 = br.readLine();
		
		System.out.println(line1.substring(0, 10));
		System.out.println(line2.substring(0, 10));
		
		if(line1.equals(line2)){
			System.out.println("true");
		}else{
			System.out.println("false");
		}
		
		br1.close();
		br.close();
	}
	
	@Test
	public void sbvss() throws IOException{
		//String s = "";
		StringBuffer sb = new StringBuffer();
		
		String input = "sample_input_small.txt";
		//String input = "tes.txt";
		BufferedReader br = new BufferedReader(new FileReader(input));
		FileOutputStream fos = new FileOutputStream("small.bin");
		String line = null;
		
		long startTime = System.currentTimeMillis();
		//int i = 0;
		while((line = br.readLine()) != null){
			fos.write(line.getBytes());
			fos.flush();
		}
//		while((line = br.readLine()) != null){
//			//s += line;
//			//System.out.println(i++);
//			if(sb.length() >=  4 * 1024){
//				fos.write(sb.toString().getBytes());
//				fos.flush();
//				//System.out.println("flush");
//				sb = new StringBuffer();
//			}
//				
//			sb.append(line);
//			//fos.write(line.getBytes());
//		}
//		
//		if(sb.length() != 0){
//			fos.write(sb.toString().getBytes());
//			fos.flush();
//			//System.out.println("flush");
//		}
		
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
		//System.out.println(sb.length());
		
		br.close();
		fos.close();
	}
	
	@Test
	public void testBuff() throws IOException{
		String s = "abcde";
		byte[] b = s.getBytes();
		System.out.println(b);
		System.out.println(b.length);
	}
	
	@Test
	public void createTestFile() throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter("test4.txt"));
		for(int i = 0; i < 600000; i++){
			bw.write(ThreadLocalRandom.current().nextInt(1, 500000) + "\n");
		}
		
		bw.close();
	}
	
	@Test
	public void test1(){
		HuffmanEncoding he = new HuffmanEncoding();
		//String inputFile = "sample_input_small.txt";
		//String inputFile = "sample_input_large.txt";
		String inputFile = "test1.txt";
		//String inputFile = "test2.txt";
		
		Hashtable<String, Integer> ht = he.generateFrequencyTableFromFile(inputFile);
		Heap heap = new pairingHeap();
		for(Iterator<Entry<String, Integer>> itr = ht.entrySet().iterator(); itr.hasNext();){
			Entry<String, Integer> en = itr.next();
			heap.add(heap.getNewHeapNodeInstance(en.getKey(), en.getValue(), null, null));
		}
		
		heap.toHuffmanTrie();
		//System.out.println(heap); //stackoverflow!!!!
	}
	
	@Test
	public void generateFreqTable(){
		HuffmanEncoding he = new HuffmanEncoding();
		//String inputFile = "sample_input_small.txt";
		String inputFile = "sample_input_large.txt";
		Hashtable<String, Integer> ht = he.generateFrequencyTableFromFile(inputFile);
		//printHashTable(ht);
//		Heap heap1 = new BinaryHeap();
		Heap heap2 = new FourWayHeap();
//		Heap heap3 = new pairingHeap();
		//HuffmanNode hn = he.generateHuffmanTree(heap, ht);
//		System.out.println(he.generateHuffmanCode(heap1, ht));
//		System.out.println();
		System.out.println(he.generateHuffmanCode(heap2, ht));
		System.out.println();
//		System.out.println(he.generateHuffmanCode(heap3, ht));
	}
	
//	private void printHashTable(Hashtable<String, Integer> ht){
//		for(Iterator<Entry<String, Integer>> itr = ht.entrySet().iterator(); itr.hasNext();){
//			Entry<String, Integer> en = itr.next();
//			System.out.println(en.getKey() + " : " + en.getValue());
//		}
//	}
	
	@Test 
	public void testHeap(){
		FourWayHeap fh = new FourWayHeap();
		BinaryHeap bh = new BinaryHeap();
		pairingHeap ph = new pairingHeap();
		
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		for(int i = 65; i < 127; i++){
			char ch = (char) i;
			String k = String.valueOf(ch);
			hm.put(k, ThreadLocalRandom.current().nextInt(1, 100));
		}
		
		//System.out.println(hm);
		
		Iterator<Entry<String, Integer>> itr = hm.entrySet().iterator();
		while(itr.hasNext()){
			Entry<String, Integer> en = itr.next();
			HuffmanNode bf = new HuffmanNode(en.getKey(), en.getValue(), null, null);
			fh.add(bf);
			bh.add(bf);
			HuffmanNode hf = ph.getNewHeapNodeInstance(en.getKey(), en.getValue(), null, null);
			ph.add(hf);
		}
//
//		
//		HuffmanNode h2 = fh.deletMin();
//		System.out.println(h2.tag + ":" + h2.freq);
//		System.out.println();
//		
////		HuffmanNode h3 = fh.deletMin();
////		System.out.println(h3.tag + ":" + h3.freq);
////		System.out.println();
////		
////		System.out.println(fh);
		
		for(; fh.size() != 0;){
//			System.out.println(fh.size());
//			System.out.println(fh);
//			HuffmanNode h2 = fh.deletMin();
//			System.out.println(h2.tag + ":" + h2.freq);
//			System.out.println();
			HuffmanNode h1 = bh.deletMin();
			HuffmanNode h2 = fh.deletMin();
			HuffmanNode h3 = ph.deletMin();
			System.out.println(h1.tag + ":" + h2.tag + ":" + h3.tag);
			System.out.println(h1.freq + ":" + h2.freq + ":" + h3.freq);
			
			if(h1.compareTo(h2) != 0 || h2.compareTo(h3) != 0 || h3.compareTo(h1) != 0)
				System.out.println("error!");
		}
	}
	
	@Test
	public void testDeleteMin(){
		for(int i = 0; i < 1000; i++){
			testStructure();
		}
	}
	
	private void testStructure(){
		BinaryHeap bh = new BinaryHeap();
		FourWayHeap fh = new FourWayHeap();
		
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		for(int i = 97; i < 123; i++){
			char ch = (char) i;
			String k = String.valueOf(ch);
			hm.put(k, ThreadLocalRandom.current().nextInt(1, 20));
		}
		
		Iterator<Entry<String, Integer>> itr = hm.entrySet().iterator();
		while(itr.hasNext()){
			Entry<String, Integer> en = itr.next();
			HuffmanNode hf = new HuffmanNode(en.getKey(), en.getValue(), null, null);
			bh.add(hf);
			fh.add(hf);
		}
		
//		System.out.println(bh);
//		System.out.println();
//		System.out.println(fh);
		
		for(; bh.size() != 0;){
			HuffmanNode h1 =  bh.deletMin();
			HuffmanNode h2 = fh.deletMin();
			if(h1.freq != h2.freq){
				System.out.println("delete Error!");
				break;
			}
		}
		
	}

	@Test
	public void removeFromArrayList(){
		ArrayList<Integer> l = new ArrayList<Integer>();
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		l.add(5);
		l.add(6);
		l.add(7);
		l.add(8);
		l.add(9);
		l.add(10);
		
		for(; l.size() > 1;){
			//System.out.println(l.size());
			l.add(l.remove(0) + l.remove(0));
		}
		
		System.out.println(l.get(0));
		
//		int i = l.remove(l.size() - 1);
//		//System.out.println(i);
//		System.out.println(l.get(0));
//		l.set(0, i);
//		System.out.println(l);
	}
	
	@Test
	public void testSubString(){
		String s = "1234#alex";
		int i = s.indexOf("#");
		System.out.println(s.substring(0, i));
		System.out.println(s.substring(i+1));
		
		System.out.println(s.split("#")[0]);
		System.out.println(s.split("#")[1]);
		
		String input = "sample_input_small.txt";
		System.out.println(input.split("\\.")[0]);
	}
	
	@Test
	public void testSort(){
		String i = "0010101000000001";
		
		System.out.println(Short.parseShort(i, 2));	
		
		ByteBuffer b = ByteBuffer.allocate(3).putShort(Short.parseShort(i, 2));
		
		BitSet bs = BitSet.valueOf(b);
		System.out.println(b.toString());
		
		StringBuffer sb = new StringBuffer();
		for(int k = 0; k < bs.length() - 1; k++){
			if(bs.get(k)){
				sb.append("1");
			}else{
				sb.append("0");
			}
		}
		
		System.out.println(sb.toString());
		System.out.println(sb.toString().length());
	}
	
}
