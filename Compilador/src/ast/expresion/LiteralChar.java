package ast.expresion;

import visitor.Visitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class LiteralChar extends AbstractExpresion {

	private char valor;

	public LiteralChar(int i, int j, Object valor) {
		super(i, j);
		this.valor = (char) valor;
	}
	
	public LiteralChar(int i, int j, char valor) {
		super(i, j);
		this.valor = valor;
	}

	public char getValor() {
		return valor;
	}

	public void setValor(char valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "'"+valor+"'";
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
}
