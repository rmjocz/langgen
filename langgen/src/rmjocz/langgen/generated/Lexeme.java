/**
 * author: Radoslaw Marek Jocz 
 * email: rmjocz@yahoo.com ; email: rm.jocz@hotmail.com
 * this code is relased on GNU General Public License v2.0 
 * https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */
package rmjocz.langgen.generated;

public class Lexeme {
  public static final int N_ERROR = -1;
  public static final int N_EOF = 0;
  public static final int L_NL = 1;
  public static final int L_ADD = 2;
  public static final int L_SUB = 3;
  public static final int L_DIV = 4;
  public static final int L_LBRA = 5;
  public static final int L_RBRA = 6;
  public static final int L_SEP = 7;
  public static final int L_LN = 8;
  public static final int L_CR = 9;
  public static final int L_DR = 10;
  public static final int L_DL = 11;
  public static final int L_X = 12;
  public static final int L_Z = 13;
  public static final int L_R = 14;
  public static final int L_NR = 15;

  private static final int[] types = {
    N_ERROR,
    N_EOF,
    L_NL,
    L_ADD,
    L_SUB,
    L_DIV,
    L_LBRA,
    L_RBRA,
    L_SEP,
    L_LN,
    L_CR,
    L_DR,
    L_DL,
    L_X,
    L_Z,
    L_R,
    L_NR
  };

  private static final String[] names = {
    "N_ERROR",
    "N_EOF",
    "L_NL",
    "L_ADD",
    "L_SUB",
    "L_DIV",
    "L_LBRA",
    "L_RBRA",
    "L_SEP",
    "L_LN",
    "L_CR",
    "L_DR",
    "L_DL",
    "L_X",
    "L_Z",
    "L_R",
    "L_NR"
  };

  int type;
  int begin;
  int end;
  int row;
  int column;
  String content;

  public Lexeme(int type, int begin, int end, int row, int column, String content) {
    this.type = type;
    this.begin = begin;
    this.end = end;
    this.row = row;
    this.column = column;
    this.content = content;
  }

  public Lexeme(int type) {
    this(type, -1, -1, -1, -1, "");
  }

  public Lexeme(int type, int begin, int end, String content) {
    this(type, begin, end, -1, -1, content);
  }

  @Override
  public String toString() {
    return getTypeName(type) +
           ", type: " + type +
           " begin: " + begin +
           " end: " + end +
           " row: " + row +
           " column: " + column +
           " content: " + "'" + content + "'";
  }

  public static String getTypeName(int type) {
    for(int i=0; i<types.length; i++) {
      if (types[i] == type) {
        return names[i];
      }
    }
    return "";
  }
  public int getType() {
    return type;
  }

  public String getContent() {
    return content;
  }

}
