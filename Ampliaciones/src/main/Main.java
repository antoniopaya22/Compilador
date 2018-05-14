package main;
import java.io.FileReader;
import java.io.IOException;

import generaciondecodigo.ExecuteCGVisitor;
import generaciondecodigo.OffsetVisitor;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
import lexico.Lexico;
import manejadorerrores.ME;
import semantico.ComprobacionTiposVisitor;
import semantico.IdVisitor;
import semantico.LValueVisitor;
import sintactico.Parser;

public class Main {
	public static void main(String args[]) throws IOException {
	    if (args.length<2) {
	        System.err.println("Necesito el archivo de entrada y de salida.");
	        return;
	    }
	        
		FileReader fr=null;
		try {
			fr=new FileReader(args[0]);
		} catch(IOException io) {
			System.err.println("El archivo "+args[0]+" no se ha podido abrir.");
			return;
		}
		
		// * Creamos léxico y sintáctico
		Lexico lexico = new Lexico(fr);
		Parser parser = new Parser(lexico);
		// * "Parseamos"
		parser.run();	
		if (comprobarErrores())
			return;
		
	
		// ==========ANALISIS====================
		//Identificacion
		parser.getAST().accept(new IdVisitor(), null);
		//LValue
		parser.getAST().accept(new LValueVisitor(), null);
		//Tipos
		parser.getAST().accept(new ComprobacionTiposVisitor(), null);
		
		// ==========SINTESIS====================
		if (!comprobarErrores()) {
			//Offset
			parser.getAST().accept(new OffsetVisitor(), null);
			//Generacion Codigo
			parser.getAST().accept(new ExecuteCGVisitor(args[0],args[1]), null);
			
			IntrospectorModel modelo=new IntrospectorModel("Programa",parser.getAST());
			new IntrospectorTree("Introspector", modelo);
		}
		
	}

	private static boolean comprobarErrores(){
		if (ME.getME().huboErrores()) {
			ME.getME().mostrarErrores(System.err);
			return true;
		} else {
			ME.getME().getErrores().clear();
			return false;
		}
	}
}