package CompilerScanner.ParserScan;

import java.io.File;
import java.util.Hashtable;
import CompilerScanner.SyntaxTree.*;
import java.util.ArrayList;

//do what veerav thaught me with subprogram declarations and compound statements. 

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
    /**This private Global Variable is to keep the VariableSymbolTable**/
    private VariableSymbolTable IdTable;
    /**This private Global Variable is to keep the IdentifierInfromation for the second argument of IdTable**/
    private IdentifierInformation secondArg;

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
        Hashtable symbols = new SymbolTable();//need to use as token identifier
        scanner = new CompilerScanner(input, symbols);
        scanner.nextToken();
        currentToken = scanner.getToken();
    }

    /**
     * Program method is the first call the file will make
     * It will match three things and then call
     * declarations > subprogram_declarations > compound_statement
     * and then it would match again.
     * @return 
     */
    public Node program()
    {
        Node answer;
        System.out.println("program");
        match(currentToken.PROGRAM);//match program
        answer = new ProgramNode(currentToken);
        match(currentToken.ID);//match id
        match(currentToken.SEMI_COLON);//match ;
        Node right = declarations();
        subprogram_declarations();
        compound_statement();
        match(currentToken.PERIOD);//match the .
        System.out.println("CODE DONE :)");
        ((ProgramNode)answer).setDeclarationsNode(right);
        return(answer);
    }

    /**
     * Identifier list matches an id or matches and id and calls
     * identifier_list
     * @return 
     */
    public ArrayList identifier_list()
    {
        System.out.println("identifier_list");
        ArrayList answer = new ArrayList();
        answer.add(scanner.getLexeme());
        secondArg = new IdentifierInformation(scanner.getLexeme());
        IdTable = new VariableSymbolTable();
        IdTable.put(scanner.getLexeme(), secondArg);
        match(currentToken.ID);//match ID
        
        while (currentToken == Token.COMMA)
        {
            match(currentToken.COMMA);//match ,
            answer.add(scanner.getLexeme());
            secondArg = new IdentifierInformation(scanner.getLexeme());
            IdTable.put(scanner.getLexeme(), secondArg);
            match(currentToken.ID);//match ID
        }
        return(answer);
    }

    /**
     * declarations matches var and calls
     * 
     * identifier_list > matches > type > matches > declarations
     * @return 
     */
    public Node declarations()
    {
        System.out.println("declerations");
        Node answer=null;
        
        if (currentToken == Token.VAR)
        {
            answer = new DeclarationsNode();
            match(currentToken.VAR);//match var
            ((DeclarationsNode)answer).setList(identifier_list());
            match(currentToken.COLON);//match :
            
            type();
            
            match(currentToken.SEMI_COLON);//match ;
            ((DeclarationsNode)answer).setRight(declarations());
        }
        return(answer);
    }
    
    public void addToHash(Token id, ArrayList list, VariableSymbolTable ids)
    {
        //This for loop is going trough the list To set the variable type for each 
        //variable in the symbol table. 
       for(int i = 0; i < list.size(); i ++)
       {
           IdentifierInformation idsInfo;
           String name = (String) list.get(i);
           idsInfo = (IdentifierInformation)ids.get(name);
           idsInfo.setType(id);
       }
    }

    /**
     * type if token = array then
     * match "Array" and "[" and "Number" and ":" and "Number" and "]" and "of"
     * then calls standard_type
     * or just matches standard_type
     */
    public Token type()
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
        }
        return standard_type();        
    }

    /** Standard_type matches either Number or Real only*/
    public Token standard_type()
    {
        Token token = null;
        
        System.out.println("standard_type");
        
        if (currentToken == Token.INTEGER)
        {
            token = currentToken;
            match(currentToken.INTEGER);//match num
        }
        
        else if (currentToken == Token.REAL)
        {
            token = currentToken;
            match(currentToken.REAL);//match real
        }
        
        else
        {
            error();
        }
        
        return token;
    }

    /**
     * subprogram_declarations calls itself, then if currentToken is ";"
     * it calls itself again
     * @return 
     */
    public Node subprogram_declarations()
    {
    	Node answer = null;
        System.out.println("subprogram_declarations");
        if (currentToken == Token.FUNCTION || currentToken == Token.PROCEDURE)
        {
            subprogram_declarations();
            match(currentToken.SEMI_COLON);//match ;
            answer = new SubprogramNode();
            subprogram_declarations();
        }
    	return answer;
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
        {
            error();
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
     * @return 
     */
    public Node compound_statement()
    {
    	Node answer = null;
        System.out.println("compound_statement");
        answer = new CompoundStatementNode();
        match(currentToken.BEGIN);//match begin
        optional_statements();
        match(currentToken.END);//match end
        return answer;
    }

    /**
     * optional_statement calls statement_list if currentToken is and ID
     * or lambda
     * @return 
     */
    public ArrayList optional_statements()
    {
        System.out.println("optional_statements");
        
        if (currentToken == Token.ID
                || currentToken == Token.BEGIN
                || currentToken == Token.IF
                || currentToken == Token.WHILE
                || currentToken == Token.READ
                || currentToken == Token.WRITE)
        {
            return statement_list();
        }
        
        else
        {
            return null;
        }
    }

    /**
     * calls statement or calls statement matches ";" and calls statement_list
     * @return 
     */
    public ArrayList statement_list()
    {
        ArrayList statements = new ArrayList();
        
        System.out.println("statement_list");
        statements.add(statement());
        
        if (currentToken == Token.SEMI_COLON)
        {
            match(currentToken.SEMI_COLON);//match ;
            statement_list();
        }
        return statements;
    }

    /**
     * Statement first calls variable then matches assingop followed by a call
     * expression or calls procedure_statement or compound_statement or match 
     * if followed by expression matched by then followed by statement and match
     * else and call statement again. Or match while and call expression and 
     * then match do and call statement. Or treat read as a token so call read. 
     * Or treat write as token so call write. 
     * @return 
     */
    public Node statement()
    {
        Node answer = null;
        System.out.println("statement");
        if (currentToken == Token.ID)
        {
            answer = new AssignmentStatement();
            match(currentToken.ID);//match ID
            if (currentToken == Token.LEFT_SQUARE_BRACKET)
            {
                ((AssignmentStatement)answer).setLeft(variable());
                match(currentToken.LEFT_SQUARE_BRACKET);//match := 
                ((AssignmentStatement)answer).setRight(expression());
            }//end checl [
            else if (currentToken == Token.COLON_EQUALS)
            {
                ((AssignmentStatement)answer).setLeft(variable());
                match(currentToken.COLON_EQUALS);//match := 
                ((AssignmentStatement)answer).setRight(expression());
            }
            else if (currentToken == Token.LEFT_PARENTHESIS)
            {
                //add answer later
                procedure_statement();
            }//end check (
        }//end check ID
        else if (currentToken == Token.BEGIN)
        {
            answer = compound_statement();
        }//end checl begin
        else if (currentToken == Token.IF)
        {
            answer = new IfStatement();
            match(currentToken.IF);//match IF
            ((IfStatement)answer).setExpression(expression());
            match(currentToken.THEN);//match then
            ((IfStatement)answer).setStatement_1(statement());
            match(currentToken.ELSE);//match else
            ((IfStatement)answer).setStatement_2(statement());
        }
        else if (currentToken == Token.WHILE)
        {
            answer = new WhileStatement();
            match(currentToken.WHILE);//match IF
            ((WhileStatement)answer).setExpression(expression());
            match(currentToken.DO);//match then
            ((WhileStatement)answer).setStatement(statement());
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
        return answer;
    }

    /**
     * Variable first calls ID then checks if [ follows
     * if it does then it calls expression if not then it errors out
     */
    public Node variable()
    {
        Node answer = null;
        answer = new ValueNode(scanner.getLexeme());
        System.out.println("variable");
        //match(currentToken.ID);//match id
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
        return answer;
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
     * @return 
     */
    public Node expression()
    {
        Node answer = null;
        Node exp = null;
        System.out.println("expression");
        answer = simple_expression();
        if (currentToken == Token.EQUAL)
        {
            exp = answer;
            answer = new Expression(currentToken);
            match(currentToken.EQUAL);//match whatever the relop
            ((Expression)answer).setLeft(exp);
            ((Expression)answer).setRight(simple_expression());
        }
        else if (currentToken == Token.LESS_THAN)
        {
            exp = answer;
            answer = new Expression(currentToken);
            match(currentToken.LESS_THAN);//match less than
            ((Expression)answer).setLeft(exp);
            ((Expression)answer).setRight(simple_expression());
        }
        else if (currentToken == Token.GREATER_THAN)
        {
            exp = answer;
            answer = new Expression(currentToken);
            match(currentToken.GREATER_THAN);//match >
            ((Expression)answer).setLeft(exp);
            ((Expression)answer).setRight(simple_expression());
        }
        else if (currentToken == Token.GREATER_THAN_EQUAL)
        {
            exp = answer;
            answer = new Expression(currentToken);
            match(currentToken.GREATER_THAN_EQUAL);//match >=
            ((Expression)answer).setLeft(exp);
            ((Expression)answer).setRight(simple_expression());
        }
        else if (currentToken == Token.LESS_THAN_EQUAL)
        {
            exp = answer;
            answer = new Expression(currentToken);
            match(currentToken.LESS_THAN_EQUAL);
            ((Expression)answer).setLeft(exp);
            ((Expression)answer).setRight(simple_expression());
        }
        else if (currentToken == Token.NOT_EQUAL)
        {
            exp = answer;
            answer = new Expression(currentToken);
            match(currentToken.NOT_EQUAL);
            ((Expression)answer).setLeft(exp);
            ((Expression)answer).setRight(simple_expression());
        }
        return answer;
    }

    /**
     * Simple expression checks if ID since it looks ahead 
     * is so calls term and simple part 
     * If it matches as a + or - then if matches 
     * and calls term and simpler_part
     * @return 
     */
    public Node simple_expression()
    {
        Node answer = null;
        Node sign = null;
        System.out.println("simple_expressions");
        if (currentToken == Token.PLUS || currentToken == Token.MINUS)
        {
            sign();
        }
        answer = term();
        simple_part();
        return answer;
    }

    /**
     * Simple part checks if the curent token is a "+" , "-" , or "or" 
     * if that is true then nothing 
     * @return 
     */
    public Node simple_part()
    {
        Node answer  = null;
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
        return answer;
    }

    /**
     * Term only calls factor and term_part
     * @return 
     */
    public Node term()
    {
        Node answer = null;
        System.out.println("term");
        factor();
        term_part();
        return answer;
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
        else if (currentToken == Token.DIVIDE)
        {
            match(currentToken.DIVIDE);//match /
            factor();
            term_part();
        }
        else if (currentToken == Token.MOD)
        {
            match(currentToken.MOD);//match mod
            factor();
            term_part();
        }
        else if (currentToken == Token.AND)
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