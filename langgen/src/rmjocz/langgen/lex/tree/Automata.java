/**
 * author: Radoslaw Marek Jocz 
 * email: rmjocz@yahoo.com ; email: rm.jocz@hotmail.com
 * this code is relased on GNU General Public License v2.0 
 * https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */
package rmjocz.langgen.lex.tree;

public class Automata {
  public Node[] nodes;
  public String[] names;
    
  public Automata(Node[] nodes, String[] names) {
    this.nodes = nodes;
    this.names = names;
  }

}
