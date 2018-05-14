/**
 * 
 */
package ast.sentencia;

import ast.AbstractNodoAST;
import ast.expresion.Expresion;
import visitor.Visitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class Return extends AbstractNodoAST implements Sentencia {

	private Expresion expresion;

	
	public Return(int i, int j, Object expresion) {
		super(i, j);
		this.expresion = (Expresion) expresion;
	}

	public Return(int i, int j, Expresion expresion) {
		super(i, j);
		this.expresion = expresion;
	}

	public Expresion getExpresion() {
		return expresion;
	}

	public void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "Return [expresion=" + expresion + "]";
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
}
