package CompilerScanner.SyntaxTree;

/**
 * This class keeps the Valuenode, it extends ExpressionNode
 * @author Alfonso Vazquez
 */
public class ValueNode extends ExpressionNode
{
    String attribute;
    
    /**
     * This sets teh attribute 
     * @param attribute
     */
    public ValueNode(String attribute)
    {
        this.attribute = attribute;
    }

    /**
     * ToString 
     * @return
     */
    @Override
    public String toString()
    {
        return "ValueNode{" + "attribute=" + attribute + "}";
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
     * Sets the attribute. 
     * @param attribute
     */
    public void setAttribute(String attribute)
    {
        this.attribute = attribute;
    }


    /**
     * indentedToString is to keep the level of the tree. 
     * This keeps the level of the Value. 
     */
    @Override
    public String indentedToString(int level)
    {
        String answer = super.indentedToString(level);
        answer += "Value " + attribute + "\n";
        return answer;
    }
}
