package model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.ExceptionWrongInstruction;
import main.InstructionParser;
import model.instructions.Instruction;

public class ProgramMV {

	private ArrayList <Instruction> program; 
	private int sizeProgram;
	
	/**
	 * Constructora por defecto.
	 */
	public ProgramMV() {
		this.program = new ArrayList<Instruction>();
		this.sizeProgram = 0;
	}

	/**
	 * Método que añade la instrucción introducida al programa.
	 * @param instr es la instrucción que se quiere añadir al programa
	 */
	public void push(Instruction instr) {
		this.program.add(this.sizeProgram, instr);
		this.sizeProgram++;
	}

	/**
	 * Método que devuelve la instrucción que se encuentra en la posición i del programa.
	 * @param i es la posición en la que se encuentra la instrucción
	 * @return la instrucción 
	 */
	public Instruction get(int i) {
		return this.program.get(i);
	}
	
	/**
	 * Método que devuelve el tamaño del programa.
	 * @return el numero de instrucciones que forman el programa
	 */
	public int getSizeProgram() {
		return this.sizeProgram;
	}
	
	/**
	 * Método encargado de devolver una representación textual del programa.
	 */
	public String toString() {
		String p = "";
		for (int i = 0; i < this.sizeProgram; i++) {
			p += i + ": " + this.program.get(i).toString();
			if (i < this.sizeProgram-1) p += System.getProperty("line.separator");
		}
		return p;
	}
	
	public static ProgramMV readProgram() {
		@SuppressWarnings("resource")
		Scanner line = new Scanner(System.in);
		String s;
		ProgramMV prog = new ProgramMV();
		do {
			System.out.print(">");
			s = line.nextLine();
			Instruction ins = InstructionParser.parseProgramInstruction(s);
			if (ins == null && !s.equalsIgnoreCase("END")) System.out.println("Instrucción Incorrecta");
			else if (ins != null) prog.push(ins);
		} while (!s.equalsIgnoreCase("END"));
		return prog;
	}
	
	public static ProgramMV readProgram(String fName) throws IOException {
		ProgramMV prog = new ProgramMV();
		File f = new File(fName);
		@SuppressWarnings("resource")
		Scanner entrada = new Scanner(new FileReader(f));
		String s;
		
	  	while (entrada.hasNext()) {
	  		do {
	  			s = entrada.nextLine();
	  		} while (s.startsWith(";") && (entrada.hasNext()));
	  		
	  		if (!s.startsWith(";")) {
		  		int i = 0;
		  		String t = "";
		  		String[] cad = s.split(" ");
		  		while ((i < cad.length) && (!cad[i].equals(";"))) {
		  			if(i == 0) {
		  				t = t + cad[i]+ " ";
		  			}
		  			else {
		  				t = t + cad[i];
		  			}
		  			i++;	  			
		  		}
		  		if (!t.equalsIgnoreCase(" ")){
			 		Instruction ins = InstructionParser.parseProgramInstruction(t);
			  		if (ins == null && !s.equalsIgnoreCase("END")) throw new ExceptionWrongInstruction("Error en el programa: Linea " + t);
			  		else if (ins != null) prog.push(ins);
		  		}
	  		}
 		} 
	  	return prog;
	}
}
