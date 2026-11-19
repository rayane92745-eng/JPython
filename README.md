# JPython

JPython is a Python-like interpreter implemented from scratch using Java. 

## Project Structure
- `lexer`: Handles lexical analysis (converting source code into tokens).
- `parser`: (Upcoming) Handles syntax analysis and builds the Abstract Syntax Tree (AST).
- `interpreter`: (Upcoming) Executes the AST nodes.

## Current Progress
- [x] Basic Lexer implementation
- [x] Tokenization of arithmetic expressions
- [ ] Parser implementation
- [ ] Interpreter engine

## How to Run
1. Clone the repository.
2. Use Maven to build: `mvn clean install`.
3. Run the `Main` class.