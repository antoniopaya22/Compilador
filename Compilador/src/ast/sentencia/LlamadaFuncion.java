/**
 * 
 */
package ast.sentencia;

import java.util.List;

import ast.AbstractNodoAST;
import ast.definicion.DefFuncion;
import ast.definicion.Definicion;
import ast.expresion.Expresion;
import ast.expresion.Variable;
import visitor.Visitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class LlamadaFuncion extends AbstractNodoAST implements Sentencia {

	private Variable nombre;
	private List<Expresion> params;
	private DefFuncion definicion;

	@SuppressWarnings("unchecked")
	public LlamadaFuncion(int i, int j, Object nombre, Object params) {
		super(i, j);
		this.nombre = new Variable(i,j,nombre);
		this.params = (List<Expresion>) params;
	}
	
	public LlamadaFuncion(int i, int j, String nombre, List<Expresion> params) {
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
		return "InvocacionFuncion [nombre=" + nombre + ", params=" + params + "]";
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
}
