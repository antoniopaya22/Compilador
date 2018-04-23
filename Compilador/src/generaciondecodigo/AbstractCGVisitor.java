package generaciondecodigo;

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
import visitor.Visitor;

public abstract class AbstractCGVisitor implements Visitor{
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.LiteralEntero, java.lang.Object)
	 */
	@Override
	public Object visit(LiteralEntero e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a LiteralEntero");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.LiteralReal, java.lang.Object)
	 */
	@Override
	public Object visit(LiteralReal e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a LiteralReal");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.LiteralChar, java.lang.Object)
	 */
	@Override
	public Object visit(LiteralChar e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a LiteralChar");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.Logica, java.lang.Object)
	 */
	@Override
	public Object visit(Logica e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a Logica");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.Comparacion, java.lang.Object)
	 */
	@Override
	public Object visit(Comparacion e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a Comparacion");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.Aritmetica, java.lang.Object)
	 */
	@Override
	public Object visit(Aritmetica e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a Aritmetica");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.AccesoCampoStruct, java.lang.Object)
	 */
	@Override
	public Object visit(AccesoCampoStruct e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a AccesoCampoStruct");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.AccesoArray, java.lang.Object)
	 */
	@Override
	public Object visit(AccesoArray e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a AccesoArray");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.MenosUnario, java.lang.Object)
	 */
	@Override
	public Object visit(MenosUnario e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a MenosUnario");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.NotUnario, java.lang.Object)
	 */
	@Override
	public Object visit(NotUnario e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a NotUnario");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.Variable, java.lang.Object)
	 */
	@Override
	public Object visit(Variable e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a Variable");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.Cast, java.lang.Object)
	 */
	@Override
	public Object visit(Cast e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a Cast");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.expresion.InvocacionFuncion, java.lang.Object)
	 */
	@Override
	public Object visit(InvocacionFuncion e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a InvocacionFuncion");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.sentencia.While, java.lang.Object)
	 */
	@Override
	public Object visit(While e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a While");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.sentencia.Escritura, java.lang.Object)
	 */
	@Override
	public Object visit(Escritura e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a Escritura");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.sentencia.Lectura, java.lang.Object)
	 */
	@Override
	public Object visit(Lectura e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a Lectura");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.sentencia.If, java.lang.Object)
	 */
	@Override
	public Object visit(If e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a If");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.sentencia.Asignacion, java.lang.Object)
	 */
	@Override
	public Object visit(Asignacion e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a Asignacion");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.sentencia.Return, java.lang.Object)
	 */
	@Override
	public Object visit(Return e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a Return");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.sentencia.LlamadaFuncion, java.lang.Object)
	 */
	@Override
	public Object visit(LlamadaFuncion e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a LlamadaFuncion");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.tipo.Char, java.lang.Object)
	 */
	@Override
	public Object visit(Char e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a Char");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.tipo.Entero, java.lang.Object)
	 */
	@Override
	public Object visit(Entero e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a Entero");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.tipo.Real, java.lang.Object)
	 */
	@Override
	public Object visit(Real e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a Real");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.tipo.TipoArray, java.lang.Object)
	 */
	@Override
	public Object visit(TipoArray e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a TipoArray");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.tipo.TipoFuncion, java.lang.Object)
	 */
	@Override
	public Object visit(TipoFuncion e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a TipoFuncion");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.tipo.TipoStruct, java.lang.Object)
	 */
	@Override
	public Object visit(TipoStruct e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a TipoStruct");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(java.lang.Void, java.lang.Object)
	 */
	@Override
	public Object visit(Void e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a Void");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(manejadorerrores.Error, java.lang.Object)
	 */
	@Override
	public Object visit(TipoError e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a TipoError");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.tipo.Campo, java.lang.Object)
	 */
	@Override
	public Object visit(Campo e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a Campo");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.definicion.DefFuncion, java.lang.Object)
	 */
	@Override
	public Object visit(DefFuncion e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a DefFuncion");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.definicion.DefVariable, java.lang.Object)
	 */
	@Override
	public Object visit(DefVariable e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a DefVariable");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ast.Programa, java.lang.Object)
	 */
	@Override
	public Object visit(Programa e, Object param) {
		throw new IllegalStateException("Esta plantilla no se puede aplicar a Programa");
	}

}
