package ast.sentencia;

import java.util.List;

import ast.AbstractNodoAST;
import ast.expresion.Expresion;
import visitor.Visitor;

public class If extends AbstractNodoAST implements Sentencia {

	private Expresion condicion;
	private List<Sentencia> cuerpo_if;
	private List<Sentencia> cuerpo_else;

	@SuppressWarnings("unchecked")
	public If(int i, int j, Object condicion, Object cuerpo_if, Object cuerpo_else) {
		super(i, j);
		this.condicion = (Expresion) condicion;
		this.cuerpo_if = (List<Sentencia>) cuerpo_if;
		this.cuerpo_else = (List<Sentencia>) cuerpo_else;
	}
	
	public If(int i, int j, Expresion condicion, List<Sentencia> cuerpo_if, List<Sentencia> cuerpo_else) {
		super(i, j);
		this.condicion = condicion;
		this.cuerpo_if = cuerpo_if;
		this.cuerpo_else = cuerpo_else;
	}

	public Expresion getCondicion() {
		return condicion;
	}

	public void setCondicion(Expresion condicion) {
		this.condicion = condicion;
	}

	public List<Sentencia> getCuerpo_if() {
		return cuerpo_if;
	}

	public void setCuerpo_if(List<Sentencia> cuerpo_if) {
		this.cuerpo_if = cuerpo_if;
	}

	public List<Sentencia> getCuerpo_else() {
		return cuerpo_else;
	}

	public void setCuerpo_else(List<Sentencia> cuerpo_else) {
		this.cuerpo_else = cuerpo_else;
	}

	@Override
	public String toString() {
		return "If [condicion=" + condicion + ", cuerpo_if=" + cuerpo_if + ", cuerpo_else=" + cuerpo_else + "]";
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
}
