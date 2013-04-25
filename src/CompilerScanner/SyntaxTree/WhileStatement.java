package CompilerScanner.SyntaxTree;

/**
 * This is to keep the whileStatements. it extends StatemetnNode
 * @author Alfonso Vazquez
 */
public class WhileStatement extends StatementNode
{
    private Node expression;
    private Node statement;

    /**
     * This returns expression 
     * @return node called expression
     */
    public Node getExpression()
    {
        return expression;
    }

    /**
     * This sets the Expression node
     * @param expression
     */
    public void setExpression(Node expression)
    {
        this.expression = expression;
    }

    /**
     * This gets the statement
     * @return
     */
    public Node getStatement()
    {
        return statement;
    }

    /**
     * This sets the statement
     * @param statement
     */
    public void setStatement(Node statement)
    {
        this.statement = statement;
    }
    /**
     * This keeps the level of the while statement. 
     * @param level
     * @return returns the level 
     */
    @Override
    public String indentedToString( int level)
    {
        String answer = super.indentedToString(level);
        answer += "WhileStatement " + "\n";
        if(expression != null)
        {
            answer += expression.indentedToString(level+1);
        }
        if(statement != null)
        {
            answer += statement.indentedToString(level+1);
        }
        return answer;
    }
}