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
        NEWLINE(287),
        SEMICOLON(288),
        LPAREN(289),
        RPAREN(290),
        LBRACKET(291),
        RBRACKET(292),
        COMMA(293),
        DOT(294),
        COLON(295),
        RANGE(296),
        EQUALS(297),
        ASSIGN(298),
        NEQUALS(299),
        GREATER(300),
        LESS(301),
        LEQUALS(302),
        GEQUALS(303),
        PLUS(304),
        MINUS(305),
        MULTIPLY(306),
        DIVIDE(307),
        REMAINDER(308);

    int value;

    TokenType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
