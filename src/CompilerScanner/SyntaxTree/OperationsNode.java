package CompilerScanner.SyntaxTree;
import CompilerScanner.ParserScan.Token;

/**
 * This calls keeps the OperationNode level. It extends ExpressionNode
 * @author Alfonso Vazquez
 */
public class OperationsNode extends ExpressionNode
{
    Node left;
    Node right;
    Token operation;
    
    /**
     * This is to set token 
     * @param token
     */
    public OperationsNode(Token token)
    {
        operation = token;
    }

    /**
     * This is to get left of the operation
     * @return left 
     */
    public Node getLeft()
    {
        return left;
    }

    /**
     *  This sets the left
     * @param left
     */
    public void setLeft(Node left)
    {
        this.left = left;
    }

    /**
     * This gets teh Operation of token
     * @return token called operation
     */
    public Token getOperation()
    {
        return operation;
    }

    /**
     * Sets the operations
     * @param operation
     */
    public void setOperation(Token operation)
    {
        this.operation = operation;
    }

    /**
     * Gets the right of the node
     * @return
     */
    public Node getRight()
    {
        return right;
    }

    /**
     * sets the right of the Node
     * @param right
     */
    public void setRight(Node right)
    {
        this.right = right;
    }
    /**
     * This is to keep the level of the Syntax Tree
     */
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
