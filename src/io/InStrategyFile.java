package io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InStrategyFile implements InStrategy {
	private String fname; 
	private FileReader f;
	
	public InStrategyFile(String fname) { 
		this.fname = fname; 
	}
	
	public void open() throws FileNotFoundException {
		f = new FileReader(fname);
	}
	
	public void close() throws IOException { 
		f.close();
	}
	
	public int read() throws IOException {
		int x = -1;
		if (f.ready()) x = f.read();
		return x;
	}
}
