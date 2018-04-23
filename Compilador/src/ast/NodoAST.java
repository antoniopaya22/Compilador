package ast;

import visitor.Visitor;

public interface NodoAST {
	public Object accept(Visitor v, Object object);
	public int getFila();
	public int getColumna();
}
