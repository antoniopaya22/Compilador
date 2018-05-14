package generaciondecodigo;

import ast.definicion.Campo;
import ast.definicion.DefVariable;
import ast.expresion.AccesoArray;
import ast.tipo.TipoStruct;
import ast.expresion.Variable;
import ast.expresion.*;
import ast.tipo.*;

public class AddressCGVisitor extends AbstractCGVisitor {

	private CodeGenerator cg;
	private ValueCGVisitor vv;

	public AddressCGVisitor(CodeGenerator cg) {
		this.cg = cg;
	}

	public void setVv(ValueCGVisitor vv) {
		this.vv = vv;
	}

	@Override
	public Object visit(Variable var, Object param){
		if(var.getDefinicion() instanceof DefVariable){
			DefVariable dv =(DefVariable) var.getDefinicion();
			cg.pusha(dv); //Aqui esta todo encapsulado tanto offset ==0 y offset !=1
		}
		return null;
	}
	
	@Override
	public Object visit(AccesoCampoStruct acs, Object param){
		acs.getNombre().accept(this, param);
		cg.push("i", ((Campo)((TipoStruct)acs.getNombre().getTipo()).getCampo(acs.getCampo())).getOffset());
		cg.add("i");
		return null;
	}
	
	@Override
	public Object visit(AccesoArray aa, Object param){
		aa.getNombre().accept(this, param);
		aa.getIndex().accept(vv, param);
		cg.convertTo(aa.getIndex().getTipo(), Entero.getInstancia());
		cg.push("i", aa.getTipo().getNumeroBytes());
		cg.mult("i");
		cg.add("i");
		
		return null;
		
	}
}
