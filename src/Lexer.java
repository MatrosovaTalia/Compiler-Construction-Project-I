import javafx.util.Pair;

import java.io.*;
import java.nio.file.*;

/*
    Keywords:
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
    print(?)
    return(?)

    Symbolic Tokens:
    .. - range
    + - addition, can be unary to indicate the value is > 0
    - - subtraction, can be unary to mean negation
    * - multiplication
    / - division
    % - remainder from division
    [ -
    ] - addressing an array element
    < - less than
    <= - less or equal to
    > - greater than
    >= - greater or equal to
    = - equal to
    /= - not equal to
    . - addressing a record attribute
    , - separates parameters in routine calls/declarations
    := - assignment
    : - used to specify type in declarations
    ( -
    ) - used to enclose parameter list
    // - single-line comment
    /* - enclose multiline comments

    Complex Tokens:
    Identifier
    IntegralLiteral
    RealLiteral
 */


public class Lexer {

    public TokenSequence lex(String sourcePath) throws IOException {
        TokenSequence seq = new TokenSequence();
        StringBuffer buffer = new StringBuffer();
        BufferedReader in = Files.newBufferedReader(Path.of(sourcePath));

        int c, pos = 1, line = 1;
        Token tok = new Token("ERROR", TokenType.IDENTIFIER, -1, new Pair<>(0, 0));

        while ((c = in.read()) != -1) {
            seq.add(tok);
            pos += 1;
        }

        in.close();
        return seq;
    }
}