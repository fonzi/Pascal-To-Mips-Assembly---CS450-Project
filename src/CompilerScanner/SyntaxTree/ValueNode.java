/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CompilerScanner.SyntaxTree;

/**
 *
 * @author fonzi
 */
public class ValueNode extends ExpressionNode
{
    String attribute;
    
    /**
     * 
     * @param attribute
     */
    public ValueNode(String attribute)
    {
        this.attribute = attribute;
    }

    /**
     * 
     * @return
     */
    @Override
    public String toString()
    {
        return "ValueNode{" + "attribute=" + attribute + '}';
    }
    

    /**
     * 
     * @return
     */
    public String getAttribute()
    {
        return attribute;
    }

    /**
     * 
     * @param attribute
     */
    public void setAttribute(String attribute)
    {
        this.attribute = attribute;
    }


    @Override
    public String indentedToString(int level)
    {
        String answer = super.indentedToString(level);
        answer += "Value " + attribute + "\n";
        return answer;
    }
}
