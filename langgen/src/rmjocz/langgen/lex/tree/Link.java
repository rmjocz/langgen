/**
 * author: Radoslaw Marek Jocz 
 * email: rmjocz@yahoo.com ; email: rm.jocz@hotmail.com
 * this code is relased on GNU General Public License v2.0 
 * https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */
package rmjocz.langgen.lex.tree;

public class Link {
  public Integer dest = null;
  public AbstractAscSet[] aas = null;
  public boolean opt_df_na = false, opt_eol = false, opt_bf_rs = false;
  public Integer lx_id = null;
  
  public Link() {
  }
  
}
