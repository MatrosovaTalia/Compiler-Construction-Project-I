package lexer;

public enum TokenType {
    // Keywords
//    VAR, TYPE, IS, END, INTEGER, REAL, BOOLEAN, TRUE, FALSE, RECORD,
//    ARRAY, WHILE, LOOP, FOR, IN, REVERSE, IF, THEN, ELSE, ROUTINE,
//    AND, OR, NOT, XOR, PRINT, RETURN,
//
//    // Operators
//    RANGE, ADD, MINUS, MULTIPLY, DIVIDE, REMAINDER,
//    RBRACKET, LBRACKET, LESS, LEQUALS, GREATER, GEQUALS,
//    EQUALS, NEQUALS, DOT, COMMA, ASSIGN, COLON, LPAREN, RPAREN,
//
//    //Literals
//    IDENTIFIER, INTEGER_LITERAL, REAL_LITERAL,
//
//    // Special Tokens
//    NEWLINE, SEMICOLON, EOF, ILLEGAL

    /* Token kinds.  */
    YYEOF(0),
    YYerror(256),
    YYUNDEF(257),
    IDENTIFIER(258),
    INTEGER_LITERAL(259),
    REAL_LITERAL(260),
    TRUE(261),
    FALSE(262),
    VAR(263),
    TYPE(264),
    IS(265),
    END(266),
    RECORD(267),
    INTEGER(268),
    REAL(269),
    BOOLEAN(270),
    ARRAY(271),
    WHILE(272),
    LOOP(273),
    FOR(274),
    IN(275),
    REVERSE(276),
    IF(277),
    THEN(278),
    ELSE(279),
    ROUTINE(280),
    AND(281),
    OR(282),
    NOT(283),
    XOR(284),
    PRINT(285),
    RETURN(286),
    LPAREN(287),
    RPAREN(288),
    LBRACKET(289),
    RBRACKET(290),
    COMMA(291),
    DOT(292),
    COLON(293),
    RANGE(294),
    EQUALS(295),
    ASSIGN(296),
    NEQUALS(297),
    GREATER(298),
    LESS(299),
    LEQUALS(300),
    GEQUALS(301),
    PLUS(302),
    MINUS(303),
    MULTIPLY(304),
    DIVIDE(305),
    REMAINDER(306);

    int value;

    TokenType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
