/**
 * 
 */
package ast.definicion;

import ast.AbstractNodoAST;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public abstract class AbstractDefinicion extends AbstractNodoAST implements Definicion{

	

	public AbstractDefinicion(int linea, int columna) {
		super(linea, columna);
	}

	

}
