package edu.ufl.alexgre.project.huffmancoding;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Hashtable;

import edu.ufl.alexgre.project.threestructure.FourWayHeap;
import edu.ufl.alexgre.project.threestructure.Heap;

public class HuffmanEncoder {
	public static void main(String[] args) {
		String input = null;
		BufferedReader br = null;
		FileOutputStream fos = null;
		
		if(args.length == 1)
			input = args[0];
		else
			//input = "sample_input_small.txt";
			input = "sample_input_large.txt";
			//input = "test1.txt";
			//input = "test2.txt";
			//input = "test4.txt";
		
		
		HuffmanEncoding he = new HuffmanEncoding(input);
		Hashtable<String, Integer> freqTable = he.generateFrequencyTableFromFile(input);
		Heap heap = new FourWayHeap();
		System.out.println("Generating code table...");
		Hashtable<String, String> codeTable = he.generateHuffmanCode(heap, freqTable);
		System.out.println("Encoding file...");
		String line = null;
		
		byte[] buff = new byte[4 * 1024];
		ArrayList<Character> buffer = new ArrayList<Character>(32);
		
		try{
			fos = new FileOutputStream(input.split("\\.")[0] + "_encoded.bin");
			br = new BufferedReader(new FileReader(input));
			
			//use bitset
			BitSet bs = new BitSet();
			int k = 0;
			while((line = br.readLine()) != null){
				String code = codeTable.get(line).trim();
				for(int i = 0; i < code.length(); i++, k++){
					if(code.charAt(i) == '1'){
						bs.set(k, true);
					}	
				}
			}
			
			//the encoded bin file has extra true bit at the end
			//when decode, we need to exclude the last bit
			bs.set(k, true);
			//System.out.println(k);
			fos.write(bs.toByteArray());
						
			//use byte array and integer
//			int i = 0;
//			while((line = br.readLine()) != null){
//				String code = codeTable.get(line).trim();
//				for(int k = 0; k < code.length(); k++){
//					buffer.add(code.charAt(k));
//				}
//				
//				while(buffer.size() >= 8){
//					StringBuffer sb = new StringBuffer();
//					for(int k = 0; k < 8; k++)
//						sb.append(buffer.remove(0));
//					buff[i] = Integer.valueOf(sb.toString(), 2).byteValue();
//					i++;
//					if(i == 4 * 1024){
//						fos.write(buff);
//						buff = new byte[4 * 1024];
//						i = 0;
//					}
//				}
//			}
//			
//			for (int j = 0; j < i; j++) {
//				fos.write(buff[j]);
//	        }
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			CloseStream.close(br);
			CloseStream.close(fos);
			
//			if(br != null){
//				try {
//					br.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			
//			if(fos != null){
//				try{
//					fos.close();
//				}catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
		}
		
		System.out.println("Encoding finished. The encoded file is " + input.split("//.")[0] + "_encoded.bin");
	}
}
