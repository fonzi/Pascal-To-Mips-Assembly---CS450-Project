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
    
    /**
     * Holds the ArrayList of type Node
     */
    public CompoundStatementNode ()
    {
        statementList = new ArrayList<Node>();
    }
    
    /**
     * Gets the ArrayList 
     * @return returns the ArrayList
     */
    public ArrayList<Node> getStatementList()
    {
        return statementList;
    }

    /**
     * Sets the StatementList with one argument
     * @param statementList
     */
    public void setStatementList(ArrayList<Node> statementList)
    {
        this.statementList = statementList;
    }
    
    
    /**
     * Keeps the tree growing, by one level each time it is called
     * @param is of type int 
     * @return this returns the visual of the tree  
     */
    @Override
    public String indentedToString( int level)
    {
        String answer = " ";
        answer = super.indentedToString(level);
        answer += "Compound Statement" + "\n";
        for(int i = 0; i < statementList.size(); i ++)
        {            
            answer += statementList.get(i).indentedToString(level + 1);
        }
        return answer;
    }
}