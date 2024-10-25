
package generated;

import lex.*;
import java.io.*;

public class TestLexer {

  public TestLexer(String fnm) {
    System.out.println("file: " + fnm);
    String str = readFile(fnm).toString();
    System.out.println(str);
    try {
      LexemeAnalyser lea = new LexemeAnalyser(str);
      Lexeme lex;
      while((lex = lea.getLexeme())!=null) {
        System.out.println(lex.toString());
      }
    } catch (LexemeException e) {
      System.err.println(e.getMessage());
    }
  }

  public StringBuffer readFile(String fnm) {
    try {
      FileReader fre = new FileReader(fnm);
      BufferedReader bre = new BufferedReader(fre);
      char[] buf = new char[512];
      int pos = 0;
      StringBuffer sbf = new StringBuffer();
      while ((pos=bre.read(buf))!=-1) {
        sbf.append(buf,0,pos);
      }
      return sbf;
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(0);
    }
    return null;
  }

  public static void main(String[] args) {
    if (args.length==1) {
      new TestLexer(args[0]);
    } else {
      new TestLexer("HAAS-LATHE-ALL-2020-04-23.nc");
//      new TestLexer("FANUC-LATHE-ALL-2024-01-12.nc");
    }




  }

}
