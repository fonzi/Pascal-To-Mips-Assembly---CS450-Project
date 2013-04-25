/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CompilerScanner.SyntaxTree;

/**
 * Class to keep the statements. 
 * @author fonzi
 */
public class StatementNode extends Node
{
    /**
     * To string to return part of the tree
     * @param level of tree
     * @return return the tree node 
     */
    @Override
    public String indentedToString(int level)
    {
        String answer = super.indentedToString(level);
        return answer;
    }
}
