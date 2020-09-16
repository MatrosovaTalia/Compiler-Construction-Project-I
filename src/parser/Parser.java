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
   16,   16,   15,    7,    7,    7,    7,   17,   17,   17,
   19,   20,   20,   20,   18,   13,   13,   21,   21,   22,
   22,   22,   22,   22,   22,   22,   22,   23,   24,   30,
   31,   31,   25,   26,   32,   33,   33,   27,   34,   34,
   10,   10,   36,   36,   37,   37,   37,   35,   39,   39,
   40,   40,   40,   40,   40,   40,   38,   42,   42,   43,
   43,   43,   41,   45,   45,   46,   46,   44,   44,   47,
   47,   47,   47,   47,   47,   29,   48,   48,   48,   28,
};
final static short yylen[] = {                            2,
    1,    0,    2,    1,    1,    1,    1,    1,    6,    4,
    1,    1,    0,    2,    4,   10,    2,    0,    2,    2,
    0,    2,    3,    1,    1,    1,    1,    1,    1,    1,
    4,    0,    2,    2,    5,    0,    2,    1,    1,    2,
    2,    2,    2,    2,    2,    2,    1,    3,    2,    3,
    1,    3,    5,    6,    5,    0,    1,    6,    0,    2,
    2,    2,    0,    3,    1,    1,    1,    2,    0,    3,
    1,    1,    1,    1,    1,    1,    2,    0,    3,    1,
    1,    1,    2,    0,    3,    1,    1,    1,    3,    1,
    1,    1,    1,    1,    1,    2,    0,    3,    4,    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    6,    0,    1,    0,    4,    5,    7,
    8,    0,    0,    0,    3,    0,    0,    0,    0,    0,
    0,   90,   91,   92,   93,    0,    0,   14,   95,   94,
    0,    0,    0,    0,   88,   27,    0,   28,   29,   30,
    0,    0,   24,   25,   26,   11,   12,   10,   15,    0,
    0,    0,    0,    0,    0,   49,   96,    0,   62,   65,
   66,   67,   61,    0,   75,   76,   72,   71,   73,   74,
   68,    0,   80,   81,   82,   77,    0,   86,   87,   83,
    0,    0,    0,    0,    0,    0,    0,   20,   51,    0,
    0,    0,   89,    0,    0,    0,    0,    0,    0,    0,
    0,    9,   23,    0,    0,   22,   50,    0,    0,   98,
   64,   70,   79,   85,   34,   33,   31,    0,   19,    0,
   52,   99,   35,    0,    0,    0,    0,    0,   47,   38,
    0,    0,    0,   39,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   17,    0,   46,   37,   40,
   41,   42,   43,   44,   45,    0,    0,    0,    0,    0,
    0,   16,   48,    0,   57,    0,    0,    0,  100,   53,
    0,    0,    0,    0,    0,   54,   60,   58,   55,
};
final static short yydgoto[] = {                          5,
    6,    7,  130,    9,   10,   11,   42,   18,   48,   28,
   51,  105,  131,  132,   52,   88,   43,   44,   45,  100,
  133,  134,  135,   29,  137,  138,  139,  140,   30,   56,
   90,  159,  166,  174,   31,   63,   64,   32,   71,   72,
   33,   76,   77,   34,   80,   81,   35,   57,
};
final static short yysindex[] = {                      -182,
 -226, -222, -220,    0,    0,    0, -182,    0,    0,    0,
    0, -258, -250, -249,    0, -235, -176, -171, -176, -210,
 -183,    0,    0,    0,    0, -235, -193,    0,    0,    0,
 -270,  -58, -291, -184,    0,    0, -219,    0,    0,    0,
 -221, -178,    0,    0,    0,    0,    0,    0,    0, -208,
 -190, -186, -235, -235, -148,    0,    0, -168,    0,    0,
    0,    0,    0, -193,    0,    0,    0,    0,    0,    0,
    0, -193,    0,    0,    0,    0, -193,    0,    0,    0,
 -193, -254, -235, -171, -176, -181, -210,    0,    0, -212,
 -160, -205,    0, -270,  -58, -291, -184, -254, -254, -126,
 -152,    0,    0, -176, -114,    0,    0, -235, -205,    0,
    0,    0,    0,    0,    0,    0,    0, -176,    0,  -20,
    0,    0,    0, -235, -112, -235, -137, -235,    0,    0,
 -120, -171,  -20,    0, -171, -171, -171, -171, -171, -171,
 -144, -116, -105, -115, -235,    0, -171,    0,    0,    0,
    0,    0,    0,    0,    0, -235,  -20, -103, -102,  -20,
 -129,    0,    0,  -92,    0, -235,  -20, -100,    0,    0,
 -121,  -88,  -20,  -83, -235,    0,    0,    0,    0,
};
final static short yyrindex[] = {                       181,
    0,    0,    0,    0,    0,    0,  181,    0,    0,    0,
    0, -158,    0,    0,    0,    0,    0,    0,    0,    0,
 -145,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -18, -169,  -68, -104,    0,    0,    0,    0,    0,    0,
    0, -158,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -99,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -79,    0,    0,    0,  -76,    0,    0,    0,    0,
    0, -145,    0,  -18, -169,  -68, -104,  -79,  -79,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -145,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -74,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -245,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -74, -213,    0, -245,
    0,    0,    0,    0,    0,    0,  -74,  -71,    0,    0,
    0,    0,  -74,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  187,    0,    8,    0,  -73,    0,  -17,  153,  -77,  -26,
    0,    0,  -84,    0,  110,    0,    0,    0,    0,   35,
    0,    0,    0, -119,    0,    0,    0,    0, -117,    0,
    0,    0,    0,    0,  -22,  107,    0,  131,  115,    0,
  128,  118,    0,  130,  119,    0,    0,  -75,
};
final static int YYTABLESIZE=277;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         58,
  136,   49,  141,   16,   59,    1,  102,    8,   99,   60,
   61,   19,   62,  136,    8,  141,  110,   36,   73,   74,
   75,   21,   22,   23,   99,   99,   89,   91,   24,   25,
   12,   98,   36,  122,   13,   17,   14,  136,   20,  141,
  136,   94,  141,   56,   56,   56,   50,  136,  149,  141,
   56,   56,   26,  136,  148,  141,  101,  150,  151,  152,
  153,  154,  155,   21,   22,   23,   82,  103,   83,  162,
   24,   25,  164,   27,   56,  168,  107,    1,    2,  108,
   36,  121,  172,   16,   54,   85,  119,   55,  177,   37,
   38,   39,   40,   41,   26,   56,    3,  142,   86,  144,
  123,  146,   69,    4,   53,   87,   54,   69,   92,   55,
   69,   69,  104,   69,   46,   47,   69,   69,  161,   69,
   93,   69,   69,   78,   79,   69,   97,   13,   13,  163,
  109,   97,  115,  116,   97,   97,  117,   97,  118,  171,
   97,   97,  147,   97,  143,   97,   97,  120,  179,   97,
  145,   97,   97,  156,   97,  157,   97,   97,   97,  169,
   97,  160,   97,   97,   97,   97,   97,   84,  158,  167,
  170,  165,   84,  175,  176,   84,   84,  173,   84,  178,
    2,   84,   84,   32,   84,   18,   84,   84,   36,   21,
   84,   59,   84,   15,   84,   84,  106,   84,   84,   84,
  111,   84,   95,   78,   96,   84,   84,   84,   78,  112,
   97,   78,   78,  113,   78,  114,    0,   78,   78,    0,
   78,    0,   78,   78,    0,    0,   78,    0,   78,    0,
    0,   78,    0,   78,   78,   78,   21,   78,   65,    1,
    2,   66,    0,   67,   68,   69,    0,   70,    0,    0,
  124,    0,  125,   63,    0,  126,    0,    0,   63,    0,
    0,    0,    0,  127,  128,  129,    0,   63,   63,    0,
   63,    0,   63,   63,    0,    0,   63,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         26,
  120,   19,  120,  262,   27,  260,   84,    0,   82,  280,
  281,  262,  283,  133,    7,  133,   92,  263,  310,  311,
  312,  257,  258,  259,   98,   99,   53,   54,  264,  265,
  257,  286,  278,  109,  257,  294,  257,  157,  288,  157,
  160,   64,  160,  257,  258,  259,  257,  167,  133,  167,
  264,  265,  288,  173,  132,  173,   83,  135,  136,  137,
  138,  139,  140,  257,  258,  259,  286,   85,  290,  147,
  264,  265,  157,  309,  288,  160,  289,  260,  261,  292,
  257,  108,  167,  262,  290,  294,  104,  293,  173,  266,
  267,  268,  269,  270,  288,  309,  279,  124,  289,  126,
  118,  128,  272,  286,  288,  292,  290,  277,  257,  293,
  280,  281,  294,  283,  286,  287,  286,  287,  145,  289,
  289,  291,  292,  308,  309,  295,  272,  286,  287,  156,
  291,  277,   98,   99,  280,  281,  263,  283,  291,  166,
  286,  287,  263,  289,  257,  291,  292,  262,  175,  295,
  288,  297,  298,  298,  300,  272,  302,  303,  304,  289,
  306,  277,  308,  309,  310,  311,  312,  272,  274,  272,
  263,  275,  277,  295,  263,  280,  281,  278,  283,  263,
    0,  286,  287,  263,  289,  262,  291,  292,  263,  289,
  295,  263,  297,    7,   42,  300,   87,  302,  303,  304,
   94,  306,   72,  272,   77,  310,  311,  312,  277,   95,
   81,  280,  281,   96,  283,   97,   -1,  286,  287,   -1,
  289,   -1,  291,  292,   -1,   -1,  295,   -1,  297,   -1,
   -1,  300,   -1,  302,  303,  304,  257,  306,  297,  260,
  261,  300,   -1,  302,  303,  304,   -1,  306,   -1,   -1,
  271,   -1,  273,  272,   -1,  276,   -1,   -1,  277,   -1,
   -1,   -1,   -1,  284,  285,  286,   -1,  286,  287,   -1,
  289,   -1,  291,  292,   -1,   -1,  295,
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
"RoutineCall : IDENTIFIER RoutineCallTail",
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
"Expression : MINUS Relation",
"Relations :",
"Relations : LogicWord Relation Relations",
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
"Print : PRINT LPAREN Expression RPAREN",
};

//#line 304 "parser.y"

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
 par.dotest(2);
}
//#line 537 "Parser.java"
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
{System.out.println("Global Declaration parsed");}
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
case 31:
//#line 134 "parser.y"
{System.out.println("Record type parsed");}
break;
case 32:
//#line 138 "parser.y"
{System.out.println("Variable declarations parsed");}
break;
case 33:
//#line 139 "parser.y"
{System.out.println("Variable declaration parsed");}
break;
case 68:
//#line 231 "parser.y"
{System.out.println("Relation declared");}
break;
case 69:
//#line 236 "parser.y"
{System.out.println("SimpleTail parsed");}
break;
case 70:
//#line 237 "parser.y"
{System.out.println("SimpleTail parsed");}
break;
//#line 750 "Parser.java"
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
