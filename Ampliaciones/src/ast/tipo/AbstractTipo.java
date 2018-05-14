package ast.tipo;

import java.util.List;

import ast.AbstractNodoAST;
import ast.expresion.Expresion;

public abstract class AbstractTipo extends AbstractNodoAST implements Tipo {

	public AbstractTipo(int line, int column) {
		super(line, column);
	}
	
	@Override
	public boolean esLogico() {
		return false;
	}

	@Override
	public Tipo aritmetica(Tipo tipo) {
		return null;
	}

	@Override
	public Tipo aritmetica() {
		return null;
	}

	@Override
	public Tipo comparacion(Tipo tipo) {
		return null;
	}

	@Override
	public Tipo logica(Tipo tipo) {
		return null;
	}

	@Override
	public Tipo logica() {
		return null;
	}

	@Override
	public Tipo punto(String campo) {
		return null;
	}

	@Override
	public Tipo corchetes(Tipo tipo) {
		return null;
	}

	@Override
	public Tipo cast(Tipo tipo) {
		return null;
	}

	@Override
	public Tipo parentesis(List<Expresion> tipos) {
		return null;
	}

	@Override
	public boolean esBasico() {
		return false;
	}

	@Override
	public Tipo promocionaA(Tipo tipo) {
		return null;
	}

	@Override
	public String sufijo() {
		throw new IllegalStateException("El tipo no tiene sufijo");
	}
	
	@Override
	public Tipo esMayor(Tipo c) {
		throw new IllegalStateException("El tipo "+c.toString()+" no se puede comparar con "+this);
	}
}
