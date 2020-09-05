%{
import java.lang.Math;
import java.io.*;
import java.util.StringTokenizer;
import lexer.*;
import reader.Reader;

%}

/* YACC Declarations */

// Identifiers & numbers
%token IDENTIFIER
%token INTEGER_LITERAL
%token REAL_LITERAL

// Keywords
%token VAR TYPE IS END TRUE FALSE RECORD INTEGER REAL BOOLEAN
%token ARRAY WHILE LOOP FOR IN REVERSE IF THEN ELSE ROUTINE AND
%token OR NOT XOR PRINT RETURN

// Separators
%token SEPARATOR

// Delimiters
%token LPAREN '('
%token RPAREN ')'
%token LBRACKET '['
%token RBRACKET ']'
%token COMMA ','
%token DOT '.'
%token COLON ':'
%token  RANGE '..'

// Operator signs
%token EQUALS '='
%token ASSIGN ':='
%token NEQUALS '/='
%token GREATER '>'
%token LESS '<'
%token LEQUALS '<='
%token GEQUALS '>='
%token ADD '+'
%token MINUS '-'
%token MULTIPLY '*'
%token DIVIDE '/'
%token REMAINDER '%'
%token SLCOMMENT '//'
%token MLCOMMENT_START '/*'
%token MLCOMMENT_END '*/'

%start Program

%%

Program
    : /* empty */
    | SimpleDeclaration
    | RoutineDeclaration
    ;

SimpleDeclaration
    : VariableDeclaration
    | TypeDeclaration
    ;

/*VariableDeclaration
    : VAR IDENTIFIER IS Expression
    | VAR IDENTIFIER ':' Type IS Expression
    | VAR IDENTIFIER ':' Type
    ;*/

/////
VariableDeclaration
    : VAR IDENTIFIER VarDecMid
    ;

VarDecMid
    : IS Expression
    | COLON Type VarDecEnd
    ;

VarDecEnd
    : /* empty */
    | IS Expression
    ;
/////

TypeDeclaration
    : TYPE IDENTIFIER IS Type
    ;

RoutineDeclaration
    : ROUTINE IDENTIFIER Parameters SetType IS Body END
    ;

SetType
    : /* empty */
    | COLON Type
    ;

Parameters
    : LPAREN ParameterList RPAREN
    ;

ParameterList
    : Parameter
    | ParameterList COMMA Parameter
    ;

Parameter
    : Type IDENTIFIER
    ;

Type
    : PrimitiveType
    | ArrayType
    | RecordType
    | IDENTIFIER
    ;

PrimitiveType
    : INTEGER
    | REAL
    | BOOLEAN
    ;

RecordType
    : RECORD Variables END
    ;

Variables
    : VariableDeclaration
    | Variables VariableDeclaration
    ;

ArrayType
    : ARRAY LBRACKET Expression RBRACKET Type
    ;

Body
    : BodyList
    | Body BodyList
    ;

BodyList
    : SimpleDeclaration
    | Statement
    ;

Statement
    : Assignment
    | RoutineCall
    | WhileLoop
    | ForLoop
    | IfStatement
    ;

Assignment
    : ModifiablePrimary ':=' Expression
    ;

RoutineCall
    : IDENTIFIER LPAREN Expressions RPAREN
    ;

Expressions
    : Expression
    | Expressions COMMA Expression
    ;

WhileLoop
    : WHILE Expression LOOP Body END
    ;

ForLoop
    : FOR IDENTIFIER Range LOOP Body END
    ;

Range
    : IN Expression '..' Expression
    | IN REVERSE Expression '..' Expression
    ;

IfStatement
    : IF Expression THEN Body ElseTail END 
    ;

ElseTail
    : /* empty */
    | ELSE Body
    ;

Expression
    : Relations
    ;

/*Relations
    : Relation
    | Relations LogicWord Relation
    ;*/

/////
Relations
    : BeginRelation Relation
    ;

BeginRelation
    : /* empty */
    | Relations LogicWord
    ;
/////

LogicWord
    : AND
    | OR
    | XOR
    ;

Relation
    : Simples
    ;

Simples
    : /* empty */
    | Simple
    | Simples RelationSign Simple
    ;

RelationSign
    : '<' | '>' | '<=' | '>=' | '=' | '/='
    ;

Simple
    : Factors
    ;

Factors
    : Factor
    | Factors FactorSign Factor
    ;

FactorSign
    : '*' | '/' | '%'
    ;

Factor
    : Summands
    ;

Summands
    : Summand
    | Summands SummandSign Summand
    ;

SummandSign
    : '+' | '-'
    ;

Summand
    : Primary
    | Exp
    ;

Exp
    : '(' Expression ')'
    ;

Primary
    : INTEGER_LITERAL
    | REAL_LITERAL
    | TRUE
    | FALSE
    | ModifiablePrimary
    ;

ModifiablePrimary
    : IDENTIFIER ElementCall
    ;

ElementCall
    : /* empty */
    | DOT IDENTIFIER ElementCall
    | LBRACKET Expression RBRACKET ElementCall
    ;

%%

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

void dotest()
{
	Reader reader = new Reader();
	this.lexer = new Lexer();
	int i = 3;
	reader.read("tests/" + i + ".txt");
	lexer.tokenize(reader.sourceText);
	yyparse();
}



public static void main(String args[])
{
 Parser par = new Parser(false);
 par.dotest();
}