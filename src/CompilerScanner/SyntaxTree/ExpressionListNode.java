package CompilerScanner.SyntaxTree;

import java.util.ArrayList;

/**
 * ExpressionListNode is to keep the structure of expressions. This extends Node. 
 * @author Alfonso Vazquez
 */
public class ExpressionListNode extends Node
{
    private ArrayList array = new ArrayList();

    /**
     * This gets the Array where the Expressions are held
     * @return the array
     */
    public ArrayList getArray()
    {
        return array;
    }

    /**
     * This sets the Array of where the expressions will be held. 
     * @param array the array to set
     */
    public void setArray(ArrayList array)
    {
        this.array = array;
    }
    
    /**
     * This is to print the Expression level of the tree. 
     */
     @Override
    public String indentedToString(int level)
    {
        String answer = " ";
        answer = super.indentedToString(level);
        answer += "Expression " + this.array.toString() + "\n";
        return answer;
    }
}
