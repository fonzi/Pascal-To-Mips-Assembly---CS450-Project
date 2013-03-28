/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CompilerScanner.SyntaxTree;

/**
 *
 * @author fonzi
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
     * 
     */
    public SubprogramNode(){}

    /**
     * 
     * @return
     */
    public String getCode()
    {
        return code;
    }

    /**
     * 
     * @param code
     */
    public void setCode(String code)
    {
        this.code = code;
    }

    /**
     * 
     * @return
     */
    public Node getFirst()
    {
        return first;
    }

    /**
     * 
     * @param first
     */
    public void setFirst(Node first)
    {
        this.first = first;
    }

    /**
     * 
     * @return
     */
    public Node getFourth()
    {
        return fourth;
    }

    /**
     * 
     * @param fourth
     */
    public void setFourth(Node fourth)
    {
        this.fourth = fourth;
    }

    /**
     * 
     * @return
     */
    public Node getSecond()
    {
        return second;
    }

    /**
     * 
     * @param second
     */
    public void setSecond(Node second)
    {
        this.second = second;
    }

    /**
     * 
     * @return
     */
    public Node getThird()
    {
        return third;
    }

    /**
     * 
     * @param third
     */
    public void setThird(Node third)
    {
        this.third = third;
    }

    /**
     * 
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