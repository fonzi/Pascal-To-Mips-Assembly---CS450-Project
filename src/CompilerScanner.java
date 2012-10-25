import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PushbackReader;
import java.lang.StringBuffer;

/**
 * @author Alfonso Vazquez
 * Scanner for Compiler
 * Contains 6 methods
 * transitionTable is of type 2d array or matrix contains all the state transitions from the automita
 * compilerScanner is the constuctor of the class containing file and table
 * getLexeme is the getter for the scanner to call the lexeme
 * nextToken keeps track of the tokens and states the scanner is transitioning through
 * toString used to return 
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
			    // 0,   1,   2,   3,   4,   5,   6,   7,   8,   9,  10,  11,  12,  13,  14,  15, 
				 201, 201, 201, 201, 201, 201, 201, 201, 201,   0, 201, 201, 201,   0, 201, 201,
				//16    17  18   19   20   21   22   23   24   25   26   27   28   29   30   31
				 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201, 201,
				//32   33   34   35   36   37   38   39   40   41   42   43   44   45   46   47
				   0, 201, 201, 201, 201, 201, 201, 201, 202, 202, 202, 202, 201, 202, 202, 202,
 				//48    49   50   51   52   53  54   55    56  57   58  59    60   61   62   63
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
	//These four protected constant arguments are to initialize the File, lexeme, symbolTable, pushBackReader and Token Enum.
	protected File argument;
	protected StringBuffer lexeme;
	protected SymbolTable symbols;
	protected PushbackReader pushBack;
	protected Token tokens;
	/**
	 * This is the scanner construstor; locates the file argument of where the file is found in the computer
	 * and uses the SymbolTable class as the hashTable, which contains the values from the enum Token.
	 * @param fileLocation argument of type file
	 * @param table argument of type SymbolTable
	 */
	public CompilerScanner(File fileLocation, SymbolTable table)
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
		}
		catch(IOException e)
		{
			System.err.println(NOTHING_IN_IO);
		}
	}
	/**
	 * getLexeme returns the lexeme when it is called lexeme is of type StringBuffer and it is retained with a toString. 
	 * @return
	 */
	public String getLexeme() 
	{
		return lexeme.toString();
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
		int nextCharacter = 0;
		int currentState = 0;
		int nextState = 0;
		//lexeme is initialized here
		lexeme = new StringBuffer();
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
				
				//if the case is 0 then turn the nextCharacter number into a char by appending
				case 0:
					break;
				case 1: 
					break;
				case 2: 
					lexeme.append(Character.toChars(nextCharacter));
					break;
				case 3: 
					lexeme.append(Character.toChars(nextCharacter)); 
					break;
				case 4: 
					lexeme.append(Character.toChars(nextCharacter)); 
					break;
				case 5: 
					lexeme.append(Character.toChars(nextCharacter)); 
					break;
				case 6: 
					lexeme.append(Character.toChars(nextCharacter)); 
					break;
				case 7:
					lexeme.append(Character.toChars(nextCharacter)); 
					break;
				case 8:
					lexeme.append(Character.toChars(nextCharacter)); 
					break;
				case 9: 
					lexeme.append(Character.toChars(nextCharacter)); 
					break;

				//0 is a lexeme
				//1 is not a lexeme
				//2 is error
				
				//case 200's are to be either pushed back or treated as cases to append the lexme
				case 200: 
					try
					{
						pushBack.unread(nextCharacter);
					}
					catch(IOException e)
					{
						System.err.println(NOTHING_IN_IO);
					}
					return 0;
				case 201: return 2;
				case 202:
					lexeme.append(Character.toChars(nextCharacter));
					return 0;
				case 203:
					try
					{
						pushBack.unread(nextCharacter);
						
					}
					catch(IOException e)
					{
						System.err.println(NOTHING_IN_IO);
					}
					return 0;	
				case 204: 
					try
					{
						pushBack.unread(nextCharacter);
						
					}
					catch(IOException e)
					{
						System.err.println(NOTHING_IN_IO);
					}
					break;
					
				case 205: 
					lexeme.append(Character.toChars(nextCharacter));
					return 0; 
				case 206:
					lexeme.append(Character.toChars(nextCharacter));
					return 0;
				case 207: 		
					try
					{
						pushBack.unread(nextCharacter);
					}
					catch(IOException e)
					{
						System.err.println(NOTHING_IN_IO);
					}
					break;
				
				case 208:
					lexeme.append(Character.toChars(nextCharacter));
					return 0;
				case 209: 
					try
					{
						pushBack.unread(nextCharacter);
					
					}
					catch(IOException e)
					{
						System.err.println(NOTHING_IN_IO);
					}
					return 0;
				case 210:
					lexeme.append(Character.toChars(nextCharacter));
					return 0;		
				
			}//end switch
		}//end do
		while(nextCharacter != -1);
		//says that their is no lexeme
		return 1;		
	}
	

	public String toString()
	{
		return "Lexeme " + lexeme;
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
			System.out.println(scan.getLexeme());
		}//end while
	}//end main
}//end

/*
 * Factorial.pas tested results are below. 
 * 

program
Factorial
;



var

i

x
:
integer
;

fact
:
real
;


begin

writeln
(

Enter
the
value
of
the
factorial

)
;

readln
(
x
)
;

fact
:=
1
;


for
i
:=
1
to
x
do


fact
:=
fact
*
i
;

writeln
(
x



is


fact
)
;


readln
;


end
.

*/

