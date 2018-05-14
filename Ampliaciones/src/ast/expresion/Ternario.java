/**
 * 
 */
package ast.expresion;

import visitor.Visitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class Ternario extends AbstractExpresion {

	private Expresion exp1, exp2, exp3;

	public Ternario(int i, int j, Object exp1, Object exp2, Object exp3) {
		super(i, j);
		this.exp1 = (Expresion) exp1;
		this.exp2 = (Expresion) exp2;
		this.exp3 = (Expresion) exp3;
	}

	public Ternario(int i, int j, Expresion exp1, Expresion exp2, Expresion exp3) {
		super(i, j);
		this.exp1 = exp1;
		this.exp2 = exp2;
		this.exp3 = exp3;
	}

	public Expresion getExp1() {
		return exp1;
	}

	public void setExp1(Expresion exp1) {
		this.exp1 = exp1;
	}

	public Expresion getExp2() {
		return exp2;
	}

	public void setExp2(Expresion exp2) {
		this.exp2 = exp2;
	}

	public Expresion getExp3() {
		return exp3;
	}

	public void setExp3(Expresion exp3) {
		this.exp3 = exp3;
	}

	@Override
	public String toString() {
		return exp1 + "?" + exp2 + ":" + exp3;
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
}
