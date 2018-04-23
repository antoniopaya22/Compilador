package ast.definicion;

import ast.NodoAST;
import ast.tipo.Tipo;

public interface Definicion extends NodoAST{

	Tipo getTipo();
	
	String getId();
}
