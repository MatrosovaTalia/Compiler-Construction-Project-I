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
/* "smallgrammar.y":14  */

import lexems.Identifier;
import lexems.ILexem;
import lexer.*;
import reader.Reader;
import simple.*;
import java.util.ArrayList;

/* "YYParser.java":53  */

/**
 * A Bison parser, automatically generated from <tt>smallgrammar.y</tt>.
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
    S_Type(62),                    /* Type  */
    S_PrimitiveType(63),           /* PrimitiveType  */
    S_Identifier(64);              /* Identifier  */


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
      SymbolKind.S_Type,
      SymbolKind.S_PrimitiveType,
      SymbolKind.S_Identifier
    };

    static final SymbolKind get(int code) {
      return values_[code];
    }

    public final int getCode() {
      return this.yycode_;
    }

    /* YYNAMES_[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
       First, the terminals, then, starting at \a YYNTOKENS_, nonterminals.  */
    private static final String[] yynames_ = yynames_init();
  private static final String[] yynames_init()
  {
    return new String[]
    {
  "end of file", "error", "invalid token", "IDENTIFIER",
  "INTEGER_LITERAL", "REAL_LITERAL", "TRUE", "FALSE", "VAR", "TYPE", "IS",
  "END", "RECORD", "INTEGER", "REAL", "BOOLEAN", "ARRAY", "WHILE", "LOOP",
  "FOR", "IN", "REVERSE", "IF", "THEN", "ELSE", "ROUTINE", "AND", "OR",
  "NOT", "XOR", "PRINT", "RETURN", "NEWLINE", "SEMICOLON", "LPAREN",
  "RPAREN", "LBRACKET", "RBRACKET", "COMMA", "DOT", "COLON", "RANGE",
  "EQUALS", "ASSIGN", "NEQUALS", "GREATER", "LESS", "LEQUALS", "GEQUALS",
  "PLUS", "MINUS", "MULTIPLY", "DIVIDE", "REMAINDER", "$accept", "Program",
  "GlobalDeclarations", "GlobalDeclaration", "SimpleDeclaration",
  "VariableDeclaration", "OptionalSemicolon", "TypeDeclaration", "Type",
  "PrimitiveType", "Identifier", null
    };
  }

    /* The user-facing name of this symbol.  */
    public final String getName() {
      return yynames_[yycode_];
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
    ILexem getLVal();

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


    /**
     * Build and emit a "syntax error" message in a user-defined way.
     *
     * @param ctx  The context of the error.
     */
     void reportSyntaxError (Context ctx);

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


  private java.io.PrintStream yyDebugStream = System.err;

  /**
   * The <tt>PrintStream</tt> on which the debugging output is printed.
   */
  public final java.io.PrintStream getDebugStream () { return yyDebugStream; }

  /**
   * Set the <tt>PrintStream</tt> on which the debug output is printed.
   * @param s The stream that is used for debugging output.
   */
  public final void setDebugStream (java.io.PrintStream s) { yyDebugStream = s; }

  private int yydebug = 0;

  /**
   * Answer the verbosity of the debugging output; 0 means that all kinds of
   * output from the parser are suppressed.
   */
  public final int getDebugLevel () { return yydebug; }

  /**
   * Set the verbosity of the debugging output; 0 means that all kinds of
   * output from the parser are suppressed.
   * @param level The verbosity level for debugging output.
   */
  public final void setDebugLevel (int level) { yydebug = level; }


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


  protected final void yycdebug (String s) {
    if (0 < yydebug)
      yyDebugStream.println (s);
  }

  private final class YYStack {
    private int[] stateStack = new int[16];
    private ILexem[] valueStack = new ILexem[16];

    public int size = 16;
    public int height = -1;

    public final void push (int state, ILexem value) {
      height++;
      if (size == height)
        {
          int[] newStateStack = new int[size * 2];
          System.arraycopy (stateStack, 0, newStateStack, 0, height);
          stateStack = newStateStack;

          ILexem[] newValueStack = new ILexem[size * 2];
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

    public final ILexem valueAt (int i) {
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
    ILexem yyval = (0 < yylen) ? yystack.valueAt(yylen - 1) : yystack.valueAt(0);

    yyReducePrint(yyn, yystack);

    switch (yyn)
      {
          case 2: /* Program: GlobalDeclarations  */
  if (yyn == 2)
    /* "smallgrammar.y":95  */
                         {ast = ((Declarations)(yystack.valueAt (0)));};
  break;


  case 3: /* GlobalDeclarations: %empty  */
  if (yyn == 3)
    /* "smallgrammar.y":99  */
                  { yyval = new Declarations();};
  break;


  case 4: /* GlobalDeclarations: GlobalDeclaration GlobalDeclarations  */
  if (yyn == 4)
    /* "smallgrammar.y":100  */
                                           {yyval = ((Declarations)(yystack.valueAt (0))); ((Declarations)(yystack.valueAt (0))).add(((IDeclaration)(yystack.valueAt (1))));};
  break;


  case 5: /* GlobalDeclaration: SimpleDeclaration  */
  if (yyn == 5)
    /* "smallgrammar.y":104  */
                        {yyval = ((IDeclaration)(yystack.valueAt (0)));};
  break;


  case 6: /* SimpleDeclaration: VariableDeclaration  */
  if (yyn == 6)
    /* "smallgrammar.y":110  */
                          { yyval = ((VariableDeclaration)(yystack.valueAt (0)));};
  break;


  case 7: /* SimpleDeclaration: TypeDeclaration  */
  if (yyn == 7)
    /* "smallgrammar.y":111  */
                      { yyval = ((TypeDeclaration)(yystack.valueAt (0)));};
  break;


  case 8: /* VariableDeclaration: VAR Identifier COLON Type OptionalSemicolon  */
  if (yyn == 8)
    /* "smallgrammar.y":115  */
                                                  {yyval = new VariableDeclaration(((Identifier)(yystack.valueAt (3))), ((IType)(yystack.valueAt (1))));};
  break;


  case 11: /* TypeDeclaration: TYPE Identifier IS Type OptionalSemicolon  */
  if (yyn == 11)
    /* "smallgrammar.y":125  */
                                               {yyval = new TypeDeclaration(((Identifier)(yystack.valueAt (3))), ((IType)(yystack.valueAt (1))));};
  break;


  case 12: /* Type: PrimitiveType  */
  if (yyn == 12)
    /* "smallgrammar.y":130  */
                    { yyval = ((PrimitiveType)(yystack.valueAt (0))); };
  break;


  case 13: /* Type: Identifier  */
  if (yyn == 13)
    /* "smallgrammar.y":133  */
                 { yyval = ((Identifier)(yystack.valueAt (0))); };
  break;


  case 14: /* PrimitiveType: INTEGER  */
  if (yyn == 14)
    /* "smallgrammar.y":137  */
              { yyval = new PrimitiveType("integer");};
  break;


  case 15: /* PrimitiveType: REAL  */
  if (yyn == 15)
    /* "smallgrammar.y":138  */
           { yyval = new PrimitiveType("real"); };
  break;


  case 16: /* PrimitiveType: BOOLEAN  */
  if (yyn == 16)
    /* "smallgrammar.y":139  */
              { yyval = new PrimitiveType("boolean"); };
  break;


  case 17: /* Identifier: IDENTIFIER  */
  if (yyn == 17)
    /* "smallgrammar.y":143  */
                   {yyval = ((Identifier)(yystack.valueAt (0)));};
  break;



/* "YYParser.java":706  */

        default: break;
      }

    yySymbolPrint("-> $$ =", SymbolKind.get(yyr1_[yyn]), yyval);

    yystack.pop(yylen);
    yylen = 0;
    /* Shift the result of the reduction.  */
    int yystate = yyLRGotoState(yystack.stateAt(0), yyr1_[yyn]);
    yystack.push(yystate, yyval);
    return YYNEWSTATE;
  }


  /*--------------------------------.
  | Print this symbol on YYOUTPUT.  |
  `--------------------------------*/

  private void yySymbolPrint(String s, SymbolKind yykind,
                             ILexem yyvalue) {
      if (0 < yydebug) {
          yycdebug(s
                   + (yykind.getCode() < YYNTOKENS_ ? " token " : " nterm ")
                   + yykind.getName() + " ("
                   + (yyvalue == null ? "(null)" : yyvalue.toString()) + ")");
      }
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
    ILexem yylval = null;

    yycdebug ("Starting parse");
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
        yycdebug ("Entering state " + yystate);
        if (0 < yydebug)
          yystack.print (yyDebugStream);

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

            yycdebug ("Reading a token");
            yychar = yylexer.yylex ();
            yylval = yylexer.getLVal();

          }

        /* Convert token to internal form.  */
        yytoken = yytranslate_ (yychar);
        yySymbolPrint("Next token is", yytoken,
                      yylval);

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
                yySymbolPrint("Shifting", yytoken,
                              yylval);

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
            if (0 < yydebug)
              yystack.print (yyDebugStream);
          }

        if (label == YYABORT)
          /* Leave the switch.  */
          break;



        /* Shift the error token.  */
        yySymbolPrint("Shifting", SymbolKind.get(yystos_[yyn]),
                      yylval);

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
      yylexer.reportSyntaxError(yyctx);
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

  private static final byte yypact_ninf_ = -34;
  private static final byte yytable_ninf_ = -1;

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final byte[] yypact_ = yypact_init();
  private static final byte[] yypact_init()
  {
    return new byte[]
    {
      -7,     3,     3,     5,   -34,    -7,   -34,   -34,   -34,   -34,
     -33,    -2,   -34,   -34,    -3,    -3,   -34,   -34,   -34,   -24,
     -34,   -34,   -24,   -34,   -34,   -34
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
       3,     0,     0,     0,     2,     3,     5,     6,     7,    17,
       0,     0,     1,     4,     0,     0,    14,    15,    16,     9,
      12,    13,     9,    10,     8,    11
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final byte[] yypgoto_ = yypgoto_init();
  private static final byte[] yypgoto_init()
  {
    return new byte[]
    {
     -34,   -34,     8,   -34,   -34,   -34,    -8,   -34,     0,   -34,
       2
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final byte[] yydefgoto_ = yydefgoto_init();
  private static final byte[] yydefgoto_init()
  {
    return new byte[]
    {
      -1,     3,     4,     5,     6,     7,    24,     8,    19,    20,
      21
    };
  }

/* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule whose
   number is the opposite.  If YYTABLE_NINF, syntax error.  */
  private static final byte[] yytable_ = yytable_init();
  private static final byte[] yytable_init()
  {
    return new byte[]
    {
       9,     1,     2,    10,    11,    12,     9,    14,    15,    23,
      16,    17,    18,    13,    25,    22
    };
  }

private static final byte[] yycheck_ = yycheck_init();
  private static final byte[] yycheck_init()
  {
    return new byte[]
    {
       3,     8,     9,     1,     2,     0,     3,    40,    10,    33,
      13,    14,    15,     5,    22,    15
    };
  }

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
  private static final byte[] yystos_ = yystos_init();
  private static final byte[] yystos_init()
  {
    return new byte[]
    {
       0,     8,     9,    55,    56,    57,    58,    59,    61,     3,
      64,    64,     0,    56,    40,    10,    13,    14,    15,    62,
      63,    64,    62,    33,    60,    60
    };
  }

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
  private static final byte[] yyr1_ = yyr1_init();
  private static final byte[] yyr1_init()
  {
    return new byte[]
    {
       0,    54,    55,    56,    56,    57,    58,    58,    59,    60,
      60,    61,    62,    62,    63,    63,    63,    64
    };
  }

/* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
  private static final byte[] yyr2_ = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     1,     0,     2,     1,     1,     1,     5,     0,
       1,     5,     1,     1,     1,     1,     1,     1
    };
  }



  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
  private static final short[] yyrline_ = yyrline_init();
  private static final short[] yyrline_init()
  {
    return new short[]
    {
       0,    95,    95,    99,   100,   104,   110,   111,   115,   120,
     121,   125,   130,   133,   137,   138,   139,   143
    };
  }


  // Report on the debug stream that the rule yyrule is going to be reduced.
  private void yyReducePrint (int yyrule, YYStack yystack)
  {
    if (yydebug == 0)
      return;

    int yylno = yyrline_[yyrule];
    int yynrhs = yyr2_[yyrule];
    /* Print the symbols being reduced, and their result.  */
    yycdebug ("Reducing stack by rule " + (yyrule - 1)
              + " (line " + yylno + "):");

    /* The symbols being reduced.  */
    for (int yyi = 0; yyi < yynrhs; yyi++)
      yySymbolPrint("   $" + (yyi + 1) + " =",
                    SymbolKind.get(yystos_[yystack.stateAt (yynrhs - (yyi + 1))]),
                    yystack.valueAt ((yynrhs) - (yyi + 1)));
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


  private static final int YYLAST_ = 15;
  private static final int YYEMPTY_ = -2;
  private static final int YYFINAL_ = 12;
  private static final int YYNTOKENS_ = 54;

/* Unqualified %code blocks.  */
/* "smallgrammar.y":23  */

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

/* "YYParser.java":1307  */

}
/* "smallgrammar.y":146  */

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
