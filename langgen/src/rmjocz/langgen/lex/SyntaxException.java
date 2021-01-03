/**
 * author: Radoslaw Marek Jocz 
 * email: rmjocz@yahoo.com ; email: rm.jocz@hotmail.com
 * this code is relased on GNU General Public License v2.0 
 * https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */
package rmjocz.langgen.lex;

public class SyntaxException extends Exception {
  Lexeme lexeme = null;
  int[] lexst = null;
  String msg = null;
  
  public SyntaxException(String msg) {
    this.msg = msg;
  }

  public SyntaxException(Lexeme lexeme, int[] lexst) {
    this.lexeme = lexeme;
    this.lexst = lexst;
  }

  public String getMessage() {
    if (msg!=null) {
      return msg;
    }
    String lexsrs = Lexeme.getTypeName(lexst[0]);
    for(int i=1; i<lexst.length; i++) {
      lexsrs+= ", " + Lexeme.getTypeName(lexst[i]);
    }
    return "Syntax exception, unexpected lexeme: " +
      lexeme.toString() + " , expected one of: " + lexsrs;
  }

  public void printStackTrace() {
    System.err.println(getMessage());
  }

}
