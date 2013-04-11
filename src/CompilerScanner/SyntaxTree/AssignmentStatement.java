package CompilerScanner.SyntaxTree;

/**
 * This node extends Statement Node
 * This node will create assignment structure for the tree
 * @author Alfonso Vazquez
 */
public class AssignmentStatement extends StatementNode
{

    /**This is the left side of an assingment statement**/
    private Node left;
    /**This is the right side of an assingment statement**/
    private Node right;

    /**
     * Empty only needed to call
     */
    public AssignmentStatement()
    {
    }

    /**
     * Gets the left of the node
     * @return
     */
    public Node getLeft()
    {
        return left;
    }

    /**
     * Sets the left of the node
     * @param left
     */
    public void setLeft(Node left)
    {
        this.left = left;
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
     * Sets the right of the node
     * @param right
     */
    public void setRight(Node right)
    {
        this.right = right;
    }

    /**
     * This is to keep the level of the tree going down + 1 level
     * @param level
     * @return returns a toString which is indented todo a visual representation of the tree
     */
    @Override
    public String indentedToString(int level)
    {
        String answer = super.indentedToString(level);
        answer += "AssignmentStatement " + "\n";
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

    /**
     * Returns the structure of the tree as left and right 
     * @return "AssignmentStatement{" + "left=" + left + ", right=" + right + '}'
     */
    @Override
    public String toString()
    {
        return "AssignmentStatement{" + "left=" + left + ", right=" + right + '}';
    }
    
}
