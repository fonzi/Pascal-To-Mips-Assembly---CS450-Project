package CompilerScanner.SyntaxTree;

import CompilerScanner.ParserScan.Token;
/**
 * This Class is the top of the tree, it should have, right left and middle, which would call
 * subprogram, declarations and compound. 
 * It has a attribute of type Token. 
 * @author Alfonso Vazquez
 */
public class ProgramNode extends Node
{
    Token attribute;
    /**
     * ProgramNode is the top of the tree which calls the attribute. 
     * Then starts creating nodes for the lower level of the tree.
     * @param attr
     */
    public ProgramNode( Token attr)
    {
        this.attribute = attr;
    }
    
    private Node subprogram;
    private Node declarationsNode;
    private Node compoundNode;
        
    /**
     * Gets the last seen attribute.
     * @return
     */
    public Token getAttribute()
    {
        return attribute;
    }

    /**
     * Sets the Token or the attribute for the tree to know what to do, 
     * This should be call in the SyntaxHashTable class (not yet implented)
     * @param attribute
     */
    public void setAttribute(Token attribute)
    {
        this.attribute = attribute;
    }

    /**
     * This gets eth compoundNode 
     * @return returns the compoundNode
     */
    public Node getCompoundNode()
    {
        return compoundNode;
    }

    /**
     * This sets the compoundNode 
     * @param compoundNode
     */
    public void setCompoundNode(Node compoundNode)
    {
        this.compoundNode = compoundNode;
    }

    /**
     * This gets the declarationsNode
     * @return declarationsNode
     */
    public Node getDeclarationsNode()
    {
        return declarationsNode;
    }

    /**
     * Sets the declarationsNode 
     * @param declarationsNode
     */
    public void setDeclarationsNode(Node declarationsNode)
    {
        this.declarationsNode = declarationsNode;
    }

    /**
     * Gets the subprogramNode
     * @return
     */
    public Node getSubprogram()
    {
        return subprogram;
    }

    /**
     * Sets the subprogram
     * @param subprogram
     */
    public void setSubprogram(Node subprogram)
    {
        this.subprogram = subprogram;
    }
    
    /**
     * This is to keep the level of the Program. 
     * @param level
     * @return returns the level of the tree display. 
     */
    @Override
    public String indentedToString( int level)
    {
        String answer = super.indentedToString(level);
        answer += "Program " + this.attribute + "\n";
        if(subprogram != null)
        {
            answer += subprogram.indentedToString(level+1);
        }
        if(declarationsNode != null)
        {
            answer += declarationsNode.indentedToString(level+1);
        }
        if(compoundNode != null)
        {
            answer += compoundNode.indentedToString(level+1);
        }

        return answer;
    }
}
