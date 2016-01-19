package io;

public class InStrategyNull implements InStrategy {
	
	public void open() {}
	
	public void close() {}
	
	public int read() { 
		return -1;
	}
}
