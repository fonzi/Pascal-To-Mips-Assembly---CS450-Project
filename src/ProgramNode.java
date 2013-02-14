public class ProgramNode extends ProgramNode
{
	@Override 
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