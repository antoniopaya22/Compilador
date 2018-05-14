package generaciondecodigo;

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
import ast.expresion.Variable;
import ast.tipo.Tipo;
import ast.tipo.TipoFuncion;

public class ValueCGVisitor extends AbstractCGVisitor {

	private CodeGenerator cg;
	private AddressCGVisitor av;

	public ValueCGVisitor(CodeGenerator cg) {
		this.cg = cg;
	}

	public void setAv(AddressCGVisitor av) {
		this.av = av;
	}

	@Override
	public Object visit(Variable e, Object param) {
		e.accept(av, param);
		cg.load(e.getTipo().sufijo());
		return null;
	}

	@Override
	public Object visit(LiteralEntero e, Object param) {
		cg.push("i", e.getValor());
		return null;
	}

	@Override
	public Object visit(LiteralChar e, Object param) {
		cg.push("b", e.getValor());
		return null;
	}

	@Override
	public Object visit(LiteralReal e, Object param) {
		cg.push("f", e.getValor());
		return null;
	}

	@Override
	public Object visit(AccesoArray e, Object param) {
		e.accept(av, param);
		cg.load(e.getTipo().sufijo());
		return null;
	}

	@Override
	public Object visit(AccesoCampoStruct e, Object param) {
		e.accept(av, param);
		cg.load(e.getTipo().sufijo());
		return null;
	}

	@Override
	public Object visit(Cast e, Object param) {
		e.getExpresion().accept(this, param);
		cg.convertTo(e.getExpresion().getTipo(), e.getTipo());
		return null;
	}

	@Override
	public Object visit(Aritmetica e, Object param) {
		e.getOp1().accept(this, param);
		cg.convertTo(e.getOp1().getTipo(), e.getTipo());
		e.getOp2().accept(this, param);
		cg.convertTo(e.getOp2().getTipo(), e.getTipo());
		cg.aritmetica(e.getOperador(), e.getTipo());
		return null;
	}

	@Override
	public Object visit(Comparacion e, Object param) {
		Tipo tipoMayor = e.getOp1().getTipo().esMayor(e.getOp2().getTipo());
		e.getOp1().accept(this, param);
		cg.convertTo(e.getOp1().getTipo(), tipoMayor);
		e.getOp2().accept(this, param);
		cg.convertTo(e.getOp2().getTipo(), tipoMayor);
		cg.comparacion(e.getOperador(), tipoMayor);
		return null;
	}

	@Override
	public Object visit(Logica e, Object param) {
		e.getOp1().accept(this, param);
		cg.convertTo(e.getOp1().getTipo(), e.getTipo());
		e.getOp2().accept(this, param);
		cg.convertTo(e.getOp2().getTipo(), e.getTipo());
		cg.logica(e.getOperador(), e.getTipo());
		return null;
	}

	@Override
	public Object visit(MenosUnario e, Object param) {
		cg.push(e.getExpresion().getTipo().sufijo(), 0);
		e.getExpresion().accept(this, param);
		cg.sub(e.getTipo().sufijo());
		return null;
	}
	
	@Override
	public Object visit(NotUnario e, Object param){
		e.getExpresion().accept(this, param);
		cg.not();
		return null;
	}
	
	@Override
	public Object visit(InvocacionFuncion e, Object param){
		cg.generarLinea(e.getFila());
		cg.generateComentario(e.toString());
		int i = 0;
		for (Expresion exp : e.getParams()) {
			exp.accept(this, param);
			cg.convertTo(exp.getTipo(), ((TipoFuncion)e.getDefinicion().getTipo()).getParam(i).getTipo());
			i++;
		}
		cg.call(e.getVariable().getNombre());
		return null;
	}
}
