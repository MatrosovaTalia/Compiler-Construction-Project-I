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
    9,    9,    8,    8,    6,    4,   14,   12,   12,   11,
   11,   15,    7,    7,    7,    7,   16,   16,   16,   18,
   19,   19,   19,   17,   13,   13,   20,   20,   21,   21,
   21,   21,   21,   21,   21,   21,   22,   23,   29,   29,
   24,   25,   30,   31,   31,   26,   32,   32,   10,   10,
   10,   10,   34,   34,   34,   36,   36,   36,   33,   38,
   38,   39,   39,   39,   39,   39,   39,   37,   41,   41,
   42,   42,   42,   40,   44,   44,   35,   35,   43,   43,
   45,   45,   45,   45,   45,   45,   28,   46,   46,   46,
   27,
};
final static short yylen[] = {                            2,
    1,    0,    2,    1,    1,    1,    1,    1,    6,    4,
    1,    1,    0,    2,    4,   10,    2,    0,    2,    1,
    3,    3,    1,    1,    1,    1,    1,    1,    1,    4,
    0,    2,    2,    5,    0,    2,    1,    1,    2,    2,
    2,    2,    2,    2,    2,    1,    3,    4,    1,    3,
    5,    6,    5,    0,    1,    6,    0,    2,    2,    3,
    3,    4,    0,    3,    4,    1,    1,    1,    2,    0,
    3,    1,    1,    1,    1,    1,    1,    2,    0,    3,
    1,    1,    1,    2,    0,    3,    1,    1,    1,    3,
    1,    1,    1,    1,    1,    1,    2,    0,    3,    4,
    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    6,    0,    1,    0,    4,    5,    7,
    8,    0,    0,    0,    3,    0,    0,    0,    0,    0,
    0,   91,   92,   93,   94,    0,    0,   87,   88,   14,
   96,   95,    0,    0,    0,    0,    0,   89,   26,    0,
   27,   28,   29,    0,    0,   23,   24,   25,   11,   12,
   10,   15,    0,    0,    0,    0,    0,    0,   97,    0,
    0,    0,   66,   67,   68,   59,    0,    0,   76,   77,
   73,   72,   74,   75,   69,    0,   81,   82,   83,   78,
    0,    0,   84,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   61,    0,   90,    0,    0,   60,    0,
    0,    0,    0,    0,    0,    0,    9,   22,    0,    0,
   21,    0,   48,    0,   99,   62,    0,   64,   71,   80,
   86,   33,   32,   30,    0,   19,    0,   50,  100,   65,
   34,    0,    0,    0,    0,    0,   46,   37,    0,    0,
    0,   38,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   17,    0,   45,   36,   39,   40,   41,
   42,   43,   44,    0,    0,    0,    0,    0,    0,   16,
   47,    0,   55,    0,    0,    0,  101,   51,    0,    0,
    0,    0,    0,   52,   58,   56,   53,
};
final static short yydgoto[] = {                          5,
    6,    7,  138,    9,   10,   11,   45,   18,   51,   90,
   54,  110,  139,  140,   55,   46,   47,   48,  105,  141,
  142,  143,   31,  145,  146,  147,  148,   32,   91,  167,
  174,  182,   33,   66,   34,   67,   35,   75,   76,   36,
   80,   81,   37,   83,   38,   59,
};
final static short yysindex[] = {                      -130,
 -247, -242, -237,    0,    0,    0, -130,    0,    0,    0,
    0, -256, -239, -261,    0, -229, -132, -219, -132, -224,
 -171,    0,    0,    0,    0, -164, -229,    0,    0,    0,
    0,    0, -141, -160,  -39, -122, -206,    0,    0, -236,
    0,    0,    0, -251, -178,    0,    0,    0,    0,    0,
    0,    0, -208, -199, -196, -229, -229, -150,    0, -141,
 -160, -174,    0,    0,    0,    0, -233, -141,    0,    0,
    0,    0,    0,    0,    0, -160,    0,    0,    0,    0,
 -160, -160,    0, -252, -229, -219, -132, -181, -224, -169,
 -163, -162, -274,    0, -141,    0, -160, -141,    0,  -39,
 -122, -206, -252, -252, -120, -144,    0,    0, -132, -121,
    0, -229,    0, -274,    0,    0, -141,    0,    0,    0,
    0,    0,    0,    0, -132,    0,   -1,    0,    0,    0,
    0, -229, -107, -229, -129, -229,    0,    0, -101, -219,
   -1,    0, -219, -219, -219, -219, -219, -219, -134, -104,
  -99,  -98, -229,    0, -219,    0,    0,    0,    0,    0,
    0,    0,    0, -229,   -1, -102,  -91,   -1, -119,    0,
    0,  -72,    0, -229,   -1,  -84,    0,    0,  -96,  -70,
   -1,  -66, -229,    0,    0,    0,    0,
};
final static short yyrindex[] = {                       200,
    0,    0,    0,    0,    0,    0,  200,    0,    0,    0,
    0, -177,    0,    0,    0,    0,    0,    0,    0,    0,
 -126,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   16,    0,    9,  -49,  -85,    0,    0,    0,
    0,    0,    0,    0, -177,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -86,    0,    0,    0,    0,   16,
    0,    0,    0,    0,    0,    0,    0,   16,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -58,    0,    0,    0,  -53,    0,  -81,
    0,    0, -126,    0,   16,    0,    0,   16,    0,    9,
  -49,  -85,  -58,  -58,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -126,    0,    0,   16,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -52,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -207,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -52, -176,    0, -207,    0,    0,
    0,    0,    0,    0,  -52,  -50,    0,    0,    0,    0,
  -52,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  207,    0,   14,    0,  -30,    0,  -17,  171,  -83,  -16,
  131,    0,  -54,    0,    0,    0,    0,    0,   49,    0,
    0,    0, -123,    0,    0,    0,    0,  -90, -105,    0,
    0,    0,  -21,  -51,  -25,    0,  146,  124,    0,  148,
  129,    0,  151,  133,    0,  -71,
};
final static int YYTABLESIZE=311;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         30,
   61,   52,  107,  144,   60,   16,  128,    1,   94,   12,
   62,   82,   68,    8,   13,   57,   99,  144,   58,   14,
    8,  115,   19,   21,   22,   23,   20,   21,   22,   23,
   24,   25,   53,  103,   24,   25,  149,   17,   85,   95,
   92,  144,  129,  116,  144,   98,  118,  169,   97,   84,
  149,  144,   26,  104,   27,   35,  156,  144,   27,  158,
  159,  160,  161,  162,  163,  130,   49,   50,  106,  108,
   35,  170,  104,  104,  149,  117,   82,  149,   28,   29,
   54,   54,   54,   16,  149,   87,  157,   54,   54,   88,
  149,  126,   21,   22,   23,   89,   21,   22,   23,   24,
   25,   28,   29,   24,   25,   54,   93,  131,   13,   13,
  172,   54,  109,  176,   96,  150,   56,  152,   57,  154,
  180,   58,  112,   27,   39,  113,  185,   27,  114,    1,
    2,   54,   54,   40,   41,   42,   43,   44,   63,   64,
  127,   65,  124,   28,   29,   98,  125,  171,    3,  151,
   98,  122,  123,   98,   98,    4,   98,  179,  153,   98,
   98,  155,   98,  164,   98,   98,  187,  165,   98,  177,
   98,   98,  173,   98,  166,   98,   98,   98,  168,   98,
  175,   98,   98,   98,   98,   98,   85,   77,   78,   79,
  178,   85,  184,  181,   85,   85,  186,   85,  183,    2,
   85,   85,   20,   85,   31,   85,   85,   49,   18,   85,
   35,   85,   57,   15,   85,   86,   85,   85,   85,  111,
   85,  100,   79,  119,   85,   85,   85,   79,  101,  120,
   79,   79,  102,   79,  121,    0,   79,   79,    0,   79,
    0,   79,   79,    0,    0,   79,    0,   79,    0,    0,
   79,    0,   79,   79,   79,   21,   79,   69,    1,    2,
   70,    0,   71,   72,   73,    0,   74,    0,    0,  132,
    0,  133,    0,    0,  134,    0,    0,    0,    0,    0,
   70,    0,  135,  136,  137,   70,    0,   63,   70,   70,
    0,   70,   63,    0,   70,   70,    0,   70,    0,   70,
   70,   63,   63,   70,   63,    0,   63,   63,    0,    0,
   63,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         16,
   26,   19,   86,  127,   26,  262,  112,  260,   60,  257,
   27,   37,   34,    0,  257,  290,   68,  141,  293,  257,
    7,   93,  262,  257,  258,  259,  288,  257,  258,  259,
  264,  265,  257,  286,  264,  265,  127,  294,  290,   61,
   57,  165,  114,   95,  168,   67,   98,  153,  282,  286,
  141,  175,  282,   84,  288,  263,  140,  181,  288,  143,
  144,  145,  146,  147,  148,  117,  286,  287,   85,   87,
  278,  155,  103,  104,  165,   97,  102,  168,  308,  309,
  257,  258,  259,  262,  175,  294,  141,  264,  265,  289,
  181,  109,  257,  258,  259,  292,  257,  258,  259,  264,
  265,  308,  309,  264,  265,  282,  257,  125,  286,  287,
  165,  288,  294,  168,  289,  132,  288,  134,  290,  136,
  175,  293,  292,  288,  257,  289,  181,  288,  291,  260,
  261,  308,  309,  266,  267,  268,  269,  270,  280,  281,
  262,  283,  263,  308,  309,  272,  291,  164,  279,  257,
  277,  103,  104,  280,  281,  286,  283,  174,  288,  286,
  287,  263,  289,  298,  291,  292,  183,  272,  295,  289,
  297,  298,  275,  300,  274,  302,  303,  304,  277,  306,
  272,  308,  309,  310,  311,  312,  272,  310,  311,  312,
  263,  277,  263,  278,  280,  281,  263,  283,  295,    0,
  286,  287,  289,  289,  263,  291,  292,  289,  262,  295,
  263,  297,  263,    7,  300,   45,  302,  303,  304,   89,
  306,   76,  272,  100,  310,  311,  312,  277,   81,  101,
  280,  281,   82,  283,  102,   -1,  286,  287,   -1,  289,
   -1,  291,  292,   -1,   -1,  295,   -1,  297,   -1,   -1,
  300,   -1,  302,  303,  304,  257,  306,  297,  260,  261,
  300,   -1,  302,  303,  304,   -1,  306,   -1,   -1,  271,
   -1,  273,   -1,   -1,  276,   -1,   -1,   -1,   -1,   -1,
  272,   -1,  284,  285,  286,  277,   -1,  272,  280,  281,
   -1,  283,  277,   -1,  286,  287,   -1,  289,   -1,  291,
  292,  286,  287,  295,  289,   -1,  291,  292,   -1,   -1,
  295,
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
"VariableDeclaration : VAR IDENTIFIER COLON Type ExpressionTail OptionalSemicolon",
"VariableDeclaration : VAR IDENTIFIER ExpressionTail OptionalSemicolon",
"OptionalSemicolon : NEWLINE",
"OptionalSemicolon : SEMICOLON",
"ExpressionTail :",
"ExpressionTail : IS Expression",
"TypeDeclaration : TYPE IDENTIFIER IS Type",
"RoutineDeclaration : ROUTINE IDENTIFIER LPAREN Parameters RPAREN TypeTail IS Body END OptionalSemicolon",
"Return : RETURN Expression",
"TypeTail :",
"TypeTail : COLON Type",
"Parameters : ParameterDeclaration",
"Parameters : ParameterDeclaration COMMA Parameters",
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
"VariableDeclarations : NEWLINE VariableDeclarations",
"ArrayType : ARRAY LBRACKET Expression RBRACKET Type",
"Body :",
"Body : BodyDeclaration Body",
"BodyDeclaration : SimpleDeclaration",
"BodyDeclaration : Statement",
"Statement : Assignment OptionalSemicolon",
"Statement : RoutineCall OptionalSemicolon",
"Statement : WhileLoop OptionalSemicolon",
"Statement : ForLoop OptionalSemicolon",
"Statement : IfStatement OptionalSemicolon",
"Statement : Print OptionalSemicolon",
"Statement : Return OptionalSemicolon",
"Statement : NEWLINE",
"Assignment : ModifiablePrimary ASSIGN Expression",
"RoutineCall : IDENTIFIER LPAREN Expressions RPAREN",
"Expressions : Expression",
"Expressions : Expression COMMA Expressions",
"WhileLoop : WHILE Expression LOOP Body END",
"ForLoop : FOR IDENTIFIER Range LOOP Body END",
"Range : IN ReverseTail Expression RANGE Expression",
"ReverseTail :",
"ReverseTail : REVERSE",
"IfStatement : IF Expression THEN Body ElseTail END",
"ElseTail :",
"ElseTail : ELSE Body",
"Expression : Relation Relations",
"Expression : SummandSign Relation Relations",
"Expression : NOT Relation Relations",
"Expression : NOT SummandSign Relation Relations",
"Relations :",
"Relations : LogicWord Relation Relations",
"Relations : LogicWord NOT Relation Relations",
"LogicWord : AND",
"LogicWord : OR",
"LogicWord : XOR",
"Relation : Simple SimpleTail",
"SimpleTail :",
"SimpleTail : RelationSign Simple SimpleTail",
"RelationSign : LESS",
"RelationSign : GREATER",
"RelationSign : LEQUALS",
"RelationSign : GEQUALS",
"RelationSign : EQUALS",
"RelationSign : NEQUALS",
"Simple : Factor FactorTail",
"FactorTail :",
"FactorTail : FactorSign Factor FactorTail",
"FactorSign : MULTIPLY",
"FactorSign : DIVIDE",
"FactorSign : REMAINDER",
"Factor : Summand SummandTail",
"SummandTail :",
"SummandTail : SummandSign Summand SummandTail",
"SummandSign : PLUS",
"SummandSign : MINUS",
"Summand : Primary",
"Summand : LPAREN Expression RPAREN",
"Primary : INTEGER_LITERAL",
"Primary : REAL_LITERAL",
"Primary : TRUE",
"Primary : FALSE",
"Primary : ModifiablePrimary",
"Primary : RoutineCall",
"ModifiablePrimary : IDENTIFIER ElementCall",
"ElementCall :",
"ElementCall : DOT IDENTIFIER ElementCall",
"ElementCall : LBRACKET Expression RBRACKET ElementCall",
"Print : PRINT LPAREN Expressions RPAREN",
};

//#line 302 "parser.y"

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
 par.dotest(16);
}
//#line 551 "Parser.java"
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
case 4:
//#line 66 "parser.y"
{System.out.println("Global Declaration parsed");}
break;
case 5:
//#line 67 "parser.y"
{System.out.println("Routine Declaration parsed");}
break;
case 6:
//#line 68 "parser.y"
{System.out.println("Newline parsed");}
break;
case 8:
//#line 73 "parser.y"
{System.out.println("Type Declaration parsed");}
break;
case 9:
//#line 77 "parser.y"
{{System.out.println("Variable declaration parsed");}}
break;
case 10:
//#line 78 "parser.y"
{System.out.println("Variable declaration parsed");}
break;
case 11:
//#line 81 "parser.y"
{System.out.println("Optional Newline parsed");}
break;
case 12:
//#line 82 "parser.y"
{System.out.println("Optional Semicolon parsed");}
break;
case 16:
//#line 95 "parser.y"
{System.out.println("Routine Declaration parsed");}
break;
case 30:
//#line 131 "parser.y"
{System.out.println("Record type parsed");}
break;
case 31:
//#line 135 "parser.y"
{System.out.println("Variable declarations parsed");}
break;
case 32:
//#line 136 "parser.y"
{System.out.println("Variable declaration parsed");}
break;
case 69:
//#line 228 "parser.y"
{System.out.println("Relation declared");}
break;
case 70:
//#line 233 "parser.y"
{System.out.println("SimpleTail parsed");}
break;
case 71:
//#line 234 "parser.y"
{System.out.println("SimpleTail parsed");}
break;
//#line 764 "Parser.java"
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
