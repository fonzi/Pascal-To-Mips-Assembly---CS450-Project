/**
 * enum called token to list all the lexical 
 * units from the small pascal language
 * 
 * @author Alfonso Vazquez
 *
 */
package CompilerScanner;

public enum Token 
{
	
	
	//Reserved Words
	IF,
	THEN,
	ELSE,
	WHILE,
	DO,
	VAR,
	BEGIN,
	END,
	ARRAY,
	OF,
	FUNCTION,
	PROCEDURE,
	PROGRAM,
	OR,
	NOT,
	MOD,
	AND,
	REAL,
	INTEGER,
	ID,
	NUMBER,
        READ,
        WRITE,
		
	//Other units | Symbols.
	SEMI_COLON,
	PERIOD,
	COLON_EQUALS,
	LEFT_SQUARE_BRACKET,
	RIGHT_SQUARE_BRACKET,
	LEFT_PARENTHESIS,
	RIGHT_PARENTHESIS,
	PLUS,
	MINUS,
	MULTIPLY,
	DIVIDE,
	EQUAL,
	NOT_EQUAL,
	LESS_THAN,
	LESS_THAN_EQUAL,
	GREATER_THAN_EQUAL,
	GREATER_THAN,
	COLON,
	COMMA,
	
}
