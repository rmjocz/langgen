/**
 * author: Radoslaw Marek Jocz 
 * email: rmjocz@yahoo.com ; email: rm.jocz@hotmail.com
 * this code is relased on GNU General Public License v2.0 
 * https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */
package rmjocz.langgen.lex.gen;

import rmjocz.langgen.lex.LexemeAnalyser;
import rmjocz.langgen.lex.LexemeException;
import rmjocz.langgen.lex.SyntaxAnalyser;
import rmjocz.langgen.lex.SyntaxException;
import rmjocz.langgen.lex.tree.AbstractAscSet;
import rmjocz.langgen.lex.tree.Automata;
import rmjocz.langgen.lex.tree.Node;

import java.io.PrintStream;

public class LexerGenerator {
  String str;
  Automata aut;

  public LexerGenerator(String str) {
    this.str = str;
  }

  public void parse() throws LexemeException, SyntaxException {
      LexemeAnalyser lea = new LexemeAnalyser(str);
      SyntaxAnalyser sya = new SyntaxAnalyser(lea);
      aut = sya.analyse();
  }

  public void generateLexemeException(PrintStream ps) {
    ps.println(
      "public class LexemeException extends Exception {" +
      "\n" +
      "  Lexeme lexeme;\n" +
      "  String info;\n" +
      "\n" +
      "  public LexemeException(Lexeme lexeme) {\n" +
      "    this.lexeme = lexeme;\n" +
      "    info = \"\";\n" +
      "  }\n" +
      "\n" +
      "  public LexemeException(Lexeme lexeme, String info) {\n" +
      "    this.lexeme = lexeme;\n" +
      "    this.info = info;\n" +
      "  }\n" +
      "\n" +              
              
      "  @Override\n" +
      "  public String getMessage() {\n" +
      "    return \"Lexeme exception: \" + info + \" \" + lexeme.toString();\n" +
      "  }\n" +
      "\n" +
      "  @Override\n" +
      "  public void printStackTrace() {\n" +
      "    System.err.println(getMessage());\n" +
      "  }\n" +
      "}");
  }

  public void generateLexeme(PrintStream ps) {
    ps.println("public class Lexeme {");
    lexeme_constants_decl(ps, aut.names);
    ps.println(
      "  int type;\n" +
      "  int begin;\n" +
      "  int end;\n" +
      "  int row;\n" +
      "  int column;\n" +
      "  String content;\n" +
      "\n" +
      "  public Lexeme(int type, int begin, int end, int row, int column, String content) {\n" +
      "    this.type = type;\n" +
      "    this.begin = begin;\n" +
      "    this.end = end;\n" +
      "    this.row = row;\n" +
      "    this.column = column;\n" +
      "    this.content = content;\n" +
      "  }\n" +
      "\n" +
      "  public Lexeme(int type) {\n" +
      "    this(type, -1, -1, -1, -1, \"\");\n" +
      "  }\n" +
      "\n" +
      "  public Lexeme(int type, int begin, int end, String content) {\n" +
      "    this(type, begin, end, -1, -1, content);\n" +
      "  }\n" +
      "\n" +
      "  @Override\n" +
      "  public String toString() {\n" +
      "    return getTypeName(type) +\n" +
      "           \", type: \" + type +\n" +
      "           \" begin: \" + begin +\n" +
      "           \" end: \" + end +\n" +
      "           \" row: \" + row +\n" +
      "           \" column: \" + column +\n" +
      "           \" content: \" + \"'\" + content + \"'\";\n" +
      "  }\n" +
      "\n" +
      "  public static String getTypeName(int type) {\n" +
      "    for(int i=0; i<types.length; i++) {\n" +
      "      if (types[i] == type) {\n" +
      "        return names[i];\n" +
      "      }\n" +
      "    }\n" +
      "    return \"\";\n" +
      "  }\n" +

      "  public int getType() {\n" +
      "    return type;\n" +
      "  }\n" +
      "\n" +
      "  public String getContent() {\n" +
      "    return content;\n" +
      "  }\n" +
      "\n" +
      "}");
  }

  public void lexeme_constants_decl(PrintStream ps, String[] names) {
    ps.println("  public static final int N_ERROR = -1;\n" +
               "  public static final int N_EOF = 0;");
    for (int i=0; i<names.length; i++) {
      ps.println("  public static final int " + names[i] + " = " + (i+1) + ";");
    }
    ps.println();
    ps.println("  private static final int[] types = {\n" +
               "    N_ERROR,\n" +
               "    N_EOF,");
    for (int i=0; i<names.length-1; i++) {
      ps.println("    " + names[i] + ",");
    }
    ps.println("    " + names[names.length-1]);
    ps.println("  };\n");

    ps.println("  private static final String[] names = {\n" +
               "    \"N_ERROR\",\n" +
               "    \"N_EOF\",");
    for (int i=0; i<names.length-1; i++) {
      ps.println("    \"" + names[i] + "\",");
    }
    ps.println("    \"" + names[names.length-1] + "\"\n" +
               "  };\n");
  }

  public void generateLexemeAnalyser(PrintStream ps) {
    ps.println(
      "public class LexemeAnalyser {\n" +
      "  String text;\n" +
      "  int n;\n" +
      "  boolean eof;\n" +
      "  int b, e, r, c, f;\n" +
      "  int s;\n" +
      "  final static char EOFEC = '\\uFFFF';\n" +
      "  final int maxLexLen;\n" +
      "\n" +
      "  public LexemeAnalyser(String text, int maxLexLen) {\n" +
      "    this.text = text;\n" +
      "    n = text.length();\n" +
      "    eof = false;\n" +
      "    b = 0; e = 0; r = 1; c = 0; f = 0;\n" +
      "    s = 0;\n" +
      "    this.maxLexLen = maxLexLen;\n" +
      "  }\n" +
      "\n" +
      "  public LexemeAnalyser(String text) {\n" +              
      "    this(text, 1024);\n" +
      "  }\n" +
      "\n" +              
      "  private char getChar() {\n" +
      "    if (e<n) {\n" +
      "      return text.charAt(e);\n" +
      "    } else {\n" +
      "      eof = true;\n" +
      "      return EOFEC;\n" +
      "    }\n" +
      "  }\n" +
      "\n" +
      "  private String getText() {\n" +
      "    return e<n ? text.substring(b, e) : text.substring(b, n);\n" +
      "  }\n" +
      "\n" +
      "  private Lexeme getLexemeImpl(int type, int b, int e, int r, int c) throws LexemeException {\n" +
      "    if (e-b > maxLexLen) {\n" +
      "      throw new LexemeException(new Lexeme(type, b, e, r, c, getText()), \"string is too long:\" + (e-b) + \" > \" + maxLexLen);\n" +
      "    }\n" +              
      "    return new Lexeme(type, b, e, r, c, getText());\n" +
      "  }\n");
    lexer_auto(ps, aut);
    ps.println("}");
  }

  private void lexer_ascset(PrintStream ps, AbstractAscSet[] aas) {
    if (aas.length>0) {
      for(int i=0; i<aas.length; i++) {
        ps.println("          "+aas[i].genString());
      }
    }
  }

  private void lexer_dest(PrintStream ps, Node[] nds, int i, int j) {
    if (nds[i].links[j].dest!=i) {
      ps.println(" s = "+nds[i].links[j].dest+";");
    } else {
      ps.println();
    }
  }

  private boolean lexer_link(PrintStream ps, Node[] nds, String[] nms, int i, int j) {
   if (nds[i].links[j].lx_id!=null) {
      lexer_ascset(ps, nds[i].links[j].aas);
      int id = nds[i].links[j].lx_id;
      if (nds[i].links[j].opt_eol) {
        ps.print("          e++; c = b - f + 1; f = e;");
        lexer_dest(ps, nds, i, j);
        ps.println("          return getLexemeImpl(Lexeme."+nms[id]+", b, e<n?e:n, r++, c);\n");
      } else {
        ps.print("          e++; c = b - f + 1;");
        lexer_dest(ps, nds, i, j);
        ps.println("          return getLexemeImpl(Lexeme."+nms[id]+", b, e<n?e:n, r, c);\n");
      }
      return true;
    } else {
      lexer_ascset(ps, nds[i].links[j].aas);
      if (nds[i].links[j].opt_bf_rs) {
        ps.print("          e++; b = e;");
      } else {
        ps.print("          e++;");
      }
      lexer_dest(ps, nds, i, j);
      ps.println("          break;\n");
    }
    return false;
  }

  private void lexer_link_df_na(PrintStream ps, Node[] nds, String[] nms, int i, int j) {
    ps.println("          default:");
    ps.println("          eof = false;");
    if (nds[i].links[j].lx_id == null) {
      if (nds[i].links[j].opt_eol) {
        ps.print("         f = e;");
      } else {
        ps.print("         ");
      }
      lexer_dest(ps, nds, i, j);
      ps.println("          break;");
    } else {
      int id = nds[i].links[j].lx_id;
      if (nds[i].links[j].opt_eol) {
        ps.print("          c = b - f + 1; f = e;");
        lexer_dest(ps, nds, i, j);
        ps.println("          return getLexemeImpl(Lexeme."+nms[id]+", b, e<n?e:n, r++, c);");
      } else {
        ps.print("          c = b - f + 1;");
        lexer_dest(ps, nds, i, j);
        ps.println("          return getLexemeImpl(Lexeme."+nms[id]+", b, e<n?e:n, r, c);");
      }
    }
  }

  private boolean lexer_link_eof_df(PrintStream ps, Node[] nds, String[] nms, int i, int j) {
    Node nde = nds[i];

    if (j<nde.links.length-1) {
      return lexer_link(ps, nds, nms, i, j);
    } else {
      if (nde.links[j].opt_df_na) {
        lexer_link_df_na(ps, nds, nms, i, j);
        return true;
      } else {
        boolean rt = lexer_link(ps, nds, nms, i, j);
        if (nde.opt_en_st) {
          ps.println("          case EOFEC:");
          ps.println("          break;\n");
        }
        ps.println("          default:");
        ps.println("          e++; c = b - f + 1;");
        ps.println("          throw new LexemeException(new Lexeme(Lexeme.N_ERROR, b, e<n?e:n, r, c, getText()));");
        return rt && !nde.opt_en_st;
      }
    }
  }

  private void lexer_auto(PrintStream ps, Automata aut) {
    ps.println("  public Lexeme getLexeme() throws LexemeException {");
    ps.println("    b = e;");
    ps.println("    while (!eof) {");
    ps.println("      char ch = getChar();");
    ps.println("      switch(s) {");
    Node[] nds = aut.nodes;
    String[] nms = aut.names;
    for(int i=0; i<nds.length; i++) {
      ps.println("        case "+i+":");
      ps.println("        switch (ch) {");
      boolean r = true;
      for(int j=0; j<nds[i].links.length; j++) {
        r &= lexer_link_eof_df(ps, nds, nms, i, j);
      }
      ps.println("        }");
      if (i<nds.length-1) {
        if (!r) {
          ps.println("        break;\n");
        } else {
          ps.println();
        }
      }
    }
    ps.println("      }");
    ps.println("    }");
    ps.println("    return null;");
    ps.println("  }");
  }

}
