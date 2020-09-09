package parser; //### This file created by BYACC 1.8(/Java extension  1.15)
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
    0,    1,    1,    2,    2,    2,    3,    3,    5,    5,
    8,    8,    6,    4,   13,   13,   11,   11,   10,   15,
   15,   14,    7,    7,    7,    7,   16,   16,   16,   18,
   19,   19,   17,   12,   12,   20,   20,   21,   21,   21,
   21,   21,   21,   21,   22,   23,   29,   29,   30,   30,
   24,   25,   31,   32,   32,   26,   33,   33,    9,   35,
   35,   36,   36,   36,   34,   38,   38,   39,   39,   39,
   39,   39,   39,   37,   41,   41,   42,   42,   42,   40,
   44,   44,   45,   45,   43,   43,   46,   46,   46,   46,
   46,   28,   47,   47,   47,   27,
};
final static short yylen[] = {                            2,
    1,    0,    2,    2,    2,    1,    1,    1,    5,    3,
    0,    2,    4,   10,    0,    3,    0,    2,    2,    0,
    2,    3,    1,    1,    1,    1,    1,    1,    1,    3,
    0,    2,    5,    0,    2,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    3,    2,    0,    3,    1,    3,
    5,    6,    4,    0,    1,    6,    0,    2,    2,    0,
    3,    1,    1,    1,    2,    0,    2,    1,    1,    1,
    1,    1,    1,    2,    0,    2,    1,    1,    1,    2,
    0,    2,    1,    1,    1,    3,    1,    1,    1,    1,
    1,    2,    0,    3,    4,    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    6,    0,    1,    0,    0,    0,    7,
    8,    0,    0,    0,    3,    4,    5,    0,    0,   10,
    0,    0,    0,   87,   88,   89,   90,    0,   12,   91,
    0,    0,    0,    0,   85,   26,    0,   27,   28,   29,
    0,    0,   23,   24,   25,   13,    0,    0,    0,    0,
    0,   92,    0,   62,   63,   64,   59,    0,   72,   73,
   69,   68,   70,   71,   65,    0,   77,   78,   79,   74,
    0,   83,   84,   80,    0,    0,    0,    0,    9,    0,
    0,    0,   19,    0,    0,   86,    0,   67,   76,   82,
   32,   30,    0,   22,    0,    0,   21,    0,   94,   61,
    0,   18,    0,   95,   33,    0,    0,    0,    0,    0,
   44,   36,    0,    0,   37,   38,   39,   40,   41,   42,
   43,    0,    0,   46,    0,    0,    0,    0,    0,    0,
   35,    0,   49,    0,    0,    0,    0,    0,    0,    0,
   14,   45,   48,    0,    0,   55,    0,    0,    0,   96,
   16,   50,   51,    0,    0,    0,    0,   53,   52,   58,
   56,
};
final static short yydgoto[] = {                          5,
    6,    7,  112,    9,   10,   11,   42,   20,   29,   48,
   96,  113,  130,   49,   83,   43,   44,   45,   77,  114,
  115,  116,  117,  118,  119,  120,  121,   30,  124,  134,
  137,  147,  157,   31,   57,   58,   32,   65,   66,   33,
   70,   71,   34,   74,   75,   35,   52,
};
final static short yysindex[] = {                      -178,
 -242, -217, -212,    0,    0,    0, -178, -208, -201,    0,
    0, -258, -174, -197,    0,    0,    0, -207, -248,    0,
 -248, -163, -221,    0,    0,    0,    0, -207,    0,    0,
 -227,   62,   49,  -13,    0,    0, -157,    0,    0,    0,
 -185, -156,    0,    0,    0,    0, -188, -181, -180, -207,
 -147,    0, -176,    0,    0,    0,    0, -207,    0,    0,
    0,    0,    0,    0,    0, -207,    0,    0,    0,    0,
 -207,    0,    0,    0, -207, -157, -150, -207,    0, -248,
 -177, -163,    0, -173, -221,    0, -227,    0,    0,    0,
    0,    0, -171,    0, -248, -142,    0, -221,    0,    0,
 -248,    0, -184,    0,    0, -222, -207, -136, -207, -161,
    0,    0, -158, -184,    0,    0,    0,    0,    0,    0,
    0, -165, -207,    0, -135, -138, -139, -207, -207, -121,
    0, -207,    0, -219, -184, -130, -129, -184, -141, -137,
    0,    0,    0, -207, -115,    0, -144, -184, -127,    0,
    0,    0,    0, -207, -106, -184, -103,    0,    0,    0,
    0,
};
final static short yyrindex[] = {                       156,
    0,    0,    0,    0,    0,    0,  156,    0,    0,    0,
    0,  -70,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
  115, -102,   73,   37,    0,    0, -100,    0,    0,    0,
    0,  -70,    0,    0,    0,    0,    0,    0, -126,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -100,    0,    0,    0,    0,
  -98,    0,    0,    0,  -37,    0,  115,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -37,    0,    0,
    0,    0, -246,    0,    0, -132,    0,    0,    0,    0,
    0,    0,  -97, -252,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -96, -122,    0, -251,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -96,  -95,    0,
    0,    0,    0,    0,    0,  -96,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
  166,    0,   59,    0,  -34,    0,  -20,  135,  -14,    0,
    0, -107,    0,   98,    0,    0,    0,    0,  109,    0,
    0,    0,    0,    0,    0,    0,    0, -101,    0,    0,
    0,    0,    0,  134,  107,    0,  129,    0,    0,  125,
    0,    0,  122,    0,    0,    0,  -69,
};
final static int YYTABLESIZE=406;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         93,
   46,  122,   76,   18,   93,   93,  131,   93,   36,   93,
   34,   34,  122,   53,   12,   99,   34,   37,   38,   39,
   40,   41,   93,   93,   93,   34,   34,  145,  104,   72,
  149,   73,   34,  122,   19,   84,  122,   93,   34,   13,
  155,   76,   93,   93,   14,   93,  122,   93,  160,   23,
   24,   25,   54,   55,  122,   56,   26,   27,    8,   94,
   93,   93,   93,   93,  123,    8,   50,   50,  143,   51,
   51,  144,  106,   81,  102,    1,    2,   16,   81,   28,
  105,    1,    2,   81,   17,   69,  107,   21,  108,   22,
   67,  109,  125,   47,  127,   68,   81,   81,   81,  110,
    3,  111,    1,   78,   80,   18,   81,    4,  133,   85,
   82,   86,   92,  139,  140,   95,   98,  142,  101,  103,
  126,   62,   59,   61,   47,  128,  129,   47,   47,  152,
   47,  132,   75,   75,   75,  136,  135,  138,   47,  158,
   47,  141,  148,   47,  146,   47,  150,  153,  151,  154,
  156,   47,   47,   47,   66,    2,  159,   66,   66,  161,
   66,   20,   31,   17,   93,   15,   34,   57,   66,   66,
   66,   54,   15,   66,   66,   66,   79,   66,   66,   97,
   66,   66,   66,   66,   91,   66,   11,   66,   66,   11,
   11,   87,   11,  100,   88,   89,   90,    0,    0,    0,
   11,    0,   11,    0,    0,   11,    0,   11,    0,    0,
    0,    0,    0,   11,   11,   11,    0,    0,    0,   93,
    0,    0,   93,   93,    0,   93,    0,    0,    0,    0,
    0,    0,    0,   93,   93,   93,    0,    0,   93,   93,
   93,    0,   93,   93,    0,   93,   93,   93,   93,    0,
   93,    0,   93,   93,    0,    0,    0,   93,    0,   93,
   93,   93,   93,   93,    0,    0,   93,    0,   93,    0,
    0,   93,   93,   93,    0,    0,   93,   93,   93,    0,
   93,   93,    0,   93,   93,   93,   93,    0,   93,    0,
   93,   93,    0,   81,    0,    0,   81,   81,    0,   81,
   93,    0,    0,    0,   93,    0,   93,   81,   81,   81,
    0,    0,   81,   81,   81,    0,   81,   81,    0,   81,
   81,   81,   81,    0,   81,    0,   81,   81,    0,   75,
    0,    0,   75,   75,    0,   75,   81,    0,    0,    0,
   81,    0,   81,   75,   75,   75,    0,    0,   75,   75,
   75,    0,   75,   75,    0,   75,   75,   75,   75,    0,
   75,   60,   75,   75,    0,   63,    0,   64,    0,    0,
    0,   60,   75,    0,   60,   60,   75,   60,   75,    0,
    0,    0,    0,    0,    0,   60,   60,   60,    0,    0,
   60,   60,   60,    0,    0,    0,    0,    0,   60,   60,
   60,    0,   60,    0,   60,   60,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   21,  103,   37,  262,   42,   43,  114,   45,  257,   47,
  263,  263,  114,   28,  257,   85,  263,  266,  267,  268,
  269,  270,   60,   61,   62,  278,  278,  135,   98,   43,
  138,   45,  285,  135,  293,   50,  138,   37,  285,  257,
  148,   76,   42,   43,  257,   45,  148,   47,  156,  257,
  258,  259,  280,  281,  156,  283,  264,  265,    0,   80,
   60,   61,   62,   78,  287,    7,  289,  289,  288,  292,
  292,  291,  257,   37,   95,  260,  261,  286,   42,  287,
  101,  260,  261,   47,  286,   37,  271,  262,  273,  287,
   42,  276,  107,  257,  109,   47,   60,   61,   62,  284,
  279,  286,  260,  289,  293,  262,  288,  286,  123,  257,
  291,  288,  263,  128,  129,  293,  290,  132,  290,  262,
  257,   60,   61,   62,  257,  287,  285,  260,  261,  144,
  263,  297,   60,   61,   62,  274,  272,  277,  271,  154,
  273,  263,  272,  276,  275,  278,  288,  263,  286,  294,
  278,  284,  285,  286,  257,    0,  263,  260,  261,  263,
  263,  288,  263,  262,  297,  263,  263,  263,  271,  272,
  273,  294,    7,  276,  277,  278,   42,  280,  281,   82,
  283,  284,  285,  286,   76,  288,  257,  290,  291,  260,
  261,   58,  263,   87,   66,   71,   75,   -1,   -1,   -1,
  271,   -1,  273,   -1,   -1,  276,   -1,  278,   -1,   -1,
   -1,   -1,   -1,  284,  285,  286,   -1,   -1,   -1,  257,
   -1,   -1,  260,  261,   -1,  263,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  271,  272,  273,   -1,   -1,  276,  277,
  278,   -1,  280,  281,   -1,  283,  284,  285,  286,   -1,
  288,   -1,  290,  291,   -1,   -1,   -1,  257,   -1,  297,
  260,  261,  300,  263,   -1,   -1,  304,   -1,  306,   -1,
   -1,  271,  272,  273,   -1,   -1,  276,  277,  278,   -1,
  280,  281,   -1,  283,  284,  285,  286,   -1,  288,   -1,
  290,  291,   -1,  257,   -1,   -1,  260,  261,   -1,  263,
  300,   -1,   -1,   -1,  304,   -1,  306,  271,  272,  273,
   -1,   -1,  276,  277,  278,   -1,  280,  281,   -1,  283,
  284,  285,  286,   -1,  288,   -1,  290,  291,   -1,  257,
   -1,   -1,  260,  261,   -1,  263,  300,   -1,   -1,   -1,
  304,   -1,  306,  271,  272,  273,   -1,   -1,  276,  277,
  278,   -1,  280,  281,   -1,  283,  284,  285,  286,   -1,
  288,  300,  290,  291,   -1,  304,   -1,  306,   -1,   -1,
   -1,  257,  300,   -1,  260,  261,  304,  263,  306,   -1,
   -1,   -1,   -1,   -1,   -1,  271,  272,  273,   -1,   -1,
  276,  277,  278,   -1,   -1,   -1,   -1,   -1,  284,  285,
  286,   -1,  288,   -1,  290,  291,
};
}
final static short YYFINAL=5;
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
"Program : GlobalDeclarations",
"GlobalDeclarations :",
"GlobalDeclarations : GlobalDeclaration GlobalDeclarations",
"GlobalDeclaration : SimpleDeclaration SEPARATOR",
"GlobalDeclaration : RoutineDeclaration SEPARATOR",
"GlobalDeclaration : SEPARATOR",
"SimpleDeclaration : VariableDeclaration",
"SimpleDeclaration : TypeDeclaration",
"VariableDeclaration : VAR IDENTIFIER COLON Type ExpressionTail",
"VariableDeclaration : VAR IDENTIFIER ExpressionTail",
"ExpressionTail :",
"ExpressionTail : IS Expression",
"TypeDeclaration : TYPE IDENTIFIER IS Type",
"RoutineDeclaration : ROUTINE IDENTIFIER LPAREN Parameters RPAREN TypeTail IS Body Return END",
"Return :",
"Return : RETURN Expression SEPARATOR",
"TypeTail :",
"TypeTail : COLON Type",
"Parameters : ParameterDeclaration ParameterDeclarations",
"ParameterDeclarations :",
"ParameterDeclarations : COMMA ParameterDeclaration",
"ParameterDeclaration : IDENTIFIER COLON Type",
"Type : PrimitiveType",
"Type : ArrayType",
"Type : RecordType",
"Type : IDENTIFIER",
"PrimitiveType : INTEGER",
"PrimitiveType : REAL",
"PrimitiveType : BOOLEAN",
"RecordType : RECORD VariableDeclarations END",
"VariableDeclarations :",
"VariableDeclarations : VariableDeclaration VariableDeclarations",
"ArrayType : ARRAY LBRACKET Expression RBRACKET Type",
"Body :",
"Body : BodyDeclaration Body",
"BodyDeclaration : SimpleDeclaration",
"BodyDeclaration : Statement",
"Statement : Assignment",
"Statement : RoutineCall",
"Statement : WhileLoop",
"Statement : ForLoop",
"Statement : IfStatement",
"Statement : Print",
"Statement : SEPARATOR",
"Assignment : ModifiablePrimary ASSIGN Expression",
"RoutineCall : IDENTIFIER RoutineCallTail",
"RoutineCallTail :",
"RoutineCallTail : LPAREN Expressions RPAREN",
"Expressions : Expression",
"Expressions : Expressions COMMA Expression",
"WhileLoop : WHILE Expression LOOP Body END",
"ForLoop : FOR IDENTIFIER Range LOOP Body END",
"Range : IN ReverseTail RANGE Expression",
"ReverseTail :",
"ReverseTail : REVERSE",
"IfStatement : IF Expression THEN Body ElseTail END",
"ElseTail :",
"ElseTail : ELSE Body",
"Expression : Relation Relations",
"Relations :",
"Relations : LogicWord Relation Relations",
"LogicWord : AND",
"LogicWord : OR",
"LogicWord : XOR",
"Relation : Simple SimpleTail",
"SimpleTail :",
"SimpleTail : RelationSign Simple",
"RelationSign : '<'",
"RelationSign : '>'",
"RelationSign : \"<=\"",
"RelationSign : \">=\"",
"RelationSign : '='",
"RelationSign : \"/=\"",
"Simple : Factor FactorTail",
"FactorTail :",
"FactorTail : FactorSign Factor",
"FactorSign : '*'",
"FactorSign : '/'",
"FactorSign : '%'",
"Factor : Summand SummandTail",
"SummandTail :",
"SummandTail : SummandSign Summand",
"SummandSign : '+'",
"SummandSign : '-'",
"Summand : Primary",
"Summand : LPAREN Expression RPAREN",
"Primary : INTEGER_LITERAL",
"Primary : REAL_LITERAL",
"Primary : TRUE",
"Primary : FALSE",
"Primary : ModifiablePrimary",
"ModifiablePrimary : IDENTIFIER ElementCall",
"ElementCall :",
"ElementCall : DOT IDENTIFIER ElementCall",
"ElementCall : LBRACKET Expression RBRACKET ElementCall",
"Print : PRINT LPAREN Expression RPAREN",
};

//#line 296 "parser.y"

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

void dotest(int i)
{
	Reader reader = new Reader();
	this.lexer = new Lexer();
	reader.read("tests/" + i + ".txt");
	lexer.tokenize(reader.sourceText);
	yyparse();
}



public static void main(String args[])
{
 Parser par = new Parser(false);
 par.dotest(3);
}
//#line 554 "Parser.java"
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
//#line 57 "parser.y"
{System.out.println("Global Declarations parsed");}
break;
case 3:
//#line 62 "parser.y"
{System.out.println("Global Declaration parsed");}
break;
case 4:
//#line 66 "parser.y"
{System.out.println("Simple Declaration parsed");}
break;
case 5:
//#line 67 "parser.y"
{System.out.println("Routine Declaration parsed");}
break;
case 6:
//#line 68 "parser.y"
{System.out.println("Routine Declaration parsed");}
break;
case 7:
//#line 72 "parser.y"
{System.out.println("Variable Declaration parsed");}
break;
case 8:
//#line 73 "parser.y"
{System.out.println("Type Declaration parsed");}
break;
//#line 731 "Parser.java"
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
