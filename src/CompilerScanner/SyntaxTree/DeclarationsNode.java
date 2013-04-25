package CompilerScanner.SyntaxTree;

import java.util.ArrayList;

/**
 * This class is to keep the decl
 * @author Alfonso Vazquez
 */
//first in line
public class DeclarationsNode extends Node
{

    ArrayList<Node> list;
    Node right;

    /**
     * Costructor, makes a new ArrayList
     */
    public DeclarationsNode()
    {
        list = new ArrayList<Node>();
    }

    /**
     * Gets the array list 
     * @return an array list
     */
    public ArrayList getList()
    {
        return list;
    }

    /**
     * Sets teh array list
     * @param list of type ArrayList
     */
    public void setList(ArrayList list)
    {
        this.list = list;
    }

    /**
     * Gets the right node of the parent node
     * @return the right node of the parent node 
     */
    public Node getRight()
    {
        return right;
    }

    /**
     * Sets the right of the parent node
     * @param right of the parent node
     */
    public void setRight(Node right)
    {
        this.right = right;
    }

    /**
     * This is to keep the tree printing by one level down
     * @param level is of type int to see how tall the tree is, the taller the tree the larger the integer
     * @return returns the node of the declarations
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
