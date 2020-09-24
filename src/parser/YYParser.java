///* A Bison parser, made by GNU Bison 3.7.1.  */
//
///* Skeleton implementation for Bison LALR(1) parsers in Java
//
//   Copyright (C) 2007-2015, 2018-2020 Free Software Foundation, Inc.
//
//   This program is free software: you can redistribute it and/or modify
//   it under the terms of the GNU General Public License as published by
//   the Free Software Foundation, either version 3 of the License, or
//   (at your option) any later version.
//
//   This program is distributed in the hope that it will be useful,
//   but WITHOUT ANY WARRANTY; without even the implied warranty of
//   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//   GNU General Public License for more details.
//
//   You should have received a copy of the GNU General Public License
//   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */
//
///* As a special exception, you may create a larger work that contains
//   part or all of the Bison parser skeleton and distribute that work
//   under terms of your choice, so long as that work isn't itself a
//   parser generator using the skeleton or a modified version thereof
//   as a parser skeleton.  Alternatively, if you modify or redistribute
//   the parser skeleton itself, you may (at your option) remove this
//   special exception, which will cause the skeleton and the resulting
//   Bison output files to be licensed under the GNU General Public
//   License without this special exception.
//
//   This special exception was added by the Free Software Foundation in
//   version 2.2 of Bison.  */
//
///* DO NOT RELY ON FEATURES THAT ARE NOT DOCUMENTED in the manual,
//   especially those whose name start with YY_ or yy_.  They are
//   private implementation details that can be changed or removed.  */
//
package parser;



import java.text.MessageFormat;
/* "%code imports" blocks.  */
/* "parser.y":10  */

import lexems.*;
import lexer.*;
import reader.Reader;

/* "YYParser.java":50  */
//
///**
// * A Bison parser, automatically generated from <tt>parser.y</tt>.
// *
// * @author LALR (1) parser skeleton written by Paolo Bonzini.
// */
public class YYParser
{
//  /** Version number for the Bison executable that generated this parser.  */
//  public static final String bisonVersion = "3.7.1";
//
//  /** Name of the skeleton that generated this parser.  */
//  public static final String bisonSkeleton = "lalr1.java";
//
//
//
//
//
//  /**
//   * A class defining a pair of positions.  Positions, defined by the
//   * <code>Position</code> class, denote a point in the input.
//   * Locations represent a part of the input through the beginning
//   * and ending positions.
//   */
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

//
//  public enum SymbolKind
//  {
//    S_YYEOF(0),                    /* "end of file"  */
//    S_YYerror(1),                  /* error  */
//    S_YYUNDEF(2),                  /* "invalid token"  */
//    S_IDENTIFIER(3),               /* IDENTIFIER  */
//    S_INTEGER_LITERAL(4),          /* INTEGER_LITERAL  */
//    S_REAL_LITERAL(5),             /* REAL_LITERAL  */
//    S_TRUE(6),                     /* TRUE  */
//    S_FALSE(7),                    /* FALSE  */
//    S_VAR(8),                      /* VAR  */
//    S_TYPE(9),                     /* TYPE  */
//    S_IS(10),                      /* IS  */
//    S_END(11),                     /* END  */
//    S_RECORD(12),                  /* RECORD  */
//    S_INTEGER(13),                 /* INTEGER  */
//    S_REAL(14),                    /* REAL  */
//    S_BOOLEAN(15),                 /* BOOLEAN  */
//    S_ARRAY(16),                   /* ARRAY  */
//    S_WHILE(17),                   /* WHILE  */
//    S_LOOP(18),                    /* LOOP  */
//    S_FOR(19),                     /* FOR  */
//    S_IN(20),                      /* IN  */
//    S_REVERSE(21),                 /* REVERSE  */
//    S_IF(22),                      /* IF  */
//    S_THEN(23),                    /* THEN  */
//    S_ELSE(24),                    /* ELSE  */
//    S_ROUTINE(25),                 /* ROUTINE  */
//    S_AND(26),                     /* AND  */
//    S_OR(27),                      /* OR  */
//    S_NOT(28),                     /* NOT  */
//    S_XOR(29),                     /* XOR  */
//    S_PRINT(30),                   /* PRINT  */
//    S_RETURN(31),                  /* RETURN  */
//    S_LPAREN(32),                  /* LPAREN  */
//    S_RPAREN(33),                  /* RPAREN  */
//    S_LBRACKET(34),                /* LBRACKET  */
//    S_RBRACKET(35),                /* RBRACKET  */
//    S_COMMA(36),                   /* COMMA  */
//    S_DOT(37),                     /* DOT  */
//    S_COLON(38),                   /* COLON  */
//    S_RANGE(39),                   /* RANGE  */
//    S_EQUALS(40),                  /* EQUALS  */
//    S_ASSIGN(41),                  /* ASSIGN  */
//    S_NEQUALS(42),                 /* NEQUALS  */
//    S_GREATER(43),                 /* GREATER  */
//    S_LESS(44),                    /* LESS  */
//    S_LEQUALS(45),                 /* LEQUALS  */
//    S_GEQUALS(46),                 /* GEQUALS  */
//    S_PLUS(47),                    /* PLUS  */
//    S_MINUS(48),                   /* MINUS  */
//    S_MULTIPLY(49),                /* MULTIPLY  */
//    S_DIVIDE(50),                  /* DIVIDE  */
//    S_REMAINDER(51),               /* REMAINDER  */
//    S_YYACCEPT(52),                /* $accept  */
//    S_Program(53),                 /* Program  */
//    S_GlobalDeclarations(54),      /* GlobalDeclarations  */
//    S_GlobalDeclaration(55),       /* GlobalDeclaration  */
//    S_SimpleDeclaration(56),       /* SimpleDeclaration  */
//    S_VariableDeclaration(57),     /* VariableDeclaration  */
//    S_TypeDeclaration(58),         /* TypeDeclaration  */
//    S_RoutineDeclaration(59),      /* RoutineDeclaration  */
//    S_Parameters(60),              /* Parameters  */
//    S_ParameterDeclaration(61),    /* ParameterDeclaration  */
//    S_Type(62),                    /* Type  */
//    S_PrimitiveType(63),           /* PrimitiveType  */
//    S_RecordType(64),              /* RecordType  */
//    S_VariableDeclarations(65),    /* VariableDeclarations  */
//    S_ArrayType(66),               /* ArrayType  */
//    S_Body(67),                    /* Body  */
//    S_BodyDeclaration(68),         /* BodyDeclaration  */
//    S_Statement(69),               /* Statement  */
//    S_Return(70),                  /* Return  */
//    S_Assignment(71),              /* Assignment  */
//    S_RoutineCall(72),             /* RoutineCall  */
//    S_Expressions(73),             /* Expressions  */
//    S_WhileLoop(74),               /* WhileLoop  */
//    S_ForLoop(75),                 /* ForLoop  */
//    S_IfStatement(76),             /* IfStatement  */
//    S_Expression(77),              /* Expression  */
//    S_Relations(78),               /* Relations  */
//    S_LogicWord(79),               /* LogicWord  */
//    S_Relation(80),                /* Relation  */
//    S_SimpleTail(81),              /* SimpleTail  */
//    S_RelationSign(82),            /* RelationSign  */
//    S_Simple(83),                  /* Simple  */
//    S_FactorTail(84),              /* FactorTail  */
//    S_FactorSign(85),              /* FactorSign  */
//    S_Factor(86),                  /* Factor  */
//    S_SummandTail(87),             /* SummandTail  */
//    S_SummandSign(88),             /* SummandSign  */
//    S_Summand(89),                 /* Summand  */
//    S_Primary(90),                 /* Primary  */
//    S_ModifiablePrimary(91),       /* ModifiablePrimary  */
//    S_ElementCall(92),             /* ElementCall  */
//    S_Print(93),                   /* Print  */
//    S_Identifier(94);              /* Identifier  */
//
//
//    private final int yycode_;
//
//    SymbolKind (int n) {
//      this.yycode_ = n;
//    }
//
//    private static final SymbolKind[] values_ = {
//      SymbolKind.S_YYEOF,
//      SymbolKind.S_YYerror,
//      SymbolKind.S_YYUNDEF,
//      SymbolKind.S_IDENTIFIER,
//      SymbolKind.S_INTEGER_LITERAL,
//      SymbolKind.S_REAL_LITERAL,
//      SymbolKind.S_TRUE,
//      SymbolKind.S_FALSE,
//      SymbolKind.S_VAR,
//      SymbolKind.S_TYPE,
//      SymbolKind.S_IS,
//      SymbolKind.S_END,
//      SymbolKind.S_RECORD,
//      SymbolKind.S_INTEGER,
//      SymbolKind.S_REAL,
//      SymbolKind.S_BOOLEAN,
//      SymbolKind.S_ARRAY,
//      SymbolKind.S_WHILE,
//      SymbolKind.S_LOOP,
//      SymbolKind.S_FOR,
//      SymbolKind.S_IN,
//      SymbolKind.S_REVERSE,
//      SymbolKind.S_IF,
//      SymbolKind.S_THEN,
//      SymbolKind.S_ELSE,
//      SymbolKind.S_ROUTINE,
//      SymbolKind.S_AND,
//      SymbolKind.S_OR,
//      SymbolKind.S_NOT,
//      SymbolKind.S_XOR,
//      SymbolKind.S_PRINT,
//      SymbolKind.S_RETURN,
//      SymbolKind.S_LPAREN,
//      SymbolKind.S_RPAREN,
//      SymbolKind.S_LBRACKET,
//      SymbolKind.S_RBRACKET,
//      SymbolKind.S_COMMA,
//      SymbolKind.S_DOT,
//      SymbolKind.S_COLON,
//      SymbolKind.S_RANGE,
//      SymbolKind.S_EQUALS,
//      SymbolKind.S_ASSIGN,
//      SymbolKind.S_NEQUALS,
//      SymbolKind.S_GREATER,
//      SymbolKind.S_LESS,
//      SymbolKind.S_LEQUALS,
//      SymbolKind.S_GEQUALS,
//      SymbolKind.S_PLUS,
//      SymbolKind.S_MINUS,
//      SymbolKind.S_MULTIPLY,
//      SymbolKind.S_DIVIDE,
//      SymbolKind.S_REMAINDER,
//      SymbolKind.S_YYACCEPT,
//      SymbolKind.S_Program,
//      SymbolKind.S_GlobalDeclarations,
//      SymbolKind.S_GlobalDeclaration,
//      SymbolKind.S_SimpleDeclaration,
//      SymbolKind.S_VariableDeclaration,
//      SymbolKind.S_TypeDeclaration,
//      SymbolKind.S_RoutineDeclaration,
//      SymbolKind.S_Parameters,
//      SymbolKind.S_ParameterDeclaration,
//      SymbolKind.S_Type,
//      SymbolKind.S_PrimitiveType,
//      SymbolKind.S_RecordType,
//      SymbolKind.S_VariableDeclarations,
//      SymbolKind.S_ArrayType,
//      SymbolKind.S_Body,
//      SymbolKind.S_BodyDeclaration,
//      SymbolKind.S_Statement,
//      SymbolKind.S_Return,
//      SymbolKind.S_Assignment,
//      SymbolKind.S_RoutineCall,
//      SymbolKind.S_Expressions,
//      SymbolKind.S_WhileLoop,
//      SymbolKind.S_ForLoop,
//      SymbolKind.S_IfStatement,
//      SymbolKind.S_Expression,
//      SymbolKind.S_Relations,
//      SymbolKind.S_LogicWord,
//      SymbolKind.S_Relation,
//      SymbolKind.S_SimpleTail,
//      SymbolKind.S_RelationSign,
//      SymbolKind.S_Simple,
//      SymbolKind.S_FactorTail,
//      SymbolKind.S_FactorSign,
//      SymbolKind.S_Factor,
//      SymbolKind.S_SummandTail,
//      SymbolKind.S_SummandSign,
//      SymbolKind.S_Summand,
//      SymbolKind.S_Primary,
//      SymbolKind.S_ModifiablePrimary,
//      SymbolKind.S_ElementCall,
//      SymbolKind.S_Print,
//      SymbolKind.S_Identifier
//    };
//
//    static final SymbolKind get(int code) {
//      return values_[code];
//    }
//
//    public final int getCode() {
//      return this.yycode_;
//    }
//
//    /* Return YYSTR after stripping away unnecessary quotes and
//       backslashes, so that it's suitable for yyerror.  The heuristic is
//       that double-quoting is unnecessary unless the string contains an
//       apostrophe, a comma, or backslash (other than backslash-backslash).
//       YYSTR is taken from yytname.  */
//    private static String yytnamerr_(String yystr)
//    {
//      if (yystr.charAt (0) == '"')
//        {
//          StringBuffer yyr = new StringBuffer();
//          strip_quotes: for (int i = 1; i < yystr.length(); i++)
//            switch (yystr.charAt(i))
//              {
//              case '\'':
//              case ',':
//                break strip_quotes;
//
//              case '\\':
//                if (yystr.charAt(++i) != '\\')
//                  break strip_quotes;
//                /* Fall through.  */
//              default:
//                yyr.append(yystr.charAt(i));
//                break;
//
//              case '"':
//                return yyr.toString();
//              }
//        }
//      return yystr;
//    }
//
//    /* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
//       First, the terminals, then, starting at \a YYNTOKENS_, nonterminals.  */
//    private static final String[] yytname_ = yytname_init();
//  private static final String[] yytname_init()
//  {
//    return new String[]
//    {
//  "\"end of file\"", "error", "\"invalid token\"", "IDENTIFIER",
//  "INTEGER_LITERAL", "REAL_LITERAL", "TRUE", "FALSE", "VAR", "TYPE", "IS",
//  "END", "RECORD", "INTEGER", "REAL", "BOOLEAN", "ARRAY", "WHILE", "LOOP",
//  "FOR", "IN", "REVERSE", "IF", "THEN", "ELSE", "ROUTINE", "AND", "OR",
//  "NOT", "XOR", "PRINT", "RETURN", "LPAREN", "RPAREN", "LBRACKET",
//  "RBRACKET", "COMMA", "DOT", "COLON", "RANGE", "EQUALS", "ASSIGN",
//  "NEQUALS", "GREATER", "LESS", "LEQUALS", "GEQUALS", "PLUS", "MINUS",
//  "MULTIPLY", "DIVIDE", "REMAINDER", "$accept", "Program",
//  "GlobalDeclarations", "GlobalDeclaration", "SimpleDeclaration",
//  "VariableDeclaration", "TypeDeclaration", "RoutineDeclaration",
//  "Parameters", "ParameterDeclaration", "Type", "PrimitiveType",
//  "RecordType", "VariableDeclarations", "ArrayType", "Body",
//  "BodyDeclaration", "Statement", "Return", "Assignment", "RoutineCall",
//  "Expressions", "WhileLoop", "ForLoop", "IfStatement", "Expression",
//  "Relations", "LogicWord", "Relation", "SimpleTail", "RelationSign",
//  "Simple", "FactorTail", "FactorSign", "Factor", "SummandTail",
//  "SummandSign", "Summand", "Primary", "ModifiablePrimary", "ElementCall",
//  "Print", "Identifier", null
//    };
//  }
//
//    /* The user-facing name of this symbol.  */
//    public final String getName() {
//      return yytnamerr_(yytname_[yycode_]);
//    }
//
//  };
//
//
//  /**
//   * Communication interface between the scanner and the Bison-generated
//   * parser <tt>YYParser</tt>.
//   */
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
    /** Token LPAREN, to be returned by the scanner.  */
    static final int LPAREN = 287;
    /** Token RPAREN, to be returned by the scanner.  */
    static final int RPAREN = 288;
    /** Token LBRACKET, to be returned by the scanner.  */
    static final int LBRACKET = 289;
    /** Token RBRACKET, to be returned by the scanner.  */
    static final int RBRACKET = 290;
    /** Token COMMA, to be returned by the scanner.  */
    static final int COMMA = 291;
    /** Token DOT, to be returned by the scanner.  */
    static final int DOT = 292;
    /** Token COLON, to be returned by the scanner.  */
    static final int COLON = 293;
    /** Token RANGE, to be returned by the scanner.  */
    static final int RANGE = 294;
    /** Token EQUALS, to be returned by the scanner.  */
    static final int EQUALS = 295;
    /** Token ASSIGN, to be returned by the scanner.  */
    static final int ASSIGN = 296;
    /** Token NEQUALS, to be returned by the scanner.  */
    static final int NEQUALS = 297;
    /** Token GREATER, to be returned by the scanner.  */
    static final int GREATER = 298;
    /** Token LESS, to be returned by the scanner.  */
    static final int LESS = 299;
    /** Token LEQUALS, to be returned by the scanner.  */
    static final int LEQUALS = 300;
    /** Token GEQUALS, to be returned by the scanner.  */
    static final int GEQUALS = 301;
    /** Token PLUS, to be returned by the scanner.  */
    static final int PLUS = 302;
    /** Token MINUS, to be returned by the scanner.  */
    static final int MINUS = 303;
    /** Token MULTIPLY, to be returned by the scanner.  */
    static final int MULTIPLY = 304;
    /** Token DIVIDE, to be returned by the scanner.  */
    static final int DIVIDE = 305;
    /** Token REMAINDER, to be returned by the scanner.  */
    static final int REMAINDER = 306;

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


  }

//
//  /**
//   * The object doing lexical analysis for us.
//   */
//  private Lexer yylexer;
//
//
//
//
//
//  /**
//   * Instantiates the Bison-generated parser.
//   * @param yylexer The scanner that will supply tokens to the parser.
//   */
//  public YYParser (Lexer yylexer)
//  {
//
//    this.yylexer = yylexer;
//
//  }
//
//
//
//  private int yynerrs = 0;
//
//  /**
//   * The number of syntax errors so far.
//   */
//  public final int getNumberOfErrors () { return yynerrs; }
//
//  /**
//   * Print an error message via the lexer.
//   * Use a <code>null</code> location.
//   * @param msg The error message.
//   */
//  public final void yyerror(String msg) {
//      yylexer.yyerror((Location)null, msg);
//  }
//
//  /**
//   * Print an error message via the lexer.
//   * @param loc The location associated with the message.
//   * @param msg The error message.
//   */
//  public final void yyerror(Location loc, String msg) {
//      yylexer.yyerror(loc, msg);
//  }
//
//  /**
//   * Print an error message via the lexer.
//   * @param pos The position associated with the message.
//   * @param msg The error message.
//   */
//  public final void yyerror(Position pos, String msg) {
//      yylexer.yyerror(new Location (pos), msg);
//  }
//
//
//  private final class YYStack {
//    private int[] stateStack = new int[16];
//    private Location[] locStack = new Location[16];
//    private ILexem[] valueStack = new ILexem[16];
//
//    public int size = 16;
//    public int height = -1;
//
//    public final void push (int state, ILexem value, Location loc) {
//      height++;
//      if (size == height)
//        {
//          int[] newStateStack = new int[size * 2];
//          System.arraycopy (stateStack, 0, newStateStack, 0, height);
//          stateStack = newStateStack;
//          Location[] newLocStack = new Location[size * 2];
//          System.arraycopy (locStack, 0, newLocStack, 0, height);
//          locStack = newLocStack;
//
//          ILexem[] newValueStack = new ILexem[size * 2];
//          System.arraycopy (valueStack, 0, newValueStack, 0, height);
//          valueStack = newValueStack;
//
//          size *= 2;
//        }
//
//      stateStack[height] = state;
//      locStack[height] = loc;
//      valueStack[height] = value;
//    }
//
//    public final void pop () {
//      pop (1);
//    }
//
//    public final void pop (int num) {
//      // Avoid memory leaks... garbage collection is a white lie!
//      if (0 < num) {
//        java.util.Arrays.fill (valueStack, height - num + 1, height + 1, null);
//        java.util.Arrays.fill (locStack, height - num + 1, height + 1, null);
//      }
//      height -= num;
//    }
//
//    public final int stateAt (int i) {
//      return stateStack[height - i];
//    }
//
//
//    public final Location locationAt (int i) {
//      return locStack[height - i];
//    }
//
//    public final ILexem valueAt (int i) {
//      return valueStack[height - i];
//    }
//
//    // Print the state stack on the debug stream.
//    public void print (java.io.PrintStream out) {
//      out.print ("Stack now");
//
//      for (int i = 0; i <= height; i++)
//        {
//          out.print (' ');
//          out.print (stateStack[i]);
//        }
//      out.println ();
//    }
//  }
//
//  /**
//   * Returned by a Bison action in order to stop the parsing process and
//   * return success (<tt>true</tt>).
//   */
//  public static final int YYACCEPT = 0;
//
//  /**
//   * Returned by a Bison action in order to stop the parsing process and
//   * return failure (<tt>false</tt>).
//   */
//  public static final int YYABORT = 1;
//
//
//
//  /**
//   * Returned by a Bison action in order to start error recovery without
//   * printing an error message.
//   */
//  public static final int YYERROR = 2;
//
//  /**
//   * Internal return codes that are not supported for user semantic
//   * actions.
//   */
//  private static final int YYERRLAB = 3;
//  private static final int YYNEWSTATE = 4;
//  private static final int YYDEFAULT = 5;
//  private static final int YYREDUCE = 6;
//  private static final int YYERRLAB1 = 7;
//  private static final int YYRETURN = 8;
//
//
//  private int yyerrstatus_ = 0;
//
//
//  /**
//   * Whether error recovery is being done.  In this state, the parser
//   * reads token until it reaches a known state, and then restarts normal
//   * operation.
//   */
//  public final boolean recovering ()
//  {
//    return yyerrstatus_ == 0;
//  }
//
//  /** Compute post-reduction state.
//   * @param yystate   the current state
//   * @param yysym     the nonterminal to push on the stack
//   */
//  private int yyLRGotoState (int yystate, int yysym)
//  {
//    int yyr = yypgoto_[yysym - YYNTOKENS_] + yystate;
//    if (0 <= yyr && yyr <= YYLAST_ && yycheck_[yyr] == yystate)
//      return yytable_[yyr];
//    else
//      return yydefgoto_[yysym - YYNTOKENS_];
//  }
//
//  private int yyaction(int yyn, YYStack yystack, int yylen)
//  {
//    /* If YYLEN is nonzero, implement the default value of the action:
//       '$$ = $1'.  Otherwise, use the top of the stack.
//
//       Otherwise, the following line sets YYVAL to garbage.
//       This behavior is undocumented and Bison
//       users should not rely upon it.  */
//    ILexem yyval = (0 < yylen) ? yystack.valueAt(yylen - 1) : yystack.valueAt(0);
//    Location yyloc = yylloc(yystack, yylen);
//
//    switch (yyn)
//      {
//          case 2: /* Program: GlobalDeclarations  */
//  if (yyn == 2)
//    /* "parser.y":120  */
//                         {ast = yystack.valueAt (0);};
//  break;
//
//
//  case 3: /* GlobalDeclarations: %empty  */
//  if (yyn == 3)
//    /* "parser.y":124  */
//                  { yyval = new GlobalDeclarations();};
//  break;
//
//
//  case 4: /* GlobalDeclarations: GlobalDeclaration GlobalDeclarations  */
//  if (yyn == 4)
//    /* "parser.y":125  */
//                                           {yyval = yystack.valueAt (0); yystack.valueAt (0).add(yystack.valueAt (1));};
//  break;
//
//
//  case 5: /* GlobalDeclaration: SimpleDeclaration  */
//  if (yyn == 5)
//    /* "parser.y":129  */
//                        {yyval = yystack.valueAt (0);};
//  break;
//
//
//  case 6: /* GlobalDeclaration: RoutineDeclaration  */
//  if (yyn == 6)
//    /* "parser.y":130  */
//                         {yyval = yystack.valueAt (0);};
//  break;
//
//
//  case 7: /* SimpleDeclaration: VariableDeclaration  */
//  if (yyn == 7)
//    /* "parser.y":134  */
//                          { yyval = yystack.valueAt (0);};
//  break;
//
//
//  case 8: /* SimpleDeclaration: TypeDeclaration  */
//  if (yyn == 8)
//    /* "parser.y":135  */
//                      { yyval = yystack.valueAt (0);};
//  break;
//
//
//  case 9: /* VariableDeclaration: VAR Identifier COLON Type  */
//  if (yyn == 9)
//    /* "parser.y":139  */
//                                 {yyval = new VariableDeclaration(yystack.valueAt (2), yystack.valueAt (0));};
//  break;
//
//
//  case 10: /* VariableDeclaration: VAR Identifier COLON Type IS Expression  */
//  if (yyn == 10)
//    /* "parser.y":140  */
//                                               { yyval = new VariableDeclaration(yystack.valueAt (4), yystack.valueAt (2), yystack.valueAt (0)); };
//  break;
//
//
//  case 11: /* VariableDeclaration: VAR Identifier IS Expression  */
//  if (yyn == 11)
//    /* "parser.y":141  */
//                                    {yyval = new VariableDeclaration(yystack.valueAt (2), yystack.valueAt (0));};
//  break;
//
//
//  case 12: /* TypeDeclaration: TYPE Identifier IS Type  */
//  if (yyn == 12)
//    /* "parser.y":145  */
//                              {yyval = new TypeDeclaration(yystack.valueAt (2), yystack.valueAt (0));};
//  break;
//
//
//  case 13: /* RoutineDeclaration: ROUTINE Identifier LPAREN Parameters RPAREN IS Body END  */
//  if (yyn == 13)
//    /* "parser.y":149  */
//                                                               {
//    	yyval = new RoutineDeclaration(yystack.valueAt (6), yystack.valueAt (4), yystack.valueAt (1), null);
//    };
//  break;
//
//
//  case 14: /* RoutineDeclaration: ROUTINE Identifier LPAREN Parameters RPAREN COLON Type IS Body END  */
//  if (yyn == 14)
//    /* "parser.y":152  */
//                                                                          {
//    	yyval = new RoutineDeclaration(yystack.valueAt (8), yystack.valueAt (6), yystack.valueAt (3), yystack.valueAt (1));
//    };
//  break;
//
//
//  case 15: /* Parameters: ParameterDeclaration  */
//  if (yyn == 15)
//    /* "parser.y":160  */
//                           { yyval = new Parameters(yystack.valueAt (0));};
//  break;
//
//
//  case 16: /* Parameters: ParameterDeclaration COMMA Parameters  */
//  if (yyn == 16)
//    /* "parser.y":161  */
//                                            { yyval = yystack.valueAt (0); yystack.valueAt (0).add(yystack.valueAt (2)); };
//  break;
//
//
//  case 17: /* ParameterDeclaration: Identifier COLON Type  */
//  if (yyn == 17)
//    /* "parser.y":166  */
//                            { yyval = new Parameter(yystack.valueAt (2), yystack.valueAt (0)); };
//  break;
//
//
//  case 18: /* Type: PrimitiveType  */
//  if (yyn == 18)
//    /* "parser.y":170  */
//                    { yyval = yystack.valueAt (0); };
//  break;
//
//
//  case 19: /* Type: ArrayType  */
//  if (yyn == 19)
//    /* "parser.y":171  */
//                { yyval = yystack.valueAt (0); };
//  break;
//
//
//  case 20: /* Type: RecordType  */
//  if (yyn == 20)
//    /* "parser.y":172  */
//                 { yyval = yystack.valueAt (0); };
//  break;
//
//
//  case 21: /* Type: Identifier  */
//  if (yyn == 21)
//    /* "parser.y":173  */
//                 { yyval = yystack.valueAt (0); };
//  break;
//
//
//  case 22: /* PrimitiveType: INTEGER  */
//  if (yyn == 22)
//    /* "parser.y":177  */
//              { yyval = new PrimitiveType("integer");};
//  break;
//
//
//  case 23: /* PrimitiveType: REAL  */
//  if (yyn == 23)
//    /* "parser.y":178  */
//           { yyval = new PrimitiveType("real"); };
//  break;
//
//
//  case 24: /* PrimitiveType: BOOLEAN  */
//  if (yyn == 24)
//    /* "parser.y":179  */
//              { yyval = new PrimitiveType("boolean"); };
//  break;
//
//
//  case 25: /* RecordType: RECORD VariableDeclarations END  */
//  if (yyn == 25)
//    /* "parser.y":183  */
//                                      {yyval = yystack.valueAt (0);};
//  break;
//
//
//  case 26: /* VariableDeclarations: %empty  */
//  if (yyn == 26)
//    /* "parser.y":187  */
//                  { yyval = new RecordType(); };
//  break;
//
//
//  case 27: /* VariableDeclarations: VariableDeclaration VariableDeclarations  */
//  if (yyn == 27)
//    /* "parser.y":188  */
//                                               { yyval = yystack.valueAt (0); yystack.valueAt (0).add(yystack.valueAt (1));};
//  break;
//
//
//  case 28: /* ArrayType: ARRAY LBRACKET Expression RBRACKET Type  */
//  if (yyn == 28)
//    /* "parser.y":193  */
//                                              { yyval = new ArrayType(yystack.valueAt (2), yystack.valueAt (0)); };
//  break;
//
//
//  case 29: /* Body: %empty  */
//  if (yyn == 29)
//    /* "parser.y":197  */
//                  { yyval = new Body();};
//  break;
//
//
//  case 30: /* Body: BodyDeclaration Body  */
//  if (yyn == 30)
//    /* "parser.y":198  */
//                           {yyval = yystack.valueAt (0); yystack.valueAt (0).addBody(yystack.valueAt (1));};
//  break;
//
//
//  case 31: /* BodyDeclaration: SimpleDeclaration  */
//  if (yyn == 31)
//    /* "parser.y":202  */
//                        {yyval = yystack.valueAt (0);};
//  break;
//
//
//  case 32: /* BodyDeclaration: Statement  */
//  if (yyn == 32)
//    /* "parser.y":203  */
//                {yyval = yystack.valueAt (0);};
//  break;
//
//
//  case 33: /* Statement: Assignment  */
//  if (yyn == 33)
//    /* "parser.y":207  */
//                  {yyval = yystack.valueAt (0);};
//  break;
//
//
//  case 34: /* Statement: RoutineCall  */
//  if (yyn == 34)
//    /* "parser.y":208  */
//                   {yyval = yystack.valueAt (0); };
//  break;
//
//
//  case 35: /* Statement: WhileLoop  */
//  if (yyn == 35)
//    /* "parser.y":209  */
//                 {yyval = yystack.valueAt (0);};
//  break;
//
//
//  case 36: /* Statement: ForLoop  */
//  if (yyn == 36)
//    /* "parser.y":210  */
//               {yyval = yystack.valueAt (0);};
//  break;
//
//
//  case 37: /* Statement: IfStatement  */
//  if (yyn == 37)
//    /* "parser.y":211  */
//                  {yyval = yystack.valueAt (0);};
//  break;
//
//
//  case 38: /* Statement: Print  */
//  if (yyn == 38)
//    /* "parser.y":212  */
//             {yyval = yystack.valueAt (0);};
//  break;
//
//
//  case 39: /* Statement: Return  */
//  if (yyn == 39)
//    /* "parser.y":213  */
//              {yyval = yystack.valueAt (0);};
//  break;
//
//
//  case 40: /* Return: RETURN Expression  */
//  if (yyn == 40)
//    /* "parser.y":217  */
//                        {yyval = new Return(yystack.valueAt (0));};
//  break;
//
//
//  case 41: /* Assignment: ModifiablePrimary ASSIGN Expression  */
//  if (yyn == 41)
//    /* "parser.y":221  */
//                                          {yyval = new Assignment(yystack.valueAt (2), yystack.valueAt (0));};
//  break;
//
//
//  case 42: /* RoutineCall: Identifier LPAREN Expressions RPAREN  */
//  if (yyn == 42)
//    /* "parser.y":225  */
//                                           {yyval = new RoutineCall(yystack.valueAt (3), yystack.valueAt (1)); };
//  break;
//
//
//  case 43: /* Expressions: Expression  */
//  if (yyn == 43)
//    /* "parser.y":229  */
//                 { yyval = new Expressions(yystack.valueAt (0)); };
//  break;
//
//
//  case 44: /* Expressions: Expression COMMA Expressions  */
//  if (yyn == 44)
//    /* "parser.y":230  */
//                                   { yyval = yystack.valueAt (0); yystack.valueAt (0).add(yystack.valueAt (2)); };
//  break;
//
//
//  case 45: /* WhileLoop: WHILE Expression LOOP Body END  */
//  if (yyn == 45)
//    /* "parser.y":234  */
//                                     {yyval = new WhileLoop(yystack.valueAt (3), yystack.valueAt (1)); };
//  break;
//
//
//  case 46: /* ForLoop: FOR Identifier IN Expression RANGE Expression LOOP Body END  */
//  if (yyn == 46)
//    /* "parser.y":239  */
//    {yyval = new ForLoop(yystack.valueAt (7), yystack.valueAt (5), yystack.valueAt (3), yystack.valueAt (1), false);};
//  break;
//
//
//  case 47: /* ForLoop: FOR Identifier IN REVERSE Expression RANGE Expression LOOP Body END  */
//  if (yyn == 47)
//    /* "parser.y":241  */
//    {yyval = new ForLoop(yystack.valueAt (8), yystack.valueAt (5), yystack.valueAt (3), yystack.valueAt (1), true);};
//  break;
//
//
//  case 48: /* IfStatement: IF Expression THEN Body END  */
//  if (yyn == 48)
//    /* "parser.y":245  */
//                                  {yyval = new IfStatement(yystack.valueAt (3), yystack.valueAt (1));};
//  break;
//
//
//  case 49: /* IfStatement: IF Expression THEN Body ELSE Body END  */
//  if (yyn == 49)
//    /* "parser.y":246  */
//                                            {yyval = new IfStatement(yystack.valueAt (5), yystack.valueAt (3), yystack.valueAt (1));};
//  break;
//
//
//  case 50: /* Expression: Relation Relations  */
//  if (yyn == 50)
//    /* "parser.y":251  */
//                         { yyval = yystack.valueAt (0); yystack.valueAt (0).add(yystack.valueAt (1)); };
//  break;
//
//
//  case 51: /* Expression: SummandSign Relation Relations  */
//  if (yyn == 51)
//    /* "parser.y":252  */
//                                     { yyval = yystack.valueAt (0);  yystack.valueAt (1).setSummandSign(yystack.valueAt (2)); yystack.valueAt (0).add(yystack.valueAt (1)); };
//  break;
//
//
//  case 52: /* Expression: NOT Relation Relations  */
//  if (yyn == 52)
//    /* "parser.y":253  */
//                             { yyval = yystack.valueAt (0); yystack.valueAt (1).setNot(); yystack.valueAt (0).add(yystack.valueAt (1)); };
//  break;
//
//
//  case 53: /* Expression: NOT SummandSign Relation Relations  */
//  if (yyn == 53)
//    /* "parser.y":254  */
//                                         { yyval = yystack.valueAt (0); yystack.valueAt (1).setSummandSign(yystack.valueAt (2)); yystack.valueAt (1).setNot(); yystack.valueAt (0).add(yystack.valueAt (1)); };
//  break;
//
//
//  case 54: /* Relations: %empty  */
//  if (yyn == 54)
//    /* "parser.y":258  */
//                   { yyval = new Expression();};
//  break;
//
//
//  case 55: /* Relations: LogicWord Relation Relations  */
//  if (yyn == 55)
//    /* "parser.y":259  */
//                                   { yyval = yystack.valueAt (0); yystack.valueAt (0).add2(yystack.valueAt (1), yystack.valueAt (2)); };
//  break;
//
//
//  case 56: /* Relations: LogicWord NOT Relation Relations  */
//  if (yyn == 56)
//    /* "parser.y":260  */
//                                       {yyval = yystack.valueAt (0); yystack.valueAt (1).setNot(); yystack.valueAt (0).add2(yystack.valueAt (1), yystack.valueAt (3));};
//  break;
//
//
//  case 57: /* LogicWord: AND  */
//  if (yyn == 57)
//    /* "parser.y":266  */
//          { yyval = new LogicWord("and"); };
//  break;
//
//
//  case 58: /* LogicWord: OR  */
//  if (yyn == 58)
//    /* "parser.y":267  */
//         { yyval = new LogicWord("or"); };
//  break;
//
//
//  case 59: /* LogicWord: XOR  */
//  if (yyn == 59)
//    /* "parser.y":268  */
//          { yyval = new LogicWord("xor"); };
//  break;
//
//
//  case 60: /* Relation: Simple SimpleTail  */
//  if (yyn == 60)
//    /* "parser.y":272  */
//                        { yyval = yystack.valueAt (0); yystack.valueAt (0).add(yystack.valueAt (1)); };
//  break;
//
//
//  case 61: /* SimpleTail: %empty  */
//  if (yyn == 61)
//    /* "parser.y":277  */
//                  { yyval = new IRelation(); };
//  break;
//
//
//  case 62: /* SimpleTail: RelationSign Simple SimpleTail  */
//  if (yyn == 62)
//    /* "parser.y":278  */
//                                     { yyval = yystack.valueAt (0); yystack.valueAt (0).add2(yystack.valueAt (1), yystack.valueAt (2)); };
//  break;
//
//
//  case 63: /* RelationSign: LESS  */
//  if (yyn == 63)
//    /* "parser.y":283  */
//           { yyval = new RelationSign("<"); };
//  break;
//
//
//  case 64: /* RelationSign: GREATER  */
//  if (yyn == 64)
//    /* "parser.y":284  */
//              { yyval = new RelationSign(">"); };
//  break;
//
//
//  case 65: /* RelationSign: LEQUALS  */
//  if (yyn == 65)
//    /* "parser.y":285  */
//              { yyval = new RelationSign("<="); };
//  break;
//
//
//  case 66: /* RelationSign: GEQUALS  */
//  if (yyn == 66)
//    /* "parser.y":286  */
//              { yyval = new RelationSign(">="); };
//  break;
//
//
//  case 67: /* RelationSign: EQUALS  */
//  if (yyn == 67)
//    /* "parser.y":287  */
//             { yyval =  new RelationSign("="); };
//  break;
//
//
//  case 68: /* RelationSign: NEQUALS  */
//  if (yyn == 68)
//    /* "parser.y":288  */
//              { yyval = new RelationSign("/="); };
//  break;
//
//
//  case 69: /* Simple: Factor FactorTail  */
//  if (yyn == 69)
//    /* "parser.y":292  */
//                        { yyval = yystack.valueAt (0); yystack.valueAt (0).add(yystack.valueAt (1)); };
//  break;
//
//
//  case 70: /* FactorTail: %empty  */
//  if (yyn == 70)
//    /* "parser.y":296  */
//                  {yyval = new Simple(); };
//  break;
//
//
//  case 71: /* FactorTail: FactorSign Factor FactorTail  */
//  if (yyn == 71)
//    /* "parser.y":297  */
//                                   { yyval = yystack.valueAt (0); yystack.valueAt (0).add2(yystack.valueAt (1), yystack.valueAt (2)); };
//  break;
//
//
//  case 72: /* FactorSign: MULTIPLY  */
//  if (yyn == 72)
//    /* "parser.y":301  */
//               { yyval = new FactorSign("*"); };
//  break;
//
//
//  case 73: /* FactorSign: DIVIDE  */
//  if (yyn == 73)
//    /* "parser.y":302  */
//             { yyval = new FactorSign("/"); };
//  break;
//
//
//  case 74: /* FactorSign: REMAINDER  */
//  if (yyn == 74)
//    /* "parser.y":303  */
//                { yyval = new FactorSign("%"); };
//  break;
//
//
//  case 75: /* Factor: Summand SummandTail  */
//  if (yyn == 75)
//    /* "parser.y":307  */
//                          { yyval = yystack.valueAt (0); yystack.valueAt (0).add(yystack.valueAt (1)); };
//  break;
//
//
//  case 76: /* SummandTail: %empty  */
//  if (yyn == 76)
//    /* "parser.y":311  */
//                  { yyval = new Factor(); };
//  break;
//
//
//  case 77: /* SummandTail: SummandSign Summand SummandTail  */
//  if (yyn == 77)
//    /* "parser.y":312  */
//                                      { yyval = yystack.valueAt (0); yystack.valueAt (0).add2(yystack.valueAt (1), yystack.valueAt (2)); };
//  break;
//
//
//  case 78: /* SummandSign: PLUS  */
//  if (yyn == 78)
//    /* "parser.y":315  */
//           { yyval = new SummandSign("+"); };
//  break;
//
//
//  case 79: /* SummandSign: MINUS  */
//  if (yyn == 79)
//    /* "parser.y":316  */
//            { yyval = new SummandSign("-"); };
//  break;
//
//
//  case 80: /* Summand: Primary  */
//  if (yyn == 80)
//    /* "parser.y":320  */
//              { yyval = new Summand(yystack.valueAt (0)); };
//  break;
//
//
//  case 81: /* Summand: LPAREN Expression RPAREN  */
//  if (yyn == 81)
//    /* "parser.y":321  */
//                               { yyval = new Summand(yystack.valueAt (1)); };
//  break;
//
//
//  case 82: /* Primary: INTEGER_LITERAL  */
//  if (yyn == 82)
//    /* "parser.y":325  */
//                      { yyval = yystack.valueAt (0);};
//  break;
//
//
//  case 83: /* Primary: REAL_LITERAL  */
//  if (yyn == 83)
//    /* "parser.y":326  */
//                   { yyval = yystack.valueAt (0); };
//  break;
//
//
//  case 84: /* Primary: TRUE  */
//  if (yyn == 84)
//    /* "parser.y":327  */
//           { yyval = new BooleanLiteral(true); };
//  break;
//
//
//  case 85: /* Primary: FALSE  */
//  if (yyn == 85)
//    /* "parser.y":328  */
//            { yyval = new BooleanLiteral(false); };
//  break;
//
//
//  case 86: /* Primary: ModifiablePrimary  */
//  if (yyn == 86)
//    /* "parser.y":329  */
//                        { yyval = yystack.valueAt (0); };
//  break;
//
//
//  case 87: /* Primary: RoutineCall  */
//  if (yyn == 87)
//    /* "parser.y":330  */
//                  { yyval = yystack.valueAt (0);};
//  break;
//
//
//  case 88: /* ModifiablePrimary: Identifier ElementCall  */
//  if (yyn == 88)
//    /* "parser.y":334  */
//                             { yyval = new ModifiablePrimary(yystack.valueAt (1), yystack.valueAt (0)); };
//  break;
//
//
//  case 89: /* ElementCall: %empty  */
//  if (yyn == 89)
//    /* "parser.y":338  */
//                  { yyval = new ElementCall(); };
//  break;
//
//
//  case 90: /* ElementCall: DOT Identifier ElementCall  */
//  if (yyn == 90)
//    /* "parser.y":339  */
//                                 { yyval = yystack.valueAt (0); yystack.valueAt (0).add(yystack.valueAt (1)); };
//  break;
//
//
//  case 91: /* ElementCall: LBRACKET Expression RBRACKET ElementCall  */
//  if (yyn == 91)
//    /* "parser.y":340  */
//                                               { yyval = yystack.valueAt (0); yystack.valueAt (0).add(yystack.valueAt (2));};
//  break;
//
//
//  case 92: /* Print: PRINT LPAREN Expressions RPAREN  */
//  if (yyn == 92)
//    /* "parser.y":344  */
//                                      {yyval = new Print(yystack.valueAt (1));};
//  break;
//
//
//  case 93: /* Identifier: IDENTIFIER  */
//  if (yyn == 93)
//    /* "parser.y":348  */
//                   {yyval = yylexer.getLVal();};
//  break;
//
//
//
///* "YYParser.java":1405  */
//
//        default: break;
//      }
//
//    yystack.pop(yylen);
//    yylen = 0;
//    /* Shift the result of the reduction.  */
//    int yystate = yyLRGotoState(yystack.stateAt(0), yyr1_[yyn]);
//    yystack.push(yystate, yyval, yyloc);
//    return YYNEWSTATE;
//  }
//
//
//
//
//  /**
//   * Parse input from the scanner that was specified at object construction
//   * time.  Return whether the end of the input was reached successfully.
//   *
//   * @return <tt>true</tt> if the parsing succeeds.  Note that this does not
//   *          imply that there were no syntax errors.
//   */
//  public boolean parse()
//
//  {
//    /* @$.  */
//    Location yyloc;
//
//
//    /* Lookahead token kind.  */
//    int yychar = YYEMPTY_;
//    /* Lookahead symbol kind.  */
//    SymbolKind yytoken = null;
//
//    /* State.  */
//    int yyn = 0;
//    int yylen = 0;
//    int yystate = 0;
//    YYStack yystack = new YYStack ();
//    int label = YYNEWSTATE;
//
//
//    /* The location where the error started.  */
//    Location yyerrloc = null;
//
//    /* Location. */
//    Location yylloc = new Location (null, null);
//
//    /* Semantic value of the lookahead.  */
//    ILexem yylval = null;
//
//    yyerrstatus_ = 0;
//    yynerrs = 0;
//
//    /* Initialize the stack.  */
//    yystack.push (yystate, yylval, yylloc);
//
//
//
//    for (;;)
//      switch (label)
//      {
//        /* New state.  Unlike in the C/C++ skeletons, the state is already
//           pushed when we come here.  */
//      case YYNEWSTATE:
//
//        /* Accept?  */
//        if (yystate == YYFINAL_)
//          return true;
//
//        /* Take a decision.  First try without lookahead.  */
//        yyn = yypact_[yystate];
//        if (yyPactValueIsDefault (yyn))
//          {
//            label = YYDEFAULT;
//            break;
//          }
//
//        /* Read a lookahead token.  */
//        if (yychar == YYEMPTY_)
//          {
//
//            yychar = yylexer.yylex ();
//            yylval = yylexer.getLVal();
//            yylloc = new Location(yylexer.getStartPos(),
//                                          yylexer.getEndPos());
//
//          }
//
//        /* Convert token to internal form.  */
//        yytoken = yytranslate_ (yychar);
//
//        if (yytoken == SymbolKind.S_YYerror)
//          {
//            // The scanner already issued an error message, process directly
//            // to error recovery.  But do not keep the error token as
//            // lookahead, it is too special and may lead us to an endless
//            // loop in error recovery. */
//            yychar = Lexer.YYUNDEF;
//            yytoken = SymbolKind.S_YYUNDEF;
//            yyerrloc = yylloc;
//            label = YYERRLAB1;
//          }
//        else
//          {
//            /* If the proper action on seeing token YYTOKEN is to reduce or to
//               detect an error, take that action.  */
//            yyn += yytoken.getCode();
//            if (yyn < 0 || YYLAST_ < yyn || yycheck_[yyn] != yytoken.getCode())
//              label = YYDEFAULT;
//
//            /* <= 0 means reduce or error.  */
//            else if ((yyn = yytable_[yyn]) <= 0)
//              {
//                if (yyTableValueIsError (yyn))
//                  label = YYERRLAB;
//                else
//                  {
//                    yyn = -yyn;
//                    label = YYREDUCE;
//                  }
//              }
//
//            else
//              {
//                /* Shift the lookahead token.  */
//                /* Discard the token being shifted.  */
//                yychar = YYEMPTY_;
//
//                /* Count tokens shifted since error; after three, turn off error
//                   status.  */
//                if (yyerrstatus_ > 0)
//                  --yyerrstatus_;
//
//                yystate = yyn;
//                yystack.push (yystate, yylval, yylloc);
//                label = YYNEWSTATE;
//              }
//          }
//        break;
//
//      /*-----------------------------------------------------------.
//      | yydefault -- do the default action for the current state.  |
//      `-----------------------------------------------------------*/
//      case YYDEFAULT:
//        yyn = yydefact_[yystate];
//        if (yyn == 0)
//          label = YYERRLAB;
//        else
//          label = YYREDUCE;
//        break;
//
//      /*-----------------------------.
//      | yyreduce -- Do a reduction.  |
//      `-----------------------------*/
//      case YYREDUCE:
//        yylen = yyr2_[yyn];
//        label = yyaction(yyn, yystack, yylen);
//        yystate = yystack.stateAt (0);
//        break;
//
//      /*------------------------------------.
//      | yyerrlab -- here on detecting error |
//      `------------------------------------*/
//      case YYERRLAB:
//        /* If not already recovering from an error, report this error.  */
//        if (yyerrstatus_ == 0)
//          {
//            ++yynerrs;
//            if (yychar == YYEMPTY_)
//              yytoken = null;
//            yyreportSyntaxError (new Context (yystack, yytoken, yylloc));
//          }
//
//        yyerrloc = yylloc;
//        if (yyerrstatus_ == 3)
//          {
//            /* If just tried and failed to reuse lookahead token after an
//               error, discard it.  */
//
//            if (yychar <= Lexer.YYEOF)
//              {
//                /* Return failure if at end of input.  */
//                if (yychar == Lexer.YYEOF)
//                  return false;
//              }
//            else
//              yychar = YYEMPTY_;
//          }
//
//        /* Else will try to reuse lookahead token after shifting the error
//           token.  */
//        label = YYERRLAB1;
//        break;
//
//      /*-------------------------------------------------.
//      | errorlab -- error raised explicitly by YYERROR.  |
//      `-------------------------------------------------*/
//      case YYERROR:
//        yyerrloc = yystack.locationAt (yylen - 1);
//        /* Do not reclaim the symbols of the rule which action triggered
//           this YYERROR.  */
//        yystack.pop (yylen);
//        yylen = 0;
//        yystate = yystack.stateAt (0);
//        label = YYERRLAB1;
//        break;
//
//      /*-------------------------------------------------------------.
//      | yyerrlab1 -- common code for both syntax error and YYERROR.  |
//      `-------------------------------------------------------------*/
//      case YYERRLAB1:
//        yyerrstatus_ = 3;       /* Each real token shifted decrements this.  */
//
//        // Pop stack until we find a state that shifts the error token.
//        for (;;)
//          {
//            yyn = yypact_[yystate];
//            if (!yyPactValueIsDefault (yyn))
//              {
//                yyn += SymbolKind.S_YYerror.getCode();
//                if (0 <= yyn && yyn <= YYLAST_
//                    && yycheck_[yyn] == SymbolKind.S_YYerror.getCode())
//                  {
//                    yyn = yytable_[yyn];
//                    if (0 < yyn)
//                      break;
//                  }
//              }
//
//            /* Pop the current state because it cannot handle the
//             * error token.  */
//            if (yystack.height == 0)
//              return false;
//
//
//            yyerrloc = yystack.locationAt (0);
//            yystack.pop ();
//            yystate = yystack.stateAt (0);
//          }
//
//        if (label == YYABORT)
//          /* Leave the switch.  */
//          break;
//
//
//        /* Muck with the stack to setup for yylloc.  */
//        yystack.push (0, null, yylloc);
//        yystack.push (0, null, yyerrloc);
//        yyloc = yylloc (yystack, 2);
//        yystack.pop (2);
//
//        /* Shift the error token.  */
//
//        yystate = yyn;
//        yystack.push (yyn, yylval, yyloc);
//        label = YYNEWSTATE;
//        break;
//
//        /* Accept.  */
//      case YYACCEPT:
//        return true;
//
//        /* Abort.  */
//      case YYABORT:
//        return false;
//      }
//}
//
//
//
//
//  /**
//   * Information needed to get the list of expected tokens and to forge
//   * a syntax error diagnostic.
//   */
//  public static final class Context
//  {
//    Context (YYStack stack, SymbolKind token, Location loc)
//    {
//      yystack = stack;
//      yytoken = token;
//      yylocation = loc;
//    }
//
//    private YYStack yystack;
//
//
//    /**
//     * The symbol kind of the lookahead token.
//     */
//    public final SymbolKind getToken ()
//    {
//      return yytoken;
//    }
//
//    private SymbolKind yytoken;
//
//    /**
//     * The location of the lookahead.
//     */
//    public final Location getLocation ()
//    {
//      return yylocation;
//    }
//
//    private Location yylocation;
//    static final int NTOKENS = YYParser.YYNTOKENS_;
//
//    /**
//     * Put in YYARG at most YYARGN of the expected tokens given the
//     * current YYCTX, and return the number of tokens stored in YYARG.  If
//     * YYARG is null, return the number of expected tokens (guaranteed to
//     * be less than YYNTOKENS).
//     */
//    int getExpectedTokens (SymbolKind yyarg[], int yyargn)
//    {
//      return getExpectedTokens (yyarg, 0, yyargn);
//    }
//
//    int getExpectedTokens (SymbolKind yyarg[], int yyoffset, int yyargn)
//    {
//      int yycount = yyoffset;
//      int yyn = yypact_[this.yystack.stateAt (0)];
//      if (!yyPactValueIsDefault (yyn))
//        {
//          /* Start YYX at -YYN if negative to avoid negative
//             indexes in YYCHECK.  In other words, skip the first
//             -YYN actions for this state because they are default
//             actions.  */
//          int yyxbegin = yyn < 0 ? -yyn : 0;
//          /* Stay within bounds of both yycheck and yytname.  */
//          int yychecklim = YYLAST_ - yyn + 1;
//          int yyxend = yychecklim < NTOKENS ? yychecklim : NTOKENS;
//          for (int yyx = yyxbegin; yyx < yyxend; ++yyx)
//            if (yycheck_[yyx + yyn] == yyx && yyx != SymbolKind.S_YYerror.getCode()
//                && !yyTableValueIsError(yytable_[yyx + yyn]))
//              {
//                if (yyarg == null)
//                  yycount += 1;
//                else if (yycount == yyargn)
//                  return 0; // FIXME: this is incorrect.
//                else
//                  yyarg[yycount++] = SymbolKind.get(yyx);
//              }
//        }
//      if (yyarg != null && yycount == yyoffset && yyoffset < yyargn)
//        yyarg[yycount] = null;
//      return yycount - yyoffset;
//    }
//  }
//
//
//
//  /**
//   * Build and emit a "syntax error" message in a user-defined way.
//   *
//   * @param ctx  The context of the error.
//   */
//  private void yyreportSyntaxError(Context yyctx) {
//      yyerror(yyctx.yylocation, "syntax error");
//  }
//
//  /**
//   * Whether the given <code>yypact_</code> value indicates a defaulted state.
//   * @param yyvalue   the value to check
//   */
//  private static boolean yyPactValueIsDefault (int yyvalue)
//  {
//    return yyvalue == yypact_ninf_;
//  }
//
//  /**
//   * Whether the given <code>yytable_</code>
//   * value indicates a syntax error.
//   * @param yyvalue the value to check
//   */
//  private static boolean yyTableValueIsError (int yyvalue)
//  {
//    return yyvalue == yytable_ninf_;
//  }
//
//  private static final short yypact_ninf_ = -118;
//  private static final short yytable_ninf_ = -1;
//
///* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
//   STATE-NUM.  */
//  private static final short[] yypact_ = yypact_init();
//  private static final short[] yypact_init()
//  {
//    return new short[]
//    {
//       4,    23,    23,    23,    30,  -118,     4,  -118,  -118,  -118,
//    -118,  -118,    -3,    22,    10,  -118,  -118,   133,    85,    85,
//      23,  -118,  -118,  -118,  -118,   140,   133,  -118,  -118,  -118,
//    -118,    19,   150,    80,    78,   -26,  -118,  -118,    28,    36,
//    -118,  -118,  -118,    29,    49,  -118,  -118,  -118,  -118,  -118,
//      31,    34,    33,    19,    78,    44,  -118,  -118,  -118,  -118,
//     151,  -118,  -118,  -118,  -118,  -118,  -118,  -118,    78,  -118,
//    -118,  -118,  -118,    78,    19,  -118,    78,   133,   133,    23,
//    -118,    36,    67,   133,   133,     1,    23,    85,  -118,    19,
//    -118,    78,    19,   150,    80,  -118,   -26,    56,    54,    59,
//      74,  -118,  -118,    61,  -118,   167,    85,  -118,  -118,  -118,
//      19,  -118,  -118,  -118,  -118,  -118,   133,    74,  -118,    85,
//     133,    23,   133,    70,   133,  -118,   101,   167,  -118,  -118,
//    -118,  -118,  -118,  -118,  -118,    72,  -118,   105,  -118,  -118,
//    -118,  -118,    99,    98,    97,   133,  -118,  -118,  -118,   133,
//     167,   167,   121,   167,    89,  -118,   112,   122,   133,    95,
//      16,  -118,  -118,  -118,   111,   133,  -118,   167,   133,   134,
//     148,   142,   167,  -118,   167,   152,   153,  -118,  -118
//    };
//  }
//
///* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
//   Performed when YYTABLE does not specify something else to do.  Zero
//   means the default is an error.  */
//  private static final byte[] yydefact_ = yydefact_init();
//  private static final byte[] yydefact_init()
//  {
//    return new byte[]
//    {
//       3,     0,     0,     0,     0,     2,     3,     5,     7,     8,
//       6,    93,     0,     0,     0,     1,     4,     0,     0,     0,
//       0,    82,    83,    84,    85,     0,     0,    78,    79,    87,
//      11,    54,    61,    70,     0,    76,    80,    86,    89,    26,
//      22,    23,    24,     0,     9,    18,    20,    19,    21,    12,
//       0,    15,     0,    54,     0,     0,    57,    58,    59,    50,
//       0,    67,    68,    64,    63,    65,    66,    60,     0,    72,
//      73,    74,    69,     0,    54,    75,     0,     0,     0,     0,
//      88,    26,     0,     0,     0,     0,     0,     0,    52,    54,
//      81,     0,    54,    61,    70,    51,    76,     0,    43,     0,
//      89,    27,    25,     0,    10,    29,     0,    16,    17,    53,
//      54,    55,    62,    71,    77,    42,     0,    89,    90,     0,
//       0,     0,     0,     0,     0,    31,     0,    29,    32,    39,
//      33,    34,    35,    36,    37,     0,    38,     0,    56,    44,
//      91,    28,     0,     0,     0,     0,    40,    13,    30,     0,
//      29,    29,     0,    29,     0,    41,     0,     0,     0,     0,
//       0,    92,    14,    45,     0,     0,    48,    29,     0,     0,
//       0,     0,    29,    49,    29,     0,     0,    46,    47
//    };
//  }
//
///* YYPGOTO[NTERM-NUM].  */
//  private static final short[] yypgoto_ = yypgoto_init();
//  private static final short[] yypgoto_init()
//  {
//    return new short[]
//    {
//    -118,  -118,   156,  -118,    25,   -23,  -118,  -118,    81,  -118,
//     -15,  -118,  -118,    90,  -118,  -117,  -118,  -118,  -118,  -118,
//     -99,  -108,  -118,  -118,  -118,   -17,   -36,  -118,   -11,    73,
//    -118,   106,    79,  -118,   104,    82,   -20,   109,  -118,   -58,
//     -76,  -118,     0
//    };
//  }
//
///* YYDEFGOTO[NTERM-NUM].  */
//  private static final short[] yydefgoto_ = yydefgoto_init();
//  private static final short[] yydefgoto_init()
//  {
//    return new short[]
//    {
//      -1,     4,     5,     6,   125,     8,     9,    10,    50,    51,
//      44,    45,    46,    82,    47,   126,   127,   128,   129,   130,
//      29,    97,   132,   133,   134,    98,    59,    60,    31,    67,
//      68,    32,    72,    73,    33,    75,    34,    35,    36,    37,
//      80,   136,    38
//    };
//  }
//
///* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
//   positive, shift that token.  If negative, reduce the rule whose
//   number is the opposite.  If YYTABLE_NINF, syntax error.  */
//  private static final short[] yytable_ = yytable_init();
//  private static final short[] yytable_init()
//  {
//    return new short[]
//    {
//      30,    12,    13,    14,    49,    54,   131,    17,   139,    55,
//     148,   105,     1,     2,    53,    76,    81,    88,    48,    48,
//      52,    27,    28,    74,   118,     7,    11,   166,   131,     3,
//      15,     7,    19,   156,   157,    18,   160,   154,    95,   106,
//     167,   140,    20,    89,     1,    56,    57,   135,    58,    92,
//     170,   131,   131,   109,   131,   175,   111,   176,    81,    84,
//      77,    99,    78,    83,    85,    79,   103,   104,   131,   135,
//      86,    87,   108,   131,   138,   131,    76,    90,   102,   100,
//     110,    11,    21,    22,    23,    24,    52,    48,    11,   115,
//     116,   137,   135,   135,   117,   135,   119,    39,    40,    41,
//      42,    43,   145,   142,   141,   144,    48,   146,    78,   135,
//      26,    79,   147,   149,   135,   150,   135,   151,   152,    48,
//     153,   143,   161,   162,    11,    21,    22,    23,    24,    69,
//      70,    71,   155,   163,   165,   159,    11,    21,    22,    23,
//      24,   164,   158,    11,    21,    22,    23,    24,   169,    25,
//     168,   171,   172,    26,    11,    21,    22,    23,    24,   173,
//     174,    25,    16,   177,   178,    26,   112,   107,    27,    28,
//      11,   101,    26,   113,    93,     1,     2,    94,   114,    91,
//      27,    28,     0,    26,   120,    96,   121,    27,    28,   122,
//      61,     0,    62,    63,    64,    65,    66,   123,   124
//    };
//  }
//
//private static final short[] yycheck_ = yycheck_init();
//  private static final short[] yycheck_init()
//  {
//    return new short[]
//    {
//      17,     1,     2,     3,    19,    25,   105,    10,   116,    26,
//     127,    10,     8,     9,    25,    35,    39,    53,    18,    19,
//      20,    47,    48,    34,   100,     0,     3,    11,   127,    25,
//       0,     6,    10,   150,   151,    38,   153,   145,    74,    38,
//      24,   117,    32,    54,     8,    26,    27,   105,    29,    60,
//     167,   150,   151,    89,   153,   172,    92,   174,    81,    10,
//      32,    78,    34,    34,    33,    37,    83,    84,   167,   127,
//      36,    38,    87,   172,   110,   174,    96,    33,    11,    79,
//      91,     3,     4,     5,     6,     7,    86,    87,     3,    33,
//      36,   106,   150,   151,    35,   153,    35,    12,    13,    14,
//      15,    16,    32,   120,   119,   122,   106,   124,    34,   167,
//      32,    37,    11,    41,   172,    10,   174,    18,    20,   119,
//      23,   121,    33,    11,     3,     4,     5,     6,     7,    49,
//      50,    51,   149,    11,    39,   152,     3,     4,     5,     6,
//       7,   158,    21,     3,     4,     5,     6,     7,   165,    28,
//      39,   168,    18,    32,     3,     4,     5,     6,     7,    11,
//      18,    28,     6,    11,    11,    32,    93,    86,    47,    48,
//       3,    81,    32,    94,    68,     8,     9,    73,    96,    28,
//      47,    48,    -1,    32,    17,    76,    19,    47,    48,    22,
//      40,    -1,    42,    43,    44,    45,    46,    30,    31
//    };
//  }
//
///* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
//   symbol of state STATE-NUM.  */
//  private static final byte[] yystos_ = yystos_init();
//  private static final byte[] yystos_init()
//  {
//    return new byte[]
//    {
//       0,     8,     9,    25,    53,    54,    55,    56,    57,    58,
//      59,     3,    94,    94,    94,     0,    54,    10,    38,    10,
//      32,     4,     5,     6,     7,    28,    32,    47,    48,    72,
//      77,    80,    83,    86,    88,    89,    90,    91,    94,    12,
//      13,    14,    15,    16,    62,    63,    64,    66,    94,    62,
//      60,    61,    94,    80,    88,    77,    26,    27,    29,    78,
//      79,    40,    42,    43,    44,    45,    46,    81,    82,    49,
//      50,    51,    84,    85,    80,    87,    88,    32,    34,    37,
//      92,    57,    65,    34,    10,    33,    36,    38,    78,    80,
//      33,    28,    80,    83,    86,    78,    89,    73,    77,    77,
//      94,    65,    11,    77,    77,    10,    38,    60,    62,    78,
//      80,    78,    81,    84,    87,    33,    36,    35,    92,    35,
//      17,    19,    22,    30,    31,    56,    67,    68,    69,    70,
//      71,    72,    74,    75,    76,    91,    93,    62,    78,    73,
//      92,    62,    77,    94,    77,    32,    77,    11,    67,    41,
//      10,    18,    20,    23,    73,    77,    67,    67,    21,    77,
//      67,    33,    11,    11,    77,    39,    11,    24,    39,    77,
//      67,    77,    18,    11,    18,    67,    67,    11,    11
//    };
//  }
//
///* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
//  private static final byte[] yyr1_ = yyr1_init();
//  private static final byte[] yyr1_init()
//  {
//    return new byte[]
//    {
//       0,    52,    53,    54,    54,    55,    55,    56,    56,    57,
//      57,    57,    58,    59,    59,    60,    60,    61,    62,    62,
//      62,    62,    63,    63,    63,    64,    65,    65,    66,    67,
//      67,    68,    68,    69,    69,    69,    69,    69,    69,    69,
//      70,    71,    72,    73,    73,    74,    75,    75,    76,    76,
//      77,    77,    77,    77,    78,    78,    78,    79,    79,    79,
//      80,    81,    81,    82,    82,    82,    82,    82,    82,    83,
//      84,    84,    85,    85,    85,    86,    87,    87,    88,    88,
//      89,    89,    90,    90,    90,    90,    90,    90,    91,    92,
//      92,    92,    93,    94
//    };
//  }
//
///* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
//  private static final byte[] yyr2_ = yyr2_init();
//  private static final byte[] yyr2_init()
//  {
//    return new byte[]
//    {
//       0,     2,     1,     0,     2,     1,     1,     1,     1,     4,
//       6,     4,     4,     8,    10,     1,     3,     3,     1,     1,
//       1,     1,     1,     1,     1,     3,     0,     2,     5,     0,
//       2,     1,     1,     1,     1,     1,     1,     1,     1,     1,
//       2,     3,     4,     1,     3,     5,     9,    10,     5,     7,
//       2,     3,     3,     4,     0,     3,     4,     1,     1,     1,
//       2,     0,     3,     1,     1,     1,     1,     1,     1,     2,
//       0,     3,     1,     1,     1,     2,     0,     3,     1,     1,
//       1,     3,     1,     1,     1,     1,     1,     1,     2,     0,
//       3,     4,     4,     1
//    };
//  }
//
//
//
//
//  /* YYTRANSLATE_(TOKEN-NUM) -- Symbol number corresponding to TOKEN-NUM
//     as returned by yylex, with out-of-bounds checking.  */
//  private static final SymbolKind yytranslate_(int t)
//  {
//    // Last valid token kind.
//    int code_max = 306;
//    if (t <= 0)
//      return SymbolKind.S_YYEOF;
//    else if (t <= code_max)
//      return SymbolKind.get(yytranslate_table_[t]);
//    else
//      return SymbolKind.S_YYUNDEF;
//  }
//  private static final byte[] yytranslate_table_ = yytranslate_table_init();
//  private static final byte[] yytranslate_table_init()
//  {
//    return new byte[]
//    {
//       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
//       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
//       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
//      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
//      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
//      35,    36,    37,    38,    39,    40,    41,    42,    43,    44,
//      45,    46,    47,    48,    49,    50,    51
//    };
//  }
//
//
//  private static final int YYLAST_ = 198;
//  private static final int YYEMPTY_ = -2;
//  private static final int YYFINAL_ = 15;
//  private static final int YYNTOKENS_ = 52;
//
///* Unqualified %code blocks.  */
///* "parser.y":16  */
//
//    private static GlobalDeclarations ast;
//    public static GlobalDeclarations makeAST(String i) {
//        ast = new lexems.GlobalDeclarations();
//        MyLexer l = new MyLexer();
//        Reader reader = new Reader();
//        reader.read("tests/" + i + ".txt");
//        l.tokenize(reader.sourceText);
//        YYParser p = new YYParser(l);
//        if (!p.parse()) {
//            System.exit(1);
//        }
//        return ast;
//    }
//
///* "YYParser.java":2079  */
//
}
