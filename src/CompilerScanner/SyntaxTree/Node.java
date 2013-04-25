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
     * This is the level of the node, so each time the tree goes down deeper the more it will add "--"
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
     * Gets the name
     * @return name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Sets the name 
     * @param setName 
     */
    public void setName(String setName)
    {
        this.name = setName;
    }
}