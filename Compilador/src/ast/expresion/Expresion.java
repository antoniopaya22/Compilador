package ast.expresion;

import ast.NodoAST;
import ast.tipo.Tipo;

/**
 * 
 * @author Antonio Paya Gonzalez
 *
 */
public interface Expresion extends NodoAST{

	boolean esLValue();

	void setLValue(boolean lValue);

	Tipo getTipo();

	void setTipo(Tipo tipo);
}
