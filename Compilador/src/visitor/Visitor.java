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
import ast.expresion.AsignacionLogica;
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
public interface Visitor {
	
	//========EXPRESIONES=============
		//Constantes
	public Object visit(LiteralEntero e, Object param);
	public Object visit(LiteralReal e, Object param);
	public Object visit(LiteralChar e, Object param);
		//Expresiones binarias
	public Object visit(Logica e, Object param);
	public Object visit(Comparacion e, Object param);
	public Object visit(Aritmetica e, Object param);
	public Object visit(AccesoCampoStruct e, Object param);
	public Object visit(AccesoArray e, Object param);
	public Object visit(AsignacionLogica asignacionLogica, Object object);
		//Expresiones unarias
	public Object visit(MenosUnario e, Object param);
	public Object visit(NotUnario e, Object param);
		//Otras expresiones
	public Object visit(Variable e, Object param);
	public Object visit(Cast e, Object param);
	public Object visit(InvocacionFuncion e, Object param);
	
	//========SENTENCIAS=============
	public Object visit(While e, Object param);
	public Object visit(Escritura e, Object param);
	public Object visit(Lectura e, Object param);
	public Object visit(If e, Object param);
	public Object visit(Asignacion e, Object param);
	public Object visit(Return e, Object param);
	public Object visit(LlamadaFuncion e, Object param);
	
	//========TIPOS=============
	public Object visit(Char e, Object param);
	public Object visit(Entero e, Object param);
	public Object visit(Real e, Object param);
	public Object visit(TipoArray e, Object param);
	public Object visit(TipoFuncion e, Object param);
	public Object visit(TipoStruct e, Object param);
	public Object visit(Void e, Object param);
	public Object visit(TipoError tipoError, Object object);
	public Object visit(Campo e, Object param);
	
	//========DEFINICIONES=============
	public Object visit(DefFuncion e, Object param);
	public Object visit(DefVariable e, Object param);
	
	//========OTROS====================
	public Object visit(Programa e, Object param);
}
