//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package sintactico;



//#line 2 "../../src/sintactico/sintactico.y"
/* * sentencias de código Java*/
/* * Se sitúan al comienzo del archivo generado*/
/* * El package lo añade yacc si utilizamos la opción -Jpackage*/
import lexico.Lexico;
import ast.*;
import ast.tipo.Void;
import ast.definicion.*;
import ast.expresion.*;
import ast.sentencia.*;
import ast.tipo.*;
import java.util.*;

//#line 30 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short CTE_ENTERA=257;
public final static short CTE_CHAR=258;
public final static short CTE_REAL=259;
public final static short ID=260;
public final static short NOT_EQ=261;
public final static short AND=262;
public final static short OR=263;
public final static short POW=264;
public final static short EQ=265;
public final static short L_EQ=266;
public final static short G_EQ=267;
public final static short READ=268;
public final static short WRITE=269;
public final static short WHILE=270;
public final static short IF=271;
public final static short ELSE=272;
public final static short INT=273;
public final static short FLOAT=274;
public final static short CHAR=275;
public final static short VAR=276;
public final static short STRUCT=277;
public final static short RETURN=278;
public final static short FUNC=279;
public final static short MAIN=280;
public final static short END=281;
public final static short MENOSQUEELSE=282;
public final static short MENOS_UNARIO=283;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    3,    3,    4,    6,    8,    7,    7,    5,    5,
    5,    5,    5,   10,   10,   10,    1,    1,   12,   12,
   11,   11,   13,   15,    9,    9,   16,   16,   17,   17,
   18,   18,    2,   19,   14,   20,   20,   21,   21,   21,
   21,   21,   21,   21,   24,   24,   24,   25,   25,   26,
   26,   23,   23,   22,   22,   22,   22,   22,   22,   22,
   22,   22,   22,   22,   22,   22,   22,   22,   22,   22,
   22,   22,   22,   22,   22,   22,   22,   22,
};
final static short yylen[] = {                            2,
    2,    3,    4,    2,    4,    3,    2,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    2,    0,    2,    0,
    1,    1,    4,    2,    3,    1,    1,    0,    3,    1,
    4,    3,    8,    1,    7,    2,    0,    3,    5,    5,
    1,    1,    4,    5,    9,    5,   11,    7,    5,    1,
    0,    3,    1,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    4,    4,    4,    3,
    3,    2,    3,    2,    1,    1,    1,    1,
};
final static short yydefred[] = {                        18,
    0,    0,    0,    0,    1,   17,   21,   22,   26,    0,
    0,   34,    0,    9,   10,   11,    0,    0,    0,    0,
   12,    0,   13,    0,    0,    0,    0,    0,   25,    4,
   23,    0,   30,    0,    0,   20,    0,    0,    8,    0,
    2,   24,    0,    0,    0,   20,    5,    7,    0,    3,
   14,   15,   16,   31,   29,   19,    0,    0,    6,   78,
   76,   77,    0,    0,    0,    0,    0,    0,    0,    0,
   35,    0,    0,   36,    0,   41,   42,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   33,    0,
    0,    0,    0,    0,    0,    0,   37,    0,   37,   38,
   73,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   70,    0,
    0,    0,    0,    0,   73,    0,   73,    0,   68,   43,
   69,    0,   44,   39,   40,   67,   37,   49,   37,    0,
    0,    0,    0,   48,    0,   37,    0,    0,   37,   45,
    0,   47,
};
final static short yydgoto[] = {                          1,
    2,    5,   20,   21,   73,   23,   38,   39,   40,   54,
    6,   45,   56,    8,   33,   34,   35,   25,   13,   57,
   74,   75,  111,   76,   77,  112,
};
final static short yysindex[] = {                         0,
    0, -263, -252, -254,    0,    0,    0,    0,    0,  -43,
  -20,    0,  -16,    0,    0,    0,  -93, -222, -215,  -88,
    0,   -9,    0, -205,  -66,   20, -252,  -31,    0,    0,
    0,  -88,    0,   23,   21,    0,  -56, -121,    0,  -43,
    0,    0, -221, -205, -208,    0,    0,    0,   13,    0,
    0,    0,    0,    0,    0,    0,  -33, -208,    0,    0,
    0,    0,   30,   34,   35,   83,  276,  369,  369,  369,
    0,  369,   36,    0,  545,    0,    0,  -11,  369,  369,
  369,   37,  369,  577,  369,  636,  671,  -44,  -44,  706,
  369,  369,  369,  369,  369,  369,  369,  369,  369,  369,
  369,  369,  369,  369,  369,  369,  369, -181,    0,  915,
   38,   43,  -13,   -2,  369,  728,    0,  758,    0,    0,
    0,  788,  966,  955,  955,  966,  -28,  -28,  814,  -28,
  -28,  -10,  -10,   -8,   -8,   -8,  -44,  821,    0,  369,
   26,   27,   28,   48,    0,   11,    0,   33,    0,    0,
    0,  915,    0,    0,    0,    0,    0,    0,    0, -182,
   55,   77,  -32,    0, -179,    0,  -29,  104,    0,    0,
  126,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   56,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   57,    0,    0,    0,    0,    0,
    0,    0,  -27,    0,  148,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  148,    0,    0,
    0,    0,  849,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   58,    0,
    0,  409,    0,    0,    0,    0,    0,  444,  479,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   -1,
   60,    0,    0,    0,   58,    0,    0,    0,    0,    0,
    0,    0,  522,  828, 1241,  863, 1165, 1187,    0, 1210,
 1245, 1130, 1152, 1000, 1060, 1095,  515,    0,    0,    0,
  883,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    5,    0,    0,    0,    0,    0,    0,    0,  170,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,   62,    0,    1,    0,    0,   66,  102,    0,
    0,   61,  106,    0,   68,    0,    0,    0,    0,  -48,
    0, 1447,  -21,    0,    0,    4,
};
final static int YYTABLESIZE=1587;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         70,
   19,  108,   18,   47,  106,   11,   72,    9,  105,   78,
   22,   69,    3,  103,  101,    4,  102,  108,  104,   24,
   30,   70,  106,   26,  106,   12,  105,  142,   72,   27,
  140,  103,   42,   69,   28,  108,  104,  108,  143,   53,
   49,  140,   53,   70,   29,   52,  107,   18,   52,   31,
   72,   51,   52,   53,   32,   69,   36,   18,  113,  114,
   37,   41,  107,   43,   44,   70,   46,    3,  146,   79,
  148,   59,   72,   80,   81,   91,  115,   69,  139,   18,
  107,  140,  107,  141,  153,  154,  155,   70,  156,  163,
  166,   71,  167,  169,   72,   32,   28,   27,   51,   69,
   50,   18,   50,   48,   10,    0,   58,    7,  161,   70,
  162,   55,    0,  109,    0,   70,   72,  168,  144,    0,
  171,   69,   83,   18,    0,    0,    0,   69,    0,    0,
    0,    0,    0,    0,    0,  158,   70,    0,    9,    0,
    0,    0,    0,   72,    0,   18,    0,    0,   69,    0,
    0,    0,    0,    0,    0,    0,    0,  160,   70,    0,
    0,    0,    0,    0,    0,   72,    0,   18,    0,    0,
   69,    0,    0,   18,    0,    0,    0,    0,    0,  164,
   37,    0,    0,    0,   14,   15,   16,   37,   17,    0,
    0,    0,   37,    0,   18,    0,    0,    0,    0,    0,
    0,  165,   46,    0,    0,    0,    0,    0,    0,   46,
    0,    0,    0,    0,   46,    0,   18,    0,    0,    0,
    0,    0,    0,   60,   61,   62,   63,    0,  170,   14,
   15,   16,    0,   17,   64,   65,   66,   67,   37,   14,
   15,   16,    0,   17,   68,   60,   61,   62,   63,    0,
  172,    0,    0,    0,    0,    0,   64,   65,   66,   67,
   46,   14,   15,   16,    0,   17,   68,   60,   61,   62,
   63,    0,   37,    0,    0,    0,    0,    0,   64,   65,
   66,   67,    0,   14,   15,   16,    0,   17,   68,   60,
   61,   62,   63,    0,   46,    0,    0,    0,    0,    0,
   64,   65,   66,   67,    0,   14,   15,   16,   70,   17,
   68,   60,   61,   62,   63,   85,    0,    0,    0,    0,
   69,    0,   64,   65,   66,   67,    0,   14,   15,   16,
    0,   17,   68,   60,   61,   62,   63,    0,    0,   60,
   61,   62,   82,    0,   64,   65,   66,   67,    0,   14,
   15,   16,    0,   17,   68,   14,   15,   16,    0,   17,
   60,   61,   62,   63,    0,    0,   18,    0,    0,    0,
    0,   64,   65,   66,   67,    0,   14,   15,   16,    0,
   17,   68,   60,   61,   62,   63,    0,    0,    0,    0,
    0,    0,    0,   64,   65,   66,   67,    0,   14,   15,
   16,   70,   17,   68,   37,   37,   37,   37,   72,    0,
    0,    0,    0,   69,    0,   37,   37,   37,   37,    0,
   37,   37,   37,    0,   37,   37,   46,   46,   46,   46,
    0,    0,    0,    0,    0,    0,    0,   46,   46,   46,
   46,   75,   46,   46,   46,   75,   46,   46,    0,   75,
   75,   75,   75,   75,   75,   75,    0,    0,    0,   18,
    0,    0,    0,    0,    0,    0,    0,   75,   75,   75,
   75,    0,    0,    0,    0,    0,   72,    0,    0,    0,
   72,    0,    0,    0,   72,   72,   72,   72,   72,    0,
   72,    0,    0,    0,    0,    0,    0,    0,    0,   75,
    0,   75,   72,   72,   72,   72,    0,    0,    0,    0,
    0,   74,    0,    0,    0,   74,    0,    0,    0,   74,
   74,   74,   74,   74,    0,   74,    0,    0,    0,    0,
    0,   75,   60,   61,   62,   82,   72,   74,   74,   74,
   74,    0,    0,    0,    0,    0,    0,   71,   14,   15,
   16,   71,   17,    0,    0,   71,   71,   71,   71,   71,
    0,   71,   60,    0,    0,   60,   72,    0,    0,    0,
    0,   74,    0,   71,   71,   71,   71,  106,    0,    0,
   60,  105,   60,    0,    0,    0,  103,  101,    0,  102,
  108,  104,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   74,    0,    0,  100,   98,   99,   71,    0,  106,
    0,    0,    0,  105,   60,    0,    0,    0,  103,  101,
    0,  102,  108,  104,    0,   60,   61,   62,   82,    0,
    0,    0,    0,    0,    0,  107,  100,   71,   99,    0,
    0,   14,   15,   16,   60,   17,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  107,  106,   75,
   75,   75,  105,   75,   75,   75,    0,  103,  101,    0,
  102,  108,  104,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  100,    0,   99,    0,  117,
    0,    0,    0,  106,   72,   72,   72,  105,   72,   72,
   72,    0,  103,  101,    0,  102,  108,  104,    0,    0,
    0,    0,    0,    0,    0,    0,  107,    0,    0,  120,
  100,    0,   99,    0,    0,    0,    0,    0,  106,   74,
   74,   74,  105,   74,   74,   74,  121,  103,  101,    0,
  102,  108,  104,    0,    0,    0,    0,    0,  119,    0,
  106,  107,    0,    0,  105,  100,    0,   99,  145,  103,
  101,    0,  102,  108,  104,   71,   71,   71,    0,   71,
   71,   71,   60,   60,   60,    0,   60,  100,    0,   99,
  106,    0,    0,    0,  105,    0,  107,    0,  147,  103,
  101,    0,  102,  108,  104,   92,   93,   94,    0,   95,
   96,   97,    0,    0,    0,    0,    0,  100,  107,   99,
  106,    0,    0,    0,  105,    0,    0,    0,  149,  103,
  101,    0,  102,  108,  104,    0,    0,   92,   93,   94,
    0,   95,   96,   97,    0,    0,  106,  100,  107,   99,
  105,    0,    0,  106,    0,  103,  101,  105,  102,  108,
  104,    0,  103,  101,    0,  102,  108,  104,   65,    0,
    0,   65,  150,  100,    0,   99,    0,    0,  107,    0,
  100,   75,   99,    0,    0,   75,   65,    0,   65,    0,
   75,   75,    0,   75,   75,   75,   92,   93,   94,    0,
   95,   96,   97,   59,  107,    0,   59,    0,   75,   75,
   75,  107,    0,  151,    0,   67,    0,    0,    0,   67,
   65,   59,    0,   59,   67,   67,    0,   67,   67,   67,
    0,   92,   93,   94,    0,   95,   96,   97,    0,   75,
    0,    0,   67,   67,   67,    0,    0,  106,    0,    0,
   65,  105,    0,    0,    0,   59,  103,  101,    0,  102,
  108,  104,    0,    0,    0,    0,   92,   93,   94,    0,
   95,   96,   97,   67,  100,    0,   99,    0,    0,    0,
    0,    0,    0,    0,    0,   59,    0,  106,   92,   93,
   94,  105,   95,   96,   97,    0,  103,  101,  106,  102,
  108,  104,  105,    0,    0,  107,    0,  103,  101,    0,
  102,  108,  104,    0,  100,    0,   99,    0,   92,   93,
   94,    0,   95,   96,   97,  100,    0,   99,    0,    0,
    0,    0,    0,    0,    0,    0,   55,    0,    0,    0,
   55,   55,   55,   55,   55,  107,   55,    0,   92,   93,
   94,    0,   95,   96,   97,    0,  107,    0,   55,   55,
   55,   55,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   92,   93,   94,    0,   95,   96,
   97,   92,   93,   94,    0,   95,   96,   97,    0,   65,
   65,    0,   55,    0,    0,    0,   56,    0,    0,    0,
   56,   56,   56,   56,   56,    0,   56,    0,    0,   75,
   75,   75,    0,   75,   75,   75,    0,    0,   56,   56,
   56,   56,   55,   59,   59,   59,    0,   59,    0,    0,
    0,   58,    0,    0,    0,   58,   58,   58,   58,   58,
    0,   58,    0,   67,   67,   67,    0,   67,   67,   67,
    0,    0,   56,   58,   58,   58,   58,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   54,    0,   54,   54,   54,   92,   93,   94,    0,   95,
   96,   97,   56,    0,    0,    0,    0,   58,   54,   54,
   54,   54,   57,    0,   57,   57,   57,    0,    0,    0,
    0,    0,    0,    0,    0,   63,    0,    0,   63,    0,
   57,   57,   57,   57,    0,   92,    0,   58,    0,   95,
   96,   97,   54,   63,   63,   63,   63,   64,    0,    0,
   64,   96,   97,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   57,   64,   64,   64,   64,    0,
   61,    0,   54,   61,    0,    0,    0,   63,    0,    0,
   55,   55,   55,    0,   55,   55,   55,    0,   61,   61,
   61,   61,    0,    0,   57,    0,    0,    0,    0,   64,
    0,   66,    0,    0,   66,   62,    0,   63,   62,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   66,
    0,   66,   61,   62,   62,   62,   62,    0,    0,   64,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   56,   56,   56,    0,   56,   56,   56,    0,    0,    0,
    0,    0,   61,   66,    0,    0,    0,   62,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   58,   58,   58,    0,   58,
   58,   58,    0,   66,    0,    0,    0,   62,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   54,   54,   54,    0,   54,   54,   54,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   57,   57,   57,    0,   57,   57,   57,    0,
    0,    0,    0,    0,    0,   63,   63,   63,    0,   63,
   63,   63,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   64,   64,   64,
    0,   64,   64,   64,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   61,   61,   61,    0,   61,   61,   61,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   66,   66,    0,   62,   62,   62,    0,   62,
   62,   62,   84,   86,   87,   88,   89,    0,   90,    0,
    0,    0,    0,    0,    0,  110,  110,  110,    0,  116,
    0,  118,    0,    0,    0,    0,    0,  122,  123,  124,
  125,  126,  127,  128,  129,  130,  131,  132,  133,  134,
  135,  136,  137,  138,    0,    0,    0,    0,    0,    0,
    0,  110,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  152,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   44,   46,   91,  125,   33,  260,   40,  260,   37,   58,
   10,   45,  276,   42,   43,  279,   45,   46,   47,   40,
   20,   33,   33,   40,   33,  280,   37,   41,   40,  123,
   44,   42,   32,   45,  257,   46,   47,   46,   41,   41,
   40,   44,   44,   33,  260,   41,   91,   91,   44,   59,
   40,  273,  274,  275,  260,   45,  123,   91,   80,   81,
   41,   93,   91,   41,   44,   33,  123,  276,  117,   40,
  119,   59,   40,   40,   40,   40,   40,   45,  260,   91,
   91,   44,   91,   41,   59,   59,   59,   33,   41,  272,
  123,  125,  272,  123,   40,  123,   41,   41,   41,   45,
   41,   91,   41,   38,    3,   -1,   46,    2,  157,   33,
  159,   44,   -1,  125,   -1,   33,   40,  166,  115,   -1,
  169,   45,   40,   91,   -1,   -1,   -1,   45,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  125,   33,   -1,  260,   -1,
   -1,   -1,   -1,   40,   -1,   91,   -1,   -1,   45,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  125,   33,   -1,
   -1,   -1,   -1,   -1,   -1,   40,   -1,   91,   -1,   -1,
   45,   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,  125,
   33,   -1,   -1,   -1,  273,  274,  275,   40,  277,   -1,
   -1,   -1,   45,   -1,   91,   -1,   -1,   -1,   -1,   -1,
   -1,  125,   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,
   -1,   -1,   -1,   -1,   45,   -1,   91,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,   -1,  125,  273,
  274,  275,   -1,  277,  268,  269,  270,  271,   91,  273,
  274,  275,   -1,  277,  278,  257,  258,  259,  260,   -1,
  125,   -1,   -1,   -1,   -1,   -1,  268,  269,  270,  271,
   91,  273,  274,  275,   -1,  277,  278,  257,  258,  259,
  260,   -1,  125,   -1,   -1,   -1,   -1,   -1,  268,  269,
  270,  271,   -1,  273,  274,  275,   -1,  277,  278,  257,
  258,  259,  260,   -1,  125,   -1,   -1,   -1,   -1,   -1,
  268,  269,  270,  271,   -1,  273,  274,  275,   33,  277,
  278,  257,  258,  259,  260,   40,   -1,   -1,   -1,   -1,
   45,   -1,  268,  269,  270,  271,   -1,  273,  274,  275,
   -1,  277,  278,  257,  258,  259,  260,   -1,   -1,  257,
  258,  259,  260,   -1,  268,  269,  270,  271,   -1,  273,
  274,  275,   -1,  277,  278,  273,  274,  275,   -1,  277,
  257,  258,  259,  260,   -1,   -1,   91,   -1,   -1,   -1,
   -1,  268,  269,  270,  271,   -1,  273,  274,  275,   -1,
  277,  278,  257,  258,  259,  260,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  268,  269,  270,  271,   -1,  273,  274,
  275,   33,  277,  278,  257,  258,  259,  260,   40,   -1,
   -1,   -1,   -1,   45,   -1,  268,  269,  270,  271,   -1,
  273,  274,  275,   -1,  277,  278,  257,  258,  259,  260,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  268,  269,  270,
  271,   33,  273,  274,  275,   37,  277,  278,   -1,   41,
   42,   43,   44,   45,   46,   47,   -1,   -1,   -1,   91,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,   60,   61,
   62,   -1,   -1,   -1,   -1,   -1,   33,   -1,   -1,   -1,
   37,   -1,   -1,   -1,   41,   42,   43,   44,   45,   -1,
   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,
   -1,   93,   59,   60,   61,   62,   -1,   -1,   -1,   -1,
   -1,   33,   -1,   -1,   -1,   37,   -1,   -1,   -1,   41,
   42,   43,   44,   45,   -1,   47,   -1,   -1,   -1,   -1,
   -1,  123,  257,  258,  259,  260,   93,   59,   60,   61,
   62,   -1,   -1,   -1,   -1,   -1,   -1,   33,  273,  274,
  275,   37,  277,   -1,   -1,   41,   42,   43,   44,   45,
   -1,   47,   41,   -1,   -1,   44,  123,   -1,   -1,   -1,
   -1,   93,   -1,   59,   60,   61,   62,   33,   -1,   -1,
   59,   37,   61,   -1,   -1,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  123,   -1,   -1,   60,   61,   62,   93,   -1,   33,
   -1,   -1,   -1,   37,   93,   -1,   -1,   -1,   42,   43,
   -1,   45,   46,   47,   -1,  257,  258,  259,  260,   -1,
   -1,   -1,   -1,   -1,   -1,   91,   60,  123,   62,   -1,
   -1,  273,  274,  275,  123,  277,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,   33,  261,
  262,  263,   37,  265,  266,  267,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   60,   -1,   62,   -1,  123,
   -1,   -1,   -1,   33,  261,  262,  263,   37,  265,  266,
  267,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,   -1,   59,
   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,   33,  261,
  262,  263,   37,  265,  266,  267,   41,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,  123,   -1,
   33,   91,   -1,   -1,   37,   60,   -1,   62,   41,   42,
   43,   -1,   45,   46,   47,  261,  262,  263,   -1,  265,
  266,  267,  261,  262,  263,   -1,  265,   60,   -1,   62,
   33,   -1,   -1,   -1,   37,   -1,   91,   -1,   41,   42,
   43,   -1,   45,   46,   47,  261,  262,  263,   -1,  265,
  266,  267,   -1,   -1,   -1,   -1,   -1,   60,   91,   62,
   33,   -1,   -1,   -1,   37,   -1,   -1,   -1,   41,   42,
   43,   -1,   45,   46,   47,   -1,   -1,  261,  262,  263,
   -1,  265,  266,  267,   -1,   -1,   33,   60,   91,   62,
   37,   -1,   -1,   33,   -1,   42,   43,   37,   45,   46,
   47,   -1,   42,   43,   -1,   45,   46,   47,   41,   -1,
   -1,   44,   59,   60,   -1,   62,   -1,   -1,   91,   -1,
   60,   33,   62,   -1,   -1,   37,   59,   -1,   61,   -1,
   42,   43,   -1,   45,   46,   47,  261,  262,  263,   -1,
  265,  266,  267,   41,   91,   -1,   44,   -1,   60,   61,
   62,   91,   -1,   93,   -1,   33,   -1,   -1,   -1,   37,
   93,   59,   -1,   61,   42,   43,   -1,   45,   46,   47,
   -1,  261,  262,  263,   -1,  265,  266,  267,   -1,   91,
   -1,   -1,   60,   61,   62,   -1,   -1,   33,   -1,   -1,
  123,   37,   -1,   -1,   -1,   93,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,   -1,  261,  262,  263,   -1,
  265,  266,  267,   91,   60,   -1,   62,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  123,   -1,   33,  261,  262,
  263,   37,  265,  266,  267,   -1,   42,   43,   33,   45,
   46,   47,   37,   -1,   -1,   91,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   60,   -1,   62,   -1,  261,  262,
  263,   -1,  265,  266,  267,   60,   -1,   62,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   37,   -1,   -1,   -1,
   41,   42,   43,   44,   45,   91,   47,   -1,  261,  262,
  263,   -1,  265,  266,  267,   -1,   91,   -1,   59,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  261,  262,  263,   -1,  265,  266,
  267,  261,  262,  263,   -1,  265,  266,  267,   -1,  262,
  263,   -1,   93,   -1,   -1,   -1,   37,   -1,   -1,   -1,
   41,   42,   43,   44,   45,   -1,   47,   -1,   -1,  261,
  262,  263,   -1,  265,  266,  267,   -1,   -1,   59,   60,
   61,   62,  123,  261,  262,  263,   -1,  265,   -1,   -1,
   -1,   37,   -1,   -1,   -1,   41,   42,   43,   44,   45,
   -1,   47,   -1,  261,  262,  263,   -1,  265,  266,  267,
   -1,   -1,   93,   59,   60,   61,   62,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   41,   -1,   43,   44,   45,  261,  262,  263,   -1,  265,
  266,  267,  123,   -1,   -1,   -1,   -1,   93,   59,   60,
   61,   62,   41,   -1,   43,   44,   45,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,   -1,
   59,   60,   61,   62,   -1,  261,   -1,  123,   -1,  265,
  266,  267,   93,   59,   60,   61,   62,   41,   -1,   -1,
   44,  266,  267,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   93,   59,   60,   61,   62,   -1,
   41,   -1,  123,   44,   -1,   -1,   -1,   93,   -1,   -1,
  261,  262,  263,   -1,  265,  266,  267,   -1,   59,   60,
   61,   62,   -1,   -1,  123,   -1,   -1,   -1,   -1,   93,
   -1,   41,   -1,   -1,   44,   41,   -1,  123,   44,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,
   -1,   61,   93,   59,   60,   61,   62,   -1,   -1,  123,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  261,  262,  263,   -1,  265,  266,  267,   -1,   -1,   -1,
   -1,   -1,  123,   93,   -1,   -1,   -1,   93,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  261,  262,  263,   -1,  265,
  266,  267,   -1,  123,   -1,   -1,   -1,  123,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  261,  262,  263,   -1,  265,  266,  267,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  261,  262,  263,   -1,  265,  266,  267,   -1,
   -1,   -1,   -1,   -1,   -1,  261,  262,  263,   -1,  265,
  266,  267,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  261,  262,  263,
   -1,  265,  266,  267,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  261,  262,  263,   -1,  265,  266,  267,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  262,  263,   -1,  261,  262,  263,   -1,  265,
  266,  267,   66,   67,   68,   69,   70,   -1,   72,   -1,
   -1,   -1,   -1,   -1,   -1,   79,   80,   81,   -1,   83,
   -1,   85,   -1,   -1,   -1,   -1,   -1,   91,   92,   93,
   94,   95,   96,   97,   98,   99,  100,  101,  102,  103,
  104,  105,  106,  107,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  115,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  140,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=283;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"CTE_ENTERA","CTE_CHAR","CTE_REAL",
"ID","NOT_EQ","AND","OR","POW","EQ","L_EQ","G_EQ","READ","WRITE","WHILE","IF",
"ELSE","INT","FLOAT","CHAR","VAR","STRUCT","RETURN","FUNC","MAIN","END",
"MENOSQUEELSE","MENOS_UNARIO",
};
final static String yyrule[] = {
"$accept : programa",
"programa : lista_definiciones main",
"llaves_array : '[' CTE_ENTERA ']'",
"llaves_array : '[' CTE_ENTERA ']' llaves_array",
"array : llaves_array tipo",
"struct : STRUCT '{' lista_campos '}'",
"campo : lista_id tipo ';'",
"lista_campos : lista_campos campo",
"lista_campos : campo",
"tipo : INT",
"tipo : FLOAT",
"tipo : CHAR",
"tipo : array",
"tipo : struct",
"tipo_simple : INT",
"tipo_simple : FLOAT",
"tipo_simple : CHAR",
"lista_definiciones : lista_definiciones definicion",
"lista_definiciones :",
"lista_def_variables : lista_def_variables def_variable_completa",
"lista_def_variables :",
"definicion : def_variable_completa",
"definicion : def_funcion",
"def_variable_completa : VAR lista_id tipo ';'",
"def_variable : ID tipo",
"lista_id : lista_id ',' ID",
"lista_id : ID",
"lista_parametro_tipo_funcion : lista_parametro_tipo",
"lista_parametro_tipo_funcion :",
"lista_parametro_tipo : lista_parametro_tipo ',' def_variable",
"lista_parametro_tipo : def_variable",
"tipo_funcion : '(' lista_parametro_tipo_funcion ')' tipo_simple",
"tipo_funcion : '(' lista_parametro_tipo_funcion ')'",
"main : FUNC main_id '(' ')' '{' lista_def_variables lista_sentencias '}'",
"main_id : MAIN",
"def_funcion : FUNC ID tipo_funcion '{' lista_def_variables lista_sentencias '}'",
"lista_sentencias : lista_sentencias sentencia",
"lista_sentencias :",
"sentencia : RETURN expresion ';'",
"sentencia : READ '(' lista_expresiones ')' ';'",
"sentencia : WRITE '(' lista_expresiones ')' ';'",
"sentencia : if_else",
"sentencia : while",
"sentencia : expresion '=' expresion ';'",
"sentencia : ID '(' lista_expresiones_llamada_fun ')' ';'",
"if_else : IF expresion '{' lista_sentencias '}' ELSE '{' lista_sentencias '}'",
"if_else : IF expresion '{' lista_sentencias '}'",
"if_else : IF '(' expresion ')' '{' lista_sentencias '}' ELSE '{' lista_sentencias '}'",
"while : WHILE '(' expresion ')' '{' lista_sentencias '}'",
"while : WHILE expresion '{' lista_sentencias '}'",
"lista_expresiones_llamada_fun : lista_expresiones",
"lista_expresiones_llamada_fun :",
"lista_expresiones : lista_expresiones ',' expresion",
"lista_expresiones : expresion",
"expresion : expresion '+' expresion",
"expresion : expresion '*' expresion",
"expresion : expresion '/' expresion",
"expresion : expresion '-' expresion",
"expresion : expresion '%' expresion",
"expresion : expresion EQ expresion",
"expresion : expresion NOT_EQ expresion",
"expresion : expresion '>' expresion",
"expresion : expresion '<' expresion",
"expresion : expresion L_EQ expresion",
"expresion : expresion G_EQ expresion",
"expresion : expresion AND expresion",
"expresion : expresion OR expresion",
"expresion : ID '(' lista_expresiones_llamada_fun ')'",
"expresion : tipo '(' expresion ')'",
"expresion : expresion '[' expresion ']'",
"expresion : expresion '.' ID",
"expresion : expresion '!' expresion",
"expresion : '-' expresion",
"expresion : '(' expresion ')'",
"expresion : '!' expresion",
"expresion : ID",
"expresion : CTE_CHAR",
"expresion : CTE_REAL",
"expresion : CTE_ENTERA",
};

//#line 214 "../../src/sintactico/sintactico.y"

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
//#line 728 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 42 "../../src/sintactico/sintactico.y"
{ 
		this.ast = new Programa(0, 0, val_peek(1));
		List<Definicion> defs = ((Programa)this.ast).getDefiniciones();
		defs.add((Definicion)val_peek(0));
		((Programa)this.ast).setDefiniciones(defs);
	}
break;
case 2:
//#line 52 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Integer>(); ((List<Integer>)yyval).add((Integer)val_peek(1)); }
break;
case 3:
//#line 53 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); ((List<Integer>)yyval).add((Integer)val_peek(2)); }
break;
case 4:
//#line 56 "../../src/sintactico/sintactico.y"
{ yyval = addArray((List<Integer>)val_peek(1),(Tipo)val_peek(0),lexico.getLinea(), lexico.getColumna()); }
break;
case 5:
//#line 59 "../../src/sintactico/sintactico.y"
{ yyval = new TipoStruct(lexico.getLinea(), lexico.getColumna(), val_peek(1)); }
break;
case 6:
//#line 62 "../../src/sintactico/sintactico.y"
{ yyval = addDefCampo((Tipo)val_peek(1), (List<String>)val_peek(2), lexico.getLinea()); }
break;
case 7:
//#line 65 "../../src/sintactico/sintactico.y"
{
											  List<Campo> list = (List<Campo>)val_peek(1);
											  HashMap<String,Campo> campos = new HashMap<>();
											  for(Campo c : list){
											  	campos.put(c.getId(),c);
											  }
											  for(Campo c : (List<Campo>)val_peek(0)){
											  	if(campos.containsKey(c.getId())){
											  		new TipoError(c.getFila(), c.getColumna(), 
											  			"Ya existe el campo "+c.getId()+" en ese Struct");
											  	}
											  	else campos.put(c.getId(),c);
											  }
											  list.addAll((List<Campo>)val_peek(0)); 
											  yyval = list;
											}
break;
case 8:
//#line 81 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 9:
//#line 84 "../../src/sintactico/sintactico.y"
{ yyval = Entero.getInstancia(); }
break;
case 10:
//#line 85 "../../src/sintactico/sintactico.y"
{ yyval = Real.getInstancia(); }
break;
case 11:
//#line 86 "../../src/sintactico/sintactico.y"
{ yyval = Char.getInstancia(); }
break;
case 12:
//#line 87 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 13:
//#line 88 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 14:
//#line 91 "../../src/sintactico/sintactico.y"
{ yyval = Entero.getInstancia(); }
break;
case 15:
//#line 92 "../../src/sintactico/sintactico.y"
{ yyval = Real.getInstancia(); }
break;
case 16:
//#line 93 "../../src/sintactico/sintactico.y"
{ yyval = Char.getInstancia(); }
break;
case 17:
//#line 97 "../../src/sintactico/sintactico.y"
{
																 List<Definicion> list = (List<Definicion>)val_peek(1);
																 list.addAll((List<Definicion>)val_peek(0)); 
																 yyval = list;
															}
break;
case 18:
//#line 102 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Definicion>();}
break;
case 19:
//#line 105 "../../src/sintactico/sintactico.y"
{
																 List<Definicion> list = (List<Definicion>)val_peek(1);
																 list.addAll((List<Definicion>)val_peek(0)); 
																 yyval = list;
																}
break;
case 20:
//#line 110 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Definicion>();}
break;
case 21:
//#line 113 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 22:
//#line 114 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 23:
//#line 117 "../../src/sintactico/sintactico.y"
{ yyval = addDefVar((Tipo)val_peek(1), (List<String>)val_peek(2), lexico.getLinea()); }
break;
case 24:
//#line 120 "../../src/sintactico/sintactico.y"
{ yyval = new DefVariable(lexico.getLinea(), lexico.getColumna(), val_peek(1), val_peek(0)); }
break;
case 25:
//#line 123 "../../src/sintactico/sintactico.y"
{ ((List<String>)yyval).add((String)val_peek(0)); yyval = val_peek(2);  }
break;
case 26:
//#line 124 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<String>(); ((List<String>)yyval).add((String)val_peek(0)); }
break;
case 27:
//#line 127 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 28:
//#line 128 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<DefVariable>(); }
break;
case 29:
//#line 131 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(2); ((List<DefVariable>)yyval).add((DefVariable)val_peek(0)); }
break;
case 30:
//#line 132 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<DefVariable>(); ((List<DefVariable>)yyval).add((DefVariable)val_peek(0)); }
break;
case 31:
//#line 135 "../../src/sintactico/sintactico.y"
{ yyval = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), val_peek(0), val_peek(2)); }
break;
case 32:
//#line 136 "../../src/sintactico/sintactico.y"
{ yyval = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), Void.getInstancia(), val_peek(1)); }
break;
case 33:
//#line 139 "../../src/sintactico/sintactico.y"
{ yyval = new DefFuncion(((Variable)val_peek(6)).getFila(), ((Variable)val_peek(6)).getColumna(), new TipoFuncion(lexico.getLinea(), lexico.getColumna(), Void.getInstancia(), new ArrayList<DefVariable>()), val_peek(6), val_peek(2), val_peek(1)); }
break;
case 34:
//#line 142 "../../src/sintactico/sintactico.y"
{ yyval = new Variable(lexico.getLinea(), lexico.getColumna(), "main"); }
break;
case 35:
//#line 144 "../../src/sintactico/sintactico.y"
{ 
																					List<Definicion> lista = new ArrayList<Definicion>();
																					lista.add(new DefFuncion(((TipoFuncion)val_peek(4)).getFila(), ((TipoFuncion)val_peek(4)).getColumna(), val_peek(4), val_peek(5), val_peek(2), val_peek(1)));
																					yyval = lista; 
																				 }
break;
case 36:
//#line 153 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1); ((List<Sentencia>)yyval).add((Sentencia)val_peek(0)); }
break;
case 37:
//#line 154 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Sentencia>(); }
break;
case 38:
//#line 157 "../../src/sintactico/sintactico.y"
{ yyval = new Return(lexico.getLinea(), lexico.getColumna(), val_peek(1)); }
break;
case 39:
//#line 158 "../../src/sintactico/sintactico.y"
{ yyval = new Lectura(lexico.getLinea(), lexico.getColumna(), val_peek(2)); }
break;
case 40:
//#line 159 "../../src/sintactico/sintactico.y"
{ yyval = new Escritura(lexico.getLinea(), lexico.getColumna(), val_peek(2)); }
break;
case 41:
//#line 160 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 42:
//#line 161 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 43:
//#line 162 "../../src/sintactico/sintactico.y"
{ yyval = new Asignacion(lexico.getLinea(), lexico.getColumna(), val_peek(3), val_peek(1)); }
break;
case 44:
//#line 163 "../../src/sintactico/sintactico.y"
{ yyval = new LlamadaFuncion(lexico.getLinea(), lexico.getColumna(), val_peek(4), val_peek(2)); }
break;
case 45:
//#line 167 "../../src/sintactico/sintactico.y"
{ yyval = new If(lexico.getLinea(), lexico.getColumna(), val_peek(7), val_peek(5), val_peek(1)); }
break;
case 46:
//#line 168 "../../src/sintactico/sintactico.y"
{ yyval = new If(lexico.getLinea(), lexico.getColumna(), val_peek(3), val_peek(1), new ArrayList<Sentencia>()); }
break;
case 47:
//#line 169 "../../src/sintactico/sintactico.y"
{ yyval = new If(lexico.getLinea(), lexico.getColumna(), val_peek(8), val_peek(5), val_peek(1)); }
break;
case 48:
//#line 172 "../../src/sintactico/sintactico.y"
{ yyval = new While(lexico.getLinea(), lexico.getColumna(), val_peek(4), val_peek(1)); }
break;
case 49:
//#line 173 "../../src/sintactico/sintactico.y"
{ yyval = new While(lexico.getLinea(), lexico.getColumna(), val_peek(3), val_peek(1)); }
break;
case 50:
//#line 179 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 51:
//#line 180 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Expresion>(); }
break;
case 52:
//#line 183 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(2); ((List<Expresion>)yyval).add((Expresion)val_peek(0)); }
break;
case 53:
//#line 184 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Expresion>(); ((List<Expresion>)yyval).add((Expresion)val_peek(0)); }
break;
case 54:
//#line 187 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLinea(), lexico.getColumna(), val_peek(2), "+", val_peek(0)); }
break;
case 55:
//#line 188 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLinea(), lexico.getColumna(), val_peek(2), "*", val_peek(0)); }
break;
case 56:
//#line 189 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLinea(), lexico.getColumna(), val_peek(2), "/", val_peek(0)); }
break;
case 57:
//#line 190 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLinea(), lexico.getColumna(), val_peek(2), "-", val_peek(0)); }
break;
case 58:
//#line 191 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLinea(), lexico.getColumna(), val_peek(2), "%", val_peek(0)); }
break;
case 59:
//#line 192 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), val_peek(2), "==", val_peek(0)); }
break;
case 60:
//#line 193 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), val_peek(2), "!=", val_peek(0)); }
break;
case 61:
//#line 194 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), val_peek(2), ">", val_peek(0)); }
break;
case 62:
//#line 195 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), val_peek(2), "<", val_peek(0)); }
break;
case 63:
//#line 196 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), val_peek(2), "<=", val_peek(0)); }
break;
case 64:
//#line 197 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), val_peek(2), ">=", val_peek(0)); }
break;
case 65:
//#line 198 "../../src/sintactico/sintactico.y"
{ yyval = new Logica(lexico.getLinea(), lexico.getColumna(), val_peek(2), "&&", val_peek(0)); }
break;
case 66:
//#line 199 "../../src/sintactico/sintactico.y"
{ yyval = new Logica(lexico.getLinea(), lexico.getColumna(), val_peek(2), "||", val_peek(0)); }
break;
case 67:
//#line 200 "../../src/sintactico/sintactico.y"
{ yyval = new InvocacionFuncion(lexico.getLinea(), lexico.getColumna(), val_peek(3), val_peek(1)); }
break;
case 68:
//#line 201 "../../src/sintactico/sintactico.y"
{ yyval = new Cast(lexico.getLinea(), lexico.getColumna(), val_peek(3), val_peek(1)); }
break;
case 69:
//#line 202 "../../src/sintactico/sintactico.y"
{ yyval = new AccesoArray(lexico.getLinea(), lexico.getColumna(), val_peek(3), val_peek(1)); }
break;
case 70:
//#line 203 "../../src/sintactico/sintactico.y"
{ yyval = new AccesoCampoStruct(lexico.getLinea(), lexico.getColumna(), val_peek(2), val_peek(0)); }
break;
case 71:
//#line 204 "../../src/sintactico/sintactico.y"
{ yyval = new Logica(lexico.getLinea(), lexico.getColumna(), val_peek(2), "!", val_peek(0)); }
break;
case 72:
//#line 205 "../../src/sintactico/sintactico.y"
{ yyval = new MenosUnario(lexico.getLinea(), lexico.getColumna(), val_peek(0)); }
break;
case 73:
//#line 206 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 74:
//#line 207 "../../src/sintactico/sintactico.y"
{ yyval = new NotUnario(lexico.getLinea(), lexico.getColumna(), val_peek(0)); }
break;
case 75:
//#line 208 "../../src/sintactico/sintactico.y"
{ yyval = new Variable(lexico.getLinea(), lexico.getColumna(), (String)val_peek(0)); }
break;
case 76:
//#line 209 "../../src/sintactico/sintactico.y"
{ yyval = new LiteralChar(lexico.getLinea(), lexico.getColumna(), val_peek(0)); }
break;
case 77:
//#line 210 "../../src/sintactico/sintactico.y"
{ yyval = new LiteralReal(lexico.getLinea(), lexico.getColumna(), val_peek(0)); }
break;
case 78:
//#line 211 "../../src/sintactico/sintactico.y"
{ yyval = new LiteralEntero(lexico.getLinea(), lexico.getColumna(), val_peek(0)); }
break;
//#line 1221 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
