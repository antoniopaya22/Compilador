package ast.sentencia;

import java.util.List;

import ast.AbstractNodoAST;
import ast.expresion.Expresion;
import visitor.Visitor;

public class DoWhile extends AbstractNodoAST implements Sentencia {

	private Expresion condicion;
	private List<Sentencia> cuerpo;

	@SuppressWarnings("unchecked")
	public DoWhile(int i, int j, Object condicion, Object cuerpo) {
		super(i, j);
		this.condicion = (Expresion) condicion;
		this.cuerpo = (List<Sentencia>) cuerpo;
	}
	
	public DoWhile(int i, int j, Expresion condicion, List<Sentencia> cuerpo) {
		super(i, j);
		this.condicion = condicion;
		this.cuerpo = cuerpo;
	}

	public Expresion getCondicion() {
		return condicion;
	}

	public void setCondicion(Expresion condicion) {
		this.condicion = condicion;
	}

	public List<Sentencia> getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo_if(List<Sentencia> cuerpo) {
		this.cuerpo = cuerpo;
	}

	@Override
	public String toString() {
		return "DoWhile [condicion=" + condicion + ", cuerpo=" + cuerpo + "]";
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
}
