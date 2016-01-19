package model;

import java.io.IOException;
import java.util.ArrayList;

import exceptions.ExceptionDivisionByZero;
import exceptions.ExceptionMissingOperand;
import exceptions.ExceptionWrongDirection;
import exceptions.MVError;
import io.InStrategy;
import io.OutStrategy;
import main.Main;
import model.instructions.Instruction;

public class CPU implements Observable<CPUObserver> {
	
	private ArrayList<CPUObserver> observers;
	private InStrategy inStream;
	private OutStrategy outStream;
	private Memory<Integer> memory;
	private Stack<Integer> stack;
	private boolean exit;
	private int pc;
	private ProgramMV program;
	
	public void addObserver(CPUObserver o) {
		observers.add(o);
	}
	
	public void removeObserver(CPUObserver o) {
		observers.remove(o);
	}
	
	public CPU() {
		this.memory = new Memory<Integer>();
		this.stack = new Stack<Integer>();
		this.exit = false;
		this.pc = 0;
		this.program = new ProgramMV();
		this.observers = new ArrayList<CPUObserver>();
	}
	
	public void loadProgram(ProgramMV p) {
		this.program = p;
		// cargar el programa p
		// inicializar la Pila, Memoria, etc.
		// avisar a los oyentes
	}
	
	public void step() throws ExceptionDivisionByZero, ExceptionWrongDirection, ExceptionMissingOperand, IOException {	
		for (CPUObserver o : observers) {
			o.onStartInstrExecution(this.getInstruction());
		}
		try {
			this.getInstruction().execute(this);
		} catch (ExceptionDivisionByZero e) {
			for (CPUObserver o : observers) {
				o.onError(e.getMessage());
			}
			throw new ExceptionDivisionByZero(e.getMessage());
		} catch (ExceptionWrongDirection e) {
			for (CPUObserver o : observers) {
				o.onError(e.getMessage());
			}
			throw new ExceptionWrongDirection(e.getMessage());
		} catch (ExceptionMissingOperand e) {
			for (CPUObserver o : observers) {
				o.onError(e.getMessage());
			}
			throw new ExceptionMissingOperand(e.getMessage());
		} catch (IOException e) {
			for (CPUObserver o : observers) {
				o.onError(e.getMessage());
			}
			throw new IOException(e.getMessage());
		}
		for (CPUObserver o : observers) {
			o.onEndInstrExecution(this.pc, this.stack, this.memory);
		}
		if (this.abortComputation()) {
			this.exit();
			for (CPUObserver o : observers) {
				o.onHalt();
			}
		}
	}
	
	public void stepn(int n) throws ExceptionWrongDirection, IOException {
		int i = 0;
		while (i < n && !this.abortComputation()) {
			this.step();
			i++;
		}
	}
	
	public void run() throws ExceptionWrongDirection, IOException {
		for (CPUObserver o : observers) {
			o.onStartRun();
		}	
		if (Main.getMode() != 2) {
			this.reset();
		}
		while (!this.abortComputation()) {
			this.step();
		}
		for (CPUObserver o : observers) {
			o.onEndRun();
		}
	}
	
	// OPCIONAL
	public void pause() {}

	public void reset() throws IOException {
		this.memory = new Memory<Integer>();
		this.stack = new Stack<Integer>();
		this.exit = false;
		this.pc = 0;
		this.inStream.open();
		this.outStream.open();
		for (CPUObserver o : observers) {
			o.onReset(this.program);
		}
	}
	
	public int pop() throws ExceptionMissingOperand {
		int pop = this.stack.pop(this.getInstruction());
		return pop;
	}
	
	public void push(int n) {
			this.stack.push(n);
	}
	
	public Stack<Integer> getStack() {
		return this.stack;
	}
	
	public Memory<Integer> getMemory() {
		return this.memory;
	}
	
	public void setInStream(InStrategy s) {
		if (s == null){
			throw new MVError("Cannot set inStream to null");
		}
		else {
			inStream = s;
		}
	}
	
	public void setOutStream(OutStrategy s) {
		if (s == null){
			throw new MVError("Cannot set outStream to null");
		}
		else {
			outStream = s;
		}
	}
	
	public InStrategy getInStream() {
		return inStream;
	}
	
	public OutStrategy getOutStream() {
		return outStream;
	}
	
	public int increaseProgramCounter() {
		return this.pc++;
	}
	
	public int getSizeStack() {
		return this.stack.getSize();
	} 
	
	public Instruction getInstruction() {
		return this.program.get(this.pc);
	}
	
	public ProgramMV getProgram() {
		return this.program;
	}
	
	public int getSizeProgram() {
		return this.program.getSizeProgram();
	}
	
	public int getProgramCounter() {
		return this.pc;
	}
	
	public void setProgramCounter(int n) {
		this.pc = n;
	}
	
	public int getTopStack() {
		return this.stack.getTop(this.getInstruction());
	} 
	
	/**
	 * Método que lleva a cabo la ejecución de la instrucción HALT
	 */
	public void exit() {
		this.exit = true;
	}
	
	/**
	 * Método que indica si la CPU ha sido detenida por una instrucción HALT.
	 * @return true si se ha detenenido la CPU
	 */
	public boolean isFinished() {
		return this.exit;
	}
	
	public boolean abortComputation() {
		return this.isFinished() || this.program.getSizeProgram() <= pc || pc < 0;
	}
	
	public int getValueMemory(int n) throws ExceptionWrongDirection {
		int value = 0;
		value = this.memory.getValue(this.getInstruction(), n);
		return value;
	}
	
	public void setValueMemory(int n) throws ExceptionMissingOperand, ExceptionWrongDirection {
		if (n > 0) {
			this.memory.setValue(n, this.stack.getTop(this.getInstruction()));
		} else throw new ExceptionWrongDirection("Error ejecutando " + getInstruction().toString() + ": dirección incorrecta (" + n + ")");
	}
	
	public void setValueMemory(int pos, int value) {
		this.memory.setValue(pos, value);
	}
	
	public int read() throws IOException {
		int in = this.inStream.read();
		return in;
	}
	
	public void write(int n) throws IOException {
		this.outStream.write(n);
	}
	
	public void close() throws IOException {
		this.inStream.close();
		this.outStream.close();
	}
	
	public String toString() {
		return "El estado de la maquina tras ejecutar la instruccion es: " + 
		System.getProperty("line.separator") + this.memory.toString() + this.stack.toString();
	}
	
}
