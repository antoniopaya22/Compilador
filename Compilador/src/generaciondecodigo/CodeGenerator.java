/**
 * 
 */
package generaciondecodigo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import ast.definicion.DefVariable;
import ast.tipo.Char;
import ast.tipo.Entero;
import ast.tipo.Real;
import ast.tipo.Tipo;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class CodeGenerator {

	public String sourceFile;
	private PrintWriter out;
	
	public static int labels = 0;

	public CodeGenerator(String archivoEntrada,String archivoSalida) throws FileNotFoundException {
		File f = new File(archivoSalida);
		out = new PrintWriter(f);
		sourceFile = archivoEntrada;
		this.indicateSourceFile();
	}

	private void indicateSourceFile() {
		out.println();
		out.println("#source \"" + sourceFile + "\"\n");

	}

	public void callMain() {
		out.println("call main");
		out.println("halt");
		out.flush();
	}

	private void generarCodigo(String code) {
		out.println("\t " + code);
		out.flush();
	}

	public void generarLinea(int linea) {
		out.println();
		out.println("#line\t" + linea);
		out.flush();
	}

	public void generateComentario(String info) {
		out.println("\t ' * " + info);
		out.flush();
	}
	
	public int getLabels(int cantidad) {
		int anteriores = labels;
		labels += cantidad;
		return anteriores;
	}

	// ===========PUSH============

	public void pushBP() {
		this.generarCodigo("push bp");
	}

	public void push(String sufijo, int value) {
		this.generarCodigo("push" + sufijo + " " + value);
	}

	public void push(String sufijo, double value) {
		this.generarCodigo("push" + sufijo + " " + value);
	}

	public void pusha(DefVariable df) {
		if (df.getAmbito() == 0) {
			this.generarCodigo("pusha " + df.getOffset());
		} else {
			// Posicion relativa (variable local o parametro)
			this.pushBP();
			this.push("i", df.getOffset());
			this.add("i");
		}
	}

	// ===========LOAD Y STORE============

	public void load(String sufijo) {
		this.generarCodigo("load" + sufijo);
	}

	public void store(String sufijo) {
		this.generarCodigo("store" + sufijo);
	}

	// ===========POP Y DUP============

	public void pop(String sufijo) {
		this.generarCodigo("pop" + sufijo);
	}

	public void dup(String sufijo) {
		this.generarCodigo("dup" + sufijo);
	}

	// ===========INSTRUCCIONES ARITMETICAS============

	public void add(String sufijo) {
		this.generarCodigo("add" + sufijo);
	}

	public void sub(String sufijo) {
		this.generarCodigo("sub" + sufijo);
	}

	public void mult(String sufijo) {
		this.generarCodigo("mul" + sufijo);
	}

	public void div(String sufijo) {
		this.generarCodigo("div" + sufijo);
	}

	public void mod(String sufijo) {
		this.generarCodigo("mod" + sufijo);
	}

	// ===========INSTRUCCIONES COMPARACION============

	public void gt(String sufijo) {
		this.generarCodigo("gt" + sufijo);
	}

	public void lt(String sufijo) {
		this.generarCodigo("lt" + sufijo);
	}

	public void ge(String sufijo) {
		this.generarCodigo("ge" + sufijo);
	}

	public void le(String sufijo) {
		this.generarCodigo("le" + sufijo);
	}

	public void eq(String sufijo) {
		this.generarCodigo("eq" + sufijo);
	}

	public void ne(String sufijo) {
		this.generarCodigo("ne" + sufijo);
	}

	// ===========INSTRUCCIONES LOGICAS============

	public void and() {
		this.generarCodigo("and");
	}

	public void or() {
		this.generarCodigo("or");
	}

	public void not() {
		this.generarCodigo("not");
	}

	// ===========INPUT OUTPUT============

	public void in(String sufijo) {
		this.generarCodigo("in" + sufijo);
	}

	public void out(String sufijo) {
		this.generarCodigo("out" + sufijo);
	}

	// ===========CONVERSION============

	public void convertTo(Tipo tipo1, Tipo tipo2) {

		if (tipo2 instanceof Entero) {
			if (tipo1 instanceof Entero)
				return;
			else
				this.generarCodigo(tipo1.sufijo() + "2" + tipo2.sufijo());
		} else if (tipo2 instanceof Char) {
			if (tipo1 instanceof Char)
				return;
			else if (tipo1 instanceof Entero)
				this.generarCodigo(tipo1.sufijo() + "2" + tipo2.sufijo());
			else {
				this.generarCodigo(tipo1.sufijo() + "2i");
				this.generarCodigo("i2" + tipo2.sufijo());
			}
		} else if (tipo2 instanceof Real) {
			if (tipo1 instanceof Real)
				return;
			else if (tipo1 instanceof Entero)
				this.generarCodigo(tipo1.sufijo() + "2" + tipo2.sufijo());
			else {
				this.generarCodigo(tipo1.sufijo() + "2i");
				this.generarCodigo("i2" + tipo2.sufijo());
			}
		}

	}

	// ===========SALTO============

	public void jmp(String label) {
		this.generarCodigo("jmp " + label);
	}

	public void jz(String label) {
		this.generarCodigo("jz " + label);
	}

	public void jnz(String label) {
		this.generarCodigo("jnz " + label);
	}

	// ===========FUNCIONES============

	public void id(String label) {
		out.println(" " + label.toLowerCase() + ":");
		out.flush();
	}

	public void call(String label) {
		this.generarCodigo("call " + label);
	}

	public void enter(int value) {
		this.generarCodigo("enter " + value);
	}

	public void ret(int returnValueSize, int localVariablesSize, int parametersSize) {
		this.generarCodigo("ret " + returnValueSize + ", " + localVariablesSize + ", " + parametersSize);
	}

	// ===========OTROS============

	public void nop() {
		this.generarCodigo("nop");
	}

	// ===========OPERACIONES=========

	public void aritmetica(String operando, Tipo tipo) {
		String sufijo = tipo instanceof Char? "i" : tipo.sufijo();
		switch (operando) {
			case "+":
				this.add(sufijo);
				break;
			case "-":
				this.sub(sufijo);
				break;
			case "*":
				this.mult(sufijo);
				break;
			case "/":
				this.div(sufijo);
				break;
			case "%":
				this.mod(sufijo);
				break;
			}
	}

	public void comparacion(String operando, Tipo tipo) {
		String sufijo = tipo instanceof Char? "i" : tipo.sufijo();
		switch (operando) {
			case "<":
				this.lt(sufijo);
				break;
			case ">":
				this.gt(sufijo);
				break;
			case "<=":
				this.ge(sufijo);
				break;
			case ">=":
				this.le(sufijo);
				break;
			case "==":
				this.eq(sufijo);
				break;
			case "!=":
				this.ne(sufijo);
				break;
			}
	}

	public void logica(String operando, Tipo tipo) {
		switch (operando) {
			case "&&":
				this.and();
				break;
			case "||":
				this.or();
				break;
			}
	}
}
