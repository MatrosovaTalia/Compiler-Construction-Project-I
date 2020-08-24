import java.io.*;
import java.nio.file.*;

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

    public TokenSequence lex(String sourcePath) throws IOException {
        TokenSequence seq = new TokenSequence();
        StringBuilder buffer = new StringBuilder();
        BufferedReaderWithPosition in = new BufferedReaderWithPosition(new FileReader(sourcePath));

        int c = in.read();

        while (c != -1) {
            // Every 'if else' clause is responsible for processing one or more lexemes.
            // It is important that after processing within each clause:
            // 1. BUFFER STAYS EMPTY
            // 2. c HAS A VALUE OF THE NEXT UNPROCESSED (!!!!!) CHARACTER

            // this clause handles:
            // 1. identifiers
            // 2. keywords
            if (Character.isLetter(c) || c == '_') {

                while ((Character.isLetterOrDigit(c) || c == '_') && c != -1) {
                    buffer.append((char) c);
                    c = in.read();
                }

                String st = buffer.toString();
                Integer code;

                if ((code = Token.KEYWORD_TABLE.get(st)) != null) {
                    seq.add(new Token(st, TokenType.KEYWORD, code, new Pair<>(in.line(), in.pos())));
                } else {
                    seq.add(new Token(st, TokenType.IDENTIFIER, 100, new Pair<>(in.line(), in.pos())));
                }
                buffer.delete(0, buffer.length());
            }


            // this clause handles:
            // 1. real literals that start with a digit (e.g. 0.12132)
            // 2. integer literals (zeros at the beginning are allowed)
            // 3. real literals followed by '..' operator
            else if (Character.isDigit(c)) {

                while (Character.isDigit(c) && c != '.' && c != -1) {
                    buffer.append((char) c);
                    c = in.read();
                }

                if (c == '.') {
                    int nextChar = in.read();
                    if (nextChar == '.') { // which means '..' operator was encountered

                        seq.add(new Token(buffer.toString(),
                                TokenType.INTEGER, 102, new Pair<>(in.line(), in.pos())));
                        buffer.delete(0, buffer.length());
                        seq.add(new Token("..",
                                TokenType.SYMBOLIC, 4646, new Pair<>(in.line(), in.pos())));
                        c = in.read(); // reading in the next unprocessed character

                    } else if (Character.isDigit(nextChar)) { // which means a real literal was encountered

                        buffer.append((char) c);
                        while (Character.isDigit(nextChar)) {
                            buffer.append((char) nextChar);
                            nextChar = in.read();
                        }
                        seq.add(new Token(buffer.toString(),
                                TokenType.REAL, 101, new Pair<>(in.line(), in.pos())));
                        buffer.delete(0, buffer.length());
                        c = nextChar;

                    } else { // which means a real literal without digits after dot (like 1. <=> 1.0)

                        buffer.append((char) c);
                        seq.add(new Token(buffer.toString(),
                                TokenType.REAL, 101, new Pair<>(in.line(), in.pos())));
                        buffer.delete(0, buffer.length());
                        c = nextChar;

                    }
                } else {
                    seq.add(new Token(buffer.toString(),
                            TokenType.INTEGER, 102, new Pair<>(in.line(), in.pos())));
                    buffer.delete(0, buffer.length());
                }
            }

            // this clause handles:
            // 1. real literals starting with '.' (e.g. '.12231')
            // 2. '..' operator
            // 3. '.' operator
            else if (c == '.') {

                buffer.append((char) c);
                int nextChar = in.read();
                // '..' operator
                if (nextChar == '.') {
                    buffer.append((char) nextChar);
                    seq.add(new Token(buffer.toString(),
                            TokenType.SYMBOLIC, c + nextChar * 100, new Pair<>(in.line(), in.pos())));
                    buffer.delete(0, buffer.length());
                    c = in.read(); // in this case nextChar is already processed,
                    // therefore c gets the value of the next character
                }
                // real literals starting with '.' (e.g. '.12231')
                else if (Character.isDigit(nextChar)) { // .001234125 literals
                    while (Character.isDigit(nextChar) && nextChar != -1) {
                        buffer.append((char) nextChar);
                        nextChar = in.read();
                    }
                    seq.add(new Token(buffer.toString(), TokenType.REAL, 101, new Pair<>(in.line(), in.pos())));
                    buffer.delete(0, buffer.length());
                    c = nextChar;
                }
                // standalone '.'
                else {
                    seq.add(new Token(buffer.toString(), TokenType.SYMBOLIC, c, new Pair<>(in.line(), in.pos())));
                    buffer.delete(0, buffer.length());
                    c = nextChar;
                }
            }

            // this clause handles operators:
            // 1. '<'
            // 2. '>'
            // 3. ':'
            // 4. '>='
            // 5. '<='
            // 6. ':='
            else if (c == '<' || c == '>' || c == ':') {

                buffer.append((char) c);
                int nextChar = in.read();

                // operators '>=', '<=' and ':='
                if (nextChar == '=') {
                    // which means one of the following operators
                    // :=, >=, <=
                    buffer.append((char) nextChar);
                    seq.add(new Token(buffer.toString(),
                            TokenType.SYMBOLIC, c + nextChar * 100, new Pair<>(in.line(), in.pos())));

                    buffer.delete(0, buffer.length());
                    c = in.read();
                }
                // operators '>', '<', ':'
                else {
                    seq.add(new Token(buffer.toString(),
                            TokenType.SYMBOLIC, c, new Pair<>(in.line(), in.pos())));
                    buffer.delete(0, buffer.length());
                    c = nextChar;
                }
            }

            // this clause handles:
            // 1. multiline comments /* ... */,
            // 2. single line comments // ... '\n'
            // 3. operator '/'
            // 4. operator '/='
            else if (c == '/') {
                buffer.append((char) c);
                int nextChar = in.read();
                // operator '/='
                if (nextChar == '=') {
                    buffer.append((char) nextChar);
                    seq.add(new Token(buffer.toString(),
                            TokenType.SYMBOLIC, c + nextChar * 100, new Pair<>(in.line(), in.pos())));
                    buffer.delete(0, buffer.length());
                    c = in.read();
                }
                // multiline comments
                else if (nextChar == '*') {
                    buffer.append((char) nextChar);
                    seq.add(new Token(buffer.toString(),
                            TokenType.SYMBOLIC, c + nextChar * 100, new Pair<>(in.line(), in.pos())));
                    buffer.delete(0, buffer.length());
                    while (((c = in.read()) != '*' ||
                            (nextChar = in.read()) != '/') &&
                            nextChar != -1 && c != -1) {
                        // do nothing (for now)
                        // we can potentially do something
                        // with comment text in this loop
                    }
                    if (c == '*' && nextChar == '/') {
                        buffer.append((char) c);
                        buffer.append((char) nextChar);
                        seq.add(new Token(buffer.toString(),
                                TokenType.SYMBOLIC, c + nextChar * 100, new Pair<>(in.line(), in.pos())));
                    }
                    buffer.delete(0, buffer.length());
                    c = in.read();
                }
                // single line comments
                else if (nextChar == '/') {
                    buffer.append((char) nextChar);
                    seq.add(new Token(buffer.toString(),
                            TokenType.SYMBOLIC, c + nextChar * 100, new Pair<>(in.line(), in.pos())));
                    buffer.delete(0, buffer.length());
                    while ((c = in.read()) != '\n' && c != -1) {
                        // do nothing (for now)
                        // we can potentially do something
                        // with comment text in this loop
                    }
                    // when the '\n' or eof is reached, proceed further
                    c = in.read();
                }
                // operator '/'
                else {
                    seq.add(new Token(buffer.toString(), TokenType.SYMBOLIC, c, new Pair<>(in.line(), in.pos())));
                    buffer.delete(0, buffer.length());
                    c = nextChar;
                }

            }

            // this clause handles:
            // single-character tokens, such as:
            // '(', ')', '[', ']', '+', '-', '*', '%', '=', ','
            else if (c == '(' || c == ')' || c == '[' || c == ']' ||
                    c == '+' || c == '-' || c == '*' || c == '%' ||
                    c == '=' || c == ',' || c == ';') {
                TokenType type = TokenType.SYMBOLIC;
                if (c == ';') type = TokenType.SEPARATOR;
                buffer.append((char) c);
                seq.add(new Token(buffer.toString(), type, c, new Pair<>(in.line(), in.pos())));
                buffer.delete(0, buffer.length());
                c = in.read();
            }

            // this clause handles:
            // other characters
            else {
                if (c == '\n') {
                    seq.add(new Token("\n", TokenType.SEPARATOR, c * 10, new Pair<>(in.line(), in.pos())));
                }
                c = in.read();
            }
        }

        in.close();
        return seq;
    }
}