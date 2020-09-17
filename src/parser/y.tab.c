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
   19,   20,   20,   20,   18,   13,   13,   21,   21,   22,
   22,   22,   22,   22,   22,   22,   22,   23,   24,   30,
   31,   31,   25,   26,   32,   33,   33,   27,   34,   34,
   10,   10,   36,   36,   37,   37,   37,   35,   39,   39,
   40,   40,   40,   40,   40,   40,   38,   42,   42,   43,
   43,   43,   41,   45,   45,   46,   46,   44,   44,   47,
   47,   47,   47,   47,   47,   29,   48,   48,   48,   28,
};
short yylen[] = {                                         2,
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
short yydefred[] = {                                      0,
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
short yydgoto[] = {                                       5,
    6,    7,  130,    9,   10,   11,   42,   18,   48,   28,
   51,  105,  131,  132,   52,   88,   43,   44,   45,  100,
  133,  134,  135,   29,  137,  138,  139,  140,   30,   56,
   90,  159,  166,  174,   31,   63,   64,   32,   71,   72,
   33,   76,   77,   34,   80,   81,   35,   57,
};
short yysindex[] = {                                   -182,
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
short yyrindex[] = {                                    181,
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
short yygindex[] = {                                      0,
  187,    0,    8,    0,  -73,    0,  -17,  153,  -77,  -26,
    0,    0,  -84,    0,  110,    0,    0,    0,    0,   35,
    0,    0,    0, -119,    0,    0,    0,    0, -117,    0,
    0,    0,    0,    0,  -22,  107,    0,  131,  115,    0,
  128,  118,    0,  130,  119,    0,    0,  -75,
};
#define YYTABLESIZE 277
short yytable[] = {                                      58,
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
short yycheck[] = {                                      26,
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
#line 304 "parser.y"

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
#line 57 "parser.y"
{System.out.println("Global Declarations parsed");}
break;
case 4:
#line 66 "parser.y"
{System.out.println("Global Declaration parsed");}
break;
case 5:
#line 67 "parser.y"
{System.out.println("Global Declaration parsed");}
break;
case 6:
#line 68 "parser.y"
{System.out.println("Newline parsed");}
break;
case 8:
#line 73 "parser.y"
{System.out.println("Type Declaration parsed");}
break;
case 9:
#line 77 "parser.y"
{{System.out.println("Variable declaration parsed");}}
break;
case 10:
#line 78 "parser.y"
{System.out.println("Variable declaration parsed");}
break;
case 11:
#line 81 "parser.y"
{System.out.println("Optional Newline parsed");}
break;
case 12:
#line 82 "parser.y"
{System.out.println("Optional Semicolon parsed");}
break;
case 16:
#line 95 "parser.y"
{System.out.println("Routine Declaration parsed");}
break;
case 31:
#line 134 "parser.y"
{System.out.println("Record type parsed");}
break;
case 32:
#line 138 "parser.y"
{System.out.println("Variable declarations parsed");}
break;
case 33:
#line 139 "parser.y"
{System.out.println("Variable declaration parsed");}
break;
case 68:
#line 231 "parser.y"
{System.out.println("Relation declared");}
break;
case 69:
#line 236 "parser.y"
{System.out.println("SimpleTail parsed");}
break;
case 70:
#line 237 "parser.y"
{System.out.println("SimpleTail parsed");}
break;
#line 677 "y.tab.c"
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
