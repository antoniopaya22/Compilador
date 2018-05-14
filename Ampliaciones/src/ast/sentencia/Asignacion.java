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
public class Asignacion extends AbstractNodoAST implements Sentencia {

	private Expresion exp1;
	private Expresion exp2;

	public Asignacion(int i, int j, Object variable, Object expresion) {
		super(i, j);
		this.exp1 = (Expresion) variable;
		this.exp2 = (Expresion) expresion;
	}
	
	public Asignacion(int i, int j, Expresion variable, Expresion expresion) {
		super(i, j);
		this.exp1 = variable;
		this.exp2 = expresion;
	}

	public Expresion getExp1() {
		return exp1;
	}

	public void setExp1(Expresion variable) {
		this.exp1 = variable;
	}

	public Expresion getExp2() {
		return exp2;
	}

	public void setExp2(Expresion expresion) {
		this.exp2 = expresion;
	}

	@Override
	public String toString() {
		return "Asignacion [exp1=" + exp1 + ", exp2=" + exp2 + "]";
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
}
