/**
* This class holds the nodes that are used to build a syntax tree
*/
public abstract class Node
{
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