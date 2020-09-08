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
package parser;





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
    0,    0,    1,    2,    2,    3,    3,    5,    8,    8,
    6,    4,   13,   13,   11,   11,   10,   15,   15,   14,
    7,    7,    7,    7,   16,   16,   16,   18,   19,   19,
   17,   12,   12,   20,   20,   21,   21,   21,   21,   21,
   21,   22,   23,   29,   29,   30,   30,   24,   25,   31,
   32,   32,   26,   33,   33,    9,   35,   35,   36,   36,
   36,   34,   38,   38,   39,   39,   39,   39,   39,   39,
   37,   41,   41,   42,   42,   42,   40,   44,   44,   45,
   45,   43,   43,   46,   46,   46,   46,   46,   28,   47,
   47,   47,   27,
};
final static short yylen[] = {                            2,
    0,    1,    2,    1,    1,    3,    1,    5,    0,    2,
    4,   10,    0,    2,    0,    2,    2,    0,    2,    3,
    1,    1,    1,    1,    1,    1,    1,    3,    0,    2,
    5,    0,    2,    1,    1,    1,    1,    1,    1,    1,
    1,    3,    2,    0,    3,    1,    3,    5,    6,    4,
    0,    1,    6,    0,    2,    2,    0,    3,    1,    1,
    1,    2,    0,    2,    1,    1,    1,    1,    1,    1,
    2,    0,    2,    1,    1,    1,    2,    0,    2,    1,
    1,    1,    3,    1,    1,    1,    1,    1,    2,    0,
    3,    4,    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    2,    0,    4,    5,    7,    0,
    0,    0,    3,    0,    6,    0,    0,    0,   24,    0,
   25,   26,   27,    0,   11,   21,   22,   23,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   17,    0,
   30,   28,    0,   84,   85,   86,   87,    0,    0,   88,
    0,    0,    0,    0,   82,   20,    0,    0,   19,    0,
    8,    0,    0,   89,    0,    0,   59,   60,   61,   56,
    0,   69,   70,   66,   65,   67,   68,   62,    0,   74,
   75,   76,   71,    0,   80,   81,   77,    0,   16,    0,
   10,    0,    0,   83,   31,    0,   64,   73,   79,    0,
    0,    0,    0,    0,   34,    0,    0,   35,   36,   37,
   38,   39,   40,   41,    0,    0,   91,   58,    0,   43,
    0,    0,    0,    0,    0,    0,   33,    0,   92,   46,
    0,    0,    0,    0,    0,    0,   14,   12,   42,   45,
    0,    0,   52,    0,    0,    0,   93,   47,   48,    0,
    0,    0,    0,   50,   49,   55,   53,
};
final static short yydgoto[] = {                          4,
    5,    6,  105,    8,   33,    9,   25,   61,   49,   30,
   58,  106,  126,   31,   39,   26,   27,   28,   34,  107,
  108,  109,  110,  111,  112,  113,  114,   50,  120,  131,
  134,  144,  153,   51,   70,   71,   52,   78,   79,   53,
   83,   84,   54,   87,   88,   55,   64,
};
final static short yysindex[] = {                      -244,
 -236, -224, -223,    0,    0, -244,    0,    0,    0, -211,
 -209, -228,    0, -197,    0, -202, -188, -222,    0, -211,
    0,    0,    0, -214,    0,    0,    0,    0, -217, -199,
 -200, -202, -211, -168, -207, -202, -193, -188,    0, -154,
    0,    0, -196,    0,    0,    0,    0, -207, -183,    0,
 -195,   62,   45,  -23,    0,    0, -202, -153,    0, -207,
    0, -207, -147,    0, -176, -202,    0,    0,    0,    0,
 -207,    0,    0,    0,    0,    0,    0,    0, -207,    0,
    0,    0,    0, -207,    0,    0,    0, -207,    0, -155,
    0, -177, -196,    0,    0, -195,    0,    0,    0, -250,
 -207, -138, -207, -167,    0, -159, -155,    0,    0,    0,
    0,    0,    0,    0, -169, -196,    0,    0, -207,    0,
 -141, -137, -136, -207, -207, -127,    0, -207,    0,    0,
 -187, -155, -133, -125, -155, -143,    0,    0,    0,    0,
 -207, -115,    0, -145, -155, -128,    0,    0,    0, -207,
 -111, -155, -110,    0,    0,    0,    0,
};
final static short yyrindex[] = {                       157,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -98,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -130,    0,  -98,    0,    0,    0,  -93,    0,    0,  115,
    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
  -85, -117,   73,   37,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -254,
    0,    0,  -37,    0,    0,  -85,    0,    0,    0, -146,
    0,    0,    0,    0,    0,  -86, -249,    0,    0,    0,
    0,    0,    0,    0,    0,  -37,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -84, -124,    0, -259,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -84,  -83,    0,    0,    0,    0,
    0,  -84,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  175,    0,   12,    0,  172,    0,  -25,    0,  -47,    0,
    0, -105,    0,  145,    0,    0,    0,    0,  151,    0,
    0,    0,    0,    0,    0,    0,    0,  -62,    0,    0,
    0,    0,    0,  114,   93,    0,  111,    0,    0,  112,
    0,    0,  107,    0,    0,    0,  -90,
};
final static int YYTABLESIZE=400;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         90,
   65,  127,  117,   32,   90,   90,   40,   90,   32,   90,
   56,    7,   91,   32,   92,    1,    2,    7,   32,   85,
   10,   86,   90,   90,   90,  129,  142,  115,   32,  146,
   32,   89,   11,   12,    3,   32,  119,   90,   62,  151,
   95,   63,   90,   90,  115,   90,  156,   90,   14,   43,
   44,   45,   16,  121,   19,  123,   46,   47,   17,   18,
   90,   90,   90,   20,   21,   22,   23,   24,   29,  115,
   32,  130,  115,   78,   35,   36,  136,  137,   78,   48,
  139,   82,  115,   78,   67,   68,   80,   69,   37,  115,
   38,   81,   62,  148,   42,   63,   78,   78,   78,   57,
  140,  100,  154,  141,    1,    2,   66,   60,   90,   93,
   44,   94,  116,   44,   44,  101,   44,  102,  122,  124,
  103,   75,   72,   74,   44,  125,   44,  128,  104,   44,
  132,   44,   72,   72,   72,  138,  133,   44,   44,   63,
  135,  143,   63,   63,  147,   63,  145,  149,  150,  152,
   90,  155,  157,   63,   63,   63,    1,   18,   63,   63,
   63,   63,   63,   63,   29,   63,   63,   63,   15,   51,
   63,   57,   63,   63,   57,   57,   13,   57,   32,   54,
   13,   15,   59,   41,   96,   57,   57,   57,  118,   97,
   57,   57,   57,   57,   99,   98,    0,    0,   57,   57,
    0,    0,   57,    0,   57,   57,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   90,
    0,    0,   90,   90,    0,   90,    0,    0,    0,    0,
    0,    0,    0,   90,   90,   90,    0,    0,   90,   90,
   90,   90,   90,   90,    0,   90,   90,   90,    0,    0,
   90,    0,   90,   90,    0,    0,    0,   90,    0,   90,
   90,   90,   90,   90,    0,    0,   90,    0,   90,    0,
    0,   90,   90,   90,    0,    0,   90,   90,   90,   90,
   90,   90,    0,   90,   90,   90,    0,    0,   90,    0,
   90,   90,    0,   78,    0,    0,   78,   78,    0,   78,
   90,    0,    0,    0,   90,    0,   90,   78,   78,   78,
    0,    0,   78,   78,   78,   78,   78,   78,    0,   78,
   78,   78,    0,    0,   78,    0,   78,   78,    0,   72,
    0,    0,   72,   72,    0,   72,   78,    0,    0,    0,
   78,    0,   78,   72,   72,   72,    0,    0,   72,   72,
   72,   72,   72,   72,    0,   72,   72,   72,    0,    0,
   72,   73,   72,   72,    0,   76,    0,   77,    0,    0,
    0,    9,   72,    0,    9,    9,   72,    9,   72,    0,
    0,    0,    0,    0,    0,    9,    0,    9,    0,    0,
    9,    0,    9,    9,    0,    0,    0,    0,    9,    9,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   48,  107,   93,  263,   42,   43,   32,   45,  263,   47,
   36,    0,   60,  263,   62,  260,  261,    6,  278,   43,
  257,   45,   60,   61,   62,  116,  132,   90,  278,  135,
  285,   57,  257,  257,  279,  285,  287,   37,  289,  145,
   66,  292,   42,   43,  107,   45,  152,   47,  260,  257,
  258,  259,  262,  101,  257,  103,  264,  265,  287,  257,
   60,   61,   62,  266,  267,  268,  269,  270,  257,  132,
  293,  119,  135,   37,  289,  293,  124,  125,   42,  287,
  128,   37,  145,   47,  280,  281,   42,  283,  288,  152,
  291,   47,  289,  141,  263,  292,   60,   61,   62,  293,
  288,  257,  150,  291,  260,  261,  290,  262,  262,  257,
  257,  288,  290,  260,  261,  271,  263,  273,  257,  287,
  276,   60,   61,   62,  271,  285,  273,  297,  284,  276,
  272,  278,   60,   61,   62,  263,  274,  284,  285,  257,
  277,  275,  260,  261,  288,  263,  272,  263,  294,  278,
  297,  263,  263,  271,  272,  273,    0,  288,  276,  277,
  278,  279,  280,  281,  263,  283,  284,  285,  262,  294,
  288,  257,  290,  291,  260,  261,  263,  263,  263,  263,
    6,   10,   38,   33,   71,  271,  272,  273,   96,   79,
  276,  277,  278,  279,   88,   84,   -1,   -1,  284,  285,
   -1,   -1,  288,   -1,  290,  291,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,
   -1,   -1,  260,  261,   -1,  263,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  271,  272,  273,   -1,   -1,  276,  277,
  278,  279,  280,  281,   -1,  283,  284,  285,   -1,   -1,
  288,   -1,  290,  291,   -1,   -1,   -1,  257,   -1,  297,
  260,  261,  300,  263,   -1,   -1,  304,   -1,  306,   -1,
   -1,  271,  272,  273,   -1,   -1,  276,  277,  278,  279,
  280,  281,   -1,  283,  284,  285,   -1,   -1,  288,   -1,
  290,  291,   -1,  257,   -1,   -1,  260,  261,   -1,  263,
  300,   -1,   -1,   -1,  304,   -1,  306,  271,  272,  273,
   -1,   -1,  276,  277,  278,  279,  280,  281,   -1,  283,
  284,  285,   -1,   -1,  288,   -1,  290,  291,   -1,  257,
   -1,   -1,  260,  261,   -1,  263,  300,   -1,   -1,   -1,
  304,   -1,  306,  271,  272,  273,   -1,   -1,  276,  277,
  278,  279,  280,  281,   -1,  283,  284,  285,   -1,   -1,
  288,  300,  290,  291,   -1,  304,   -1,  306,   -1,   -1,
   -1,  257,  300,   -1,  260,  261,  304,  263,  306,   -1,
   -1,   -1,   -1,   -1,   -1,  271,   -1,  273,   -1,   -1,
  276,   -1,  278,  279,   -1,   -1,   -1,   -1,  284,  285,
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
"Program : GlobalDeclarations",
"GlobalDeclarations : GlobalDeclaration GlobalDeclarations",
"GlobalDeclaration : SimpleDeclaration",
"GlobalDeclaration : RoutineDeclaration",
"SimpleDeclaration : VAR IDENTIFIER VariableDeclaration",
"SimpleDeclaration : TypeDeclaration",
"VariableDeclaration : VAR IDENTIFIER COLON Type ExpressionTail",
"ExpressionTail :",
"ExpressionTail : IS Expression",
"TypeDeclaration : TYPE IDENTIFIER IS Type",
"RoutineDeclaration : ROUTINE IDENTIFIER LPAREN Parameters RPAREN TypeTail IS Body Return END",
"Return :",
"Return : RETURN Expression",
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

//#line 293 "parser.y"

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
//#line 546 "Parser.java"
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
