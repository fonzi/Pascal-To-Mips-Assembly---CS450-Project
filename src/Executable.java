//package CompilerScanner;
import java.io.File;
/**
 * Executable is the main of the compiler
 * This is the class that initialez everything and takes in the argument
 * for the file location.
 * @author Alfonso Vazquez
 */
public class Executable 
{
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
            parse.program();
            
            //---------------------------------------------------------------//
            //                          This while loop is for               //
            //                          the scanner test                     //
            //---------------------------------------------------------------//
            
            /*
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
            */
            
	}//end main
}