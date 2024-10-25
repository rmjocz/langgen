package lex;

import java.io.*;
import java.util.*;
import lex.tree.Automata;
import lex.tree.Node;

public class TestParser {

  public TestParser(String fnm) {
    System.out.println("file: " + fnm);
    String str = readFile(fnm).toString();
    System.out.println(str);
    try {
      LexemeAnalyser lea = new LexemeAnalyser(str);
      SyntaxAnalyser sya = new SyntaxAnalyser(lea);
      Automata aut = sya.analyse();
      Node[] nds = aut.nodes;
      String[] nms = aut.names;
      System.out.println("parsed tree: ");
      for(int i=0; i<nds.length; i++) {
        System.out.print(i);
        if (nds[i].opt_en_st) {
          System.out.println(".");
        } else {
          System.out.println();
        }
        for(int j=0; j<nds[i].links.length; j++) {
          System.out.print("  "+nds[i].links[j].dest);
          if (nds[i].links[j].aas.length>0) {
            System.out.print(" \"");
            for(int k=0; k<nds[i].links[j].aas.length; k++) {
              System.out.print(nds[i].links[j].aas[k]);
            }
            System.out.print("\"");            
          }
          if (nds[i].links[j].opt_bf_rs) {
            System.out.print(" -");            
          }
          if (nds[i].links[j].opt_eol) {
            System.out.print(" ~");            
          }
          if (nds[i].links[j].opt_df_na) {
            System.out.print(" @");
          }
          if (nds[i].links[j].lx_id != null) {
            System.out.println(" "+nms[nds[i].links[j].lx_id]);
          } else {
            System.out.println("--null--");
          }
        }
        System.out.println();
      }
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
      new TestParser(args[0]);
    }
  }

}
