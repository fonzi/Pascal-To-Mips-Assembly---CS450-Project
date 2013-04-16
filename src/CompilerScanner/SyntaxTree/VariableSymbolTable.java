package CompilerScanner.SyntaxTree;

import CompilerScanner.ParserScan.CompilerScanner;
import java.util.*;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fonzi
 */
public class VariableSymbolTable 
{
    
    private CompilerScanner scanner;
    
    public Hashtable variableTable = new Hashtable(); 
    
    public void add(String lexeme, IdentifierInformation id)
    {
        variableTable.put(lexeme, id);
    }

    Enumeration items = variableTable.keys();
    
    @Override
    public String toString()
    { 
        String keyChain = null; 
        while(items.hasMoreElements())
        {
            keyChain = (String)items.nextElement();
            variableTable.put(keyChain, new Integer(1));
            System.out.println(variableTable.get(keyChain));
        }
        return "";
    }
    
}
