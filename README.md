# Práctica Final TP 13-14

Proyecto realizado para la asignatura de Tecnología de la Programación (curso 2013-2014) con el objetivo de aprender el paradigma de *Programación Orientada a Objetos* (*POO*). 

Se trata de una aplicación de escritorio en Java que simula el funcionamiento de una máquina virtual. Dispone de tres modos de ejecución: interactivo (CLI), batch e interfaz (GUI).

Para su realización se han utilizado los patrones de diseño *MVC*, *Command* y *Observer*, y las librerías *Swing* para la interfaz gráfica y *Apache Commons CLI* para parsear las opciones de línea de comandos.

## Uso

    main.Main [-a <asmfile>] [-h] [-i <infile>] [-m <mode>] [-o <outfile>]


- `-h`, `--help` Muestra la información de ayuda.

- `-a` Fichero con el código ASM a ejecutar. Se requiere en el modo `batch`.

- `-i` Entrada de la máquina para el programa.

- `-m` Modo (`batch` | `interactive` | `window` ). `interactive` por defecto.

- `-o` Fichero en el que se guarda la salida.

## Instrucciones

    | Aritméticas | Booleanas  |   De salto    | Comparación |  Secuenciales  |
    | ----------- | ---------- | ------------- | ----------- | -------------- |
    |   ADD       |   AND      |   BF          |   EQ        |   DUP          | 
	|   DIV       |   OR       |   BT          |   GT        |   FLIP         |
	|   MUL       |   NOT      |   JUMP        |   LE        |   HALT         |
	|   SUB       |            |   JUMP [IND]  |   LT        |   IN           |
	|             |            |   RBF         |             |   LOAD         | 
	|             |            |   RBT         |             |   LOAD [IND]   |
	|             |            |   RJUMP       |             |   OUT          |
	|             |            |               |             |   POP          |
	|             |            |               |             |   PUSH         |
	|             |            |               |             |   STORE        |
	|             |            |               |             |   STORE [IND]  |

## Ejemplo

`-m window -a progr.txt -i in.txt -o out.txt` para ejecutar el programa de ejemplo.

![Antes de la ejecución del programa](https://github.com/cris3w/tp-vm-poo/blob/master/vm-1.png "vm-1")
![Durante la ejecución del programa](https://github.com/cris3w/tp-vm-poo/blob/master/vm-2.png "vm-2")
![Después de la ejecución del programa](https://github.com/cris3w/tp-vm-poo/blob/master/vm-3.png "vm-3")
