package CompilerScanner;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PushbackReader;
import java.lang.StringBuilder;
import java.util.Hashtable;

/**
 * @author Alfonso Vazquez
 * Scanner for Compiler
 * transitionTable is of type 2d array or matrix contains all the state transitions from the automita
 * compilerScanner is the constuctor of the class containing file and table
 * getLexeme is the getter for the scanner to call the lexeme
 * nextToken keeps track of the tokens and states the scanner is transitioning through
 * pushBack is used to read in and push back to the input stream
 * main is used to test the constructor along with nextToken.
 *
 */
public class CompilerScanner
{
    /*
     * The following four lines are error exceptions declared in a simplified form. 
     */
    static final String ERROR_CONSOLE = "The File does not exist or is incorrect format";
    static final String UNKOWN_TOKEN  = "The token is now part of the language";
    static final String NOTHING_IN_IO = "The File cannot be read";
    static final String ONLY_ONE_ARGUMENT_NEEDED = "You only need one file location,or, You have more than the file location, file location is the only argument needed";
	
    /**
     * transitionTable, type 2d array of "matrix" is to keep the record of transitions according to the automata. 
     * has a total of 10 elements and each element holing a value from 0-9 and 200-210 representing the automata states. 
     */
    protected static final int [][] transitionTable = 
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
		//anything can go from here exepct things that are not in the language
		//state 201 is the error state	
		{
		    // 0,   1,   2,   3,   4,   5,   6,   7,   8,   9,  10,  11,  12,  13,  14,  15, // 0 - 15	 
                    201, 201, 201, 201, 201, 201, 201, 201, 201,   0, 201, 201, 201,   0, 201, 201,	
                    //16    17  18   19   20   21   22   23   24   25   26   27   28   29   30   31  //16 - 31
                    201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201,
                    //32   33   34   35   36   37   38   39   40   41   42   43   44   45   46   47  //32 - 47
                    0, 201, 201, 201, 201, 201, 201, 201, 202, 202, 202, 202, 201, 202, 202, 202,
                    //48    49   50   51   52   53  54   55    56  57   58  59    60   61   62   63  //48 - 
                    3,   3,   3,   3,   3,   3,   3,   3,   3,   3,   8, 202,   6, 202,   7, 201,
                    //64   65    66   67   68   69   70   71  72    73   74  75   76    77  78   79   
                    201,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,
                    //80    81   82   83   84   85  86    87   89   90   91  92  93    94   95   96			
                    2,   2,   2,   2,   2,   2,   2,   2,   2,   2,  202, 202, 201, 202, 201, 201,
                    //97    98   99  100  101  102  103   104  105  106  107 108  109  110  111  112 
                    201,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,
                    //112 113 114  115   116   117 118  119  120  121  122  123  124 125 126   127
                    2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   1, 201, 201, 201, 201
                 },       	
                 //State 1
                 {
                    // 0,   1,   2,   3,   4,   5,   6,   7,   8,   9,  10,  11,  12,  13,  14,  15,
                    1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,
                    1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,
                    1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,
                    1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,
                    1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,
                    1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,
                    1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,
                    1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   0,   1,   1
		},        
		//State 2
		{
                    // 0,   1,   2,   3,   4,   5,   6,   7,   8,   9,  10,  11,  12,  13,  14,  15,
                    200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200,
                    200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200,
                    200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200,
                      2,   2,   2,   2,   2,   2,   2,   2,   2,   2, 200, 200, 200, 200, 200, 200,
                    200,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,
                      2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2, 200, 200, 200, 200, 200,
                    200,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2,    2,  2,   2,
                      2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2, 200, 200, 200, 200, 200
		},        
		//State 3
		{
                    // 0,   1,   2,   3,   4,   5,   6,   7,   8,   9,  10,  11,  12,  13,  14,  15,
                    203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203,
                    203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203,
                    203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203,   5, 203,
                      3,   3,   3,   3,   3,   3,   3,   3,   3,   3, 203, 203, 203, 203, 203, 203,
                    203, 203, 203, 203, 203,   4, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203,
                    203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203,
                    203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203,
                    203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203
		},        
		//State 4
		{
                    // 0,   1,   2,   3,   4,   5,   6,   7,   8,   9,  10,  11,  12,  13,  14,  15,
                    201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201,
                    201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201,
                    201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201,   9, 201,   9, 201, 201,
                    201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201,
                    201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201,
                    201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201,
                    201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201,
                    201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201
		},
                //State 5
		{
                    // 0,   1,   2,   3,   4,   5,   6,   7,   8,   9,  10,  11,  12,  13,  14,  15,
                    203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203,
                    203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203,
                    203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203,
                    5,   5,   5,   5,   5,   5,   5,   5,   5,   5, 203, 203, 203, 203, 203, 203,
                    203, 203, 203, 203, 203,   4, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203,
                    203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203,
                    203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203,
                    203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203
		},
                //State 6
		{
                    // 0,   1,   2,   3,   4,   5,   6,   7,   8,   9,  10,  11,  12,  13,  14,  15,
                    204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204,
                    //16    17  18   19   20   21   22   23   24   25   26   27   28   29   30   31
                    204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204,
                    //32   33   34   35   36   37   38   39   40   41   42   43   44   45   46   47
                    204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204,
                    //48    49   50   51   52   53  54   55    56  57   58  59    60   61   62   63
                    204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 205, 206, 204,
                    //64   65    66   67   68   69   70   71  72    73   74  75   76    77  78   79   
                    204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204,
                    //80    81   82   83   84   85  86    87   89   90   91  92  93    94   95   96
                    204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204,
                    //97    98   99  100  101  102 103  104  105  106  107  108  109  110  111  112 
                    204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204,
                    //112 113 114  115   116   117 118  119  120  121  122  123  124  125  126  127
                    204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204, 204
		},
		//State 7
		{
                    // 0,   1,   2,   3,   4,   5,   6,   7,   8,   9,  10,  11,  12,  13,  14,  15,
                    207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207,
                    207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207,
                    207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207,
                    207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 208, 207, 207,
                    207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207,
                    207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207,
                    207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207,
                    207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207, 207
                },
        	//State 8
		{
                    // 0,   1,   2,   3,   4,   5,   6,   7,   8,   9,  10,  11,  12,  13,  14,  15,	 
                    209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209,
                    209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209,
                    209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209,
                    209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 210, 209, 209,
                    209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209,
                    209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209,
                    209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209,
                    209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209, 209
                },
		//State 9
		{         
                    // 0,   1,   2,   3,   4,   5,   6,   7,   8,   9,  10,  11,  12,  13,  14,  15,
                    203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203,
                    203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203,	 
                    203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203,	        
                      9,   9,   9,   9,   9,   9,   9,   9,   9,   9, 203, 203, 203, 203, 203, 203,
                    203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203,
                    203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203,
                    203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 
                    203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203, 203
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
	
	/**
	 * This is the scanner construstor; locates the file argument of where the file is 
	 *found in the computer
	 * and uses the SymbolTable class as the hashTable, 
	 *which contains the values from the enum Token.
	 * @param fileLocation argument of type file
	 * @param table argument of type SymbolTable
	 */
	public CompilerScanner(File fileLocation, Hashtable table)
	{
            this.argument= fileLocation;
            this.symbols = table;
            try
            {
                //creates a new pushBackReader based of type FileReader based of type File
                pushBack= new PushbackReader(new FileReader (fileLocation));
            }
            catch(FileNotFoundException e)
            {
                e.printStackTrace();
		System.err.println(ERROR_CONSOLE);
		System.exit( 1);
            }
            catch(IOException e)
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
	/**
	 * nextToken is of type int to reuturn is lexeme is not lexeme or error
	 * return 0 is lexeme
	 * return 1 is not lexeme
	 * return 2 error
	 * case switch statement represents the states of the automata and where to go after the pointer from the file has invoked the transitionTable and SymbolTable
	 * @return
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
                catch(IOException e)
                {
                    System.err.println(NOTHING_IN_IO);
		}
		// -1 is the representation of the end of file in integer format
		if(nextCharacter == -1)
		{
                    return 1;
		}
		//sets next state to current and nextChar from transitionTable
		nextState = transitionTable[currentState][nextCharacter];
		switch(nextState)
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
                        catch(IOException e) 
                        {
                            System.err.println(NOTHING_IN_IO);   
                        }
                        if(lexeme.toString().equals("if"))
                        {
                            tokens = tokens.IF;   
                        }
                        else if(lexeme.toString().equals("then"))
                        {
                            tokens = tokens.THEN;   
                        }
                        else if(lexeme.toString().equals("else"))
                        {
                            tokens = tokens.ELSE;   
                        }
                        else if(lexeme.toString().equals("while"))
                        {
                            tokens = tokens.WHILE;   
                        }
                        else if(lexeme.toString().equals("do"))
                        {
                            tokens = tokens.DO;   
                        }
                        else if(lexeme.toString().equals("var"))
                        {
                            tokens = tokens.VAR;   
                        }
                        else if(lexeme.toString().equals("begin"))
                        {
                            tokens = tokens.BEGIN;
                        }    
                        else if(lexeme.toString().equals("end"))
                        {
                            tokens = tokens.END;   
                        }
                        else if(lexeme.toString().equals("array"))
                        {
                            tokens = tokens.ARRAY;   
                        }
                        else if(lexeme.toString().equals("of"))
                        {
                            tokens = tokens.OF;   
                        }
                        else if(lexeme.toString().equals("functions"))
                        {
                            tokens = tokens.FUNCTION;   
                        }
                        else if(lexeme.toString().equals("procedure"))
                        {
                            tokens = tokens.PROCEDURE;   
                        }
                        else if(lexeme.toString().equals("program"))
                        {
                            tokens = tokens.PROGRAM;   
                        }
                        else if(lexeme.toString().equals("or"))
                        {
                            tokens = tokens.OR;   
                        }
                        else if(lexeme.toString().equals("not"))
                        {
                            tokens = tokens.NOT;   
                        }
                        else if(lexeme.toString().equals("mod"))
                        {
                            tokens = tokens.MOD;   
                        }
                        else if(lexeme.toString().equals("and"))
                        {
                            tokens = tokens.AND;   
                        }
                        else if(lexeme.toString().equals("real"))
                        {
                            tokens = tokens.REAL;   
                        }
                        else if(lexeme.toString().equals("integer"))
                        {
                            tokens = tokens.INTEGER;   
                        }
                        else tokens = tokens.ID;
                        return IS_A_LEXEME;
				                        
                    case ERROR: return IS_AN_ERROR;

                        //single symbol uses if and else if statements to look
                        //for single symbols
                    case SINGLE_SYMBOL:
                        lexeme.append(Character.toChars(nextCharacter));
                        if(lexeme.toString().equals(";"))
                        {
                             tokens = tokens.SEMI_COLON;
                        }
                        else if(lexeme.toString().equals("."))
                        {
                            tokens = tokens.PERIOD;
                        }
                        else if(lexeme.toString().equals("["))
                        {
                            tokens = tokens.LEFT_SQUARE_BRACKET;
                        }
                        else if(lexeme.toString().equals("]"))
                        {
                            tokens = tokens.RIGHT_SQUARE_BRACKET;
                        }
                        else if(lexeme.toString().equals("("))
                        {
                            tokens = tokens.LEFT_PARENTHESIS;
                        }
                        else if(lexeme.toString().equals(")"))
                        {
                            tokens = tokens.RIGHT_PARENTHESIS;
                        }
                        else if(lexeme.toString().equals("+"))
                        {
                            tokens = tokens.PLUS;
                        }
                        else if(lexeme.toString().equals("-"))
                        {
                            tokens = tokens.MINUS;
                        }
                        else if(lexeme.toString().equals("*"))
                        {
                            tokens = tokens.MULTIPLY;
                        }
                        else if(lexeme.toString().equals("/"))
                        {
                            tokens = tokens.DIVIDE;
                        }
                        else if(lexeme.toString().equals("="))
                        {
                            tokens = tokens.EQUAL;
                        }
                        return IS_A_LEXEME;
				
                    case DONE_DIGIT:
                        try
                        {
                            pushBack.unread(nextCharacter);	
                        }
                        catch(IOException e)
                        {
                            System.err.println(NOTHING_IN_IO);
                        }
                        
                        tokens = tokens.NUMBER;
                        return IS_A_LEXEME;	
                   
                    case DONE_LESS_THAN: 
                        try
                        {
                            pushBack.unread(nextCharacter);
                        }
                        catch(IOException e)
                        {
                            System.err.println(NOTHING_IN_IO);
                        }
                        tokens = tokens.LESS_THAN;
                        break;
			
                    case DONE_LESS_THAN_EQUAL: 
                        lexeme.append(Character.toChars(nextCharacter));
                        tokens = tokens.LESS_THAN_EQUAL;
                        return IS_A_LEXEME; 
			   
                    case DONE_NOT_EQUAL:
                        lexeme.append(Character.toChars(nextCharacter));
                        tokens = tokens.NOT_EQUAL;
                        return IS_A_LEXEME;	
                     
                    case DONE_GREATER_THAN:	
                        try
                        {
                            pushBack.unread(nextCharacter);
                        }
                        catch(IOException e)
                        {
                            System.err.println(NOTHING_IN_IO);
                        }
                        tokens = tokens.GREATER_THAN;
                        return IS_A_LEXEME;
                        
                    case DONE_GREATER_THAN_EQUAL:
                        lexeme.append(Character.toChars(nextCharacter));
                        tokens = tokens.GREATER_THAN_EQUAL;
                        return IS_A_LEXEME;
				
                    case DONE_COLON: 
                        try
                        {
                            pushBack.unread(nextCharacter);
                        }
                        catch(IOException e)
                        {
                            System.err.println(NOTHING_IN_IO);
                        }
                        tokens = tokens.COLON;
                        return IS_A_LEXEME;
				
                    case DONE_COLON_EQUAL:
                        lexeme.append(Character.toChars(nextCharacter));
                        tokens = tokens.COLON_EQUALS;
                        return IS_A_LEXEME;		
				
			}//end switch
		}//end do
		while(nextCharacter != -1);
		//says that their is no lexeme
		return IS_NOT_A_LEXEME;		
	}

	/**
	 * Main used to test the constructor and transitionTable along with Token and SymbolTable
	 * file based on args[0]
	 * @param args
	 */
	public static void main (String [] args)
	{
            String fileLocation = args[0];
            if(fileLocation == null || args.length > 1)
            {
                System.err.println(ONLY_ONE_ARGUMENT_NEEDED);
            }
            int i = 0;
            File file = new File(args[0]);
            SymbolTable table = new SymbolTable();
            CompilerScanner scan = new CompilerScanner(file,table);
            while (i != 1)
            {
		i = scan.nextToken();		
                if(scan.lexeme.length() != 0)
                {
                    System.out.println("LEXEME: "+scan.getLexeme() +" TOKEN: ["+ scan.getToken()+ "]");
                }
                
            }//end while
	}//end main
}//end class