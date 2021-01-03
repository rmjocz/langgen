/**
 * author: Radoslaw Marek Jocz 
 * email: rmjocz@yahoo.com ; email: rm.jocz@hotmail.com
 * this code is relased on GNU General Public License v2.0 
 * https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */
package rmjocz.langgen.lex.util;

import java.util.Scanner;
import rmjocz.langgen.lex.tree.AscScpVal;

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
