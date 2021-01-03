/**
 * author: Radoslaw Marek Jocz 
 * email: rmjocz@yahoo.com ; email: rm.jocz@hotmail.com
 * this code is relased on GNU General Public License v2.0 
 * https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */
package rmjocz.langgen.lex.gen;

import java.io.*;
import java.util.*;
import rmjocz.langgen.lex.LexemeException;
import rmjocz.langgen.lex.SyntaxException;

public class GeneratorOutputMain {

  public GeneratorOutputMain(String fnm) {
    System.out.println("file: " + fnm);
    String str = readFile(fnm).toString();
    System.out.println(str);
    try {
      LexerGenerator gen = new LexerGenerator(str);
      gen.parse();
      gen.generateLexemeAnalyser(System.out);
      System.out.println();
      gen.generateLexeme(System.out);
      System.out.println();      
      gen.generateLexemeException(System.out);
    } catch (LexemeException e) {
      System.err.println(e.getMessage());
    } catch (SyntaxException e) {
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
      new GeneratorOutputMain(args[0]);
    }
  }

}
