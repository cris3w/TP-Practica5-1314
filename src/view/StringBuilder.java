package view;

import java.util.Arrays;

public class StringBuilder {
	private String text;
	
	public StringBuilder() {
		this.text = "";
	}
	
	public void append(String str) {
		this.text = this.text + str;
	}
	
	public void append(char c) {
		char array[] = this.text.toCharArray();
		int pos = array.length;
		array = Arrays.copyOf(array, pos+1);
		array[pos] = c;
		this.text = new String(array);
	}
	
	public void setCharAt(int pos, char c) {
		char array[]  = this.text.toCharArray();
		array[pos] = c;
		this.text = new String(array);
	}
	
	public int getCharAt(int pos) {
		char array[]  = this.text.toCharArray();
		char c = array[pos];
		int n = (int) c;
		return n;
	}
	
	public void deleteCharAt(int pos) {
		char array[] = this.text.toCharArray();
		String textAux = "";
		for (int i = 0; i < pos; i++) {
			textAux = textAux + array[i];
		}
		for (int i = pos+1; i < array.length; i++) {
			textAux = textAux + array[i];
		}
		this.text = textAux;
	}
	
	public String toString() {
		return this.text;
	}
	
	public int length() {
		return this.text.length();
	}
}
