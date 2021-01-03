/**
 * author: Radoslaw Marek Jocz 
 * email: rmjocz@yahoo.com ; email: rm.jocz@hotmail.com
 * this code is relased on GNU General Public License v2.0 
 * https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */
package rmjocz.langgen.lex.tree;

public class AscVal extends AbstractAscSet {
  char c;
  
  public AscVal(char c) {
    this.c = c;
  }
  
  public boolean contain(char c) {
    return c == this.c;
  }
  
  public String toString() {
    return getSeqEscChar(c);
  }
  
  public String genString() {    
    return "case '"+getSeqEscChar(c)+"': ";
  }

}
