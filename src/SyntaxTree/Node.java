package CompilerScanner.SyntaxTree;

/**
 * This is the top level of the tree
 * This is of type interface, contains a Node[] and a method children();
 * @author Alfonso Vazquez
 */
public abstract class Node
{   
    private String name;
    
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
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String setName)
    {
        this.name = setName;
    }
}