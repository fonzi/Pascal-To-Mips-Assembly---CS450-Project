/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CompilerScanner.SyntaxTree;

/**
 *
 * @author fonzi
 */
public class WhileStatement extends StatementNode
{
    private Node expression;
    private Node statement;

    /**
     * 
     * @return
     */
    public Node getExpression()
    {
        return expression;
    }

    /**
     * 
     * @param expression
     */
    public void setExpression(Node expression)
    {
        this.expression = expression;
    }

    /**
     * 
     * @return
     */
    public Node getStatement()
    {
        return statement;
    }

    /**
     * 
     * @param statement
     */
    public void setStatement(Node statement)
    {
        this.statement = statement;
    }
    /**
     * 
     * @param level
     * @return
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