/**
 * author: Radoslaw Marek Jocz 
 * email: rmjocz@yahoo.com ; email: rm.jocz@hotmail.com
 * this code is relased on GNU General Public License v2.0 
 * https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */
package rmjocz.langgen.lex;

public class Lexeme {

	public static final int TYPE_N_UNDEF     = -1;
	public static final int TYPE_N_EOF       = 0;  

	public static final int TYPE_T_NL        = 1;

	public static final int TYPE_T_ASC       = 10;
	public static final int TYPE_T_SCP       = 11;
  
	public static final int TYPE_T_ESC_ASC_C = 20;
	public static final int TYPE_T_ESC_BSL   = 21;    
	public static final int TYPE_T_ESC_MIN   = 22;
	public static final int TYPE_T_ESC_DQ    = 23;
	public static final int TYPE_T_ESC_CR    = 24;
	public static final int TYPE_T_ESC_LF    = 25;
  
	public static final int TYPE_T_OPT_DF_NA = 30;
	public static final int TYPE_T_OPT_EOL   = 31;
	public static final int TYPE_T_OPT_BF_RS = 32;  
	public static final int TYPE_T_OPT_EN_ST = 33;    
  
	public static final int TYPE_T_ST_NR     = 40;
	public static final int TYPE_T_LX_ID     = 41;  

	int type;
	int begin;
	int end;
	int row;
	int column;
	String content;

	public Lexeme(int type, int begin, int end, int row, int column, String content) {
		this.type = type;
		this.begin = begin;
		this.end = end;
		this.row = row;
		this.column = column;
		this.content = content;
	}

	public Lexeme(int type) {
		this(type, -1, -1, -1, -1, "");
	}

	public Lexeme(int type, int begin, int end, String content) {
		this(type, begin, end, -1, -1, content);
	}

	public String toString() {
		return getTypeName(type) +
					 ", type: " + type +
					 " begin: " + begin +
					 " end: " + end +
					 " row: " + row +
					 " column: " + column +
					 " content: " + "'" + content + "'";
	}

	public static String getTypeName(int type) {
		switch (type) {      
			case TYPE_N_UNDEF:
			return "_N_UNDEF";
      
      case TYPE_N_EOF:
      return "_N_EOF"; 

			case TYPE_T_NL:
			return "_T_NL";

			case TYPE_T_ASC:
			return "_T_ASC";

			case TYPE_T_SCP:
			return "_T_SCP";
      
      case TYPE_T_ESC_ASC_C:
      return "_T_ESC_ASC_C";
      
      case TYPE_T_ESC_BSL:
      return "_T_ESC_BSL";
      
      case TYPE_T_ESC_MIN:
      return "_T_ESC_MIN";
      
      case TYPE_T_ESC_DQ:
      return "_T_ESC_DQ";
      
      case TYPE_T_ESC_CR:
      return "_T_ESC_CR";
      
      case TYPE_T_ESC_LF:
      return "_T_ESC_LF";
      
      case TYPE_T_OPT_DF_NA:
      return "_T_OPT_DF_NA";
      
      case TYPE_T_OPT_EOL:
      return "_T_OPT_EOL";
      
      case TYPE_T_OPT_BF_RS:
      return "_T_OPT_BF_RS";
      
      case TYPE_T_OPT_EN_ST:
      return "_T_OPT_EN_ST";
      
      case TYPE_T_ST_NR:
      return "_T_ST_NR";
      
      case TYPE_T_LX_ID:
      return "_T_LX_ID";
		}
		return null;
	}

}
