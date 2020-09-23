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
%token <String> IDENTIFIER
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

%type <GlobalDeclarations> Program
%type <GlobalDeclarations> GlobalDeclarations
%type <ILexem> GlobalDeclaration
%type <ILexem> SimpleDeclaration
%type <ILexem> RoutineDeclaration
%type <IList> VariableDeclarations
%type <ILexem> VariableDeclaration
%type <ILexem> TypeDeclaration
%type <Type> Type
%type <ILexem> Return
%type <ILexem> Print
%type <Parameters> Parameters
%type <ILexem> ParameterDeclaration
%type <ILexem> PrimitiveType
%type <ILexem> ArrayType
%type <IList> RecordType
%type <Body> Body
%type <ILexem> BodyDeclaration
%type <ILexem> Statement
%type <ILexem> Assignment
%type <ILexem> RoutineCall
%type <Expressions> Expressions
%type <Expression> Expression
%type <ILexem> WhileLoop
%type <ILexem> ForLoop
%type <ILexem> IfStatement
%type <IList2> Relations
%type <ILexem> LogicWord
%type <IRelation> Relation
%type <IList2> SimpleTail
%type <ILexem> RelationSign
%type <IList2> Simple
%type <ILexem> FactorSign
%type <IList2> FactorTail
%type <ILexem> Factor
%type <SummandSign> SummandSign
%type <ILexem> Summand
%type <IList2> SummandTail
%type <ILexem> Primary
%type <ModifiablePrimary> ModifiablePrimary
%type <IList> ElementCall
%type <ILexem> Identifier

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
    | NEWLINE
    ;

SimpleDeclaration
    : VariableDeclaration { $$ = $1;}
    | TypeDeclaration { $$ = $1;}
    ;

VariableDeclaration
    : VAR Identifier COLON Type OptionalSemicolon {$$ = new VariableDeclaration($2, $4);}
    | VAR Identifier COLON Type IS Expression OptionalSemicolon { $$ = new VariableDeclaration($2, $4, $6); }
    | VAR Identifier IS Expression OptionalSemicolon {$$ = new VariableDeclaration($2, $4);}
    ;
OptionalSemicolon
    : NEWLINE
    | SEMICOLON
    ;

TypeDeclaration
    : TYPE Identifier IS Type {$$ = new TypeDeclaration($2, $4);}
    ;

RoutineDeclaration
    : ROUTINE Identifier LPAREN Parameters RPAREN IS Body END OptionalSemicolon {
    	$$ = new RoutineDeclaration($2, $4, $7);
    }
    | ROUTINE Identifier LPAREN Parameters RPAREN COLON Type IS Body END OptionalSemicolon {
    	$$ = new RoutineDeclaration($2, $4, $7, $9);
    }
    ;

Return
    : RETURN Expression {$$ = new Return($2);}
    ;


Parameters
    : ParameterDeclaration { $$ = $1; }
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
    : RECORD NEWLINE VariableDeclarations END {$$ = $3;}
    ;

VariableDeclarations
    : /* empty */ { $$ = new RecordType(); }
    | VariableDeclaration VariableDeclarations { $$ = $2; $2.add($1);}
    | NEWLINE VariableDeclarations { $$ = $2; }
    ;


ArrayType
    : ARRAY LBRACKET Expression RBRACKET Type { $$ = new ArrayType($3, $5); }
    ;

Body
    : /* empty */ { $$ = new Body();}
    | BodyDeclaration Body {$$ = $2; $2.addBody($1);}
    ;

BodyDeclaration
    : SimpleDeclaration {$$ = new BodyDeclaration($1);}
    | Statement {$$ = new BodyDeclaration($1);}
    ;

Statement
    : Assignment OptionalSemicolon {$$ = $1;}
    | RoutineCall OptionalSemicolon {$$ = $1; }
    | WhileLoop OptionalSemicolon {$$ = $1;}
    | ForLoop OptionalSemicolon {$$ = $1;}
    | IfStatement OptionalSemicolon{$$ = $1;}
    | Print OptionalSemicolon {$$ = $1;}
    | Return OptionalSemicolon {$$ = $1;}
    | NEWLINE
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
//	case REVERSE -> code = REVERSE;
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