/**
 * author: Radoslaw Marek Jocz
 * email: rmjocz@yahoo.com ; email: rm.jocz@hotmail.com
 * this code is relased on GNU General Public License v2.0
 * https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */
package rmjocz.langgen.lex;

import java.util.ArrayList;
import rmjocz.langgen.lex.tree.AscVal;
import rmjocz.langgen.lex.tree.AscScpVal;
import rmjocz.langgen.lex.tree.AbstractAscSet;
import rmjocz.langgen.lex.tree.Link;
import rmjocz.langgen.lex.tree.Node;
import rmjocz.langgen.lex.tree.Automata;

public class SyntaxAnalyser {
  LexemeAnalyser la;
  boolean eof;
  double cou;
  int nva;
  Lexeme lex;
  ArrayList <String> nms;

  public SyntaxAnalyser(LexemeAnalyser la) {
    this.la = la;
    eof = false;
    lex = null;
    nms = new ArrayList<>();
  }

  private Lexeme getLexeme() throws LexemeException {
    Lexeme tmp;
    tmp = lex;
    lex = la.getLexeme();
    if (tmp==null) {
      tmp = lex;
      lex = la.getLexeme();
    }
    if (tmp!=null) {
      return tmp;
    } else {
      eof = true;
      return new Lexeme(Lexeme.TYPE_N_EOF);
    }
  }

  private Lexeme peekLexeme() throws LexemeException {
    if (lex==null) {
      lex = la.getLexeme();
    }
    if (lex!=null) {
      return lex;
    } else {
      eof = true;
      return new Lexeme(Lexeme.TYPE_N_EOF);
    }
  }

  private char getASCCode(Lexeme lex) throws IllegalArgumentException {
    switch (lex.type) {
      case Lexeme.TYPE_T_ASC:
      return lex.content.charAt(0);

      case Lexeme.TYPE_T_ESC_ASC_C:
      return (char)Integer.parseInt(lex.content.substring(1), 16);

      case Lexeme.TYPE_T_ESC_BSL: case Lexeme.TYPE_T_ESC_DQ:
      case Lexeme.TYPE_T_ESC_MIN:
      return lex.content.charAt(1);

      case Lexeme.TYPE_T_ESC_CR:
      return '\r';

      case Lexeme.TYPE_T_ESC_LF:
      return '\n';

      default:
      throw new IllegalArgumentException();
    }
  }

  private AbstractAscSet[] _abstractAscSet() throws LexemeException, SyntaxException, IllegalArgumentException {
    int s = 0, t;
    Lexeme lex, tmp = null;
    ArrayList <AbstractAscSet> cset = new ArrayList<>();

    while(true) {
      lex = peekLexeme();
      t = lex.type;
      switch(s) {
        case 0:
        switch(t) {
          case Lexeme.TYPE_T_ASC: case Lexeme.TYPE_T_ESC_ASC_C:
          case Lexeme.TYPE_T_ESC_BSL: case Lexeme.TYPE_T_ESC_MIN:
          case Lexeme.TYPE_T_ESC_DQ: case Lexeme.TYPE_T_ESC_CR:
          case Lexeme.TYPE_T_ESC_LF:
          tmp = getLexeme();
          s = 1;
          break;

          default:
          return cset.toArray(new AbstractAscSet[0]);
//          return cset.size()>0 ? cset.toArray(new AbstractAscSet[0]) : null;
        }
        break;

        case 1:
        switch(t) {
          case Lexeme.TYPE_T_SCP:
          getLexeme();
          s = 2;
          break;

          default:
          cset.add(new AscVal(getASCCode(tmp)));
          s = 0;
        }
        break;

        case 2:
        switch(t) {
          case Lexeme.TYPE_T_ASC: case Lexeme.TYPE_T_ESC_ASC_C:
          case Lexeme.TYPE_T_ESC_BSL: case Lexeme.TYPE_T_ESC_MIN:
          case Lexeme.TYPE_T_ESC_DQ: case Lexeme.TYPE_T_ESC_CR:
          case Lexeme.TYPE_T_ESC_LF:
          cset.add(new AscScpVal(getASCCode(tmp), getASCCode(lex)));
          getLexeme();
          s = 0;
          break;

          default:
          throw new SyntaxException("::_abstractAscSet::s=2");
        }
      }
    }
  }

  private Link _link(int offset) throws LexemeException, SyntaxException {
    int s = 0, t;
    Lexeme lex, tmp = null;
    Link lnk = new Link();

    while(true) {
      lex = peekLexeme();
      t = lex.type;
      switch(s) {
        case 0:
        switch(t) {
          case Lexeme.TYPE_T_ST_NR:
          if (Integer.parseInt(lex.content)==0) {
            lnk.dest = 0;
          } else {
            lnk.dest = Integer.parseInt(lex.content) + offset;
          }
          getLexeme();
          s = 1;
          break;

          default:
          throw new SyntaxException("::_link::s=0");
        }
        break;

        case 1:
        lnk.aas = _abstractAscSet();
        s = 2;
        break;

        case 2:
        switch(t) {
          case Lexeme.TYPE_T_OPT_BF_RS:
          lnk.opt_bf_rs = true;
          getLexeme();
          break;

          case Lexeme.TYPE_T_OPT_DF_NA:
          lnk.opt_df_na = true;
          getLexeme();
          break;

          case Lexeme.TYPE_T_OPT_EOL:
          lnk.opt_eol = true;
          getLexeme();
          break;

          default:
          s = 3;
          break;
        }
        break;

        case 3:
        switch(t) {
          case Lexeme.TYPE_T_LX_ID:
          if (nms.indexOf(lex.content) == -1) {
            nms.add(lex.content);
          }
          lnk.lx_id =  nms.indexOf(lex.content);
          getLexeme();
          return lnk;

          default:
          return lnk;
        }
      }
    }
  }

  private Node[] _add_node(int stcnt, int stcsb, Node nde, ArrayList <Link> lnks, ArrayList <Node> ndes) {
    if (stcnt!=0 && stcsb==0) {
      Link[] lks = ((Node)ndes.get(0)).links;
      for (int i=lks.length-1; i>=0; i--) {
        lnks.add(0, lks[i]);
      }
      nde.links = lnks.toArray(new Link[0]);
      ndes.set(0, nde);
    } else {
      nde.links = lnks.toArray(new Link[0]);
      ndes.add(nde);
    }
    lnks.clear();
    return ndes.toArray(new Node[0]);
  }

  private Node[] _nodes() throws LexemeException, SyntaxException {
    int s = 0, t, stcnt = 0, stcsb = 0, st0c = 0;
    Integer stn = null;
    Lexeme lex, tmp = null;
    Node nde = null;
    ArrayList <Link> lnks = new ArrayList<>();
    ArrayList <Node> ndes = new ArrayList<>();

    while(true) {
      lex = peekLexeme();
      t = lex.type;
      switch(s) {
        case 0:
        switch(t) {
          case Lexeme.TYPE_T_ST_NR:
          if (new Integer(lex.content)!=stcsb) {
            if (new Integer(lex.content)!=0) {
              throw new SyntaxException("::_nodes::s=0, node->no. not correct");
            } else {
              stcsb = 0;
              st0c++;
            }
          }
          nde = new Node();
          getLexeme();
          s = 1;
          break;

          case Lexeme.TYPE_T_NL:
          getLexeme();
          break;

          case Lexeme.TYPE_N_EOF:
          return ndes.toArray(new Node[0]);

          default:
          throw new SyntaxException("::_nodes::s=0");
        }
        break;

        case 1:
        switch(t) {
          case Lexeme.TYPE_T_OPT_EN_ST:
          nde.opt_en_st = true;
          getLexeme();
          s = 2;
          break;

          case Lexeme.TYPE_T_NL:
          getLexeme();
          s = 3;
          break;

          default:
          throw new SyntaxException("::_nodes::s=1");
        }
        break;

        case 2:
        switch(t) {
          case Lexeme.TYPE_T_NL:
          getLexeme();
          s = 3;
          break;

          default:
          throw new SyntaxException("::_nodes::s=2");
        }
        break;

        case 3:
        lnks.add(_link(stcnt-stcsb-st0c));
        s = 4;
        break;

        case 4:
        switch(t) {
          case Lexeme.TYPE_T_NL:
          getLexeme();
          s = 5;
          break;

          case Lexeme.TYPE_N_EOF:
          return _add_node(stcnt, stcsb, nde, lnks, ndes);

          default:
          throw new SyntaxException("::_nodes::s=4");
        }
        break;

        case 5:
        switch(t) {
          case Lexeme.TYPE_T_NL:
          _add_node(stcnt, stcsb, nde, lnks, ndes);
          getLexeme();
          stcnt++;
          stcsb++;
          s = 0;
          break;

          case Lexeme.TYPE_N_EOF:
          return _add_node(stcnt, stcsb, nde, lnks, ndes);

          default:
          lnks.add(_link(stcnt-stcsb-st0c));
          s = 4;
        }
        break;
      }
    }
  }

  public Automata analyse() throws LexemeException, SyntaxException {
    try {
      return new Automata(_nodes(), nms.toArray(new String[0]));
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
    return null;
  }

}
