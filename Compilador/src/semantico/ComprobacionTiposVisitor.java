package semantico;

import ast.definicion.DefFuncion;
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
import ast.sentencia.Sentencia;
import ast.sentencia.While;
import ast.tipo.Char;
import ast.tipo.Entero;
import ast.tipo.Real;
import ast.tipo.Tipo;
import ast.tipo.TipoError;
import ast.tipo.Void;
import visitor.AbstractVisitor;

public class ComprobacionTiposVisitor extends AbstractVisitor {

	@Override
	public Object visit(LiteralEntero e, Object param) {
		e.setTipo(Entero.getInstancia());
		return null;
	}

	@Override
	public Object visit(LiteralReal e, Object param) {
		e.setTipo(Real.getInstancia());
		return null;
	}

	@Override
	public Object visit(LiteralChar e, Object param) {
		e.setTipo(Char.getInstancia());
		return null;
	}

	@Override
	public Object visit(Variable e, Object param) {
		e.setTipo(e.getDefinicion().getTipo());
		return null;
	}

	@Override
	public Object visit(While e, Object param) {
		e.getCondicion().accept(this, param);
		if (!e.getCondicion().getTipo().esLogico())
			e.getCondicion().setTipo(new TipoError(e.getCondicion().getFila(), e.getCondicion().getColumna(),
					"La condición del while debe ser de tipo Logico"));
		e.getCuerpo().stream().forEach(x -> x.accept(this, param));
		return false;
	}

	@Override
	public Object visit(If e, Object param) {
		boolean returnIf = false;
		boolean returnElse = false;
		
		e.getCondicion().accept(this, param);
		if (!e.getCondicion().getTipo().esLogico())
			e.getCondicion().setTipo(new TipoError(e.getCondicion().getFila(), e.getCondicion().getColumna(),
					"La condición del if debe ser de tipo Logico"));

		for (Sentencia x : e.getCuerpo_if()) {
			if ((boolean) x.accept(this, param)) {
				returnIf = true;
			}
		}
		for (Sentencia x : e.getCuerpo_else()) {
			if ((boolean) x.accept(this, param)) {
				returnElse = true;
			}
		}
		if(e.getCuerpo_else().size() == 0) return false;
		return returnIf && returnElse;
	}

	@Override
	public Object visit(Aritmetica e, Object param) {
		e.getOp1().accept(this, param);
		e.getOp2().accept(this, param);
		e.setTipo(e.getOp1().getTipo().aritmetica(e.getOp2().getTipo()));
		if (e.getTipo() == null)
			e.setTipo(new TipoError(e.getFila(), e.getColumna(),
					"Los operandos de una expresion aritmetica deben ser operables"));
		return null;
	}

	@Override
	public Object visit(MenosUnario e, Object param) {
		e.getExpresion().accept(this, param);
		e.setTipo(e.getExpresion().getTipo().aritmetica());
		if (e.getTipo() == null)
			e.setTipo(new TipoError(e.getFila(), e.getColumna(),
					"Para realizar un menos unario se requiere una expresion aritmetica"));
		return null;
	}

	@Override
	public Object visit(NotUnario e, Object param) {
		e.getExpresion().accept(this, param);
		e.setTipo(e.getExpresion().getTipo().logica());
		if (e.getTipo() == null)
			e.setTipo(new TipoError(e.getFila(), e.getColumna(),
					"Para realizar un not unario se requiere una expresion logica"));
		return null;
	}

	@Override
	public Object visit(Comparacion e, Object param) {
		e.getOp1().accept(this, param);
		e.getOp2().accept(this, param);
		e.setTipo(e.getOp1().getTipo().comparacion(e.getOp2().getTipo()));
		if (e.getTipo() == null)
			e.setTipo(new TipoError(e.getFila(), e.getColumna(), "La comparacion no es posible con esos operandos"));
		return null;
	}

	@Override
	public Object visit(Logica e, Object param) {
		e.getOp1().accept(this, param);
		e.getOp2().accept(this, param);
		e.setTipo(e.getOp1().getTipo().logica(e.getOp2().getTipo()));
		if (e.getTipo() == null)
			e.setTipo(
					new TipoError(e.getFila(), e.getColumna(), "La operacion logica no es posible con esos operandos"));
		return null;
	}
	
	@Override
	public Object visit(AsignacionLogica e, Object param) {
		e.getOp1().accept(this, param);
		e.getOp2().accept(this, param);
		e.setTipo(e.getOp1().getTipo().logica(e.getOp2().getTipo()));
		if (e.getTipo() == null)
			e.setTipo(
					new TipoError(e.getFila(), e.getColumna(), "La operacion logica no es posible con esos operandos"));
		return null;
	}

	@Override
	public Object visit(AccesoCampoStruct e, Object param) {
		super.visit(e, param);
		e.setTipo(e.getNombre().getTipo().punto(e.getCampo()));
		if (e.getTipo() == null)
			e.setTipo(new TipoError(e.getFila(), e.getColumna(),
					"El struct " + e.getNombre() + " no contiene el campo "+e.getCampo()));
		return null;
	}

	@Override
	public Object visit(AccesoArray e, Object param) {
		super.visit(e, param);
		e.setTipo(e.getNombre().getTipo().corchetes(e.getIndex().getTipo()));
		if (e.getTipo() == null)
			e.setTipo(new TipoError(e.getFila(), e.getColumna(), "Fallo en el acceso al array"));
		return null;
	}

	@Override
	public Object visit(Cast e, Object param) {
		super.visit(e, param);
		e.setTipo(e.getTipoDinamico());
		if (e.getTipo() == null)
			e.setTipo(new TipoError(e.getFila(), e.getColumna(), "Fallo en el cast, tipo no casteable"));
		return null;
	}

	@Override
	public Object visit(LlamadaFuncion e, Object param) {
		super.visit(e, param);

		if (e.getDefinicion() != null && (e.getDefinicion().getTipo().parentesis(e.getParams())) == null) {
			new TipoError(e.getFila(), e.getColumna(), "Llamada a funcion no valida");
		}
		return false;
	}

	@Override
	public Object visit(InvocacionFuncion e, Object param) {
		super.visit(e, param);
		if (e.getDefinicion() != null) {
			e.setTipo(e.getDefinicion().getTipo().parentesis(e.getParams()));
			if (e.getTipo() == null)
				e.setTipo(new TipoError(e.getFila(), e.getColumna(), "La llamada a la funcion no es posible"));
		}
		return false;
	}

	@Override
	public Object visit(Asignacion e, Object param) {
		super.visit(e, param);
		if (e.getExp2().getTipo().promocionaA(e.getExp1().getTipo()) == null) {
			e.getExp1().setTipo(new TipoError(e.getFila(), e.getColumna(), "La asignacion no es posible"));
		}
		return false;
	}
	
	@Override
	public Object visit(Escritura e, Object param) {
		super.visit(e, param);
		return false;
	}
	
	@Override
	public Object visit(Lectura e, Object param) {
		super.visit(e, param);
		return false;
	}
	
	@Override
	public Object visit(DefFuncion e, Object param) {
		boolean tieneReturn = false;
		e.getTipo().accept(this, param);
		e.getDefiniciones().stream().forEach(x -> x.accept(this, param));
		for(Sentencia set : e.getCuerpo()) {
			if ((boolean) set.accept(this, e.getTipo().getTipoRetorno())) {
				tieneReturn = true;
			}
		}
		if(!tieneReturn && !(e.getTipo().getTipoRetorno() instanceof Void ))
			new TipoError(e.getFila(), e.getColumna(), "La funcion no tiene un return");
		return null;
	}
	
	@Override
	public Object visit(Return e, Object param) {
		e.getExpresion().accept(this, param);
		if(e.getExpresion().getTipo().promocionaA((Tipo) param) == null) {
			new TipoError(e.getFila(), e.getColumna(), "El tipo de return no es igual al de la funcion");
		}
		return true;
	}
}
