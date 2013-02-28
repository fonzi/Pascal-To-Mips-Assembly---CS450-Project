/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CompilerScanner.SyntaxTree;

import java.util.ArrayList;
/**
 * Use array list to contain 
 * @author fonzi
 */
public class CompoundStatementNode extends Node
{
    
    ArrayList<Node> statementList;
    //second in line
    
    public CompoundStatementNode ()
    {
        statementList = new ArrayList<Node>();
    }
    
    @Override
    public String indentedToString( int level)
    {
        String answer = " ";
        answer = super.indentedToString(level);
        answer += "Compound Statement" + this.statementList.toString() + "\n";
        for(int i = 0; i < statementList.size(); i ++)
        {            
            answer += statementList.get(i).indentedToString(level + 1);
        }
        return answer;
    }
}