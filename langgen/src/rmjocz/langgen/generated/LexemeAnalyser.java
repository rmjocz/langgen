/**
 * author: Radoslaw Marek Jocz 
 * email: rmjocz@yahoo.com ; email: rm.jocz@hotmail.com
 * this code is relased on GNU General Public License v2.0 
 * https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */
package rmjocz.langgen.generated;

public class LexemeAnalyser {
  String text;
  int n;
  boolean eof;
  int b, e, r, c, f;
  int s;
  final static char EOFEC = '\uFFFF';
  final int maxLexLen;

  public LexemeAnalyser(String text, int maxLexLen) {
    this.text = text;
    n = text.length();
    eof = false;
    b = 0; e = 0; r = 1; c = 0; f = 0;
    s = 0;
    this.maxLexLen = maxLexLen;
  }

  public LexemeAnalyser(String text) {
    this(text, 1024);
  }

  private char getChar() {
    if (e<n) {
      return text.charAt(e);
    } else {
      eof = true;
      return EOFEC;
    }
  }

  private String getText() {
    return e<n ? text.substring(b, e) : text.substring(b, n);
  }

  private Lexeme getLexemeImpl(int type, int b, int e, int r, int c) throws LexemeException {
    if (e-b > maxLexLen) {
      throw new LexemeException(new Lexeme(type, b, e, r, c, getText()), "string is too long:" + (e-b) + " > " + maxLexLen);
    }
    return new Lexeme(type, b, e, r, c, getText());
  }

  public Lexeme getLexeme() throws LexemeException {
    b = e;
    while (!eof) {
      char ch = getChar();
      switch(s) {
        case 0:
        switch (ch) {
          case ' ': 
          e++; b = e;
          break;

          case '\n': 
          e++; c = b - f + 1; f = e;
          return getLexemeImpl(Lexeme.L_NL, b, e<n?e:n, r++, c);

          case '\r': 
          e++; s = 1;
          break;

          case '+': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_ADD, b, e<n?e:n, r, c);

          case '-': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_SUB, b, e<n?e:n, r, c);

          case '/': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_DIV, b, e<n?e:n, r, c);

          case '(': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_LBRA, b, e<n?e:n, r, c);

          case ')': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_RBRA, b, e<n?e:n, r, c);

          case ':': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_SEP, b, e<n?e:n, r, c);

          case 'L': 
          e++; s = 2;
          break;

          case 'C': 
          e++; s = 3;
          break;

          case 'D': 
          e++; s = 4;
          break;

          case 'x': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_X, b, e<n?e:n, r, c);

          case 'y': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_Z, b, e<n?e:n, r, c);

          case 'z': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_Z, b, e<n?e:n, r, c);

          case 'r': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_R, b, e<n?e:n, r, c);

          case '.': 
          e++; s = 5;
          break;

          case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': 
          e++; s = 6;
          break;

          case EOFEC:
          break;

          default:
          e++; c = b - f + 1;
          throw new LexemeException(new Lexeme(Lexeme.N_ERROR, b, e<n?e:n, r, c, getText()));
        }
        break;

        case 1:
        switch (ch) {
          case '\n': 
          e++; c = b - f + 1; f = e; s = 0;
          return getLexemeImpl(Lexeme.L_NL, b, e<n?e:n, r++, c);

          default:
          eof = false;
          c = b - f + 1; f = e; s = 0;
          return getLexemeImpl(Lexeme.L_NL, b, e<n?e:n, r++, c);
        }

        case 2:
        switch (ch) {
          case 'N': 
          e++; c = b - f + 1; s = 0;
          return getLexemeImpl(Lexeme.L_LN, b, e<n?e:n, r, c);

          default:
          e++; c = b - f + 1;
          throw new LexemeException(new Lexeme(Lexeme.N_ERROR, b, e<n?e:n, r, c, getText()));
        }

        case 3:
        switch (ch) {
          case 'R': 
          e++; c = b - f + 1; s = 0;
          return getLexemeImpl(Lexeme.L_CR, b, e<n?e:n, r, c);

          default:
          e++; c = b - f + 1;
          throw new LexemeException(new Lexeme(Lexeme.N_ERROR, b, e<n?e:n, r, c, getText()));
        }

        case 4:
        switch (ch) {
          case 'R': 
          e++; c = b - f + 1; s = 0;
          return getLexemeImpl(Lexeme.L_DR, b, e<n?e:n, r, c);

          case 'L': 
          e++; c = b - f + 1; s = 0;
          return getLexemeImpl(Lexeme.L_DL, b, e<n?e:n, r, c);

          default:
          e++; c = b - f + 1;
          throw new LexemeException(new Lexeme(Lexeme.N_ERROR, b, e<n?e:n, r, c, getText()));
        }

        case 5:
        switch (ch) {
          case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': 
          e++; s = 7;
          break;

          default:
          e++; c = b - f + 1;
          throw new LexemeException(new Lexeme(Lexeme.N_ERROR, b, e<n?e:n, r, c, getText()));
        }
        break;

        case 6:
        switch (ch) {
          case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': 
          e++;
          break;

          case '.': 
          e++; s = 7;
          break;

          default:
          eof = false;
          c = b - f + 1; s = 0;
          return getLexemeImpl(Lexeme.L_NR, b, e<n?e:n, r, c);
        }
        break;

        case 7:
        switch (ch) {
          case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': 
          e++;
          break;

          default:
          eof = false;
          c = b - f + 1; s = 0;
          return getLexemeImpl(Lexeme.L_NR, b, e<n?e:n, r, c);
        }
      }
    }
    return null;
  }
}
