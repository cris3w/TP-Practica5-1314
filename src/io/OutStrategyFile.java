package io;

import java.io.FileWriter;
import java.io.IOException;

public class OutStrategyFile implements OutStrategy {
	private String fname; 
	private FileWriter f;
	
	public OutStrategyFile(String fname) { 
		this.fname = fname; 
	}
	
	public void open() throws IOException { 
		f = new FileWriter(fname);
	}
	
	public void close() throws IOException { 
		f.close();
	}
	
	public void write(int top) throws IOException {
		char c = (char) top;
		f.write(c);
	}
}
