package model;

import java.util.ArrayList;

import exceptions.ExceptionWrongDirection;
import model.instructions.Instruction;

public class Memory<T> implements Observable<MemoryObserver<T>> {
	
	private ArrayList<T> memory;
	private int capacity = 2;
	private ArrayList<MemoryObserver<T>> observers;
	
	public static class MemCell<T> {
		int pos;
		T value;
		
		public MemCell(int pos, T t) {
			this.pos = pos;
			this.value = t;
		}
		
		public int getPos() {
			return pos;
		}
		
		public T getValue() {
			return value;
		}
	}
	
	public ArrayList<MemCell<Integer>> getArray() {
		ArrayList<MemCell<Integer>> arrayMemo = new ArrayList<MemCell<Integer>>();
		int k = 0;
		for (int i = this.memory.size()-1; i >= 0; i--) {
			if (this.memory.get(i) != null) {
				MemCell<Integer> memo = new MemCell<Integer>(i, (Integer) this.getValue(i));
				arrayMemo.add(k, memo);
			}
		}
		return arrayMemo;
	} 
	
	public Memory() {
		this.memory = new ArrayList<T>(this.capacity);
		for (int i = 0; i < this.capacity; i++) {
			this.memory.add(i, null);
		}
		this.observers = new ArrayList<MemoryObserver<T>>();
	}
	
	@Override
	public void addObserver(MemoryObserver<T> o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(MemoryObserver<T> o) {
		observers.remove(o);
	}
	
	public String toString() {
		String state = "<vacia>";
		int cont = 0;
		if (!isEmpty()) {
			for (int i = 0; i < this.capacity; i++) {
				if (this.memory.get(i) != null) {
					cont++;
					if (cont == 1) state = "[" + i + "]:" + getValue(i) + " ";
					else state = state + "[" + i + "]:" + getValue(i) + " ";
				}
			}
	    }
		return "Memoria: " + state + "\n";
	}
	
	private boolean isEmpty() {
		boolean empty = true;
		int i = 0;
		while (i < this.capacity && empty) {
			if (this.memory.get(i) != null) empty = false;
			i++;
		}
		return empty; 
	}
	
	// OPCIONAL
	public void reset() {}
	
	private void resize(int value) {
		int aux = this.capacity;
		this.capacity = value;
		ArrayList<T> auxArray = new ArrayList<T>(value);
		
		for(int i = 0; i < aux; i++) {
			auxArray.add(i, this.memory.get(i));
		}
		for(int i = aux; i < value; i++){
			auxArray.add(i, null);
		}
		this.memory = auxArray;
	}
	
	private T getValue(int pos) {
		T value = null;
		if (pos < this.capacity && pos >= 0) {
			if (this.memory.get(pos) != null) {
				value = this.memory.get(pos);
			}
		}
		else if (pos >= 0) this.resize(pos*2);
		return value;
	}
	
	public T getValue(Instruction instruction, int pos) throws ExceptionWrongDirection { 
		T value = null;
		if (pos < this.capacity && pos >= 0) {
			if (this.memory.get(pos) != null) {
				value = this.memory.get(pos);
			}
		}
		else if (pos >= 0) this.resize(pos*2);
		else throw new ExceptionWrongDirection("Error ejecutando " + instruction + ": dirección incorrecta (" + pos +")");
		return value;
	}
	
	public void setValue(int pos, T value) {
		int aux = pos;
		if (pos >= this.capacity) {
			this.resize(pos*2);
			this.memory.set(aux, value);
		} else if (pos >= 0) {
			this.memory.set(pos, value);
		}
		for (MemoryObserver<T> o : observers) {
			o.onWrite(pos, value);
		}
	}
}
