package ast.sentencia;

import java.util.List;

import ast.AbstractNodoAST;
import ast.expresion.Expresion;
import visitor.Visitor;

public class For extends AbstractNodoAST implements Sentencia {

	private Asignacion asig1, asig2;
	private Expresion condicion;
	private List<Sentencia> cuerpo;

	@SuppressWarnings("unchecked")
	public For(int i, int j, Object asig1, Object condicion, Object asig2, Object cuerpo) {
		super(i, j);
		this.asig1 = (Asignacion) asig1;
		this.asig2 = (Asignacion) asig2;
		this.condicion = (Expresion) condicion;
		this.cuerpo = (List<Sentencia>) cuerpo;
	}

	public For(int i, int j, Asignacion asig1, Expresion condicion, Asignacion asig2, List<Sentencia> cuerpo) {
		super(i, j);
		this.condicion = condicion;
		this.cuerpo = cuerpo;
	}

	public Asignacion getAsig1() {
		return asig1;
	}

	public void setAsig1(Asignacion asig1) {
		this.asig1 = asig1;
	}

	public Asignacion getAsig2() {
		return asig2;
	}

	public void setAsig2(Asignacion asig2) {
		this.asig2 = asig2;
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

	public void setCuerpo(List<Sentencia> cuerpo) {
		this.cuerpo = cuerpo;
	}

	@Override
	public String toString() {
		return "for(" + asig1 + ";" + condicion + ";" + asig2 + ")";
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
}
