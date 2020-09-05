package parser;//### This file created by BYACC 1.8(/Java extension  1.15)
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






//#line 2 "parser.y"
import java.lang.Math;
import java.io.*;
import java.util.StringTokenizer;
import lexer.*;
import reader.Reader;

//#line 24 "Parser.java"




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
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IDENTIFIER=257;
public final static short INTEGER_LITERAL=258;
public final static short REAL_LITERAL=259;
public final static short VAR=260;
public final static short TYPE=261;
public final static short IS=262;
public final static short END=263;
public final static short TRUE=264;
public final static short FALSE=265;
public final static short RECORD=266;
public final static short INTEGER=267;
public final static short REAL=268;
public final static short BOOLEAN=269;
public final static short ARRAY=270;
public final static short WHILE=271;
public final static short LOOP=272;
public final static short FOR=273;
public final static short IN=274;
public final static short REVERSE=275;
public final static short IF=276;
public final static short THEN=277;
public final static short ELSE=278;
public final static short ROUTINE=279;
public final static short AND=280;
public final static short OR=281;
public final static short NOT=282;
public final static short XOR=283;
public final static short PRINT=284;
public final static short RETURN=285;
public final static short SEPARATOR=286;
public final static short LPAREN=287;
public final static short RPAREN=288;
public final static short LBRACKET=289;
public final static short RBRACKET=290;
public final static short COMMA=291;
public final static short DOT=292;
public final static short COLON=293;
public final static short RANGE=294;
public final static short EQUALS=296;
public final static short ASSIGN=297;
public final static short NEQUALS=299;
public final static short GREATER=301;
public final static short LESS=302;
public final static short LEQUALS=303;
public final static short GEQUALS=305;
public final static short ADD=307;
public final static short MINUS=308;
public final static short MULTIPLY=309;
public final static short DIVIDE=310;
public final static short REMAINDER=311;
public final static short SLCOMMENT=312;
public final static short MLCOMMENT_START=314;
public final static short MLCOMMENT_END=316;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    0,    1,    1,    3,    5,    5,    8,    8,
    4,    2,   10,   10,    9,   12,   12,   13,    7,    7,
    7,    7,   14,   14,   14,   16,   17,   17,   15,   11,
   11,   18,   18,   19,   19,   19,   19,   19,   20,   21,
   26,   26,   22,   23,   27,   27,   24,   28,   28,    6,
   29,   30,   30,   32,   32,   32,   31,   33,   33,   33,
   35,   35,   35,   35,   35,   35,   34,   36,   36,   38,
   38,   38,   37,   39,   39,   41,   41,   40,   40,   43,
   42,   42,   42,   42,   42,   25,   44,   44,   44,
};
final static short yylen[] = {                            2,
    0,    1,    1,    1,    1,    3,    2,    3,    0,    2,
    4,    7,    0,    2,    3,    1,    3,    2,    1,    1,
    1,    1,    1,    1,    1,    3,    1,    2,    5,    1,
    2,    1,    1,    1,    1,    1,    1,    1,    3,    4,
    1,    3,    5,    6,    4,    5,    6,    0,    2,    1,
    2,    0,    2,    1,    1,    1,    1,    0,    1,    3,
    1,    1,    1,    1,    1,    1,    1,    1,    3,    1,
    1,    1,    1,    1,    3,    1,    1,    1,    1,    3,
    1,    1,    1,    1,    1,    2,    0,    3,    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    2,    3,    4,    5,    0,    0,
    0,   52,    0,    6,    0,    0,    0,    7,    0,    0,
   22,    0,   23,   24,   25,    0,    0,   19,   20,   21,
   11,    0,    0,   16,    0,    0,   54,   55,   56,   53,
    0,   81,   82,   83,   84,   52,   85,   51,    0,   59,
    0,   68,    0,   74,   78,   79,   27,    0,   52,   52,
    8,   18,   15,    0,   14,    0,   52,    0,   86,    0,
   65,   66,   62,   61,   63,   64,    0,   70,   71,   72,
    0,   76,   77,    0,   26,   28,    0,   10,   17,    0,
   52,    0,   52,   32,    0,   30,   33,   34,   35,   36,
   37,   38,    0,    0,    0,   80,   60,   69,   75,    0,
   52,    0,    0,    0,   12,   31,   52,    0,   88,   29,
   41,    0,    0,    0,    0,    0,   39,   89,   40,   52,
    0,   52,    0,    0,    0,   42,   43,    0,   52,    0,
    0,    0,   52,   45,   44,    0,   47,   46,
};
final static short yydgoto[] = {                          4,
   94,    6,    7,    8,   14,   18,   32,   61,   17,   36,
   95,   33,   34,   28,   29,   30,   58,   96,   97,   98,
   99,  100,  101,  102,  103,  122,  125,  142,   19,   20,
   48,   40,   49,   50,   77,   51,   52,   81,   53,   54,
   84,   55,   56,   69,
};
final static short yysindex[] = {                      -246,
 -248, -240, -236,    0,    0,    0,    0,    0, -244, -249,
 -259,    0,  -79,    0,  -79,  -79, -243,    0, -213,  -34,
    0, -226,    0,    0,    0, -237, -208,    0,    0,    0,
    0, -199, -262,    0,  -79, -203,    0,    0,    0,    0,
 -206,    0,    0,    0,    0,    0,    0,    0,  -58,    0,
   48,    0,   -4,    0,    0,    0,    0, -159,    0,    0,
    0,    0,    0,  -79,    0, -129,    0, -197,    0,   25,
    0,    0,    0,    0,    0,    0,  -34,    0,    0,    0,
  -34,    0,    0,  -34,    0,    0, -214,    0,    0, -200,
    0, -180,    0,    0, -241,    0,    0,    0,    0,    0,
    0,    0, -207, -190, -206,    0,    0,    0,    0,  -79,
    0, -179, -169, -171,    0,    0,    0, -206,    0,    0,
    0, -175, -129, -158, -154, -129,    0,    0,    0,    0,
 -120,    0, -176, -129, -149,    0,    0, -174,    0,  -99,
 -129, -140,    0,    0,    0, -129,    0,    0,
};
final static short yyrindex[] = {                       125,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -136,    0,  218,  145,
    0,    0,    0,    0,    0,    0,  241,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   37,    0,    0,    0,    0,    0,    0,    0,  181,    0,
  109,    0,   73,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -168,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    1,    0,    0,
    0,    0,    0,  298,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -127,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -125,    0,    0,
};
final static short yygindex[] = {                         0,
  139,    0,  -11,    0,    0,  -36,   -8,    0,    0,    0,
  -70,    0,   82,    0,    0,    0,    0,  -95,    0,    0,
    0,    0,    0,    0,  -12,    0,    0,    0,    0,    0,
    0,    0,    0,   71,    0,    0,   68,    0,    0,   70,
    0,    0,    0,  -93,
};
final static int YYTABLESIZE=604;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                        116,
   87,   74,   71,   73,   27,   46,   31,   47,    9,   70,
   57,  119,   15,    1,    2,   90,   10,   12,    1,    2,
   11,  115,   87,   88,  128,   63,   65,   16,   64,   91,
  104,   92,    3,    1,   93,  116,   87,   87,   82,  116,
   83,   87,   87,   87,  116,   87,   86,   87,   13,   35,
  116,   59,  131,   60,  112,  135,  114,   62,   66,  105,
   87,   87,   87,  140,   47,  106,   37,   38,   47,   39,
  146,   47,   73,   87,  121,  110,  113,   87,   87,   87,
  127,   87,   67,   87,   80,   68,  111,  133,   67,   78,
  117,   68,  123,  136,   79,  138,   87,   87,   87,  118,
    1,  120,  144,   85,  124,  126,  148,   90,   67,   73,
    1,    2,  129,   73,   73,  130,  132,  134,  139,   73,
  143,   91,  147,   92,    1,   13,   93,   90,  141,   87,
    1,    2,   73,   73,   73,   48,   90,   49,    5,    1,
    2,   91,  137,   92,   58,   89,   93,  107,  108,   67,
   91,    0,   92,  109,    0,   93,    0,   90,    0,    0,
    1,    2,    0,  145,    0,    0,    0,    0,   67,   67,
   67,   91,    0,   92,    0,    0,   93,   21,    0,    0,
   57,    0,    0,    0,    0,   58,   22,   23,   24,   25,
   26,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   58,   58,   58,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   50,    0,    0,
    0,   57,   41,   42,   43,    0,    0,    0,    0,   44,
   45,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    9,   72,    0,    0,    0,   75,    0,   76,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   87,   50,    0,
   87,   87,    0,   87,    0,    0,    0,    0,    0,    0,
    0,   87,   87,   87,    0,    0,   87,   87,   87,    0,
   87,   87,    0,   87,    0,    0,    0,    0,   87,    0,
   87,   87,    0,   87,    0,   87,   87,   87,   87,   87,
   87,    0,    0,    0,   87,    0,   87,   87,   87,   87,
    0,    0,   87,   87,   87,    0,   87,   87,    0,   87,
    0,    0,    0,    0,   87,    0,   87,   87,    0,   73,
    0,   87,   73,   73,    0,   73,   87,   52,    0,    0,
   87,    0,   87,   73,   73,   73,    0,    0,   73,   73,
   73,    0,   73,   73,    0,   73,    0,   52,   52,   52,
   73,    0,   73,   73,    0,   67,    0,   73,   67,   67,
    0,   67,   73,    0,    0,    0,   73,    0,   73,   67,
   67,   67,    0,    0,   67,   67,   67,    0,   67,   67,
    0,   67,    0,    0,    0,    0,   67,    0,   67,   67,
    0,    0,    0,   67,   58,   58,    0,   58,   67,    0,
    0,    0,   67,    0,   67,   58,   58,   58,    0,    0,
   58,   58,   58,    0,   58,   58,    0,   58,    0,    0,
    0,    0,   58,    0,   58,   58,    0,   57,    0,   58,
   57,   57,    0,   57,   58,    0,    0,    0,   58,    0,
   58,   57,   57,   57,    0,    0,   57,   57,   57,    0,
   57,   57,    0,   57,    0,    0,    0,    0,   57,    0,
   57,   57,    0,    0,   50,   57,    0,   50,   50,    0,
   50,    0,    0,    0,    0,    0,    0,    0,   50,   50,
   50,    0,    0,   50,   50,   50,    0,    9,    0,    0,
    9,    9,    0,    9,    0,   50,    0,   50,   50,    0,
    0,    9,   50,    9,    0,    0,    9,    0,    9,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   52,   52,   52,    0,    0,    0,
    0,   52,   52,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   52,   52,    0,
   52,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   52,    0,    0,    0,    0,   52,    0,    0,
    0,   52,    0,   52,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         95,
    0,   60,   61,   62,   13,   40,   15,   20,  257,   46,
   22,  105,  262,  260,  261,  257,  257,  262,  260,  261,
  257,  263,   59,   60,  118,  288,   35,  287,  291,  271,
   67,  273,  279,  260,  276,  131,    0,   37,   43,  135,
   45,   41,   42,   43,  140,   45,   58,   47,  293,  293,
  146,  289,  123,  262,   91,  126,   93,  257,  262,  257,
   60,   61,   62,  134,   77,   41,  280,  281,   81,  283,
  141,   84,    0,   37,  111,  290,  257,   41,   42,   43,
  117,   45,  289,   47,   37,  292,  287,  124,  289,   42,
  298,  292,  272,  130,   47,  132,   60,   61,   62,  290,
  260,  110,  139,  263,  274,  277,  143,  257,    0,   37,
  260,  261,  288,   41,   42,  291,  275,  272,  295,   47,
  295,  271,  263,  273,    0,  262,  276,  257,  278,  298,
  260,  261,   60,   61,   62,  263,  257,  263,    0,  260,
  261,  271,  263,  273,    0,   64,  276,   77,   81,   41,
  271,   -1,  273,   84,   -1,  276,   -1,  257,   -1,   -1,
  260,  261,   -1,  263,   -1,   -1,   -1,   -1,   60,   61,
   62,  271,   -1,  273,   -1,   -1,  276,  257,   -1,   -1,
    0,   -1,   -1,   -1,   -1,   41,  266,  267,  268,  269,
  270,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   60,   61,   62,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,    0,   -1,   -1,
   -1,   41,  257,  258,  259,   -1,   -1,   -1,   -1,  264,
  265,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
    0,  300,   -1,   -1,   -1,  304,   -1,  306,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,   41,   -1,
  260,  261,   -1,  263,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  271,  272,  273,   -1,   -1,  276,  277,  278,   -1,
  280,  281,   -1,  283,   -1,   -1,   -1,   -1,  288,   -1,
  290,  291,   -1,  257,   -1,  295,  260,  261,  298,  263,
  300,   -1,   -1,   -1,  304,   -1,  306,  271,  272,  273,
   -1,   -1,  276,  277,  278,   -1,  280,  281,   -1,  283,
   -1,   -1,   -1,   -1,  288,   -1,  290,  291,   -1,  257,
   -1,  295,  260,  261,   -1,  263,  300,   40,   -1,   -1,
  304,   -1,  306,  271,  272,  273,   -1,   -1,  276,  277,
  278,   -1,  280,  281,   -1,  283,   -1,   60,   61,   62,
  288,   -1,  290,  291,   -1,  257,   -1,  295,  260,  261,
   -1,  263,  300,   -1,   -1,   -1,  304,   -1,  306,  271,
  272,  273,   -1,   -1,  276,  277,  278,   -1,  280,  281,
   -1,  283,   -1,   -1,   -1,   -1,  288,   -1,  290,  291,
   -1,   -1,   -1,  295,  260,  261,   -1,  263,  300,   -1,
   -1,   -1,  304,   -1,  306,  271,  272,  273,   -1,   -1,
  276,  277,  278,   -1,  280,  281,   -1,  283,   -1,   -1,
   -1,   -1,  288,   -1,  290,  291,   -1,  257,   -1,  295,
  260,  261,   -1,  263,  300,   -1,   -1,   -1,  304,   -1,
  306,  271,  272,  273,   -1,   -1,  276,  277,  278,   -1,
  280,  281,   -1,  283,   -1,   -1,   -1,   -1,  288,   -1,
  290,  291,   -1,   -1,  257,  295,   -1,  260,  261,   -1,
  263,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  271,  272,
  273,   -1,   -1,  276,  277,  278,   -1,  257,   -1,   -1,
  260,  261,   -1,  263,   -1,  288,   -1,  290,  291,   -1,
   -1,  271,  295,  273,   -1,   -1,  276,   -1,  278,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  257,  258,  259,   -1,   -1,   -1,
   -1,  264,  265,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  280,  281,   -1,
  283,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  295,   -1,   -1,   -1,   -1,  300,   -1,   -1,
   -1,  304,   -1,  306,
};
}
final static short YYFINAL=4;
final static short YYMAXTOKEN=317;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
null,"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"IDENTIFIER","INTEGER_LITERAL",
"REAL_LITERAL","VAR","TYPE","IS","END","TRUE","FALSE","RECORD","INTEGER","REAL",
"BOOLEAN","ARRAY","WHILE","LOOP","FOR","IN","REVERSE","IF","THEN","ELSE",
"ROUTINE","AND","OR","NOT","XOR","PRINT","RETURN","SEPARATOR","LPAREN","RPAREN",
"LBRACKET","RBRACKET","COMMA","DOT","COLON","RANGE","\"..\"","EQUALS","ASSIGN",
"\":=\"","NEQUALS","\"/=\"","GREATER","LESS","LEQUALS","\"<=\"","GEQUALS",
"\">=\"","ADD","MINUS","MULTIPLY","DIVIDE","REMAINDER","SLCOMMENT","\"//\"",
"MLCOMMENT_START","\"/*\"","MLCOMMENT_END","\"*/\"",
};
final static String yyrule[] = {
"$accept : Program",
"Program :",
"Program : SimpleDeclaration",
"Program : RoutineDeclaration",
"SimpleDeclaration : VariableDeclaration",
"SimpleDeclaration : TypeDeclaration",
"VariableDeclaration : VAR IDENTIFIER VarDecMid",
"VarDecMid : IS Expression",
"VarDecMid : COLON Type VarDecEnd",
"VarDecEnd :",
"VarDecEnd : IS Expression",
"TypeDeclaration : TYPE IDENTIFIER IS Type",
"RoutineDeclaration : ROUTINE IDENTIFIER Parameters SetType IS Body END",
"SetType :",
"SetType : COLON Type",
"Parameters : LPAREN ParameterList RPAREN",
"ParameterList : Parameter",
"ParameterList : ParameterList COMMA Parameter",
"Parameter : Type IDENTIFIER",
"Type : PrimitiveType",
"Type : ArrayType",
"Type : RecordType",
"Type : IDENTIFIER",
"PrimitiveType : INTEGER",
"PrimitiveType : REAL",
"PrimitiveType : BOOLEAN",
"RecordType : RECORD Variables END",
"Variables : VariableDeclaration",
"Variables : Variables VariableDeclaration",
"ArrayType : ARRAY LBRACKET Expression RBRACKET Type",
"Body : BodyList",
"Body : Body BodyList",
"BodyList : SimpleDeclaration",
"BodyList : Statement",
"Statement : Assignment",
"Statement : RoutineCall",
"Statement : WhileLoop",
"Statement : ForLoop",
"Statement : IfStatement",
"Assignment : ModifiablePrimary \":=\" Expression",
"RoutineCall : IDENTIFIER LPAREN Expressions RPAREN",
"Expressions : Expression",
"Expressions : Expressions COMMA Expression",
"WhileLoop : WHILE Expression LOOP Body END",
"ForLoop : FOR IDENTIFIER Range LOOP Body END",
"Range : IN Expression \"..\" Expression",
"Range : IN REVERSE Expression \"..\" Expression",
"IfStatement : IF Expression THEN Body ElseTail END",
"ElseTail :",
"ElseTail : ELSE Body",
"Expression : Relations",
"Relations : BeginRelation Relation",
"BeginRelation :",
"BeginRelation : Relations LogicWord",
"LogicWord : AND",
"LogicWord : OR",
"LogicWord : XOR",
"Relation : Simples",
"Simples :",
"Simples : Simple",
"Simples : Simples RelationSign Simple",
"RelationSign : '<'",
"RelationSign : '>'",
"RelationSign : \"<=\"",
"RelationSign : \">=\"",
"RelationSign : '='",
"RelationSign : \"/=\"",
"Simple : Factors",
"Factors : Factor",
"Factors : Factors FactorSign Factor",
"FactorSign : '*'",
"FactorSign : '/'",
"FactorSign : '%'",
"Factor : Summands",
"Summands : Summand",
"Summands : Summands SummandSign Summand",
"SummandSign : '+'",
"SummandSign : '-'",
"Summand : Primary",
"Summand : Exp",
"Exp : '(' Expression ')'",
"Primary : INTEGER_LITERAL",
"Primary : REAL_LITERAL",
"Primary : TRUE",
"Primary : FALSE",
"Primary : ModifiablePrimary",
"ModifiablePrimary : IDENTIFIER ElementCall",
"ElementCall :",
"ElementCall : DOT IDENTIFIER ElementCall",
"ElementCall : LBRACKET Expression RBRACKET ElementCall",
};

//#line 288 "parser.y"

Lexer lexer;

int yylex() {
	Token tok = lexer.lex();
	TokenType type = tok.getType();
	System.out.println(tok.toString());
    int code;
    switch (type) {
	case VAR -> code = VAR;
	case TYPE -> code = TYPE;
	case IS -> code = IS;
	case END -> code = END;
	case INTEGER -> code = INTEGER;
	case REAL -> code = REAL;
	case BOOLEAN -> code = BOOLEAN;
	case TRUE -> code = TRUE;
	case FALSE -> code = FALSE;
	case RECORD -> code = RECORD;
	case ARRAY -> code = ARRAY;
	case WHILE -> code = WHILE;
	case LOOP -> code = LOOP;
	case FOR -> code = FOR;
	case IN -> code = IN;
	case REVERSE -> code = REVERSE;
	case IF -> code = IF;
	case THEN -> code = THEN;
	case ELSE -> code = ELSE;
	case ROUTINE -> code = ROUTINE;
	case AND -> code = AND;
	case OR -> code = OR;
	case NOT -> code = NOT;
	case XOR -> code = XOR;
	case PRINT -> code = PRINT;
	case RETURN -> code = RETURN;
	case RANGE -> code = RANGE;
	case ADD -> code = ADD;
	case MINUS -> code = MINUS;
	case MULTIPLY -> code = MULTIPLY;
	case DIVIDE -> code = DIVIDE;
	case REMAINDER -> code = REMAINDER;
	case RBRACKET -> code = RBRACKET;
	case LBRACKET -> code = LBRACKET;
	case LESS -> code = LESS;
	case LEQUALS -> code = LEQUALS;
	case GREATER -> code = GREATER;
	case GEQUALS -> code = GEQUALS;
	case EQUALS -> code = EQUALS;
	case NEQUALS -> code = NEQUALS;
	case DOT -> code = DOT;
	case COMMA -> code = COMMA;
	case ASSIGN -> code = ASSIGN;
	case COLON -> code = COLON;
	case LPAREN -> code = LPAREN;
	case RPAREN -> code = RPAREN;
	case SLCOMMENT -> code = SLCOMMENT;
	case MLCOMMENT_START -> code = MLCOMMENT_START;
	case MLCOMMENT_END -> code = MLCOMMENT_END;
	case IDENTIFIER -> code = IDENTIFIER;
	case INTEGER_LITERAL -> code = INTEGER_LITERAL;
	case REAL_LITERAL -> code = REAL_LITERAL;
	case SEPARATOR -> code = SEPARATOR;
//	case EOF -> code = 0;
	default -> code = -1;
    }
    return code;
}

void yyerror(String mes) {
    System.out.println(mes);
}

void dotest()
{
	Reader reader = new Reader();
	this.lexer = new Lexer();
	int i = 3;
	reader.read("tests/" + i + ".txt");
	lexer.tokenize(reader.sourceText);
	yyparse();
}



public static void main(String args[])
{
 Parser par = new Parser(false);
 par.dotest();
}
//#line 580 "Parser.java"
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
