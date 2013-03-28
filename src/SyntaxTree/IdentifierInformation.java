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
 * @author Alfonso Vazquez
 */
public class IdentifierInformation
{

    public IdentifierInformation(String IDName)
    {
        this.IDName = IDName;
    }
    private Token Type;
    private Token Return;
    private int Size;
    private String IDName;

    /**
     * @return the Type
     */
    public Token getType()
    {
        return Type;
    }

    /**
     * @param Type the Type to set
     */
    public void setType(Token Type)
    {
        this.Type = Type;
    }

    /**
     * @return the Return
     */
    public Token getReturn()
    {
        return Return;
    }

    /**
     * @param Return the Return to set
     */
    public void setReturn(Token Return)
    {
        this.Return = Return;
    }

    /**
     * @return the Size
     */
    public int getSize()
    {
        return Size;
    }

    /**
     * @param Size the Size to set
     */
    public void setSize(int Size)
    {
        this.Size = Size;
    }

    /**
     * @return the IDName
     */
    public String getIDName()
    {
        return IDName;
    }

    /**
     * @param IDName the IDName to set
     */
    public void setIDName(String IDName)
    {
        this.IDName = IDName;
    }

    @Override
    public String toString()
    {
        return "IdentifierInfromation{" + "Type=" + Type + ", Return=" + Return + ", Size=" + Size + ", IDName=" + IDName + '}';
    }
}
