package ast.expresion;

import visitor.Visitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class AccesoArray extends AbstractExpresion {

	private Expresion nombre;
	private Expresion index;
	
	
	public AccesoArray(int i, int j, Object nombre, Object index) {
		super(i, j);
		this.nombre = (Expresion) nombre;
		this.index = (Expresion) index;
	}


	public AccesoArray(int i, int j, Expresion nombre, Expresion index) {
		super(i, j);
		this.nombre = nombre;
		this.index = index;
	}

	public Expresion getNombre() {
		return nombre;
	}

	public void setNombre(Expresion nombre) {
		this.nombre = nombre;
	}

	public Expresion getIndex() {
		return index;
	}

	public void setIndex(Expresion index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return ""+nombre+"["+index+"]";
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
}
