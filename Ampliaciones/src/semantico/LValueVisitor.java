/**
 * 
 */
package semantico;

import ast.expresion.AccesoArray;
import ast.expresion.AccesoCampoStruct;
import ast.expresion.Aritmetica;
import ast.expresion.Cast;
import ast.expresion.Comparacion;
import ast.expresion.Expresion;
import ast.expresion.InvocacionFuncion;
import ast.expresion.LiteralChar;
import ast.expresion.LiteralEntero;
import ast.expresion.LiteralReal;
import ast.expresion.Logica;
import ast.expresion.MenosUnario;
import ast.expresion.NotUnario;
import ast.expresion.Ternario;
import ast.expresion.Variable;
import ast.sentencia.Asignacion;
import ast.sentencia.Lectura;
import ast.tipo.TipoError;
import visitor.AbstractVisitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class LValueVisitor extends AbstractVisitor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.Aritmetica, java.lang.Object)
	 */
	@Override
	public Object visit(Aritmetica e, Object param) {
		e.setLValue(false);
		return super.visit(e, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.AccesoArray, java.lang.Object)
	 */
	@Override
	public Object visit(AccesoArray e, Object param) {
		e.setLValue(true);
		return super.visit(e, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.InvocacionFuncion, java.lang.Object)
	 */
	@Override
	public Object visit(InvocacionFuncion e, Object param) {
		e.setLValue(false);
		return super.visit(e, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.Comparacion, java.lang.Object)
	 */
	@Override
	public Object visit(Comparacion e, Object param) {
		e.setLValue(false);
		return super.visit(e, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.Cast, java.lang.Object)
	 */
	@Override
	public Object visit(Cast e, Object param) {
		e.setLValue(false);
		return super.visit(e, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.LiteralEntero, java.lang.Object)
	 */
	@Override
	public Object visit(LiteralEntero e, Object param) {
		e.setLValue(false);
		return super.visit(e, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.LiteralReal, java.lang.Object)
	 */
	@Override
	public Object visit(LiteralReal e, Object param) {
		e.setLValue(false);
		return super.visit(e, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.LiteralChar, java.lang.Object)
	 */
	@Override
	public Object visit(LiteralChar e, Object param) {
		e.setLValue(false);
		return super.visit(e, param);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.LiteralChar, java.lang.Object)
	 */
	@Override
	public Object visit(Ternario e, Object param) {
		e.setLValue(false);
		return super.visit(e, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.Logica, java.lang.Object)
	 */
	@Override
	public Object visit(Logica e, Object param) {
		e.setLValue(false);
		return super.visit(e, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.AccesoCampoStruct, java.lang.Object)
	 */
	@Override
	public Object visit(AccesoCampoStruct e, Object param) {
		e.setLValue(true);
		return super.visit(e, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.MenosUnario, java.lang.Object)
	 */
	@Override
	public Object visit(MenosUnario e, Object param) {
		e.setLValue(false);
		return super.visit(e, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.NotUnario, java.lang.Object)
	 */
	@Override
	public Object visit(NotUnario e, Object param) {
		e.setLValue(false);
		return super.visit(e, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.Variable, java.lang.Object)
	 */
	@Override
	public Object visit(Variable e, Object param) {
		e.setLValue(true);
		return super.visit(e, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.sentencia.Asignacion, java.lang.Object)
	 */
	@Override
	public Object visit(Asignacion e, Object param) {
		super.visit(e, param);
		if (!e.getExp1().esLValue()) {
			new TipoError(e.getFila(), e.getColumna(), "La parte izquierda de la asignacion no puede estar ahi");
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.sentencia.Lectura, java.lang.Object)
	 */
	@Override
	public Object visit(Lectura e, Object param) {
		for (Expresion exp : e.getExpresion()) {
			exp.accept(this, param);
			if (!exp.esLValue()) {
				new TipoError(exp.getFila(), exp.getColumna(), "La variable de Read debe ser LValue");
			}
		}
		return null;
	}
}
