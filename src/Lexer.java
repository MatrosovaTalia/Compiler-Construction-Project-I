import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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


public class Lexer {
    private final StringReaderWithPosition in;
    private final CharacterBuffer buffer;
    private int c;
    private String sourceText;
    private Token enqueuedToken = null;


    public Lexer(String sourcePath) {
        try (BufferedReader in = new BufferedReader(new FileReader(sourcePath))) {
            int ch;
            StringBuilder sourceTextBuffer = new StringBuilder();
            while ((ch = in.read()) != -1) {
                sourceTextBuffer.append((char) ch);
            }
            this.sourceText = sourceTextBuffer.toString();
        } catch (FileNotFoundException e) {
            System.out.println("The file that was specified can not be found!");
        } catch (IOException e) {
            System.out.println("There was some error while reading from the file!");
        }
        this.in = new StringReaderWithPosition(sourceText);
        this.c = in.read();
        this.buffer = new CharacterBuffer();
    }


    /**
     * this method handles:
     * 1. identifiers
     * 2. keywords
     *
     * @return a new Token object of one of the listed types
     */
    private Token scanKeywordOrIdentifier() {
        Token tok;
        Pair<Integer, Integer> pos = new Pair<>(in.line(), in.pos());
        while ((Character.isLetterOrDigit(c) || c == '_') && c != -1) {

            buffer.add(c);
            c = in.read();
        }

        String st = buffer.toString();
        Integer code;
        buffer.flush();

        if ((code = Token.KEYWORD_TABLE.get(st)) != null) {
            tok = new Token(st, TokenType.KEYWORD, code, pos);
        } else {
            tok = new Token(st, TokenType.IDENTIFIER, 100, pos);
        }
        return tok;
    }

    /**
     * this method handles:
     * 1. real literals that start with a digit (e.g. 0.12132)
     * 2. integer literals (zeros at the beginning are allowed)
     * 3. real literals followed by '..' operator
     *
     * @return a new Token object of one of the listed types
     */
    private Token scanRealOrIntegerLiteral() {
        Token tok;
        Pair<Integer, Integer> pos = new Pair<>(in.line(), in.pos());
        while (Character.isDigit(c) && c != '.' && c != -1) {
            buffer.add(c);
            c = in.read();
        }

        if (c == '.') {
            int nextChar = in.read();
            if (nextChar == '.') { // which means '..' operator was encountered
                tok = new Token(buffer.toString(),
                        TokenType.INTEGER, 102, pos);
                enqueuedToken = new Token("..",
                        TokenType.SYMBOLIC, 4646, pos);
                buffer.flush();
                c = in.read(); // reading in the next unprocessed character
            } else if (Character.isDigit(nextChar)) { // which means a real literal was encountered
                buffer.add(c);
                while (Character.isDigit(nextChar)) {
                    buffer.add(nextChar);
                    nextChar = in.read();
                }
                tok = new Token(buffer.toString(),
                        TokenType.REAL, 101, pos);
                buffer.flush();
                c = nextChar;

            } else { // which means a real literal without digits after dot (like 1. <=> 1.0)

                buffer.add(c);
                tok = new Token(buffer.toString(),
                        TokenType.REAL, 101, pos);
                buffer.flush();
                c = nextChar;

            }
        } else {
            tok = new Token(buffer.toString(),
                    TokenType.INTEGER, 102, pos);
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
     * @return a new Token object of one of the listed types
     */
    private Token scanAmbiguousWithDot() {
        Token tok;
        Pair<Integer, Integer> pos = new Pair<>(in.line(), in.pos());
        buffer.add(c);
        int nextChar = in.read();
        // '..' operator
        if (nextChar == '.') {
            buffer.add(nextChar);
            tok = new Token(buffer.toString(),
                    TokenType.SYMBOLIC, c + nextChar * 100, pos);
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
            tok = new Token(buffer.toString(), TokenType.REAL, 101, pos);
            buffer.flush();
            c = nextChar;
        }
        // standalone '.'
        else {
            tok = new Token(buffer.toString(), TokenType.SYMBOLIC, c, pos);
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
     * @return a new Token object of one of the listed types
     */
    private Token scanAmbiguousWithEquals() {

        Token tok;
        Pair<Integer, Integer> pos = new Pair<>(in.line(), in.pos());
        buffer.add(c);
        int nextChar = in.read();

        // operators '>=', '<=' and ':='
        if (nextChar == '=') {
            // which means one of the following operators
            // :=, >=, <=
            buffer.add(nextChar);
            tok = new Token(buffer.toString(),
                    TokenType.SYMBOLIC, c + nextChar * 100, pos);

            buffer.flush();
            c = in.read();
        }
        // operators '>', '<', ':'
        else {
            tok = new Token(buffer.toString(),
                    TokenType.SYMBOLIC, c, pos);
            buffer.flush();
            c = nextChar;
        }
        return tok;
    }

    /**
     * this method handles:
     * 1. multiline comments /* ... ,
     * 2. single line comments // ... '\n'
     * 3. operator '/'
     * 4. operator '/='
     *
     * @return a new Token object of one of the listed types
     */
    private Token scanAmbiguousWithSlash() {
        Token tok;
        Pair<Integer, Integer> pos = new Pair<>(in.line(), in.pos());
        buffer.add(c);
        int nextChar = in.read();

        // operator '/='
        if (nextChar == '=') {
            buffer.add(nextChar);
            tok = new Token(buffer.toString(),
                    TokenType.SYMBOLIC, c + nextChar * 100, pos);
            buffer.flush();
            c = in.read();
        }

        // multiline comments
        else if (nextChar == '*') {
            buffer.add(nextChar);
            tok = new Token(buffer.toString(),
                    TokenType.SYMBOLIC, c + nextChar * 100, pos);
            buffer.flush();
            while (((c = in.read()) != '*' ||
                    (nextChar = in.read()) != '/') &&
                    nextChar != -1 && c != -1) {
                // do nothing (for now)
                // we can potentially do something
                // with comment text in this loop
            }
            if (c == '*' && nextChar == '/') {
                buffer.add(c);
                buffer.add(nextChar);
                enqueuedToken = new Token(buffer.toString(),
                        TokenType.SYMBOLIC, c + nextChar * 100, pos);
            }
            buffer.flush();
            c = in.read();
        }

        // single line comments
        else if (nextChar == '/') {
            buffer.add(nextChar);
            tok = new Token(buffer.toString(),
                    TokenType.SYMBOLIC, c + nextChar * 100, pos);
            buffer.flush();
            while ((c = in.read()) != '\n' && c != -1) {
                // do nothing (for now)
                // we can potentially do something
                // with comment text in this loop
            }
            // when the '\n' or eof is reached, proceed further
            if (c == '\n') {
                enqueuedToken = new Token("\n", TokenType.SEPARATOR, c * 10, pos);
            }
            c = in.read();
        }
        // operator '/'
        else {
            tok = new Token(buffer.toString(), TokenType.SYMBOLIC, c, pos);
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
     * @return a new Token object of one of the listed types
     */
    private Token scanSingleCharacterToken() {
        TokenType type = TokenType.SYMBOLIC;
        Pair<Integer, Integer> pos = new Pair<>(in.line(), in.pos());
        if (c == ';') type = TokenType.SEPARATOR;
        buffer.add(c);
        Token tok = new Token(buffer.toString(), type, c, pos);
        buffer.flush();
        c = in.read();
        return tok;
    }

    private Token scanNewlineSeparator() {
        Pair<Integer, Integer> pos = new Pair<>(in.line(), in.pos());
        Token tok = new Token("\n", TokenType.SEPARATOR, c * 10, pos);
        c = in.read();
        return tok;
    }

    public TokenSequence lex() {
        TokenSequence seq = new TokenSequence();
        Pair<Integer, Integer> pos = new Pair<>(in.line(), in.pos());
        while (c != -1 || enqueuedToken != null) {
            if (enqueuedToken != null) {
                seq.add(enqueuedToken);
                enqueuedToken = null;
            }
            if (Character.isLetter(c) || c == '_') {
                seq.add(scanKeywordOrIdentifier());
            } else if (Character.isDigit(c)) {
                seq.add(scanRealOrIntegerLiteral());
            } else if (c == '.') {
                seq.add(scanAmbiguousWithDot());
            } else if (c == '<' || c == '>' || c == ':') {
                seq.add(scanAmbiguousWithEquals());
            } else if (c == '/') {
                seq.add(scanAmbiguousWithSlash());
            } else if (c == '(' || c == ')' || c == '[' || c == ']' ||
                    c == '+' || c == '-' || c == '*' || c == '%' ||
                    c == '=' || c == ',' || c == ';') {
                seq.add(scanSingleCharacterToken());
            } else if (c == '\n') {
                seq.add(scanNewlineSeparator());
            } else if (Character.isWhitespace(c)) {
                // skip it
                c = in.read();
            } else if (c != -1) {
                throw new IllegalCharacterException("The source code of your program" +
                        " contains an illegal character " + "in line " + in.line() +
                        ", position " + in.pos() +
                        ": '" + (char) c + "'!");

            }
        }
        return seq;
    }
}