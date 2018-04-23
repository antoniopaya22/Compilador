/**
 * 
 */
package ast.expresion;

import visitor.Visitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class NotUnario extends AbstractExpresion {

	private Expresion expresion;

	public NotUnario(int i, int j, Object exp) {
		super(i, j);
		this.expresion = (Expresion) exp;
	}
	
	public NotUnario(int i, int j, Expresion exp) {
		super(i, j);
		this.expresion = exp;
	}

	public Expresion getExpresion() {
		return expresion;
	}

	public void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "!"+expresion;
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
}
