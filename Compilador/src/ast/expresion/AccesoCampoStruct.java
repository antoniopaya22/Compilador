package ast.expresion;

import visitor.Visitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class AccesoCampoStruct extends AbstractExpresion {

	private Expresion nombre;
	private String campo;

	public AccesoCampoStruct(int i, int j, Object nombre, Object campo) {
		super(i, j);
		this.nombre = (Expresion) nombre;
		this.campo = (String) campo;
	}

	public AccesoCampoStruct(int i, int j,Expresion nombre, String campo) {
		super(i, j);
		this.nombre = nombre;
		this.campo = campo;
	}

	public Expresion getNombre() {
		return nombre;
	}

	public void setNombre(Expresion nombre) {
		this.nombre = nombre;
	}

	public String getCampo() {
		return campo;
	}

	public void setIndex(String campo) {
		this.campo = campo;
	}

	@Override
	public String toString() {
		return ""+nombre+"."+campo;
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
}
