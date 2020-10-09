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

//import lexems.Identifier;
//import lexems.ILexem;
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





  /**
   * A class defining a pair of positions.  Positions, defined by the
   * <code>Position</code> class, denote a point in the input.
   * Locations represent a part of the input through the beginning
   * and ending positions.
   */
  public static class Location {
    /**
     * The first, inclusive, position in the range.
     */
    public Position begin;

    /**
     * The first position beyond the range.
     */
    public Position end;

    /**
     * Create a <code>Location</code> denoting an empty range located at
     * a given point.
     * @param loc The position at which the range is anchored.
     */
    public Location (Position loc) {
      this.begin = this.end = loc;
    }

    /**
     * Create a <code>Location</code> from the endpoints of the range.
     * @param begin The first position included in the range.
     * @param end   The first position beyond the range.
     */
    public Location (Position begin, Position end) {
      this.begin = begin;
      this.end = end;
    }

    /**
     * Print a representation of the location.  For this to be correct,
     * <code>Position</code> should override the <code>equals</code>
     * method.
     */
    public String toString () {
      if (begin.equals (end))
        return begin.toString ();
      else
        return begin.toString () + "-" + end.toString ();
    }
  }

  private Location yylloc(YYStack rhs, int n)
  {
    if (0 < n)
      return new Location(rhs.locationAt(n-1).begin, rhs.locationAt(0).end);
    else
      return new Location(rhs.locationAt(0).end);
  }

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
    S_TypeDeclaration(60),         /* TypeDeclaration  */
    S_RoutineDeclaration(61),      /* RoutineDeclaration  */
    S_Parameters(62),              /* Parameters  */
    S_ParameterDeclaration(63),    /* ParameterDeclaration  */
    S_Body(64),                    /* Body  */
    S_BodyDeclaration(65),         /* BodyDeclaration  */
    S_Statement(66),               /* Statement  */
    S_Assignment(67),              /* Assignment  */
    S_RoutineCall(68),             /* RoutineCall  */
    S_WhileStatement(69),          /* WhileStatement  */
    S_ForLoop(70),                 /* ForLoop  */
    S_IfStatement(71),             /* IfStatement  */
    S_Return(72),                  /* Return  */
    S_Print(73),                   /* Print  */
    S_Expressions(74),             /* Expressions  */
    S_Expression(75),              /* Expression  */
    S_OptionalSemicolon(76),       /* OptionalSemicolon  */
    S_Type(77),                    /* Type  */
    S_PrimitiveType(78),           /* PrimitiveType  */
    S_RecordType(79),              /* RecordType  */
    S_RecordDeclarations(80),      /* RecordDeclarations  */
    S_ArrayType(81),               /* ArrayType  */
    S_ModifiablePrimary(82),       /* ModifiablePrimary  */
    S_ElementCall(83),             /* ElementCall  */
    S_Identifier(84);              /* Identifier  */


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
      SymbolKind.S_TypeDeclaration,
      SymbolKind.S_RoutineDeclaration,
      SymbolKind.S_Parameters,
      SymbolKind.S_ParameterDeclaration,
      SymbolKind.S_Body,
      SymbolKind.S_BodyDeclaration,
      SymbolKind.S_Statement,
      SymbolKind.S_Assignment,
      SymbolKind.S_RoutineCall,
      SymbolKind.S_WhileStatement,
      SymbolKind.S_ForLoop,
      SymbolKind.S_IfStatement,
      SymbolKind.S_Return,
      SymbolKind.S_Print,
      SymbolKind.S_Expressions,
      SymbolKind.S_Expression,
      SymbolKind.S_OptionalSemicolon,
      SymbolKind.S_Type,
      SymbolKind.S_PrimitiveType,
      SymbolKind.S_RecordType,
      SymbolKind.S_RecordDeclarations,
      SymbolKind.S_ArrayType,
      SymbolKind.S_ModifiablePrimary,
      SymbolKind.S_ElementCall,
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
  "VariableDeclaration", "TypeDeclaration", "RoutineDeclaration",
  "Parameters", "ParameterDeclaration", "Body", "BodyDeclaration",
  "Statement", "Assignment", "RoutineCall", "WhileStatement", "ForLoop",
  "IfStatement", "Return", "Print", "Expressions", "Expression",
  "OptionalSemicolon", "Type", "PrimitiveType", "RecordType",
  "RecordDeclarations", "ArrayType", "ModifiablePrimary", "ElementCall",
  "Identifier", null
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
     * Method to retrieve the beginning position of the last scanned token.
     * @return the position at which the last scanned token starts.
     */
    Position getStartPos();

    /**
     * Method to retrieve the ending position of the last scanned token.
     * @return the first position beyond the last scanned token.
     */
    Position getEndPos();

    /**
     * Method to retrieve the semantic value of the last scanned token.
     * @return the semantic value of the last scanned token.
     */
    ILexem getLVal();

    /**
     * Entry point for the scanner.  Returns the token identifier corresponding
     * to the next token and prepares to return the semantic value
     * and beginning/ending positions of the token.
     * @return the token identifier corresponding to the next token.
     */
    int yylex();

    /**
     * Emit an error referring to the given locationin a user-defined way.
     *
     * @param loc The location of the element to which the
     *                error message is related.
     * @param msg The string for the error message.
     */
     void yyerror(Location loc, String msg);


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
   * Use a <code>null</code> location.
   * @param msg The error message.
   */
  public final void yyerror(String msg) {
      yylexer.yyerror((Location)null, msg);
  }

  /**
   * Print an error message via the lexer.
   * @param loc The location associated with the message.
   * @param msg The error message.
   */
  public final void yyerror(Location loc, String msg) {
      yylexer.yyerror(loc, msg);
  }

  /**
   * Print an error message via the lexer.
   * @param pos The position associated with the message.
   * @param msg The error message.
   */
  public final void yyerror(Position pos, String msg) {
      yylexer.yyerror(new Location (pos), msg);
  }

  protected final void yycdebug (String s) {
    if (0 < yydebug)
      yyDebugStream.println (s);
  }

  private final class YYStack {
    private int[] stateStack = new int[16];
    private Location[] locStack = new Location[16];
    private ILexem[] valueStack = new ILexem[16];

    public int size = 16;
    public int height = -1;

    public final void push (int state, ILexem value, Location loc) {
      height++;
      if (size == height)
        {
          int[] newStateStack = new int[size * 2];
          System.arraycopy (stateStack, 0, newStateStack, 0, height);
          stateStack = newStateStack;
          Location[] newLocStack = new Location[size * 2];
          System.arraycopy (locStack, 0, newLocStack, 0, height);
          locStack = newLocStack;

          ILexem[] newValueStack = new ILexem[size * 2];
          System.arraycopy (valueStack, 0, newValueStack, 0, height);
          valueStack = newValueStack;

          size *= 2;
        }

      stateStack[height] = state;
      locStack[height] = loc;
      valueStack[height] = value;
    }

    public final void pop () {
      pop (1);
    }

    public final void pop (int num) {
      // Avoid memory leaks... garbage collection is a white lie!
      if (0 < num) {
        java.util.Arrays.fill (valueStack, height - num + 1, height + 1, null);
        java.util.Arrays.fill (locStack, height - num + 1, height + 1, null);
      }
      height -= num;
    }

    public final int stateAt (int i) {
      return stateStack[height - i];
    }


    public final Location locationAt (int i) {
      return locStack[height - i];
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
    Location yyloc = yylloc(yystack, yylen);

    yyReducePrint(yyn, yystack);

    switch (yyn)
      {
          case 2: /* Program: GlobalDeclarations  */
  if (yyn == 2)
    /* "smallgrammar.y":124  */
                         {ast = ((Declarations)(yystack.valueAt (0)));};
  break;


  case 3: /* GlobalDeclarations: %empty  */
  if (yyn == 3)
    /* "smallgrammar.y":128  */
                  { yyval = new Declarations();};
  break;


  case 4: /* GlobalDeclarations: GlobalDeclaration GlobalDeclarations  */
  if (yyn == 4)
    /* "smallgrammar.y":129  */
                                           {yyval = ((Declarations)(yystack.valueAt (0))); ((Declarations)(yystack.valueAt (0))).add(((IDeclaration)(yystack.valueAt (1))));};
  break;


  case 5: /* GlobalDeclaration: SimpleDeclaration  */
  if (yyn == 5)
    /* "smallgrammar.y":133  */
                        {yyval = ((IDeclaration)(yystack.valueAt (0)));};
  break;


  case 6: /* GlobalDeclaration: RoutineDeclaration  */
  if (yyn == 6)
    /* "smallgrammar.y":134  */
                         {yyval = ((RoutineDeclaration)(yystack.valueAt (0)));};
  break;


  case 7: /* SimpleDeclaration: VariableDeclaration  */
  if (yyn == 7)
    /* "smallgrammar.y":138  */
                          { yyval = ((VariableDeclaration)(yystack.valueAt (0)));};
  break;


  case 8: /* SimpleDeclaration: TypeDeclaration  */
  if (yyn == 8)
    /* "smallgrammar.y":139  */
                      { yyval = ((TypeDeclaration)(yystack.valueAt (0)));};
  break;


  case 9: /* VariableDeclaration: VAR Identifier COLON Type OptionalSemicolon  */
  if (yyn == 9)
    /* "smallgrammar.y":143  */
                                                  {yyval = new VariableDeclaration(((Identifier)(yystack.valueAt (3))), ((IType)(yystack.valueAt (1))), null);};
  break;


  case 10: /* VariableDeclaration: VAR Identifier COLON Type IS Expression OptionalSemicolon  */
  if (yyn == 10)
    /* "smallgrammar.y":144  */
                                                                { yyval = new VariableDeclaration(((Identifier)(yystack.valueAt (5))), ((IType)(yystack.valueAt (3))), ((IExpression)(yystack.valueAt (1)))); };
  break;


  case 11: /* VariableDeclaration: VAR Identifier IS Expression OptionalSemicolon  */
  if (yyn == 11)
    /* "smallgrammar.y":145  */
                                                     {yyval = new VariableDeclaration(((Identifier)(yystack.valueAt (3))), null, ((IExpression)(yystack.valueAt (1))));};
  break;


  case 12: /* TypeDeclaration: TYPE Identifier IS Type OptionalSemicolon  */
  if (yyn == 12)
    /* "smallgrammar.y":149  */
                                               {yyval = new TypeDeclaration(((Identifier)(yystack.valueAt (3))), ((IType)(yystack.valueAt (1))));};
  break;


  case 13: /* RoutineDeclaration: ROUTINE Identifier LPAREN Parameters RPAREN COLON Type IS Body END  */
  if (yyn == 13)
    /* "smallgrammar.y":153  */
                                                                          {yyval = new RoutineDeclaration(((Identifier)(yystack.valueAt (8))), ((Parameters)(yystack.valueAt (6))), ((IType)(yystack.valueAt (3))), ((Body)(yystack.valueAt (1))));};
  break;


  case 14: /* RoutineDeclaration: ROUTINE Identifier LPAREN Parameters RPAREN IS Body END  */
  if (yyn == 14)
    /* "smallgrammar.y":154  */
                                                               {yyval = new RoutineDeclaration(((Identifier)(yystack.valueAt (6))), ((Parameters)(yystack.valueAt (4))), null, ((Body)(yystack.valueAt (1))));};
  break;


  case 15: /* Parameters: ParameterDeclaration  */
  if (yyn == 15)
    /* "smallgrammar.y":158  */
                           { Parameters x = new Parameters(); x.add(((Parameter)(yystack.valueAt (0)))); yyval = x;};
  break;


  case 16: /* Parameters: ParameterDeclaration COMMA Parameters  */
  if (yyn == 16)
    /* "smallgrammar.y":159  */
                                            { yyval = ((Parameters)(yystack.valueAt (0))); ((Parameters)(yystack.valueAt (0))).add(((Parameter)(yystack.valueAt (2)))); };
  break;


  case 17: /* ParameterDeclaration: Identifier COLON Type  */
  if (yyn == 17)
    /* "smallgrammar.y":164  */
                            { yyval = new Parameter(((Identifier)(yystack.valueAt (2))), ((IType)(yystack.valueAt (0)))); };
  break;


  case 18: /* Body: %empty  */
  if (yyn == 18)
    /* "smallgrammar.y":168  */
                  { yyval = new Body();};
  break;


  case 19: /* Body: BodyDeclaration Body  */
  if (yyn == 19)
    /* "smallgrammar.y":169  */
                           {yyval = ((Body)(yystack.valueAt (0))); ((Body)(yystack.valueAt (0))).add(((IStatement)(yystack.valueAt (1))));};
  break;


  case 20: /* BodyDeclaration: VariableDeclaration  */
  if (yyn == 20)
    /* "smallgrammar.y":173  */
                          {yyval = new LocalVariableDeclaration(((VariableDeclaration)(yystack.valueAt (0))));};
  break;


  case 21: /* BodyDeclaration: Statement  */
  if (yyn == 21)
    /* "smallgrammar.y":174  */
                {yyval = ((IStatement)(yystack.valueAt (0)));};
  break;


  case 22: /* Statement: Assignment  */
  if (yyn == 22)
    /* "smallgrammar.y":178  */
                  {yyval = ((Assignment)(yystack.valueAt (0)));};
  break;


  case 23: /* Statement: RoutineCall  */
  if (yyn == 23)
    /* "smallgrammar.y":179  */
                   {yyval = ((RoutineCall)(yystack.valueAt (0))); };
  break;


  case 24: /* Statement: WhileStatement  */
  if (yyn == 24)
    /* "smallgrammar.y":180  */
                      {yyval = ((WhileStatement)(yystack.valueAt (0)));};
  break;


  case 25: /* Statement: ForLoop  */
  if (yyn == 25)
    /* "smallgrammar.y":181  */
               {yyval = ((ForLoop)(yystack.valueAt (0)));};
  break;


  case 26: /* Statement: IfStatement  */
  if (yyn == 26)
    /* "smallgrammar.y":182  */
                  {yyval = ((IfStatement)(yystack.valueAt (0)));};
  break;


  case 27: /* Statement: Print  */
  if (yyn == 27)
    /* "smallgrammar.y":183  */
             {yyval = ((Print)(yystack.valueAt (0)));};
  break;


  case 28: /* Statement: Return  */
  if (yyn == 28)
    /* "smallgrammar.y":184  */
              {yyval = ((Return)(yystack.valueAt (0)));};
  break;


  case 29: /* Assignment: ModifiablePrimary ASSIGN Expression  */
  if (yyn == 29)
    /* "smallgrammar.y":188  */
                                          {yyval = new Assignment(((ModifiablePrimary)(yystack.valueAt (2))), ((IExpression)(yystack.valueAt (0))));};
  break;


  case 30: /* RoutineCall: Identifier LPAREN Expressions RPAREN  */
  if (yyn == 30)
    /* "smallgrammar.y":193  */
                                           {yyval = new RoutineCall(((Identifier)(yystack.valueAt (3))), ((Expressions)(yystack.valueAt (1)))); };
  break;


  case 31: /* WhileStatement: WHILE Expression LOOP Body END  */
  if (yyn == 31)
    /* "smallgrammar.y":197  */
                                     {yyval = new WhileStatement(((IExpression)(yystack.valueAt (3))), ((Body)(yystack.valueAt (1)))); };
  break;


  case 32: /* ForLoop: FOR Identifier IN Expression RANGE Expression LOOP Body END  */
  if (yyn == 32)
    /* "smallgrammar.y":202  */
    {yyval = new ForLoop(((Identifier)(yystack.valueAt (7))), ((IExpression)(yystack.valueAt (5))), ((IExpression)(yystack.valueAt (3))), ((Body)(yystack.valueAt (1))), false);};
  break;


  case 33: /* ForLoop: FOR Identifier IN REVERSE Expression RANGE Expression LOOP Body END  */
  if (yyn == 33)
    /* "smallgrammar.y":204  */
    {yyval = new ForLoop(((Identifier)(yystack.valueAt (8))), ((IExpression)(yystack.valueAt (5))), ((IExpression)(yystack.valueAt (3))), ((Body)(yystack.valueAt (1))), true);};
  break;


  case 34: /* IfStatement: IF Expression THEN Body END  */
  if (yyn == 34)
    /* "smallgrammar.y":208  */
                                  {yyval = new IfStatement(((IExpression)(yystack.valueAt (3))), ((Body)(yystack.valueAt (1))), null);};
  break;


  case 35: /* IfStatement: IF Expression THEN Body ELSE Body END  */
  if (yyn == 35)
    /* "smallgrammar.y":209  */
                                            {yyval = new IfStatement(((IExpression)(yystack.valueAt (5))), ((Body)(yystack.valueAt (3))), ((Body)(yystack.valueAt (1))));};
  break;


  case 36: /* Return: RETURN Expression  */
  if (yyn == 36)
    /* "smallgrammar.y":213  */
                        {yyval = new Return(((IExpression)(yystack.valueAt (0))));};
  break;


  case 37: /* Print: PRINT LPAREN Expressions RPAREN  */
  if (yyn == 37)
    /* "smallgrammar.y":217  */
                                      {yyval = new Print(((Expressions)(yystack.valueAt (1))));};
  break;


  case 38: /* Expressions: Expression  */
  if (yyn == 38)
    /* "smallgrammar.y":221  */
                 { Expressions x = new Expressions(); x.add(((IExpression)(yystack.valueAt (0)))); yyval = x; };
  break;


  case 39: /* Expressions: Expression COMMA Expressions  */
  if (yyn == 39)
    /* "smallgrammar.y":222  */
                                   { yyval = ((Expressions)(yystack.valueAt (0))); ((Expressions)(yystack.valueAt (0))).add(((IExpression)(yystack.valueAt (2)))); };
  break;


  case 40: /* Expression: INTEGER_LITERAL  */
  if (yyn == 40)
    /* "smallgrammar.y":226  */
                      {yyval = ((IntegerLiteral)(yystack.valueAt (0)));};
  break;


  case 41: /* Expression: REAL_LITERAL  */
  if (yyn == 41)
    /* "smallgrammar.y":227  */
                   {yyval = ((RealLiteral)(yystack.valueAt (0)));};
  break;


  case 42: /* Expression: TRUE  */
  if (yyn == 42)
    /* "smallgrammar.y":228  */
           {yyval = ((BooleanLiteral)(yystack.valueAt (0)));};
  break;


  case 43: /* Expression: FALSE  */
  if (yyn == 43)
    /* "smallgrammar.y":229  */
            {yyval = ((BooleanLiteral)(yystack.valueAt (0)));};
  break;


  case 44: /* Expression: RoutineCall  */
  if (yyn == 44)
    /* "smallgrammar.y":230  */
                  {yyval = ((RoutineCall)(yystack.valueAt (0)));};
  break;


  case 45: /* Expression: ModifiablePrimary  */
  if (yyn == 45)
    /* "smallgrammar.y":231  */
                        {yyval = ((ModifiablePrimary)(yystack.valueAt (0)));};
  break;


  case 46: /* Expression: LPAREN Expression RPAREN  */
  if (yyn == 46)
    /* "smallgrammar.y":232  */
                                { yyval = ((IExpression)(yystack.valueAt (1))); };
  break;


  case 47: /* Expression: Expression PLUS Expression  */
  if (yyn == 47)
    /* "smallgrammar.y":233  */
                                 {yyval = new BinaryExpression("PLUS", ((IExpression)(yystack.valueAt (2))), ((IExpression)(yystack.valueAt (0))));};
  break;


  case 48: /* Expression: Expression MINUS Expression  */
  if (yyn == 48)
    /* "smallgrammar.y":234  */
                                  {yyval = new BinaryExpression("MINUS", ((IExpression)(yystack.valueAt (2))), ((IExpression)(yystack.valueAt (0))));};
  break;


  case 49: /* Expression: Expression MULTIPLY Expression  */
  if (yyn == 49)
    /* "smallgrammar.y":235  */
                                     {yyval = new BinaryExpression("MULTIPLY", ((IExpression)(yystack.valueAt (2))), ((IExpression)(yystack.valueAt (0))));};
  break;


  case 50: /* Expression: Expression DIVIDE Expression  */
  if (yyn == 50)
    /* "smallgrammar.y":236  */
                                   {yyval = new BinaryExpression("DIVIDE", ((IExpression)(yystack.valueAt (2))), ((IExpression)(yystack.valueAt (0))));};
  break;


  case 51: /* Expression: Expression REMAINDER Expression  */
  if (yyn == 51)
    /* "smallgrammar.y":237  */
                                      {yyval = new BinaryExpression("REMAINDER", ((IExpression)(yystack.valueAt (2))), ((IExpression)(yystack.valueAt (0))));};
  break;


  case 52: /* Expression: Expression AND Expression  */
  if (yyn == 52)
    /* "smallgrammar.y":238  */
                                {yyval = new BinaryExpression("AND", ((IExpression)(yystack.valueAt (2))), ((IExpression)(yystack.valueAt (0))));};
  break;


  case 53: /* Expression: Expression OR Expression  */
  if (yyn == 53)
    /* "smallgrammar.y":239  */
                               {yyval = new BinaryExpression("OR", ((IExpression)(yystack.valueAt (2))), ((IExpression)(yystack.valueAt (0))));};
  break;


  case 54: /* Expression: Expression XOR Expression  */
  if (yyn == 54)
    /* "smallgrammar.y":240  */
                                 {yyval = new BinaryExpression("XOR", ((IExpression)(yystack.valueAt (2))), ((IExpression)(yystack.valueAt (0))));};
  break;


  case 55: /* Expression: Expression LESS Expression  */
  if (yyn == 55)
    /* "smallgrammar.y":241  */
                                 {yyval = new BinaryExpression("LESS", ((IExpression)(yystack.valueAt (2))), ((IExpression)(yystack.valueAt (0))));};
  break;


  case 56: /* Expression: Expression GREATER Expression  */
  if (yyn == 56)
    /* "smallgrammar.y":242  */
                                    {yyval = new BinaryExpression("GREATER", ((IExpression)(yystack.valueAt (2))), ((IExpression)(yystack.valueAt (0))));};
  break;


  case 57: /* Expression: Expression LEQUALS Expression  */
  if (yyn == 57)
    /* "smallgrammar.y":243  */
                                    {yyval = new BinaryExpression("LEQUALS", ((IExpression)(yystack.valueAt (2))), ((IExpression)(yystack.valueAt (0))));};
  break;


  case 58: /* Expression: Expression GEQUALS Expression  */
  if (yyn == 58)
    /* "smallgrammar.y":244  */
                                    {yyval = new BinaryExpression("GEQUALS", ((IExpression)(yystack.valueAt (2))), ((IExpression)(yystack.valueAt (0))));};
  break;


  case 59: /* Expression: Expression EQUALS Expression  */
  if (yyn == 59)
    /* "smallgrammar.y":245  */
                                   {yyval = new BinaryExpression("EQUALS", ((IExpression)(yystack.valueAt (2))), ((IExpression)(yystack.valueAt (0))));};
  break;


  case 60: /* Expression: Expression NEQUALS Expression  */
  if (yyn == 60)
    /* "smallgrammar.y":246  */
                                    {yyval = new BinaryExpression("NEQUALS", ((IExpression)(yystack.valueAt (2))), ((IExpression)(yystack.valueAt (0))));};
  break;


  case 63: /* Type: PrimitiveType  */
  if (yyn == 63)
    /* "smallgrammar.y":258  */
                    { yyval = ((PrimitiveType)(yystack.valueAt (0))); };
  break;


  case 64: /* Type: ArrayType  */
  if (yyn == 64)
    /* "smallgrammar.y":259  */
                { yyval = ((ArrayType)(yystack.valueAt (0))); };
  break;


  case 65: /* Type: RecordType  */
  if (yyn == 65)
    /* "smallgrammar.y":260  */
                 { yyval = ((RecordType)(yystack.valueAt (0))); };
  break;


  case 66: /* Type: Identifier  */
  if (yyn == 66)
    /* "smallgrammar.y":261  */
                 { yyval = ((Identifier)(yystack.valueAt (0))); };
  break;


  case 67: /* PrimitiveType: INTEGER  */
  if (yyn == 67)
    /* "smallgrammar.y":265  */
              { yyval = new PrimitiveType("integer");};
  break;


  case 68: /* PrimitiveType: REAL  */
  if (yyn == 68)
    /* "smallgrammar.y":266  */
           { yyval = new PrimitiveType("real"); };
  break;


  case 69: /* PrimitiveType: BOOLEAN  */
  if (yyn == 69)
    /* "smallgrammar.y":267  */
              { yyval = new PrimitiveType("boolean"); };
  break;


  case 70: /* RecordType: RECORD RecordDeclarations END  */
  if (yyn == 70)
    /* "smallgrammar.y":271  */
                                    {yyval = ((RecordType)(yystack.valueAt (1)));};
  break;


  case 71: /* RecordDeclarations: %empty  */
  if (yyn == 71)
    /* "smallgrammar.y":275  */
                  { yyval = new RecordType(); };
  break;


  case 72: /* RecordDeclarations: VariableDeclaration RecordDeclarations  */
  if (yyn == 72)
    /* "smallgrammar.y":276  */
                                             { yyval = ((RecordType)(yystack.valueAt (0))); ((RecordType)(yystack.valueAt (0))).add(((VariableDeclaration)(yystack.valueAt (1))));};
  break;


  case 73: /* ArrayType: ARRAY LBRACKET Expression RBRACKET Type  */
  if (yyn == 73)
    /* "smallgrammar.y":280  */
                                              { yyval = new ArrayType(((IExpression)(yystack.valueAt (2))), ((IType)(yystack.valueAt (0)))); };
  break;


  case 74: /* ModifiablePrimary: Identifier ElementCall  */
  if (yyn == 74)
    /* "smallgrammar.y":285  */
                             { yyval = new ModifiablePrimary(((Identifier)(yystack.valueAt (1))), ((ElementCall)(yystack.valueAt (0)))); };
  break;


  case 75: /* ElementCall: %empty  */
  if (yyn == 75)
    /* "smallgrammar.y":289  */
                  { yyval = new ElementCall(); };
  break;


  case 76: /* ElementCall: DOT Identifier ElementCall  */
  if (yyn == 76)
    /* "smallgrammar.y":290  */
                                 { yyval = ((ElementCall)(yystack.valueAt (0))); ((ElementCall)(yystack.valueAt (0))).add(new FieldAccess(((Identifier)(yystack.valueAt (1))))); };
  break;


  case 77: /* ElementCall: LBRACKET Expression RBRACKET ElementCall  */
  if (yyn == 77)
    /* "smallgrammar.y":291  */
                                               { yyval = ((ElementCall)(yystack.valueAt (0))); ((ElementCall)(yystack.valueAt (0))).add(new IndexAccess(((IExpression)(yystack.valueAt (2)))));};
  break;


  case 78: /* Identifier: IDENTIFIER  */
  if (yyn == 78)
    /* "smallgrammar.y":297  */
                   {yyval = ((Identifier)(yystack.valueAt (0)));};
  break;



/* "YYParser.java":1275  */

        default: break;
      }

    yySymbolPrint("-> $$ =", SymbolKind.get(yyr1_[yyn]), yyval, yyloc);

    yystack.pop(yylen);
    yylen = 0;
    /* Shift the result of the reduction.  */
    int yystate = yyLRGotoState(yystack.stateAt(0), yyr1_[yyn]);
    yystack.push(yystate, yyval, yyloc);
    return YYNEWSTATE;
  }


  /*--------------------------------.
  | Print this symbol on YYOUTPUT.  |
  `--------------------------------*/

  private void yySymbolPrint(String s, SymbolKind yykind,
                             ILexem yyvalue, Location yylocation) {
      if (0 < yydebug) {
          yycdebug(s
                   + (yykind.getCode() < YYNTOKENS_ ? " token " : " nterm ")
                   + yykind.getName() + " ("
                   + yylocation + ": "
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
    /* @$.  */
    Location yyloc;


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


    /* The location where the error started.  */
    Location yyerrloc = null;

    /* Location. */
    Location yylloc = new Location (null, null);

    /* Semantic value of the lookahead.  */
    ILexem yylval = null;

    yycdebug ("Starting parse");
    yyerrstatus_ = 0;
    yynerrs = 0;

    /* Initialize the stack.  */
    yystack.push (yystate, yylval, yylloc);



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
            yylloc = new Location(yylexer.getStartPos(),
                                          yylexer.getEndPos());

          }

        /* Convert token to internal form.  */
        yytoken = yytranslate_ (yychar);
        yySymbolPrint("Next token is", yytoken,
                      yylval, yylloc);

        if (yytoken == SymbolKind.S_YYerror)
          {
            // The scanner already issued an error message, process directly
            // to error recovery.  But do not keep the error token as
            // lookahead, it is too special and may lead us to an endless
            // loop in error recovery. */
            yychar = Lexer.YYUNDEF;
            yytoken = SymbolKind.S_YYUNDEF;
            yyerrloc = yylloc;
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
                              yylval, yylloc);

                /* Discard the token being shifted.  */
                yychar = YYEMPTY_;

                /* Count tokens shifted since error; after three, turn off error
                   status.  */
                if (yyerrstatus_ > 0)
                  --yyerrstatus_;

                yystate = yyn;
                yystack.push (yystate, yylval, yylloc);
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
            yyreportSyntaxError (new Context (yystack, yytoken, yylloc));
          }

        yyerrloc = yylloc;
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
        yyerrloc = yystack.locationAt (yylen - 1);
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


            yyerrloc = yystack.locationAt (0);
            yystack.pop ();
            yystate = yystack.stateAt (0);
            if (0 < yydebug)
              yystack.print (yyDebugStream);
          }

        if (label == YYABORT)
          /* Leave the switch.  */
          break;


        /* Muck with the stack to setup for yylloc.  */
        yystack.push (0, null, yylloc);
        yystack.push (0, null, yyerrloc);
        yyloc = yylloc (yystack, 2);
        yystack.pop (2);

        /* Shift the error token.  */
        yySymbolPrint("Shifting", SymbolKind.get(yystos_[yyn]),
                      yylval, yyloc);

        yystate = yyn;
        yystack.push (yyn, yylval, yyloc);
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
    Context (YYStack stack, SymbolKind token, Location loc)
    {
      yystack = stack;
      yytoken = token;
      yylocation = loc;
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

    /**
     * The location of the lookahead.
     */
    public final Location getLocation ()
    {
      return yylocation;
    }

    private Location yylocation;
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

  private static final short yypact_ninf_ = -88;
  private static final short yytable_ninf_ = -1;

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short[] yypact_ = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
      98,    18,    18,    18,    48,   -88,    98,   -88,   -88,   -88,
     -88,   -88,     7,    47,    24,   -88,   -88,   108,   168,   168,
      18,   -88,   -88,   -88,   -88,   108,   -88,   210,   -88,   -27,
      52,   -88,   -88,   -88,    25,    -6,   -88,   -88,   -88,   -88,
      29,    36,    26,    39,   238,   108,   108,   108,   -88,   108,
     108,   108,   108,   108,   108,   108,   108,   108,   108,   108,
     -88,   108,   108,    18,   -88,    52,    65,   108,   108,   -88,
     -88,    15,    18,   168,   -88,   174,   174,   174,   -88,   -88,
     -88,   -88,   -88,   -88,   224,   224,   252,   252,   252,    45,
     266,   294,    13,   -88,   -88,   322,   210,    66,   168,   -88,
     -88,   -88,   108,    13,   -88,   168,   -88,   108,    18,   108,
      59,   108,   -88,    75,    66,   -88,   -88,   -88,   -88,   -88,
     -88,   -88,   -88,    56,    85,   -88,   -88,   -88,   102,    82,
     182,   108,   392,   -88,   -88,   108,    66,    66,   183,    66,
      69,   392,    99,   105,   108,   336,    -1,   -88,   -88,   -88,
     364,   108,   -88,    66,   108,   114,   115,   150,    66,   -88,
      66,   119,   122,   -88,   -88
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
       3,     0,     0,     0,     0,     2,     3,     5,     7,     8,
       6,    78,     0,     0,     0,     1,     4,     0,     0,     0,
       0,    40,    41,    42,    43,     0,    44,    61,    45,    75,
      71,    67,    68,    69,     0,    61,    63,    65,    64,    66,
      61,     0,    15,     0,     0,     0,     0,     0,    62,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
      11,     0,     0,     0,    74,    71,     0,     0,     0,     9,
      12,     0,     0,     0,    46,    52,    53,    54,    59,    60,
      56,    55,    57,    58,    47,    48,    49,    50,    51,     0,
      38,     0,    75,    72,    70,     0,    61,    18,     0,    16,
      17,    30,     0,    75,    76,     0,    10,     0,     0,     0,
       0,     0,    20,     0,    18,    21,    22,    23,    24,    25,
      26,    28,    27,     0,     0,    39,    77,    73,     0,     0,
       0,     0,    36,    14,    19,     0,    18,    18,     0,    18,
       0,    29,     0,     0,     0,     0,     0,    37,    13,    31,
       0,     0,    34,    18,     0,     0,     0,     0,    18,    35,
      18,     0,     0,    32,    33
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final byte[] yypgoto_ = yypgoto_init();
  private static final byte[] yypgoto_init()
  {
    return new byte[]
    {
     -88,   -88,   103,   -88,   -88,    16,   -88,   -88,    53,   -88,
     -36,   -88,   -88,   -88,   -83,   -88,   -88,   -88,   -88,   -88,
     -87,   -17,   -29,   -14,   -88,   -88,    54,   -88,   -71,   -79,
       0
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final byte[] yydefgoto_ = yydefgoto_init();
  private static final byte[] yydefgoto_init()
  {
    return new byte[]
    {
      -1,     4,     5,     6,     7,   112,     9,    10,    41,    42,
     113,   114,   115,   116,    26,   118,   119,   120,   121,   122,
      89,    90,    60,    35,    36,    37,    66,    38,    28,    64,
      29
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
      27,    12,    13,    14,    68,    40,    69,    61,    44,    62,
     152,    70,    63,   104,   117,   125,     8,    17,    39,    39,
      43,    11,     8,   153,   126,    97,   123,    48,    75,    76,
      77,   117,    78,    79,    80,    81,    82,    83,    84,    85,
      86,    87,    88,   123,   140,    91,    65,    18,    15,    62,
      95,    96,    63,   117,   117,    98,   117,    19,    20,   100,
       1,    67,    48,    92,    72,   123,   123,   106,   123,    11,
     117,    71,    43,    39,     1,   117,    94,   117,   134,    73,
     101,    65,   123,   107,   124,   108,   133,   123,   109,   123,
     128,   127,   130,   131,   132,   136,   110,   111,    39,   135,
     142,   143,   138,   146,   147,    39,     1,     2,   129,    16,
     148,    11,    21,    22,    23,    24,   149,   156,   141,    93,
     137,   145,   161,     3,   162,    99,   159,   150,    45,    46,
     163,    47,   158,   164,   155,     0,     0,   157,     0,     0,
      45,    46,    25,    47,    49,     0,    50,    51,    52,    53,
      54,    55,    56,    57,    58,    59,    49,     0,    50,    51,
      52,    53,    54,    55,    56,    57,    58,    59,   160,     0,
       0,    11,     0,     0,     0,     0,    45,    46,     0,    47,
      30,    31,    32,    33,    34,     0,    11,    21,    22,    23,
      24,     0,    49,     0,    50,    51,    52,    53,    54,    55,
      56,    57,    58,    59,   144,   139,     0,     0,    45,    46,
       0,    47,     0,     0,     0,     0,    49,    25,    50,    51,
      52,    53,    54,     0,    49,     0,    50,    51,    52,    53,
      54,    55,    56,    57,    58,    59,    45,    46,     0,    47,
       0,     0,     0,    48,     0,     0,     0,     0,     0,     0,
      45,    46,    49,    47,    50,    51,    52,    53,    54,    55,
      56,    57,    58,    59,    45,    46,    49,    47,    50,    51,
      52,    53,    54,    74,     0,    57,    58,    59,    45,    46,
      49,    47,    50,    51,    52,    53,    54,    55,    56,    57,
      58,    59,    45,    46,    49,    47,    50,    51,    52,    53,
      54,     0,     0,     0,   102,     0,     0,     0,    49,     0,
      50,    51,    52,    53,    54,    55,    56,    57,    58,    59,
      45,    46,     0,    47,     0,     0,     0,     0,     0,     0,
       0,   103,     0,     0,     0,     0,    49,     0,    50,    51,
      52,    53,    54,    55,    56,    57,    58,    59,    45,    46,
       0,    47,     0,     0,     0,     0,     0,     0,     0,   105,
       0,     0,    45,    46,    49,    47,    50,    51,    52,    53,
      54,    55,    56,    57,    58,    59,     0,   151,    49,     0,
      50,    51,    52,    53,    54,    55,    56,    57,    58,    59,
      45,    46,     0,    47,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,   154,    49,     0,    50,    51,
      52,    53,    54,    55,    56,    57,    58,    59,    45,    46,
       0,    47,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,    49,     0,    50,    51,    52,    53,
      54,    55,    56,    57,    58,    59
    };
  }

private static final short[] yycheck_ = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
      17,     1,     2,     3,    10,    19,    35,    34,    25,    36,
      11,    40,    39,    92,    97,   102,     0,    10,    18,    19,
      20,     3,     6,    24,   103,    10,    97,    33,    45,    46,
      47,   114,    49,    50,    51,    52,    53,    54,    55,    56,
      57,    58,    59,   114,   131,    62,    30,    40,     0,    36,
      67,    68,    39,   136,   137,    40,   139,    10,    34,    73,
       8,    36,    33,    63,    38,   136,   137,    96,   139,     3,
     153,    35,    72,    73,     8,   158,    11,   160,   114,    40,
      35,    65,   153,    17,    98,    19,    11,   158,    22,   160,
     107,   105,   109,    34,   111,    10,    30,    31,    98,    43,
     136,   137,    20,   139,    35,   105,     8,     9,   108,     6,
      11,     3,     4,     5,     6,     7,    11,   153,   135,    65,
      18,   138,   158,    25,   160,    72,    11,   144,    26,    27,
      11,    29,    18,    11,   151,    -1,    -1,   154,    -1,    -1,
      26,    27,    34,    29,    42,    -1,    44,    45,    46,    47,
      48,    49,    50,    51,    52,    53,    42,    -1,    44,    45,
      46,    47,    48,    49,    50,    51,    52,    53,    18,    -1,
      -1,     3,    -1,    -1,    -1,    -1,    26,    27,    -1,    29,
      12,    13,    14,    15,    16,    -1,     3,     4,     5,     6,
       7,    -1,    42,    -1,    44,    45,    46,    47,    48,    49,
      50,    51,    52,    53,    21,    23,    -1,    -1,    26,    27,
      -1,    29,    -1,    -1,    -1,    -1,    42,    34,    44,    45,
      46,    47,    48,    -1,    42,    -1,    44,    45,    46,    47,
      48,    49,    50,    51,    52,    53,    26,    27,    -1,    29,
      -1,    -1,    -1,    33,    -1,    -1,    -1,    -1,    -1,    -1,
      26,    27,    42,    29,    44,    45,    46,    47,    48,    49,
      50,    51,    52,    53,    26,    27,    42,    29,    44,    45,
      46,    47,    48,    35,    -1,    51,    52,    53,    26,    27,
      42,    29,    44,    45,    46,    47,    48,    49,    50,    51,
      52,    53,    26,    27,    42,    29,    44,    45,    46,    47,
      48,    -1,    -1,    -1,    38,    -1,    -1,    -1,    42,    -1,
      44,    45,    46,    47,    48,    49,    50,    51,    52,    53,
      26,    27,    -1,    29,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    37,    -1,    -1,    -1,    -1,    42,    -1,    44,    45,
      46,    47,    48,    49,    50,    51,    52,    53,    26,    27,
      -1,    29,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    37,
      -1,    -1,    26,    27,    42,    29,    44,    45,    46,    47,
      48,    49,    50,    51,    52,    53,    -1,    41,    42,    -1,
      44,    45,    46,    47,    48,    49,    50,    51,    52,    53,
      26,    27,    -1,    29,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    41,    42,    -1,    44,    45,
      46,    47,    48,    49,    50,    51,    52,    53,    26,    27,
      -1,    29,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    42,    -1,    44,    45,    46,    47,
      48,    49,    50,    51,    52,    53
    };
  }

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
  private static final byte[] yystos_ = yystos_init();
  private static final byte[] yystos_init()
  {
    return new byte[]
    {
       0,     8,     9,    25,    55,    56,    57,    58,    59,    60,
      61,     3,    84,    84,    84,     0,    56,    10,    40,    10,
      34,     4,     5,     6,     7,    34,    68,    75,    82,    84,
      12,    13,    14,    15,    16,    77,    78,    79,    81,    84,
      77,    62,    63,    84,    75,    26,    27,    29,    33,    42,
      44,    45,    46,    47,    48,    49,    50,    51,    52,    53,
      76,    34,    36,    39,    83,    59,    80,    36,    10,    76,
      76,    35,    38,    40,    35,    75,    75,    75,    75,    75,
      75,    75,    75,    75,    75,    75,    75,    75,    75,    74,
      75,    75,    84,    80,    11,    75,    75,    10,    40,    62,
      77,    35,    38,    37,    83,    37,    76,    17,    19,    22,
      30,    31,    59,    64,    65,    66,    67,    68,    69,    70,
      71,    72,    73,    82,    77,    74,    83,    77,    75,    84,
      75,    34,    75,    11,    64,    43,    10,    18,    20,    23,
      74,    75,    64,    64,    21,    75,    64,    35,    11,    11,
      75,    41,    11,    24,    41,    75,    64,    75,    18,    11,
      18,    64,    64,    11,    11
    };
  }

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
  private static final byte[] yyr1_ = yyr1_init();
  private static final byte[] yyr1_init()
  {
    return new byte[]
    {
       0,    54,    55,    56,    56,    57,    57,    58,    58,    59,
      59,    59,    60,    61,    61,    62,    62,    63,    64,    64,
      65,    65,    66,    66,    66,    66,    66,    66,    66,    67,
      68,    69,    70,    70,    71,    71,    72,    73,    74,    74,
      75,    75,    75,    75,    75,    75,    75,    75,    75,    75,
      75,    75,    75,    75,    75,    75,    75,    75,    75,    75,
      75,    76,    76,    77,    77,    77,    77,    78,    78,    78,
      79,    80,    80,    81,    82,    83,    83,    83,    84
    };
  }

/* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
  private static final byte[] yyr2_ = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     1,     0,     2,     1,     1,     1,     1,     5,
       7,     5,     5,    10,     8,     1,     3,     3,     0,     2,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     3,
       4,     5,     9,    10,     5,     7,     2,     4,     1,     3,
       1,     1,     1,     1,     1,     1,     3,     3,     3,     3,
       3,     3,     3,     3,     3,     3,     3,     3,     3,     3,
       3,     0,     1,     1,     1,     1,     1,     1,     1,     1,
       3,     0,     2,     5,     2,     0,     3,     4,     1
    };
  }



  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
  private static final short[] yyrline_ = yyrline_init();
  private static final short[] yyrline_init()
  {
    return new short[]
    {
       0,   124,   124,   128,   129,   133,   134,   138,   139,   143,
     144,   145,   149,   153,   154,   158,   159,   164,   168,   169,
     173,   174,   178,   179,   180,   181,   182,   183,   184,   188,
     193,   197,   201,   203,   208,   209,   213,   217,   221,   222,
     226,   227,   228,   229,   230,   231,   232,   233,   234,   235,
     236,   237,   238,   239,   240,   241,   242,   243,   244,   245,
     246,   252,   253,   258,   259,   260,   261,   265,   266,   267,
     271,   275,   276,   280,   285,   289,   290,   291,   297
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
                    yystack.valueAt ((yynrhs) - (yyi + 1)),
                    yystack.locationAt ((yynrhs) - (yyi + 1)));
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


  private static final int YYLAST_ = 445;
  private static final int YYEMPTY_ = -2;
  private static final int YYFINAL_ = 15;
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

/* "YYParser.java":2057  */

}
/* "smallgrammar.y":300  */

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
