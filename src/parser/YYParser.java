/* A Bison parser, made by GNU Bison 3.7.1.  */

/* Skeleton implementation for Bison LALR(1) parsers in Java

   Copyright (C) 2007-2015, 2018-2020 Free Software Foundation, Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* DO NOT RELY ON FEATURES THAT ARE NOT DOCUMENTED in the manual,
   especially those whose name start with YY_ or yy_.  They are
   private implementation details that can be changed or removed.  */

package parser;



import java.text.MessageFormat;
/* "%code imports" blocks.  */
/* "parser.y":10  */

import lexems.*;
import lexer.*;
import reader.Reader;

/* "YYParser.java":50  */

/**
 * A Bison parser, automatically generated from <tt>parser.y</tt>.
 *
 * @author LALR (1) parser skeleton written by Paolo Bonzini.
 */
public class YYParser
{
  /** Version number for the Bison executable that generated this parser.  */
  public static final String bisonVersion = "3.7.1";

  /** Name of the skeleton that generated this parser.  */
  public static final String bisonSkeleton = "lalr1.java";






  public enum SymbolKind
  {
    S_YYEOF(0),                    /* "end of file"  */
    S_YYerror(1),                  /* error  */
    S_YYUNDEF(2),                  /* "invalid token"  */
    S_IDENTIFIER(3),               /* IDENTIFIER  */
    S_INTEGER_LITERAL(4),          /* INTEGER_LITERAL  */
    S_REAL_LITERAL(5),             /* REAL_LITERAL  */
    S_TRUE(6),                     /* TRUE  */
    S_FALSE(7),                    /* FALSE  */
    S_VAR(8),                      /* VAR  */
    S_TYPE(9),                     /* TYPE  */
    S_IS(10),                      /* IS  */
    S_END(11),                     /* END  */
    S_RECORD(12),                  /* RECORD  */
    S_INTEGER(13),                 /* INTEGER  */
    S_REAL(14),                    /* REAL  */
    S_BOOLEAN(15),                 /* BOOLEAN  */
    S_ARRAY(16),                   /* ARRAY  */
    S_WHILE(17),                   /* WHILE  */
    S_LOOP(18),                    /* LOOP  */
    S_FOR(19),                     /* FOR  */
    S_IN(20),                      /* IN  */
    S_REVERSE(21),                 /* REVERSE  */
    S_IF(22),                      /* IF  */
    S_THEN(23),                    /* THEN  */
    S_ELSE(24),                    /* ELSE  */
    S_ROUTINE(25),                 /* ROUTINE  */
    S_AND(26),                     /* AND  */
    S_OR(27),                      /* OR  */
    S_NOT(28),                     /* NOT  */
    S_XOR(29),                     /* XOR  */
    S_PRINT(30),                   /* PRINT  */
    S_RETURN(31),                  /* RETURN  */
    S_NEWLINE(32),                 /* NEWLINE  */
    S_SEMICOLON(33),               /* SEMICOLON  */
    S_LPAREN(34),                  /* LPAREN  */
    S_RPAREN(35),                  /* RPAREN  */
    S_LBRACKET(36),                /* LBRACKET  */
    S_RBRACKET(37),                /* RBRACKET  */
    S_COMMA(38),                   /* COMMA  */
    S_DOT(39),                     /* DOT  */
    S_COLON(40),                   /* COLON  */
    S_RANGE(41),                   /* RANGE  */
    S_EQUALS(42),                  /* EQUALS  */
    S_ASSIGN(43),                  /* ASSIGN  */
    S_NEQUALS(44),                 /* NEQUALS  */
    S_GREATER(45),                 /* GREATER  */
    S_LESS(46),                    /* LESS  */
    S_LEQUALS(47),                 /* LEQUALS  */
    S_GEQUALS(48),                 /* GEQUALS  */
    S_PLUS(49),                    /* PLUS  */
    S_MINUS(50),                   /* MINUS  */
    S_MULTIPLY(51),                /* MULTIPLY  */
    S_DIVIDE(52),                  /* DIVIDE  */
    S_REMAINDER(53),               /* REMAINDER  */
    S_YYACCEPT(54),                /* $accept  */
    S_Program(55),                 /* Program  */
    S_GlobalDeclarations(56),      /* GlobalDeclarations  */
    S_GlobalDeclaration(57),       /* GlobalDeclaration  */
    S_SimpleDeclaration(58),       /* SimpleDeclaration  */
    S_VariableDeclaration(59),     /* VariableDeclaration  */
    S_OptionalSemicolon(60),       /* OptionalSemicolon  */
    S_TypeDeclaration(61),         /* TypeDeclaration  */
    S_RoutineDeclaration(62),      /* RoutineDeclaration  */
    S_Return(63),                  /* Return  */
    S_Parameters(64),              /* Parameters  */
    S_ParameterDeclaration(65),    /* ParameterDeclaration  */
    S_Type(66),                    /* Type  */
    S_PrimitiveType(67),           /* PrimitiveType  */
    S_RecordType(68),              /* RecordType  */
    S_VariableDeclarations(69),    /* VariableDeclarations  */
    S_ArrayType(70),               /* ArrayType  */
    S_Body(71),                    /* Body  */
    S_BodyDeclaration(72),         /* BodyDeclaration  */
    S_Statement(73),               /* Statement  */
    S_Assignment(74),              /* Assignment  */
    S_RoutineCall(75),             /* RoutineCall  */
    S_Expressions(76),             /* Expressions  */
    S_WhileLoop(77),               /* WhileLoop  */
    S_ForLoop(78),                 /* ForLoop  */
    S_IfStatement(79),             /* IfStatement  */
    S_Expression(80),              /* Expression  */
    S_Relations(81),               /* Relations  */
    S_LogicWord(82),               /* LogicWord  */
    S_Relation(83),                /* Relation  */
    S_SimpleTail(84),              /* SimpleTail  */
    S_RelationSign(85),            /* RelationSign  */
    S_Simple(86),                  /* Simple  */
    S_FactorTail(87),              /* FactorTail  */
    S_FactorSign(88),              /* FactorSign  */
    S_Factor(89),                  /* Factor  */
    S_SummandTail(90),             /* SummandTail  */
    S_SummandSign(91),             /* SummandSign  */
    S_Summand(92),                 /* Summand  */
    S_Primary(93),                 /* Primary  */
    S_ModifiablePrimary(94),       /* ModifiablePrimary  */
    S_ElementCall(95),             /* ElementCall  */
    S_Print(96);                   /* Print  */


    private final int yycode_;

    SymbolKind (int n) {
      this.yycode_ = n;
    }

    private static final SymbolKind[] values_ = {
      SymbolKind.S_YYEOF,
      SymbolKind.S_YYerror,
      SymbolKind.S_YYUNDEF,
      SymbolKind.S_IDENTIFIER,
      SymbolKind.S_INTEGER_LITERAL,
      SymbolKind.S_REAL_LITERAL,
      SymbolKind.S_TRUE,
      SymbolKind.S_FALSE,
      SymbolKind.S_VAR,
      SymbolKind.S_TYPE,
      SymbolKind.S_IS,
      SymbolKind.S_END,
      SymbolKind.S_RECORD,
      SymbolKind.S_INTEGER,
      SymbolKind.S_REAL,
      SymbolKind.S_BOOLEAN,
      SymbolKind.S_ARRAY,
      SymbolKind.S_WHILE,
      SymbolKind.S_LOOP,
      SymbolKind.S_FOR,
      SymbolKind.S_IN,
      SymbolKind.S_REVERSE,
      SymbolKind.S_IF,
      SymbolKind.S_THEN,
      SymbolKind.S_ELSE,
      SymbolKind.S_ROUTINE,
      SymbolKind.S_AND,
      SymbolKind.S_OR,
      SymbolKind.S_NOT,
      SymbolKind.S_XOR,
      SymbolKind.S_PRINT,
      SymbolKind.S_RETURN,
      SymbolKind.S_NEWLINE,
      SymbolKind.S_SEMICOLON,
      SymbolKind.S_LPAREN,
      SymbolKind.S_RPAREN,
      SymbolKind.S_LBRACKET,
      SymbolKind.S_RBRACKET,
      SymbolKind.S_COMMA,
      SymbolKind.S_DOT,
      SymbolKind.S_COLON,
      SymbolKind.S_RANGE,
      SymbolKind.S_EQUALS,
      SymbolKind.S_ASSIGN,
      SymbolKind.S_NEQUALS,
      SymbolKind.S_GREATER,
      SymbolKind.S_LESS,
      SymbolKind.S_LEQUALS,
      SymbolKind.S_GEQUALS,
      SymbolKind.S_PLUS,
      SymbolKind.S_MINUS,
      SymbolKind.S_MULTIPLY,
      SymbolKind.S_DIVIDE,
      SymbolKind.S_REMAINDER,
      SymbolKind.S_YYACCEPT,
      SymbolKind.S_Program,
      SymbolKind.S_GlobalDeclarations,
      SymbolKind.S_GlobalDeclaration,
      SymbolKind.S_SimpleDeclaration,
      SymbolKind.S_VariableDeclaration,
      SymbolKind.S_OptionalSemicolon,
      SymbolKind.S_TypeDeclaration,
      SymbolKind.S_RoutineDeclaration,
      SymbolKind.S_Return,
      SymbolKind.S_Parameters,
      SymbolKind.S_ParameterDeclaration,
      SymbolKind.S_Type,
      SymbolKind.S_PrimitiveType,
      SymbolKind.S_RecordType,
      SymbolKind.S_VariableDeclarations,
      SymbolKind.S_ArrayType,
      SymbolKind.S_Body,
      SymbolKind.S_BodyDeclaration,
      SymbolKind.S_Statement,
      SymbolKind.S_Assignment,
      SymbolKind.S_RoutineCall,
      SymbolKind.S_Expressions,
      SymbolKind.S_WhileLoop,
      SymbolKind.S_ForLoop,
      SymbolKind.S_IfStatement,
      SymbolKind.S_Expression,
      SymbolKind.S_Relations,
      SymbolKind.S_LogicWord,
      SymbolKind.S_Relation,
      SymbolKind.S_SimpleTail,
      SymbolKind.S_RelationSign,
      SymbolKind.S_Simple,
      SymbolKind.S_FactorTail,
      SymbolKind.S_FactorSign,
      SymbolKind.S_Factor,
      SymbolKind.S_SummandTail,
      SymbolKind.S_SummandSign,
      SymbolKind.S_Summand,
      SymbolKind.S_Primary,
      SymbolKind.S_ModifiablePrimary,
      SymbolKind.S_ElementCall,
      SymbolKind.S_Print
    };

    static final SymbolKind get(int code) {
      return values_[code];
    }

    public final int getCode() {
      return this.yycode_;
    }

    /* Return YYSTR after stripping away unnecessary quotes and
       backslashes, so that it's suitable for yyerror.  The heuristic is
       that double-quoting is unnecessary unless the string contains an
       apostrophe, a comma, or backslash (other than backslash-backslash).
       YYSTR is taken from yytname.  */
    private static String yytnamerr_(String yystr)
    {
      if (yystr.charAt (0) == '"')
        {
          StringBuffer yyr = new StringBuffer();
          strip_quotes: for (int i = 1; i < yystr.length(); i++)
            switch (yystr.charAt(i))
              {
              case '\'':
              case ',':
                break strip_quotes;

              case '\\':
                if (yystr.charAt(++i) != '\\')
                  break strip_quotes;
                /* Fall through.  */
              default:
                yyr.append(yystr.charAt(i));
                break;

              case '"':
                return yyr.toString();
              }
        }
      return yystr;
    }

    /* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
       First, the terminals, then, starting at \a YYNTOKENS_, nonterminals.  */
    private static final String[] yytname_ = yytname_init();
  private static final String[] yytname_init()
  {
    return new String[]
    {
  "\"end of file\"", "error", "\"invalid token\"", "IDENTIFIER",
  "INTEGER_LITERAL", "REAL_LITERAL", "TRUE", "FALSE", "VAR", "TYPE", "IS",
  "END", "RECORD", "INTEGER", "REAL", "BOOLEAN", "ARRAY", "WHILE", "LOOP",
  "FOR", "IN", "REVERSE", "IF", "THEN", "ELSE", "ROUTINE", "AND", "OR",
  "NOT", "XOR", "PRINT", "RETURN", "NEWLINE", "SEMICOLON", "LPAREN",
  "RPAREN", "LBRACKET", "RBRACKET", "COMMA", "DOT", "COLON", "RANGE",
  "EQUALS", "ASSIGN", "NEQUALS", "GREATER", "LESS", "LEQUALS", "GEQUALS",
  "PLUS", "MINUS", "MULTIPLY", "DIVIDE", "REMAINDER", "$accept", "Program",
  "GlobalDeclarations", "GlobalDeclaration", "SimpleDeclaration",
  "VariableDeclaration", "OptionalSemicolon", "TypeDeclaration",
  "RoutineDeclaration", "Return", "Parameters", "ParameterDeclaration",
  "Type", "PrimitiveType", "RecordType", "VariableDeclarations",
  "ArrayType", "Body", "BodyDeclaration", "Statement", "Assignment",
  "RoutineCall", "Expressions", "WhileLoop", "ForLoop", "IfStatement",
  "Expression", "Relations", "LogicWord", "Relation", "SimpleTail",
  "RelationSign", "Simple", "FactorTail", "FactorSign", "Factor",
  "SummandTail", "SummandSign", "Summand", "Primary", "ModifiablePrimary",
  "ElementCall", "Print", null
    };
  }

    /* The user-facing name of this symbol.  */
    public final String getName() {
      return yytnamerr_(yytname_[yycode_]);
    }

  };


  /**
   * Communication interface between the scanner and the Bison-generated
   * parser <tt>YYParser</tt>.
   */
  public interface Lexer {
    /* Token kinds.  */
    /** Token "end of file", to be returned by the scanner.  */
    static final int YYEOF = 0;
    /** Token error, to be returned by the scanner.  */
    static final int YYerror = 256;
    /** Token "invalid token", to be returned by the scanner.  */
    static final int YYUNDEF = 257;
    /** Token IDENTIFIER, to be returned by the scanner.  */
    static final int IDENTIFIER = 258;
    /** Token INTEGER_LITERAL, to be returned by the scanner.  */
    static final int INTEGER_LITERAL = 259;
    /** Token REAL_LITERAL, to be returned by the scanner.  */
    static final int REAL_LITERAL = 260;
    /** Token TRUE, to be returned by the scanner.  */
    static final int TRUE = 261;
    /** Token FALSE, to be returned by the scanner.  */
    static final int FALSE = 262;
    /** Token VAR, to be returned by the scanner.  */
    static final int VAR = 263;
    /** Token TYPE, to be returned by the scanner.  */
    static final int TYPE = 264;
    /** Token IS, to be returned by the scanner.  */
    static final int IS = 265;
    /** Token END, to be returned by the scanner.  */
    static final int END = 266;
    /** Token RECORD, to be returned by the scanner.  */
    static final int RECORD = 267;
    /** Token INTEGER, to be returned by the scanner.  */
    static final int INTEGER = 268;
    /** Token REAL, to be returned by the scanner.  */
    static final int REAL = 269;
    /** Token BOOLEAN, to be returned by the scanner.  */
    static final int BOOLEAN = 270;
    /** Token ARRAY, to be returned by the scanner.  */
    static final int ARRAY = 271;
    /** Token WHILE, to be returned by the scanner.  */
    static final int WHILE = 272;
    /** Token LOOP, to be returned by the scanner.  */
    static final int LOOP = 273;
    /** Token FOR, to be returned by the scanner.  */
    static final int FOR = 274;
    /** Token IN, to be returned by the scanner.  */
    static final int IN = 275;
    /** Token REVERSE, to be returned by the scanner.  */
    static final int REVERSE = 276;
    /** Token IF, to be returned by the scanner.  */
    static final int IF = 277;
    /** Token THEN, to be returned by the scanner.  */
    static final int THEN = 278;
    /** Token ELSE, to be returned by the scanner.  */
    static final int ELSE = 279;
    /** Token ROUTINE, to be returned by the scanner.  */
    static final int ROUTINE = 280;
    /** Token AND, to be returned by the scanner.  */
    static final int AND = 281;
    /** Token OR, to be returned by the scanner.  */
    static final int OR = 282;
    /** Token NOT, to be returned by the scanner.  */
    static final int NOT = 283;
    /** Token XOR, to be returned by the scanner.  */
    static final int XOR = 284;
    /** Token PRINT, to be returned by the scanner.  */
    static final int PRINT = 285;
    /** Token RETURN, to be returned by the scanner.  */
    static final int RETURN = 286;
    /** Token NEWLINE, to be returned by the scanner.  */
    static final int NEWLINE = 287;
    /** Token SEMICOLON, to be returned by the scanner.  */
    static final int SEMICOLON = 288;
    /** Token LPAREN, to be returned by the scanner.  */
    static final int LPAREN = 289;
    /** Token RPAREN, to be returned by the scanner.  */
    static final int RPAREN = 290;
    /** Token LBRACKET, to be returned by the scanner.  */
    static final int LBRACKET = 291;
    /** Token RBRACKET, to be returned by the scanner.  */
    static final int RBRACKET = 292;
    /** Token COMMA, to be returned by the scanner.  */
    static final int COMMA = 293;
    /** Token DOT, to be returned by the scanner.  */
    static final int DOT = 294;
    /** Token COLON, to be returned by the scanner.  */
    static final int COLON = 295;
    /** Token RANGE, to be returned by the scanner.  */
    static final int RANGE = 296;
    /** Token EQUALS, to be returned by the scanner.  */
    static final int EQUALS = 297;
    /** Token ASSIGN, to be returned by the scanner.  */
    static final int ASSIGN = 298;
    /** Token NEQUALS, to be returned by the scanner.  */
    static final int NEQUALS = 299;
    /** Token GREATER, to be returned by the scanner.  */
    static final int GREATER = 300;
    /** Token LESS, to be returned by the scanner.  */
    static final int LESS = 301;
    /** Token LEQUALS, to be returned by the scanner.  */
    static final int LEQUALS = 302;
    /** Token GEQUALS, to be returned by the scanner.  */
    static final int GEQUALS = 303;
    /** Token PLUS, to be returned by the scanner.  */
    static final int PLUS = 304;
    /** Token MINUS, to be returned by the scanner.  */
    static final int MINUS = 305;
    /** Token MULTIPLY, to be returned by the scanner.  */
    static final int MULTIPLY = 306;
    /** Token DIVIDE, to be returned by the scanner.  */
    static final int DIVIDE = 307;
    /** Token REMAINDER, to be returned by the scanner.  */
    static final int REMAINDER = 308;

    /** Deprecated, use YYEOF instead.  */
    public static final int EOF = YYEOF;


    /**
     * Method to retrieve the semantic value of the last scanned token.
     * @return the semantic value of the last scanned token.
     */
    Object getLVal();

    /**
     * Entry point for the scanner.  Returns the token identifier corresponding
     * to the next token and prepares to return the semantic value
     * of the token.
     * @return the token identifier corresponding to the next token.
     */
    int yylex();

    /**
     * Emit an errorin a user-defined way.
     *
     *
     * @param msg The string for the error message.
     */
     void yyerror(String msg);


  }


  /**
   * The object doing lexical analysis for us.
   */
  private Lexer yylexer;





  /**
   * Instantiates the Bison-generated parser.
   * @param yylexer The scanner that will supply tokens to the parser.
   */
  public YYParser (Lexer yylexer)
  {

    this.yylexer = yylexer;

  }



  private int yynerrs = 0;

  /**
   * The number of syntax errors so far.
   */
  public final int getNumberOfErrors () { return yynerrs; }

  /**
   * Print an error message via the lexer.
   *
   * @param msg The error message.
   */
  public final void yyerror(String msg) {
      yylexer.yyerror(msg);
  }



  private final class YYStack {
    private int[] stateStack = new int[16];
    private Object[] valueStack = new Object[16];

    public int size = 16;
    public int height = -1;

    public final void push (int state, Object value) {
      height++;
      if (size == height)
        {
          int[] newStateStack = new int[size * 2];
          System.arraycopy (stateStack, 0, newStateStack, 0, height);
          stateStack = newStateStack;

          Object[] newValueStack = new Object[size * 2];
          System.arraycopy (valueStack, 0, newValueStack, 0, height);
          valueStack = newValueStack;

          size *= 2;
        }

      stateStack[height] = state;
      valueStack[height] = value;
    }

    public final void pop () {
      pop (1);
    }

    public final void pop (int num) {
      // Avoid memory leaks... garbage collection is a white lie!
      if (0 < num) {
        java.util.Arrays.fill (valueStack, height - num + 1, height + 1, null);
      }
      height -= num;
    }

    public final int stateAt (int i) {
      return stateStack[height - i];
    }

    public final Object valueAt (int i) {
      return valueStack[height - i];
    }

    // Print the state stack on the debug stream.
    public void print (java.io.PrintStream out) {
      out.print ("Stack now");

      for (int i = 0; i <= height; i++)
        {
          out.print (' ');
          out.print (stateStack[i]);
        }
      out.println ();
    }
  }

  /**
   * Returned by a Bison action in order to stop the parsing process and
   * return success (<tt>true</tt>).
   */
  public static final int YYACCEPT = 0;

  /**
   * Returned by a Bison action in order to stop the parsing process and
   * return failure (<tt>false</tt>).
   */
  public static final int YYABORT = 1;



  /**
   * Returned by a Bison action in order to start error recovery without
   * printing an error message.
   */
  public static final int YYERROR = 2;

  /**
   * Internal return codes that are not supported for user semantic
   * actions.
   */
  private static final int YYERRLAB = 3;
  private static final int YYNEWSTATE = 4;
  private static final int YYDEFAULT = 5;
  private static final int YYREDUCE = 6;
  private static final int YYERRLAB1 = 7;
  private static final int YYRETURN = 8;


  private int yyerrstatus_ = 0;


  /**
   * Whether error recovery is being done.  In this state, the parser
   * reads token until it reaches a known state, and then restarts normal
   * operation.
   */
  public final boolean recovering ()
  {
    return yyerrstatus_ == 0;
  }

  /** Compute post-reduction state.
   * @param yystate   the current state
   * @param yysym     the nonterminal to push on the stack
   */
  private int yyLRGotoState (int yystate, int yysym)
  {
    int yyr = yypgoto_[yysym - YYNTOKENS_] + yystate;
    if (0 <= yyr && yyr <= YYLAST_ && yycheck_[yyr] == yystate)
      return yytable_[yyr];
    else
      return yydefgoto_[yysym - YYNTOKENS_];
  }

  private int yyaction(int yyn, YYStack yystack, int yylen)
  {
    /* If YYLEN is nonzero, implement the default value of the action:
       '$$ = $1'.  Otherwise, use the top of the stack.

       Otherwise, the following line sets YYVAL to garbage.
       This behavior is undocumented and Bison
       users should not rely upon it.  */
    Object yyval = (0 < yylen) ? yystack.valueAt(yylen - 1) : yystack.valueAt(0);

    switch (yyn)
      {
          case 2: /* Program: GlobalDeclarations  */
  if (yyn == 2)
    /* "parser.y":120  */
                         {ast = ((GlobalDeclarations)(yystack.valueAt (0)));};
  break;


  case 3: /* GlobalDeclarations: %empty  */
  if (yyn == 3)
    /* "parser.y":124  */
                  { yyval = new GlobalDeclarations();};
  break;


  case 4: /* GlobalDeclarations: GlobalDeclaration GlobalDeclarations  */
  if (yyn == 4)
    /* "parser.y":125  */
                                           {yyval = ((GlobalDeclarations)(yystack.valueAt (0))); ((GlobalDeclarations)(yystack.valueAt (0))).add(((ILexem)(yystack.valueAt (1))));};
  break;


  case 5: /* GlobalDeclaration: SimpleDeclaration  */
  if (yyn == 5)
    /* "parser.y":129  */
                        {yyval = ((ILexem)(yystack.valueAt (0)));};
  break;


  case 6: /* GlobalDeclaration: RoutineDeclaration  */
  if (yyn == 6)
    /* "parser.y":130  */
                         {yyval = ((ILexem)(yystack.valueAt (0)));};
  break;


  case 8: /* SimpleDeclaration: VariableDeclaration  */
  if (yyn == 8)
    /* "parser.y":135  */
                          { yyval = ((ILexem)(yystack.valueAt (0)));};
  break;


  case 9: /* SimpleDeclaration: TypeDeclaration  */
  if (yyn == 9)
    /* "parser.y":136  */
                      { yyval = ((ILexem)(yystack.valueAt (0)));};
  break;


  case 10: /* VariableDeclaration: VAR IDENTIFIER COLON Type OptionalSemicolon  */
  if (yyn == 10)
    /* "parser.y":140  */
                                                  {yyval = new VariableDeclaration(((Identifier)(yystack.valueAt (3))), ((Type)(yystack.valueAt (1))));};
  break;


  case 11: /* VariableDeclaration: VAR IDENTIFIER COLON Type IS Expression OptionalSemicolon  */
  if (yyn == 11)
    /* "parser.y":141  */
                                                                { yyval = new VariableDeclaration(((Identifier)(yystack.valueAt (5))), ((Type)(yystack.valueAt (3))), ((Expression)(yystack.valueAt (1)))); };
  break;


  case 12: /* VariableDeclaration: VAR IDENTIFIER IS Expression OptionalSemicolon  */
  if (yyn == 12)
    /* "parser.y":142  */
                                                     {yyval = new VariableDeclaration(((Identifier)(yystack.valueAt (3))), ((Expression)(yystack.valueAt (1))));};
  break;


  case 15: /* TypeDeclaration: TYPE IDENTIFIER IS Type  */
  if (yyn == 15)
    /* "parser.y":150  */
                              {yyval = new TypeDeclaration(((Identifier)(yystack.valueAt (2))), ((Type)(yystack.valueAt (0))));};
  break;


  case 16: /* RoutineDeclaration: ROUTINE IDENTIFIER LPAREN Parameters RPAREN IS Body END OptionalSemicolon  */
  if (yyn == 16)
    /* "parser.y":154  */
                                                                                {
    	yyval = new RoutineDeclaration(((Identifier)(yystack.valueAt (7))), ((Parameters)(yystack.valueAt (5))), ((Body)(yystack.valueAt (2))));
    };
  break;


  case 17: /* RoutineDeclaration: ROUTINE IDENTIFIER LPAREN Parameters RPAREN COLON Type IS Body END OptionalSemicolon  */
  if (yyn == 17)
    /* "parser.y":157  */
                                                                                           {
    	yyval = new RoutineDeclaration(((Identifier)(yystack.valueAt (9))), ((Parameters)(yystack.valueAt (7))), ((Type)(yystack.valueAt (4))), ((Body)(yystack.valueAt (2))));
    };
  break;


  case 18: /* Return: RETURN Expression  */
  if (yyn == 18)
    /* "parser.y":163  */
                        {yyval = new Return(((Expression)(yystack.valueAt (0))));};
  break;


  case 19: /* Parameters: ParameterDeclaration  */
  if (yyn == 19)
    /* "parser.y":168  */
                           { yyval = ((ILexem)(yystack.valueAt (0))); };
  break;


  case 20: /* Parameters: ParameterDeclaration COMMA Parameters  */
  if (yyn == 20)
    /* "parser.y":169  */
                                            { yyval = ((Parameters)(yystack.valueAt (0))); ((Parameters)(yystack.valueAt (0))).add(((ILexem)(yystack.valueAt (2)))); };
  break;


  case 21: /* ParameterDeclaration: IDENTIFIER COLON Type  */
  if (yyn == 21)
    /* "parser.y":174  */
                            { yyval = new Parameter(((Identifier)(yystack.valueAt (2))), ((Type)(yystack.valueAt (0)))); };
  break;


  case 22: /* Type: PrimitiveType  */
  if (yyn == 22)
    /* "parser.y":178  */
                    { yyval = ((ILexem)(yystack.valueAt (0))); };
  break;


  case 23: /* Type: ArrayType  */
  if (yyn == 23)
    /* "parser.y":179  */
                { yyval = ((ILexem)(yystack.valueAt (0))); };
  break;


  case 24: /* Type: RecordType  */
  if (yyn == 24)
    /* "parser.y":180  */
                 { yyval = ((IList)(yystack.valueAt (0))); };
  break;


  case 25: /* Type: IDENTIFIER  */
  if (yyn == 25)
    /* "parser.y":181  */
                 { yyval = ((Identifier)(yystack.valueAt (0))); };
  break;


  case 26: /* PrimitiveType: INTEGER  */
  if (yyn == 26)
    /* "parser.y":185  */
              { yyval = new PrimitiveType("integer");};
  break;


  case 27: /* PrimitiveType: REAL  */
  if (yyn == 27)
    /* "parser.y":186  */
           { yyval = new PrimitiveType("real"); };
  break;


  case 28: /* PrimitiveType: BOOLEAN  */
  if (yyn == 28)
    /* "parser.y":187  */
              { yyval = new PrimitiveType("boolean"); };
  break;


  case 29: /* RecordType: RECORD NEWLINE VariableDeclarations END  */
  if (yyn == 29)
    /* "parser.y":191  */
                                              {yyval = ((IList)(yystack.valueAt (1)));};
  break;


  case 30: /* VariableDeclarations: %empty  */
  if (yyn == 30)
    /* "parser.y":195  */
                  { yyval = new RecordType(); };
  break;


  case 31: /* VariableDeclarations: VariableDeclaration VariableDeclarations  */
  if (yyn == 31)
    /* "parser.y":196  */
                                               { yyval = ((IList)(yystack.valueAt (0))); ((IList)(yystack.valueAt (0))).add(((ILexem)(yystack.valueAt (1))));};
  break;


  case 32: /* VariableDeclarations: NEWLINE VariableDeclarations  */
  if (yyn == 32)
    /* "parser.y":197  */
                                   { yyval = ((IList)(yystack.valueAt (0))); };
  break;


  case 33: /* ArrayType: ARRAY LBRACKET Expression RBRACKET Type  */
  if (yyn == 33)
    /* "parser.y":202  */
                                              { yyval = new ArrayType(((Expression)(yystack.valueAt (2))), ((Type)(yystack.valueAt (0)))); };
  break;


  case 34: /* Body: %empty  */
  if (yyn == 34)
    /* "parser.y":206  */
                  { yyval = new Body();};
  break;


  case 35: /* Body: BodyDeclaration Body  */
  if (yyn == 35)
    /* "parser.y":207  */
                           {yyval = ((Body)(yystack.valueAt (0))); ((Body)(yystack.valueAt (0))).addBody(((ILexem)(yystack.valueAt (1))));};
  break;


  case 36: /* BodyDeclaration: SimpleDeclaration  */
  if (yyn == 36)
    /* "parser.y":211  */
                        {yyval = new BodyDeclaration(((ILexem)(yystack.valueAt (0))));};
  break;


  case 37: /* BodyDeclaration: Statement  */
  if (yyn == 37)
    /* "parser.y":212  */
                {yyval = new BodyDeclaration(((ILexem)(yystack.valueAt (0))));};
  break;


  case 38: /* Statement: Assignment OptionalSemicolon  */
  if (yyn == 38)
    /* "parser.y":216  */
                                   {yyval = ((ILexem)(yystack.valueAt (1)));};
  break;


  case 39: /* Statement: RoutineCall OptionalSemicolon  */
  if (yyn == 39)
    /* "parser.y":217  */
                                    {yyval = ((ILexem)(yystack.valueAt (1))); };
  break;


  case 40: /* Statement: WhileLoop OptionalSemicolon  */
  if (yyn == 40)
    /* "parser.y":218  */
                                  {yyval = ((ILexem)(yystack.valueAt (1)));};
  break;


  case 41: /* Statement: ForLoop OptionalSemicolon  */
  if (yyn == 41)
    /* "parser.y":219  */
                                {yyval = ((ILexem)(yystack.valueAt (1)));};
  break;


  case 42: /* Statement: IfStatement OptionalSemicolon  */
  if (yyn == 42)
    /* "parser.y":220  */
                                   {yyval = ((ILexem)(yystack.valueAt (1)));};
  break;


  case 43: /* Statement: Print OptionalSemicolon  */
  if (yyn == 43)
    /* "parser.y":221  */
                              {yyval = ((ILexem)(yystack.valueAt (1)));};
  break;


  case 44: /* Statement: Return OptionalSemicolon  */
  if (yyn == 44)
    /* "parser.y":222  */
                               {yyval = ((ILexem)(yystack.valueAt (1)));};
  break;


  case 46: /* Assignment: ModifiablePrimary ASSIGN Expression  */
  if (yyn == 46)
    /* "parser.y":227  */
                                          {yyval = new Assignment(((ModifiablePrimary)(yystack.valueAt (2))), ((Expression)(yystack.valueAt (0))));};
  break;


  case 47: /* RoutineCall: IDENTIFIER LPAREN Expressions RPAREN  */
  if (yyn == 47)
    /* "parser.y":231  */
                                           {yyval = new RoutineCall(((Identifier)(yystack.valueAt (3))), ((Expressions)(yystack.valueAt (1)))); };
  break;


  case 48: /* Expressions: Expression  */
  if (yyn == 48)
    /* "parser.y":235  */
                 { yyval = new Expressions(((Expression)(yystack.valueAt (0)))); };
  break;


  case 49: /* Expressions: Expression COMMA Expressions  */
  if (yyn == 49)
    /* "parser.y":236  */
                                   { yyval = ((Expressions)(yystack.valueAt (0))); ((Expressions)(yystack.valueAt (0))).add(((Expression)(yystack.valueAt (2)))); };
  break;


  case 50: /* WhileLoop: WHILE Expression LOOP Body END  */
  if (yyn == 50)
    /* "parser.y":240  */
                                     {yyval = new WhileLoop(((Expression)(yystack.valueAt (3))), ((Body)(yystack.valueAt (1)))); };
  break;


  case 51: /* ForLoop: FOR IDENTIFIER IN Expression RANGE Expression LOOP Body END  */
  if (yyn == 51)
    /* "parser.y":245  */
    {yyval = new ForLoop(((Identifier)(yystack.valueAt (7))), ((Expression)(yystack.valueAt (5))), ((Expression)(yystack.valueAt (3))), ((Body)(yystack.valueAt (1))), false);};
  break;


  case 52: /* ForLoop: FOR IDENTIFIER IN REVERSE Expression RANGE Expression LOOP Body END  */
  if (yyn == 52)
    /* "parser.y":247  */
    {yyval = new ForLoop(((Identifier)(yystack.valueAt (8))), ((Expression)(yystack.valueAt (5))), ((Expression)(yystack.valueAt (3))), ((Body)(yystack.valueAt (1))), true);};
  break;


  case 53: /* IfStatement: IF Expression THEN Body END  */
  if (yyn == 53)
    /* "parser.y":251  */
                                  {yyval = new IfStatement(((Expression)(yystack.valueAt (3))), ((Body)(yystack.valueAt (1))));};
  break;


  case 54: /* IfStatement: IF Expression THEN Body ELSE Body END  */
  if (yyn == 54)
    /* "parser.y":252  */
                                            {yyval = new IfStatement(((Expression)(yystack.valueAt (5))), ((Body)(yystack.valueAt (3))), ((Body)(yystack.valueAt (1))));};
  break;


  case 55: /* Expression: Relation Relations  */
  if (yyn == 55)
    /* "parser.y":257  */
                         { yyval = ((IList2)(yystack.valueAt (0))); ((IList2)(yystack.valueAt (0))).add(((IRelation)(yystack.valueAt (1)))); };
  break;


  case 56: /* Expression: SummandSign Relation Relations  */
  if (yyn == 56)
    /* "parser.y":258  */
                                     { yyval = ((IList2)(yystack.valueAt (0)));  ((IRelation)(yystack.valueAt (1))).setSummandSign(((SummandSign)(yystack.valueAt (2)))); ((IList2)(yystack.valueAt (0))).add(((IRelation)(yystack.valueAt (1)))); };
  break;


  case 57: /* Expression: NOT Relation Relations  */
  if (yyn == 57)
    /* "parser.y":259  */
                             { yyval = ((IList2)(yystack.valueAt (0))); ((IRelation)(yystack.valueAt (1))).setNot(); ((IList2)(yystack.valueAt (0))).add(((IRelation)(yystack.valueAt (1)))); };
  break;


  case 58: /* Expression: NOT SummandSign Relation Relations  */
  if (yyn == 58)
    /* "parser.y":260  */
                                         { yyval = ((IList2)(yystack.valueAt (0))); ((IRelation)(yystack.valueAt (1))).setSummandSign(((SummandSign)(yystack.valueAt (2)))); ((IRelation)(yystack.valueAt (1))).setNot(); ((IList2)(yystack.valueAt (0))).add(((IRelation)(yystack.valueAt (1)))); };
  break;


  case 59: /* Relations: %empty  */
  if (yyn == 59)
    /* "parser.y":264  */
                   { yyval = new Expression();};
  break;


  case 60: /* Relations: LogicWord Relation Relations  */
  if (yyn == 60)
    /* "parser.y":265  */
                                   { yyval = ((IList2)(yystack.valueAt (0))); ((IList2)(yystack.valueAt (0))).add2(((IRelation)(yystack.valueAt (1))), ((ILexem)(yystack.valueAt (2)))); };
  break;


  case 61: /* Relations: LogicWord NOT Relation Relations  */
  if (yyn == 61)
    /* "parser.y":266  */
                                       {yyval = ((IList2)(yystack.valueAt (0))); ((IRelation)(yystack.valueAt (1))).setNot(); ((IList2)(yystack.valueAt (0))).add2(((IRelation)(yystack.valueAt (1))), ((ILexem)(yystack.valueAt (3))));};
  break;


  case 62: /* LogicWord: AND  */
  if (yyn == 62)
    /* "parser.y":272  */
          { yyval = new LogicWord("and"); };
  break;


  case 63: /* LogicWord: OR  */
  if (yyn == 63)
    /* "parser.y":273  */
         { yyval = new LogicWord("or"); };
  break;


  case 64: /* LogicWord: XOR  */
  if (yyn == 64)
    /* "parser.y":274  */
          { yyval = new LogicWord("xor"); };
  break;


  case 65: /* Relation: Simple SimpleTail  */
  if (yyn == 65)
    /* "parser.y":278  */
                        { yyval = ((IList2)(yystack.valueAt (0))); ((IList2)(yystack.valueAt (0))).add(((IList2)(yystack.valueAt (1)))); };
  break;


  case 66: /* SimpleTail: %empty  */
  if (yyn == 66)
    /* "parser.y":283  */
                  { yyval = new IRelation(); };
  break;


  case 67: /* SimpleTail: RelationSign Simple SimpleTail  */
  if (yyn == 67)
    /* "parser.y":284  */
                                     { yyval = ((IList2)(yystack.valueAt (0))); ((IList2)(yystack.valueAt (0))).add2(((IList2)(yystack.valueAt (1))), ((ILexem)(yystack.valueAt (2)))); };
  break;


  case 68: /* RelationSign: LESS  */
  if (yyn == 68)
    /* "parser.y":289  */
           { yyval = new RelationSign("<"); };
  break;


  case 69: /* RelationSign: GREATER  */
  if (yyn == 69)
    /* "parser.y":290  */
              { yyval = new RelationSign(">"); };
  break;


  case 70: /* RelationSign: LEQUALS  */
  if (yyn == 70)
    /* "parser.y":291  */
              { yyval = new RelationSign("<="); };
  break;


  case 71: /* RelationSign: GEQUALS  */
  if (yyn == 71)
    /* "parser.y":292  */
              { yyval = new RelationSign(">="); };
  break;


  case 72: /* RelationSign: EQUALS  */
  if (yyn == 72)
    /* "parser.y":293  */
             { yyval =  new RelationSign("="); };
  break;


  case 73: /* RelationSign: NEQUALS  */
  if (yyn == 73)
    /* "parser.y":294  */
              { yyval = new RelationSign("/="); };
  break;


  case 74: /* Simple: Factor FactorTail  */
  if (yyn == 74)
    /* "parser.y":298  */
                        { yyval = ((IList2)(yystack.valueAt (0))); ((IList2)(yystack.valueAt (0))).add(((ILexem)(yystack.valueAt (1)))); };
  break;


  case 75: /* FactorTail: %empty  */
  if (yyn == 75)
    /* "parser.y":302  */
                  {yyval = new Simple(); };
  break;


  case 76: /* FactorTail: FactorSign Factor FactorTail  */
  if (yyn == 76)
    /* "parser.y":303  */
                                   { yyval = ((IList2)(yystack.valueAt (0))); ((IList2)(yystack.valueAt (0))).add2(((ILexem)(yystack.valueAt (1))), ((ILexem)(yystack.valueAt (2)))); };
  break;


  case 77: /* FactorSign: MULTIPLY  */
  if (yyn == 77)
    /* "parser.y":307  */
               { yyval = new FactorSign("*"); };
  break;


  case 78: /* FactorSign: DIVIDE  */
  if (yyn == 78)
    /* "parser.y":308  */
             { yyval = new FactorSign("/"); };
  break;


  case 79: /* FactorSign: REMAINDER  */
  if (yyn == 79)
    /* "parser.y":309  */
                { yyval = new FactorSign("%"); };
  break;


  case 80: /* Factor: Summand SummandTail  */
  if (yyn == 80)
    /* "parser.y":313  */
                          { yyval = ((IList2)(yystack.valueAt (0))); ((IList2)(yystack.valueAt (0))).add(((ILexem)(yystack.valueAt (1)))); };
  break;


  case 81: /* SummandTail: %empty  */
  if (yyn == 81)
    /* "parser.y":317  */
                  { yyval = new Factor(); };
  break;


  case 82: /* SummandTail: SummandSign Summand SummandTail  */
  if (yyn == 82)
    /* "parser.y":318  */
                                      { yyval = ((IList2)(yystack.valueAt (0))); ((IList2)(yystack.valueAt (0))).add2(((ILexem)(yystack.valueAt (1))), ((SummandSign)(yystack.valueAt (2)))); };
  break;


  case 83: /* SummandSign: PLUS  */
  if (yyn == 83)
    /* "parser.y":322  */
           { yyval = new SummandSign("+"); };
  break;


  case 84: /* SummandSign: MINUS  */
  if (yyn == 84)
    /* "parser.y":323  */
            { yyval = new SummandSign("-"); };
  break;


  case 85: /* Summand: Primary  */
  if (yyn == 85)
    /* "parser.y":327  */
              { yyval = new Summand(((ILexem)(yystack.valueAt (0)))); };
  break;


  case 86: /* Summand: LPAREN Expression RPAREN  */
  if (yyn == 86)
    /* "parser.y":328  */
                               { yyval = new Summand(((Expression)(yystack.valueAt (1)))); };
  break;


  case 87: /* Primary: INTEGER_LITERAL  */
  if (yyn == 87)
    /* "parser.y":332  */
                      { yyval = ((IntegerLiteral)(yystack.valueAt (0)));};
  break;


  case 88: /* Primary: REAL_LITERAL  */
  if (yyn == 88)
    /* "parser.y":333  */
                   { yyval = ((RealLiteral)(yystack.valueAt (0))); };
  break;


  case 89: /* Primary: TRUE  */
  if (yyn == 89)
    /* "parser.y":334  */
           { yyval = true; };
  break;


  case 90: /* Primary: FALSE  */
  if (yyn == 90)
    /* "parser.y":335  */
            { yyval = false; };
  break;


  case 91: /* Primary: ModifiablePrimary  */
  if (yyn == 91)
    /* "parser.y":336  */
                        { yyval = ((ModifiablePrimary)(yystack.valueAt (0))); };
  break;


  case 92: /* Primary: RoutineCall  */
  if (yyn == 92)
    /* "parser.y":337  */
                  { yyval = ((ILexem)(yystack.valueAt (0)));};
  break;


  case 93: /* ModifiablePrimary: IDENTIFIER ElementCall  */
  if (yyn == 93)
    /* "parser.y":341  */
                             { yyval = new ModifiablePrimary(((Identifier)(yystack.valueAt (1))), ((IList)(yystack.valueAt (0)))); };
  break;


  case 94: /* ElementCall: %empty  */
  if (yyn == 94)
    /* "parser.y":345  */
                  { yyval = new ElementCall(); };
  break;


  case 95: /* ElementCall: DOT IDENTIFIER ElementCall  */
  if (yyn == 95)
    /* "parser.y":346  */
                                 { yyval = ((IList)(yystack.valueAt (0))); ((IList)(yystack.valueAt (0))).add(((Identifier)(yystack.valueAt (1)))); };
  break;


  case 96: /* ElementCall: LBRACKET Expression RBRACKET ElementCall  */
  if (yyn == 96)
    /* "parser.y":347  */
                                               { yyval = ((IList)(yystack.valueAt (0))); ((IList)(yystack.valueAt (0))).add(((Expression)(yystack.valueAt (2))));};
  break;


  case 97: /* Print: PRINT LPAREN Expressions RPAREN  */
  if (yyn == 97)
    /* "parser.y":351  */
                                      {yyval = new Print(((Expressions)(yystack.valueAt (1))));};
  break;



/* "YYParser.java":1316  */

        default: break;
      }

    yystack.pop(yylen);
    yylen = 0;
    /* Shift the result of the reduction.  */
    int yystate = yyLRGotoState(yystack.stateAt(0), yyr1_[yyn]);
    yystack.push(yystate, yyval);
    return YYNEWSTATE;
  }




  /**
   * Parse input from the scanner that was specified at object construction
   * time.  Return whether the end of the input was reached successfully.
   *
   * @return <tt>true</tt> if the parsing succeeds.  Note that this does not
   *          imply that there were no syntax errors.
   */
  public boolean parse()

  {


    /* Lookahead token kind.  */
    int yychar = YYEMPTY_;
    /* Lookahead symbol kind.  */
    SymbolKind yytoken = null;

    /* State.  */
    int yyn = 0;
    int yylen = 0;
    int yystate = 0;
    YYStack yystack = new YYStack ();
    int label = YYNEWSTATE;



    /* Semantic value of the lookahead.  */
    Object yylval = null;

    yyerrstatus_ = 0;
    yynerrs = 0;

    /* Initialize the stack.  */
    yystack.push (yystate, yylval);



    for (;;)
      switch (label)
      {
        /* New state.  Unlike in the C/C++ skeletons, the state is already
           pushed when we come here.  */
      case YYNEWSTATE:

        /* Accept?  */
        if (yystate == YYFINAL_)
          return true;

        /* Take a decision.  First try without lookahead.  */
        yyn = yypact_[yystate];
        if (yyPactValueIsDefault (yyn))
          {
            label = YYDEFAULT;
            break;
          }

        /* Read a lookahead token.  */
        if (yychar == YYEMPTY_)
          {

            yychar = yylexer.yylex ();
            yylval = yylexer.getLVal();

          }

        /* Convert token to internal form.  */
        yytoken = yytranslate_ (yychar);

        if (yytoken == SymbolKind.S_YYerror)
          {
            // The scanner already issued an error message, process directly
            // to error recovery.  But do not keep the error token as
            // lookahead, it is too special and may lead us to an endless
            // loop in error recovery. */
            yychar = Lexer.YYUNDEF;
            yytoken = SymbolKind.S_YYUNDEF;
            label = YYERRLAB1;
          }
        else
          {
            /* If the proper action on seeing token YYTOKEN is to reduce or to
               detect an error, take that action.  */
            yyn += yytoken.getCode();
            if (yyn < 0 || YYLAST_ < yyn || yycheck_[yyn] != yytoken.getCode())
              label = YYDEFAULT;

            /* <= 0 means reduce or error.  */
            else if ((yyn = yytable_[yyn]) <= 0)
              {
                if (yyTableValueIsError (yyn))
                  label = YYERRLAB;
                else
                  {
                    yyn = -yyn;
                    label = YYREDUCE;
                  }
              }

            else
              {
                /* Shift the lookahead token.  */
                /* Discard the token being shifted.  */
                yychar = YYEMPTY_;

                /* Count tokens shifted since error; after three, turn off error
                   status.  */
                if (yyerrstatus_ > 0)
                  --yyerrstatus_;

                yystate = yyn;
                yystack.push (yystate, yylval);
                label = YYNEWSTATE;
              }
          }
        break;

      /*-----------------------------------------------------------.
      | yydefault -- do the default action for the current state.  |
      `-----------------------------------------------------------*/
      case YYDEFAULT:
        yyn = yydefact_[yystate];
        if (yyn == 0)
          label = YYERRLAB;
        else
          label = YYREDUCE;
        break;

      /*-----------------------------.
      | yyreduce -- Do a reduction.  |
      `-----------------------------*/
      case YYREDUCE:
        yylen = yyr2_[yyn];
        label = yyaction(yyn, yystack, yylen);
        yystate = yystack.stateAt (0);
        break;

      /*------------------------------------.
      | yyerrlab -- here on detecting error |
      `------------------------------------*/
      case YYERRLAB:
        /* If not already recovering from an error, report this error.  */
        if (yyerrstatus_ == 0)
          {
            ++yynerrs;
            if (yychar == YYEMPTY_)
              yytoken = null;
            yyreportSyntaxError (new Context (yystack, yytoken));
          }

        if (yyerrstatus_ == 3)
          {
            /* If just tried and failed to reuse lookahead token after an
               error, discard it.  */

            if (yychar <= Lexer.YYEOF)
              {
                /* Return failure if at end of input.  */
                if (yychar == Lexer.YYEOF)
                  return false;
              }
            else
              yychar = YYEMPTY_;
          }

        /* Else will try to reuse lookahead token after shifting the error
           token.  */
        label = YYERRLAB1;
        break;

      /*-------------------------------------------------.
      | errorlab -- error raised explicitly by YYERROR.  |
      `-------------------------------------------------*/
      case YYERROR:
        /* Do not reclaim the symbols of the rule which action triggered
           this YYERROR.  */
        yystack.pop (yylen);
        yylen = 0;
        yystate = yystack.stateAt (0);
        label = YYERRLAB1;
        break;

      /*-------------------------------------------------------------.
      | yyerrlab1 -- common code for both syntax error and YYERROR.  |
      `-------------------------------------------------------------*/
      case YYERRLAB1:
        yyerrstatus_ = 3;       /* Each real token shifted decrements this.  */

        // Pop stack until we find a state that shifts the error token.
        for (;;)
          {
            yyn = yypact_[yystate];
            if (!yyPactValueIsDefault (yyn))
              {
                yyn += SymbolKind.S_YYerror.getCode();
                if (0 <= yyn && yyn <= YYLAST_
                    && yycheck_[yyn] == SymbolKind.S_YYerror.getCode())
                  {
                    yyn = yytable_[yyn];
                    if (0 < yyn)
                      break;
                  }
              }

            /* Pop the current state because it cannot handle the
             * error token.  */
            if (yystack.height == 0)
              return false;


            yystack.pop ();
            yystate = yystack.stateAt (0);
          }

        if (label == YYABORT)
          /* Leave the switch.  */
          break;



        /* Shift the error token.  */

        yystate = yyn;
        yystack.push (yyn, yylval);
        label = YYNEWSTATE;
        break;

        /* Accept.  */
      case YYACCEPT:
        return true;

        /* Abort.  */
      case YYABORT:
        return false;
      }
}




  /**
   * Information needed to get the list of expected tokens and to forge
   * a syntax error diagnostic.
   */
  public static final class Context
  {
    Context (YYStack stack, SymbolKind token)
    {
      yystack = stack;
      yytoken = token;
    }

    private YYStack yystack;


    /**
     * The symbol kind of the lookahead token.
     */
    public final SymbolKind getToken ()
    {
      return yytoken;
    }

    private SymbolKind yytoken;
    static final int NTOKENS = YYParser.YYNTOKENS_;

    /**
     * Put in YYARG at most YYARGN of the expected tokens given the
     * current YYCTX, and return the number of tokens stored in YYARG.  If
     * YYARG is null, return the number of expected tokens (guaranteed to
     * be less than YYNTOKENS).
     */
    int getExpectedTokens (SymbolKind yyarg[], int yyargn)
    {
      return getExpectedTokens (yyarg, 0, yyargn);
    }

    int getExpectedTokens (SymbolKind yyarg[], int yyoffset, int yyargn)
    {
      int yycount = yyoffset;
      int yyn = yypact_[this.yystack.stateAt (0)];
      if (!yyPactValueIsDefault (yyn))
        {
          /* Start YYX at -YYN if negative to avoid negative
             indexes in YYCHECK.  In other words, skip the first
             -YYN actions for this state because they are default
             actions.  */
          int yyxbegin = yyn < 0 ? -yyn : 0;
          /* Stay within bounds of both yycheck and yytname.  */
          int yychecklim = YYLAST_ - yyn + 1;
          int yyxend = yychecklim < NTOKENS ? yychecklim : NTOKENS;
          for (int yyx = yyxbegin; yyx < yyxend; ++yyx)
            if (yycheck_[yyx + yyn] == yyx && yyx != SymbolKind.S_YYerror.getCode()
                && !yyTableValueIsError(yytable_[yyx + yyn]))
              {
                if (yyarg == null)
                  yycount += 1;
                else if (yycount == yyargn)
                  return 0; // FIXME: this is incorrect.
                else
                  yyarg[yycount++] = SymbolKind.get(yyx);
              }
        }
      if (yyarg != null && yycount == yyoffset && yyoffset < yyargn)
        yyarg[yycount] = null;
      return yycount - yyoffset;
    }
  }



  /**
   * Build and emit a "syntax error" message in a user-defined way.
   *
   * @param ctx  The context of the error.
   */
  private void yyreportSyntaxError(Context yyctx) {
      yyerror("syntax error");
  }

  /**
   * Whether the given <code>yypact_</code> value indicates a defaulted state.
   * @param yyvalue   the value to check
   */
  private static boolean yyPactValueIsDefault (int yyvalue)
  {
    return yyvalue == yypact_ninf_;
  }

  /**
   * Whether the given <code>yytable_</code>
   * value indicates a syntax error.
   * @param yyvalue the value to check
   */
  private static boolean yyTableValueIsError (int yyvalue)
  {
    return yyvalue == yytable_ninf_;
  }

  private static final short yypact_ninf_ = -109;
  private static final short yytable_ninf_ = -1;

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short[] yypact_ = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
      19,    11,    26,    39,  -109,    54,  -109,    19,  -109,  -109,
    -109,  -109,    -2,    57,    41,  -109,  -109,   134,   104,   104,
      70,    52,  -109,  -109,  -109,  -109,   139,   134,  -109,  -109,
    -109,    25,    83,   154,   101,    18,    30,  -109,  -109,  -109,
      58,  -109,  -109,  -109,    48,    16,  -109,  -109,  -109,  -109,
      61,    88,    67,   134,   134,   121,  -109,    83,    18,    91,
    -109,  -109,  -109,  -109,  -109,  -109,  -109,    13,  -109,  -109,
    -109,  -109,  -109,  -109,  -109,    18,  -109,  -109,  -109,  -109,
      18,    83,  -109,    18,    23,   134,   134,  -109,   104,    -1,
      70,    93,    97,   112,    86,  -109,    83,  -109,    18,    83,
     154,   101,  -109,    30,    23,    23,   145,   113,    25,  -109,
     163,   104,  -109,  -109,   134,    86,  -109,  -109,    83,  -109,
    -109,  -109,  -109,  -109,  -109,  -109,   104,  -109,   134,   156,
     134,   123,   134,  -109,  -109,    25,   149,   163,  -109,    25,
      25,    25,    25,    25,   120,    25,   157,  -109,  -109,  -109,
    -109,   146,   150,   151,   134,  -109,  -109,    25,  -109,  -109,
    -109,  -109,  -109,  -109,   134,  -109,   163,   163,   127,   163,
     140,  -109,  -109,   167,   168,   134,   162,    32,  -109,    25,
    -109,   164,   134,  -109,   163,  -109,   134,   169,   170,   172,
     163,  -109,   163,   175,   180,  -109,  -109
    };
  }

/* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
   Performed when YYTABLE does not specify something else to do.  Zero
   means the default is an error.  */
  private static final byte[] yydefact_ = yydefact_init();
  private static final byte[] yydefact_init()
  {
    return new byte[]
    {
       3,     0,     0,     0,     7,     0,     2,     3,     5,     8,
       9,     6,     0,     0,     0,     1,     4,     0,     0,     0,
       0,    94,    87,    88,    89,    90,     0,     0,    83,    84,
      92,     0,    59,    66,    75,     0,    81,    85,    91,    25,
       0,    26,    27,    28,     0,     0,    22,    24,    23,    15,
       0,     0,    19,     0,     0,     0,    93,    59,     0,     0,
      13,    14,    12,    62,    63,    64,    55,     0,    72,    73,
      69,    68,    70,    71,    65,     0,    77,    78,    79,    74,
       0,    59,    80,     0,    30,     0,     0,    10,     0,     0,
       0,     0,    48,     0,    94,    57,    59,    86,     0,    59,
      66,    75,    56,    81,    30,    30,     0,     0,     0,    21,
      34,     0,    20,    47,     0,    94,    95,    58,    59,    60,
      67,    76,    82,    32,    31,    29,     0,    11,     0,     0,
       0,     0,     0,    45,    36,     0,     0,    34,    37,     0,
       0,     0,     0,     0,     0,     0,     0,    49,    96,    61,
      33,     0,     0,     0,     0,    18,    44,     0,    35,    38,
      39,    40,    41,    42,     0,    43,    34,    34,     0,    34,
       0,    16,    46,     0,     0,     0,     0,     0,    97,     0,
      50,     0,     0,    53,    34,    17,     0,     0,     0,     0,
      34,    54,    34,     0,     0,    51,    52
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short[] yypgoto_ = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -109,  -109,   185,  -109,    71,   -72,   -43,  -109,  -109,  -109,
     107,  -109,   -18,  -109,  -109,   -10,  -109,   -63,  -109,  -109,
    -109,  -107,  -108,  -109,  -109,  -109,   -17,   -46,  -109,   -22,
     106,  -109,   129,   108,  -109,   128,   109,   -21,   124,  -109,
    -103,   -54,  -109
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final short[] yydefgoto_ = yydefgoto_init();
  private static final short[] yydefgoto_init()
  {
    return new short[]
    {
      -1,     5,     6,     7,   134,     9,    62,    10,    11,   135,
      51,    52,    45,    46,    47,   106,    48,   136,   137,   138,
     139,    30,    91,   141,   142,   143,    92,    66,    67,    32,
      74,    75,    33,    79,    80,    34,    82,    35,    36,    37,
      38,    56,   145
    };
  }

/* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule whose
   number is the opposite.  If YYTABLE_NINF, syntax error.  */
  private static final short[] yytable_ = yytable_init();
  private static final short[] yytable_init()
  {
    return new short[]
    {
      31,    49,    87,   140,    57,    58,   147,   144,    17,   110,
      59,    95,   105,    81,    12,    83,    21,    22,    23,    24,
      25,    21,    22,    23,    24,    25,    86,     1,     2,    13,
     140,     1,   105,   105,   144,   102,    96,    93,    18,   111,
     116,    98,    14,   183,     3,    99,   170,    27,    60,    61,
     117,     4,    27,   119,    15,   104,   184,    60,    61,   140,
     140,   148,   140,   144,   144,   127,   144,    19,   107,   108,
     109,     8,   149,    50,   158,    20,   118,   140,     8,    28,
      29,   144,    83,   140,    85,   140,    53,   144,    54,   144,
      84,    55,   156,   146,   123,   124,   159,   160,   161,   162,
     163,    88,   165,   173,   174,    90,   177,    39,   150,    63,
      64,   151,    65,   153,   171,   155,    40,    41,    42,    43,
      44,   188,    54,    89,    94,    55,    97,   193,   113,   194,
      21,    22,    23,    24,    25,   114,   185,    21,    22,    23,
      24,    25,    21,    22,    23,    24,    25,   172,   175,   115,
     126,   176,    76,    77,    78,    26,   125,   154,   181,   152,
     157,    27,    26,   164,   167,   187,    21,   166,    27,   189,
     168,     1,     2,    27,   169,   178,    28,    29,   179,   180,
     128,   191,   129,    28,    29,   130,   195,   190,    28,    29,
     192,   196,    16,   131,   132,   133,    68,   112,    69,    70,
      71,    72,    73,   182,   100,   186,   120,   103,   101,   121,
       0,     0,   122
    };
  }

private static final short[] yycheck_ = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
      17,    19,    45,   110,    26,    26,   114,   110,    10,    10,
      27,    57,    84,    35,     3,    36,     3,     4,     5,     6,
       7,     3,     4,     5,     6,     7,    10,     8,     9,     3,
     137,     8,   104,   105,   137,    81,    58,    54,    40,    40,
      94,    28,     3,    11,    25,    67,   154,    34,    32,    33,
      96,    32,    34,    99,     0,    32,    24,    32,    33,   166,
     167,   115,   169,   166,   167,   108,   169,    10,    85,    86,
      88,     0,   118,     3,   137,    34,    98,   184,     7,    49,
      50,   184,   103,   190,    36,   192,    34,   190,    36,   192,
      32,    39,   135,   111,   104,   105,   139,   140,   141,   142,
     143,    40,   145,   166,   167,    38,   169,     3,   126,    26,
      27,   128,    29,   130,   157,   132,    12,    13,    14,    15,
      16,   184,    36,    35,     3,    39,    35,   190,    35,   192,
       3,     4,     5,     6,     7,    38,   179,     3,     4,     5,
       6,     7,     3,     4,     5,     6,     7,   164,    21,    37,
      37,   168,    51,    52,    53,    28,    11,    34,   175,     3,
      11,    34,    28,    43,    18,   182,     3,    10,    34,   186,
      20,     8,     9,    34,    23,    35,    49,    50,    11,    11,
      17,    11,    19,    49,    50,    22,    11,    18,    49,    50,
      18,    11,     7,    30,    31,    32,    42,    90,    44,    45,
      46,    47,    48,    41,    75,    41,   100,    83,    80,   101,
      -1,    -1,   103
    };
  }

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
  private static final byte[] yystos_ = yystos_init();
  private static final byte[] yystos_init()
  {
    return new byte[]
    {
       0,     8,     9,    25,    32,    55,    56,    57,    58,    59,
      61,    62,     3,     3,     3,     0,    56,    10,    40,    10,
      34,     3,     4,     5,     6,     7,    28,    34,    49,    50,
      75,    80,    83,    86,    89,    91,    92,    93,    94,     3,
      12,    13,    14,    15,    16,    66,    67,    68,    70,    66,
       3,    64,    65,    34,    36,    39,    95,    83,    91,    80,
      32,    33,    60,    26,    27,    29,    81,    82,    42,    44,
      45,    46,    47,    48,    84,    85,    51,    52,    53,    87,
      88,    83,    90,    91,    32,    36,    10,    60,    40,    35,
      38,    76,    80,    80,     3,    81,    83,    35,    28,    83,
      86,    89,    81,    92,    32,    59,    69,    80,    80,    66,
      10,    40,    64,    35,    38,    37,    95,    81,    83,    81,
      84,    87,    90,    69,    69,    11,    37,    60,    17,    19,
      22,    30,    31,    32,    58,    63,    71,    72,    73,    74,
      75,    77,    78,    79,    94,    96,    66,    76,    95,    81,
      66,    80,     3,    80,    34,    80,    60,    11,    71,    60,
      60,    60,    60,    60,    43,    60,    10,    18,    20,    23,
      76,    60,    80,    71,    71,    21,    80,    71,    35,    11,
      11,    80,    41,    11,    24,    60,    41,    80,    71,    80,
      18,    11,    18,    71,    71,    11,    11
    };
  }

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
  private static final byte[] yyr1_ = yyr1_init();
  private static final byte[] yyr1_init()
  {
    return new byte[]
    {
       0,    54,    55,    56,    56,    57,    57,    57,    58,    58,
      59,    59,    59,    60,    60,    61,    62,    62,    63,    64,
      64,    65,    66,    66,    66,    66,    67,    67,    67,    68,
      69,    69,    69,    70,    71,    71,    72,    72,    73,    73,
      73,    73,    73,    73,    73,    73,    74,    75,    76,    76,
      77,    78,    78,    79,    79,    80,    80,    80,    80,    81,
      81,    81,    82,    82,    82,    83,    84,    84,    85,    85,
      85,    85,    85,    85,    86,    87,    87,    88,    88,    88,
      89,    90,    90,    91,    91,    92,    92,    93,    93,    93,
      93,    93,    93,    94,    95,    95,    95,    96
    };
  }

/* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
  private static final byte[] yyr2_ = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     1,     0,     2,     1,     1,     1,     1,     1,
       5,     7,     5,     1,     1,     4,     9,    11,     2,     1,
       3,     3,     1,     1,     1,     1,     1,     1,     1,     4,
       0,     2,     2,     5,     0,     2,     1,     1,     2,     2,
       2,     2,     2,     2,     2,     1,     3,     4,     1,     3,
       5,     9,    10,     5,     7,     2,     3,     3,     4,     0,
       3,     4,     1,     1,     1,     2,     0,     3,     1,     1,
       1,     1,     1,     1,     2,     0,     3,     1,     1,     1,
       2,     0,     3,     1,     1,     1,     3,     1,     1,     1,
       1,     1,     1,     2,     0,     3,     4,     4
    };
  }




  /* YYTRANSLATE_(TOKEN-NUM) -- Symbol number corresponding to TOKEN-NUM
     as returned by yylex, with out-of-bounds checking.  */
  private static final SymbolKind yytranslate_(int t)
  {
    // Last valid token kind.
    int code_max = 308;
    if (t <= 0)
      return SymbolKind.S_YYEOF;
    else if (t <= code_max)
      return SymbolKind.get(yytranslate_table_[t]);
    else
      return SymbolKind.S_YYUNDEF;
  }
  private static final byte[] yytranslate_table_ = yytranslate_table_init();
  private static final byte[] yytranslate_table_init()
  {
    return new byte[]
    {
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40,    41,    42,    43,    44,
      45,    46,    47,    48,    49,    50,    51,    52,    53
    };
  }


  private static final int YYLAST_ = 212;
  private static final int YYEMPTY_ = -2;
  private static final int YYFINAL_ = 15;
  private static final int YYNTOKENS_ = 54;

/* Unqualified %code blocks.  */
/* "parser.y":16  */

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

/* "YYParser.java":1971  */

}
/* "parser.y":355  */

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
