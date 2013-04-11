/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CompilerScanner.SyntaxTree;
import CompilerScanner.ParserScan.*;
/**
 *
 * @author fonzi
 */
public class Expression extends ExpressionNode
{
    private Token attribute;
    private Node right;
    private Node left;
    
    /**
     * 
     * @param attr
     */
    public Expression(Token attr)
    {
        this.attribute = attr;
    }
    /**
     * @return the attribute
     */
    public Token getAttribute()
    {
        return attribute;
    }
    /**
     * @param attribute the attribute to set
     */
    public void setAttribute(Token attribute)
    {
        this.attribute = attribute;
    }
    /**
     * @return the right
     */
    public Node getRight()
    {
        return right;
    }
    /**
     * @param right the right to set
     */
    public void setRight(Node right)
    {
        this.right = right;
    }
    /**
     * @return the left
     */
    public Node getLeft()
    {
        return left;
    }
    /**
     * @param left the left to set
     */
    public void setLeft(Node left)
    {
        this.left = left;
    }
    /**
     * 
     * @param level
     * @return
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
