#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#line 2 "parser.y"
import java.lang.Math;
import java.io.*;
import java.util.StringTokenizer;
import lexer.*;
import reader.Reader;

#line 13 "y.tab.c"
#define IDENTIFIER 257
#define INTEGER_LITERAL 258
#define REAL_LITERAL 259
#define VAR 260
#define TYPE 261
#define IS 262
#define END 263
#define TRUE 264
#define FALSE 265
#define RECORD 266
#define INTEGER 267
#define REAL 268
#define BOOLEAN 269
#define ARRAY 270
#define WHILE 271
#define LOOP 272
#define FOR 273
#define IN 274
#define REVERSE 275
#define IF 276
#define THEN 277
#define ELSE 278
#define ROUTINE 279
#define AND 280
#define OR 281
#define NOT 282
#define XOR 283
#define PRINT 284
#define RETURN 285
#define NEWLINE 286
#define SEMICOLON 287
#define LPAREN 288
#define RPAREN 289
#define LBRACKET 290
#define RBRACKET 291
#define COMMA 292
#define DOT 293
#define COLON 294
#define RANGE 295
#define EQUALS 297
#define ASSIGN 298
#define NEQUALS 300
#define GREATER 302
#define LESS 303
#define LEQUALS 304
#define GEQUALS 306
#define PLUS 308
#define MINUS 309
#define MULTIPLY 310
#define DIVIDE 311
#define REMAINDER 312
#define SLCOMMENT 313
#define MLCOMMENT_START 315
#define MLCOMMENT_END 317
#define YYERRCODE 256
short yylhs[] = {                                        -1,
    0,    1,    1,    2,    2,    2,    3,    3,    5,    5,
    9,    9,    8,    8,    6,    4,   14,   12,   12,   11,
   16,   16,   15,    7,    7,    7,    7,   17,   17,   17,
   19,   20,   20,   18,   13,   13,   21,   21,   22,   22,
   22,   22,   22,   22,   22,   22,   23,   24,   30,   30,
   31,   31,   25,   26,   32,   33,   33,   27,   34,   34,
   10,   36,   36,   37,   37,   37,   35,   39,   39,   40,
   40,   40,   40,   40,   40,   38,   42,   42,   43,   43,
   43,   41,   45,   45,   46,   46,   44,   44,   47,   47,
   47,   47,   47,   29,   48,   48,   48,   28,
};
short yylen[] = {                                         2,
    1,    0,    2,    1,    1,    1,    1,    1,    6,    4,
    1,    1,    0,    2,    4,   10,    2,    0,    2,    2,
    0,    2,    3,    1,    1,    1,    1,    1,    1,    1,
    4,    0,    2,    5,    0,    2,    1,    1,    2,    2,
    2,    2,    2,    2,    2,    1,    3,    2,    0,    3,
    1,    3,    5,    6,    5,    0,    1,    6,    0,    2,
    2,    0,    3,    1,    1,    1,    2,    0,    2,    1,
    1,    1,    1,    1,    1,    2,    0,    2,    1,    1,
    1,    2,    0,    2,    1,    1,    1,    3,    1,    1,
    1,    1,    1,    2,    0,    3,    4,    4,
};
short yydefred[] = {                                      0,
    0,    0,    0,    6,    0,    1,    0,    4,    5,    7,
    8,    0,    0,    0,    3,    0,    0,    0,    0,    0,
    0,   89,   90,   91,   92,    0,   14,   93,    0,    0,
    0,    0,   87,   27,    0,   28,   29,   30,    0,    0,
   24,   25,   26,   11,   12,   10,   15,    0,    0,    0,
    0,    0,   94,    0,   64,   65,   66,   61,    0,   74,
   75,   71,   70,   72,   73,   67,    0,   79,   80,   81,
   76,    0,   85,   86,   82,    0,    0,    0,    0,    0,
    0,    0,   20,    0,    0,   88,    0,   69,   78,   84,
    0,    0,    0,    9,   23,    0,    0,   22,    0,   96,
   63,   33,   31,    0,   19,    0,   97,   34,    0,    0,
    0,    0,    0,    0,   46,   37,    0,    0,    0,   38,
    0,    0,    0,    0,    0,    0,    0,    0,   48,    0,
    0,    0,    0,   17,    0,   45,   36,   39,   40,   41,
   42,   43,   44,    0,   51,    0,    0,    0,    0,    0,
    0,   16,   47,   50,    0,    0,   57,    0,    0,    0,
   98,   52,   53,    0,    0,    0,    0,    0,   54,   60,
   58,   55,
};
short yydgoto[] = {                                       5,
    6,    7,  116,    9,   10,   11,   40,   18,   46,   27,
   49,   97,  117,  118,   50,   83,   41,   42,   43,   92,
  119,  120,  121,  122,  123,  124,  125,  126,   28,  129,
  146,  149,  158,  167,   29,   58,   59,   30,   66,   67,
   31,   71,   72,   32,   75,   76,   33,   53,
};
short yysindex[] = {                                   -242,
 -228, -200, -193,    0,    0,    0, -242,    0,    0,    0,
    0, -259, -251, -222,    0, -252, -198, -204, -198, -184,
 -237,    0,    0,    0,    0, -252,    0,    0, -203,  -40,
 -141, -268,    0,    0, -211,    0,    0,    0, -205, -170,
    0,    0,    0,    0,    0,    0,    0, -189, -188, -179,
 -252, -142,    0, -173,    0,    0,    0,    0, -252,    0,
    0,    0,    0,    0,    0,    0, -252,    0,    0,    0,
    0, -252,    0,    0,    0, -252, -138, -252, -204, -198,
 -169, -184,    0, -167, -237,    0, -203,    0,    0,    0,
 -138, -136, -163,    0,    0, -198, -132,    0, -237,    0,
    0,    0,    0, -198,    0,  -15,    0,    0, -262, -252,
 -124, -252, -152, -252,    0,    0, -125, -204,  -15,    0,
 -204, -204, -204, -204, -204, -204, -159, -252,    0, -128,
 -127, -129, -252,    0, -204,    0,    0,    0,    0,    0,
    0,    0,    0, -252,    0, -227,  -15, -122, -117,  -15,
 -139,    0,    0,    0, -252, -102,    0, -252,  -15, -119,
    0,    0,    0, -123,  -96,  -15,  -89, -252,    0,    0,
    0,    0,
};
short yyrindex[] = {                                    175,
    0,    0,    0,    0,    0,    0,  175,    0,    0,    0,
    0, -176,    0,    0,    0,    0,    0,    0,    0,    0,
 -104,    0,    0,    0,    0,    0,    0,    0, -196,   -5,
 -183,  -63,    0,    0,    0,    0,    0,    0,    0, -176,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -111,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -83,    0,    0,    0,
  -81,    0,    0,    0, -146,    0, -196,    0,    0,    0,
  -83,    0,    0,    0,    0,    0,    0,    0, -146,    0,
    0,    0,    0,    0,    0,  -79,    0,    0, -265,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -246,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -79, -249,    0, -246,
    0,    0,    0,    0,    0,    0,    0,    0,  -79,  -77,
    0,    0,    0,    0,    0,  -79,    0,    0,    0,    0,
    0,    0,
};
short yygindex[] = {                                      0,
  182,    0,   23,    0,  -57,    0,  -17,  150,  -75,  -26,
    0,    0,  -92,    0,  110,    0,    0,    0,    0,  103,
    0,    0,    0,    0,    0,    0,    0,    0, -105,    0,
    0,    0,    0,    0,  136,  114,    0,  130,    0,    0,
  131,    0,    0,  134,    0,    0,    0,  -61,
};
#define YYTABLESIZE 290
short yytable[] = {                                      54,
  127,   47,   16,   94,   21,   22,   23,   56,   56,   56,
   19,   24,   25,  127,   56,   56,   35,    1,    2,   91,
   49,   49,    8,  100,   84,  128,  137,   51,   12,    8,
   52,   35,   95,   91,   17,   26,    3,  107,   56,   73,
   74,  127,  136,    4,  127,  138,  139,  140,  141,  142,
  143,   93,   51,  127,  156,   52,   13,  160,   34,  152,
  127,  154,   95,   14,  155,   20,  165,   35,   36,   37,
   38,   39,   48,  170,   77,   62,   55,   56,  105,   57,
   62,   44,   45,  130,   78,  132,  108,  134,   77,   62,
   62,   16,   62,   77,   62,   62,   77,   77,   62,   77,
   81,  145,   77,   77,   80,   77,  151,   77,   77,   13,
   13,   77,   82,   77,   85,   86,   77,  153,   77,   77,
   77,    1,   77,   99,   96,   95,  103,  104,  162,  106,
   95,  164,  131,   95,   95,  133,   95,  135,  144,   95,
   95,  172,   95,  147,   95,   95,  148,  150,   95,  161,
   95,   95,  157,   95,  159,   95,   95,   95,  166,   95,
  163,   95,   95,   95,   95,   95,  169,   95,   68,   69,
   70,  168,   95,  171,    2,   95,   95,   21,   95,   32,
   18,   95,   95,   35,   95,   59,   95,   95,   15,   79,
   95,   98,   95,  102,   87,   95,   88,   95,   95,   95,
  101,   95,   89,   95,   95,   95,   95,   95,   83,   90,
    0,    0,    0,   83,    0,    0,   83,   83,    0,   83,
    0,    0,   83,   83,    0,   83,    0,   83,   83,    0,
    0,   83,    0,   83,    0,    0,   83,    0,   83,   83,
   83,  109,   83,    0,    1,    2,   83,   83,   83,    0,
    0,    0,    0,    0,    0,  110,   60,  111,    0,   61,
  112,   62,   63,   64,    0,   65,   68,    0,  113,  114,
  115,   68,    0,    0,   68,   68,    0,   68,    0,    0,
   68,   68,    0,   68,    0,   68,   68,    0,    0,   68,
};
short yycheck[] = {                                      26,
  106,   19,  262,   79,  257,  258,  259,  257,  258,  259,
  262,  264,  265,  119,  264,  265,  263,  260,  261,   77,
  286,  287,    0,   85,   51,  288,  119,  290,  257,    7,
  293,  278,  298,   91,  294,  288,  279,   99,  288,  308,
  309,  147,  118,  286,  150,  121,  122,  123,  124,  125,
  126,   78,  290,  159,  147,  293,  257,  150,  257,  135,
  166,  289,   80,  257,  292,  288,  159,  266,  267,  268,
  269,  270,  257,  166,  286,  272,  280,  281,   96,  283,
  277,  286,  287,  110,  290,  112,  104,  114,  272,  286,
  287,  262,  289,  277,  291,  292,  280,  281,  295,  283,
  289,  128,  286,  287,  294,  289,  133,  291,  292,  286,
  287,  295,  292,  297,  257,  289,  300,  144,  302,  303,
  304,  260,  306,  291,  294,  272,  263,  291,  155,  262,
  277,  158,  257,  280,  281,  288,  283,  263,  298,  286,
  287,  168,  289,  272,  291,  292,  274,  277,  295,  289,
  297,  298,  275,  300,  272,  302,  303,  304,  278,  306,
  263,  308,  309,  310,  311,  312,  263,  272,  310,  311,
  312,  295,  277,  263,    0,  280,  281,  289,  283,  263,
  262,  286,  287,  263,  289,  263,  291,  292,    7,   40,
  295,   82,  297,   91,   59,  300,   67,  302,  303,  304,
   87,  306,   72,  308,  309,  310,  311,  312,  272,   76,
   -1,   -1,   -1,  277,   -1,   -1,  280,  281,   -1,  283,
   -1,   -1,  286,  287,   -1,  289,   -1,  291,  292,   -1,
   -1,  295,   -1,  297,   -1,   -1,  300,   -1,  302,  303,
  304,  257,  306,   -1,  260,  261,  310,  311,  312,   -1,
   -1,   -1,   -1,   -1,   -1,  271,  297,  273,   -1,  300,
  276,  302,  303,  304,   -1,  306,  272,   -1,  284,  285,
  286,  277,   -1,   -1,  280,  281,   -1,  283,   -1,   -1,
  286,  287,   -1,  289,   -1,  291,  292,   -1,   -1,  295,
};
#define YYFINAL 5
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 318
#if YYDEBUG
char *yyname[] = {
"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,"'%'",0,0,"'('","')'","'*'","'+'","','","'-'","'.'","'/'",0,0,0,0,0,0,0,0,
0,0,"':'",0,"'<'","'='","'>'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,"'['",0,"']'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,"IDENTIFIER","INTEGER_LITERAL","REAL_LITERAL","VAR",
"TYPE","IS","END","TRUE","FALSE","RECORD","INTEGER","REAL","BOOLEAN","ARRAY",
"WHILE","LOOP","FOR","IN","REVERSE","IF","THEN","ELSE","ROUTINE","AND","OR",
"NOT","XOR","PRINT","RETURN","NEWLINE","SEMICOLON","LPAREN","RPAREN","LBRACKET",
"RBRACKET","COMMA","DOT","COLON","RANGE","\"..\"","EQUALS","ASSIGN","\":=\"",
"NEQUALS","\"/=\"","GREATER","LESS","LEQUALS","\"<=\"","GEQUALS","\">=\"",
"PLUS","MINUS","MULTIPLY","DIVIDE","REMAINDER","SLCOMMENT","\"//\"",
"MLCOMMENT_START","\"/*\"","MLCOMMENT_END","\"*/\"",
};
char *yyrule[] = {
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
#endif
#ifndef YYSTYPE
typedef int YYSTYPE;
#endif
#define yyclearin (yychar=(-1))
#define yyerrok (yyerrflag=0)
#ifdef YYSTACKSIZE
#ifndef YYMAXDEPTH
#define YYMAXDEPTH YYSTACKSIZE
#endif
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH 500
#endif
#endif
int yydebug;
int yynerrs;
int yyerrflag;
int yychar;
short *yyssp;
YYSTYPE *yyvsp;
YYSTYPE yyval;
YYSTYPE yylval;
short yyss[YYSTACKSIZE];
YYSTYPE yyvs[YYSTACKSIZE];
#define yystacksize YYSTACKSIZE
#line 302 "parser.y"

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
 par.dotest(3);
}
#line 473 "y.tab.c"
#define YYABORT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR goto yyerrlab
int
yyparse()
{
    register int yym, yyn, yystate;
#if YYDEBUG
    register char *yys;
    extern char *getenv();

    if (yys = getenv("YYDEBUG"))
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = (-1);

    yyssp = yyss;
    yyvsp = yyvs;
    *yyssp = yystate = 0;

yyloop:
    if (yyn = yydefred[yystate]) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, reading %d (%s)\n", yystate,
                    yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: state %d, shifting to state %d (%s)\n",
                    yystate, yytable[yyn],yyrule[yyn]);
#endif
        if (yyssp >= yyss + yystacksize - 1)
        {
            goto yyoverflow;
        }
        *++yyssp = yystate = yytable[yyn];
        *++yyvsp = yylval;
        yychar = (-1);
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;
#ifdef lint
    goto yynewerror;
#endif
yynewerror:
    yyerror("syntax error");
#ifdef lint
    goto yyerrlab;
#endif
yyerrlab:
    ++yynerrs;
yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yyssp]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: state %d, error recovery shifting\
 to state %d\n", *yyssp, yytable[yyn]);
#endif
                if (yyssp >= yyss + yystacksize - 1)
                {
                    goto yyoverflow;
                }
                *++yyssp = yystate = yytable[yyn];
                *++yyvsp = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: error recovery discarding state %d\n",
                            *yyssp);
#endif
                if (yyssp <= yyss) goto yyabort;
                --yyssp;
                --yyvsp;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, error recovery discards token %d (%s)\n",
                    yystate, yychar, yys);
        }
#endif
        yychar = (-1);
        goto yyloop;
    }
yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("yydebug: state %d, reducing by rule %d (%s)\n",
                yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    yyval = yyvsp[1-yym];
    switch (yyn)
    {
case 1:
#line 59 "parser.y"
{System.out.println("Global Declarations parsed");}
break;
case 3:
#line 64 "parser.y"
{System.out.println("Global Declaration parsed");}
break;
case 4:
#line 68 "parser.y"
{System.out.println("Simple Declaration parsed");}
break;
case 5:
#line 69 "parser.y"
{System.out.println("Routine Declaration parsed");}
break;
case 6:
#line 70 "parser.y"
{System.out.println("Routine Declaration parsed");}
break;
case 7:
#line 74 "parser.y"
{System.out.println("Variable Declaration parsed");}
break;
case 8:
#line 75 "parser.y"
{System.out.println("Type Declaration parsed");}
break;
#line 641 "y.tab.c"
    }
    yyssp -= yym;
    yystate = *yyssp;
    yyvsp -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: after reduction, shifting from state 0 to\
 state %d\n", YYFINAL);
#endif
        yystate = YYFINAL;
        *++yyssp = YYFINAL;
        *++yyvsp = yyval;
        if (yychar < 0)
        {
            if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("yydebug: state %d, reading %d (%s)\n",
                        YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("yydebug: after reduction, shifting from state %d \
to state %d\n", *yyssp, yystate);
#endif
    if (yyssp >= yyss + yystacksize - 1)
    {
        goto yyoverflow;
    }
    *++yyssp = yystate;
    *++yyvsp = yyval;
    goto yyloop;
yyoverflow:
    yyerror("yacc stack overflow");
yyabort:
    return (1);
yyaccept:
    return (0);
}
