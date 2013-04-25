package CodeGeneration;

import CompilerScanner.ParserScan.Token;
import CompilerScanner.SyntaxTree.AssignmentStatement;
import CompilerScanner.SyntaxTree.IfStatement;
import CompilerScanner.SyntaxTree.Node;
import CompilerScanner.SyntaxTree.OperationsNode;
import CompilerScanner.SyntaxTree.WhileStatement;

/**
 * This class will create cde for the Syntax Tree 
 * @author Alfonso Vazquez
 */
public class CodeGenerator
{
    //the t register always starts at 0 
    //only 8 t registers
    private int currentTRegister = 0;
    
    /**
     * This method takes the input of the tree to start generating code
     * @param root
     * @return the actual code for MIPS emulator
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
     * This is to write the code if the node is an instance of a type of node
     * @param node
     * @param register
     * @return returns the instance of code
     */
    public String writeCode(Node node, String register)
    {
        String nodeCode = null;
        
        if (node instanceof OperationsNode)
        {
            nodeCode = writeCode((OperationsNode)node, register);
        }
        if (node instanceof AssignmentStatement)
        {
            nodeCode = writeCode((AssignmentStatement)node, register);
        }
        if (node instanceof IfStatement)
        {
            nodeCode = writeCode((IfStatement)node, register);
        }
        if (node instanceof WhileStatement)
        {
            nodeCode = writeCode((WhileStatement)node, register);
        }
        
        return(nodeCode);
    }
    /**
     * This is to write the code along with the T registers, from the operationNode and the resultRegister
     * This will keep the count of how many registers are needed from the hashtable 
     * @param opNode
     * @param resultRegister
     * @return returns the code along with the tregisters in order.
     */
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
            code += "mult    "+leftRegister+",    "+rightRegister + "\n";
            code += "mflo " + resultRegister;
        }
        if(kindOfOp == Token.DIVIDE)
        {
            code += "div    " +leftRegister+",    "+rightRegister +"\n";
            code += "mflo "+ resultRegister;
        }
        if(kindOfOp == Token.AND)
        {
            code += "and    "+leftRegister+",    "+rightRegister+"\n";
            code += "$d " + resultRegister;
        }
        if(kindOfOp == Token.OR)
        {
            code += "or    "+leftRegister+",    "+rightRegister+"\n";
            code += "$d " + resultRegister;
        }
        if(kindOfOp == Token.LESS_THAN)
        {
            code += "slt    "+leftRegister+",    "+rightRegister+"\n";
            code += "$d    "+resultRegister;
        }
        
        //this is wrong
        if(kindOfOp == Token.GREATER_THAN)
        {
            code += "bgt    "+leftRegister+",    "+rightRegister+"\n";
            code += "$s   "+resultRegister;
        }
        return (code);
    }
   
    /**
     * This should check if the node passed in is of type if 
     * @param ifNode
     * @param resultRegister
     * @return the if code for mips
     */
    public String writeCode(IfStatement ifNode, String resultRegister)
    {      
        String code = "";
        
        return (code);
    }
    
    /**
     * This should check if the node passed in is of type while
     * @param whileNode
     * @param resultRegister
     * @return the while code for mips
     */
    public String writeCode(WhileStatement whileNode, String resultRegister)
    {
        String code = "";
        return (code);
    }
}
