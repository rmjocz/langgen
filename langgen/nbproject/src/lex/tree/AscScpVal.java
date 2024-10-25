package lex.tree;

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
