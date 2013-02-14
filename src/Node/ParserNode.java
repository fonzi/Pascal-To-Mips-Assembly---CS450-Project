package CompilerScanner.Node;

/**
 *
 * @author fonzi
 */
public class ParserNode extends Node
{
    protected Node declarations;
    protected Node subprogram;
    protected Node compound_statement;
    
    @Override
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
}
