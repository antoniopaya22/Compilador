package ast.expresion;

import visitor.Visitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class LiteralReal extends AbstractExpresion {

	private float valor;

	
	public LiteralReal(int i, int j, Object valor) {
		super(i, j);
		this.valor = (float) valor;
	}
	
	public LiteralReal(int i, int j, float valor) {
		super(i, j);
		this.valor = valor;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return valor+"";
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
}
