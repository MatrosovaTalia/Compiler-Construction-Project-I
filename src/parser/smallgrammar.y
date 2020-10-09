%language "Java"
%output "YYParser.java"
%define lex_throws {}
%define api.parser.class {YYParser}
%define api.parser.public
%define api.package {parser}
%define api.value.type {ILexem}
%define parse.error custom
%define parse.trace


%locations

%code imports {
//import lexems.Identifier;
//import lexems.ILexem;
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
%token  <IntegerLiteral>INTEGER_LITERAL
%token  <RealLiteral>REAL_LITERAL
%token  <BooleanLiteral>TRUE
%token 	<BooleanLiteral>FALSE

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
%type <RoutineDeclaration>RoutineDeclaration
%type <Parameters>Parameters
%type <Parameter>ParameterDeclaration
%type <Body>Body
%type <IStatement>BodyDeclaration
%type <IType> Type
%type <PrimitiveType> PrimitiveType
%type <Identifier> Identifier
%type <IExpression>Expression
%type <RecordType>RecordType
%type <RecordType>RecordDeclarations
%type <ArrayType>ArrayType
%type <WhileStatement>WhileStatement
%type <IStatement>Statement
%type <ForLoop>ForLoop
%type <IfStatement>IfStatement
%type <Return>Return
%type <Print>Print
%type <Expressions>Expressions
%type <RoutineCall>RoutineCall
%type <ModifiablePrimary>ModifiablePrimary
%type <ElementCall>ElementCall
%type <Assignment>Assignment


%left MINUS PLUS
%left MULTIPLY DIVIDE REMAINDER
%left AND OR XOR
%left LESS GREATER EQUALS NEQUALS LEQUALS GEQUALS


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
    | RoutineDeclaration {$$ = $1;}
    ;

SimpleDeclaration
    : VariableDeclaration { $$ = $1;}
    | TypeDeclaration { $$ = $1;}
    ;

VariableDeclaration
    : VAR Identifier COLON Type OptionalSemicolon {$$ = new VariableDeclaration($2, $4, null);}
    | VAR Identifier COLON Type IS Expression OptionalSemicolon { $$ = new VariableDeclaration($2, $4, $6); }
    | VAR Identifier IS Expression OptionalSemicolon {$$ = new VariableDeclaration($2, null, $4);}
    ;

TypeDeclaration
    : TYPE Identifier IS Type OptionalSemicolon{$$ = new TypeDeclaration($2, $4);}
    ;

RoutineDeclaration
    : ROUTINE Identifier LPAREN Parameters RPAREN COLON Type IS Body END  {$$ = new RoutineDeclaration($2, $4, $7, $9);}
    | ROUTINE Identifier LPAREN Parameters RPAREN IS Body END  {$$ = new RoutineDeclaration($2, $4, null, $7);}
    ;

Parameters
    : ParameterDeclaration { Parameters x = new Parameters(); x.add($1); $$ = x;}
    | ParameterDeclaration COMMA Parameters { $$ = $3; $3.add($1); }
    ;


ParameterDeclaration
    : Identifier COLON Type { $$ = new Parameter($1, $3); }
    ;

Body
    : /* empty */ { $$ = new Body();}
    | BodyDeclaration Body {$$ = $2; $2.add($1);}
    ;

BodyDeclaration
    : VariableDeclaration {$$ = new LocalVariableDeclaration($1);}
    | Statement {$$ = $1;}
    ;

Statement
    : Assignment  {$$ = $1;}
    | RoutineCall  {$$ = $1; }
    | WhileStatement  {$$ = $1;}
    | ForLoop  {$$ = $1;}
    | IfStatement {$$ = $1;}
    | Print  {$$ = $1;}
    | Return  {$$ = $1;}
    ;

Assignment
    : ModifiablePrimary ASSIGN Expression {$$ = new Assignment($1, $3);}
    ;


RoutineCall
    : Identifier LPAREN Expressions RPAREN {$$ = new RoutineCall($1, $3); }
    ;

WhileStatement
    : WHILE Expression LOOP Body END {$$ = new WhileStatement($2, $4); }
    ;

ForLoop
    : FOR Identifier IN Expression RANGE Expression LOOP Body END
    {$$ = new ForLoop($2, $4, $6, $8, false);}
    | FOR Identifier IN REVERSE Expression RANGE Expression LOOP Body END
    {$$ = new ForLoop($2, $5, $7, $9, true);}
    ;

IfStatement
    : IF Expression THEN Body END {$$ = new IfStatement($2, $4, null);}
    | IF Expression THEN Body ELSE Body END {$$ = new IfStatement($2, $4, $6);}
    ;

Return
    : RETURN Expression {$$ = new Return($2);}
    ;

Print
    : PRINT LPAREN Expressions RPAREN {$$ = new Print($3);}
    ;

Expressions
    : Expression { Expressions x = new Expressions(); x.add($1); $$ = x; }
    | Expression COMMA Expressions { $$ = $3; $3.add($1); }
    ;

Expression
    : INTEGER_LITERAL {$$ = $1;}
    | REAL_LITERAL {$$ = $1;}
    | TRUE {$$ = $1;}
    | FALSE {$$ = $1;}
    | RoutineCall {$$ = $1;}
    | ModifiablePrimary {$$ = $1;}
    | LPAREN Expression RPAREN  { $$ = $2; }
    | Expression PLUS Expression {$$ = new BinaryExpression("PLUS", $1, $3);}
    | Expression MINUS Expression {$$ = new BinaryExpression("MINUS", $1, $3);}
    | Expression MULTIPLY Expression {$$ = new BinaryExpression("MULTIPLY", $1, $3);}
    | Expression DIVIDE Expression {$$ = new BinaryExpression("DIVIDE", $1, $3);}
    | Expression REMAINDER Expression {$$ = new BinaryExpression("REMAINDER", $1, $3);}
    | Expression AND Expression {$$ = new BinaryExpression("AND", $1, $3);}
    | Expression OR Expression {$$ = new BinaryExpression("OR", $1, $3);}
    | Expression XOR Expression  {$$ = new BinaryExpression("XOR", $1, $3);}
    | Expression LESS Expression {$$ = new BinaryExpression("LESS", $1, $3);}
    | Expression GREATER Expression {$$ = new BinaryExpression("GREATER", $1, $3);}
    | Expression LEQUALS Expression {$$ = new BinaryExpression("LEQUALS", $1, $3);}
    | Expression GEQUALS Expression {$$ = new BinaryExpression("GEQUALS", $1, $3);}
    | Expression EQUALS Expression {$$ = new BinaryExpression("EQUALS", $1, $3);}
    | Expression NEQUALS Expression {$$ = new BinaryExpression("NEQUALS", $1, $3);}
    ;



OptionalSemicolon
    : /* empty */
    | SEMICOLON
    ;


Type
    : PrimitiveType { $$ = $1; }
    | ArrayType { $$ = $1; }
    | RecordType { $$ = $1; }
    | Identifier { $$ = $1; }
    ;

PrimitiveType
    : INTEGER { $$ = new PrimitiveType("integer");}
    | REAL { $$ = new PrimitiveType("real"); }
    | BOOLEAN { $$ = new PrimitiveType("boolean"); }
    ;

RecordType
    : RECORD RecordDeclarations END {$$ = $2;}
    ;

RecordDeclarations
    : /* empty */ { $$ = new RecordType(); }
    | VariableDeclaration RecordDeclarations { $$ = $2; $2.add($1);}
    ;

ArrayType
    : ARRAY LBRACKET Expression RBRACKET Type { $$ = new ArrayType($3, $5); }
    ;


ModifiablePrimary
    : Identifier ElementCall { $$ = new ModifiablePrimary($1, $2); }
    ;

ElementCall
    : /* empty */ { $$ = new ElementCall(); }
    | DOT Identifier ElementCall { $$ = $3; $3.add(new FieldAccess($2)); }
    | LBRACKET Expression RBRACKET ElementCall { $$ = $4; $4.add(new IndexAccess($2));}
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