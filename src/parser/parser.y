%language "Java"
%output "YYParser.java"
%define lex_throws {}
%define api.parser.class {YYParser}
%define api.parser.public
%define api.package {parser}
%define api.value.type {ILexem}

%locations
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
%token  IDENTIFIER
%token  INTEGER_LITERAL
%token  REAL_LITERAL
%token  TRUE FALSE
// Keywords
%token VAR TYPE IS END RECORD
%token INTEGER REAL BOOLEAN
%token ARRAY WHILE LOOP FOR IN REVERSE IF THEN ELSE ROUTINE AND
%token OR NOT XOR PRINT RETURN

// Separators
//%token  NEWLINE
//%token SEMICOLON

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

%type  Program
%type  GlobalDeclarations
%type  GlobalDeclaration
%type  SimpleDeclaration
%type  RoutineDeclaration
%type  VariableDeclarations
%type  VariableDeclaration
%type  TypeDeclaration
%type Type
%type  Return
%type  Print
%type  Parameters
%type  ParameterDeclaration
%type  PrimitiveType
%type  ArrayType
%type  RecordType
%type  Body
%type  BodyDeclaration
%type  Statement
%type  Assignment
%type  RoutineCall
%type  Expressions
%type  Expression
%type  WhileLoop
%type  ForLoop
%type  IfStatement
%type  Relations
%type  LogicWord
%type  Relation
%type  SimpleTail
%type  RelationSign
%type  Simple
%type  FactorSign
%type  FactorTail
%type  Factor
%type  SummandSign
%type  Summand
%type  SummandTail
%type  Primary
%type  ModifiablePrimary
%type  ElementCall
%type  Identifier

%start Program

%%

Program
    : GlobalDeclarations {ast = $1;}
    ;

GlobalDeclarations
    : /* empty */ { $$ = new GlobalDeclarations();}
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
    : VAR Identifier COLON Type  {$$ = new VariableDeclaration($2, $4);}
    | VAR Identifier COLON Type IS Expression  { $$ = new VariableDeclaration($2, $4, $6); }
    | VAR Identifier IS Expression  {$$ = new VariableDeclaration($2, $4);}
    ;

TypeDeclaration
    : TYPE Identifier IS Type {$$ = new TypeDeclaration($2, $4);}
    ;

RoutineDeclaration
    : ROUTINE Identifier LPAREN Parameters RPAREN IS Body END  {
    	$$ = new RoutineDeclaration($2, $4, $7, null);
    }
    | ROUTINE Identifier LPAREN Parameters RPAREN COLON Type IS Body END  {
    	$$ = new RoutineDeclaration($2, $4, $7, $9);
    }
    ;



Parameters
    : ParameterDeclaration { $$ = new Parameters($1);}
    | ParameterDeclaration COMMA Parameters { $$ = $3; $3.add($1); }
    ;


ParameterDeclaration
    : Identifier COLON Type { $$ = new Parameter($1, $3); }
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
    : RECORD VariableDeclarations END {$$ = $3;}
    ;

VariableDeclarations
    : /* empty */ { $$ = new RecordType(); }
    | VariableDeclaration VariableDeclarations { $$ = $2; $2.add($1);}
    ;


ArrayType
    : ARRAY LBRACKET Expression RBRACKET Type { $$ = new ArrayType($3, $5); }
    ;

Body
    : /* empty */ { $$ = new Body();}
    | BodyDeclaration Body {$$ = $2; $2.addBody($1);}
    ;

BodyDeclaration
    : SimpleDeclaration {$$ = $1;}
    | Statement {$$ = $1;}
    ;

Statement
    : Assignment  {$$ = $1;}
    | RoutineCall  {$$ = $1; }
    | WhileLoop  {$$ = $1;}
    | ForLoop  {$$ = $1;}
    | IfStatement {$$ = $1;}
    | Print  {$$ = $1;}
    | Return  {$$ = $1;}
    ;

Return
    : RETURN Expression {$$ = new Return($2);}
    ;

Assignment
    : ModifiablePrimary ASSIGN Expression {$$ = new Assignment($1, $3);}
    ;

RoutineCall
    : Identifier LPAREN Expressions RPAREN {$$ = new RoutineCall($1, $3); }
    ;

Expressions
    : Expression { $$ = new Expressions($1); }
    | Expression COMMA Expressions { $$ = $3; $3.add($1); }
    ;

WhileLoop
    : WHILE Expression LOOP Body END {$$ = new WhileLoop($2, $4); }
    ;

ForLoop
    : FOR Identifier IN Expression RANGE Expression LOOP Body END
    {$$ = new ForLoop($2, $4, $6, $8, false);}
    | FOR Identifier IN REVERSE Expression RANGE Expression LOOP Body END
    {$$ = new ForLoop($2, $5, $7, $9, true);}
    ;

IfStatement
    : IF Expression THEN Body END {$$ = new IfStatement($2, $4);}
    | IF Expression THEN Body ELSE Body END {$$ = new IfStatement($2, $4, $6);}
    ;


Expression
    : Relation Relations { $$ = $2; $2.add($1); }
    | SummandSign Relation Relations { $$ = $3;  $2.setSummandSign($1); $3.add($2); }
    | NOT Relation Relations { $$ = $3; $2.setNot(); $3.add($2); }
    | NOT SummandSign Relation Relations { $$ = $4; $3.setSummandSign($2); $3.setNot(); $4.add($3); }
    ;

Relations
    : /* empty */  { $$ = new Expression();}
    | LogicWord Relation Relations { $$ = $3; $3.add2($2, $1); }
    | LogicWord NOT Relation Relations {$$ = $4; $3.setNot(); $4.add2($3, $1);}
    ;



LogicWord
    : AND { $$ = new LogicWord("and"); }
    | OR { $$ = new LogicWord("or"); }
    | XOR { $$ = new LogicWord("xor"); }
    ;

Relation
    : Simple SimpleTail { $$ = $2; $2.add($1); }
    ;


SimpleTail
    : /* empty */ { $$ = new IRelation(); }
    | RelationSign Simple SimpleTail { $$ = $3; $3.add2($2, $1); }
    ;


RelationSign
    : LESS { $$ = new RelationSign("<"); }
    | GREATER { $$ = new RelationSign(">"); }
    | LEQUALS { $$ = new RelationSign("<="); }
    | GEQUALS { $$ = new RelationSign(">="); }
    | EQUALS { $$ =  new RelationSign("="); }
    | NEQUALS { $$ = new RelationSign("/="); }
    ;

Simple
    : Factor FactorTail { $$ = $2; $2.add($1); }
    ;

FactorTail
    : /* empty */ {$$ = new Simple(); }
    | FactorSign Factor FactorTail { $$ = $3; $3.add2($2, $1); }
    ;

FactorSign
    : MULTIPLY { $$ = new FactorSign("*"); };
    | DIVIDE { $$ = new FactorSign("/"); }
    | REMAINDER { $$ = new FactorSign("%"); }
    ;

Factor
    : Summand SummandTail { $$ = $2; $2.add($1); }
    ;

SummandTail
    : /* empty */ { $$ = new Factor(); }
    | SummandSign Summand SummandTail { $$ = $3; $3.add2($2, $1); }

SummandSign
    : PLUS { $$ = new SummandSign("+"); }
    | MINUS { $$ = new SummandSign("-"); }
    ;

Summand
    : Primary { $$ = new Summand($1); }
    | LPAREN Expression RPAREN { $$ = new Summand($2); }
    ;

Primary
    : INTEGER_LITERAL { $$ = $1;}
    | REAL_LITERAL { $$ = $1; }
    | TRUE { $$ = new BooleanLiteral(true); }
    | FALSE { $$ = new BooleanLiteral(false); }
    | ModifiablePrimary { $$ = $1; }
    | RoutineCall { $$ = $1;}
    ;

ModifiablePrimary
    : Identifier ElementCall { $$ = new ModifiablePrimary($1, $2); }
    ;

ElementCall
    : /* empty */ { $$ = new ElementCall(); }
    | DOT Identifier ElementCall { $$ = $3; $3.add($2); }
    | LBRACKET Expression RBRACKET ElementCall { $$ = $4; $4.add($2);}
    ;

Print
    : PRINT LPAREN Expressions RPAREN {$$ = new Print($3);}
    ;

Identifier:
	IDENTIFIER {$$ = yylexer.getLVal();}
