/**
 * 
 */
package manejadorerrores;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import ast.tipo.TipoError;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class ME {

	private static ME instance = null;
	private List<TipoError> errors;

	private ME() {
		super();
		errors = new ArrayList<TipoError>();
	}
	
	public static ME getME() {
		if (instance == null) {
			instance = new ME();
		}
		return instance;
	}
	
	public boolean huboErrores() {
		return !errors.isEmpty();
	}

	public void addError(TipoError eT) {
		if (eT != null) {
			errors.add(eT);
		}
	}

	public void mostrarErrores(PrintStream err) {
		for (TipoError each : errors) {
			err.println(each.getMensaje() + ", fila:" + each.getFila() + ", columna: " + each.getColumna());
		}
	}

	public List<TipoError> getErrores() {
		return errors;
	}

}
