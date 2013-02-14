package CompilerScanner.Node;

import java.util.ArrayList;

/**
 *
 * @author fonzi
 */
public class DeclarationsNode extends Node
{  
    protected Node idList;
    protected DeclarationsNode declarations;
    protected ArrayList ids;
    
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
