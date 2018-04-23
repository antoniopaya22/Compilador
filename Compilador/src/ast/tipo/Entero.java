
package ast.tipo;

import visitor.Visitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class Entero extends AbstractTipo {

	private static Entero instancia;

	public Entero(int line, int column) {
		super(line, column);
	}

	public static Entero getInstancia() {
		if (instancia == null) {
			instancia = new Entero(0, 0);
		}
		return instancia;
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
	
	@Override
	public boolean esLogico() {
		return true;
	}
	
	@Override
	public Tipo aritmetica(Tipo t) {
		if((t instanceof Entero) || (t instanceof TipoError) || (t instanceof Real))
			return t;
		else if(t instanceof Char)
			return this;
		return null;
	}
	
	@Override
	public Tipo aritmetica() {
		return this;
	}
	
	@Override
	public Tipo comparacion(Tipo t) {
		if((t instanceof Entero) || (t instanceof Char) || (t instanceof Real))
			return this;
		else if(t instanceof TipoError)
			return t;
		return null;
	}
	
	@Override
	public Tipo logica(Tipo t) {
		if((t instanceof Entero) || (t instanceof Char))
			return this;
		else if(t instanceof TipoError)
			return t;
		return null;
	}
	
	@Override
	public Tipo logica() {
		return this;
	}
	
	@Override
	public Tipo cast(Tipo t) {
		return t;
	}
	
	@Override
	public boolean esBasico() {
		return true;
	}
	
	@Override
	public Tipo promocionaA(Tipo t) {
		if((t instanceof Real) || (t instanceof TipoError))
			return t;
		else if(t instanceof Entero)
			return this;
		return null;
	}

	@Override
	public int getNumeroBytes() {
		return 2;
	}
	
	@Override
	public String sufijo() {
		return "i";
	}
	
	@Override
	public Tipo esMayor(Tipo c) {
		if(c instanceof Char || c instanceof Entero)
			return c;
		if(c instanceof Real)
			return Real.getInstancia();
		return null;
	}
	
	@Override
	public String toString() {
		return "int";
	}
}
