/**
 * 
 */
package ast.expresion;

import java.util.List;

import ast.definicion.DefFuncion;
import ast.definicion.Definicion;
import visitor.Visitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class InvocacionFuncion extends AbstractExpresion {

	private Variable nombre;
	private List<Expresion> params;
	private DefFuncion definicion;
	
	@SuppressWarnings("unchecked")
	public InvocacionFuncion(int i, int j, Object nombre, Object params) {
		super(i, j);
		this.nombre = new Variable(i,j,nombre);
		this.params = (List<Expresion>) params;
	}

	public InvocacionFuncion(int i, int j, String nombre, List<Expresion> params) {
		super(i, j);
		this.nombre = new Variable(i,j,nombre);
		this.params = params;
	}

	public Variable getVariable() {
		return nombre;
	}

	public void setVariable(Variable nombre) {
		this.nombre = nombre;
	}

	public List<Expresion> getParams() {
		return params;
	}

	public void setParams(List<Expresion> params) {
		this.params = params;
	}
	
	public Definicion getDefinicion() {
		return definicion;
	}

	public void setDefinicion(DefFuncion definicion) {
		this.definicion = definicion;
	}

	@Override
	public String toString() {
		String cadena = ""+nombre+"(";
		for (Expresion expresion : params) {
			cadena+=expresion+",";
		}
		cadena = cadena.substring(0,cadena.length()-1);
		cadena+=")";
		return cadena;
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
}
