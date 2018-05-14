package generaciondecodigo;

import java.io.FileNotFoundException;

import ast.Programa;
import ast.definicion.DefFuncion;
import ast.definicion.DefVariable;
import ast.definicion.Definicion;
import ast.expresion.Expresion;
import ast.sentencia.Asignacion;
import ast.sentencia.DoWhile;
import ast.sentencia.Escritura;
import ast.sentencia.For;
import ast.sentencia.If;
import ast.sentencia.Lectura;
import ast.sentencia.LlamadaFuncion;
import ast.sentencia.Return;
import ast.sentencia.Sentencia;
import ast.sentencia.While;
import ast.tipo.Entero;
import ast.tipo.TipoFuncion;
import ast.tipo.Void;

public class ExecuteCGVisitor extends AbstractCGVisitor {

	private CodeGenerator cg;
	private AddressCGVisitor av;
	private ValueCGVisitor vv;

	public ExecuteCGVisitor(String inputFile,String outputFile) throws FileNotFoundException {
		cg = new CodeGenerator(inputFile,outputFile);
		av = new AddressCGVisitor(cg);
		vv = new ValueCGVisitor(cg);
		this.av.setVv(vv);
		this.vv.setAv(av);
	}

	@Override
	public Object visit(Programa e, Object param) {
		// Comentar las variables globales
		for (Definicion d : e.getDefiniciones()) {
			if (d instanceof DefVariable)
				d.accept(this, param);
		}
		cg.callMain();
		for (Definicion d : e.getDefiniciones()) {
			if (d instanceof DefFuncion)
				d.accept(this, param);
		}

		return null;
	}

	// =========SENTENCIAS========

	@Override
	public Object visit(Asignacion e, Object param) {
		cg.generarLinea(e.getFila());
		cg.generateComentario(""+e.getExp1()+" = "+e.getExp2());
		e.getExp1().accept(av, param);
		e.getExp2().accept(vv, param);
		cg.convertTo(e.getExp2().getTipo(), e.getExp1().getTipo());
		cg.store(e.getExp1().getTipo().sufijo());
		return null;
	}

	@Override
	public Object visit(Escritura e, Object param) {
		cg.generarLinea(e.getFila());
		int count = 0;
		for (Expresion exp : e.getExpresion()) {
			cg.generateComentario("Write part " + count);
			exp.accept(vv, param);
			cg.out(exp.getTipo().sufijo());
			count++;
		}
		return null;
	}

	@Override
	public Object visit(Lectura e, Object param) {
		cg.generarLinea(e.getFila());
		int count = 0;
		for (Expresion exp : e.getExpresion()) {
			cg.generateComentario("Read part " + count);
			exp.accept(av, param);
			cg.in(exp.getTipo().sufijo());
			cg.store(exp.getTipo().sufijo());
			count++;
		}
		return null;
	}

	@Override
	public Object visit(Return e, Object param) {
		cg.generarLinea(e.getFila());
		cg.generateComentario("Return");
		DefFuncion df = (DefFuncion) param;
		e.getExpresion().accept(vv, param);
		cg.convertTo(e.getExpresion().getTipo(), ((TipoFuncion) df.getTipo()).getTipoRetorno());
		cg.ret(df.getTipo().getTipoRetorno().getNumeroBytes(), df.totalLocalVariableSize, df.parametersSize);
		return null;
	}
	
	@Override
	public Object visit(While e, Object param) {
		cg.generarLinea(e.getFila());
		cg.generateComentario(e.toString());
		int label = cg.getLabels(2);
		cg.id("label_"+label+"");
		e.getCondicion().accept(vv, param);
		cg.convertTo(e.getCondicion().getTipo(), Entero.getInstancia());
		cg.jz("label_"+(label+1)+"");
		for (Sentencia s : e.getCuerpo()) {
			s.accept(this, param);
		}
		cg.jmp("label_"+label+"");
		cg.id("label_"+(label+1)+"");
		return null;
	}
	
	@Override
	public Object visit(DoWhile e, Object param) {
		cg.generarLinea(e.getFila());
		cg.generateComentario(e.toString());
		
		int label = cg.getLabels(2);
		cg.id("label_"+label+"");
		for (Sentencia s : e.getCuerpo()) {
			s.accept(this, param);
		}
		e.getCondicion().accept(vv, param);
		cg.convertTo(e.getCondicion().getTipo(), Entero.getInstancia());
		cg.jz("label_"+(label+1)+"");
		cg.jmp("label_"+label+"");
		cg.id("label_"+(label+1)+"");
		return null;
	}
	
	@Override
	public Object visit(For e, Object param) {
		cg.generarLinea(e.getFila());
		cg.generateComentario(e.toString());
		int label = cg.getLabels(2);
		e.getAsig1().accept(this, param);
		cg.id("label_"+label+"");
		e.getCondicion().accept(vv, param);
		cg.convertTo(e.getCondicion().getTipo(), Entero.getInstancia());
		cg.jz("label_"+(label+1)+"");
		for (Sentencia s : e.getCuerpo()) {
			s.accept(this, param);
		}
		e.getAsig2().accept(this, param);
		cg.jmp("label_"+label+"");
		cg.id("label_"+(label+1)+"");
		return null;
	}
	
	@Override
	public Object visit(If e, Object param) {
		cg.generarLinea(e.getFila());
		cg.generateComentario(e.toString());
		int label = cg.getLabels(2);
		e.getCondicion().accept(vv, param);
		cg.convertTo(e.getCondicion().getTipo(), Entero.getInstancia());
		cg.jz("label_"+label+"");
		for (Sentencia s : e.getCuerpo_if()) {
			s.accept(this, param);
		}
		cg.jmp("label_"+(label+1)+"");
		cg.id("label_"+label+"");
		for (Sentencia s : e.getCuerpo_else()) {
			s.accept(this, param);
		}
		cg.id("label_"+(label+1)+"");
		return null;
	}
	
	@Override
	public Object visit(LlamadaFuncion e, Object param){
		cg.generarLinea(e.getFila());
		cg.generateComentario(e.toString());
		int i = 0;
		for (Expresion exp : e.getParams()) {
			exp.accept(vv, param);
			cg.convertTo(exp.getTipo(), ((TipoFuncion)e.getDefinicion().getTipo()).getParam(i).getTipo());
			i++;
		}
		cg.call(e.getVariable().getNombre());
		if(!(((TipoFuncion)e.getDefinicion().getTipo()).getTipoRetorno() instanceof Void)) {
			cg.pop(((TipoFuncion)e.getDefinicion().getTipo()).getTipoRetorno().sufijo());
		}
		return null;
	}

	// ======DEFINICIONES==========

	private void generarInfoVariable(DefVariable dv) {
		StringBuilder sb = new StringBuilder();
		sb.append(dv.getTipo());
		sb.append(" " + dv.getId());
		sb.append(" (offset " + dv.getOffset() + ")");
		cg.generateComentario(sb.toString());
	}

	@Override
	public Object visit(DefVariable dv, Object o) {
		generarInfoVariable(dv);
		return null;
	}

	@Override
	public Object visit(DefFuncion df, Object o) {
		cg.generarLinea(df.getFila());
		cg.id(df.getId());
		cg.generateComentario("Parametros");
		for (DefVariable dv : df.getTipo().getParams()) {
			dv.accept(this, o);
		}
		cg.generateComentario("Variables locales");
		for (DefVariable st : df.getDefiniciones()) {
			st.accept(this, o);
		}

		cg.enter(df.totalLocalVariableSize);

		for (Sentencia st : df.getCuerpo()) {
			if (!(st instanceof DefVariable)) {
				st.accept(this, df);
			}
		}

		if (df.getTipo().getTipoRetorno() instanceof Void) {
			cg.ret(0, df.totalLocalVariableSize, df.parametersSize);
		}

		return null;
	}
}
