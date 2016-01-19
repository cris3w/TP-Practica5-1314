package io;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface InStrategy {
	
	public void open() throws FileNotFoundException;
	
	public int read() throws IOException;
	
	public void close() throws IOException;
}
