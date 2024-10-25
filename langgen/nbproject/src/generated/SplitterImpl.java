
package generated;

import lex.*;
import java.io.*;
import java.util.ArrayList;

public class SplitterImpl {

  public SplitterImpl(String fnm) {
    System.out.println("file: " + fnm);
    String str = readFile(fnm).toString();
    System.out.println(str);
    try {
      LexemeAnalyser lea = new LexemeAnalyser(str);
      Lexeme lex;
      Integer f_beg = null, f_end = null;
//      int f_tmp;
//      String f_name;

/*

      ArrayList <GeomElement> ges0 = new ArrayList <GeomElement> ();

*/
      ArrayList <Integer> lst = new ArrayList <Integer> ();


      while((lex = lea.getLexeme())!=null) {
        if (lex.getType() == Lexeme.L_O) {
          f_beg = lex.begin;
          f_end = lea.getLexeme().end;
          lst.add(f_beg);
          System.out.print("FILE: " + str.substring(f_beg, f_end));
          System.out.println(" pos "+ f_beg);
        }
        f_end = lex.end;
      }
      lst.add(f_end);
      System.out.println("last-end "+ f_end);
      
      int io_i, io_b, io_e;
      
      io_i = 5;
      System.out.println("file np " + io_i);
      io_b = lst.get(io_i);
      io_e = lst.get(io_i+1)-1;      
      System.out.println("file ctx ");   
      System.out.println(str.substring(io_b, io_e));
      




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
      new SplitterImpl(args[0]);
    } else {
//      new SplitterImpl("HAAS-LATHE-ALL-2020-04-23.nc");
      new SplitterImpl("FANUC-LATHE-ALL-2024-01-12.nc");      
    }
  }

}
