package lexer;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import simple.*;
import misc.Pair;
import parser.YYParser;
import parser.YYParser.*;
import java.math.BigDecimal;
import java.math.BigInteger;

/*
    DONE: Keywords:
    var
    type
    is
    end
    integer
    real
    boolean
    true
    false
    record
    array
    while
    loop
    for
    in
    reverse
    if
    then
    else
    routine
    and
    or
    not
    xor
    print
    return
    Symbolic Tokens:
    DONE: .. - range
    DONE: + - addition, can be unary to indicate the value is > 0
    DONE: - - subtraction, can be unary to mean negation
    DONE: * - multiplication
    DONE: / - division
    DONE: % - remainder from division
    DONE: [ -
    DONE: ] - addressing an array element
    DONE: < - less than
    DONE: <= - less or equal to
    DONE: > - greater than
    DONE: >= - greater or equal to
    DONE: = - equal to
    DONE: /= - not equal to
    DONE: . - addressing a record attribute
    DONE: , - separates parameters in routine calls/declarations
    DONE: := - assignment
    DONE: : - used to specify type in declarations
    DONE: ( -
    DONE: ) - used to enclose parameter list
    DONE: // - single-line comment
    DONE: /* - enclose multiline comments
    DONE: ; - statement separator
    Complex Tokens:
    DONE: Identifier
    DONE: IntegralLiteral
    DONE: RealLiteral
 */


public class MyLexer implements YYParser.Lexer {
    private StringReaderWithPosition in;
    private CharacterBuffer buffer;
    private int c;
    private Token enqueuedToken = null;
    private Token currentToken = null;


    public MyLexer () {}

    public void tokenize(String sourceText) {
        this.in = new StringReaderWithPosition(sourceText);
        this.c = in.read();
        this.buffer = new CharacterBuffer();
    }

    public Token lex() {
        while (c != -1 || enqueuedToken != null) {
            if (enqueuedToken != null) {
                currentToken = enqueuedToken;
                enqueuedToken = null;
                return currentToken;
            }
            if (Character.isLetter(c) || c == '_') {
                return currentToken = scanKeywordOrIdentifier();
            } else if (Character.isDigit(c)) {
                return currentToken = scanRealOrIntegerLiteral();
            } else if (c == '.') {
                return currentToken = scanAmbiguousWithDot();
            } else if (c == '<' || c == '>' || c == ':') {
                return currentToken = scanAmbiguousWithEquals();
            } else if (c == '/') {
                return currentToken = scanAmbiguousWithSlash();
            } else if (c == '(' || c == ')' || c == '[' || c == ']' ||
                    c == '+' || c == '-' || c == '*' || c == '%' ||
                    c == '=' || c == ',') {
                return currentToken = scanSingleCharacterToken();
//            } else if (c == '\n') {
//                return scanNewlineSeparator();
            } else if (Character.isWhitespace(c) || c == ';') {
                // skip it
                c = in.read();
            } else if (c != -1) {
                return currentToken = scanIllegalCharacter();
            }
        }
        Position pos = new Position(in.line(), in.pos());
        return currentToken = new Token("", TokenType.YYEOF, pos);
    }


    /**
     * this method handles:
     * 1. identifiers
     * 2. keywords
     *
     * @return a new lexer.Token object of one of the listed types
     */
    private Token scanKeywordOrIdentifier() {
        Token tok;
        Position pos = new Position(in.line(), in.pos());
        while ((Character.isLetterOrDigit(c) || c == '_') && c != -1) {
            buffer.add(c);
            c = in.read();
        }

        String st = buffer.toString();
        TokenType type;
        buffer.flush();

        if ((type = Token.KEYWORD_TABLE.get(st)) != null) {
            tok = new Token(st, type, pos);
        } else {
            tok = new Token(st, TokenType.IDENTIFIER, pos);
        }
        return tok;
    }

    /**
     * this method handles:
     * 1. real literals that start with a digit (e.g. 0.12132)
     * 2. integer literals (zeros at the beginning are allowed)
     * 3. real literals followed by '..' operator
     *
     * @return a new lexer.Token object of one of the listed types
     */
    private Token scanRealOrIntegerLiteral() {
        Token tok;
        Position pos = new Position(in.line(), in.pos());
        while (Character.isDigit(c) && c != '.' && c != -1) {
            buffer.add(c);
            c = in.read();
        }

        if (c == '.') {
            int nextChar = in.read();
            if (nextChar == '.') { // which means '..' operator was encountered
                tok = new Token(buffer.toString(),
                        TokenType.INTEGER, pos);
                enqueuedToken = new Token("..", TokenType.RANGE, pos);
                buffer.flush();
                c = in.read(); // reading in the next unprocessed character
            } else if (Character.isDigit(nextChar)) { // which means a real literal was encountered
                buffer.add(c);
                while (Character.isDigit(nextChar)) {
                    buffer.add(nextChar);
                    nextChar = in.read();
                }
                tok = new Token(buffer.toString(), TokenType.REAL_LITERAL, pos);
                buffer.flush();
                c = nextChar;

            } else { // which means a real literal without digits after dot (like 1. <=> 1.0)

                buffer.add(c);
                tok = new Token(buffer.toString(), TokenType.REAL_LITERAL, pos);
                buffer.flush();
                c = nextChar;

            }
        } else {
            tok = new Token(buffer.toString(), TokenType.INTEGER_LITERAL, pos);
            buffer.flush();
        }
        return tok;
    }

    /**
     * this method handles:
     * 1. real literals starting with '.' (e.g. '.12231')
     * 2. '..' operator
     * 3. '.' operator
     *
     * @return a new lexer.Token object of one of the listed types
     */
    private Token scanAmbiguousWithDot() {
        Token tok;
        Position pos = new Position(in.line(), in.pos());
        buffer.add(c);
        int nextChar = in.read();

        // '..' operator
        if (nextChar == '.') {
            buffer.add(nextChar);
            tok = new Token(buffer.toString(), TokenType.RANGE, pos);
            buffer.flush();
            c = in.read(); // in this case nextChar is already processed,
            // therefore c gets the value of the next character
        }

        // real literals starting with '.' (e.g. '.12231')
        else if (Character.isDigit(nextChar)) { // .001234125 literals
            while (Character.isDigit(nextChar) && nextChar != -1) {
                buffer.add(nextChar);
                nextChar = in.read();
            }
            tok = new Token(buffer.toString(), TokenType.REAL_LITERAL, pos);
            buffer.flush();
            c = nextChar;
        }
        // standalone '.'
        else {
            tok = new Token(buffer.toString(), TokenType.DOT, pos);
            buffer.flush();
            c = nextChar;
        }
        return tok;
    }

    /**
     * this method handles operators:
     * 1. '<'
     * 2. '>'
     * 3. ':'
     * 4. '>='
     * 5. '<='
     * 6. ':='
     *
     * @return a new lexer.Token object of one of the listed types
     */
    private Token scanAmbiguousWithEquals() {
        Position pos = new Position(in.line(), in.pos());
        buffer.add(c);
        int nextChar = in.read();
        TokenType type;

        // operators '>=', '<=' and ':='
        if (nextChar == '=') {
            buffer.add(nextChar);
            switch (c) {
                case '>' -> type = TokenType.GEQUALS;
                case '<' -> type = TokenType.LEQUALS;
                case ':' -> type = TokenType.ASSIGN;
                default -> type = null;
            }
            c = in.read();
        }
        // operators '>', '<', ':'
        else {
            switch (c) {
                case '>' -> type = TokenType.GREATER;
                case '<' -> type = TokenType.LESS;
                case ':' -> type = TokenType.COLON;
                default -> type = null;
            }
            c = nextChar;
        }
        Token tok = new Token(buffer.toString(), type, pos);
        buffer.flush();
        return tok;
    }

    /**
     * this method handles:
     * 1. multiline comments /* ... ,
     * 2. single line comments // ... '\n'
     * 3. operator '/'
     * 4. operator '/='
     *
     * @return a new lexer.Token object of one of the listed types
     */
    private Token scanAmbiguousWithSlash() {
        Token tok;
        Position pos = new Position(in.line(), in.pos());
        buffer.add(c);
        int nextChar = in.read();

        // operator '/='
        if (nextChar == '=') {
            buffer.add(nextChar);
            tok = new Token(buffer.toString(), TokenType.NEQUALS, pos);
            buffer.flush();
            c = in.read();
        }

        // multiline comments
        else if (nextChar == '*') {
            while (((c = in.read()) != '*' || (nextChar = in.read()) != '/') &&
                    nextChar != -1 && c != -1) {
                // do nothing (for now)
                // we can potentially do something
                // with comment text in this loop
            }
            buffer.flush();
            c = in.read();
            return lex(); // scanning next token
        }

        // single line comments
        else if (nextChar == '/') {
            while ((c = in.read()) != '\n' && c != -1) {
                // do nothing (for now)
                // we can potentially do something
                // with comment text in this loop
            }
            // when the '\n' or eof is reached, proceed further
            buffer.flush();
            c = in.read();
            return lex(); // scanning next token
        }
        // operator '/'
        else {
            tok = new Token(buffer.toString(), TokenType.DIVIDE, pos);
            buffer.flush();
            c = nextChar;
        }
        return tok;
    }

    /**
     * this clause handles:
     * single-character tokens, such as:
     * '(', ')', '[', ']', '+', '-', '*', '%', '=', ','
     *
     * @return a new lexer.Token object of one of the listed types
     */
    private Token scanSingleCharacterToken() {
        TokenType type;
        Position pos = new Position(in.line(), in.pos());
        switch (c) {
            case '(' -> type = TokenType.LPAREN;
            case ')' -> type = TokenType.RPAREN;
            case '[' -> type = TokenType.LBRACKET;
            case ']' -> type = TokenType.RBRACKET;
            case '+' -> type = TokenType.PLUS;
            case '-' -> type = TokenType.MINUS;
            case '*' -> type = TokenType.MULTIPLY;
            case '%' -> type = TokenType.REMAINDER;
            case '=' -> type = TokenType.EQUALS;
            case ',' -> type = TokenType.COMMA;
//            case ';' -> type = TokenType.SEMICOLON;
            default -> type = null;
        }

        buffer.add(c);
        Token tok = new Token(buffer.toString(), type, pos);
        buffer.flush();
        c = in.read();
        return tok;
    }

//    /**
//     * this method handles:
//     * 1. newline separator
//     *
//     * @return a new lexer.Token object of one of the listed types
//     */
//    private Token scanNewlineSeparator() {
//        Position pos = new Position(in.line(), in.pos());
//        c = in.read();
//        return new Token("\n", TokenType.NEWLINE, pos);
//    }

    /**
     * this method handles:
     * 1. Illegal Characters
     *
     * @return a new lexer.Token object of one of the listed types
     */
    private Token scanIllegalCharacter() {
        buffer.add(c);
        Position pos = new Position(in.line(), in.pos());
        Token tok = new Token(buffer.toString(), TokenType.YYUNDEF, pos);
        buffer.flush();
        c = in.read();
        return tok;
    }


    //    @Override
    public Position getStartPos() {
        return currentToken.getPosition();
    }

    //    @Override
    public Position getEndPos() {
        var position = currentToken.getPosition();
        var line = position.getLine();
        var pos = position.getPos() + currentToken.getBody().length();
        return new Position(line, pos);
    }

    @Override
    public ILexem getLVal() {
        TokenType type = currentToken.getType();
        String body = currentToken.getBody();
        ILexem lval;
        switch (type) {
            case IDENTIFIER -> lval = new Identifier(body);
            case REAL_LITERAL -> lval = new RealLiteral(
                    new BigDecimal(body)
            );
            case INTEGER_LITERAL -> lval = new IntegerLiteral(
                    new BigInteger(body)
            );
            case TRUE -> lval = new BooleanLiteral(true);
            case FALSE -> lval = new BooleanLiteral(false);
            default -> lval = new ILexem() {
                @Override
                public void emit(ClassWriter cw, MethodVisitor mv) {

                }

                @Override
                public int hashCode() {
                    return super.hashCode();
                }

                @Override
                public boolean equals(Object obj) {
                    return super.equals(obj);
                }

                @Override
                protected Object clone() throws CloneNotSupportedException {
                    return super.clone();
                }

                @Override
                public String toString() {
                    return super.toString();
                }

                @Override
                protected void finalize() throws Throwable {
                    super.finalize();
                }


            };
        }
        return lval;
    }

    @Override
    public int yylex() {
        currentToken = lex();
        return currentToken.getType().getValue();
    }



    @Override
    public void yyerror(Location loc, String msg) {
        System.out.println("An error occurred at position " +
                currentToken.getPosition() + ":\n" + msg);
    }



    public void reportSyntaxError(Context ctx) {
        yyerror(ctx.getLocation(), ctx.getToken().getName() + ": syntax error");
//        yyerror(ctx.getToken());
//        final int TOKENMAX = 308;

//        YYParser.SymbolKind[] arg = new YYParser.SymbolKind[TOKENMAX];
//        int n = ctx.getExpectedTokens(arg, TOKENMAX);
//        for (int i = 0; i < n; ++i) {
//            System.err.print((i == 0 ? ": expected " : " or ") + arg[i].getName());
//        }
//        YYParser.SymbolKind lookahead = ctx.getToken();
//        if (lookahead != null && lookahead != YYParser.SymbolKind.S_YYUNDEF) {
//            System.err.println(" before " + lookahead.getName());
//        } else {
//            System.err.println(", got invalid token " + getValue() + " instead");
//        }
    }


}

