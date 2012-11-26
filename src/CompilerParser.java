package CompilerScanner;

public class CompilerParser
{
	private CompilerScanner scanner;
	private Token currentToken;

	public void program()
	{

            match(currentToken.PROGRAM);//match program
            match(currentToken.ID);//match id
            match(currentToken.SEMI_COLON);//match ;
            declarations();
            subprogram_declarations();
            compound_declarations();
            match(currentToken.PERIOD);//match the .

	}
        public void identifier_list()
	{
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
	public void declarations()
	{
            match(currentToken.VAR);//match var
            identifier_list();
            match(currentToken.COLON);//match :
            type();
            match(currentToken.SEMI_COLON);//match ;
            declarations();
	}
        public void type()
        {
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
        public void standard_type()
        {
            if(currentToken == Token.NUMBER)
            {
                match(currentToken.NUMBER);//match num
            }
            else if(currentToken == Token.REAL)
            {
                match(currentToken.REAL);//match real
            }
        }
	public void subprogram_declarations()
	{
            if(currentToken != null)
            {
                subprogram_declaration();
                subprogram_declarations();
            }
            else
                currentToken = null;//null == lambda
	}
        public void subprogram_declaration()
        {
            subprogram_head();
            declarations();
            subprogram_declarations();
            compound_statement();
        }
	public void compound_declarations()
	{

	}
        public void subprogram_head()
        {
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
        public void arguments()
        {
            if(currentToken == Token.LEFT_PARENTHESIS)
            {
                match(currentToken.LEFT_PARENTHESIS);//match (
                parameter_list();
                match(currentToken.RIGHT_PARENTHESIS);//match )
            }
            else 
                currentToken = null;
        }
        public void parameter_list()
        {
            
        }
        public void compound_statement()
        {
            match(currentToken.BEGIN);
            optional_statements();
            match(currentToken.END);
        }
        public void optional_statements()
        {
            if(currentToken != null)
            {
                statement_list();
            }
            else
                currentToken = null;
        }
        public void statement_list()
        {
            
        }
        public void statement()
        {

        }
        public void variable()
        {
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

        }
        public void expression()
        {

        }
        public void simple_expressions()
        {

        }
        public void simple_part()
        {
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
            factor();
            term_part();
                    
        }
        public void term_part()
        {
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

        }
        public void sign()
        {
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
		System.err.println("Parse error at line : " + scanner.getLineCounter());
		System.exit(1);
	}
}