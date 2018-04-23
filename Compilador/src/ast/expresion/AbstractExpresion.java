/**
 * 
 */
package ast.expresion;

import ast.AbstractNodoAST;
import ast.tipo.Tipo;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public abstract class AbstractExpresion extends AbstractNodoAST implements Expresion{

	private boolean lValue;
	private Tipo tipo;
	
	public AbstractExpresion(int i, int j) {
		super(i, j);
	}

	@Override
	public boolean esLValue() {
		return lValue;
	}

	@Override
	public void setLValue(boolean lValue) {
		this.lValue = lValue;
	}

	@Override
	public Tipo getTipo() {
		return tipo;
	}

	@Override
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}
