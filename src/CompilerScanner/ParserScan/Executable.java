package CompilerScanner.ParserScan;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import CodeGeneration.CodeGenerator;
import CompilerScanner.SyntaxTree.Node;

/**
 * Executable is the main of the compiler This is the class that initialez
 * everything and takes in the argument for the file location.
 * 
 * @author Alfonso Vazquez
 */
public class Executable
{

	/**
	 * This is the main method, which will excecute all of the required classes
	 * and Packages for the compiler to be successful
	 * 
	 * @param args this keeps the file location for pas
	 */
	
	public static void main(String[] args)
	{
		String fileLocation, outFile;
		if (args.length < 2)
		{
			System.err.println("You are either missing the file locaton or out name");
		}
		if(args.length == 3)
		{
			System.out.println("devMode");
			try
			{
				Thread.sleep(100000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
			
		fileLocation = args[0];
		outFile = args[1];

		int i = 0;
		File file = new File(args[0]);
		String fileName = args[0];
		SymbolTable table = new SymbolTable();
		CompilerScanner scan = new CompilerScanner(file, table);
		CompilerParser parse = new CompilerParser(fileName);
		CodeGenerator code = new CodeGenerator();
		Node tree;
		// ---------------------------------------------------------------//
		// This while loop is for //
		// the scanner test //
		// ---------------------------------------------------------------//
		System.out.println("--------------------");
		System.out.println("THIS IS THE SCANNER");
		System.out.println("--------------------");
		while (i != 1)
		{
			i = scan.nextToken();
			if (scan.lexeme.length() != 0)
			{
				System.out.println("LEXEME: " + scan.getLexeme() + " TOKEN: ["
						+ scan.getToken() + "]");
			}
		}// end while
			// System.out.println(scan.getLineCounter());

		System.out.println("--------------------");
		System.out.println("THIS IS THE PARSER");
		System.out.println("--------------------");
		tree = parse.program();
		System.out.println("--------------------");
		System.out.println("THIS IS THE SYNTAX TREE");
		System.out.println("--------------------");
		System.out.println(tree.indentedToString(0));

		System.out.println("--------------------");
		System.out.println("THE HASH TABLE");
		System.out.println("--------------------");
		System.out.println(parse.IdTable);

		System.out.println("--------------------");
		System.out.println("THE MIPS CODE");
		System.out.println("--------------------");
		System.out.println(code.writeCodeForRoot(tree));

		try
		{
			FileWriter fstream = new FileWriter(outFile + ".s");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(code.writeCodeForRoot(tree));
			out.close();
			// close out stream
		} catch (IOException e)
		{
			System.err.println("Error" + e.getMessage());
		}

	}// end main
}