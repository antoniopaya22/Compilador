/**
 * 
 */
package semantico;

import ast.definicion.Campo;
import ast.definicion.DefFuncion;
import ast.definicion.DefVariable;
import ast.definicion.Definicion;
import ast.expresion.AccesoCampoStruct;
import ast.expresion.Expresion;
import ast.expresion.InvocacionFuncion;
import ast.expresion.Variable;
import ast.sentencia.LlamadaFuncion;
import ast.tipo.TipoError;
import ast.tipo.TipoStruct;
import visitor.AbstractVisitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class IdVisitor extends AbstractVisitor {

	private TablaSimbolos tabla;

	public IdVisitor() {
		super();
		this.tabla = new TablaSimbolos();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.definicion.DefFuncion, java.lang.Object)
	 */
	@Override
	public Object visit(DefFuncion e, Object param) {
		if (!tabla.insertar(e)) {
			new TipoError(e.getFila(), e.getColumna(), "La funcion " + e.getId() + " ya existe en el programa");
		}
		tabla.set();
		super.visit(e, param);
		tabla.reset();

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.definicion.DefVariable, java.lang.Object)
	 */
	@Override
	public Object visit(DefVariable e, Object param) {
		if (!tabla.insertar(e)) {
			new TipoError(e.getFila(), e.getColumna(), "La variable " + e.getId() + " ya existe en el ambito actual");
		}
		super.visit(e, param);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.Variable, java.lang.Object)
	 */
	@Override
	public Object visit(Variable e, Object param) {
		Definicion aux = tabla.buscar(e.getNombre());
		if (aux == null) {
			// Definicion de variable con tipo error
			e.setDefinicion(new DefVariable(e.getFila(), e.getColumna(), e.getNombre(),
					new TipoError(e.getFila(), e.getColumna(), "La variable " + e.getNombre() + " no esta declarada")));
		} else {
			e.setDefinicion(aux);
		}
		return aux;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.sentencia.LlamadaFuncion, java.lang.Object)
	 */
	@Override
	public Object visit(InvocacionFuncion e, Object param) {
		Definicion aux = tabla.buscar(e.getVariable().getNombre());
		if (aux == null) {
			new TipoError(e.getFila(), e.getColumna(),
					"La funcion " + e.getVariable().getNombre() + " no esta declarada");
		} else {
			e.setDefinicion((DefFuncion)aux);
		}
		for (Expresion exp : e.getParams()) {
			exp.accept(this, param);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.sentencia.LlamadaFuncion, java.lang.Object)
	 */
	@Override
	public Object visit(LlamadaFuncion e, Object param) {
		Definicion aux = tabla.buscar(e.getVariable().getNombre());
		if (aux == null) {
			new TipoError(e.getFila(), e.getColumna(),
					"La funcion " + e.getVariable().getNombre() + " no esta declarada");
		} else {
			e.setDefinicion((DefFuncion)aux);
		}
		for (Expresion exp : e.getParams()) {
			exp.accept(this, param);
		}

		return null;
	}

	@Override
	public Object visit(TipoStruct e, Object param) {
		tabla.set();
		for (Campo c : e.getCampos()) {
			c.accept(this, param);
		}
		tabla.reset();
		return null;
	}
	
	@Override
	public Object visit(Campo e, Object param) {
//		if (!tabla.insertar(e)) {
//			new TipoError(e.getFila(), e.getColumna(), "El campo " + e.getId() + " ya existe en el struct");
//		}
		tabla.insertar(e);
		e.getTipo().accept(this, param);
		return null;
	}

	@Override
	public Object visit(AccesoCampoStruct e, Object param) {
		Object aux = e.getNombre().accept(this, param);
		if (aux != null) {
			if (aux instanceof TipoStruct) {
				for (Campo campo : ((TipoStruct) aux).getCampos()) {
					if (campo.getId().equals(e.getCampo())) {
						return campo.getTipo();
					}
				}
				new TipoError(e.getFila(), e.getColumna(), "El campo " + e.getCampo() + " no existe");
			}
		}
		return null;
	}

}
