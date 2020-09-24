%language "Java"
%output "YYParser.java"
%define lex_throws {}
%define api.parser.class {YYParser}
%define api.parser.public
%define api.package {parser}
%define api.value.type {ILexem}
%define parse.error custom
%define parse.trace


//%locations

%code imports {
import lexems.Identifier;
import lexems.ILexem;
import lexer.*;
import reader.Reader;
import simple.*;
import java.util.ArrayList;
}

%code {
    private static Declarations ast;
    public static Declarations makeAST(String i) {
        ast = new Declarations();
        MyLexer lexer = new MyLexer();
        Reader reader = new Reader();
        reader.read("tests/" + i + ".txt");
        lexer.tokenize(reader.sourceText);
        YYParser parser = new YYParser(lexer);
        if (!parser.parse()) {
            System.exit(1);
        }
        return ast;
    }
}
/* YACC Declarations */

// Identifiers & numbers
%token  <Identifier>IDENTIFIER
%token  INTEGER_LITERAL
%token  REAL_LITERAL
%token  TRUE FALSE
// Keywords
%token VAR TYPE IS END RECORD
%token INTEGER REAL BOOLEAN
%token ARRAY WHILE LOOP FOR IN REVERSE IF THEN ELSE ROUTINE AND
%token OR NOT XOR PRINT RETURN

// Separators
%token NEWLINE
%token SEMICOLON

// Delimiters
%token LPAREN
%token RPAREN
%token LBRACKET
%token RBRACKET
%token COMMA
%token DOT
%token COLON
%token RANGE

// Operator signs
%token EQUALS
%token ASSIGN
%token NEQUALS
%token GREATER
%token LESS
%token LEQUALS
%token GEQUALS
%token PLUS
%token MINUS
%token MULTIPLY
%token DIVIDE
%token REMAINDER


%type <Declarations> Program
%type <Declarations> GlobalDeclarations
%type <IDeclaration> GlobalDeclaration
%type <IDeclaration> SimpleDeclaration
%type <VariableDeclaration> VariableDeclaration
%type <TypeDeclaration> TypeDeclaration
%type <IType> Type
%type <PrimitiveType> PrimitiveType
%type <Identifier> Identifier

%start Program

%%

Program
    : GlobalDeclarations {ast = $1;}
    ;

GlobalDeclarations
    : /* empty */ { $$ = new Declarations();}
    | GlobalDeclaration GlobalDeclarations {$$ = $2; $2.add($1);}
    ;

GlobalDeclaration
    : SimpleDeclaration {$$ = $1;}
    //| RoutineDeclaration {$$ = $1;}
//    | NEWLINE {$$ = $$;}
    ;

SimpleDeclaration
    : VariableDeclaration { $$ = $1;}
    | TypeDeclaration { $$ = $1;}
    ;

VariableDeclaration
    : VAR Identifier COLON Type OptionalSemicolon {$$ = new VariableDeclaration($2, $4);}
    //| VAR Identifier COLON Type IS Expression OptionalSemicolon { $$ = new VariableDeclaration($2, $4, $6); }
    //| VAR Identifier IS Expression OptionalSemicolon {$$ = new VariableDeclaration($2, $4);}
    ;
OptionalSemicolon
    : /* empty */
    | SEMICOLON
    ;

TypeDeclaration
    : TYPE Identifier IS Type OptionalSemicolon{$$ = new TypeDeclaration($2, $4);}
    ;


Type
    : PrimitiveType { $$ = $1; }
//    | ArrayType { $$ = $1; }
    //| RecordType { $$ = $1; }
    | Identifier { $$ = $1; }
    ;

PrimitiveType
    : INTEGER { $$ = new PrimitiveType("integer");}
    | REAL { $$ = new PrimitiveType("real"); }
    | BOOLEAN { $$ = new PrimitiveType("boolean"); }
    ;

Identifier:
	IDENTIFIER {$$ = $1;}
	;

%%
//
//
//Lexer lexer;
//
//GlobalDeclarations ast;
//
//int yylex() {
//	Token tok = lexer.lex();
//	TokenType type = tok.getType();
//	System.out.println(tok.toString());
//    int code;
//    switch (type) {
//	case VAR -> code = VAR;
//	case TYPE -> code = TYPE;
//	case IS -> code = IS;
//	case END -> code = END;
//	case INTEGER -> code = INTEGER;
//	case REAL -> code = REAL;
//	case BOOLEAN -> code = BOOLEAN;
//	case TRUE -> code = TRUE;
//	case FALSE -> code = FALSE;
//	case RECORD -> code = RECORD;
//	case ARRAY -> code = ARRAY;
//	case WHILE -> code = WHILE;
//	case LOOP -> code = LOOP;
//	case FOR -> code = FOR;
//	case IN -> code = IN;
//	case REVERSE -> code = REVE	RSE;
//	case IF -> code = IF;
//	case THEN -> code = THEN;
//	case ELSE -> code = ELSE;
//	case ROUTINE -> code = ROUTINE;
//	case AND -> code = AND;
//	case OR -> code = OR;
//	case NOT -> code = NOT;
//	case XOR -> code = XOR;
//	case PRINT -> code = PRINT;
//	case RETURN -> code = RETURN;
//	case RANGE -> code = RANGE;
//	case ADD -> code = PLUS;
//	case MINUS -> code = MINUS;
//	case MULTIPLY -> code = MULTIPLY;
//	case DIVIDE -> code = DIVIDE;
//	case REMAINDER -> code = REMAINDER;
//	case RBRACKET -> code = RBRACKET;
//	case LBRACKET -> code = LBRACKET;
//	case LESS -> code = LESS;
//	case LEQUALS -> code = LEQUALS;
//	case GREATER -> code = GREATER;
//	case GEQUALS -> code = GEQUALS;
//	case EQUALS -> code = EQUALS;
//	case NEQUALS -> code = NEQUALS;
//	case DOT -> code = DOT;
//	case COMMA -> code = COMMA;
//	case ASSIGN -> code = ASSIGN;
//	case COLON -> code = COLON;
//	case LPAREN -> code = LPAREN;
//	case RPAREN -> code = RPAREN;
//	case SLCOMMENT -> code = SLCOMMENT;
//	case MLCOMMENT_START -> code = MLCOMMENT_START;
//	case MLCOMMENT_END -> code = MLCOMMENT_END;
//	case IDENTIFIER -> code = IDENTIFIER;
//	case INTEGER_LITERAL -> code = INTEGER_LITERAL;
//	case REAL_LITERAL -> code = REAL_LITERAL;
//	case NEWLINE -> code = NEWLINE;
//	case SEMICOLON -> code = SEMICOLON;
////	case EOF -> code = 0;
//	default -> code = -1;
//    }
//    return code;
//}
//
//void yyerror(String mes) {
//    System.out.println(mes);
//}
//
//void dotest(int i)
//{
//	Reader reader = new Reader();
//	this.lexer = new Lexer();
//	reader.read("tests/" + i + ".txt");
//	lexer.tokenize(reader.sourceText);
//	yyparse();
//}
//
//
//
//public static void main(String args[])
//{
// Parser par = new Parser(false);
// par.dotest(6);
//}