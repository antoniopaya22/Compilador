/**
 * 
 */
package ast.sentencia;

import java.util.List;

import ast.AbstractNodoAST;
import ast.expresion.Expresion;
import visitor.Visitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class Escritura extends AbstractNodoAST implements Sentencia {

	private List<Expresion> expresiones;
	
	@SuppressWarnings("unchecked")
	public Escritura(int i, int j, Object expresion) {
		super(i, j);
		this.expresiones = (List<Expresion>) expresion;
	}

	public Escritura(int i, int j, List<Expresion> expresion) {
		super(i, j);
		this.expresiones = expresion;
	}

	public List<Expresion> getExpresion() {
		return expresiones;
	}

	public void setExpresion(List<Expresion> expresion) {
		this.expresiones = expresion;
	}

	@Override
	public String toString() {
		return "Escritura [expresion=" + expresiones + "]";
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
}
