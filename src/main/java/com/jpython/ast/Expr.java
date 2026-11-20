package com.jpython.ast;

import com.jpython.lexer.Token;

public interface Expr {
    <R> R accept(Visitor<R> visitor);

    interface Visitor<R> {
        R visitBinaryExpr(Binary expr);
        R visitGroupingExpr(Grouping expr);
        R visitLiteralExpr(Literal expr);
        R visitVariableExpr(Variable expr);
        R visitAssignExpr(Assign expr);
    }

    record Binary(Expr left, Token operator, Expr right) implements Expr {
        @Override public <R> R accept(Visitor<R> visitor) { return visitor.visitBinaryExpr(this); }
    }

    record Grouping(Expr expression) implements Expr {
        @Override public <R> R accept(Visitor<R> visitor) { return visitor.visitGroupingExpr(this); }
    }

    record Literal(Object value) implements Expr {
        @Override public <R> R accept(Visitor<R> visitor) { return visitor.visitLiteralExpr(this); }
    }

    record Variable(Token name) implements Expr {
        @Override public <R> R accept(Visitor<R> visitor) { return visitor.visitVariableExpr(this); }
    }

    record Assign(Token name, Expr value) implements Expr {
        @Override public <R> R accept(Visitor<R> visitor) { return visitor.visitAssignExpr(this); }
    }
}