/**
 * 
 */
package semantico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ast.definicion.DefVariable;
import ast.definicion.Definicion;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class TablaSimbolos {

	private int ambito;
	private List<Map<String, Definicion>> tabla;
	
	
	public TablaSimbolos() {
		ambito = 0;
		tabla = new ArrayList<>();
		tabla.add(new HashMap<>());
	}
	
	public void set() {
		tabla.add(new HashMap<>());
		ambito++;
	}

	public void reset() {
		tabla.remove(ambito);
		ambito--;
	}

	public boolean insertar(Definicion definicion) {
		if (buscarAmbitoActual(definicion.getId()) != null) {
			return false;
		} else {
			if(definicion instanceof DefVariable)((DefVariable)definicion).setAmbito(ambito);
			tabla.get(ambito).put(definicion.getId(), definicion);
			return true;
		}
	}

	public Definicion buscar(String id) {
		for (int i = ambito; i >= 0; i--) {
			Definicion def = tabla.get(i).get(id);
			if (def != null) {
				return def;
			}
		}
		return null;
	}

	public Definicion buscarAmbitoActual(String id) {
		return tabla.get(ambito).get(id);
	}
	
}
