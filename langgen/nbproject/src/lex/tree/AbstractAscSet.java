package lex.tree;

public abstract class AbstractAscSet {
  
  public abstract boolean contain(char c);
  
  @Override
  public abstract String toString();
  
  public abstract String genString();
  
  public String getSeqEscChar(char c) {
    if (c=='\b') return "\\b";
    if (c=='\t') return "\\t";
    if (c=='\n') return "\\n";
    if (c=='\f') return "\\f";
    if (c=='\r') return "\\r";
//    if (c=='\"') return "\\\"";
    if (c=='\'') return "\\\'";
    if (c=='\\') return "\\\\";
    return ""+c;
  }
  
}
