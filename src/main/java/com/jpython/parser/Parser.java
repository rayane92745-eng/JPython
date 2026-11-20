package com.jpython.parser;

import com.jpython.ast.Expr;
import com.jpython.lexer.Token;
import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int current = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Expr parse() {
        return assignment();
    }

    private Expr assignment() {
        Expr expr = expression();
        if (match(Token.Type.EQUAL)) {
            Token equals = previous();
            Expr value = assignment();
            if (expr instanceof Expr.Variable var) {
                return new Expr.Assign(var.name(), value);
            }
            throw new RuntimeException("Invalid assignment");
        }
        return expr;
    }

    private Expr expression() {
        return term();
    }

    private Expr term() {
        Expr expr = factor();
        while (match(Token.Type.PLUS, Token.Type.MINUS)) {
            Token operator = previous();
            Expr right = factor();
            expr = new Expr.Binary(expr, operator, right);
        }
        return expr;
    }

    private Expr factor() {
        Expr expr = primary();
        while (match(Token.Type.MULTIPLY, Token.Type.DIVIDE)) {
            Token operator = previous();
            Expr right = primary();
            expr = new Expr.Binary(expr, operator, right);
        }
        return expr;
    }

    private Expr primary() {
        if (match(Token.Type.NUMBER)) {
            return new Expr.Literal(Double.parseDouble(previous().value()));
        }
        if (match(Token.Type.IDENTIFIER)) {
            return new Expr.Variable(previous());
        }
        if (match(Token.Type.LPAREN)) {
            Expr expr = expression();
            consume(Token.Type.RPAREN, "Expect ')' after expression.");
            return new Expr.Grouping(expr);
        }
        throw new RuntimeException("Expect expression.");
    }

    private boolean match(Token.Type... types) {
        for (Token.Type type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private Token consume(Token.Type type, String message) {
        if (check(type)) return advance();
        throw new RuntimeException(message);
    }

    private boolean check(Token.Type type) {
        if (isAtEnd()) return false;
        return tokens.get(current).type == type;
    }

    private Token advance() {
        if (!isAtEnd()) current++;
        return previous();
    }

    private boolean isAtEnd() {
        return tokens.get(current).type == Token.Type.EOF;
    }

    private Token previous() {
        return tokens.get(current - 1);
    }
}