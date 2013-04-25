package CompilerScanner.SyntaxTree;

import java.util.Enumeration;
import java.util.Hashtable;
import CompilerScanner.ParserScan.CompilerScanner;

/**
 * This class is to keep the Variables in the hashtable form the parser, The
 * code generation should look in here to see what the register should be .
 * 
 * @author Alfonso Vazquez
 */
public class VariableSymbolTable
{

	/**
	 * Creates the scanner
	 */
	private CompilerScanner scanner;
	/**
	 * Creates the Hashtable
	 */
	private Hashtable variableTable = new Hashtable();
	/**
	 * This method is to add to the Hashtable. It takes two inputs one lexeme, and one id.
	 * @param lexeme is created from the scanner
	 * @param id is created from the SyntaxTree
	 */
	public void add(String lexeme, IdentifierInformation id)
	{
		getVariableTable().put(lexeme, id);
	}

	/**
	 * Gets the Hashtable. 
	 * @return runs the 
	 */
	public Hashtable getVariableTable()
	{
		return variableTable;
	}
	
	public void setVariableTable(Hashtable variableTable)
	{
		this.variableTable = variableTable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	/**
	 * toString to print the hashtable as type enumartion. 
	 */
	@Override
	public String toString()
	{
		Enumeration items = getVariableTable().keys();
		String keyChain = null;
		String answer = "      Type ,    Return ,    Size ,   IDName\n";
		while (items.hasMoreElements())
		{
			keyChain = (String) items.nextElement();
			answer += getVariableTable().get(keyChain) + "\n";
		}
		return answer;
	}
}
