/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CompilerScanner.SyntaxTree;
import CompilerScanner.ParserScan.Token;

/**
 *
 * @author fonzi
 */
public class OperationsNode extends ExpressionNode
{
    Node left;
    Node right;
    Token operation;
    
    /**
     * 
     * @param token
     */
    public OperationsNode(Token token)
    {
        operation = token;
    }

    /**
     * 
     * @return
     */
    public Node getLeft()
    {
        return left;
    }

    /**
     * 
     * @param left
     */
    public void setLeft(Node left)
    {
        this.left = left;
    }

    /**
     * 
     * @return
     */
    public Token getOperation()
    {
        return operation;
    }

    /**
     * 
     * @param operation
     */
    public void setOperation(Token operation)
    {
        this.operation = operation;
    }

    /**
     * 
     * @return
     */
    public Node getRight()
    {
        return right;
    }

    /**
     * 
     * @param right
     */
    public void setRight(Node right)
    {
        this.right = right;
    }
    
    @Override
    public String indentedToString( int level)
    {
        String answer = super.indentedToString(level);
        answer += "Operations " + operation + "\n";
        if(left != null)
        {
            answer += left.indentedToString(level+1);
        }
        if(right != null)
        {
            answer += right.indentedToString(level+1);
        }
        return answer;
    }
    
}
