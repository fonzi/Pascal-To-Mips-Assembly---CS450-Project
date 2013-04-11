package CodeGeneration;

import CompilerScanner.ParserScan.Token;
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
    
    /**
     * 
     * @param root
     * @return
     */
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
        this.currentTRegister = tempTRegVal;
        code.append( nodeCode);
        code.append("sw     $s0,    answer\n");
        code.append("addi   $v0,    10\n");
        code.append("syscall\n");
        return (code.toString());
    }
    
    /**
     * 
     * @param node
     * @param register
     * @return
     */
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
    
    public String writeCode(OperationsNode opNode, String resultRegister)
    {
        String code = "";
        
        Node left = opNode.getLeft();
        String leftRegister = "$t" + currentTRegister++;
        code = writeCode( left, leftRegister);
        Node right = opNode.getRight();
        String rightRegister = "$t" + currentTRegister++;
        code += writeCode( right, rightRegister);
        Token kindOfOp = opNode.getOperation();
        
        if(kindOfOp == Token.PLUS)
        {
            code+= "add    "+resultRegister+",    "+leftRegister+
                   ",    "+rightRegister   + "\n";
        }
        if(kindOfOp == Token.MINUS)
        {
            code += "sub    "+resultRegister + ",    "+leftRegister+
                    ",    " + rightRegister  +"\n";
        }
        if(kindOfOp == Token.MULTIPLY)
        {
            code += "mult    "+leftRegister+
                    ",    "+rightRegister + "\n";
            code += "mflo " + resultRegister;
        }
        if(kindOfOp == Token.DIVIDE)
        {
            code += "div    " +leftRegister+
                    ",    "+rightRegister +"\n";
            code += "mflo "+ resultRegister;
        }
        return (code);
    }
}
