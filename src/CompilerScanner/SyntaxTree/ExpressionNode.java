/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CompilerScanner.SyntaxTree;

/**
 * This class keeps the ExpressionNodes extends Node
 * @author Alfonso Vazquez
 */
public class ExpressionNode extends Node
{   
    String code;
    
    /**
     * This is to print the level, of the ExpressionNode
     * @param level
     * @return it returns the down level of the Tree
     */
    @Override
    public String indentedToString(int level)
    {
        String answer = super.indentedToString(level);
        return answer;
    }
    /**
     * Gets the code
     * @return
     */
    public String getCode()
    {
        return code;
    }
    /**
     * Sets the code 
     * @param code
     */
    public void setCode(String code)
    {
        this.code = code;
    }
}