%language "Java"
%output "YYParser.java"
%define lex_throws {}
%define api.parser.class {YYParser}
%define api.parser.public
%define api.package {parser}
%define api.value.type {Object}


%code imports {
import lexems.*;
import lexer.*;
import reader.Reader;
}

%code {
    private static GlobalDeclarations ast;
    public static GlobalDeclarations makeAST(String i) {
        ast = new lexems.GlobalDeclarations();
        MyLexer l = new MyLexer();
        Reader reader = new Reader();
        reader.read("tests/" + i + ".txt");
        l.tokenize(reader.sourceText);
        YYParser p = new YYParser(l);
        if (!p.parse()) {
            System.exit(1);
        }
        return ast;
    }
}
/* YACC Declarations */

// Identifiers & numbers
%token <Identifier> IDENTIFIER
%token <IntegerLiteral> INTEGER_LITERAL
%token <RealLiteral> REAL_LITERAL
%token <BooleanLiteral> TRUE FALSE
// Keywords
%token VAR TYPE IS END RECORD
%token INTEGER REAL BOOLEAN
%token ARRAY WHILE LOOP FOR IN REVERSE IF THEN ELSE ROUTINE AND
%token OR NOT XOR PRINT RETURN

// Separators
%token <ILexem> NEWLINE
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

%start Program
%%

Program
    : GlobalDeclarations {System.out.println("Global Declarations parsed");}
    ;

GlobalDeclarations
    : /* empty */
    | GlobalDeclaration GlobalDeclarations
    ;

GlobalDeclaration
    : SimpleDeclaration {System.out.println("Global Declaration parsed");}
    | RoutineDeclaration {System.out.println("Routine Declaration parsed");}
    | NEWLINE {System.out.println("Newline parsed");}
    ;

SimpleDeclaration
    : VariableDeclaration
    | TypeDeclaration {System.out.println("Type Declaration parsed");}
    ;

VariableDeclaration
    : VAR IDENTIFIER COLON Type ExpressionTail OptionalSemicolon {{System.out.println("Variable declaration parsed");}}
    | VAR IDENTIFIER ExpressionTail OptionalSemicolon {System.out.println("Variable declaration parsed");}
    ;
OptionalSemicolon
    : NEWLINE {System.out.println("Optional Newline parsed");}
    | SEMICOLON {System.out.println("Optional Semicolon parsed");}
    ;

ExpressionTail
    : /* empty */
    | IS Expression
    ;

TypeDeclaration
    : TYPE IDENTIFIER IS Type
    ;

RoutineDeclaration
    : ROUTINE IDENTIFIER LPAREN Parameters RPAREN TypeTail IS Body END OptionalSemicolon {System.out.println("Routine Declaration parsed");}
    ;

Return
    : RETURN Expression  //{$$ = $2}
    ;

TypeTail
    : /* empty */
    | COLON Type
    ;

Parameters
    : ParameterDeclaration
    | ParameterDeclaration COMMA Parameters
    ;


ParameterDeclaration
    : IDENTIFIER COLON Type
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
    : RECORD NEWLINE VariableDeclarations END {System.out.println("Record type parsed");}
    ;

VariableDeclarations
    : /* empty */ {System.out.println("Variable declarations parsed");}
    | VariableDeclaration VariableDeclarations {System.out.println("Variable declaration parsed");}
    | NEWLINE VariableDeclarations
    ;


ArrayType
    : ARRAY LBRACKET Expression RBRACKET Type
    ;

Body
    : /* empty */
    | BodyDeclaration Body
    ;

BodyDeclaration
    : SimpleDeclaration
    | Statement
    ;

Statement
    : Assignment OptionalSemicolon
    | RoutineCall OptionalSemicolon
    | WhileLoop OptionalSemicolon
    | ForLoop OptionalSemicolon
    | IfStatement OptionalSemicolon
    | Print OptionalSemicolon
    | Return OptionalSemicolon
    | NEWLINE
    ;

Assignment
    : ModifiablePrimary ASSIGN Expression
    ;

RoutineCall
    : IDENTIFIER LPAREN Expressions RPAREN
    ;



Expressions
    : Expression
    | Expression COMMA Expressions
    ;

WhileLoop
    : WHILE Expression LOOP Body END
    ;

ForLoop
    : FOR IDENTIFIER Range LOOP Body END
    ;

Range
    : IN ReverseTail Expression RANGE Expression
    ;

ReverseTail
    : /* empty */
    | REVERSE
    ;

IfStatement
    : IF Expression THEN Body ElseTail END
    ;

ElseTail
    : /* empty */
    | ELSE Body
    ;

Expression
    : Relation Relations
    | SummandSign Relation Relations
    | NOT Relation Relations
    | NOT SummandSign Relation Relations
    ;

Relations
    : /* empty */
    | LogicWord Relation Relations
    | LogicWord NOT Relation Relations
    ;


LogicWord
    : AND
    | OR
    | XOR
    ;

Relation
    : Simple SimpleTail {System.out.println("Relation declared");}
    ;


SimpleTail
    : /* empty */ {System.out.println("SimpleTail parsed");}
    | RelationSign Simple SimpleTail {System.out.println("SimpleTail parsed");}
    ;


RelationSign
    : LESS | GREATER | LEQUALS | GEQUALS | EQUALS | NEQUALS
    ;

Simple
    : Factor FactorTail
    ;

FactorTail
    : /* empty */
    | FactorSign Factor FactorTail
    ;

FactorSign
    : MULTIPLY | DIVIDE | REMAINDER
    ;

Factor
    : Summand SummandTail
    ;

SummandTail
    : /* empty */
    | SummandSign Summand SummandTail


SummandSign
    : PLUS | MINUS
    ;

Summand
    : Primary
    |  LPAREN Expression RPAREN
    ;

Primary

    : INTEGER_LITERAL
    | REAL_LITERAL
    | TRUE
    | FALSE
    | ModifiablePrimary
    | RoutineCall
    ;

ModifiablePrimary
    : IDENTIFIER ElementCall
    ;

ElementCall
    : /* empty */
    | DOT IDENTIFIER ElementCall
    | LBRACKET Expression RBRACKET ElementCall
    ;

Print
    : PRINT LPAREN Expressions RPAREN //{Print($3)}
    ;

%%
