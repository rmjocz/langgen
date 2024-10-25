/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generated;

/**
file: lex_df_cnc_g-code_simulator_simple.txt
0.
  0 "\n"  ~ L_NL
  1 "\r"

1
  0 "\n"  ~ L_NL
  0      ~@ L_NL

0.
  1 "+"
  1 "\-"
  2 "."
  3 "0-9"

1
  2 "."
  3 "0-9"

2
  4 "0-9"

3
  3 "0-9"
  4 "."
  0       @ L_NR

4
  4 "0-9"
  0       @ L_NR

0.
  0 " " -
  0 "%" -
  0 ":" -
  0 "," -
  0 "#" -
  0 "[" -
  0 "]" -
  1 "("

1
  0 ")"    L_COM
  1

0.
  0 "X" L_X
  0 "Y" L_Y
  0 "Z" L_Z
  0 "U" L_U
  0 "V" L_V
  0 "W" L_W
  0 "A" L_A
  0 "L" L_L
  0 "C" L_C
  0 "R" L_R
  0 "I" L_I
  0 "J" L_J
  0 "K" L_K
  0 "O" L_O
  0 "N" L_N
  0 "G" L_G
  0 "M" L_M
  0 "T" L_T
  0 "F" L_F
  0 "S" L_S
  * 
  */

public class LexemeAnalyser {
  String text;
  int n;
  boolean eof;
  int b, e, r, c, f;
  int s;
  final static char eofec = '\uFFFF';
  final int maxLexLen;

  public LexemeAnalyser(String text) {
    this.text = text;
    n = text.length();
    eof = false;
    b = 0; e = 0; r = 1; c = 0; f = 0;
    s = 0;
    maxLexLen = 1024;
  }

  public LexemeAnalyser(String text, int maxLexLen) {
    this.text = text;
    n = text.length();
    eof = false;
    b = 0; e = 0; r = 1; c = 0; f = 0;
    s = 0;
    this.maxLexLen = maxLexLen;
  }

  private char getChar() {
    if (e<n) {
      return text.charAt(e);
    } else {
      eof = true;
      return eofec;
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
          case '\n': 
          e++; c = b - f + 1; f = e;
          return getLexemeImpl(Lexeme.L_NL, b, e<n?e:n, r++, c);

          case '\r': 
          e++; s = 1;
          break;

          case '+': 
          e++; s = 2;
          break;

          case '-': 
          e++; s = 2;
          break;

          case '.': 
          e++; s = 3;
          break;

          case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': 
          e++; s = 4;
          break;

          case ' ': 
          e++; b = e;
          break;

          case '%': 
          e++; b = e;
          break;

          case ':': 
          e++; b = e;
          break;

          case ',': 
          e++; b = e;
          break;

          case '#': 
          e++; b = e;
          break;

          case '[': 
          e++; b = e;
          break;

          case ']': 
          e++; b = e;
          break;

          case '(': 
          e++; s = 6;
          break;

          case 'X': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_X, b, e<n?e:n, r, c);

          case 'Y': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_Y, b, e<n?e:n, r, c);

          case 'Z': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_Z, b, e<n?e:n, r, c);

          case 'U': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_U, b, e<n?e:n, r, c);

          case 'V': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_V, b, e<n?e:n, r, c);

          case 'W': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_W, b, e<n?e:n, r, c);

          case 'A': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_A, b, e<n?e:n, r, c);

          case 'L': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_L, b, e<n?e:n, r, c);

          case 'C': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_C, b, e<n?e:n, r, c);

          case 'R': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_R, b, e<n?e:n, r, c);

          case 'I': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_I, b, e<n?e:n, r, c);

          case 'J': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_J, b, e<n?e:n, r, c);

          case 'K': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_K, b, e<n?e:n, r, c);

          case 'O': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_O, b, e<n?e:n, r, c);

          case 'N': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_N, b, e<n?e:n, r, c);

          case 'G': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_G, b, e<n?e:n, r, c);

          case 'M': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_M, b, e<n?e:n, r, c);

          case 'T': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_T, b, e<n?e:n, r, c);

          case 'F': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_F, b, e<n?e:n, r, c);

          case 'S': 
          e++; c = b - f + 1;
          return getLexemeImpl(Lexeme.L_S, b, e<n?e:n, r, c);

          case eofec:
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
          case '.': 
          e++; s = 3;
          break;

          case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': 
          e++; s = 4;
          break;

          default:
          e++; c = b - f + 1;
          throw new LexemeException(new Lexeme(Lexeme.N_ERROR, b, e<n?e:n, r, c, getText()));
        }
        break;

        case 3:
        switch (ch) {
          case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': 
          e++; s = 5;
          break;

          default:
          e++; c = b - f + 1;
          throw new LexemeException(new Lexeme(Lexeme.N_ERROR, b, e<n?e:n, r, c, getText()));
        }
        break;

        case 4:
        switch (ch) {
          case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': 
          e++;
          break;

          case '.': 
          e++; s = 5;
          break;

          default:
          eof = false;
          c = b - f + 1; s = 0;
          return getLexemeImpl(Lexeme.L_NR, b, e<n?e:n, r, c);
        }
        break;

        case 5:
        switch (ch) {
          case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': 
          e++;
          break;

          default:
          eof = false;
          c = b - f + 1; s = 0;
          return getLexemeImpl(Lexeme.L_NR, b, e<n?e:n, r, c);
        }
        break;

        case 6:
        switch (ch) {
          case ')': 
          e++; c = b - f + 1; s = 0;
          return getLexemeImpl(Lexeme.L_COM, b, e<n?e:n, r, c);

          default:
          e++;
          break;
        }
      }
    }
    return null;
  }
}
