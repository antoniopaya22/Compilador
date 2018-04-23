package ast.tipo;

import java.util.List;

import ast.definicion.Campo;
import visitor.Visitor;

public class TipoStruct extends AbstractTipo {

	private List<Campo> campos;

	@SuppressWarnings("unchecked")
	public TipoStruct(int line, int column, Object campos) {
		super(line, column);
		this.campos = (List<Campo>) campos;
	}
	
	public TipoStruct(int line, int column, List<Campo> campos) {
		super(line, column);
		this.campos = campos;
	}

	public List<Campo> getCampos() {
		return campos;
	}

	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}

	@Override
	public String toString() {
		return "TipoStruct [campos=" + campos + "]";
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}
	
	@Override
	public Tipo punto(String t) {
		for (Campo campo : campos) {
			if(campo.getId().equals(t)) return campo.getTipo();
		}
		return null;
	}

	@Override
	public int getNumeroBytes() {
		return this.getCampos().stream().mapToInt(x -> x.getTipo().getNumeroBytes()).sum();
	}
	
	public Campo getCampo(String nombre) {
		Campo c = campos.stream().filter(x -> x.getId().equals(nombre)).findFirst().get();
		return c;
	}
}
