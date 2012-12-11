package CompilerScanner;

import java.util.Hashtable;
/**
 * @author Fonzi
 *
 */

public class SymbolTable extends Hashtable
{
	protected final Hashtable<String,Token> symbolTable= 
                new Hashtable<String, Token>();
	/**
	 * no arguments are taken
	 * hashTable format to put language keywords and enum decelerations.
	 */
	public SymbolTable()
	{
		symbolTable.put("if", Token.IF);
		symbolTable.put("then", Token.THEN);
		symbolTable.put("else", Token.ELSE);
		symbolTable.put("while", Token.WHILE);
		symbolTable.put("do", Token.DO);
		symbolTable.put("var", Token.VAR);
		symbolTable.put("begin", Token.BEGIN);
		symbolTable.put("end", Token.END);
		symbolTable.put("array", Token.ARRAY);
		symbolTable.put("of", Token.OF);
		symbolTable.put("function", Token.FUNCTION);
		symbolTable.put("procedure", Token.PROCEDURE);
		symbolTable.put("program", Token.PROGRAM);
		symbolTable.put("or", Token.OR);
		symbolTable.put("not", Token.NOT);
		symbolTable.put("mod", Token.MOD);
		symbolTable.put("and", Token.AND);
		symbolTable.put("real", Token.REAL);
		symbolTable.put("integer", Token.INTEGER);
                symbolTable.put("read", Token.READ);
                symbolTable.put("write", Token.WRITE);
		//symbolTable.put("id", Token.ID);
		
		symbolTable.put(";", Token.SEMI_COLON);
		symbolTable.put(".", Token.PERIOD);
		symbolTable.put(":=", Token.COLON_EQUALS);//assingop on grammar 
		symbolTable.put("[", Token.LEFT_SQUARE_BRACKET);
		symbolTable.put("]", Token.RIGHT_SQUARE_BRACKET);
		symbolTable.put("(", Token.LEFT_PARENTHESIS);
		symbolTable.put(")", Token.RIGHT_PARENTHESIS);
		symbolTable.put("+", Token.PLUS);
		symbolTable.put("-", Token.MINUS);
		symbolTable.put("*", Token.MULTIPLY);
		symbolTable.put("/", Token.DIVIDE);
		symbolTable.put("=", Token.EQUAL);
		symbolTable.put("<>", Token.NOT_EQUAL);
		symbolTable.put("<", Token.LESS_THAN);
		symbolTable.put("<=", Token.LESS_THAN_EQUAL);
		symbolTable.put(">", Token.GREATER_THAN);
		symbolTable.put(">=", Token.GREATER_THAN_EQUAL);
		symbolTable.put(":", Token.COLON);
		symbolTable.put(",", Token.COMMA);
		
	}
}
