
package ast.tipo;

import visitor.Visitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class Real extends AbstractTipo {

	private static Real instancia;

	public Real(int line, int column) {
		super(line, column);
	}

	public static Real getInstancia() {
		if (instancia == null) {
			instancia = new Real(0, 0);
		}
		return instancia;
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
	
	@Override
	public Tipo aritmetica(Tipo t) {
		if((t instanceof Entero) || (t instanceof TipoError) || (t instanceof Real) || (t instanceof Char))
			return t;
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
	public Tipo cast(Tipo t) {
		return t;
	}
	
	@Override
	public boolean esBasico() {
		return true;
	}
	
	@Override
	public Tipo promocionaA(Tipo t) {
		if(t instanceof Real)
			return this;
		else if(t instanceof TipoError)
			return t;
		return null;
	}

	@Override
	public int getNumeroBytes() {
		return 4;
	}
	
	@Override
	public String sufijo() {
		return "f";
	}
	
	@Override
	public Tipo esMayor(Tipo c) {
		if(c instanceof Char || c instanceof Entero || c instanceof Real)
			return this;
		return null;
	}
	
	@Override
	public String toString() {
		return "float32";
	}
}
