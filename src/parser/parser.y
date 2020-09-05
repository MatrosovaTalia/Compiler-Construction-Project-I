%{
import java.lang.Math;
import java.io.*;
import java.util.StringTokenizer;
import lexer.Lexer;


%}

/* YACC Declarations */

// Identifiers & numbers
%token IDENTIFIER
%token INTEGERLITERAL
%token REALLITERAL

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
%token GEGUALS '>='
%token ADD '+'
%token MINUS '-'
%token MULTIPLY '*'
%token DIVIDE '/'
%token REMAINDER '%'

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
    | ':' Type VarDecEnd
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
    | ':' Type
    ;

Parameters
    : '(' ParameterList ')'
    ;

ParameterList
    : Parameter
    | ParameterList ',' Parameter
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
    : ARRAY '[' Expression ']' Type
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
    : IDENTIFIER '(' Expressions ')'
    ;

Expressions
    : Expression
    | Expressions ',' Expression
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
    : INTEGERLITERAL
    | REALLITERAL
    | TRUE
    | FALSE
    | ModifiablePrimary
    ;

ModifiablePrimary
    : IDENTIFIER ElementCall
    ;

ElementCall
    : /* empty */
    | '.' IDENTIFIER ElementCall
    | '[' Expression ']' ElementCall
    ;

%%

int yylex() {
    Lexer lexer = new Lexer();
    TokenType type = lexer.lex().getType();
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
	case EOF -> code = YYEOF;
	default -> code = -1;
    }
    return code;
}