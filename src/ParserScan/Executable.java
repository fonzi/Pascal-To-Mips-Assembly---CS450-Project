
package CompilerScanner.ParserScan;
import CompilerScanner.*;
import CompilerScanner.SyntaxTree.Node;
import java.io.File;
/**
 * Executable is the main of the compiler
 * This is the class that initialez everything and takes in the argument
 * for the file location.
 * @author Alfonso Vazquez
 */
public class Executable 
{
    /**
     * This is the main method, which will excecute all of the required classes
     * and Packages for the compiler to be succesful
     * @param args this keeps the file location for pas
     */
    public static void main (String [] args)
	{
            String fileLocation = args[0];
            if(fileLocation == null || args.length > 1)
            {
                System.err.println("Only The Name of the File Needed");
            }
            int i = 0;
            File file = new File(args[0]);
            String fileName = args[0];
            SymbolTable table = new SymbolTable();
            CompilerScanner scan = new CompilerScanner(file,table);
            CompilerParser parse = new CompilerParser(fileName);
            Node tree;
            //---------------------------------------------------------------//
            //                          This while loop is for               //
            //                          the scanner test                     //
            //---------------------------------------------------------------//
            System.out.println("--------------------");            
            System.out.println("THIS IS THE SCANNER");
            System.out.println("--------------------");            
            while (i != 1)
            {
                i = scan.nextToken();
                if(scan.lexeme.length() != 0)
                {
                    System.out.println("LEXEME: "+scan.getLexeme() 
                            +" TOKEN: ["+ scan.getToken()+ "]");
                }
            }//end while
            //System.out.println(scan.getLineCounter());
            
            
            System.out.println("--------------------");            
            System.out.println("THIS IS THE PARSER");
            System.out.println("--------------------"); 
            tree = parse.program();
            System.out.println("--------------------");
            System.out.println("THIS IS THE SYNTAX TREE");
            System.out.println("--------------------");
            System.out.println(tree.indentedToString(0));
            
            
	}//end main
}