// ************  Código a incluir ********************

package lexico;
import sintactico.Parser;

%%
// ************  Opciones ********************
// % debug // * Opción para depurar
%byaccj
%class Lexico
%public
%unicode
%line
%column

%{
// ************  Atributos y métodos ********************
// * Para acceder al número de línea (yyline es package)
public int getLinea() { 
	// * Flex empieza en cero
	return yyline+1;
}

// * Para acceder al número de columna (yycolumn es package)
public int getColumna() { 
	// * Flex empieza en cero
	return yycolumn+1;
}

// * Valor semantico del token
private Object yylval;
public Object getYylval() {
	return this.yylval;
}

%}

// ************  Patrones (macros) ********************

Entero =[0-9][0-9]*
ASCII =0|[1-9][0-9]?|1[0-9][0-9]|2[0-4][0-9]|25[0-6]
Letra =[a-zA-ZáéíóúÁÉÍÓÚñÑ]
Char =\'(\\{ASCII}|.|\\n|\\t)\'
Identificador ={Letra} ({Letra}*{Entero}*_*)*
ConstanteReal ={Entero}+ ("."{Entero}*)? ([eE] ["+""-"]? ({Entero}+))?
ComentarioSimple ="//" . *
ComentarioVariasLineas ="/*" ~ "*/"
Espacios =(\t|\n|" "|\r)
Operador =[+\-*%/\[\]\.<>=!\^\(\)]
Delimitadores = [{};,]


%%
// ************  Acciones ********************

// ===== Comentarios y espacios =====
{ComentarioSimple}			{   }
{ComentarioVariasLineas}	{   }

// ===== Operadores dobles =====
"&&="				{ this.yylval = yytext();	return Parser.AND_AS;}
"||="				{ this.yylval = yytext();	return Parser.OR_AS;}
"!="				{ this.yylval = yytext();	return Parser.NOT_EQ;}
"&&"				{ this.yylval = yytext();	return Parser.AND;}
"||"				{ this.yylval = yytext(); 	return Parser.OR;}
"**"				{ this.yylval = yytext();	return Parser.POW;}
"=="				{ this.yylval = yytext();	return Parser.EQ;}
"<="				{ this.yylval = yytext();	return Parser.L_EQ;}
">="				{ this.yylval = yytext();	return Parser.G_EQ;}

// ===== Palabras reservadas =====
read				{ this.yylval = yytext();	return Parser.READ;}
write				{ this.yylval = yytext();	return Parser.WRITE;}
while				{ this.yylval = yytext();	return Parser.WHILE;}
if					{ this.yylval = yytext();	return Parser.IF;}
else				{ this.yylval = yytext();	return Parser.ELSE;}
int					{ this.yylval = yytext();	return Parser.INT;}	
float32				{ this.yylval = yytext();	return Parser.FLOAT;}
char				{ this.yylval = yytext();	return Parser.CHAR;}
var					{ this.yylval = yytext();	return Parser.VAR;}
struct				{ this.yylval = yytext();	return Parser.STRUCT;}
return				{ this.yylval = yytext();	return Parser.RETURN;}
func				{ this.yylval = yytext();	return Parser.FUNC;}
main 				{ this.yylval = yytext(); 	return Parser.MAIN;}

// ===== Constantes, Id's, etc ====
'\\n'				{ this.yylval = '\n';	return Parser.CTE_CHAR;}
'\\t'				{ this.yylval = '\t';	return Parser.CTE_CHAR;}
{Char}				{ this.yylval = yytext().charAt(1);	return Parser.CTE_CHAR;}
{Operador}			{ this.yylval = yytext();	return (int)yytext().charAt(0);}
{Delimitadores}		{ this.yylval = yytext();	return (int)yytext().charAt(0);}
{Identificador}		{ this.yylval = new String(yytext());	return Parser.ID;}
{Entero}			{ this.yylval = new Integer(yytext());	return Parser.CTE_ENTERA;}
{ConstanteReal}		{ this.yylval = new Float(yytext());	return Parser.CTE_REAL;}

// ===== Espacios y final =====
{Espacios}			{   }
.					{ System.err.println("Error in line: ["+getLinea()+"] column: ["+getColumna()+ "] --> "+yytext().toString() );}

