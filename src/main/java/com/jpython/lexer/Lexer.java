package com.jpython.lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String input;
    private int pos = 0;

    public Lexer(String input) {
        this.input = input;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (pos < input.length()) {
            char current = input.charAt(pos);

            if (Character.isWhitespace(current)) {
                pos++;
            } else if (Character.isDigit(current)) {
                StringBuilder sb = new StringBuilder();
                while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
                    sb.append(input.charAt(pos++));
                }
                tokens.add(new Token(Token.Type.NUMBER, sb.toString()));
            } else if (Character.isLetter(current)) {
                StringBuilder sb = new StringBuilder();
                while (pos < input.length() && Character.isLetterOrDigit(input.charAt(pos))) {
                    sb.append(input.charAt(pos++));
                }
                tokens.add(new Token(Token.Type.IDENTIFIER, sb.toString()));
            } else if (current == '=') {
                tokens.add(new Token(Token.Type.EQUAL, "="));
                pos++;
            } else if (current == '+') {
                tokens.add(new Token(Token.Type.PLUS, "+"));
                pos++;
            } else if (current == '-') {
                tokens.add(new Token(Token.Type.MINUS, "-"));
                pos++;
            } else if (current == '*') {
                tokens.add(new Token(Token.Type.MULTIPLY, "*"));
                pos++;
            } else if (current == '/') {
                tokens.add(new Token(Token.Type.DIVIDE, "/"));
                pos++;
            } else if (current == '(') {
                tokens.add(new Token(Token.Type.LPAREN, "("));
                pos++;
            } else if (current == ')') {
                tokens.add(new Token(Token.Type.RPAREN, ")"));
                pos++;
            } else {
                throw new RuntimeException("Unknown character: " + current);
            }
        }
        tokens.add(new Token(Token.Type.EOF, ""));
        return tokens;
    }
}