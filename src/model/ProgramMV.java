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
	 * M�todo que a�ade la instrucci�n introducida al programa.
	 * @param instr es la instrucci�n que se quiere a�adir al programa
	 */
	public void push(Instruction instr) {
		this.program.add(this.sizeProgram, instr);
		this.sizeProgram++;
	}

	/**
	 * M�todo que devuelve la instrucci�n que se encuentra en la posici�n i del programa.
	 * @param i es la posici�n en la que se encuentra la instrucci�n
	 * @return la instrucci�n 
	 */
	public Instruction get(int i) {
		return this.program.get(i);
	}
	
	/**
	 * M�todo que devuelve el tama�o del programa.
	 * @return el numero de instrucciones que forman el programa
	 */
	public int getSizeProgram() {
		return this.sizeProgram;
	}
	
	/**
	 * M�todo encargado de devolver una representaci�n textual del programa.
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
			if (ins == null && !s.equalsIgnoreCase("END")) System.out.println("Instrucci�n Incorrecta");
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
