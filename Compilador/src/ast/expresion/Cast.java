/**
 * 
 */
package ast.expresion;

import ast.tipo.Tipo;
import visitor.Visitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class Cast extends AbstractExpresion {

	private Expresion expresion;
	private Tipo tipoDinamico;

	public Cast(int i, int j, Object tipo, Object exp) {
		super(i, j);
		this.expresion = (Expresion) exp;
		this.tipoDinamico = (Tipo) tipo;
	}

	
	public Cast(int i, int j, Tipo tipo, Expresion exp) {
		super(i, j);
		this.expresion = exp;
		this.tipoDinamico = tipo;
	}

	public Expresion getExpresion() {
		return expresion;
	}

	public void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}

	public Tipo getTipoDinamico() {
		return tipoDinamico;
	}

	public void setTipoDinamico(Tipo tipo) {
		this.tipoDinamico = tipo;
	}

	@Override
	public String toString() {
		return ""+"("+tipoDinamico+") "+expresion;
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
}
