package ast.expresion;

import visitor.Visitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class LiteralEntero extends AbstractExpresion {

	private int valor;
	
	public LiteralEntero(int i, int j, Object valor) {
		super(i, j);
		this.valor = (int) valor;
	}

	public LiteralEntero(int i, int j, int valor) {
		super(i, j);
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
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
