/**
 * 
 */
package ast.tipo;

import manejadorerrores.ME;
import visitor.Visitor;

/**
 * @author anton
 *
 */
public class TipoError extends AbstractTipo {

	public String mensaje;

	public TipoError(int i, int j, String error) {
		super(i, j);
		this.mensaje = error;
		ME.getME().addError(this);
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}

	@Override
	public int getNumeroBytes() {
		throw new IllegalStateException("NO DEBERIA DE EJECUTARSE ESTO");
	}

}
