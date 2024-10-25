/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generated;

public class LexemeException extends Exception {
  Lexeme lexeme;
  String info;

  public LexemeException(Lexeme lexeme) {
    this.lexeme = lexeme;
    info = "";
  }

  public LexemeException(Lexeme lexeme, String info) {
    this.lexeme = lexeme;
    this.info = info;
  }

  @Override
  public String getMessage() {
    return "Lexeme exception: " + info + " " + lexeme.toString();
  }

  @Override
  public void printStackTrace() {
    System.err.println(getMessage());
  }
}
