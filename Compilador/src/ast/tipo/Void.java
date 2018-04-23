
package ast.tipo;

import visitor.Visitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class Void extends AbstractTipo {

	private static Void instancia;

	public Void(int line, int column) {
		super(line, column);
	}

	public static Void getInstancia() {
		if (instancia == null) {
			instancia = new Void(0, 0);
		}
		return instancia;
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}

	@Override
	public int getNumeroBytes() {
		return 0;
	}
}
