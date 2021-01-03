/**
 * author: Radoslaw Marek Jocz 
 * email: rmjocz@yahoo.com ; email: rm.jocz@hotmail.com
 * this code is relased on GNU General Public License v2.0 
 * https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */
package rmjocz.langgen.generated;

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
