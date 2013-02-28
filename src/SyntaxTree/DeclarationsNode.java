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
    
    public DeclarationsNode ()
    {
        list = new ArrayList<Node>();
    }
    
    public ArrayList getList()
    {
        return list;
    }

    public void setList(ArrayList list)
    {
        this.list = list;
    }

    public Node getRight()
    {
        return right;
    }

    public void setRight(Node right)
    {
        this.right = right;
    }
    
    @Override
    public String indentedToString(int level)
    {
        String answer = " ";
        answer = super.indentedToString(level);
        answer += "Declarations " + this.list.toString() + "\n";
        return answer;
    }
} 