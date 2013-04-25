/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CompilerScanner.SyntaxTree;

/**
 * This takes care of the Subprogram nodes for the tree
 * @author Alfonso Vazquez
 */
//third in line
public class SubprogramNode extends Node
{

    Node first;
    Node second;
    Node third;
    Node fourth;
    String code;

    /**
     * Empty 
     */
    public SubprogramNode(){}

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

    /**
     * Gets the first
     * @return
     */
    public Node getFirst()
    {
        return first;
    }

    /**
     * Sets the first
     * @param first
     */
    public void setFirst(Node first)
    {
        this.first = first;
    }

    /**
     * Gets the fourth 
     * @return
     */
    public Node getFourth()
    {
        return fourth;
    }

    /**
     * Sets the fourth 
     * @param fourth
     */
    public void setFourth(Node fourth)
    {
        this.fourth = fourth;
    }

    /**
     * Gets the second
     * @return
     */
    public Node getSecond()
    {
        return second;
    }

    /**
     * sets the second
     * @param second
     */
    public void setSecond(Node second)
    {
        this.second = second;
    }

    /**
     * gets the third
     * @return
     */
    public Node getThird()
    {
        return third;
    }

    /**
     * sets the third
     * @param third
     */
    public void setThird(Node third)
    {
        this.third = third;
    }

    /**
     * Prints out the node of the subprogram. 
     * @param level
     * @return
     */
    @Override
    public String indentedToString(int level)
    {
        code = super.indentedToString(level);
        code += this.first.indentedToString(level + 1);
        code += this.second.indentedToString(level + 1);
        code += this.third.indentedToString(level + 1);
        code += this.fourth.indentedToString(level + 1);
        return this.code;
    }
}