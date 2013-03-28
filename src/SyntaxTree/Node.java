package CompilerScanner.SyntaxTree;

/**
 * This is the top level of the tree
 * This is of type interface, contains a Node[] and a method children();
 * @author Alfonso Vazquez
 */
public abstract class Node
{   
    private String name;
    
    /**
     * 
     * @param level
     * @return
     */
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
    
    /**
     * 
     * @return
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * 
     * @param setName
     */
    public void setName(String setName)
    {
        this.name = setName;
    }
}