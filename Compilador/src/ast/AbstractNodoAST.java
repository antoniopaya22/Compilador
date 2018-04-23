/**
 * 
 */
package ast;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public abstract class AbstractNodoAST implements NodoAST {

	protected int fila, columna;

	public AbstractNodoAST(int i, int j) {
		this.fila = i;
		this.columna = j;
	}

	@Override
	public int getFila() {
		return fila;
	}

	@Override
	public int getColumna() {
		return columna;
	}

}
