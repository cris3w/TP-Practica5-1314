package model;

import java.util.ArrayList;

import exceptions.ExceptionMissingOperand;
import model.instructions.Instruction;

public class Stack<T> implements Observable<StackObserver<T>> {

	private ArrayList<T> stack;
	private ArrayList<StackObserver<T>> observers;
	
	public Stack() {
		this.stack = new ArrayList<T>();
		this.observers = new ArrayList<StackObserver<T>>();
	}
	
	@Override
	public void addObserver(StackObserver<T> o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(StackObserver<T> o) {
		observers.remove(o);
	}
	
	public String toString() {
		String state = "<vacia>";
		for (int i = 0; i < this.stack.size(); i++) {
			if (i == 0) state = this.stack.get(i) + " ";
			else state = state + this.stack.get(i) + " ";
		}
		return "Pila de operandos: " + state;
	}
	
	public void push(T n) {
		this.stack.add(n);
		for (StackObserver<T> o : observers) {
			o.onPush(n);
		}
	}
	
	public T pop(Instruction ins) {
		T n;
		try {
		n = this.stack.remove(this.getSize()-1);
		} catch (IndexOutOfBoundsException e){
			throw new ExceptionMissingOperand("Error ejecutando " + ins + ": faltan operandos en la pila (hay " + this.getSize() + ")");
		}
		for (StackObserver<T> o : observers) {
			o.onPop(n);
		}
		return n;
	}
	
	public void reset() {} // OPCIONAL
	
	public int getSize() {
		return this.stack.size();
	}
	
	public T getTop(Instruction ins) {
		try {
			return this.stack.get(this.stack.size()-1);
		} catch (IndexOutOfBoundsException e){
			throw new ExceptionMissingOperand("Error ejecutando " + ins + ": faltan operandos en la pila (hay " + this.getSize() + ")");
		}
	}
}
