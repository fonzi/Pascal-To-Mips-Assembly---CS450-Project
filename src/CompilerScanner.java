package CompilerScanner;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.LineNumberReader;
import java.io.PushbackReader;
import java.util.Hashtable;

/**
 * @author Alfonso Vazquez
 * Scanner for Compiler
 * transitionTable: is of type 2d array or matrix contains 
 * all the state transitions from the automita
 * compilerScanner: is the constuctor of the class containing file and table
 * getLexeme: is the getter for the scanner to call the lexeme
 * nextToken: keeps track of the tokens and states the scanner 
 * is transitioning through
 * pushBack: is used to read in and push back to the input stream
 * main: is used to test the constructor along with nextToken.
 *
 */
public class CompilerScanner
{
    /*
     * The following four lines are error exceptions declared 
     * in a simplified form. 
     */

    static final String ERROR_CONSOLE = "The File does not exist or is "
            + "incorrect format";
    static final String UNKOWN_TOKEN = "The token is not part of the language";
    static final String NOTHING_IN_IO = "The File cannot be read";
    /**
     * transitionTable, type 2d array of "matrix" is to keep the 
     * record of transitions according to the automata. 
     * has a total of 10 elements and each element holing a value 
     * from 0-9 and 200-210 representing the automata states. 
     */
    protected static final int[][] transitionTable =
    {
        /*
         * State 0 - start 
         * State 1 - Comments
         * State 2 - digit or letters only
         * State 3 - only digits can come here 
         * State 4 - only E can come into this state
         * State 5 - only a period followed by any number of digits
         * State 6 - only < can come into this state 
         * State 7 - only > can come into this state
         * State 8 - only : can come into this state
         * State 9 - only + or - followed by digits can be in the steate
         * State200- is the done ID state acceptance state
         * State201- is the error state
         * State202- is the single symbol acceptance state
         * State203- is the done digit acceptance state
         * State204- is the < acceptance state
         * State205- is the <= acceptance state
         * State206- is the <> acceptance state
         * State207- is the > acceptance state
         * State208- is the >= acceptance state
         * State209- is the : acceptance state
         * State210- is the := acceptance state 
         * 
         */
        //state 0 
        //anything can go from here exepct things that are 
        //not in the language
        //state 201 is the error state	
        {
            201, 201, 201, 201, 201, 201, 201, 201,//0-7
            201, 0, 0, 201, 201, 0, 201, 201,//8-15	
            201, 201, 201, 201, 201, 201, 201, 201,//16-23
            201, 201, 201, 201, 201, 201, 201, 201,//24-31
            0, 201, 201, 201, 201, 201, 201, 201,//32-39 
            202, 202, 202, 202, 201, 202, 202, 202,//40-47
            3, 3, 3, 3, 3, 3, 3, 3,//48-55   
            3, 3, 8, 202, 6, 202, 7, 201,//56-63
            201, 2, 2, 2, 2, 2, 2, 2,//57-71   
            2, 2, 2, 2, 2, 2, 2, 2,//71-79
            2, 2, 2, 2, 2, 2, 2, 2,//80-88   
            2, 2, 202, 202, 201, 202, 201, 201,//89-97
            201, 2, 2, 2, 2, 2, 2, 2,//98-106   
            2, 2, 2, 2, 2, 2, 2, 2,//107-115
            2, 2, 2, 2, 2, 2, 2, 2,//116-123   
            2, 2, 2, 1, 201, 201, 201, 201 //124-127
        },
        //State 1
        {
            1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 0, 1, 1
        },
        //State 2
        {
            200, 200, 200, 200, 200, 200, 200, 200,
            200, 200, 200, 200, 200, 200, 200, 200,
            200, 200, 200, 200, 200, 200, 200, 200,
            200, 200, 200, 200, 200, 200, 200, 200,
            200, 200, 200, 200, 200, 200, 200, 200,
            200, 200, 200, 200, 200, 200, 200, 200,
            2, 2, 2, 2, 2, 2, 2, 2,
            2, 2, 200, 200, 200, 200, 200, 200,
            200, 2, 2, 2, 2, 2, 2, 2,
            2, 2, 2, 2, 2, 2, 2, 2,
            2, 2, 2, 2, 2, 2, 2, 2,
            2, 2, 2, 200, 200, 200, 200, 200,
            200, 2, 2, 2, 2, 2, 2, 2,
            2, 2, 2, 2, 2, 2, 2, 2,
            2, 2, 2, 2, 2, 2, 2, 2,
            2, 2, 2, 200, 200, 200, 200, 200
        },
        //State 3
        {
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 5, 203,
            3, 3, 3, 3, 3, 3, 3, 3,
            3, 3, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 4, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203
        },
        //State 4
        {
            201, 201, 201, 201, 201, 201, 201, 201,
            201, 201, 201, 201, 201, 201, 201, 201,
            201, 201, 201, 201, 201, 201, 201, 201,
            201, 201, 201, 201, 201, 201, 201, 201,
            201, 201, 201, 201, 201, 201, 201, 201,
            201, 201, 201, 9, 201, 9, 201, 201,
            201, 201, 201, 201, 201, 201, 201, 201,
            201, 201, 201, 201, 201, 201, 201, 201,
            201, 201, 201, 201, 201, 201, 201, 201,
            201, 201, 201, 201, 201, 201, 201, 201,
            201, 201, 201, 201, 201, 201, 201, 201,
            201, 201, 201, 201, 201, 201, 201, 201,
            201, 201, 201, 201, 201, 201, 201, 201,
            201, 201, 201, 201, 201, 201, 201, 201,
            201, 201, 201, 201, 201, 201, 201, 201,
            201, 201, 201, 201, 201, 201, 201, 201
        },
        //State 5
        {
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            5, 5, 5, 5, 5, 5, 5, 5,
            5, 5, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 4, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203
        },
        //State 6
        {
            204, 204, 204, 204, 204, 204, 204, 204,
            204, 204, 204, 204, 204, 204, 204, 204,
            204, 204, 204, 204, 204, 204, 204, 204,
            204, 204, 204, 204, 204, 204, 204, 204,
            204, 204, 204, 204, 204, 204, 204, 204,
            204, 204, 204, 204, 204, 204, 204, 204,
            204, 204, 204, 204, 204, 204, 204, 204,
            204, 204, 204, 204, 204, 205, 206, 204,
            204, 204, 204, 204, 204, 204, 204, 204,
            204, 204, 204, 204, 204, 204, 204, 204,
            204, 204, 204, 204, 204, 204, 204, 204,
            204, 204, 204, 204, 204, 204, 204, 204,
            204, 204, 204, 204, 204, 204, 204, 204,
            204, 204, 204, 204, 204, 204, 204, 204,
            204, 204, 204, 204, 204, 204, 204, 204,
            204, 204, 204, 204, 204, 204, 204, 204
        },
        //State 7
        {
            207, 207, 207, 207, 207, 207, 207, 207,
            207, 207, 207, 207, 207, 207, 207, 207,
            207, 207, 207, 207, 207, 207, 207, 207,
            207, 207, 207, 207, 207, 207, 207, 207,
            207, 207, 207, 207, 207, 207, 207, 207,
            207, 207, 207, 207, 207, 207, 207, 207,
            207, 207, 207, 207, 207, 207, 207, 207,
            207, 207, 207, 207, 207, 208, 207, 207,
            207, 207, 207, 207, 207, 207, 207, 207,
            207, 207, 207, 207, 207, 207, 207, 207,
            207, 207, 207, 207, 207, 207, 207, 207,
            207, 207, 207, 207, 207, 207, 207, 207,
            207, 207, 207, 207, 207, 207, 207, 207,
            207, 207, 207, 207, 207, 207, 207, 207,
            207, 207, 207, 207, 207, 207, 207, 207,
            207, 207, 207, 207, 207, 207, 207, 207
        },
        //State 8
        {
            209, 209, 209, 209, 209, 209, 209, 209,
            209, 209, 209, 209, 209, 209, 209, 209,
            209, 209, 209, 209, 209, 209, 209, 209,
            209, 209, 209, 209, 209, 209, 209, 209,
            209, 209, 209, 209, 209, 209, 209, 209,
            209, 209, 209, 209, 209, 209, 209, 209,
            209, 209, 209, 209, 209, 209, 209, 209,
            209, 209, 209, 209, 209, 210, 209, 209,
            209, 209, 209, 209, 209, 209, 209, 209,
            209, 209, 209, 209, 209, 209, 209, 209,
            209, 209, 209, 209, 209, 209, 209, 209,
            209, 209, 209, 209, 209, 209, 209, 209,
            209, 209, 209, 209, 209, 209, 209, 209,
            209, 209, 209, 209, 209, 209, 209, 209,
            209, 209, 209, 209, 209, 209, 209, 209,
            209, 209, 209, 209, 209, 209, 209, 209
        },
        //State 9
        {
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            9, 9, 9, 9, 9, 9, 9, 9,
            9, 9, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203,
            203, 203, 203, 203, 203, 203, 203, 203
        }
    };
    //This constants are declared to make the case switch more readable
    private static final int START_STATE = 0;
    private static final int COMMENT = 1;
    private static final int IN_ID = 2;
    private static final int IN_DIGIT = 3;
    private static final int IN_E = 4;
    private static final int IN_FRACTION = 5;
    private static final int IN_LESS_THAN = 6;
    private static final int IN_GREATER_THAN = 7;
    private static final int IN_COLON = 8;
    private static final int E_PLUS_OR_MINUS = 9;
    private static final int DONE_ID = 200;
    private static final int ERROR = 201;
    private static final int SINGLE_SYMBOL = 202;
    private static final int DONE_DIGIT = 203;
    private static final int DONE_LESS_THAN = 204;
    private static final int DONE_LESS_THAN_EQUAL = 205;
    private static final int DONE_NOT_EQUAL = 206;
    private static final int DONE_GREATER_THAN = 207;
    private static final int DONE_GREATER_THAN_EQUAL = 208;
    private static final int DONE_COLON = 209;
    private static final int DONE_COLON_EQUAL = 210;
    //This constants are used to replace the return in case switch	
    //0 is a lexeme
    //1 is not a lexeme
    //2 is error	
    public static final int IS_A_LEXEME = 0;
    public static final int IS_NOT_A_LEXEME = 1;
    public static final int IS_AN_ERROR = 2;
    //these are used to keep the next character pointer
    //file is used to locate the file from constructor
    //lexeme is used to build and object from tokens
    //token is of type Token to call symbol table
    //pushBack is to move the token pointer back if needed and to read
    protected File argument;
    protected StringBuilder lexeme;
    protected Hashtable symbols;
    protected PushbackReader pushBack;
    protected Token tokens;
    protected Object attribute;
    protected LineNumberReader lineNumRead;
    protected int lineNum;

    /**
     * This is the scanner construstor; locates the file 
     * argument of where the file is 
     *found in the computer
     * and uses the SymbolTable class as the hashTable, 
     *which contains the values from the enum Token.
     * @param fileLocation argument of type file
     * @param table argument of type SymbolTable
     */
    public CompilerScanner(File fileLocation, Hashtable table)
    {
        this.argument = fileLocation;
        this.symbols = table;
        this.lineNum = 0;
        try
        {
            FileReader fileToRead = new FileReader(fileLocation);
            //creates a new pushBackReader based of type 
            //FileReader based of type File
            pushBack = new PushbackReader(new FileReader(fileLocation));
            lineNumRead = new LineNumberReader(fileToRead);
            while (lineNumRead.readLine() != null)
            {
                lineNum++;
            }//end while

            lineNumRead.close();
        }
        catch (FileNotFoundException e)
        {
            System.err.println(ERROR_CONSOLE);
            System.exit(1);
        }
        catch (IOException e)
        {
            System.err.println(NOTHING_IN_IO);
        }
    }

    /**
     * getLexeme returns the lexeme when it is called lexeme is of type 
     *StringBuffer and it is retained with a toString. 
     * @return the toString of the lexeme
     */
    public String getLexeme()
    {
        return lexeme.toString();
    }

    /**
     * is used to get the tokens 
     * @return this.token that the pointer it currently is at
     */
    public Token getToken()
    {
        return this.tokens;
    }

    public int getLineCounter()
    {
        return this.lineNum;
    }

    /**
     * nextToken is of type int to reuturn is lexeme is not lexeme or error
     * case switch statement represents the states of 
     * the automata and where to go after the pointer from the file has 
     * invoked the transitionTable and SymbolTable
     * @return
     * return 0 is lexeme
     * return 1 is not lexeme
     * return 2 error
     */
    public int nextToken()
    {
        //initializes as 0
        //create as constants 
        int nextCharacter = 0;
        int currentState = 0;
        int nextState = 0;

        //lexeme is initialized here
        lexeme = new StringBuilder();
        do
        {
            //goes to the next char
            currentState = nextState;
            try
            {
                nextCharacter = pushBack.read();
            }
            catch (IOException e)
            {
                System.err.println(NOTHING_IN_IO);
            }
            // -1 is the representation of the end of file in integer format
            if (nextCharacter == -1)
            {
                return 1;
            }
            //sets next state to current and nextChar from transitionTable
            nextState = transitionTable[currentState][nextCharacter];
            switch (nextState)
            {
                //if the case is 0 then turn the nextCharacter number 
                //into a char by appending
                //create this 0 1 .. as constants 		

                //-------------------------------------------//
                //CASE blocks represent the transitions from-//
                //Automita implemented ----------------------//
                //-------------------------------------------//
                case START_STATE:
                    lexeme.append("");
                    break;

                case COMMENT:
                    break;

                case IN_ID:
                    lexeme.append(Character.toChars(nextCharacter));
                    break;

                case IN_DIGIT:
                    lexeme.append(Character.toChars(nextCharacter));
                    break;

                case IN_E:
                    lexeme.append(Character.toChars(nextCharacter));
                    break;

                case IN_FRACTION:
                    lexeme.append(Character.toChars(nextCharacter));
                    break;

                case IN_LESS_THAN:
                    lexeme.append(Character.toChars(nextCharacter));
                    break;

                case IN_GREATER_THAN:
                    lexeme.append(Character.toChars(nextCharacter));
                    break;

                case IN_COLON:
                    lexeme.append(Character.toChars(nextCharacter));
                    break;

                case E_PLUS_OR_MINUS:
                    lexeme.append(Character.toChars(nextCharacter));
                    break;

                //case 200's are to be either pushed back or treated as	
                // cases to append the lexeme	

                //Done_ID uses if and else if statement to look for 
                //reserved words
                case DONE_ID:
                    try
                    {
                        pushBack.unread(nextCharacter);
                    }
                    catch (IOException e)
                    {
                        System.err.println(NOTHING_IN_IO);
                    }
                    if (lexeme.toString().equals("if"))
                    {
                        tokens = Token.IF;
                    }
                    else if (lexeme.toString().equals("then"))
                    {
                        tokens = Token.THEN;
                    }
                    else if (lexeme.toString().equals("else"))
                    {
                        tokens = Token.ELSE;
                    }
                    else if (lexeme.toString().equals("while"))
                    {
                        tokens = Token.WHILE;
                    }
                    else if (lexeme.toString().equals("do"))
                    {
                        tokens = Token.DO;
                    }
                    else if (lexeme.toString().equals("var"))
                    {
                        tokens = Token.VAR;
                    }
                    else if (lexeme.toString().equals("begin"))
                    {
                        tokens = Token.BEGIN;
                    }
                    else if (lexeme.toString().equals("end"))
                    {
                        tokens = Token.END;
                    }
                    else if (lexeme.toString().equals("array"))
                    {
                        tokens = Token.ARRAY;
                    }
                    else if (lexeme.toString().equals("of"))
                    {
                        tokens = Token.OF;
                    }
                    else if (lexeme.toString().equals("functions"))
                    {
                        tokens = Token.FUNCTION;
                    }
                    else if (lexeme.toString().equals("procedure"))
                    {
                        tokens = Token.PROCEDURE;
                    }
                    else if (lexeme.toString().equals("program"))
                    {
                        tokens = Token.PROGRAM;
                    }
                    else if (lexeme.toString().equals("or"))
                    {
                        tokens = Token.OR;
                    }
                    else if (lexeme.toString().equals("not"))
                    {
                        tokens = Token.NOT;
                    }
                    else if (lexeme.toString().equals("mod"))
                    {
                        tokens = Token.MOD;
                    }
                    else if (lexeme.toString().equals("and"))
                    {
                        tokens = Token.AND;
                    }
                    else if (lexeme.toString().equals("real"))
                    {
                        tokens = Token.REAL;
                    }
                    else if (lexeme.toString().equals("integer"))
                    {
                        tokens = Token.INTEGER;
                    }
                    else
                    {
                        tokens = Token.ID;
                    }
                    return IS_A_LEXEME;

                case ERROR:
                    //lexeme.append(Character.toChars(nextCharacter));
                    this.getLexeme();
                    return IS_AN_ERROR;

                //single symbol uses if and else if statements to look
                //for single symbols
                case SINGLE_SYMBOL:
                    lexeme.append(Character.toChars(nextCharacter));
                    if (lexeme.toString().equals(";"))
                    {
                        tokens = Token.SEMI_COLON;
                    }
                    else if (lexeme.toString().equals("."))
                    {
                        tokens = Token.PERIOD;
                    }
                    else if (lexeme.toString().equals("["))
                    {
                        tokens = Token.LEFT_SQUARE_BRACKET;
                    }
                    else if (lexeme.toString().equals("]"))
                    {
                        tokens = Token.RIGHT_SQUARE_BRACKET;
                    }
                    else if (lexeme.toString().equals("("))
                    {
                        tokens = Token.LEFT_PARENTHESIS;
                    }
                    else if (lexeme.toString().equals(")"))
                    {
                        tokens = Token.RIGHT_PARENTHESIS;
                    }
                    else if (lexeme.toString().equals("+"))
                    {
                        tokens = Token.PLUS;
                    }
                    else if (lexeme.toString().equals("-"))
                    {
                        tokens = Token.MINUS;
                    }
                    else if (lexeme.toString().equals("*"))
                    {
                        tokens = Token.MULTIPLY;
                    }
                    else if (lexeme.toString().equals("/"))
                    {
                        tokens = Token.DIVIDE;
                    }
                    else if (lexeme.toString().equals("="))
                    {
                        tokens = Token.EQUAL;
                    }
                    return IS_A_LEXEME;

                case DONE_DIGIT:
                    try
                    {
                        pushBack.unread(nextCharacter);
                    }
                    catch (IOException e)
                    {
                        System.err.println(NOTHING_IN_IO);
                    }

                    tokens = Token.NUMBER;
                    return IS_A_LEXEME;

                case DONE_LESS_THAN:
                    try
                    {
                        pushBack.unread(nextCharacter);
                    }
                    catch (IOException e)
                    {
                        System.err.println(NOTHING_IN_IO);
                    }
                    tokens = Token.LESS_THAN;
                    break;

                case DONE_LESS_THAN_EQUAL:
                    lexeme.append(Character.toChars(nextCharacter));
                    tokens = Token.LESS_THAN_EQUAL;
                    return IS_A_LEXEME;

                case DONE_NOT_EQUAL:
                    lexeme.append(Character.toChars(nextCharacter));
                    tokens = Token.NOT_EQUAL;
                    return IS_A_LEXEME;

                case DONE_GREATER_THAN:
                    try
                    {
                        pushBack.unread(nextCharacter);
                    }
                    catch (IOException e)
                    {
                        System.err.println(NOTHING_IN_IO);
                    }
                    tokens = Token.GREATER_THAN;
                    return IS_A_LEXEME;

                case DONE_GREATER_THAN_EQUAL:
                    lexeme.append(Character.toChars(nextCharacter));
                    tokens = Token.GREATER_THAN_EQUAL;
                    return IS_A_LEXEME;

                case DONE_COLON:
                    try
                    {
                        pushBack.unread(nextCharacter);
                    }
                    catch (IOException e)
                    {
                        System.err.println(NOTHING_IN_IO);
                    }
                    tokens = Token.COLON;
                    return IS_A_LEXEME;

                case DONE_COLON_EQUAL:
                    lexeme.append(Character.toChars(nextCharacter));
                    tokens = Token.COLON_EQUALS;
                    return IS_A_LEXEME;
            }//end switch
        }//end do
        while (nextCharacter != -1);
        //says that their is no lexeme
        return IS_NOT_A_LEXEME;
    }
    /*
     * Line Counter is of type int to return the number of lines of the file
     * Uses the LineNumberReader class from java
     * @return int with the line count
     */
}//end class