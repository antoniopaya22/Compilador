package ast.expresion;

import ast.definicion.Definicion;
import visitor.Visitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class Variable extends AbstractExpresion {

	private String nombre;
	private Definicion definicion;

	public Variable(int i, int j, Object string) {
		super(i, j);
		this.nombre = (String) string;
	}

	public Variable(int i, int j, String string) {
		super(i, j);
		this.nombre = string;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Definicion getDefinicion() {
		return definicion;
	}

	public void setDefinicion(Definicion definicion) {
		this.definicion = definicion;
	}

	@Override
	public String toString() {
		return ""+nombre;
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
}
