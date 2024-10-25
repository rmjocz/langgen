package lex;

public class LexemeException extends Exception {
  Lexeme lexeme;

  public LexemeException(Lexeme lexeme) {
    this.lexeme = lexeme;
  }

  @Override
  public String getMessage() {
    return "Lexeme exception: " + lexeme.toString();
  }

  @Override
  public void printStackTrace() {
    System.err.println(getMessage());
  }

}
