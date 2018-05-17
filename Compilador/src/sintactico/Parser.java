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
public final static short AND_AS=282;
public final static short OR_AS=283;
public final static short MENOSQUEELSE=284;
public final static short MENOS_UNARIO=285;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    3,    3,    4,    6,    8,    7,    7,    5,    5,
    5,    5,    5,   10,   10,   10,    1,    1,   12,   12,
   11,   11,   13,   15,    9,    9,   16,   16,   17,   17,
   18,   18,    2,   19,   14,   20,   20,   21,   21,   21,
   21,   21,   21,   21,   21,   21,   24,   24,   24,   25,
   25,   26,   26,   23,   23,   22,   22,   22,   22,   22,
   22,   22,   22,   22,   22,   22,   22,   22,   22,   22,
   22,   22,   22,   22,   22,   22,   22,   22,   22,   22,
   22,   22,
};
final static short yylen[] = {                            2,
    2,    3,    4,    2,    4,    3,    2,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    2,    0,    2,    0,
    1,    1,    4,    2,    3,    1,    1,    0,    3,    1,
    4,    3,    8,    1,    7,    2,    0,    3,    5,    5,
    1,    1,    4,    4,    4,    5,    9,    5,   11,    7,
    5,    1,    0,    3,    1,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    4,    4,    4,    3,    3,    2,    3,    2,    1,    1,
    1,    1,
};
final static short yydefred[] = {                        18,
    0,    0,    0,    0,    1,   17,   21,   22,   26,    0,
    0,   34,    0,    9,   10,   11,    0,    0,    0,    0,
   12,    0,   13,    0,    0,    0,    0,    0,   25,    4,
   23,    0,   30,    0,    0,   20,    0,    0,    8,    0,
    2,   24,    0,    0,    0,   20,    5,    7,    0,    3,
   14,   15,   16,   31,   29,   19,    0,    0,    6,   82,
   80,   81,    0,    0,    0,    0,    0,    0,    0,    0,
   35,    0,    0,   36,    0,   41,   42,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   33,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   37,    0,   37,   38,   77,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   74,    0,    0,    0,    0,    0,
   77,    0,    0,    0,   77,    0,   72,   44,   45,   43,
   73,    0,   46,   39,   40,   71,   37,   51,   37,    0,
    0,    0,    0,   50,    0,   37,    0,    0,   37,   47,
    0,   49,
};
final static short yydgoto[] = {                          1,
    2,    5,   20,   21,   73,   23,   38,   39,   40,   54,
    6,   45,   56,    8,   33,   34,   35,   25,   13,   57,
   74,   75,  113,   76,   77,  114,
};
final static short yysindex[] = {                         0,
    0, -261, -243, -244,    0,    0,    0,    0,    0,   97,
   -8,    0,   -2,    0,    0,    0,  -82, -215, -212,  -90,
    0,   -5,    0, -204,  -66,   18, -243,  -27,    0,    0,
    0,  -90,    0,   26,   24,    0,  -52, -123,    0,   97,
    0,    0, -222, -204, -203,    0,    0,    0,   16,    0,
    0,    0,    0,    0,    0,    0,  709, -203,    0,    0,
    0,    0,   43,   46,   48,  969, 1021, 1121, 1121, 1121,
    0, 1121,   51,    0,  102,    0,    0,  768, 1121, 1121,
 1121,   52, 1121,  134, 1121,  164,  233,  -41,  -41,  198,
 1121, 1121, 1121, 1121, 1121, 1121, 1121, 1121, 1121, 1121,
 1121, 1121, 1121, 1121, 1121, 1121, 1121, 1121, 1121, -184,
    0,  588,   33,   60,  -22,  -11, 1121,  268, 1121, 1121,
    0,  315,    0,    0,    0,  345, 1511, 1476, 1476, 1511,
  171,  171,  395,  429,  464,  171,  171,  205,  205,  -26,
  -26,  -26,  -41,  499,    0, 1121,   34,   47,   49,   61,
    0,  588,  588,  799,    0,  824,    0,    0,    0,    0,
    0,  588,    0,    0,    0,    0,    0,    0,    0, -162,
  846,  868,  -12,    0, -160,    0,    3,  899,    0,    0,
  930,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   82,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   86,    0,    0,    0,    0,    0,
    0,    0,    6,    0, 1089,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, 1089,    0,    0,
    0,    0,  523,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   95,    0,
    0,  -33,    0,    0,    0,    0,    0,    2,   37,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -10,   99,    0,    0,    0,   95,    0,    0,    0,
    0,    0,    0,    0,    0,    0, 1232,   59,   63, 1278,
 1197, 1245,   81,   85,    0, 1268, 1358, 1174, 1209,  622,
  660,  733,   72,    0,    0,    0,  558,    0,    0,    0,
    0,  -38,   28,    0,    0,    0,    0,    0,    0,    0,
    0,   -4,    0,    0,    0,    0,    0,    0,    0, 1115,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  112,    0,  118,    0,    0,  105,  151,    0,
    0,  109,  159,    0,  122,    0,    0,    0,    0,   36,
    0, 1563,  -56,    0,    0,   53,
};
final static int YYTABLESIZE=1778;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         79,
   18,   47,   69,   79,  110,   69,  108,   79,   79,   79,
   79,   79,   79,   79,    3,   11,    9,    4,  148,  110,
   69,  146,   69,  115,  116,   79,   79,   79,   79,  149,
   55,   24,  146,   55,   76,   12,   54,   26,   76,   54,
   27,   28,   76,   76,   76,   76,   76,   29,   76,  109,
   51,   52,   53,   31,   69,   32,   36,   79,   37,   79,
   76,   76,   76,   76,  109,   41,   43,   44,   70,   78,
   46,   70,    3,   78,   59,  145,  146,   78,   78,   78,
   78,   78,   79,   78,   69,   80,   70,   81,   70,   79,
   91,  117,  163,   78,   76,   78,   78,   78,   78,   67,
  147,  166,   67,   68,   75,  164,   68,  165,   75,  173,
  176,  177,   75,   75,   75,   75,   75,   67,   75,   67,
   70,   68,   28,   68,   76,  179,   27,   22,   32,   78,
   75,   75,   75,   75,  108,   53,    9,   30,  107,   52,
   19,   69,   48,  105,  103,   70,  104,  110,  106,   42,
   70,   67,   50,   10,   58,   68,  154,   49,  156,   78,
    7,  102,  100,  101,   75,   55,  108,    0,    0,  150,
  107,    0,    0,    0,    0,  105,  103,    0,  104,  110,
  106,   67,   14,   15,   16,   68,   17,   18,    0,    0,
    0,    0,  109,  102,   75,  101,  108,    0,    0,    0,
  107,    0,  171,  108,  172,  105,  103,  107,  104,  110,
  106,  178,  105,  103,  181,  104,  110,  106,    0,    0,
    0,    0,    0,  102,  109,  101,    0,   79,   79,   79,
  108,   79,   79,   79,  107,    0,    0,  108,  125,  105,
  103,  107,  104,  110,  106,    0,  105,    0,   79,   79,
  110,  106,    0,    0,  109,    0,  121,  102,    0,  101,
    0,  109,   76,   76,   76,  108,   76,   76,   76,  107,
    0,    0,    0,    0,  105,  103,    0,  104,  110,  106,
    0,    0,    0,   76,   76,    0,  123,    0,  109,    0,
    0,  124,  102,    0,  101,  109,    0,   78,   78,   78,
  108,   78,   78,   78,  107,    0,    0,    0,  151,  105,
  103,    0,  104,  110,  106,    0,    0,    0,   78,   78,
   67,   67,    0,  109,   68,   68,    0,  102,    0,  101,
    0,    0,   75,   75,   75,    0,   75,   75,   75,    0,
   67,   67,    0,    0,   68,   68,    0,  108,    0,    0,
    0,  107,    0,   75,   75,  155,  105,  103,  109,  104,
  110,  106,   92,   93,   94,    0,   95,   96,   97,   14,
   15,   16,    0,   17,  102,    0,  101,  108,    0,    0,
    0,  107,    0,   98,   99,  157,  105,  103,    0,  104,
  110,  106,    0,    0,   92,   93,   94,    0,   95,   96,
   97,    0,    0,    0,  102,  109,  101,    0,    0,    0,
    0,    0,    0,    0,    0,  119,  120,    0,    0,    0,
    0,    0,    0,    0,   92,   93,   94,  108,   95,   96,
   97,  107,    0,    0,    0,  109,  105,  103,    0,  104,
  110,  106,    0,    0,    0,  119,  120,    0,    0,    0,
    0,    0,    0,  158,  102,    0,  101,    0,   92,   93,
   94,  108,   95,   96,   97,  107,    0,    0,    0,    0,
  105,  103,    0,  104,  110,  106,    0,    0,    0,  119,
  120,    0,    0,    0,    0,  109,    0,  159,  102,    0,
  101,    0,    0,   92,   93,   94,  108,   95,   96,   97,
  107,    0,    0,    0,    0,  105,  103,    0,  104,  110,
  106,    0,    0,    0,  119,  120,    0,    0,    0,  109,
    0,    0,  160,  102,    0,  101,    0,    0,   92,   93,
   94,  108,   95,   96,   97,  107,    0,    0,    0,    0,
  105,  103,    0,  104,  110,  106,    0,    0,    0,  119,
  120,    0,    0,    0,  109,   79,    0,    0,  102,   79,
  101,    0,    0,    0,   79,   79,    0,   79,   79,   79,
    0,    0,    0,    0,    0,   92,   93,   94,    0,   95,
   96,   97,   79,   79,   79,    0,    0,    0,    0,  109,
   71,  161,    0,    0,   71,    0,  119,  120,    0,   71,
   71,    0,   71,   71,   71,   92,   93,   94,    0,   95,
   96,   97,    0,   79,    0,    0,    0,   71,   71,   71,
  108,    0,    0,    0,  107,    0,  119,  120,    0,  105,
  103,    0,  104,  110,  106,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  102,   71,  101,
    0,    0,    0,    0,    0,   92,   93,   94,   57,   95,
   96,   97,   57,   57,   57,   57,   57,    0,   57,    0,
    0,    0,    0,    0,    0,    0,  119,  120,  109,    0,
   57,   57,   57,   57,    0,    0,    0,    0,    0,   92,
   93,   94,    0,   95,   96,   97,   58,    0,    0,    0,
   58,   58,   58,   58,   58,    0,   58,    0,    0,    0,
  119,  120,    0,    0,   57,    0,    0,    0,   58,   58,
   58,   58,    0,    0,   92,   93,   94,    0,   95,   96,
   97,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   70,    0,    0,   57,  119,  120,    0,   72,    0,
    0,    0,   58,   69,    0,    0,    0,    0,    0,   92,
   93,   94,    0,   95,   96,   97,    0,    0,    0,   60,
    0,    0,    0,   60,   60,   60,   60,   60,    0,   60,
  119,  120,   58,   79,   79,   79,    0,   79,   79,   79,
    0,   60,   60,   60,   60,    0,    0,    0,    0,   18,
   70,    0,    0,    0,   79,   79,    0,   72,    0,    0,
    0,    0,   69,    0,    0,    0,    0,    0,   71,   71,
   71,    0,   71,   71,   71,   60,    0,    0,    0,    0,
    0,   70,    0,   71,    0,    0,    0,    0,   72,   71,
   71,    0,    0,   69,    0,    0,    0,    0,   92,   93,
   94,    0,   95,   96,   97,   60,   70,    0,   18,    0,
    0,    0,    0,   72,    0,    0,    0,    0,   69,  119,
  120,    0,    0,    0,    0,    0,    0,    0,   70,    0,
    0,    0,   57,   57,   57,   72,   57,   57,   57,   18,
   69,    0,  111,    0,    0,    0,    0,    0,    0,    0,
   70,    0,    0,   57,   57,    0,    0,   72,    0,    0,
    0,    0,   69,    0,   18,    0,    0,    0,    0,    0,
   58,   58,   58,  168,   58,   58,   58,    0,    0,    0,
    0,   70,    0,    0,    0,    0,   18,    0,   72,    0,
    0,   58,   58,   69,    0,    0,    0,    0,  170,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   18,    0,
    0,    0,   70,    0,    0,   60,   61,   62,   63,   72,
  174,    0,    0,    0,   69,    0,   64,   65,   66,   67,
    0,   14,   15,   16,    0,   17,   68,    0,    0,   18,
    0,    0,  175,   60,   60,   60,    0,   60,   60,   60,
    0,   70,    0,    0,    0,    0,    0,    0,   83,    0,
    0,    0,    0,   69,   60,   60,    0,    0,    0,    0,
   18,    0,    0,  180,   60,   61,   62,   63,    0,    0,
    0,    0,    0,    0,    0,   64,   65,   66,   67,    0,
   14,   15,   16,    0,   17,   68,    0,    0,    0,    0,
    0,    0,    0,   70,  182,   60,   61,   62,   63,   18,
   85,    0,    0,    0,    0,   69,   64,   65,   66,   67,
    0,   14,   15,   16,    0,   17,   68,    0,    0,    0,
   60,   61,   62,   63,    0,    0,    0,    0,    0,    0,
    0,   64,   65,   66,   67,    0,   14,   15,   16,    0,
   17,   68,   60,   61,   62,   63,    0,    0,    0,    0,
    0,   18,    0,   64,   65,   66,   67,    0,   14,   15,
   16,   37,   17,   68,   60,   61,   62,   63,   37,    0,
    0,    0,    0,   37,    0,   64,   65,   66,   67,    0,
   14,   15,   16,    0,   17,   68,    0,   48,    0,    0,
    0,    0,    0,   70,   48,   60,   61,   62,   63,   48,
   72,    0,    0,    0,    0,   69,   64,   65,   66,   67,
    0,   14,   15,   16,    0,   17,   68,    0,    0,   37,
    0,    0,    0,    0,    0,    0,   60,   61,   62,   63,
    0,    0,    0,    0,    0,    0,    0,   64,   65,   66,
   67,    0,   14,   15,   16,   48,   17,   68,    0,    0,
    0,   18,    0,   37,   56,    0,   56,   56,   56,    0,
    0,    0,    0,    0,    0,   60,   61,   62,   82,    0,
    0,    0,   56,   56,   56,   56,    0,   65,    0,   48,
   65,   14,   15,   16,    0,   17,    0,    0,    0,   59,
    0,   59,   59,   59,    0,   65,   65,   65,   65,    0,
    0,    0,    0,    0,    0,    0,   56,   59,   59,   59,
   59,    0,   62,    0,    0,   62,    0,   60,   61,   62,
   82,    0,    0,    0,    0,   66,    0,    0,   66,   65,
   62,    0,   62,   14,   15,   16,   56,   17,    0,    0,
    0,   59,    0,   66,   66,   66,   66,    0,   63,    0,
    0,   63,    0,    0,    0,    0,    0,    0,   61,   65,
    0,   61,    0,    0,   62,    0,   63,   63,   63,   63,
    0,   59,    0,    0,    0,    0,   61,   66,   61,    0,
    0,    0,    0,    0,    0,   37,   37,   37,   37,    0,
    0,    0,    0,    0,   62,    0,   37,   37,   37,   37,
   63,   37,   37,   37,    0,   37,   37,   66,    0,    0,
   61,   48,   48,   48,   48,    0,    0,   60,   61,   62,
   82,    0,   48,   48,   48,   48,    0,   48,   48,   48,
   63,   48,   48,   14,   15,   16,    0,   17,   64,    0,
   61,   64,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   64,   64,   64,   64,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   56,   56,   56,    0,   56,   56,
   56,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   64,    0,    0,    0,    0,   56,   56,   65,   65,   65,
    0,   65,   65,   65,    0,    0,    0,    0,    0,   59,
   59,   59,    0,   59,   59,   59,    0,    0,   65,   65,
   64,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   59,   59,   62,   62,   62,    0,   62,    0,    0,    0,
    0,    0,    0,    0,    0,   66,   66,   66,  108,   66,
   66,   66,  107,   62,   62,    0,    0,  105,  103,    0,
  104,  110,  106,    0,    0,    0,   66,   66,   63,   63,
   63,    0,   63,   63,   63,  102,    0,  101,   61,   61,
   61,    0,   61,  108,    0,    0,    0,  107,    0,   63,
   63,    0,  105,  103,    0,  104,  110,  106,    0,   61,
   61,    0,    0,    0,    0,    0,  109,    0,    0,    0,
  102,    0,  101,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  109,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   64,   64,
   64,    0,   64,   64,   64,    0,    0,    0,   84,   86,
   87,   88,   89,    0,   90,    0,    0,    0,    0,   64,
   64,  112,  112,  112,    0,  118,    0,  122,    0,    0,
    0,    0,    0,  126,  127,  128,  129,  130,  131,  132,
  133,  134,  135,  136,  137,  138,  139,  140,  141,  142,
  143,  144,    0,    0,    0,    0,    0,    0,    0,  112,
    0,  152,  153,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  162,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   92,    0,    0,    0,
   95,   96,   97,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   96,   97,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   91,  125,   41,   37,   46,   44,   33,   41,   42,   43,
   44,   45,   46,   47,  276,  260,  260,  279,   41,   46,
   59,   44,   61,   80,   81,   59,   60,   61,   62,   41,
   41,   40,   44,   44,   33,  280,   41,   40,   37,   44,
  123,  257,   41,   42,   43,   44,   45,  260,   47,   91,
  273,  274,  275,   59,   93,  260,  123,   91,   41,   93,
   59,   60,   61,   62,   91,   93,   41,   44,   41,   33,
  123,   44,  276,   37,   59,  260,   44,   41,   42,   43,
   44,   45,   40,   47,  123,   40,   59,   40,   61,  123,
   40,   40,   59,   58,   93,   59,   60,   61,   62,   41,
   41,   41,   44,   41,   33,   59,   44,   59,   37,  272,
  123,  272,   41,   42,   43,   44,   45,   59,   47,   61,
   93,   59,   41,   61,  123,  123,   41,   10,  123,   93,
   59,   60,   61,   62,   33,   41,  260,   20,   37,   41,
   44,   61,   38,   42,   43,   61,   45,   46,   47,   32,
  123,   93,   41,    3,   46,   93,  121,   40,  123,  123,
    2,   60,   61,   62,   93,   44,   33,   -1,   -1,  117,
   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,
   47,  123,  273,  274,  275,  123,  277,   91,   -1,   -1,
   -1,   -1,   91,   60,  123,   62,   33,   -1,   -1,   -1,
   37,   -1,  167,   33,  169,   42,   43,   37,   45,   46,
   47,  176,   42,   43,  179,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,   60,   91,   62,   -1,  261,  262,  263,
   33,  265,  266,  267,   37,   -1,   -1,   33,   41,   42,
   43,   37,   45,   46,   47,   -1,   42,   -1,  282,  283,
   46,   47,   -1,   -1,   91,   -1,  123,   60,   -1,   62,
   -1,   91,  261,  262,  263,   33,  265,  266,  267,   37,
   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,  282,  283,   -1,  123,   -1,   91,   -1,
   -1,   59,   60,   -1,   62,   91,   -1,  261,  262,  263,
   33,  265,  266,  267,   37,   -1,   -1,   -1,   41,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,  282,  283,
  262,  263,   -1,   91,  262,  263,   -1,   60,   -1,   62,
   -1,   -1,  261,  262,  263,   -1,  265,  266,  267,   -1,
  282,  283,   -1,   -1,  282,  283,   -1,   33,   -1,   -1,
   -1,   37,   -1,  282,  283,   41,   42,   43,   91,   45,
   46,   47,  261,  262,  263,   -1,  265,  266,  267,  273,
  274,  275,   -1,  277,   60,   -1,   62,   33,   -1,   -1,
   -1,   37,   -1,  282,  283,   41,   42,   43,   -1,   45,
   46,   47,   -1,   -1,  261,  262,  263,   -1,  265,  266,
  267,   -1,   -1,   -1,   60,   91,   62,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  282,  283,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  261,  262,  263,   33,  265,  266,
  267,   37,   -1,   -1,   -1,   91,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,  282,  283,   -1,   -1,   -1,
   -1,   -1,   -1,   59,   60,   -1,   62,   -1,  261,  262,
  263,   33,  265,  266,  267,   37,   -1,   -1,   -1,   -1,
   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,  282,
  283,   -1,   -1,   -1,   -1,   91,   -1,   59,   60,   -1,
   62,   -1,   -1,  261,  262,  263,   33,  265,  266,  267,
   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,  282,  283,   -1,   -1,   -1,   91,
   -1,   -1,   59,   60,   -1,   62,   -1,   -1,  261,  262,
  263,   33,  265,  266,  267,   37,   -1,   -1,   -1,   -1,
   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,  282,
  283,   -1,   -1,   -1,   91,   33,   -1,   -1,   60,   37,
   62,   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   -1,   -1,  261,  262,  263,   -1,  265,
  266,  267,   60,   61,   62,   -1,   -1,   -1,   -1,   91,
   33,   93,   -1,   -1,   37,   -1,  282,  283,   -1,   42,
   43,   -1,   45,   46,   47,  261,  262,  263,   -1,  265,
  266,  267,   -1,   91,   -1,   -1,   -1,   60,   61,   62,
   33,   -1,   -1,   -1,   37,   -1,  282,  283,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   60,   91,   62,
   -1,   -1,   -1,   -1,   -1,  261,  262,  263,   37,  265,
  266,  267,   41,   42,   43,   44,   45,   -1,   47,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  282,  283,   91,   -1,
   59,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,  261,
  262,  263,   -1,  265,  266,  267,   37,   -1,   -1,   -1,
   41,   42,   43,   44,   45,   -1,   47,   -1,   -1,   -1,
  282,  283,   -1,   -1,   93,   -1,   -1,   -1,   59,   60,
   61,   62,   -1,   -1,  261,  262,  263,   -1,  265,  266,
  267,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   33,   -1,   -1,  123,  282,  283,   -1,   40,   -1,
   -1,   -1,   93,   45,   -1,   -1,   -1,   -1,   -1,  261,
  262,  263,   -1,  265,  266,  267,   -1,   -1,   -1,   37,
   -1,   -1,   -1,   41,   42,   43,   44,   45,   -1,   47,
  282,  283,  123,  261,  262,  263,   -1,  265,  266,  267,
   -1,   59,   60,   61,   62,   -1,   -1,   -1,   -1,   91,
   33,   -1,   -1,   -1,  282,  283,   -1,   40,   -1,   -1,
   -1,   -1,   45,   -1,   -1,   -1,   -1,   -1,  261,  262,
  263,   -1,  265,  266,  267,   93,   -1,   -1,   -1,   -1,
   -1,   33,   -1,  125,   -1,   -1,   -1,   -1,   40,  282,
  283,   -1,   -1,   45,   -1,   -1,   -1,   -1,  261,  262,
  263,   -1,  265,  266,  267,  123,   33,   -1,   91,   -1,
   -1,   -1,   -1,   40,   -1,   -1,   -1,   -1,   45,  282,
  283,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   33,   -1,
   -1,   -1,  261,  262,  263,   40,  265,  266,  267,   91,
   45,   -1,  125,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   33,   -1,   -1,  282,  283,   -1,   -1,   40,   -1,   -1,
   -1,   -1,   45,   -1,   91,   -1,   -1,   -1,   -1,   -1,
  261,  262,  263,  125,  265,  266,  267,   -1,   -1,   -1,
   -1,   33,   -1,   -1,   -1,   -1,   91,   -1,   40,   -1,
   -1,  282,  283,   45,   -1,   -1,   -1,   -1,  125,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,
   -1,   -1,   33,   -1,   -1,  257,  258,  259,  260,   40,
  125,   -1,   -1,   -1,   45,   -1,  268,  269,  270,  271,
   -1,  273,  274,  275,   -1,  277,  278,   -1,   -1,   91,
   -1,   -1,  125,  261,  262,  263,   -1,  265,  266,  267,
   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,
   -1,   -1,   -1,   45,  282,  283,   -1,   -1,   -1,   -1,
   91,   -1,   -1,  125,  257,  258,  259,  260,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  268,  269,  270,  271,   -1,
  273,  274,  275,   -1,  277,  278,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   33,  125,  257,  258,  259,  260,   91,
   40,   -1,   -1,   -1,   -1,   45,  268,  269,  270,  271,
   -1,  273,  274,  275,   -1,  277,  278,   -1,   -1,   -1,
  257,  258,  259,  260,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  268,  269,  270,  271,   -1,  273,  274,  275,   -1,
  277,  278,  257,  258,  259,  260,   -1,   -1,   -1,   -1,
   -1,   91,   -1,  268,  269,  270,  271,   -1,  273,  274,
  275,   33,  277,  278,  257,  258,  259,  260,   40,   -1,
   -1,   -1,   -1,   45,   -1,  268,  269,  270,  271,   -1,
  273,  274,  275,   -1,  277,  278,   -1,   33,   -1,   -1,
   -1,   -1,   -1,   33,   40,  257,  258,  259,  260,   45,
   40,   -1,   -1,   -1,   -1,   45,  268,  269,  270,  271,
   -1,  273,  274,  275,   -1,  277,  278,   -1,   -1,   91,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  268,  269,  270,
  271,   -1,  273,  274,  275,   91,  277,  278,   -1,   -1,
   -1,   91,   -1,  125,   41,   -1,   43,   44,   45,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,   -1,
   -1,   -1,   59,   60,   61,   62,   -1,   41,   -1,  125,
   44,  273,  274,  275,   -1,  277,   -1,   -1,   -1,   41,
   -1,   43,   44,   45,   -1,   59,   60,   61,   62,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   93,   59,   60,   61,
   62,   -1,   41,   -1,   -1,   44,   -1,  257,  258,  259,
  260,   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,   93,
   59,   -1,   61,  273,  274,  275,  123,  277,   -1,   -1,
   -1,   93,   -1,   59,   60,   61,   62,   -1,   41,   -1,
   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,   41,  123,
   -1,   44,   -1,   -1,   93,   -1,   59,   60,   61,   62,
   -1,  123,   -1,   -1,   -1,   -1,   59,   93,   61,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,   -1,
   -1,   -1,   -1,   -1,  123,   -1,  268,  269,  270,  271,
   93,  273,  274,  275,   -1,  277,  278,  123,   -1,   -1,
   93,  257,  258,  259,  260,   -1,   -1,  257,  258,  259,
  260,   -1,  268,  269,  270,  271,   -1,  273,  274,  275,
  123,  277,  278,  273,  274,  275,   -1,  277,   41,   -1,
  123,   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   59,   60,   61,   62,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  261,  262,  263,   -1,  265,  266,
  267,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   93,   -1,   -1,   -1,   -1,  282,  283,  261,  262,  263,
   -1,  265,  266,  267,   -1,   -1,   -1,   -1,   -1,  261,
  262,  263,   -1,  265,  266,  267,   -1,   -1,  282,  283,
  123,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  282,  283,  261,  262,  263,   -1,  265,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  261,  262,  263,   33,  265,
  266,  267,   37,  282,  283,   -1,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,  282,  283,  261,  262,
  263,   -1,  265,  266,  267,   60,   -1,   62,  261,  262,
  263,   -1,  265,   33,   -1,   -1,   -1,   37,   -1,  282,
  283,   -1,   42,   43,   -1,   45,   46,   47,   -1,  282,
  283,   -1,   -1,   -1,   -1,   -1,   91,   -1,   -1,   -1,
   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   91,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  261,  262,
  263,   -1,  265,  266,  267,   -1,   -1,   -1,   66,   67,
   68,   69,   70,   -1,   72,   -1,   -1,   -1,   -1,  282,
  283,   79,   80,   81,   -1,   83,   -1,   85,   -1,   -1,
   -1,   -1,   -1,   91,   92,   93,   94,   95,   96,   97,
   98,   99,  100,  101,  102,  103,  104,  105,  106,  107,
  108,  109,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  117,
   -1,  119,  120,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  146,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  261,   -1,   -1,   -1,
  265,  266,  267,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  266,  267,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=285;
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
"AND_AS","OR_AS","MENOSQUEELSE","MENOS_UNARIO",
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
"sentencia : expresion AND_AS expresion ';'",
"sentencia : expresion OR_AS expresion ';'",
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
"expresion : expresion AND_AS expresion",
"expresion : expresion OR_AS expresion",
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

//#line 228 "../../src/sintactico/sintactico.y"

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
//#line 777 "Parser.java"
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
//#line 44 "../../src/sintactico/sintactico.y"
{ 
		this.ast = new Programa(0, 0, val_peek(1));
		List<Definicion> defs = ((Programa)this.ast).getDefiniciones();
		defs.add((Definicion)val_peek(0));
		((Programa)this.ast).setDefiniciones(defs);
	}
break;
case 2:
//#line 54 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Integer>(); ((List<Integer>)yyval).add((Integer)val_peek(1)); }
break;
case 3:
//#line 55 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); ((List<Integer>)yyval).add((Integer)val_peek(2)); }
break;
case 4:
//#line 58 "../../src/sintactico/sintactico.y"
{ yyval = addArray((List<Integer>)val_peek(1),(Tipo)val_peek(0),lexico.getLinea(), lexico.getColumna()); }
break;
case 5:
//#line 61 "../../src/sintactico/sintactico.y"
{ yyval = new TipoStruct(lexico.getLinea(), lexico.getColumna(), val_peek(1)); }
break;
case 6:
//#line 64 "../../src/sintactico/sintactico.y"
{ yyval = addDefCampo((Tipo)val_peek(1), (List<String>)val_peek(2), lexico.getLinea()); }
break;
case 7:
//#line 67 "../../src/sintactico/sintactico.y"
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
//#line 83 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 9:
//#line 86 "../../src/sintactico/sintactico.y"
{ yyval = Entero.getInstancia(); }
break;
case 10:
//#line 87 "../../src/sintactico/sintactico.y"
{ yyval = Real.getInstancia(); }
break;
case 11:
//#line 88 "../../src/sintactico/sintactico.y"
{ yyval = Char.getInstancia(); }
break;
case 12:
//#line 89 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 13:
//#line 90 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 14:
//#line 93 "../../src/sintactico/sintactico.y"
{ yyval = Entero.getInstancia(); }
break;
case 15:
//#line 94 "../../src/sintactico/sintactico.y"
{ yyval = Real.getInstancia(); }
break;
case 16:
//#line 95 "../../src/sintactico/sintactico.y"
{ yyval = Char.getInstancia(); }
break;
case 17:
//#line 99 "../../src/sintactico/sintactico.y"
{
																 List<Definicion> list = (List<Definicion>)val_peek(1);
																 list.addAll((List<Definicion>)val_peek(0)); 
																 yyval = list;
															}
break;
case 18:
//#line 104 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Definicion>();}
break;
case 19:
//#line 107 "../../src/sintactico/sintactico.y"
{
																 List<Definicion> list = (List<Definicion>)val_peek(1);
																 list.addAll((List<Definicion>)val_peek(0)); 
																 yyval = list;
																}
break;
case 20:
//#line 112 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Definicion>();}
break;
case 21:
//#line 115 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 22:
//#line 116 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 23:
//#line 119 "../../src/sintactico/sintactico.y"
{ yyval = addDefVar((Tipo)val_peek(1), (List<String>)val_peek(2), lexico.getLinea()); }
break;
case 24:
//#line 122 "../../src/sintactico/sintactico.y"
{ yyval = new DefVariable(lexico.getLinea(), lexico.getColumna(), val_peek(1), val_peek(0)); }
break;
case 25:
//#line 125 "../../src/sintactico/sintactico.y"
{ ((List<String>)yyval).add((String)val_peek(0)); yyval = val_peek(2);  }
break;
case 26:
//#line 126 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<String>(); ((List<String>)yyval).add((String)val_peek(0)); }
break;
case 27:
//#line 129 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 28:
//#line 130 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<DefVariable>(); }
break;
case 29:
//#line 133 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(2); ((List<DefVariable>)yyval).add((DefVariable)val_peek(0)); }
break;
case 30:
//#line 134 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<DefVariable>(); ((List<DefVariable>)yyval).add((DefVariable)val_peek(0)); }
break;
case 31:
//#line 137 "../../src/sintactico/sintactico.y"
{ yyval = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), val_peek(0), val_peek(2)); }
break;
case 32:
//#line 138 "../../src/sintactico/sintactico.y"
{ yyval = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), Void.getInstancia(), val_peek(1)); }
break;
case 33:
//#line 141 "../../src/sintactico/sintactico.y"
{ yyval = new DefFuncion(((Variable)val_peek(6)).getFila(), ((Variable)val_peek(6)).getColumna(), new TipoFuncion(lexico.getLinea(), lexico.getColumna(), Void.getInstancia(), new ArrayList<DefVariable>()),val_peek(6), val_peek(2), val_peek(1)); }
break;
case 34:
//#line 144 "../../src/sintactico/sintactico.y"
{ yyval = new Variable(lexico.getLinea(), lexico.getColumna(), "main"); }
break;
case 35:
//#line 146 "../../src/sintactico/sintactico.y"
{ 
																					List<Definicion> lista = new ArrayList<Definicion>();
																					lista.add(new DefFuncion(((TipoFuncion)val_peek(4)).getFila(), ((TipoFuncion)val_peek(4)).getColumna(), val_peek(4), val_peek(5), val_peek(2), val_peek(1)));
																					yyval = lista; 
																				 }
break;
case 36:
//#line 155 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1); ((List<Sentencia>)yyval).add((Sentencia)val_peek(0)); }
break;
case 37:
//#line 156 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Sentencia>(); }
break;
case 38:
//#line 159 "../../src/sintactico/sintactico.y"
{ yyval = new Return(lexico.getLinea(), lexico.getColumna(), val_peek(1)); }
break;
case 39:
//#line 160 "../../src/sintactico/sintactico.y"
{ yyval = new Lectura(lexico.getLinea(), lexico.getColumna(), val_peek(2)); }
break;
case 40:
//#line 161 "../../src/sintactico/sintactico.y"
{ yyval = new Escritura(lexico.getLinea(), lexico.getColumna(), val_peek(2)); }
break;
case 41:
//#line 162 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 42:
//#line 163 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 43:
//#line 164 "../../src/sintactico/sintactico.y"
{ yyval = new Asignacion(lexico.getLinea(), lexico.getColumna(), val_peek(3), val_peek(1)); }
break;
case 44:
//#line 165 "../../src/sintactico/sintactico.y"
{ 
		 													  
		 													  yyval = new Asignacion(lexico.getLinea(), lexico.getColumna(), val_peek(3),
												   				new AsignacionLogica(lexico.getLinea(), lexico.getColumna(), val_peek(3), "&&", val_peek(1))); 
		 													}
break;
case 45:
//#line 170 "../../src/sintactico/sintactico.y"
{ 
		 													  
		 													  yyval = new Asignacion(lexico.getLinea(), lexico.getColumna(), val_peek(3),
												   				new AsignacionLogica(lexico.getLinea(), lexico.getColumna(), val_peek(3), "||", val_peek(1))); 
		 													}
break;
case 46:
//#line 175 "../../src/sintactico/sintactico.y"
{ yyval = new LlamadaFuncion(lexico.getLinea(), lexico.getColumna(), val_peek(4), val_peek(2)); }
break;
case 47:
//#line 179 "../../src/sintactico/sintactico.y"
{ yyval = new If(lexico.getLinea(), lexico.getColumna(), val_peek(7), val_peek(5), val_peek(1)); }
break;
case 48:
//#line 180 "../../src/sintactico/sintactico.y"
{ yyval = new If(lexico.getLinea(), lexico.getColumna(), val_peek(3), val_peek(1), new ArrayList<Sentencia>()); }
break;
case 49:
//#line 181 "../../src/sintactico/sintactico.y"
{ yyval = new If(lexico.getLinea(), lexico.getColumna(), val_peek(8), val_peek(5), val_peek(1)); }
break;
case 50:
//#line 184 "../../src/sintactico/sintactico.y"
{ yyval = new While(lexico.getLinea(), lexico.getColumna(), val_peek(4), val_peek(1)); }
break;
case 51:
//#line 185 "../../src/sintactico/sintactico.y"
{ yyval = new While(lexico.getLinea(), lexico.getColumna(), val_peek(3), val_peek(1)); }
break;
case 52:
//#line 191 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 53:
//#line 192 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Expresion>(); }
break;
case 54:
//#line 195 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(2); ((List<Expresion>)yyval).add((Expresion)val_peek(0)); }
break;
case 55:
//#line 196 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Expresion>(); ((List<Expresion>)yyval).add((Expresion)val_peek(0)); }
break;
case 56:
//#line 199 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLinea(), lexico.getColumna(), val_peek(2), "+", val_peek(0)); }
break;
case 57:
//#line 200 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLinea(), lexico.getColumna(), val_peek(2), "*", val_peek(0)); }
break;
case 58:
//#line 201 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLinea(), lexico.getColumna(), val_peek(2), "/", val_peek(0)); }
break;
case 59:
//#line 202 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLinea(), lexico.getColumna(), val_peek(2), "-", val_peek(0)); }
break;
case 60:
//#line 203 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLinea(), lexico.getColumna(), val_peek(2), "%", val_peek(0)); }
break;
case 61:
//#line 204 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), val_peek(2), "==", val_peek(0)); }
break;
case 62:
//#line 205 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), val_peek(2), "!=", val_peek(0)); }
break;
case 63:
//#line 206 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), val_peek(2), ">", val_peek(0)); }
break;
case 64:
//#line 207 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), val_peek(2), "<", val_peek(0)); }
break;
case 65:
//#line 208 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), val_peek(2), "<=", val_peek(0)); }
break;
case 66:
//#line 209 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), val_peek(2), ">=", val_peek(0)); }
break;
case 67:
//#line 210 "../../src/sintactico/sintactico.y"
{ yyval = new Logica(lexico.getLinea(), lexico.getColumna(), val_peek(2), "&&", val_peek(0)); }
break;
case 68:
//#line 211 "../../src/sintactico/sintactico.y"
{ yyval = new Logica(lexico.getLinea(), lexico.getColumna(), val_peek(2), "||", val_peek(0)); }
break;
case 69:
//#line 212 "../../src/sintactico/sintactico.y"
{ yyval = new AsignacionLogica(lexico.getLinea(), lexico.getColumna(), val_peek(2), "&&", val_peek(0)); }
break;
case 70:
//#line 213 "../../src/sintactico/sintactico.y"
{ yyval = new AsignacionLogica(lexico.getLinea(), lexico.getColumna(), val_peek(2), "||", val_peek(0)); }
break;
case 71:
//#line 214 "../../src/sintactico/sintactico.y"
{ yyval = new InvocacionFuncion(lexico.getLinea(), lexico.getColumna(), val_peek(3), val_peek(1)); }
break;
case 72:
//#line 215 "../../src/sintactico/sintactico.y"
{ yyval = new Cast(lexico.getLinea(), lexico.getColumna(), val_peek(3), val_peek(1)); }
break;
case 73:
//#line 216 "../../src/sintactico/sintactico.y"
{ yyval = new AccesoArray(lexico.getLinea(), lexico.getColumna(), val_peek(3), val_peek(1)); }
break;
case 74:
//#line 217 "../../src/sintactico/sintactico.y"
{ yyval = new AccesoCampoStruct(lexico.getLinea(), lexico.getColumna(), val_peek(2), val_peek(0)); }
break;
case 75:
//#line 218 "../../src/sintactico/sintactico.y"
{ yyval = new Logica(lexico.getLinea(), lexico.getColumna(), val_peek(2), "!", val_peek(0)); }
break;
case 76:
//#line 219 "../../src/sintactico/sintactico.y"
{ yyval = new MenosUnario(lexico.getLinea(), lexico.getColumna(), val_peek(0)); }
break;
case 77:
//#line 220 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 78:
//#line 221 "../../src/sintactico/sintactico.y"
{ yyval = new NotUnario(lexico.getLinea(), lexico.getColumna(), val_peek(0)); }
break;
case 79:
//#line 222 "../../src/sintactico/sintactico.y"
{ yyval = new Variable(lexico.getLinea(), lexico.getColumna(), (String)val_peek(0)); }
break;
case 80:
//#line 223 "../../src/sintactico/sintactico.y"
{ yyval = new LiteralChar(lexico.getLinea(), lexico.getColumna(), val_peek(0)); }
break;
case 81:
//#line 224 "../../src/sintactico/sintactico.y"
{ yyval = new LiteralReal(lexico.getLinea(), lexico.getColumna(), val_peek(0)); }
break;
case 82:
//#line 225 "../../src/sintactico/sintactico.y"
{ yyval = new LiteralEntero(lexico.getLinea(), lexico.getColumna(), val_peek(0)); }
break;
//#line 1294 "Parser.java"
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
