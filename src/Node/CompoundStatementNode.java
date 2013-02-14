/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CompilerScanner.Node;

/**
 *
 * @author fonzi
 */
public class CompoundStatementNode extends Node
{
    
    @Override
    public String indentedToString(int level)
    {
        String answer = "";
        if (level > 0)
        {
            answer = "|-- ";
        }
        for (int indent = 1; indent < level; indent++)
        {
            answer += "--- ";
        }
        return (answer);
    }
}
