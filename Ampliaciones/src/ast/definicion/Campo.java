package ast.definicion;

import ast.tipo.Tipo;
import visitor.Visitor;

public class Campo extends AbstractDefinicion {

	private String id;
	private Tipo tipo;
	private int offset;

	public Campo(int line, int column, Object nombre, Object tipo) {
		super(line, column);
		this.id = (String) nombre;
		this.tipo = (Tipo) tipo;
		this.offset = -1;
	}

	public Campo(int line, int column, String nombre, Tipo tipo) {
		super(line, column);
		this.id = nombre;
		this.tipo = tipo;
		this.offset = -1;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String nombre) {
		this.id = nombre;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "Campo [nombre=" + id + ", tipo=" + tipo + "]";
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
}
