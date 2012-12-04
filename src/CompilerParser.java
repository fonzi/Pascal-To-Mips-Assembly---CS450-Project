//package CompilerScanner;

import java.io.File;
import java.util.Hashtable;

/**
* A Parser that takes a read in file from CompilerScanner
* Uses the Production Rules from CSC 450/451
* This Parser will check to see if one of those rules from the 
* Grammar match the scanned file. 
*
* @ author Alfonso Vazquez
*/

public class CompilerParser
{	/**
	* This private Global variable is of Type CompilerScanner 
	*/
	private CompilerScanner scanner;
	/*
	* This private Global variable is of Type Token
	*/
    private Token currentToken;

	/**
	* CompilerParser method, initializes the Scanner, Hashtable for the symbolTable
	* Also calls the currentToken to get the token
	*
	* @param filename of type String to initialize the file input
	*/
    public CompilerParser(String filename)
    {
        File input = new File(filename);
        Hashtable symbols = new SymbolTable();
        scanner = new CompilerScanner(input, symbols);
        scanner.nextToken();
    	currentToken = scanner.getToken();
    }
    /**
	* Program method is the first call the file will make 
	* It will match three things and then call 
	* declarations > subprogram_declarations > compound_statement 
	* and then it would match again.
    */   
    public void program()
    {
        System.out.println("program");
        match(currentToken.PROGRAM);//match program
        match(currentToken.ID);//match id
        match(currentToken.SEMI_COLON);//match ;
        declarations();
        subprogram_declarations();
        compound_statement();
        match(currentToken.PERIOD);//match the .
    }
    /**
    * Identifier list matches an id or matches and id and calls 
    * identifier_list
    */
    public void identifier_list()
    {
		System.out.println("identifier_list");
		if(currentToken == Token.ID)
		{
			match(currentToken.ID);//match ID
		}
		else
		{
			match(currentToken.ID);//match ID
			identifier_list();
		}
	}
	/**
	* declarations matches var and calls
	* identifier_list > matches > type > matches > declarations
	*
	*/
	public void declarations()
	{
		System.out.println("declerations");
		match(currentToken.VAR);//match var
		identifier_list();
		match(currentToken.COLON);//match :
		type();
		match(currentToken.SEMI_COLON);//match ;
		declarations();
	}
	
	/**
	* type if token = array then 
	* match "Array" and "[" and "Number" and ":" and "Number" and "]" and "of"
	* then calls standard_type
	* or just matches standard_type
	*/
	public void type()
	{
		System.out.println("type");
		if(currentToken == Token.ARRAY)
		{
			match(currentToken.ARRAY);//match array
			match(currentToken.LEFT_SQUARE_BRACKET);//match [
			match(currentToken.NUMBER);//match num
			match(currentToken.COLON);//match :
			match(currentToken.NUMBER);//match num
			match(currentToken.RIGHT_SQUARE_BRACKET);//match ]
			match(currentToken.OF);//match of
			standard_type();
		}
		else
		{
			standard_type();
		}
	}
	
	/**
	* Standard_type matches either Number or Real only
	*/
	public void standard_type()
	{
		System.out.println("standard_type");
		if(currentToken == Token.NUMBER)
		{
			match(currentToken.NUMBER);//match num
		}
		else if(currentToken == Token.REAL)
		{
			match(currentToken.REAL);//match real
		}
	}
	
	/**
	* subprogram_declarations calls itself, then if currentToken is ";"
	* it calls itself again 
	*/
	public void subprogram_declarations()
	{
		System.out.println("subprogram_declarations");
		subprogram_declaration();
		if(currentToken == currentToken.SEMI_COLON)
		{
			subprogram_declarations();
		}
		else
			currentToken = null;//null == lambda
	}
	
	/**
	* subprogram_declaration calls 
	* subprogram_head > declarations > subprogram_declarations > compound_statement
	*/
	public void subprogram_declaration()
	{
		System.out.println("subprogram_declarations");
		subprogram_head();
		declarations();
		subprogram_declarations();
		compound_statement();
	}

	/**
	* subprogram_head matches and calls 
	* if currentToken is Function the 
	* match function and match id call arguments and match ":" and call standard_type
	*/
	public void subprogram_head()
	{
		System.out.println("subprogram_head");
		if(currentToken == Token.FUNCTION)
		{
			match(currentToken.FUNCTION);//match functions
			match(currentToken.ID);//match id
			arguments();
			match(currentToken.COLON);
			standard_type();
		}
		else if(currentToken == Token.PROCEDURE)
		{
			match(currentToken.PROCEDURE);//match procedure
			match(currentToken.ID);//match id
			arguments();
		}
	}
	
	/**
	* arguments matches if currentToken is "("
	* matches "(" calls parameter_list, match ")"
	* 
	*/
	public void arguments()
	{
		System.out.println("arguments");
		if(currentToken == Token.LEFT_PARENTHESIS)
		{
			match(currentToken.LEFT_PARENTHESIS);//match (
			parameter_list();
			match(currentToken.RIGHT_PARENTHESIS);//match )
		}
		else 
			currentToken = null;
	}
	
	/**
	* parameter_list calls identifier_list > matches colon > type 
	* or identifier_list > match ":" > type > match ";" > parameter_list  
	*/
	public void parameter_list()
	{
		System.out.println("parameter_list");
		if(currentToken == Token.ID)
		{
			identifier_list();
			match(currentToken.COLON);
			type();
		}
		else
		{
			if(currentToken = Token.ID)
			{
				identifier_list();
				match(currentToken.COLON);
				type();
				match(SEMI_COLON);
				parameter_list();
			}//end if
		}//end else
	} 
	
	/**
	* compound_statement matches begin and end with 
	* optional_statements in between been called
	*/
	public void compound_statement()
	{
		System.out.println("compound_statement");
		match(currentToken.BEGIN);
		optional_statements();
		match(currentToken.END);
	}
	
	/**
	* optional_statement calls statement_list if currentToken is and ID
	* or lambda
	*/
	public void optional_statements()
	{
		System.out.println("optional_statements");
		if(currentToken = Token.ID)
		{
			statement_list();
		}
	}
	/**
	* calls statement or calls statement matches ";" and calls statement_list
	*/
	public void statement_list()
	{
		System.out.println("statement_list");
		if(currentToken = Token.ID
		{
			statement();
		}
		else
		{
			statement();
			match(currentToken.SEMI_COLON)//match ;
			statement_list();
		}
	}
	
	/**
	* 
	*/
	public void statement()
	{
		System.out.println("statement");
		if(currentToken = Token.ID)
		{
			
		}
	}
	
	public void variable()
	{
		System.out.println("variable");
		if(currentToken == Token.ID)
		{
			match(currentToken.ID);//match id
		}
		else
		{
			match(currentToken.ID);
			match(currentToken.LEFT_SQUARE_BRACKET);//match [
			expression();
			match(currentToken.RIGHT_SQUARE_BRACKET);//match ]
		}
	}
	
	public void procedure_statement()
	{
		System.out.println("procedure_statement");
		if(currentToken == Token.ID)
		{
			match(currentToken.ID);//match id 
		}
		else
		{
			match(currentToken.ID);
			match(currentToken.LEFT_PARENTHESIS);//match (
			expression_list();
			match(currentToken.RIGHT_PARENTHESIS);//match )
		}
		
	}
	
	public void expression_list()
	{
		System.out.println("expression_list");
	}
	
	public void expression()
	{
		System.out.println("expression");
	}
	
	public void simple_expressions()
	{
		System.out.println("simple_expressions");
	}
	
	public void simple_part()
	{
		System.out.println("simple_part");
		if(currentToken == Token.PLUS  || currentToken == Token.MINUS 
				|| currentToken == Token.OR)
		{
			term();
			simple_part();
		}
		else 
		{
			currentToken = null;
		}
	}
	
	public void term()
	{
		System.out.println("term");
		factor();
		term_part();
				
	}
	
	public void term_part()
	{
		System.out.println("term_part");
		if(currentToken == Token.MULTIPLY || currentToken == Token.DIVIDE 
				|| currentToken == Token.MOD || currentToken == Token.AND)
		{
			factor();
			term_part();
		}
		else
		{
			currentToken = null;
		}
	}
	
	public void factor()
	{
		System.out.println("factor");
	}
	
	public void sign()
	{
		System.out.println("sign");
		if(currentToken == Token.PLUS)
		{
			match(currentToken.PLUS);
		}
		else if(currentToken == Token.MINUS)
		{
			match(currentToken.MINUS);
		}
	}
	
	public void match(Token expectedToken)
	{
		System.out.println("match");
		if(currentToken == expectedToken)
		{
			int scan = scanner.nextToken();
			switch(scan)
			{
				case CompilerScanner.IS_A_LEXEME:
				currentToken = scanner.getToken();
					break;
				case CompilerScanner.IS_NOT_A_LEXEME:
					break;
				case CompilerScanner.IS_AN_ERROR:
					break;
			}
		}
		else
		{
			error();
		}
	}//end match
	
	public void error()
	{
		System.out.println("error");    
		System.err.println("Parse error at line : " + scanner.getLineCounter());
		System.exit(1);
	}
}