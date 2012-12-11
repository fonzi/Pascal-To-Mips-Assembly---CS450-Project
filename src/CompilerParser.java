package CompilerScanner;

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
{

    /**
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
        
        match(currentToken.ID);
        if(currentToken==Token.COMMA)
        {
            match(currentToken.COMMA);//match 
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
        if (currentToken == Token.ARRAY)
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
        if (currentToken == Token.NUMBER)
        {
            match(currentToken.NUMBER);//match num
        }
        else if (currentToken == Token.REAL)
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
        if (currentToken == currentToken.SEMI_COLON)
        {
            match(currentToken.SEMI_COLON);//match ;
            subprogram_declarations();
        }
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
        if (currentToken == Token.FUNCTION)
        {
            match(currentToken.FUNCTION);//match functions
            match(currentToken.ID);//match id
            arguments();
            match(currentToken.COLON);
            standard_type();
        }
        else if (currentToken == Token.PROCEDURE)
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
        if (currentToken == Token.LEFT_PARENTHESIS)
        {
            match(currentToken.LEFT_PARENTHESIS);//match (
            parameter_list();
            match(currentToken.RIGHT_PARENTHESIS);//match )
        }
    }

    /**
     * parameter_list calls identifier_list > matches colon > type
     * or identifier_list > match ":" > type > match ";" > parameter_list
     */
    public void parameter_list()
    {
        System.out.println("parameter_list");
        identifier_list();
        match(currentToken.COLON);//match :
        type();
        if(currentToken == Token.SEMI_COLON)
        {
            match(currentToken.SEMI_COLON);//match ;
            parameter_list();
        }
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
        statement_list();
    }

    /**
     * calls statement or calls statement matches ";" and calls statement_list
     */
    public void statement_list()
    {
        statement();
        if(currentToken == Token.SEMI_COLON)
        {
            match(currentToken.SEMI_COLON);//match ;
            statement_list();
        }
    }

    /**
     *
     */
    public void statement()
    {
        if(currentToken == Token.ID)
        {
            if(currentToken == Token.LEFT_SQUARE_BRACKET)
            {
                variable();
                match(currentToken.COLON_EQUALS);//match := 
                expression();
            }//end checl [
            if(currentToken == Token.LEFT_PARENTHESIS)
            {
                procedure_statement();
            }//end check (
        }//end check ID
        if(currentToken == Token.BEGIN)
        {
            compound_statement();
        }//end checl begin
        if(currentToken == Token.IF)
        {
            match(currentToken.IF);//match IF
            expression();
            match(currentToken.THEN);//match then
            statement();
            match(currentToken.ELSE);//match else
            statement();
        }
        //read and write will be treated as Tokens.
        if(currentToken == Token.READ)
        {
            match(currentToken.READ);//match READ
        }
        if(currentToken == Token.WRITE)
        {
            match(currentToken.WRITE);//match write
        }
    }

    /**
     *
     */
    public void variable()
    {
        System.out.println("variable");
        match(currentToken.ID);//match id
        if(currentToken == Token.LEFT_SQUARE_BRACKET)
        {
            match(currentToken.LEFT_SQUARE_BRACKET);//match [
            expression();
            match(currentToken.RIGHT_SQUARE_BRACKET);//match ]
        }
    }

    /**
     *
     */
    public void procedure_statement()
    {
        System.out.println("procedure_statement");
        match(currentToken.ID);//match ID
        if(currentToken == Token.LEFT_PARENTHESIS)
        {
            match(currentToken.LEFT_PARENTHESIS);//match (
            expression_list();
            match(currentToken.RIGHT_PARENTHESIS);//match )
        }

    }

    /**
     *
     */
    public void expression_list()
    {
        System.out.println("expression_list");
        expression();
        if(currentToken == Token.COMMA)
        {
            match(currentToken.COMMA);//match ,
            expression_list();
        }
    }

    /**
     *
     */
    public void expression()
    {
        simple_expression();
        if(currentToken == Token.EQUAL || currentToken == Token.LESS_THAN || 
                currentToken == Token.GREATER_THAN || 
                currentToken == Token.GREATER_THAN_EQUAL
                ||currentToken == Token.LESS_THAN_EQUAL
                ||currentToken == Token.NOT_EQUAL)
        {
            match(currentToken);//match whatever the relop
            simple_expression();
        }
    }

    /**
     *
     */
    public void simple_expression()
    {
        System.out.println("simple_expressions");
        if(currentToken == Token.ID)
        {
            term();
            simple_part();
        }
        else if(currentToken == Token.PLUS || currentToken == Token.MINUS)
        {
          match(currentToken);//match  + or - 
          term();
          simple_part();
        }
    }

    /**
     *
     */
    public void simple_part()
    {
        System.out.println("simple_part");
        if(currentToken == Token.PLUS || currentToken == Token.MINUS 
                || currentToken == Token.OR)
        {
            match(currentToken);//match addop 
        }
    }

    /**
     *
     */
    public void term()
    {
        System.out.println("term");
        factor();
        term_part();
    }

    /**
     *
     */
    public void term_part()
    {
        System.out.println("term_part");
        if (currentToken == Token.MULTIPLY || currentToken == Token.DIVIDE
                || currentToken == Token.MOD || currentToken == Token.AND)
        {
            factor();
            term_part();
        }
    }

    /**
     *
     */
    public void factor()
    {
        System.out.println("factor");
        if(currentToken == Token.ID)
        {
            match(currentToken.ID);//match ID
            if(currentToken == Token.LEFT_SQUARE_BRACKET)
            {
                match(currentToken.LEFT_SQUARE_BRACKET);//match [
                expression();
                match(currentToken.RIGHT_SQUARE_BRACKET);//match ]
            }
            if(currentToken == Token.LEFT_PARENTHESIS)
            {
                match(currentToken.LEFT_PARENTHESIS);//match (
                expression_list();
                match(currentToken.RIGHT_PARENTHESIS);//match )
            }
            if(currentToken == Token.NUMBER)
            {
                match(currentToken.NUMBER);
            }
        }//end ID check
        if(currentToken == Token.NUMBER)
        {
            match(currentToken.NUMBER);//match number
        }//end number check
        if(currentToken == Token.LEFT_PARENTHESIS)
        {
            match(currentToken.LEFT_PARENTHESIS);//match (
            expression_list();
            match(currentToken.RIGHT_PARENTHESIS);//match )
        }//end ( check
        if(currentToken == Token.NOT)
        {
            match(currentToken.NOT);//match not
            factor();
        }//end not check
    }

    /**
     *
     */
    public void sign()
    {
        System.out.println("sign");
        if (currentToken == Token.PLUS)
        {
            match(currentToken.PLUS);
        }
        else if (currentToken == Token.MINUS)
        {
            match(currentToken.MINUS);
        }
    }

    /**
     *
     * @param expectedToken
     */
    public void match(Token expectedToken)
    {
        System.out.println("match");
        if (currentToken == expectedToken)
        {
            int scan = scanner.nextToken();
            switch (scan)
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

    /**
     *
     */
    public void error()
    {
        System.out.println("error");
        System.err.println("Parse error at line : " + scanner.getLineCounter());
        System.exit(1);
    }
}