/**
 * author: Radoslaw Marek Jocz 
 * email: rmjocz@yahoo.com ; email: rm.jocz@hotmail.com
 * this code is relased on GNU General Public License v2.0 
 * https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */
package rmjocz.langgen.lex;

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
