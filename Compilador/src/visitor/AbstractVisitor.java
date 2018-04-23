/**
 * 
 */
package visitor;

import ast.Programa;
import ast.definicion.Campo;
import ast.definicion.DefFuncion;
import ast.definicion.DefVariable;
import ast.expresion.AccesoArray;
import ast.expresion.AccesoCampoStruct;
import ast.expresion.Aritmetica;
import ast.expresion.Cast;
import ast.expresion.Comparacion;
import ast.expresion.InvocacionFuncion;
import ast.expresion.LiteralChar;
import ast.expresion.LiteralEntero;
import ast.expresion.LiteralReal;
import ast.expresion.Logica;
import ast.expresion.MenosUnario;
import ast.expresion.NotUnario;
import ast.expresion.Variable;
import ast.sentencia.Asignacion;
import ast.sentencia.Escritura;
import ast.sentencia.If;
import ast.sentencia.Lectura;
import ast.sentencia.LlamadaFuncion;
import ast.sentencia.Return;
import ast.sentencia.While;
import ast.tipo.Char;
import ast.tipo.Entero;
import ast.tipo.Real;
import ast.tipo.TipoArray;
import ast.tipo.TipoError;
import ast.tipo.TipoFuncion;
import ast.tipo.TipoStruct;
import ast.tipo.Void;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public abstract class AbstractVisitor implements Visitor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.LiteralEntero, java.lang.Object)
	 */
	@Override
	public Object visit(LiteralEntero e, Object param) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.LiteralReal, java.lang.Object)
	 */
	@Override
	public Object visit(LiteralReal e, Object param) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.LiteralChar, java.lang.Object)
	 */
	@Override
	public Object visit(LiteralChar e, Object param) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.Logica, java.lang.Object)
	 */
	@Override
	public Object visit(Logica e, Object param) {
		e.getOp1().accept(this, param);
		e.getOp2().accept(this, param);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.Comparacion, java.lang.Object)
	 */
	@Override
	public Object visit(Comparacion e, Object param) {
		e.getOp1().accept(this, param);
		e.getOp2().accept(this, param);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.Aritmetica, java.lang.Object)
	 */
	@Override
	public Object visit(Aritmetica e, Object param) {
		e.getOp1().accept(this, param);
		e.getOp2().accept(this, param);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.AccesoCampoStruct, java.lang.Object)
	 */
	@Override
	public Object visit(AccesoCampoStruct e, Object param) {
		e.getNombre().accept(this, param);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.AccesoArray, java.lang.Object)
	 */
	@Override
	public Object visit(AccesoArray e, Object param) {
		e.getNombre().accept(this, param);
		e.getIndex().accept(this, param);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.MenosUnario, java.lang.Object)
	 */
	@Override
	public Object visit(MenosUnario e, Object param) {
		e.getExpresion().accept(this, param);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.NotUnario, java.lang.Object)
	 */
	@Override
	public Object visit(NotUnario e, Object param) {
		e.getExpresion().accept(this, param);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.Variable, java.lang.Object)
	 */
	@Override
	public Object visit(Variable e, Object param) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.Cast, java.lang.Object)
	 */
	@Override
	public Object visit(Cast e, Object param) {
		e.getExpresion().accept(this, param);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.InvocacionFuncion, java.lang.Object)
	 */
	@Override
	public Object visit(InvocacionFuncion e, Object param) {
		e.getParams().stream().forEach(x -> x.accept(this, param));
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.sentencia.While, java.lang.Object)
	 */
	@Override
	public Object visit(While e, Object param) {
		e.getCondicion().accept(this, param);
		e.getCuerpo().stream().forEach(x -> x.accept(this, param));
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.sentencia.Escritura, java.lang.Object)
	 */
	@Override
	public Object visit(Escritura e, Object param) {
		e.getExpresion().stream().forEach(x -> x.accept(this, param));
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.sentencia.Lectura, java.lang.Object)
	 */
	@Override
	public Object visit(Lectura e, Object param) {
		e.getExpresion().stream().forEach(x -> x.accept(this, param));
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.sentencia.If, java.lang.Object)
	 */
	@Override
	public Object visit(If e, Object param) {
		e.getCondicion().accept(this, param);
		e.getCuerpo_if().stream().forEach(x -> x.accept(this, param));
		e.getCuerpo_else().stream().forEach(x -> x.accept(this, param));
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.sentencia.Asignacion, java.lang.Object)
	 */
	@Override
	public Object visit(Asignacion e, Object param) {
		e.getExp1().accept(this, param);
		e.getExp2().accept(this, param);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.sentencia.Return, java.lang.Object)
	 */
	@Override
	public Object visit(Return e, Object param) {
		e.getExpresion().accept(this, param);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.sentencia.LlamadaFuncion, java.lang.Object)
	 */
	@Override
	public Object visit(LlamadaFuncion e, Object param) {
		e.getParams().stream().forEach(x -> x.accept(this, param));
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.tipo.Char, java.lang.Object)
	 */
	@Override
	public Object visit(Char e, Object param) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.tipo.Entero, java.lang.Object)
	 */
	@Override
	public Object visit(Entero e, Object param) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.tipo.Real, java.lang.Object)
	 */
	@Override
	public Object visit(Real e, Object param) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.tipo.TipoArray, java.lang.Object)
	 */
	@Override
	public Object visit(TipoArray e, Object param) {
		e.getTipo().accept(this, param);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.tipo.TipoFuncion, java.lang.Object)
	 */
	@Override
	public Object visit(TipoFuncion e, Object param) {
		e.getTipoRetorno().accept(this, param);
		e.getParams().stream().forEach(x -> x.accept(this, param));
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.tipo.TipoStruct, java.lang.Object)
	 */
	@Override
	public Object visit(TipoStruct e, Object param) {
		e.getCampos().stream().forEach(x -> x.accept(this, param));
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(java.lang.Void, java.lang.Object)
	 */
	@Override
	public Object visit(Void e, Object param) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(manejadorerrores.Error, java.lang.Object)
	 */
	@Override
	public Object visit(TipoError e, Object param) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.tipo.Campo, java.lang.Object)
	 */
	@Override
	public Object visit(Campo e, Object param) {
		e.getTipo().accept(this, param);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.definicion.DefFuncion, java.lang.Object)
	 */
	@Override
	public Object visit(DefFuncion e, Object param) {
		e.getTipo().accept(this, param);
		e.getDefiniciones().stream().forEach(x -> x.accept(this, param));
		e.getCuerpo().stream().forEach(x -> x.accept(this, param));
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.definicion.DefVariable, java.lang.Object)
	 */
	@Override
	public Object visit(DefVariable e, Object param) {
		e.getTipo().accept(this, param);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.Programa, java.lang.Object)
	 */
	@Override
	public Object visit(Programa e, Object param) {
		e.getDefiniciones().stream().forEach(x -> x.accept(this, param));
		return null;
	}

}
