%{
// * sentencias de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage
import lexico.Lexico;
import ast.*;
import ast.tipo.Void;
import ast.definicion.*;
import ast.expresion.*;
import ast.sentencia.*;
import ast.tipo.*;
import java.util.*;

%}

// * Declaraciones Yacc

//Tokens
%token CTE_ENTERA CTE_CHAR CTE_REAL ID NOT_EQ AND OR POW
%token EQ L_EQ G_EQ READ WRITE WHILE IF ELSE INT FLOAT CHAR
%token VAR STRUCT RETURN FUNC MAIN END
%token AND_AS OR_AS

// Preferencia
%right '='
%right AND_AS OR_AS
%left OR AND
%left EQ NOT_EQ
%left L_EQ G_EQ '>' '<'
%left '+' '-'
%left '*' '/' '%'
%left '!'
%nonassoc MENOSQUEELSE
%nonassoc ELSE
%right MENOS_UNARIO
%nonassoc '[' ']'
%nonassoc '{' '}'
%left '.'
%nonassoc '(' ')'

%%
// * Gramática y acciones Yacc

programa: lista_definiciones main	{ 
		this.ast = new Programa(0, 0, $1);
		List<Definicion> defs = ((Programa)this.ast).getDefiniciones();
		defs.add((Definicion)$2);
		((Programa)this.ast).setDefiniciones(defs);
	}
	;	
	  
//===================================TIPOS================================================

llaves_array: '[' CTE_ENTERA ']'				{ $$ = new ArrayList<Integer>(); ((List<Integer>)$$).add((Integer)$2); }
			| '[' CTE_ENTERA ']' llaves_array	{ $$ = $4; ((List<Integer>)$$).add((Integer)$2); }
			;
		  
array: llaves_array tipo					{ $$ = addArray((List<Integer>)$1,(Tipo)$2,lexico.getLinea(), lexico.getColumna()); }
	 ;

struct:	STRUCT '{' lista_campos '}'			{ $$ = new TipoStruct(lexico.getLinea(), lexico.getColumna(), $3); }
	  ;

campo: lista_id tipo ';'					{ $$ = addDefCampo((Tipo)$2, (List<String>)$1, lexico.getLinea()); }
	  ;

lista_campos: lista_campos campo			{
											  List<Campo> list = (List<Campo>)$1;
											  HashMap<String,Campo> campos = new HashMap<>();
											  for(Campo c : list){
											  	campos.put(c.getId(),c);
											  }
											  for(Campo c : (List<Campo>)$2){
											  	if(campos.containsKey(c.getId())){
											  		new TipoError(c.getFila(), c.getColumna(), 
											  			"Ya existe el campo "+c.getId()+" en ese Struct");
											  	}
											  	else campos.put(c.getId(),c);
											  }
											  list.addAll((List<Campo>)$2); 
											  $$ = list;
											}
			| campo							{ $$ = $1; }
			;
		  
tipo: INT		{ $$ = Entero.getInstancia(); }
	| FLOAT		{ $$ = Real.getInstancia(); }
	| CHAR		{ $$ = Char.getInstancia(); }
	| array		{ $$ = $1; }
	| struct	{ $$ = $1; }
	;
	
tipo_simple: INT		{ $$ = Entero.getInstancia(); }
		   | FLOAT		{ $$ = Real.getInstancia(); }
	       | CHAR		{ $$ = Char.getInstancia(); }
		  
//===================================DEFINICIONES================================================

lista_definiciones: lista_definiciones definicion			{
																 List<Definicion> list = (List<Definicion>)$1;
																 list.addAll((List<Definicion>)$2); 
																 $$ = list;
															}
				  | /*Vacio*/								{ $$ = new ArrayList<Definicion>();}
				  ;

lista_def_variables: lista_def_variables def_variable_completa {
																 List<Definicion> list = (List<Definicion>)$1;
																 list.addAll((List<Definicion>)$2); 
																 $$ = list;
																}
							| /*Vacio*/							{ $$ = new ArrayList<Definicion>();}
					
				  
definicion: def_variable_completa							{ $$ = $1;}
		  | def_funcion										{ $$ = $1;}
		  ;
		  
def_variable_completa: VAR lista_id tipo ';'				{ $$ = addDefVar((Tipo)$3, (List<String>)$2, lexico.getLinea()); }
					 ;
		  
def_variable: ID tipo										{ $$ = new DefVariable(lexico.getLinea(), lexico.getColumna(), $1, $2); }	
			;
			
lista_id: lista_id ',' ID									{ ((List<String>)$$).add((String)$3); $$ = $1;  }
		| ID												{ $$ = new ArrayList<String>(); ((List<String>)$$).add((String)$1); }
		;
		  
lista_parametro_tipo_funcion: lista_parametro_tipo			{ $$ = $1; }
			  				| /*Vacio*/						{ $$ = new ArrayList<DefVariable>(); }
			  				;

lista_parametro_tipo: lista_parametro_tipo ',' def_variable		{ $$ = $1; ((List<DefVariable>)$$).add((DefVariable)$3); }  
		  			| def_variable								{ $$ = new ArrayList<DefVariable>(); ((List<DefVariable>)$$).add((DefVariable)$1); }  
		  			;

tipo_funcion: '(' lista_parametro_tipo_funcion ')' tipo_simple	{ $$ = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), $4, $2); }
			| '(' lista_parametro_tipo_funcion ')'				{ $$ = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), Void.getInstancia(), $2); }
			;

main: FUNC main_id '(' ')' '{' lista_def_variables lista_sentencias '}'			{ $$ = new DefFuncion(((Variable)$2).getFila(), ((Variable)$2).getColumna(), new TipoFuncion(lexico.getLinea(), lexico.getColumna(), Void.getInstancia(), new ArrayList<DefVariable>()),$2, $6, $7); }
	;
	
main_id:  MAIN										{ $$ = new Variable(lexico.getLinea(), lexico.getColumna(), "main"); }
	   ;
def_funcion: FUNC ID tipo_funcion '{' lista_def_variables lista_sentencias '}'  { 
																					List<Definicion> lista = new ArrayList<Definicion>();
																					lista.add(new DefFuncion(((TipoFuncion)$3).getFila(), ((TipoFuncion)$3).getColumna(), $3, $2, $5, $6));
																					$$ = lista; 
																				 }
	       ;

//===================================SENTENCIAS================================================

lista_sentencias: lista_sentencias sentencia				{ $$ = $1; ((List<Sentencia>)$$).add((Sentencia)$2); }
		        | /*Vacio*/									{ $$ = new ArrayList<Sentencia>(); }
			 	;

sentencia: RETURN expresion ';' 							{ $$ = new Return(lexico.getLinea(), lexico.getColumna(), $2); }
		 | READ '(' lista_expresiones ')' ';'				{ $$ = new Lectura(lexico.getLinea(), lexico.getColumna(), $3); }
		 | WRITE '(' lista_expresiones ')' ';'				{ $$ = new Escritura(lexico.getLinea(), lexico.getColumna(), $3); }
		 | if_else											{ $$ = $1;}
		 | while											{ $$ = $1;}
		 | expresion '=' expresion ';'						{ $$ = new Asignacion(lexico.getLinea(), lexico.getColumna(), $1, $3); }
		 | expresion AND_AS expresion ';'					{ 
		 													  
		 													  $$ = new Asignacion(lexico.getLinea(), lexico.getColumna(), $1,
												   				new AsignacionLogica(lexico.getLinea(), lexico.getColumna(), $1, "&&", $3)); 
		 													}
		 | expresion OR_AS expresion ';'					{ 
		 													  
		 													  $$ = new Asignacion(lexico.getLinea(), lexico.getColumna(), $1,
												   				new AsignacionLogica(lexico.getLinea(), lexico.getColumna(), $1, "||", $3)); 
		 													}
		 | ID '(' lista_expresiones_llamada_fun ')' ';'		{ $$ = new LlamadaFuncion(lexico.getLinea(), lexico.getColumna(), $1, $3); }
		 ;
		   

if_else: IF expresion '{' lista_sentencias '}' ELSE '{' lista_sentencias '}'	 		{ $$ = new If(lexico.getLinea(), lexico.getColumna(), $2, $4, $8); }
	   | IF expresion '{' lista_sentencias '}'  %prec MENOSQUEELSE						{ $$ = new If(lexico.getLinea(), lexico.getColumna(), $2, $4, new ArrayList<Sentencia>()); }
	   | IF '(' expresion ')' '{' lista_sentencias '}' ELSE '{' lista_sentencias '}'	{ $$ = new If(lexico.getLinea(), lexico.getColumna(), $3, $6, $10); }
	   ;

while: WHILE '(' expresion ')' '{' lista_sentencias '}'  	{ $$ = new While(lexico.getLinea(), lexico.getColumna(), $3, $6); }
	 | WHILE expresion '{' lista_sentencias '}'				{ $$ = new While(lexico.getLinea(), lexico.getColumna(), $2, $4); }
	 ;


//=======================================EXPRESIONES==============================================

lista_expresiones_llamada_fun: lista_expresiones					{ $$ = $1; }
							 | /*Vacio*/ 							{ $$ = new ArrayList<Expresion>(); }
							 ;

lista_expresiones: lista_expresiones ',' expresion      			{ $$ = $1; ((List<Expresion>)$$).add((Expresion)$3); }                
			     | expresion            							{ $$ = new ArrayList<Expresion>(); ((List<Expresion>)$$).add((Expresion)$1); }                         
			     ;      

expresion: expresion '+' expresion                  { $$ = new Aritmetica(lexico.getLinea(), lexico.getColumna(), $1, "+", $3); }
		 | expresion '*' expresion                  { $$ = new Aritmetica(lexico.getLinea(), lexico.getColumna(), $1, "*", $3); }
		 | expresion '/' expresion                  { $$ = new Aritmetica(lexico.getLinea(), lexico.getColumna(), $1, "/", $3); }
		 | expresion '-' expresion                  { $$ = new Aritmetica(lexico.getLinea(), lexico.getColumna(), $1, "-", $3); }         
		 | expresion '%' expresion                  { $$ = new Aritmetica(lexico.getLinea(), lexico.getColumna(), $1, "%", $3); }
		 | expresion EQ expresion                   { $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(), $1, "==", $3); }
		 | expresion NOT_EQ expresion               { $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(), $1, "!=", $3); }
		 | expresion '>' expresion					{ $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(), $1, ">", $3); }
		 | expresion '<' expresion					{ $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(), $1, "<", $3); }
		 | expresion L_EQ expresion					{ $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(), $1, "<=", $3); }
		 | expresion G_EQ expresion					{ $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(), $1, ">=", $3); }
		 | expresion AND expresion					{ $$ = new Logica(lexico.getLinea(), lexico.getColumna(), $1, "&&", $3); }
		 | expresion OR expresion					{ $$ = new Logica(lexico.getLinea(), lexico.getColumna(), $1, "||", $3); }
		 | expresion AND_AS expresion				{ $$ = new AsignacionLogica(lexico.getLinea(), lexico.getColumna(), $1, "&&", $3); }
		 | expresion OR_AS expresion				{ $$ = new AsignacionLogica(lexico.getLinea(), lexico.getColumna(), $1, "||", $3); }
		 | ID '(' lista_expresiones_llamada_fun ')' { $$ = new InvocacionFuncion(lexico.getLinea(), lexico.getColumna(), $1, $3); }
		 | tipo '(' expresion ')'					{ $$ = new Cast(lexico.getLinea(), lexico.getColumna(), $1, $3); }
		 | expresion '[' expresion ']'				{ $$ = new AccesoArray(lexico.getLinea(), lexico.getColumna(), $1, $3); }
		 | expresion '.' ID							{ $$ = new AccesoCampoStruct(lexico.getLinea(), lexico.getColumna(), $1, $3); }
		 | expresion '!' expresion					{ $$ = new Logica(lexico.getLinea(), lexico.getColumna(), $1, "!", $3); }
		 | '-' expresion		%prec MENOS_UNARIO	{ $$ = new MenosUnario(lexico.getLinea(), lexico.getColumna(), $2); }
		 | '(' expresion ')'						{ $$ = $2; }
		 | '!' expresion							{ $$ = new NotUnario(lexico.getLinea(), lexico.getColumna(), $2); }
		 | ID										{ $$ = new Variable(lexico.getLinea(), lexico.getColumna(), (String)$1); }
		 | CTE_CHAR									{ $$ = new LiteralChar(lexico.getLinea(), lexico.getColumna(), $1); }
		 | CTE_REAL									{ $$ = new LiteralReal(lexico.getLinea(), lexico.getColumna(), $1); }
		 | CTE_ENTERA		  						{ $$ = new LiteralEntero(lexico.getLinea(), lexico.getColumna(), $1); }
		 ;
%%

// * Código Java
// * Se crea una clase "Parser", lo que aquí ubiquemos será:
//	- Atributos, si son variables
//	- Métodos, si son funciones
//   de la clase "Parser"

// * Estamos obligados a implementar:
//	int yylex()
//	void yyerror(String)

// * Referencia al analizador léxico
private Lexico lexico;

private HashMap<String,DefVariable> variables;

// * Llamada al analizador léxico
private int yylex () {
    int token=0;
    try { 
		token=lexico.yylex(); 	
		this.yylval = lexico.getYylval();
    } catch(Throwable e) {
	    System.err.println ("Error Léxico en línea " + lexico.getLinea()+
		" y columna "+lexico.getColumna()+":\n\t"+e); 
    }
    return token;
}

// * Manejo de Errores Sintácticos
public void yyerror (String error) {
    new TipoError(lexico.getLinea(), lexico.getColumna(),
				"Syntax error -> " + yylex() + " (" + lexico.yytext() + ")");
}

// * Constructor del Sintáctico
public Parser(Lexico lexico) {
	this.lexico = lexico;
	variables = new HashMap<>();
}

private List<DefVariable> addDefVar(Tipo tipo, List<String> ids, int line) {
	List<DefVariable> defs = new ArrayList<>();
	for(String id : ids) {
		defs.add(new DefVariable(line, lexico.getColumna(), id, tipo));
	}
	return defs;
}

private List<Campo> addDefCampo(Tipo tipo, List<String> ids, int line) {
	List<Campo> camps = new ArrayList<>();
	for(String id : ids) {
		camps.add(new Campo(line, lexico.getColumna(), id, tipo));
	}
	return camps;
}

private TipoArray addArray(List<Integer> dimension,Tipo tipo,int line, int col){
	List<TipoArray> arrays = new ArrayList<TipoArray>();
	for(int i = 0; i< dimension.size(); i++){
		arrays.add(new TipoArray(line,col,tipo,dimension.get(i)));
	}
	for(int i = 0; i< dimension.size()-1; i++){
		arrays.get(i).setTipo(arrays.get(i+1));
	}
	return arrays.get(0);
}

private NodoAST ast;

public NodoAST getAST() {
	return this.ast;
}
