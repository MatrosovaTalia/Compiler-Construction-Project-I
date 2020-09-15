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
   10,   36,   36,   37,   37,   37,   35,   39,   39,   40,
   40,   40,   40,   40,   40,   38,   42,   42,   43,   43,
   43,   41,   45,   45,   46,   46,   44,   44,   47,   47,
   47,   47,   47,   47,   29,   48,   48,   48,   28,
};
final static short yylen[] = {                            2,
    1,    0,    2,    1,    1,    1,    1,    1,    6,    4,
    1,    1,    0,    2,    4,   10,    2,    0,    2,    2,
    0,    2,    3,    1,    1,    1,    1,    1,    1,    1,
    4,    0,    2,    2,    5,    0,    2,    1,    1,    2,
    2,    2,    2,    2,    2,    2,    1,    3,    2,    3,
    1,    3,    5,    6,    5,    0,    1,    6,    0,    2,
    2,    0,    3,    1,    1,    1,    2,    0,    2,    1,
    1,    1,    1,    1,    1,    2,    0,    2,    1,    1,
    1,    2,    0,    2,    1,    1,    1,    3,    1,    1,
    1,    1,    1,    1,    2,    0,    3,    4,    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    6,    0,    1,    0,    4,    5,    7,
    8,    0,    0,    0,    3,    0,    0,    0,    0,    0,
    0,   89,   90,   91,   92,    0,   14,   94,   93,    0,
    0,    0,    0,   87,   27,    0,   28,   29,   30,    0,
    0,   24,   25,   26,   11,   12,   10,   15,    0,    0,
    0,    0,    0,    0,   49,   95,    0,   64,   65,   66,
   61,    0,   74,   75,   71,   70,   72,   73,   67,    0,
   79,   80,   81,   76,    0,   85,   86,   82,    0,    0,
    0,    0,    0,    0,    0,   20,   51,    0,    0,    0,
   88,    0,   69,   78,   84,    0,    0,    0,    0,    9,
   23,    0,    0,   22,   50,    0,    0,   97,   63,   34,
   33,   31,    0,   19,    0,   52,   98,   35,    0,    0,
    0,    0,    0,   47,   38,    0,    0,    0,   39,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   17,    0,   46,   37,   40,   41,   42,   43,   44,   45,
    0,    0,    0,    0,    0,    0,   16,   48,    0,   57,
    0,    0,    0,   99,   53,    0,    0,    0,    0,    0,
   54,   60,   58,   55,
};
final static short yydgoto[] = {                          5,
    6,    7,  125,    9,   10,   11,   41,   18,   47,   27,
   50,  103,  126,  127,   51,   86,   42,   43,   44,   98,
  128,  129,  130,   28,  132,  133,  134,  135,   29,   55,
   88,  154,  161,  169,   30,   61,   62,   31,   69,   70,
   32,   74,   75,   33,   78,   79,   34,   56,
};
final static short yysindex[] = {                      -254,
 -234, -228, -223,    0,    0,    0, -254,    0,    0,    0,
    0, -257, -259, -274,    0, -249,   -9, -182,   -9, -199,
 -212,    0,    0,    0,    0, -249,    0,    0,    0, -191,
 -232, -203, -180,    0,    0, -222,    0,    0,    0, -213,
 -179,    0,    0,    0,    0,    0,    0,    0, -208, -198,
 -193, -249, -249, -163,    0,    0, -187,    0,    0,    0,
    0, -249,    0,    0,    0,    0,    0,    0,    0, -249,
    0,    0,    0,    0, -249,    0,    0,    0, -249, -243,
 -249, -182,   -9, -184, -199,    0,    0, -244, -174, -211,
    0, -191,    0,    0,    0, -243, -243, -144, -168,    0,
    0,   -9, -136,    0,    0, -249, -211,    0,    0,    0,
    0,    0,   -9,    0, -173,    0,    0,    0, -249, -125,
 -249, -154, -249,    0,    0, -123, -182, -173,    0, -182,
 -182, -182, -182, -182, -182, -160, -129, -128, -126, -249,
    0, -182,    0,    0,    0,    0,    0,    0,    0,    0,
 -249, -173, -120, -115, -173, -140,    0,    0,  -94,    0,
 -249, -173, -108,    0,    0, -119,  -90, -173,  -88, -249,
    0,    0,    0,    0,
};
final static short yyrindex[] = {                       179,
    0,    0,    0,    0,    0,    0,  179,    0,    0,    0,
    0, -122,    0,    0,    0,    0,    0,    0,    0,    0,
 -150,    0,    0,    0,    0,    0,    0,    0,    0, -171,
  -40,  -73, -109,    0,    0,    0,    0,    0,    0,    0,
 -122,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -105,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -82,
    0,    0,    0,  -77,    0,    0,    0,    0,    0, -150,
    0, -171,    0,    0,    0,  -82,  -82,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -150,    0,    0,    0,
    0,    0,    0,    0,  -76,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -242,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -76, -246,    0, -242,    0,    0,    0,    0,    0,
    0,  -76,  -74,    0,    0,    0,    0,  -76,    0,    0,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  183,    0,   56,    0,  -56,    0,  -17,  151,  -81,  -26,
    0,    0, -124,    0,  111,    0,    0,    0,    0,   70,
    0,    0,    0,  -95,    0,    0,    0,    0,  -93,    0,
    0,    0,    0,    0,  136,  108,    0,  135,    0,    0,
  131,    0,    0,  130,    0,    0,    0,  -60,
};
final static int YYTABLESIZE=261;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         57,
  100,   48,   19,  144,   16,    1,    2,   21,   22,   23,
   56,   56,   56,   20,   24,   25,    1,   56,   56,  131,
   36,  136,   12,   97,    3,   87,   89,  159,   13,  108,
  163,    4,  131,   14,  136,   36,   17,  167,   26,   97,
   97,   56,   96,  172,  105,  143,  117,  106,  145,  146,
  147,  148,  149,  150,   99,    8,  131,   49,  136,  131,
  157,  136,    8,   80,   63,  101,  131,   64,  136,   65,
   66,   67,  131,   68,  136,   52,   81,   53,   53,  116,
   54,   54,   16,   21,  114,   83,    1,    2,   58,   59,
   84,   60,  137,   90,  139,  118,  141,  119,   85,  120,
   62,   91,  121,   45,   46,   62,   71,   72,   73,  102,
  122,  123,  124,  156,   62,   62,  107,   62,  112,   62,
   62,   96,  113,   62,  158,  115,   96,   76,   77,   96,
   96,  138,   96,  140,  166,   96,   96,  151,   96,  142,
   96,   96,  152,  174,   96,  153,   96,   96,  164,   96,
  155,   96,   96,   96,  160,   96,  162,   96,   96,   96,
   96,   96,   83,   13,   13,  110,  111,   83,  165,  168,
   83,   83,  171,   83,  173,  170,   83,   83,    2,   83,
   32,   83,   83,   21,   18,   83,   36,   83,   59,   15,
   83,   82,   83,   83,   83,  104,   83,   92,   77,  109,
   83,   83,   83,   77,   93,   94,   77,   77,   95,   77,
    0,    0,   77,   77,    0,   77,    0,   77,   77,    0,
    0,   77,    0,   77,    0,    0,   77,    0,   77,   77,
   77,   68,   77,    0,    0,    0,   68,    0,    0,   68,
   68,    0,   68,    0,    0,   68,   68,   35,   68,    0,
   68,   68,    0,    0,   68,    0,   36,   37,   38,   39,
   40,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         26,
   82,   19,  262,  128,  262,  260,  261,  257,  258,  259,
  257,  258,  259,  288,  264,  265,  260,  264,  265,  115,
  263,  115,  257,   80,  279,   52,   53,  152,  257,   90,
  155,  286,  128,  257,  128,  278,  294,  162,  288,   96,
   97,  288,  286,  168,  289,  127,  107,  292,  130,  131,
  132,  133,  134,  135,   81,    0,  152,  257,  152,  155,
  142,  155,    7,  286,  297,   83,  162,  300,  162,  302,
  303,  304,  168,  306,  168,  288,  290,  290,  290,  106,
  293,  293,  262,  257,  102,  294,  260,  261,  280,  281,
  289,  283,  119,  257,  121,  113,  123,  271,  292,  273,
  272,  289,  276,  286,  287,  277,  310,  311,  312,  294,
  284,  285,  286,  140,  286,  287,  291,  289,  263,  291,
  292,  272,  291,  295,  151,  262,  277,  308,  309,  280,
  281,  257,  283,  288,  161,  286,  287,  298,  289,  263,
  291,  292,  272,  170,  295,  274,  297,  298,  289,  300,
  277,  302,  303,  304,  275,  306,  272,  308,  309,  310,
  311,  312,  272,  286,  287,   96,   97,  277,  263,  278,
  280,  281,  263,  283,  263,  295,  286,  287,    0,  289,
  263,  291,  292,  289,  262,  295,  263,  297,  263,    7,
  300,   41,  302,  303,  304,   85,  306,   62,  272,   92,
  310,  311,  312,  277,   70,   75,  280,  281,   79,  283,
   -1,   -1,  286,  287,   -1,  289,   -1,  291,  292,   -1,
   -1,  295,   -1,  297,   -1,   -1,  300,   -1,  302,  303,
  304,  272,  306,   -1,   -1,   -1,  277,   -1,   -1,  280,
  281,   -1,  283,   -1,   -1,  286,  287,  257,  289,   -1,
  291,  292,   -1,   -1,  295,   -1,  266,  267,  268,  269,
  270,
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
"Primary : RoutineCall",
"ModifiablePrimary : IDENTIFIER ElementCall",
"ElementCall :",
"ElementCall : DOT IDENTIFIER ElementCall",
"ElementCall : LBRACKET Expression RBRACKET ElementCall",
"Print : PRINT LPAREN Expression RPAREN",
};

//#line 303 "parser.y"

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
 par.dotest(6);
}
//#line 534 "Parser.java"
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
//#line 735 "Parser.java"
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
