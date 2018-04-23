package ast.definicion;

import ast.sentencia.Sentencia;
import ast.tipo.Tipo;
import visitor.Visitor;

public class DefVariable extends AbstractDefinicion implements Sentencia {

	private Tipo tipo;
	private String id;
	private int ambito;
	private int offset;

	public DefVariable(int line, int column, Object id, Object tipo) {
		super(line, column);
		this.tipo = (Tipo) tipo;
		this.id = (String) id;
		this.offset = -1;
	}

	public DefVariable(int line, int column, String id, Tipo tipo) {
		super(line, column);
		this.tipo = tipo;
		this.id = id;
		this.offset = -1;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAmbito() {
		return ambito;
	}

	public void setAmbito(int scope) {
		this.ambito = scope;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "DefVariable [tipo=" + tipo + ", id=" + id + "]";
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
}
