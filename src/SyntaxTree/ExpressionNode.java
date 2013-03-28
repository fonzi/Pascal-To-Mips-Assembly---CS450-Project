/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CompilerScanner.SyntaxTree;

/**
 *
 * @author fonzi
 */
public class ExpressionNode extends Node
{   
    String code;
    
    /**
     * 
     * @param level
     * @return
     */
    @Override
    public String indentedToString(int level)
    {
        String answer = super.indentedToString(level);
        return answer;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }
}
