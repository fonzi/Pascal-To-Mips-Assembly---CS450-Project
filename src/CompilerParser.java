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

    /** This private Global variable is of Type CompilerScanner*/
    private CompilerScanner scanner;
    /** This private Global variable is of Type Token*/
    private Token currentToken;

    /**
     * CompilerParser method, initializes the Scanner, Hashtable for the 
     * symbolTable.
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
        //if (currentToken == Token.PROGRAM)
        //{
        match(currentToken.PROGRAM);//match program
        match(currentToken.ID);//match id
        match(currentToken.SEMI_COLON);//match ;
        declarations();
        subprogram_declarations();
        compound_statement();
        match(currentToken.PERIOD);//match the .
        System.out.println("CODE DONE :)");
        //}
        //else
        //{
           // error();
        //}
    }

    /**
     * Identifier list matches an id or matches and id and calls
     * identifier_list
     */
    public void identifier_list()
    {
        System.out.println("identifier_list");
        match(currentToken.ID);//match ID
        if (currentToken == Token.COMMA)
        {
            match(currentToken.COMMA);//match ,
            identifier_list();
        }
    }

    /**
     * declarations matches var and calls
     * identifier_list > matches > type > matches > declarations
     */
    public void declarations()
    {
        System.out.println("declerations");
        if (currentToken == Token.VAR)
        {
            match(currentToken.VAR);//match var
            identifier_list();
            match(currentToken.COLON);//match :
            type();
            match(currentToken.SEMI_COLON);//match ;
            declarations();
        }
        else
        {
            return;
        }

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
        standard_type();
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
    }

    /** Standard_type matches either Number or Real only*/
    public void standard_type()
    {
        System.out.println("standard_type");
        if (currentToken == Token.INTEGER)
        {
            match(currentToken.INTEGER);//match num
        }
        else if (currentToken == Token.REAL)
        {
            match(currentToken.REAL);//match real
        }
        else
            error();
    }

    /**
     * subprogram_declarations calls itself, then if currentToken is ";"
     * it calls itself again
     */
    public void subprogram_declarations()
    {
        System.out.println("subprogram_declarations");
        if(currentToken == Token.FUNCTION || currentToken == Token.PROCEDURE)
        {
            subprogram_declarations();
            match(currentToken.SEMI_COLON);//match ;
            subprogram_declarations();
        }
        else 
            return;
    }

    /**
     * subprogram_declaration calls
     * subprogram_head > declarations > subprogram_declarations 
     * > compound_statement
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
     * match function and match id call arguments and match ":" 
     * and call standard_type
     */
    public void subprogram_head()
    {
        System.out.println("subprogram_head");
        if (currentToken == Token.FUNCTION)
        {
            match(currentToken.FUNCTION);//match functions
            match(currentToken.ID);//match id
            arguments();
            match(currentToken.COLON);//match ;
            standard_type();
            match(currentToken.SEMI_COLON);//match ;
        }
        else if (currentToken == Token.PROCEDURE)
        {
            match(currentToken.PROCEDURE);//match procedure
            match(currentToken.ID);//match id
            arguments();
            match(currentToken.SEMI_COLON);//match ;
        }
        else
            error();
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
        else
        {
            return;
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
        if (currentToken == Token.SEMI_COLON)
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
        match(currentToken.BEGIN);//match begin
        optional_statements();
        match(currentToken.END);//match end
    }

    /**
     * optional_statement calls statement_list if currentToken is and ID
     * or lambda
     */
    public void optional_statements()
    {
        System.out.println("optional_statements");
        if (currentToken == Token.ID
                || currentToken == Token.BEGIN
                || currentToken == Token.IF
                || currentToken == Token.WHILE
                || currentToken == Token.READ
                || currentToken == Token.WRITE)
        {
            statement_list();
        }
        else
        {
            return;
        }
    }

    /**
     * calls statement or calls statement matches ";" and calls statement_list
     */
    public void statement_list()
    {
        System.out.println("statement_list");
        statement();
        if (currentToken == Token.SEMI_COLON)
        {
            match(currentToken.SEMI_COLON);//match ;
            statement_list();
        }
    }

    /**
     * Statement first calls variable then matches assingop followed by a call
     * expression or calls procedure_statement or compound_statement or match 
     * if followed by expression matched by then followed by statement and match
     * else and call statement again. Or match while and call expression and 
     * then match do and call statement. Or treat read as a token so call read. 
     * Or treat write as token so call write. 
     */
    public void statement()
    {
        System.out.println("statement");
        if (currentToken == Token.ID)
        {
            match(currentToken.ID);//match ID
            if (currentToken == Token.LEFT_SQUARE_BRACKET)
            {
                variable();
                match(currentToken.COLON_EQUALS);//match := 
                expression();
            }//end checl [
            else if(currentToken == Token.COLON_EQUALS)
            {
                match(currentToken.COLON_EQUALS);//match :=
                expression();
            }
            else if (currentToken == Token.LEFT_PARENTHESIS)
            {
                procedure_statement();
            }//end check (
        }//end check ID
        else if (currentToken == Token.BEGIN)
        {
            compound_statement();
        }//end checl begin
        else if (currentToken == Token.IF)
        {
            match(currentToken.IF);//match IF
            expression();
            match(currentToken.THEN);//match then
            statement();
            match(currentToken.ELSE);//match else
            statement();
        }
        else if(currentToken == Token.WHILE)
        {
            match(currentToken.WHILE);//match IF
            expression();
            match(currentToken.DO);//match then
            statement();
        }
        //----------------------------------------//
        //read and write will be treated as Tokens//
        //----------------------------------------//
        else if (currentToken == Token.READ)
        {     
            match(currentToken.READ);//match READ
            match(currentToken.LEFT_PARENTHESIS);
            match(currentToken.ID);//
            match(currentToken.RIGHT_PARENTHESIS);
        }
        else if (currentToken == Token.WRITE)
        {
            match(currentToken.WRITE);//match write
            match(currentToken.LEFT_PARENTHESIS);
            expression();
            match(currentToken.RIGHT_PARENTHESIS);
        }
    }

    /**
     * Variable first calls ID then checks if [ follows
     * if it does then it calls expression if not then it errors out
     */
    public void variable()
    {
        System.out.println("variable");
        match(currentToken.ID);//match id
        if (currentToken == Token.LEFT_SQUARE_BRACKET)
        {
            match(currentToken.LEFT_SQUARE_BRACKET);//match [
            expression();
            match(currentToken.RIGHT_SQUARE_BRACKET);//match ]
        }
        else
        {
            error();
        }
    }

    /**
     * Procedure Statement matches ID first. Then it checks to see if a "(" 
     * follows if it does then match "(" and call expression list and match ")"
     * if it does not follow then just error
     */
    public void procedure_statement()
    {
        System.out.println("procedure_statement");
        match(currentToken.ID);//match ID
        if (currentToken == Token.LEFT_PARENTHESIS)
        {
            match(currentToken.LEFT_PARENTHESIS);//match (
            expression_list();
            match(currentToken.RIGHT_PARENTHESIS);//match )
        }
        else
        {
            error();
        }
    }

    /**
     * Expression List calls expression then checks if a comma follows
     * if a comma does follow then it calls expression_list otherwise error
     */
    public void expression_list()
    {
        System.out.println("expression_list");
        expression();
        if (currentToken == Token.COMMA)
        {
            match(currentToken.COMMA);//match ,
            expression_list();
        }
    }

    /**
     * Expression calls simple_expression first then the look ahead
     * checks if the token an is =, < , > , >=, <=, or <>, if yes 
     * then tou match whatever it is. 
     * then call simple_expression.
     */
    public void expression()
    {
        System.out.println("expression");
        simple_expression();
        if (currentToken == Token.EQUAL)
        {
            match(currentToken.EQUAL);//match whatever the relop
            simple_expression();
        }
        else if(currentToken == Token.LESS_THAN)
        {
            match(currentToken.LESS_THAN);//match less than
            simple_expression();
        }
        else if(currentToken == Token.GREATER_THAN)
        {
            match(currentToken.GREATER_THAN);//match >
            simple_expression();
        }
        else if(currentToken == Token.GREATER_THAN_EQUAL)
        {
            match(currentToken.GREATER_THAN_EQUAL);//match >=
            simple_expression();
        }
        else if(currentToken == Token.LESS_THAN_EQUAL)
        {
            match(currentToken.LESS_THAN_EQUAL);
            simple_expression();
        }
        else if(currentToken == Token.NOT_EQUAL)
        {
            match(currentToken.NOT_EQUAL);
            simple_expression();
        }
    }

    /**
     * Simple expression checks if ID since it looks ahead 
     * is so calls term and simple part 
     * If it matches as a + or - then if matches 
     * and calls term and simpler_part
     */
    public void simple_expression()
    {
        System.out.println("simple_expressions");
        if ( currentToken == Token.PLUS|| currentToken == Token.MINUS )
        {
            sign();
        }
        term();
        simple_part();
    }

    /**
     * Simple part checks if the curent token is a "+" , "-" , or "or" 
     * if that is true then nothing 
     */
    public void simple_part()
    {
        System.out.println("simple_part");
        if (currentToken == Token.PLUS)
        {
            match(currentToken.PLUS);//match addop
            term();
            simple_part();
        }
        else if (currentToken == Token.MINUS)
        {
            match(currentToken.MINUS);//match minus
            term();
            simple_part();
        }
        else if (currentToken == Token.OR)
        {
            match(currentToken.OR);//match OR
            term();
            simple_part();
        }
        else
        {
            return;
        }
    }

    /**
     * Term only calls factor and term_part
     */
    public void term()
    {
        System.out.println("term");
        factor();
        term_part();
    }

    /**
     * Term part checks if currentToken is "*", "/" , "mod", or "and".
     * If so then it calls factor and term_part else just error 
     */
    public void term_part()
    {
        System.out.println("term_part");
        if (currentToken == Token.MULTIPLY)
        {
            match(currentToken.MULTIPLY);//match *
            factor();
            term_part();
        }
        else if(currentToken == Token.DIVIDE)
        {
            match(currentToken.DIVIDE);//match /
            factor();
            term_part();
        }
        else if(currentToken == Token.MOD)
        {
            match(currentToken.MOD);//match mod
            factor();
            term_part();
        }
        else if(currentToken == Token.AND)
        { 
            match(currentToken.AND);//match and
            factor();
            term_part();
        }
        else
        {
            return;
        }
    }

    /**
     * Factor checks if currentToken is ID if it is id the it matches ID 
     * then more checks happen 
     * checks if token = [
     * chekcs if token = (
     * checks if token = number
     * if yes then just match and call expresssion
     */
    public void factor()
    {
        System.out.println("factor");
        if (currentToken == Token.ID)
        {
            match(currentToken.ID);//match ID
            if (currentToken == Token.LEFT_SQUARE_BRACKET)
            {
                match(currentToken.LEFT_SQUARE_BRACKET);//match [
                expression();
                match(currentToken.RIGHT_SQUARE_BRACKET);//match ]
            }
            else if (currentToken == Token.LEFT_PARENTHESIS)
            {
                match(currentToken.LEFT_PARENTHESIS);//match (
                expression_list();
                match(currentToken.RIGHT_PARENTHESIS);//match )
            }
            else if (currentToken == Token.NUMBER)
            {
                match(currentToken.NUMBER);
            }
        }//end ID check
        else if (currentToken == Token.NUMBER)
        {
            match(currentToken.NUMBER);//match number
        }//end number check
        else if (currentToken == Token.LEFT_PARENTHESIS)
        {
            match(currentToken.LEFT_PARENTHESIS);//match (
            expression_list();
            match(currentToken.RIGHT_PARENTHESIS);//match )
        }//end ( check
        else if (currentToken == Token.NOT)
        {
            match(currentToken.NOT);//match not
            factor();
        }//end not check
        else
        {
            error();
        }
    }

    /**
     * Checks if + or - 
     * If yes then match correspondinly 
     * else is error
     */
    public void sign()
    {
        System.out.println("sign");
        if (currentToken == Token.PLUS)
        {
            match(currentToken.PLUS);//match + 
        }
        else if (currentToken == Token.MINUS)
        {
            match(currentToken.MINUS);//match - 
        }
        else
        {
            error();
        }
    }

    /**
     * Match bases the answer from the scanner variables
     * is a lexeme
     * is not a lexeme
     * is and error 
     * @param expectedToken : is of type token 
     */
    public void match(Token expectedToken)
    {
        System.out.println("match : [" + currentToken + "]");
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

    /** Error only exits the parser to avoid careless coding. */
    public void error()
    {
        //-------------------------------------//
        //The Line counter needs implementation//
        //-------------------------------------//


        System.out.println("error");
        //The line counter was deployed wrong on the scanner need to fix
        //System.err.println("Parse error at line : " + scanner.getLineCounter());


        System.exit(1);
    }
}