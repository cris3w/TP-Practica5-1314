package controler;

import java.io.IOException;

import exceptions.ExceptionDivisionByZero;
import exceptions.ExceptionMissingOperand;
import exceptions.ExceptionWrongDirection;
import io.InStrategy;
import io.OutStrategy;
import model.CPU;
import model.ProgramMV;

public abstract class Controler {
	
	private CPU cpu;
	
	public Controler(CPU cpu) {
			this.cpu = cpu;
	}
	
	public void step() {
			try {
				this.cpu.step();
			} catch (ExceptionDivisionByZero e) {
			} catch (ExceptionWrongDirection e) {
			} catch (ExceptionMissingOperand e) {
			} catch (IOException e) {
			}
	}
	
	public void run() {
		try {
			this.cpu.run();
		} catch (ExceptionDivisionByZero e) {
		} catch (ExceptionWrongDirection e) {
		} catch (ExceptionMissingOperand e) {
		} catch (IOException e) {
		}
	} 
	
	public void pop() {
		try {
			this.cpu.pop();
		} catch (ExceptionDivisionByZero e) {
		}
	}
	
	public void push(int n) {
		this.cpu.push(n);
	} 
	
	public void setValueMemory(int pos, int n) {
		this.cpu.setValueMemory(pos, n);
	}
	
	public ProgramMV getProgram() {
		return this.cpu.getProgram();	
	}
	
	public InStrategy getInStrategy() {
		return cpu.getInStream();
	}
	
	public OutStrategy getOutStrategy() {
		return cpu.getOutStream();
	}
	
	public void setInStrategy(InStrategy in) { 
		cpu.setInStream(in);
	}
	
	public void setOutStrategy(OutStrategy out) { 
		cpu.setOutStream(out);
	}
	
	public void pause() {}
	
	public abstract void start(); 
	
	public void quit() {
		try {
			getOutStrategy().close();
			getInStrategy().close();
			System.exit(0);
		} catch (IOException e) {	
		}
	}
}
