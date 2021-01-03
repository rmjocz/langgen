/**
 * author: Radoslaw Marek Jocz 
 * email: rmjocz@yahoo.com ; email: rm.jocz@hotmail.com
 * this code is relased on GNU General Public License v2.0 
 * https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */
package rmjocz.langgen.lex;

public class LexemeAnalyser {
	String text;
	int n;
	boolean eof;
	int s;
	int b, e , r, c, f;
	final static char EOFEC = '\uFFFF';

	public LexemeAnalyser(String text) {
    this.text = text;
		n = text.length();
		eof = false;
		s = 0;
		b = 0; e = 0; r = 1; c = 0; f = 0;
	}

	private char getChar() {
		if (e<n) {
			return text.charAt(e);
		} else {
			eof = true;
			return EOFEC;
		}
	}

	private Lexeme getLexeme(int t) {
		int _e = e < n ? e : n;
    c = b-f+1;
		Lexeme l = new Lexeme(t, b, _e, r, c, text.substring(b, _e));
		b = e;
		return l;
	}

	public Lexeme getLexeme() throws LexemeException {
    char ch; Lexeme l;
		while (!eof) {
			ch = getChar();
			switch (s) {
				case 0:
				switch (ch) {
					case ' ':
					e++; b = e;
					break;

          case '@':
          e++;
          return getLexeme(Lexeme.TYPE_T_OPT_DF_NA);

          case '~':
          e++;
          return getLexeme(Lexeme.TYPE_T_OPT_EOL);

          case '-':
          e++;
          return getLexeme(Lexeme.TYPE_T_OPT_BF_RS);

          case '.':
          e++;
          return getLexeme(Lexeme.TYPE_T_OPT_EN_ST);

          case '\n':
          e++;
          l = getLexeme(Lexeme.TYPE_T_NL);
          r++; f = e;
          return l;

          case '\r':
          b = e; e++; s = 1;
          break;

          case '0': case '1': case '2': case '3':	case '4':  case '5':
          case '6': case '7': case '8': case '9':
					b = e; e++; s = 2;
          break;

          case 'A': case 'B': case 'C': case 'D': case 'E': case 'F':
					case 'G': case 'H': case 'I': case 'J': case 'K': case 'L':
					case 'M': case 'N': case 'O': case 'P': case 'Q': case 'R':
					case 'S': case 'T': case 'U': case 'V': case 'W': case 'X':
          case 'Y': case 'Z': case '_':
          b = e; e++; s = 3;
          break;

          case'"':
          e++; b = e; s = 4;
          break;

					case EOFEC:
					break;

          default:
					e++;
					throw new LexemeException(getLexeme(Lexeme.TYPE_N_UNDEF));
				}
				break;

				case 1:
				switch (ch) {
          case '\n':
          e++;
          l = getLexeme(Lexeme.TYPE_T_NL);
          r++; f = e; s = 0;
          return l;

          default:
          l = getLexeme(Lexeme.TYPE_T_NL);
          r++; f = e; s = 0;
          return l;
        }

        case 2:
        switch (ch) {
          case '0': case '1': case '2': case '3': case '4': case '5':
          case '6': case '7': case '8': case '9':
          e++;
          break;

          default:
          s = 0;
          return getLexeme(Lexeme.TYPE_T_ST_NR);
        }
        break;

				case 3:
        switch (ch) {
          case '0': case '1': case '2': case '3': case '4': case '5':
          case '6': case '7': case '8': case '9':
          case 'A': case 'B': case 'C': case 'D': case 'E': case 'F':
					case 'G': case 'H': case 'I': case 'J': case 'K': case 'L':
					case 'M': case 'N': case 'O': case 'P': case 'Q': case 'R':
					case 'S': case 'T': case 'U': case 'V': case 'W': case 'X':
					case 'Y': case 'Z': case '_':
          e++;
          break;

          default:
          s = 0;
          return getLexeme(Lexeme.TYPE_T_LX_ID);
        }
        break;

				case 4:
				switch (ch) {
          case ' ': case '!': case '#': case '$': case '%': case '&': case '\'':
          case '(': case ')': case '*': case '+': case ',': case '.': case '/':
          case '0': case '1': case '2': case '3': case '4': case '5': case '6':
          case '7': case '8': case '9':
          case ':': case ';': case '<': case '=': case '>': case '?': case '@':
          case 'A': case 'B': case 'C': case 'D': case 'E': case 'F': case 'G':
          case 'H': case 'I': case 'J': case 'K': case 'L': case 'M': case 'N':
          case 'O': case 'P': case 'Q': case 'R': case 'S': case 'T': case 'U':
          case 'V': case 'W': case 'X': case 'Y': case 'Z':
          case '[': case ']': case '^': case '_': case '`':
          case 'a': case 'b': case 'c': case 'd': case 'e': case 'f': case 'g':
          case 'h': case 'i': case 'j': case 'k': case 'l': case 'm': case 'n':
          case 'o': case 'p': case 'q': case 'r': case 's': case 't': case 'u':
          case 'v': case 'w': case 'x': case 'y': case 'z':
          case '{': case '|': case '}': case '~':
          e++;
          return getLexeme(Lexeme.TYPE_T_ASC);

          case '-':
          e++;
          return getLexeme(Lexeme.TYPE_T_SCP);

          case'"':
          e++; b = e; s = 0;
          break;

          case '\\':
          b = e; e++; s = 5;
          break;

					case EOFEC:
					break;

          default:
					e++;
					throw new LexemeException(getLexeme(Lexeme.TYPE_N_UNDEF));
				}
				break;

				case 5:
				switch (ch) {
          case '\\':
          e++; s = 4;
          return getLexeme(Lexeme.TYPE_T_ESC_BSL);

          case '-':
          e++; s = 4;
          return getLexeme(Lexeme.TYPE_T_ESC_MIN);

          case '"':
          e++; s = 4;
          return getLexeme(Lexeme.TYPE_T_ESC_DQ);

          case 'r':
          e++; s = 4;
          return getLexeme(Lexeme.TYPE_T_ESC_CR);

          case 'n':
          e++; s = 4;
          return getLexeme(Lexeme.TYPE_T_ESC_LF);

          case '0': case '1': case '2': case '3': case '4': case '5':
          case '6': case '7': case '8': case '9':
					case 'a': case 'b': case 'c': case 'd': case 'e': case 'f':
					case 'A': case 'B': case 'C': case 'D': case 'E': case 'F':
					e++; s = 6;
					break;

          default:
					e++;
					throw new LexemeException(getLexeme(Lexeme.TYPE_N_UNDEF));
				}
				break;

				case 6:
				switch (ch) {
          case '0': case '1': case '2': case '3': case '4': case '5':
          case '6': case '7': case '8': case '9':
					case 'a': case 'b': case 'c': case 'd': case 'e': case 'f':
					case 'A': case 'B': case 'C': case 'D': case 'E': case 'F':
					e++; s = 4;
					return getLexeme(Lexeme.TYPE_T_ESC_ASC_C);

          default:
					e++;
					throw new LexemeException(getLexeme(Lexeme.TYPE_N_UNDEF));
				}
      }

		}
		return null;
	}

}
