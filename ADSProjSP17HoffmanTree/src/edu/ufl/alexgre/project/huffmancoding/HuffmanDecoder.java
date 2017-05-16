package edu.ufl.alexgre.project.huffmancoding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.BitSet;

import edu.ufl.alexgre.project.threestructure.HuffmanNode;


public class HuffmanDecoder {
	private static  BufferedReader br = null;
	private static PrintWriter pw = null;
	private static FileInputStream fis = null;
	
	public static void main(String[] args) {
		String binFile = null;
		String outputFile = null;
		String codeTable = null;
		
		if(args.length == 3){
			binFile = args[0];
			outputFile = args[1];
			codeTable = null;
		}else{
			binFile = "sample_input_small_encoded.bin";
			codeTable = "sample_input_small.txt_code_table.txt";
			outputFile = "sample_output_small.txt";
//			binFile = "sample_input_large_encoded.bin";
//			outputFile = "sample_output_large.txt";
//			codeTable = "sample_input_large.txt_code_table.txt";
		}
		
		System.out.println("Building decode tree...");
		HuffmanDecoder hd = new HuffmanDecoder();
		try{
			System.out.println("Decoing the input file "+ binFile + " ...");
			HuffmanNode pointer = hd.buildDecodeTree(codeTable);
			//System.out.println();
			hd.decoding(binFile, outputFile, pointer);
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			CloseStream.close(br);
			CloseStream.close(fis);
			CloseStream.close(pw);
			System.out.println("Decode job finished!");
		}
	}
	
	private void decoding(String binFile, String outputFile, HuffmanNode root) throws IOException{
		try {
			File dotBin = new File(binFile);
			fis = new FileInputStream(dotBin);
			pw = new PrintWriter(new File(outputFile));
			
			HuffmanNode pointer = root;
			//ArrayList<Byte> buffer = new ArrayList<Byte>();
			int index = 0;
			//enough for about 2GB, if file larger than 2GB, need other methods
			byte[] bytes = new byte[(int) dotBin.length()];
			byte[] buff = new byte[4 * 1024];
			int len = 0;
			while((len = fis.read(buff)) != -1){
				for(int k = 0; k < len; k++, index++){
					//buffer.add(buff[k]);
					bytes[index] = buff[k];
				}
			}
			
			BitSet allBits = BitSet.valueOf(bytes);
			//when performing encoding we have an extra bit at the end of the bin file
			for(int j = 0; j < allBits.length() - 1; j++){
				if(allBits.get(j)){
					//System.out.println(1);
					pointer = pointer.right;
					if(pointer.freq == 0){
						pw.println(pointer.tag);
						pw.flush();
						pointer = root;
					}
				}else{
					//System.out.println(0);
					pointer = pointer.left;
					if(pointer.freq == 0){
						pw.println(pointer.tag);
						pw.flush();
						pointer = root;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private HuffmanNode buildDecodeTree(String codeTable) throws IOException{
		String tag = "decoder*node";
		String line = null;
		HuffmanNode root = new HuffmanNode(tag, 0, null, null);
		HuffmanNode pointer = root;
						
		br = new BufferedReader(new FileReader(codeTable));
		
		while((line = br.readLine()) != null){
			String[] huffmanNodeInfo = line.split(" ");
			String nodeTag = huffmanNodeInfo[0];
			int freq = -1;
			String bicode = huffmanNodeInfo[1];
			for(int i = 0; i < bicode.length() - 1; i++){
				if(bicode.charAt(i) == '0'){
					//System.out.println(0);
					if(pointer.left == null)
						pointer.left = new HuffmanNode(tag, freq, null, null);
					pointer = pointer.left;
				}else{
					//System.out.println(1);
					if(pointer.right == null)
						pointer.right = new HuffmanNode(tag, freq, null, null);
					pointer = pointer.right;
				}
			}
			
			if(bicode.charAt(bicode.length() - 1) == '0'){
				//System.out.println(0);
				pointer.left = new HuffmanNode(nodeTag, 0, null, null);
			}else{
				//System.out.println(1);
				pointer.right = new HuffmanNode(nodeTag, 0, null, null);
			}
			
			pointer = root;
		}
		
//		helpPrint(root);
		
		return pointer;
	}
	
//	private void helpPrint(HuffmanNode node){
//		System.out.println(node.tag);
//		if(node.left != null){
//			node = node.left;
//			System.out.println(node.tag);
//			helpPrint(node);
//		}
//		
//		if(node.right != null){
//			node = node.right;
//			System.out.println(node.tag);
//			helpPrint(node);
//		}
//	}
}
