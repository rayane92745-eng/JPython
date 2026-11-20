package com.jpython.interpreter;

import com.jpython.ast.Expr;
import com.jpython.environment.Environment;
import com.jpython.lexer.Token;

public class Interpreter implements Expr.Visitor<Object> {
    private final Environment environment = new Environment();

    public Object interpret(Expr expression) {
        return expression.accept(this);
    }

    @Override
    public Object visitBinaryExpr(Expr.Binary expr) {
        Object left = expr.left().accept(this);
        Object right = expr.right().accept(this);

        if (!(left instanceof Double l) || !(right instanceof Double r)) {
            throw new RuntimeException("Operands must be numbers.");
        }

        return switch (expr.operator().type()) {
            case PLUS -> l + r;
            case MINUS -> l - r;
            case MULTIPLY -> l * r;
            case DIVIDE -> l / r;
            default -> throw new RuntimeException("Unknown operator");
        };
    }

    @Override
    public Object visitGroupingExpr(Expr.Grouping expr) {
        return expr.expression().accept(this);
    }

    @Override
    public Object visitLiteralExpr(Expr.Literal expr) {
        return expr.value();
    }

    @Override
    public Object visitVariableExpr(Expr.Variable expr) {
        return environment.get(expr.name().value());
    }

    @Override
    public Object visitAssignExpr(Expr.Assign expr) {
        Object value = expr.value().accept(this);
        environment.define(expr.name().value(), value);
        return value;
    }
}