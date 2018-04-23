
package ast.tipo;

import visitor.Visitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class Char extends AbstractTipo {

	private static Char instancia;

	public Char(int line, int column) {
		super(line, column);
	}

	public static Char getInstancia() {
		if (instancia == null) {
			instancia = new Char(0, 0);
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
			return Entero.getInstancia();
		return null;
	}
	
	@Override
	public Tipo aritmetica() {
		return this;
	}
	
	@Override
	public Tipo comparacion(Tipo t) {
		if((t instanceof Entero) || (t instanceof Char) || (t instanceof Real))
			return Entero.getInstancia();
		else if(t instanceof TipoError)
			return t;
		return null;
	}
	
	@Override
	public Tipo logica(Tipo t) {
		if((t instanceof Entero) || (t instanceof Char))
			return Entero.getInstancia();
		else if(t instanceof TipoError)
			return t;
		return null;
	}
	
	@Override
	public Tipo logica() {
		return Entero.getInstancia();
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
		if((t instanceof TipoError) || (t instanceof Entero) || (t instanceof Char))
			return t;
		return null;
	}

	@Override
	public int getNumeroBytes() {
		return 1;
	}
	
	@Override
	public String sufijo() {
		return "b";
	}
	
	@Override
	public Tipo esMayor(Tipo c) {
		if(c instanceof Char || c instanceof Entero || c instanceof Real)
			return c;
		return null;
	}

	@Override
	public String toString() {
		return "char";
	}
	
	
}
