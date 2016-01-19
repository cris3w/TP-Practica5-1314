package io;

import java.io.IOException;

public class InStrategyStd implements InStrategy {

	public void open(){	
	}

	public void close() {
	}
	
	public int read() {
		int x = -1;
		try {
			System.out.print(">");
			x = System.in.read();
			while (System.in.available() > 0) System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return x;
	}
}
