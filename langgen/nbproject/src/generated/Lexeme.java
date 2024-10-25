/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generated;

public class Lexeme {
  public static final int N_ERROR = -1;
  public static final int N_EOF = 0;
  public static final int L_NL = 1;
  public static final int L_NR = 2;
  public static final int L_COM = 3;
  public static final int L_X = 4;
  public static final int L_Y = 5;
  public static final int L_Z = 6;
  public static final int L_U = 7;
  public static final int L_V = 8;
  public static final int L_W = 9;
  public static final int L_A = 10;
  public static final int L_L = 11;
  public static final int L_C = 12;
  public static final int L_R = 13;
  public static final int L_I = 14;
  public static final int L_J = 15;
  public static final int L_K = 16;
  public static final int L_O = 17;
  public static final int L_N = 18;
  public static final int L_G = 19;
  public static final int L_M = 20;
  public static final int L_T = 21;
  public static final int L_F = 22;
  public static final int L_S = 23;

  private static final int[] types = {
    N_ERROR,
    N_EOF,
    L_NL,
    L_NR,
    L_COM,
    L_X,
    L_Y,
    L_Z,
    L_U,
    L_V,
    L_W,
    L_A,
    L_L,
    L_C,
    L_R,
    L_I,
    L_J,
    L_K,
    L_O,
    L_N,
    L_G,
    L_M,
    L_T,
    L_F,
    L_S
  };

  private static final String[] names = {
    "N_ERROR",
    "N_EOF",
    "L_NL",
    "L_NR",
    "L_COM",
    "L_X",
    "L_Y",
    "L_Z",
    "L_U",
    "L_V",
    "L_W",
    "L_A",
    "L_L",
    "L_C",
    "L_R",
    "L_I",
    "L_J",
    "L_K",
    "L_O",
    "L_N",
    "L_G",
    "L_M",
    "L_T",
    "L_F",
    "L_S"
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
