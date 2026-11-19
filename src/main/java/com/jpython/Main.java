package com.jpython;

import com.jpython.lexer.Lexer;
import com.jpython.lexer.Token;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String code = "10 + 5 * (2 - 1)";
        System.out.println("Executing code: " + code);
        
        try {
            Lexer lexer = new Lexer(code);
            List<Token> tokens = lexer.tokenize();
            
            System.out.println("Tokens found:");
            for (Token token : tokens) {
                System.out.println(token);
            }
            
            System.out.println("\n[Status] Lexical analysis successful. Ready for Parsing stage.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}