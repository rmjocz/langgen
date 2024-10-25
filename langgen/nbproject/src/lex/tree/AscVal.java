package lex.tree;

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
