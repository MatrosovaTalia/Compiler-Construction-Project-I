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
public final static short PLUS=307;
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
    1,    0,    2,    1,    2,    1,    1,    1,    6,    4,
    0,    2,    4,   10,    0,    3,    0,    2,    2,    0,
    2,    3,    1,    1,    1,    1,    1,    1,    1,    4,
    0,    2,    5,    0,    2,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    3,    2,    0,    3,    1,    3,
    5,    6,    4,    0,    1,    6,    0,    2,    2,    0,
    3,    1,    1,    1,    2,    0,    2,    1,    1,    1,
    1,    1,    1,    2,    0,    2,    1,    1,    1,    2,
    0,    2,    1,    1,    1,    3,    1,    1,    1,    1,
    1,    2,    0,    3,    4,    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    6,    0,    1,    0,    4,    0,    7,
    8,    0,    0,    0,    3,    5,    0,    0,    0,    0,
    0,    0,   87,   88,   89,   90,    0,   12,   91,    0,
    0,    0,    0,   85,   26,    0,   27,   28,   29,    0,
    0,   23,   24,   25,   10,   13,    0,    0,    0,    0,
    0,   92,    0,   62,   63,   64,   59,    0,   72,   73,
   69,   68,   70,   71,   65,    0,   77,   78,   79,   74,
    0,   83,   84,   80,    0,    0,    0,    0,    0,    0,
    0,   19,    0,    0,   86,    0,   67,   76,   82,    0,
    0,    0,    9,   22,    0,    0,   21,    0,   94,   61,
   32,   30,    0,   18,    0,   95,   33,    0,    0,    0,
    0,    0,   44,   36,    0,    0,   37,   38,   39,   40,
   41,   42,   43,    0,    0,   46,    0,    0,    0,    0,
    0,    0,   35,    0,   49,    0,    0,    0,    0,    0,
    0,    0,   14,   45,   48,    0,    0,   55,    0,    0,
    0,   96,   16,   50,   51,    0,    0,    0,    0,   53,
   52,   58,   56,
};
final static short yydgoto[] = {                          5,
    6,    7,  114,    9,   10,   11,   41,   19,   28,   48,
   96,  115,  132,   49,   82,   42,   43,   44,   91,  116,
  117,  118,  119,  120,  121,  122,  123,   29,  126,  136,
  139,  149,  159,   30,   57,   58,   31,   65,   66,   32,
   70,   71,   33,   74,   75,   34,   52,
};
final static short yysindex[] = {                      -170,
 -238, -226, -220,    0,    0,    0, -170,    0, -219,    0,
    0, -254, -214, -218,    0,    0, -163,   31, -211,   31,
 -174, -169,    0,    0,    0,    0, -163,    0,    0, -210,
 -128, -300, -250,    0,    0, -194,    0,    0,    0, -196,
 -165,    0,    0,    0,    0,    0, -188, -189, -173, -163,
 -145,    0, -166,    0,    0,    0,    0, -163,    0,    0,
    0,    0,    0,    0,    0, -163,    0,    0,    0,    0,
 -163,    0,    0,    0, -163, -119, -163, -143,   31, -147,
 -174,    0, -142, -169,    0, -210,    0,    0,    0, -119,
 -116, -141,    0,    0,   31, -111,    0, -169,    0,    0,
    0,    0,   31,    0,   55,    0,    0, -179, -163, -105,
 -163, -133,    0,    0, -127,   55,    0,    0,    0,    0,
    0,    0,    0, -137, -163,    0,  -96, -107,  -99, -163,
 -163,  -84,    0, -163,    0, -155,   55,  -92,  -88,   55,
 -100,  -95,    0,    0,    0, -163,  -67,    0,  -93,   55,
  -80,    0,    0,    0,    0, -163,  -61,   55,  -60,    0,
    0,    0,    0,
};
final static short yyrindex[] = {                       204,
    0,    0,    0,    0,    0,    0,  204,    0,    0,    0,
    0,  -79,    0,    0,    0,    0,    0,    0,    0,    0,
    0, -146,    0,    0,    0,    0,    0,    0,    0,   33,
    1,  -36,  -91,    0,    0,    0,    0,    0,    0,    0,
  -79,    0,    0,    0,    0,    0,    0,    0,  -82,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -54,    0,    0,    0, -165,
    0,    0,    0, -256,    0,   33,    0,    0,    0,  -54,
    0,    0,    0,    0,    0,    0,    0, -256,    0,    0,
    0,    0,    0,    0, -249,    0,    0, -197,    0,    0,
    0,    0,    0,    0,  -50, -157,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -48,  -77,    0, -260,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -48,
  -47,    0,    0,    0,    0,    0,    0,  -48,    0,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
  215,    0,    6,    0,  -64,    0,  -18,  182,  -27,    0,
    0,  -78,    0,  145,    0,    0,    0,    0,  138,    0,
    0,    0,    0,    0,    0,    0,    0,  -72,    0,    0,
    0,    0,    0,  171,  144,    0,  165,    0,    0,  161,
    0,    0,  158,    0,    0,    0,  -42,
};
final static int YYTABLESIZE=341;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         53,
   93,   46,   34,   93,   93,    8,   93,   17,   67,   68,
   69,   90,    8,   34,   93,   93,   93,   34,   12,   93,
   93,   93,   83,   93,   93,   90,   93,   93,   93,   93,
   13,   93,  124,   93,   93,   34,   14,  133,   18,   93,
   93,   99,   93,  124,   93,   93,   93,   20,   93,   92,
   93,   93,   93,   93,   93,  106,   72,   73,  147,   47,
   94,  151,   47,   47,  124,   47,   16,  124,   21,   54,
   55,  157,   56,   47,   45,   47,  104,  124,   47,  162,
   47,  127,   47,  129,  107,  124,   47,   47,   47,    1,
    2,   76,   77,   22,   23,   24,   17,  135,   80,   93,
   25,   26,  141,  142,   79,   34,  144,  125,    3,   50,
   93,   84,   51,   93,   93,    4,   93,   81,  154,   50,
   34,   85,   51,   27,   93,   93,   93,   34,  160,   93,
   93,   93,  145,   93,   93,  146,   93,   93,   93,   93,
    1,   93,   93,   93,   93,   95,  102,   98,  103,   93,
  105,  128,   93,  130,   93,   93,   93,  131,   93,  134,
   93,   93,   93,   93,   93,   81,  138,   59,   81,   81,
   60,   81,   61,   62,   63,  137,   64,  140,  143,   81,
   81,   81,  148,  150,   81,   81,   81,  152,   81,   81,
  153,   81,   81,   81,   81,  155,   81,  158,   81,   81,
  156,  161,  163,    2,   81,   20,   11,   81,   31,   81,
   81,   81,   15,   81,   34,   57,   54,   81,   81,   81,
   75,   15,   78,   75,   75,   97,   75,  101,   86,  100,
   87,   88,   89,    0,   75,   75,   75,    0,    0,   75,
   75,   75,    0,   75,   75,    0,   75,   75,   75,   75,
    0,   75,    0,   75,   75,    0,    0,   66,    0,   75,
   66,   66,   75,   66,   75,   75,   75,    0,   75,    0,
    0,   66,   66,   66,    0,    0,   66,   66,   66,    0,
   66,   66,    0,   66,   66,   66,   66,   35,   66,   60,
   66,   66,   60,   60,    0,   60,   36,   37,   38,   39,
   40,    0,    0,   60,   60,   60,    0,    0,   60,   60,
   60,  108,    0,    0,    1,    2,   60,   60,   60,    0,
   60,    0,   60,   60,    0,  109,    0,  110,    0,    0,
  111,    0,    0,    0,    0,    0,    0,    0,  112,    0,
  113,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         27,
  257,   20,  263,  260,  261,    0,  263,  262,  309,  310,
  311,   76,    7,  263,  271,  272,  273,  278,  257,  276,
  277,  278,   50,  280,  281,   90,  283,  284,  285,  286,
  257,  288,  105,  290,  291,  285,  257,  116,  293,  296,
  297,   84,  299,  116,  301,  302,  303,  262,  305,   77,
  307,  308,  309,  310,  311,   98,  307,  308,  137,  257,
   79,  140,  260,  261,  137,  263,  286,  140,  287,  280,
  281,  150,  283,  271,  286,  273,   95,  150,  276,  158,
  278,  109,  257,  111,  103,  158,  284,  285,  286,  260,
  261,  286,  289,  257,  258,  259,  262,  125,  288,  297,
  264,  265,  130,  131,  293,  263,  134,  287,  279,  289,
  257,  257,  292,  260,  261,  286,  263,  291,  146,  289,
  278,  288,  292,  287,  271,  272,  273,  285,  156,  276,
  277,  278,  288,  280,  281,  291,  283,  284,  285,  286,
  260,  288,  286,  290,  291,  293,  263,  290,  290,  296,
  262,  257,  299,  287,  301,  302,  303,  285,  305,  297,
  307,  308,  309,  310,  311,  257,  274,  296,  260,  261,
  299,  263,  301,  302,  303,  272,  305,  277,  263,  271,
  272,  273,  275,  272,  276,  277,  278,  288,  280,  281,
  286,  283,  284,  285,  286,  263,  288,  278,  290,  291,
  294,  263,  263,    0,  296,  288,  286,  299,  263,  301,
  302,  303,  263,  305,  263,  263,  294,  309,  310,  311,
  257,    7,   41,  260,  261,   81,  263,   90,   58,   86,
   66,   71,   75,   -1,  271,  272,  273,   -1,   -1,  276,
  277,  278,   -1,  280,  281,   -1,  283,  284,  285,  286,
   -1,  288,   -1,  290,  291,   -1,   -1,  257,   -1,  296,
  260,  261,  299,  263,  301,  302,  303,   -1,  305,   -1,
   -1,  271,  272,  273,   -1,   -1,  276,  277,  278,   -1,
  280,  281,   -1,  283,  284,  285,  286,  257,  288,  257,
  290,  291,  260,  261,   -1,  263,  266,  267,  268,  269,
  270,   -1,   -1,  271,  272,  273,   -1,   -1,  276,  277,
  278,  257,   -1,   -1,  260,  261,  284,  285,  286,   -1,
  288,   -1,  290,  291,   -1,  271,   -1,  273,   -1,   -1,
  276,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  284,   -1,
  286,
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
"\">=\"","PLUS","MINUS","MULTIPLY","DIVIDE","REMAINDER","SLCOMMENT","\"//\"",
"MLCOMMENT_START","\"/*\"","MLCOMMENT_END","\"*/\"",
};
final static String yyrule[] = {
"$accept : Program",
"Program : GlobalDeclarations",
"GlobalDeclarations :",
"GlobalDeclarations : GlobalDeclaration GlobalDeclarations",
"GlobalDeclaration : SimpleDeclaration",
"GlobalDeclaration : RoutineDeclaration SEPARATOR",
"GlobalDeclaration : SEPARATOR",
"SimpleDeclaration : VariableDeclaration",
"SimpleDeclaration : TypeDeclaration",
"VariableDeclaration : VAR IDENTIFIER COLON Type ExpressionTail SEPARATOR",
"VariableDeclaration : VAR IDENTIFIER ExpressionTail SEPARATOR",
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
"RecordType : RECORD SEPARATOR VariableDeclarations END",
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
 par.dotest(7);
}
//#line 542 "Parser.java"
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
//#line 719 "Parser.java"
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
