/**
 * 
 */
package ast.expresion;

import visitor.Visitor;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class Aritmetica extends AbstractExpresion {

	private Expresion op1, op2;
	private String operador;
	
	public Aritmetica(int i, int j, Object op1, Object operador, Object op2) {
		super(i, j);
		this.op1 = (Expresion) op1;
		this.op2 = (Expresion) op2;
		this.operador = (String) operador;
	}

	public Aritmetica(int i, int j, Expresion op1, String operador, Expresion op2) {
		super(i, j);
		this.op1 = op1;
		this.op2 = op2;
		this.operador = operador;
	}

	public Expresion getOp1() {
		return op1;
	}

	public void setOp1(Expresion op1) {
		this.op1 = op1;
	}

	public Expresion getOp2() {
		return op2;
	}

	public void setOp2(Expresion op2) {
		this.op2 = op2;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	@Override
	public String toString() {
		return ""+op1+" "+operador+" "+op2;
	}
	
	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}

}
