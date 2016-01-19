package io;

import java.io.IOException;

public interface OutStrategy {
	
	public void open() throws IOException ;
	
	public void write(int x) throws IOException ;
	
	public void close() throws IOException;
}
