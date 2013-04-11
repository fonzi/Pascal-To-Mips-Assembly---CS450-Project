package CompilerScanner.SyntaxTree;

import java.util.ArrayList;

/**
 *
 * @author fonzi
 */
public class ExpressionListNode extends Node
{
    private ArrayList array = new ArrayList();

    /**
     * @return the array
     */
    public ArrayList getArray()
    {
        return array;
    }

    /**
     * @param array the array to set
     */
    public void setArray(ArrayList array)
    {
        this.array = array;
    }
    
     @Override
    public String indentedToString(int level)
    {
        String answer = " ";
        answer = super.indentedToString(level);
        answer += "Expression " + this.array.toString() + "\n";
        return answer;
    }
}
