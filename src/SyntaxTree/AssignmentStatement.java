package CompilerScanner.SyntaxTree;

/**
 *
 * @author Alfonso Vazquez
 */
public class AssignmentStatement extends StatementNode
{

    /**This is the left side of an assingment statement**/
    private Node left;
    /**This is the right side of an assingment statement**/
    private Node right;

    /**
     * 
     */
    public AssignmentStatement()
    {
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

    /**
     * 
     * @param level
     * @return
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
}
