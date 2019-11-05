// $ANTLR 3.4 grammar\\Maths.g 2019-10-26 21:47:43

	package ru.amm.maths.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class MathsLexer extends Lexer {
    public static final int EOF=-1;
    public static final int ADD_OP=4;
    public static final int DIGIT=5;
    public static final int LPAREN=6;
    public static final int MULT_OP=7;
    public static final int NUMBER=8;
    public static final int RPAREN=9;
    public static final int WS=10;

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public MathsLexer() {} 
    public MathsLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public MathsLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "grammar\\Maths.g"; }

    // $ANTLR start "ADD_OP"
    public final void mADD_OP() throws RecognitionException {
        try {
            int _type = ADD_OP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar\\Maths.g:18:8: ( '+' )
            // grammar\\Maths.g:18:10: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ADD_OP"

    // $ANTLR start "MULT_OP"
    public final void mMULT_OP() throws RecognitionException {
        try {
            int _type = MULT_OP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar\\Maths.g:19:9: ( '*' )
            // grammar\\Maths.g:19:11: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MULT_OP"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar\\Maths.g:21:8: ( '(' )
            // grammar\\Maths.g:21:10: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar\\Maths.g:22:8: ( ')' )
            // grammar\\Maths.g:22:10: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar\\Maths.g:24:5: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // grammar\\Maths.g:24:9: ( ' ' | '\\t' | '\\r' | '\\n' )
            {
            if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            // grammar\\Maths.g:33:7: ( ( '0' .. '9' ) )
            // grammar\\Maths.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar\\Maths.g:34:7: ( ( DIGIT )+ )
            // grammar\\Maths.g:34:9: ( DIGIT )+
            {
            // grammar\\Maths.g:34:9: ( DIGIT )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                switch ( input.LA(1) ) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    {
                    alt1=1;
                    }
                    break;

                }

                switch (alt1) {
            	case 1 :
            	    // grammar\\Maths.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NUMBER"

    public void mTokens() throws RecognitionException {
        // grammar\\Maths.g:1:8: ( ADD_OP | MULT_OP | LPAREN | RPAREN | WS | NUMBER )
        int alt2=6;
        switch ( input.LA(1) ) {
        case '+':
            {
            alt2=1;
            }
            break;
        case '*':
            {
            alt2=2;
            }
            break;
        case '(':
            {
            alt2=3;
            }
            break;
        case ')':
            {
            alt2=4;
            }
            break;
        case '\t':
        case '\n':
        case '\r':
        case ' ':
            {
            alt2=5;
            }
            break;
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            {
            alt2=6;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("", 2, 0, input);

            throw nvae;

        }

        switch (alt2) {
            case 1 :
                // grammar\\Maths.g:1:10: ADD_OP
                {
                mADD_OP(); 


                }
                break;
            case 2 :
                // grammar\\Maths.g:1:17: MULT_OP
                {
                mMULT_OP(); 


                }
                break;
            case 3 :
                // grammar\\Maths.g:1:25: LPAREN
                {
                mLPAREN(); 


                }
                break;
            case 4 :
                // grammar\\Maths.g:1:32: RPAREN
                {
                mRPAREN(); 


                }
                break;
            case 5 :
                // grammar\\Maths.g:1:39: WS
                {
                mWS(); 


                }
                break;
            case 6 :
                // grammar\\Maths.g:1:42: NUMBER
                {
                mNUMBER(); 


                }
                break;

        }

    }


 

}