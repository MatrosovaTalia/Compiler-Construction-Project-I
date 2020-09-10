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
   30,   31,   31,   25,   26,   32,   33,   33,   27,   34,
   34,   10,   36,   36,   37,   37,   37,   35,   39,   39,
   40,   40,   40,   40,   40,   40,   38,   42,   42,   43,
   43,   43,   41,   45,   45,   46,   46,   44,   44,   47,
   47,   47,   47,   47,   29,   48,   48,   48,   28,
};
final static short yylen[] = {                            2,
    1,    0,    2,    1,    1,    1,    1,    1,    6,    4,
    1,    1,    0,    2,    4,   10,    2,    0,    2,    2,
    0,    2,    3,    1,    1,    1,    1,    1,    1,    1,
    4,    0,    2,    2,    5,    0,    2,    1,    1,    2,
    2,    2,    2,    2,    2,    2,    1,    3,    2,    0,
    3,    1,    3,    5,    6,    5,    0,    1,    6,    0,
    2,    2,    0,    3,    1,    1,    1,    2,    0,    2,
    1,    1,    1,    1,    1,    1,    2,    0,    2,    1,
    1,    1,    2,    0,    2,    1,    1,    1,    3,    1,
    1,    1,    1,    1,    2,    0,    3,    4,    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    6,    0,    1,    0,    4,    5,    7,
    8,    0,    0,    0,    3,    0,    0,    0,    0,    0,
    0,   90,   91,   92,   93,    0,   14,   94,    0,    0,
    0,    0,   88,   27,    0,   28,   29,   30,    0,    0,
   24,   25,   26,   11,   12,   10,   15,    0,    0,    0,
    0,    0,   95,    0,   65,   66,   67,   62,    0,   75,
   76,   72,   71,   73,   74,   68,    0,   80,   81,   82,
   77,    0,   86,   87,   83,    0,    0,    0,    0,    0,
    0,    0,   20,    0,    0,   89,    0,   70,   79,   85,
    0,    0,    0,    0,    9,   23,    0,    0,   22,    0,
   97,   64,   34,   33,   31,    0,   19,    0,   98,   35,
    0,    0,    0,    0,    0,    0,   47,   38,    0,    0,
    0,   39,    0,    0,    0,    0,    0,    0,    0,    0,
   49,    0,    0,    0,    0,   17,    0,   46,   37,   40,
   41,   42,   43,   44,   45,    0,   52,    0,    0,    0,
    0,    0,    0,   16,   48,   51,    0,    0,   58,    0,
    0,    0,   99,   53,   54,    0,    0,    0,    0,    0,
   55,   61,   59,   56,
};
final static short yydgoto[] = {                          5,
    6,    7,  118,    9,   10,   11,   40,   18,   46,   27,
   49,   98,  119,  120,   50,   83,   41,   42,   43,   93,
  121,  122,  123,  124,  125,  126,  127,  128,   28,  131,
  148,  151,  160,  169,   29,   58,   59,   30,   66,   67,
   31,   71,   72,   32,   75,   76,   33,   53,
};
final static short yysindex[] = {                      -242,
 -246, -240, -216,    0,    0,    0, -242,    0,    0,    0,
    0, -258, -215, -281,    0, -249, -211, -174, -211, -193,
 -255,    0,    0,    0,    0, -249,    0,    0, -206,  -39,
 -140, -191,    0,    0, -214,    0,    0,    0, -239, -180,
    0,    0,    0,    0,    0,    0,    0, -209, -202, -198,
 -249, -154,    0, -182,    0,    0,    0,    0, -249,    0,
    0,    0,    0,    0,    0,    0, -249,    0,    0,    0,
    0, -249,    0,    0,    0, -249, -254, -249, -174, -211,
 -179, -193,    0, -167, -255,    0, -206,    0,    0,    0,
 -254, -254, -131, -156,    0,    0, -211, -124,    0, -255,
    0,    0,    0,    0,    0, -211,    0,  -14,    0,    0,
 -217, -249, -117, -249, -147, -249,    0,    0, -114, -174,
  -14,    0, -174, -174, -174, -174, -174, -174, -152, -249,
    0, -122, -119, -125, -249,    0, -174,    0,    0,    0,
    0,    0,    0,    0,    0, -249,    0, -208,  -14, -112,
 -115,  -14, -128,    0,    0,    0, -249,  -90,    0, -249,
  -14, -102,    0,    0,    0, -120,  -84,  -14,  -82, -249,
    0,    0,    0,    0,
};
final static short yyrindex[] = {                       182,
    0,    0,    0,    0,    0,    0,  182,    0,    0,    0,
    0, -160,    0,    0,    0,    0,    0,    0,    0,    0,
 -103,    0,    0,    0,    0,    0,    0,    0, -194,   -4,
 -181,  -62,    0,    0,    0,    0,    0,    0,    0, -160,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -104,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -76,    0,    0,    0,
  -72,    0,    0,    0, -144,    0, -194,    0,    0,    0,
  -76,  -76,    0,    0,    0,    0,    0,    0,    0, -144,
    0,    0,    0,    0,    0,    0,    0,  -70,    0,    0,
 -238,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -250,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -70, -235,
    0, -250,    0,    0,    0,    0,    0,    0,    0,    0,
  -70,  -68,    0,    0,    0,    0,    0,  -70,    0,    0,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  184,    0,   33,    0,  -65,    0,  -17,  156,  -58,  -26,
    0,    0, -118,    0,  116,    0,    0,    0,    0,   38,
    0,    0,    0,    0,    0,    0,    0,    0, -107,    0,
    0,    0,    0,    0,  143,  117,    0,  144,    0,    0,
  140,    0,    0,  137,    0,    0,    0,  -80,
};
final static int YYTABLESIZE=291;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         54,
  129,   47,  139,   16,  101,    1,   20,   21,   22,   23,
   12,   92,   36,  129,   24,   25,   13,    1,    2,  109,
   95,   57,   57,   57,   84,   92,   92,   36,   57,   57,
  158,   91,    8,  162,   51,   17,    3,   52,   26,    8,
   14,  129,  167,    4,  129,   34,   19,   50,   50,  172,
   78,   94,   57,  129,   35,   36,   37,   38,   39,   96,
  129,  138,   96,   48,  140,  141,  142,  143,  144,  145,
  130,   77,   51,   55,   56,   52,   57,   63,  154,  107,
  156,   16,   63,  157,   80,  132,   81,  134,  110,  136,
   78,   63,   63,   82,   63,   78,   63,   63,   78,   78,
   63,   78,   85,  147,   78,   78,   86,   78,  153,   78,
   78,   44,   45,   78,   97,   78,   73,   74,   78,  155,
   78,   78,   78,  100,   78,   13,   13,   96,  103,  104,
  164,  105,   96,  166,  106,   96,   96,  108,   96,  133,
  135,   96,   96,  174,   96,  146,   96,   96,  137,  149,
   96,  152,   96,   96,  150,   96,  161,   96,   96,   96,
  163,   96,  159,   96,   96,   96,   96,   96,   96,   68,
   69,   70,  165,   96,  170,  168,   96,   96,  171,   96,
  173,    2,   96,   96,   21,   96,   32,   96,   96,   18,
   15,   96,   36,   96,   60,   79,   96,   99,   96,   96,
   96,   87,   96,  102,   96,   96,   96,   96,   96,   84,
   88,   89,   90,    0,   84,    0,    0,   84,   84,    0,
   84,    0,    0,   84,   84,    0,   84,    0,   84,   84,
    0,    0,   84,    0,   84,    0,    0,   84,    0,   84,
   84,   84,  111,   84,    0,    1,    2,   84,   84,   84,
    0,    0,    0,    0,    0,    0,  112,   60,  113,    0,
   61,  114,   62,   63,   64,    0,   65,   69,    0,  115,
  116,  117,   69,    0,    0,   69,   69,    0,   69,    0,
    0,   69,   69,    0,   69,    0,   69,   69,    0,    0,
   69,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         26,
  108,   19,  121,  262,   85,  260,  288,  257,  258,  259,
  257,   77,  263,  121,  264,  265,  257,  260,  261,  100,
   79,  257,  258,  259,   51,   91,   92,  278,  264,  265,
  149,  286,    0,  152,  290,  294,  279,  293,  288,    7,
  257,  149,  161,  286,  152,  257,  262,  286,  287,  168,
  290,   78,  288,  161,  266,  267,  268,  269,  270,  298,
  168,  120,   80,  257,  123,  124,  125,  126,  127,  128,
  288,  286,  290,  280,  281,  293,  283,  272,  137,   97,
  289,  262,  277,  292,  294,  112,  289,  114,  106,  116,
  272,  286,  287,  292,  289,  277,  291,  292,  280,  281,
  295,  283,  257,  130,  286,  287,  289,  289,  135,  291,
  292,  286,  287,  295,  294,  297,  308,  309,  300,  146,
  302,  303,  304,  291,  306,  286,  287,  272,   91,   92,
  157,  263,  277,  160,  291,  280,  281,  262,  283,  257,
  288,  286,  287,  170,  289,  298,  291,  292,  263,  272,
  295,  277,  297,  298,  274,  300,  272,  302,  303,  304,
  289,  306,  275,  308,  309,  310,  311,  312,  272,  310,
  311,  312,  263,  277,  295,  278,  280,  281,  263,  283,
  263,    0,  286,  287,  289,  289,  263,  291,  292,  262,
    7,  295,  263,  297,  263,   40,  300,   82,  302,  303,
  304,   59,  306,   87,  308,  309,  310,  311,  312,  272,
   67,   72,   76,   -1,  277,   -1,   -1,  280,  281,   -1,
  283,   -1,   -1,  286,  287,   -1,  289,   -1,  291,  292,
   -1,   -1,  295,   -1,  297,   -1,   -1,  300,   -1,  302,
  303,  304,  257,  306,   -1,  260,  261,  310,  311,  312,
   -1,   -1,   -1,   -1,   -1,   -1,  271,  297,  273,   -1,
  300,  276,  302,  303,  304,   -1,  306,  272,   -1,  284,
  285,  286,  277,   -1,   -1,  280,  281,   -1,  283,   -1,
   -1,  286,  287,   -1,  289,   -1,  291,  292,   -1,   -1,
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
 par.dotest(7);
}
//#line 540 "Parser.java"
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
{System.out.println("Global Declaration parsed");}
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
//#line 741 "Parser.java"
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
