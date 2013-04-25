/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CompilerScanner.SyntaxTree;
import CompilerScanner.ParserScan.Token;
/**
 * This class is Expression, which extends ExpressionNode
 * @author Alfonso Vazquez
 */
public class Expression extends ExpressionNode
{
    private Token attribute;
    private Node right;
    private Node left;
    
    /**
     * This is a constructor to keep attribute
     * @param attr
     */
    public Expression(Token attr)
    {
        this.attribute = attr;
    }
    /**
     * This gets the Token 
     * @return the attribute
     */
    public Token getAttribute()
    {
        return attribute;
    }
    /**
     * Sets the attribute 
     * @param attribute the attribute to set
     */
    public void setAttribute(Token attribute)
    {
        this.attribute = attribute;
    }
    /**
     * Gets the right side of the Node
     * @return the right
     */
    public Node getRight()
    {
        return right;
    }
    /**
     * Sets the right side of the Node
     * @param right the right to set
     */
    public void setRight(Node right)
    {
        this.right = right;
    }
    /**
     * Gets the left side of the Node
     * @return the left
     */
    public Node getLeft()
    {
        return left;
    }
    /**
     * Sets the left side of the Node
     * @param left the left to set
     */
    public void setLeft(Node left)
    {
        this.left = left;
    }
    /**
     * Creates the indentedToString one level lower
     * @param level
     * @return retuns the level of the tree
     */
    @Override
    public String indentedToString(int level)
    {
        String answer = super.indentedToString(level);
        answer += "Expression " + "\n";
        if (left != null)
        {
            answer += left.indentedToString(level + 1);
        }
        if (right != null)
        {
            answer += right.indentedToString(level + 1);
        }
        return answer;
    }
}
