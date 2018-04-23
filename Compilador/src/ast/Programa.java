/**
 * 
 */
package ast;

import java.util.List;

import ast.definicion.Definicion;
import visitor.Visitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class Programa extends AbstractNodoAST {

	private List<Definicion> definiciones;

	@SuppressWarnings("unchecked")
	public Programa(int i, int j,Object definiciones) {
		super(i, j);
		this.definiciones = (List<Definicion>) definiciones;
	}
	
	public Programa(int i, int j,List<Definicion> definiciones) {
		super(i, j);
		this.definiciones = definiciones;
	}


	public List<Definicion> getDefiniciones() {
		return definiciones;
	}

	public void setDefiniciones(List<Definicion> definiciones) {
		this.definiciones = definiciones;
	}

	@Override
	public String toString() {
		return "Programa [definiciones=" + definiciones + "]";
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
}
