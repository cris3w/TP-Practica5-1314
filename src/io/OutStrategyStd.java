package io;

public class OutStrategyStd implements OutStrategy {

	public void open() {}

	public void close() {}
	
	public void write(int x) {
		char c = (char) x;
		System.out.print(c);
	}
}
