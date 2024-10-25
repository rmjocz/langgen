package lex.tree;

public class Link {
  public Integer dest = null;
  public AbstractAscSet[] aas = null;
  public boolean opt_df_na = false, opt_eol = false, opt_bf_rs = false;

/* if nde.links[j].cset.size()==0 and opt_df_na == false then should be created default-advance link 
   but there is not lexical option of default-advance link, so field opt_df_ad not need to exist
   such information can be obtained from the cset variable and opt_df_na == false option
*/

  public Integer lx_id = null;
  
  public Link() {
  }
  
}
