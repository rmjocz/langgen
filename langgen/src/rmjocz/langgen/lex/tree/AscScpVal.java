/**
 * author: Radoslaw Marek Jocz 
 * email: rmjocz@yahoo.com ; email: rm.jocz@hotmail.com
 * this code is relased on GNU General Public License v2.0 
 * https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */
package rmjocz.langgen.lex.tree;

public class AscScpVal extends AbstractAscSet {
  char lc, hc;
  
  public AscScpVal(char lc, char hc) {
    this.lc = lc; 
    this.hc = hc;
  }
  
  public boolean contain(char c) {
    return c >= lc && c <= hc;
  }
  
  public String toString() {
    return getSeqEscChar(lc)+"-"+getSeqEscChar(hc);
  }    
 
  public String genString() {
    String str = "";
    for(int i = (int)lc; i<=(int)hc; i++) {      
      str += "case '"+getSeqEscChar((char)i)+"': ";
    }    
    return str;
  }
  
}
