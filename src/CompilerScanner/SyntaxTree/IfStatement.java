/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CompilerScanner.SyntaxTree;

/**
 *
 * @author fonzi
 */
public class IfStatement extends StatementNode
{
    private Node expression;
    private Node statement_1;
    private Node statement_2;

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
    public Node getStatement_1()
    {
        return statement_1;
    }

    /**
     * 
     * @param statement_1
     */
    public void setStatement_1(Node statement_1)
    {
        this.statement_1 = statement_1;
    }

    /**
     * 
     * @return
     */
    public Node getStatement_2()
    {
        return statement_2;
    }

    /**
     * 
     * @param statement_2
     */
    public void setStatement_2(Node statement_2)
    {
        this.statement_2 = statement_2;
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
        answer += "IFStatement " + "\n";
        if(expression != null)
        {
            answer += expression.indentedToString(level+1);
        }
        if(statement_1 != null)
        {
            answer += statement_1.indentedToString(level+1);
        }
        if(statement_2 != null)
        {
            answer += statement_2.indentedToString(level+1);
        }
        return answer;
    } 
}
