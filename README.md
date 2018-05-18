# Compilador

## Descripción

Este proyecto es un compilador que traduce programas escritos en un lenguaje similar
a [Go](https://golang.org/) a un conjunto de instrucciones para la máquina abstracta MAPL (v1.3.1)
creada como herramienta para la asignatura Diseño de Lenguajes de Programación de la Escuela de Ingeniería Informática de la Universidad de Oviedo.
MAPL se diseñó para ser el código destino del compilador que se desarrolla como práctica obligatoria
en dicha asignatura.

## Contenido

1. **Ampliaciones:** Contiene varias ampliaciones al compilador del lenguaje original, tales como bucles for, doWhile, paso de parámetros por referencia,etc.
2. **Compilador:** Contiene el compilador básico.
3. **Documentación:** Contiene varios documentos que sirven como apoyo para entender el funcionamiento del compilador.
4. **MAPL:** Máquina abstracta MAPL (v1.3.1)

## Ejecución de programas

### Sintaxis del lenguaje

La Sintaxis que reconoce el compilador es una versión modificada de la sintaxis de [Go](https://golang.org/).

#### Ejemplos de programas

En la carpeta /entradas se encuentran una serie de programas de prueba para el compilador.

- **Ejemplo de programa**
```go

// * Global variables
var i,n,j int;
var c char;

var pair struct {
	integer int;
	character char;
};

// * Functions
func p() {}

func f(r float32, e int) int {
	var real float32;
	var c char;
	real = float32(pair.character)+float32(pair.integer)+r+float32(e);
	c='0';
	return int(c);
}

var matrix [10][10]int;

func fill(row int, value int, increment int) {
	var i,inc int;
	i=0;
	inc=increment;
	while i<10 {
		matrix[row][i]=value+inc;
		i=i+1;
		inc=inc+increment;
	}
}

func show(row int) {
	var i int;
	i=0;
	while i<10 {
		write ('(', row, ',', i, ')', ':', matrix[row][i], ' ');
		i=i+1;
	}
	write ('\n');
}


// * Main program
func main() {
	var i int;

	p(); // * Invocation (procedure) as statement
	i=0;
	pair.character='0';
	pair.integer=int('0');
	write (f(float32(i),int(pair.character)), '\n'); // Invocation as expression

	f(1.3, 2); // * Invocation (function) as statement

	i=0;
	j=0;
	while i<10 {
		fill(i, j, 1);
		show(i);
		i=i+1;
		j=i*10;
	}
}

```

### Ejecución

Para compilar un programa mediante el compilador se le deben pasar dos ficheros como parámetro a este, siendo:
1. **source.txt:** Programa a compilar (puede tener otro nombre)
2. **output.txt:** Fichero de salida de la compilación con el código MAPL (puede tener otro nombre)
