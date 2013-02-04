/**
* This class holds the nodes that are used to build a syntax tree
*/
public abstract class ParserNode
{
	public abstract int calcVal();
	public abstract ParserNode program();
	public abstract ParserNode declarations();
	public abstract ParserNode compound_statement();
	

	public String Treelevel(int level)
	{
		String answer = "";
		if (level > 0)
		{
			answer = "|--";
		}
		for(int i = 1; i < level; i ++)
		{
			answer += "---";
		}
		return(answer);
	}

}