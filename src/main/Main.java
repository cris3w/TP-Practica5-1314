package main;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;

import controler.BatchControler;
import controler.GUIControler;
import controler.InteractiveControler;
import exceptions.ExceptionMissingASMFile;
import exceptions.ExceptionWrongInstruction;
import exceptions.MVError;
import io.InStrategy;
import io.InStrategyFile;
import io.InStrategyNull;
import io.InStrategyStd;
import io.OutStrategy;
import io.OutStrategyFile;
import io.OutStrategyNull;
import io.OutStrategyStd;
import model.CPU;
import model.ProgramMV;
import model.commandInterpreter.CommandInterpreter;
import view.BatchView;
import view.InteractiveView;
import view.MainWindow;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Clase principal que incluye el método main.
 */
public class Main {
	
	private static final int _BATCH_MODE = 0;
	private static final int _INTER_MODE = 1;
	private static final int _WINDOW_MODE = 2;
	
	private static String mensajeIni = "[-a <asmfile>] [-h] [-i <infile>] [-m <mode>] [-o <outfile>]";
	private static CPU cpu;
	private static InStrategy entrada;
	private static OutStrategy salida;
	private static String fInName;
	private static String fOutName;
	private static String fAsmName;
	private static int modoExecution;
	private static boolean modoHelp = false;
	private static boolean recognizedOption = true;
	
	
	public static void configurarMv(String[] args){
		Options opciones = new Options();
		opciones.addOption("a", "asm", true, "Fichero con el codigo en ASM del programa a ejecutar. Obligatorio en modo batch");
		opciones.addOption("i", "in", true, "Entrada del programa de la maquina-p");
		opciones.addOption("m", "mode", true, "Modo de funcionamiento (batch | interactive). Por defecto, batch");
		opciones.addOption("o", "out", true, "Fichero donde se guarda la salida del programa de la maquina-p");
		opciones.addOption("h", "help", false, "Muestra un mensaje de ayuda");
	
		CommandLineParser parse = new BasicParser();
		CommandLine cmd = null;
		try {
			cmd = parse.parse(opciones, args);

			if(cmd.hasOption("h")){
		  		new HelpFormatter().printHelp(mensajeIni, opciones);
		  		modoHelp = true;
		  	} else {
				try {
					// MODO INTERACTIVE
					if (cmd.hasOption("m") && cmd.getOptionValue("m").equals("interactive") ) {
						Main.modoExecution = _INTER_MODE;
						if (cmd.hasOption("i")) {
							Main.fInName = cmd.getOptionValue("i");
							Main.entrada = new InStrategyFile(Main.fInName);		
							
						} else {
							Main.entrada = new InStrategyNull();
						}
						if (cmd.hasOption("o")) {
							Main.fOutName = cmd.getOptionValue("o");
							Main.salida = new OutStrategyFile(Main.fOutName);
						} else {
							Main.salida = new OutStrategyNull();
						}
						if (cmd.hasOption("a")) {
							Main.fAsmName = cmd.getOptionValue("a");
						} else {
							Main.fAsmName = null;
						}
					}
					// MODO BATCH
					else if (!cmd.hasOption("m") || cmd.hasOption("m") && cmd.getOptionValue("m").equals("batch")) {
						Main.modoExecution = _BATCH_MODE;
						if (cmd.hasOption("a")) {
							Main.fAsmName = cmd.getOptionValue("a");
							if (cmd.hasOption("i")) {
								Main.fInName = cmd.getOptionValue("i");
								Main.entrada = new InStrategyFile(Main.fInName);
							} else {
								Main.entrada = new InStrategyStd();
							}
							if (cmd.hasOption("o")) {
								Main.fOutName = cmd.getOptionValue("o");
								Main.salida = new OutStrategyFile(Main.fOutName);
							} else {
								Main.salida = new OutStrategyStd();
							}
						} else throw new ExceptionMissingASMFile("Fichero ASM no especificado\nUse -h|--help para más detalles");
					}
					// MODO WINDOW
					else if (cmd.hasOption("m") && cmd.getOptionValue("m").equals("window")) {
						if (cmd.hasOption("a")) {
							Main.modoExecution = _WINDOW_MODE;
							Main.fAsmName = cmd.getOptionValue("a");
							if (cmd.hasOption("i")) {
								Main.fInName = cmd.getOptionValue("i");
								Main.entrada = new InStrategyFile(Main.fInName);
							} else {
								Main.entrada = new InStrategyNull();
							}
							if (cmd.hasOption("o")) {
								Main.fOutName = cmd.getOptionValue("o");
								Main.salida = new OutStrategyFile(Main.fOutName);
							} else {
								Main.salida = new OutStrategyNull();
							}
						} else throw new ExceptionMissingASMFile("Fichero ASM no especificado\nUse -h|--help para más detalles");
					}
				} catch (ExceptionMissingASMFile e) {
					System.out.println(e.getMessage());
				}
		  	}
		} catch (MissingArgumentException e ) {
			System.out.println("Uso incorrecto: " + e.getMessage());
			System.out.println("Use -h|--help para más detalles");
		} catch (UnrecognizedOptionException e) {
			recognizedOption = false;
			System.out.println(e.getMessage());
		} catch (ParseException e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		cpu = new CPU();
		Main.configurarMv(args);
		if (!modoHelp && recognizedOption) {
			switch(modoExecution){
			case _INTER_MODE:
				try {
					interactiveMode();
				} catch (IOException e) {					
				}
				break;
			case _BATCH_MODE:
				try {
					batchMode();
				} catch (IOException e) {				
				}
				break;
			case _WINDOW_MODE:
				try {
					windowMode();
				} catch (IOException e) {					
				}
				break;
			}
		}
	} 
	
	private static void interactiveMode() throws IOException {
		try {
			cpu.setInStream(entrada);
			cpu.getInStream().open();
			cpu.setOutStream(salida);
			cpu.getOutStream().open();
		
			ProgramMV prog;
			if(fAsmName == null) {
				prog = new ProgramMV();
				System.out.println("Introduce el programa fuente: ");
				prog = ProgramMV.readProgram();
				System.out.println("El programa introducido es: ");
				System.out.println(prog.toString());
			} else {
				prog = ProgramMV.readProgram(fAsmName);
				System.out.println(prog);			
			}		
			cpu.loadProgram(prog);
			CommandInterpreter.configureCommandInterpreter(cpu);			
			
			InteractiveControler ctrl = new InteractiveControler(cpu);
			@SuppressWarnings("unused")
			InteractiveView view = new InteractiveView(cpu);
			ctrl.start();
			
			cpu.close();
		// lanzada por open() en InStrategyFile
		} catch (FileNotFoundException e) {
			System.err.println("Archivo de entrada no encontrado");
		} catch (MVError e){
			System.err.println(e.getMessage());
		}
	}
	
	private static void batchMode() throws IOException {
		try {
			cpu.setInStream(entrada);
			cpu.getInStream().open();
			cpu.setOutStream(salida);
			cpu.getOutStream().open();
			
			try {
				ProgramMV prog = ProgramMV.readProgram(fAsmName);
				cpu.loadProgram(prog);	
				
				BatchControler ctrl = new BatchControler(cpu);
				@SuppressWarnings("unused")
				BatchView view = new BatchView(cpu);
				ctrl.start();
			// lanzada por readProgram(String fName) en ProgramMV
			} catch (ExceptionWrongInstruction e) {
				System.err.println(e.getMessage());
			} finally {
				cpu.close();
			}	
		// lanzada por open() en InStrategyFile
		} catch (FileNotFoundException e) {
			System.err.println("Archivo de entrada no encontrado");
		} catch (MVError e){
			System.err.println(e.getMessage());
		}
	}
	
	private static void windowMode() throws IOException {
		try {
			cpu.setInStream(entrada);
			cpu.getInStream().open();
			cpu.setOutStream(salida);
			cpu.getOutStream().open();
			
			ProgramMV prog = ProgramMV.readProgram(fAsmName);
			cpu.loadProgram(prog);	
			
			GUIControler GUICtrl = new GUIControler(cpu);
			@SuppressWarnings("unused")
			MainWindow gui = new MainWindow(GUICtrl, cpu, cpu.getStack(), cpu.getMemory());
		// lanzada por readProgram(String fName) en ProgramMV
		} catch (ExceptionWrongInstruction e) {
			System.err.println(e.getMessage());
		// lanzada por open() en InStrategyFile
		} catch (FileNotFoundException e) {
			System.err.println("Archivo de entrada no encontrado");
		} catch (MVError e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static int getMode(){
		return modoExecution;		
	} 
}