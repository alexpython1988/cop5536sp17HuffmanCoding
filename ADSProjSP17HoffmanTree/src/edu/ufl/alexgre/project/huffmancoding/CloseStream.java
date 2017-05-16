package edu.ufl.alexgre.project.huffmancoding;

import java.io.Closeable;
import java.io.IOException;

public class CloseStream {

	public static void close(Closeable obj){
		if(obj != null){
			try {
				obj.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("IO stream closed.");
			
	}
}
