import java.util.Hashtable;
public class SymbolTable 
{
	protected final Hashtable<String,Token> symbolTable= new Hashtable<String, Token>();
	/*
	 * Transition Table used to call in the lexems and saved them for the parser
	 * 
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
		symbolTable.put("or", Token.OR);
		symbolTable.put("not", Token.NOT);
		symbolTable.put("mod", Token.MOD);
		symbolTable.put("and", Token.AND);
		symbolTable.put("real", Token.REAL);
		symbolTable.put("integer", Token.INTEGER);
		//symbolTable.put("id", Token.ID);
		symbolTable.put(";", Token.SEMI_COLON);
		symbolTable.put(".", Token.PERIOD);
		symbolTable.put(":=", Token.COLON_EQUALS);
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
		
	}
}
