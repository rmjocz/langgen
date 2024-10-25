
package lex.util;

import java.util.Scanner;
import lex.tree.AscScpVal;

public class AscTableCasePrinter {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.println("ASCII lo:");
    int lo = in.nextInt();
    System.out.println("ASCII hi:");
    int hi = in.nextInt();
    in.close();    
    AscScpVal sc = new AscScpVal((char)lo, (char)hi);
    System.out.println(sc.genString());
  }

}
