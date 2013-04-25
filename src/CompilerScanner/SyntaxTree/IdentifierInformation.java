/*
 * This class is to keep the second argument that the hashtable from VariableSymbolTable.java needs.
 * It contains: Type, return, Size for arrays; and IDName.
 * Type : Token
 * Return:Token
 * Size: Integer
 * IDName: String
 */
package CompilerScanner.SyntaxTree;

import CompilerScanner.ParserScan.Token;

/**
 * This is the identifier information, to check the size, type retuen and idname
 * @author Alfonso Vazquez
 */
public class IdentifierInformation
{

    /**
     * Constructor, keeps an IDName of type String
     * @param IDName
     */
    public IdentifierInformation(String IDName)
    {
        this.IDName = IDName;
    }
    private Token Type;
    private Token Return;
    private int Size;
    private String IDName;

    /**
     * Getter to get type
     * @return the Type
     */
    public Token getType()
    {
        return Type;
    }

    /**
     * Set the type of the token
     * @param Type the Type to set
     */
    public void setType(Token Type)
    {
        this.Type = Type;
    }

    /**
     * Return the Token 
     * @return the Return
     */
    public Token getReturn()
    {
        return Return;
    }

    /**
     * Set the return 
     * @param Return the Return to set
     */
    public void setReturn(Token Return)
    {
        this.Return = Return;
    }

    /**
     * Return the Size
     * @return the Size
     */
    public int getSize()
    {
        return Size;
    }


    /**
     * String get the name 
     * @return the IDName
     */
    public String getIDName()
    {
        return IDName;
    }

    /**
     * Return the Type 
     * @param IDName the IDName to set
     */
    public void setIDName(String IDName)
    {
        this.IDName = IDName;
    }

    /**
     * To string to print the table when needed. 
     * @return
     */
    @Override
    public String toString()
    {
    	String answer = "";
    	answer = String.format("%10s%10s%10d%10s",Type,Return, Size, IDName);
        return answer;
    }
}
