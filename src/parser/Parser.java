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
public final static short NEWLINE=286;
public final static short SEMICOLON=287;
public final static short LPAREN=288;
public final static short RPAREN=289;
public final static short LBRACKET=290;
public final static short RBRACKET=291;
public final static short COMMA=292;
public final static short DOT=293;
public final static short COLON=294;
public final static short RANGE=295;
public final static short EQUALS=297;
public final static short ASSIGN=298;
public final static short NEQUALS=300;
public final static short GREATER=302;
public final static short LESS=303;
public final static short LEQUALS=304;
public final static short GEQUALS=306;
public final static short PLUS=308;
public final static short MINUS=309;
public final static short MULTIPLY=310;
public final static short DIVIDE=311;
public final static short REMAINDER=312;
public final static short SLCOMMENT=313;
public final static short MLCOMMENT_START=315;
public final static short MLCOMMENT_END=317;
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
    1,    0,    2,    1,    1,    1,    1,    1,    6,    4,
    0,    2,    4,   10,    0,    3,    0,    2,    2,    0,
    2,    3,    1,    1,    1,    1,    1,    1,    1,    4,
    0,    2,    5,    0,    2,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    3,    2,    0,    3,    1,    3,
    5,    6,    5,    0,    1,    6,    0,    2,    2,    0,
    3,    1,    1,    1,    2,    0,    2,    1,    1,    1,
    1,    1,    1,    2,    0,    2,    1,    1,    1,    2,
    0,    2,    1,    1,    1,    3,    1,    1,    1,    1,
    1,    2,    0,    3,    4,    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    6,    0,    1,    0,    4,    5,    7,
    8,    0,    0,    0,    3,    0,    0,    0,    0,    0,
    0,   87,   88,   89,   90,    0,   12,   91,    0,    0,
    0,    0,   85,   26,    0,   27,   28,   29,    0,    0,
   23,   24,   25,   10,   13,    0,    0,    0,    0,    0,
   92,    0,   62,   63,   64,   59,    0,   72,   73,   69,
   68,   70,   71,   65,    0,   77,   78,   79,   74,    0,
   83,   84,   80,    0,    0,    0,    0,    0,    0,    0,
   19,    0,    0,   86,    0,   67,   76,   82,    0,    0,
    0,    9,   22,    0,    0,   21,    0,   94,   61,   32,
   30,    0,   18,    0,   95,   33,    0,    0,    0,    0,
    0,   44,   36,    0,    0,   37,   38,   39,   40,   41,
   42,   43,    0,    0,   46,    0,    0,    0,    0,    0,
    0,   35,    0,   49,    0,    0,    0,    0,    0,    0,
    0,   14,   45,   48,    0,    0,   55,    0,    0,    0,
   96,   16,   50,   51,    0,    0,    0,    0,    0,   52,
   58,   56,   53,
};
final static short yydgoto[] = {                          5,
    6,    7,  113,    9,   10,   11,   40,   18,   27,   47,
   95,  114,  131,   48,   81,   41,   42,   43,   90,  115,
  116,  117,  118,  119,  120,  121,  122,   28,  125,  135,
  138,  148,  158,   29,   56,   57,   30,   64,   65,   31,
   69,   70,   32,   73,   74,   33,   51,
};
final static short yysindex[] = {                      -257,
 -249, -241, -239,    0,    0,    0, -257,    0,    0,    0,
    0, -261, -234, -240,    0,  -80,   27, -242,   27, -204,
 -281,    0,    0,    0,    0,  -80,    0,    0, -184,  -24,
 -220, -203,    0,    0, -231,    0,    0,    0, -248, -193,
    0,    0,    0,    0,    0, -218, -210, -206,  -80, -183,
    0, -201,    0,    0,    0,    0,  -80,    0,    0,    0,
    0,    0,    0,    0,  -80,    0,    0,    0,    0,  -80,
    0,    0,    0,  -80, -165,  -80, -186,   27, -192, -204,
    0, -182, -281,    0, -184,    0,    0,    0, -165, -155,
 -181,    0,    0,   27, -150,    0, -281,    0,    0,    0,
    0,   27,    0,  110,    0,    0, -215,  -80, -144,  -80,
 -172,    0,    0, -167,  110,    0,    0,    0,    0,    0,
    0,    0, -178,  -80,    0, -151, -145, -154,  -80,  -80,
 -139,    0,  -80,    0, -222,  110, -147, -136,  110, -148,
 -142,    0,    0,    0,  -80, -121,    0,  -80,  110, -131,
    0,    0,    0,    0, -143, -115,  110, -113,  -80,    0,
    0,    0,    0,
};
final static short yyrindex[] = {                       153,
    0,    0,    0,    0,    0,    0,  153,    0,    0,    0,
    0, -127,    0,    0,    0,    0,    0,    0,    0,    0,
 -146,    0,    0,    0,    0,    0,    0,    0,   77,   44,
  -34,  -90,    0,    0,    0,    0,    0,    0,    0, -127,
    0,    0,    0,    0,    0,    0,    0, -134,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -102,    0,    0,    0,  -94,    0,
    0,    0, -246,    0,   77,    0,    0,    0, -102,    0,
    0,    0,    0,    0,    0,    0, -246,    0,    0,    0,
    0,    0,    0, -244,    0,    0,   14,    0,    0,    0,
    0,    0,    0,  -91, -191,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -89,  -40,    0, -258,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -89,  -88,
    0,    0,    0,    0,    0,    0,  -89,    0,    0,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
  162,    0,    6,    0,  -65,    0,  -17,  136,  -26,    0,
    0,  -56,    0,  100,    0,    0,    0,    0,  103,    0,
    0,    0,    0,    0,    0,    0,    0,  -68,    0,    0,
    0,    0,    0,  132,  112,    0,  133,    0,    0,  130,
    0,    0,  129,    0,    0,    0,  -76,
};
final static int YYTABLESIZE=396;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         52,
   16,   45,    1,    2,   34,    8,   98,   12,   49,   89,
   93,   50,    8,   93,   93,   13,   93,   14,   34,   34,
  105,    3,   82,   89,   93,   93,   93,   19,    4,   93,
   93,   93,   17,   93,   93,  123,   93,   93,   93,   93,
   34,   76,   93,   44,   93,   93,  123,   20,   93,   91,
   93,   93,   46,   93,   75,   93,   93,   93,  132,   93,
   93,   93,   93,   93,   93,   93,  144,  123,   16,  145,
  123,   34,  124,   83,   49,   78,  103,   50,   79,  146,
  123,  126,  150,  128,  106,   80,   34,   84,  123,   66,
   67,   68,  156,   34,    1,   53,   54,  134,   55,   92,
  161,   94,  140,  141,   71,   72,  143,  101,   97,  102,
   93,  104,  127,   93,   93,  129,   93,  130,  153,  133,
  136,  155,  139,  142,   93,   93,   93,  147,  137,   93,
   93,   93,  163,   93,   93,  149,   93,   93,   93,   93,
  151,  154,   93,  152,   93,   93,  157,  160,   93,  162,
   93,  159,    2,   93,   20,   93,   93,   93,   11,   93,
   31,   93,   93,   93,   93,   93,   81,   17,   15,   81,
   81,   15,   81,   34,   57,   77,   21,   22,   23,   96,
   81,   81,   81,   24,   25,   81,   81,   81,   85,   81,
   81,  100,   81,   81,   81,   81,   99,   86,   81,   87,
   81,   81,   88,    0,   81,    0,   81,   26,    0,   81,
    0,   81,   81,   81,    0,   81,   54,   54,   54,   81,
   81,   81,   75,   54,   54,   75,   75,    0,   75,    0,
    0,    0,    0,    0,    0,    0,   75,   75,   75,    0,
    0,   75,   75,   75,    0,   75,   75,   54,   75,   75,
   75,   75,    0,    0,   75,    0,   75,   75,    0,    0,
   75,    0,   75,    0,    0,   75,    0,   75,   75,   75,
   47,   75,   58,   47,   47,   59,   47,   60,   61,   62,
    0,   63,    0,   34,   47,    0,   47,    0,    0,   47,
    0,   47,   35,   36,   37,   38,   39,   47,   47,   47,
   66,    0,    0,   66,   66,    0,   66,    0,    0,    0,
    0,   93,    0,    0,   66,   66,   66,    0,    0,   66,
   66,   66,    0,   66,   66,    0,   66,   66,   66,   66,
    0,    0,   66,   60,   66,   66,   60,   60,   66,   60,
    0,    0,    0,    0,    0,    0,    0,   60,   60,   60,
    0,    0,   60,   60,   60,    0,    0,    0,    0,    0,
   60,   60,   60,    0,    0,   60,  107,   60,   60,    1,
    2,   60,    0,    0,    0,    0,    0,    0,    0,    0,
  108,    0,  109,    0,    0,  110,    0,    0,    0,    0,
    0,    0,    0,  111,    0,  112,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         26,
  262,   19,  260,  261,  263,    0,   83,  257,  290,   75,
  257,  293,    7,  260,  261,  257,  263,  257,  263,  278,
   97,  279,   49,   89,  271,  272,  273,  262,  286,  276,
  277,  278,  294,  280,  281,  104,  283,  284,  285,  286,
  285,  290,  289,  286,  291,  292,  115,  288,  295,   76,
  297,  298,  257,  300,  286,  302,  303,  304,  115,  306,
   78,  308,  309,  310,  311,  312,  289,  136,  262,  292,
  139,  263,  288,  257,  290,  294,   94,  293,  289,  136,
  149,  108,  139,  110,  102,  292,  278,  289,  157,  310,
  311,  312,  149,  285,  260,  280,  281,  124,  283,  286,
  157,  294,  129,  130,  308,  309,  133,  263,  291,  291,
  257,  262,  257,  260,  261,  288,  263,  285,  145,  298,
  272,  148,  277,  263,  271,  272,  273,  275,  274,  276,
  277,  278,  159,  280,  281,  272,  283,  284,  285,  286,
  289,  263,  289,  286,  291,  292,  278,  263,  295,  263,
  297,  295,    0,  300,  289,  302,  303,  304,  286,  306,
  263,  308,  309,  310,  311,  312,  257,  262,    7,  260,
  261,  263,  263,  263,  263,   40,  257,  258,  259,   80,
  271,  272,  273,  264,  265,  276,  277,  278,   57,  280,
  281,   89,  283,  284,  285,  286,   85,   65,  289,   70,
  291,  292,   74,   -1,  295,   -1,  297,  288,   -1,  300,
   -1,  302,  303,  304,   -1,  306,  257,  258,  259,  310,
  311,  312,  257,  264,  265,  260,  261,   -1,  263,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  271,  272,  273,   -1,
   -1,  276,  277,  278,   -1,  280,  281,  288,  283,  284,
  285,  286,   -1,   -1,  289,   -1,  291,  292,   -1,   -1,
  295,   -1,  297,   -1,   -1,  300,   -1,  302,  303,  304,
  257,  306,  297,  260,  261,  300,  263,  302,  303,  304,
   -1,  306,   -1,  257,  271,   -1,  273,   -1,   -1,  276,
   -1,  278,  266,  267,  268,  269,  270,  284,  285,  286,
  257,   -1,   -1,  260,  261,   -1,  263,   -1,   -1,   -1,
   -1,  298,   -1,   -1,  271,  272,  273,   -1,   -1,  276,
  277,  278,   -1,  280,  281,   -1,  283,  284,  285,  286,
   -1,   -1,  289,  257,  291,  292,  260,  261,  295,  263,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  271,  272,  273,
   -1,   -1,  276,  277,  278,   -1,   -1,   -1,   -1,   -1,
  284,  285,  286,   -1,   -1,  289,  257,  291,  292,  260,
  261,  295,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  271,   -1,  273,   -1,   -1,  276,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  284,   -1,  286,
};
}
final static short YYFINAL=5;
final static short YYMAXTOKEN=318;
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
"ROUTINE","AND","OR","NOT","XOR","PRINT","RETURN","NEWLINE","SEMICOLON",
"LPAREN","RPAREN","LBRACKET","RBRACKET","COMMA","DOT","COLON","RANGE","\"..\"",
"EQUALS","ASSIGN","\":=\"","NEQUALS","\"/=\"","GREATER","LESS","LEQUALS",
"\"<=\"","GEQUALS","\">=\"","PLUS","MINUS","MULTIPLY","DIVIDE","REMAINDER",
"SLCOMMENT","\"//\"","MLCOMMENT_START","\"/*\"","MLCOMMENT_END","\"*/\"",
};
final static String yyrule[] = {
"$accept : Program",
"Program : GlobalDeclarations",
"GlobalDeclarations :",
"GlobalDeclarations : GlobalDeclaration GlobalDeclarations",
"GlobalDeclaration : SimpleDeclaration",
"GlobalDeclaration : RoutineDeclaration",
"GlobalDeclaration : NEWLINE",
"SimpleDeclaration : VariableDeclaration",
"SimpleDeclaration : TypeDeclaration",
"VariableDeclaration : VAR IDENTIFIER COLON Type ExpressionTail NEWLINE",
"VariableDeclaration : VAR IDENTIFIER ExpressionTail NEWLINE",
"ExpressionTail :",
"ExpressionTail : IS Expression",
"TypeDeclaration : TYPE IDENTIFIER IS Type",
"RoutineDeclaration : ROUTINE IDENTIFIER LPAREN Parameters RPAREN TypeTail IS Body Return END",
"Return :",
"Return : RETURN Expression NEWLINE",
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
"RecordType : RECORD NEWLINE VariableDeclarations END",
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
"Statement : NEWLINE",
"Assignment : ModifiablePrimary ASSIGN Expression",
"RoutineCall : IDENTIFIER RoutineCallTail",
"RoutineCallTail :",
"RoutineCallTail : LPAREN Expressions RPAREN",
"Expressions : Expression",
"Expressions : Expressions COMMA Expression",
"WhileLoop : WHILE Expression LOOP Body END",
"ForLoop : FOR IDENTIFIER Range LOOP Body END",
"Range : IN ReverseTail Expression RANGE Expression",
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
"RelationSign : LESS",
"RelationSign : GREATER",
"RelationSign : LEQUALS",
"RelationSign : GEQUALS",
"RelationSign : EQUALS",
"RelationSign : NEQUALS",
"Simple : Factor FactorTail",
"FactorTail :",
"FactorTail : FactorSign Factor",
"FactorSign : MULTIPLY",
"FactorSign : DIVIDE",
"FactorSign : REMAINDER",
"Factor : Summand SummandTail",
"SummandTail :",
"SummandTail : SummandSign Summand",
"SummandSign : PLUS",
"SummandSign : MINUS",
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
	case ADD -> code = PLUS;
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
	case NEWLINE -> code = NEWLINE;
	case SEMICOLON -> code = SEMICOLON;
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
 par.dotest(1);
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
