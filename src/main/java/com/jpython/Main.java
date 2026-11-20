package com.jpython;

import com.jpython.lexer.Lexer;
import com.jpython.lexer.Token;
import com.jpython.parser.Parser;
import com.jpython.ast.Expr;
import com.jpython.interpreter.Interpreter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // كود تجريبي: تعريف متغير x ثم القيام بعملية حسابية عليه
        String[] code = {
            "x = 10 + 5",
            "x * 2"
        };

        Interpreter interpreter = new Interpreter();

        for (String line : code) {
            System.out.println("Executing: " + line);
            try {
                Lexer lexer = new Lexer(line);
                List<Token> tokens = lexer.tokenize();
                
                Parser parser = new Parser(tokens);
                Expr expression = parser.parse();
                
                Object result = interpreter.interpret(expression);
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
            System.out.println("------------------");
        }
    }
}