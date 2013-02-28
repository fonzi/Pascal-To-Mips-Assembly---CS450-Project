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

    public SubprogramNode(){}

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public Node getFirst()
    {
        return first;
    }

    public void setFirst(Node first)
    {
        this.first = first;
    }

    public Node getFourth()
    {
        return fourth;
    }

    public void setFourth(Node fourth)
    {
        this.fourth = fourth;
    }

    public Node getSecond()
    {
        return second;
    }

    public void setSecond(Node second)
    {
        this.second = second;
    }

    public Node getThird()
    {
        return third;
    }

    public void setThird(Node third)
    {
        this.third = third;
    }

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