package CompilerScanner.SyntaxTree;

import java.util.ArrayList;

/**
 * @author fonzi
 */
//first in line
public class DeclarationsNode extends Node
{

    ArrayList<Node> list;
    Node right;

    /**
     * 
     */
    public DeclarationsNode()
    {
        list = new ArrayList<Node>();
    }

    /**
     * 
     * @return
     */
    public ArrayList getList()
    {
        return list;
    }

    /**
     * 
     * @param list
     */
    public void setList(ArrayList list)
    {
        this.list = list;
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
        String answer = " ";
        answer = super.indentedToString(level);
        answer += "Declarations " + this.list.toString() + "\n";
        if (this.right != null)
        {
            answer += "" + this.right.indentedToString(level + 1);
        }
        return answer;
    }
}
