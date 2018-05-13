package ast.definicion;

import java.util.List;

import ast.expresion.Variable;
import ast.sentencia.Sentencia;
import ast.tipo.TipoFuncion;
import visitor.Visitor;

public class DefFuncion extends AbstractDefinicion {

	private TipoFuncion tipo;
	private Variable id;
	private List<DefVariable> definiciones;
	private List<Sentencia> cuerpo;
	
	public int totalLocalVariableSize;
	public int parametersSize;

	@SuppressWarnings("unchecked")
	public DefFuncion(int line, int column, Object tipo, Object id, Object definiciones, Object cuerpo) {
		super(line,column);
		this.tipo = (TipoFuncion) tipo;
		if(id instanceof Variable)
			this.id = (Variable) id;
		else
			this.id = new Variable(line, column, id);
		this.definiciones = (List<DefVariable>) definiciones;
		this.cuerpo = (List<Sentencia>) cuerpo;
	}

	public DefFuncion(int line, int column, TipoFuncion tipo, Variable id, List<DefVariable> definiciones,
			List<Sentencia> cuerpo) {
		super(line, column);
		this.tipo = tipo;
		this.id = id;
		this.definiciones = definiciones;
		this.cuerpo = cuerpo;
	}

	public TipoFuncion getTipo() {
		return tipo;
	}

	public void setTipo(TipoFuncion tipo) {
		this.tipo = tipo;
	}

	public List<DefVariable> getDefiniciones() {
		return definiciones;
	}

	public void setDefiniciones(List<DefVariable> definiciones) {
		this.definiciones = definiciones;
	}

	public Variable getVariable() {
		return this.id;
	}

	public String getId() {
		return this.getVariable().getNombre();
	}
	
	public void setVariable(Variable id) {
		this.id = id;
	}

	public List<Sentencia> getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(List<Sentencia> cuerpo) {
		this.cuerpo = cuerpo;
	}

	public int getTotalLocalVariableSize() {
		return totalLocalVariableSize;
	}

	public void setTotalLocalVariableSize(int totalLocalVariableSize) {
		this.totalLocalVariableSize = totalLocalVariableSize;
	}

	public int getParametersSize() {
		return parametersSize;
	}

	public void setParametersSize(int parametersSize) {
		this.parametersSize = parametersSize;
	}

	@Override
	public String toString() {
		return "DefFuncion [tipo=" + tipo + ", id=" + id + ", cuerpo=" + cuerpo + "]";
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}

}
