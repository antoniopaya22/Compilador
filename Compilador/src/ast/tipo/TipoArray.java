
package ast.tipo;

import visitor.Visitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class TipoArray extends AbstractTipo {

	private Tipo tipo;
	private int tam;

	public TipoArray(int line, int column,Object tipo, Object length) {
		super(line, column);
		this.tipo = (Tipo) tipo;
		this.tam = (int) length;
	}
	
	public TipoArray(int line, int column,Tipo tipo, int length) {
		super(line, column);
		this.tipo = tipo;
		this.tam = length;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public int getTam() {
		return tam;
	}

	@Override
	public String toString() {
		return "TipoArray [Tipo=" + tipo + ", Tam=" + tam + "]";
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
	
	@Override
	public Tipo corchetes(Tipo t) {
		if (t.promocionaA(Entero.getInstancia()) == null) {
			return null;
		}
		return this.tipo;
	}

	@Override
	public int getNumeroBytes() {
		return this.tam + this.getTipo().getNumeroBytes();
	}
}
