
package ast.tipo;

import java.util.List;

import ast.definicion.DefVariable;
import ast.expresion.Expresion;
import visitor.Visitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class TipoFuncion extends AbstractTipo {

	private Tipo tipoRetorno;
	private List<DefVariable> params;

	@SuppressWarnings("unchecked")
	public TipoFuncion(int line, int column, Object tipoRetorno, Object params) {
		super(line, column);
		this.tipoRetorno = (Tipo) tipoRetorno;
		this.params = (List<DefVariable>) params;
	}
	
	public TipoFuncion(int line, int column, Tipo tipoRetorno, List<DefVariable> params) {
		super(line, column);
		this.tipoRetorno = tipoRetorno;
		this.params = params;
	}

	public Tipo getTipoRetorno() {
		return tipoRetorno;
	}

	public void setTipoRetorno(Tipo tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}

	public List<DefVariable> getParams() {
		return params;
	}

	public void setParams(List<DefVariable> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "TipoFuncion [tipoRetorno=" + tipoRetorno + ", params=" + params + "]";
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
	
	@Override
	public Tipo parentesis(List<Expresion> tipos) {
		if (params.size() != tipos.size()) {
			return null;
		}
		Tipo t;
		for (int i = 0; i < tipos.size(); i++) {
			t = tipos.get(i).getTipo();
			if (null == t.promocionaA(params.get(i).getTipo())) {
				return null;
			}
		}
		return this.tipoRetorno;
	}

	@Override
	public int getNumeroBytes() {
		return this.getTipoRetorno().getNumeroBytes();
	}
}
