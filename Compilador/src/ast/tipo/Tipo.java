/**
 * 
 */
package ast.tipo;

import java.util.List;

import ast.NodoAST;
import ast.expresion.Expresion;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public interface Tipo extends NodoAST{
	
	boolean esLogico();
	
	Tipo aritmetica(Tipo tipo);
	
	Tipo aritmetica();
	
	Tipo comparacion(Tipo tipo);
	
	Tipo logica(Tipo tipo);
	
	Tipo logica();
	
	Tipo punto(String campo);
	
	Tipo corchetes(Tipo tipo);
	
	Tipo cast(Tipo tipo);
	
	Tipo parentesis(List<Expresion> list);
	
	boolean esBasico();
	
	int getNumeroBytes();
	
	Tipo promocionaA(Tipo tipo);
	
	String sufijo();

	Tipo esMayor(Tipo tipo);

}
