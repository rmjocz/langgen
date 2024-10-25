package lex.gen;

import java.io.*;
import java.util.*;
import lex.LexemeException;
import lex.SyntaxException;

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
