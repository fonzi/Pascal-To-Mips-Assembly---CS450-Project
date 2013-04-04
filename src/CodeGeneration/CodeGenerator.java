package CodeGeneration;

import CompilerScanner.SyntaxTree.*;

import java.lang.StringBuilder;

/**
 * This class will create cde for the Syntax Tree 
 * @author fonzi
 */
public class CodeGenerator
{
    //the t register always starts at 0 
    //only 8 t registers
    private int currentTRegister = 0;
    
    public String writeCodeForRoot(Node root)
    {
        StringBuilder code = new StringBuilder();
        code.append(".data\n");
        code.append("\n\n\n");
        code.append(".text\n");
        code.append("main:\n");
        
        String nodeCode = null;
        int tempTRegVal = this.currentTRegister;
        nodeCode = writeCode(root, "$s0");
        
        return (code.toString());
    }
    
    public String writeCode(Node node, String register)
    {
        String nodeCode = null;
        
        if(node instanceof OperationsNode)
        {
            nodeCode = writeCode((OperationsNode)node, register);
        }
        else if (node instanceof AssignmentStatement)
        {
            nodeCode = writeCode((AssignmentStatement)node, register);
        }
        
        return(nodeCode);
    }
}
