package CompilerScanner.Node;

/**
 * This class is the super class of All of the nodes that will be implemented 
 * This class contains a method called intendedToString to print out the syntax tree 
 * @author Alfonso Vazquez
 */
public class Node
{
    private String name;
    
    //this method is a toString to print out the tree 
    //this will be @override at subclasses
    /**
     * This method is to print out the syntax tree (given to us by Erik) 
     * @param level
     * @return returns string with indents indicated by "--"
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
    
    //Setters and getter to get the name of a node from the subclasses 
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
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }
}
    
