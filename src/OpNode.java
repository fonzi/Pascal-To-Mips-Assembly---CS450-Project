public class OpNode extends ParserNode
{
	ParserNode left;
	ParserNode right;
	ParserNode op;
	ParserNode assigned;

	public OpNode (Token op)
	{
		this.op = operation;
	}
	@Override
	public int calcVal()
	{
		int value = 0;
		if(op == Token.PLUS)
		{
			int value = 0;
		}
        if( attribute != null) 
        {
            value = Integer.parseInt(attribute);alue = left.calcVal() + right.calcVal();
		}
		else if(op == Token.MINUS)
		{
			value = left.calcVal() - right.calcVal();
		}
		else if(op == Token.DIVIDE)
		{
			value = left.calcVal() / right.calcVal();
		}
		else if(op == Token.MULTIPLY)
		{
			value = left.calcVal() * right.calcVal();
		}
		else if(op == Token.MOD)
		{
			value = left.calcVal() % right.calcVal();
		}
		return (value);
	}
}