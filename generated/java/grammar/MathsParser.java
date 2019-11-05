// $ANTLR 3.4 grammar\\Maths.g 2019-10-26 21:47:43

	package ru.amm.maths.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class MathsParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ADD_OP", "DIGIT", "LPAREN", "MULT_OP", "NUMBER", "RPAREN", "WS"
    };

    public static final int EOF=-1;
    public static final int ADD_OP=4;
    public static final int DIGIT=5;
    public static final int LPAREN=6;
    public static final int MULT_OP=7;
    public static final int NUMBER=8;
    public static final int RPAREN=9;
    public static final int WS=10;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public MathsParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public MathsParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return MathsParser.tokenNames; }
    public String getGrammarFileName() { return "grammar\\Maths.g"; }


    public static class mathExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "mathExpr"
    // grammar\\Maths.g:37:1: mathExpr : expression EOF !;
    public final MathsParser.mathExpr_return mathExpr() throws RecognitionException {
        MathsParser.mathExpr_return retval = new MathsParser.mathExpr_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token EOF2=null;
        MathsParser.expression_return expression1 =null;


        Object EOF2_tree=null;

        try {
            // grammar\\Maths.g:37:10: ( expression EOF !)
            // grammar\\Maths.g:37:12: expression EOF !
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expression_in_mathExpr172);
            expression1=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression1.getTree());

            EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_mathExpr174); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "mathExpr"


    public static class expression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expression"
    // grammar\\Maths.g:41:1: expression : sumExpression ;
    public final MathsParser.expression_return expression() throws RecognitionException {
        MathsParser.expression_return retval = new MathsParser.expression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        MathsParser.sumExpression_return sumExpression3 =null;



        try {
            // grammar\\Maths.g:42:2: ( sumExpression )
            // grammar\\Maths.g:42:4: sumExpression
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_sumExpression_in_expression187);
            sumExpression3=sumExpression();

            state._fsp--;

            adaptor.addChild(root_0, sumExpression3.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expression"


    public static class sumExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "sumExpression"
    // grammar\\Maths.g:45:1: sumExpression : multExpr ( ADD_OP ^ multExpr )* ;
    public final MathsParser.sumExpression_return sumExpression() throws RecognitionException {
        MathsParser.sumExpression_return retval = new MathsParser.sumExpression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ADD_OP5=null;
        MathsParser.multExpr_return multExpr4 =null;

        MathsParser.multExpr_return multExpr6 =null;


        Object ADD_OP5_tree=null;

        try {
            // grammar\\Maths.g:46:2: ( multExpr ( ADD_OP ^ multExpr )* )
            // grammar\\Maths.g:46:4: multExpr ( ADD_OP ^ multExpr )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_multExpr_in_sumExpression198);
            multExpr4=multExpr();

            state._fsp--;

            adaptor.addChild(root_0, multExpr4.getTree());

            // grammar\\Maths.g:46:13: ( ADD_OP ^ multExpr )*
            loop1:
            do {
                int alt1=2;
                switch ( input.LA(1) ) {
                case ADD_OP:
                    {
                    alt1=1;
                    }
                    break;

                }

                switch (alt1) {
            	case 1 :
            	    // grammar\\Maths.g:46:14: ADD_OP ^ multExpr
            	    {
            	    ADD_OP5=(Token)match(input,ADD_OP,FOLLOW_ADD_OP_in_sumExpression201); 
            	    ADD_OP5_tree = 
            	    (Object)adaptor.create(ADD_OP5)
            	    ;
            	    root_0 = (Object)adaptor.becomeRoot(ADD_OP5_tree, root_0);


            	    pushFollow(FOLLOW_multExpr_in_sumExpression204);
            	    multExpr6=multExpr();

            	    state._fsp--;

            	    adaptor.addChild(root_0, multExpr6.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "sumExpression"


    public static class multExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "multExpr"
    // grammar\\Maths.g:50:1: multExpr : baseExpr ( MULT_OP ^ baseExpr )* ;
    public final MathsParser.multExpr_return multExpr() throws RecognitionException {
        MathsParser.multExpr_return retval = new MathsParser.multExpr_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token MULT_OP8=null;
        MathsParser.baseExpr_return baseExpr7 =null;

        MathsParser.baseExpr_return baseExpr9 =null;


        Object MULT_OP8_tree=null;

        try {
            // grammar\\Maths.g:51:2: ( baseExpr ( MULT_OP ^ baseExpr )* )
            // grammar\\Maths.g:51:5: baseExpr ( MULT_OP ^ baseExpr )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_baseExpr_in_multExpr219);
            baseExpr7=baseExpr();

            state._fsp--;

            adaptor.addChild(root_0, baseExpr7.getTree());

            // grammar\\Maths.g:51:14: ( MULT_OP ^ baseExpr )*
            loop2:
            do {
                int alt2=2;
                switch ( input.LA(1) ) {
                case MULT_OP:
                    {
                    alt2=1;
                    }
                    break;

                }

                switch (alt2) {
            	case 1 :
            	    // grammar\\Maths.g:51:15: MULT_OP ^ baseExpr
            	    {
            	    MULT_OP8=(Token)match(input,MULT_OP,FOLLOW_MULT_OP_in_multExpr222); 
            	    MULT_OP8_tree = 
            	    (Object)adaptor.create(MULT_OP8)
            	    ;
            	    root_0 = (Object)adaptor.becomeRoot(MULT_OP8_tree, root_0);


            	    pushFollow(FOLLOW_baseExpr_in_multExpr225);
            	    baseExpr9=baseExpr();

            	    state._fsp--;

            	    adaptor.addChild(root_0, baseExpr9.getTree());

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "multExpr"


    public static class baseExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "baseExpr"
    // grammar\\Maths.g:55:1: baseExpr : ( NUMBER | ( LPAREN ^ expression RPAREN ) );
    public final MathsParser.baseExpr_return baseExpr() throws RecognitionException {
        MathsParser.baseExpr_return retval = new MathsParser.baseExpr_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token NUMBER10=null;
        Token LPAREN11=null;
        Token RPAREN13=null;
        MathsParser.expression_return expression12 =null;


        Object NUMBER10_tree=null;
        Object LPAREN11_tree=null;
        Object RPAREN13_tree=null;

        try {
            // grammar\\Maths.g:56:2: ( NUMBER | ( LPAREN ^ expression RPAREN ) )
            int alt3=2;
            switch ( input.LA(1) ) {
            case NUMBER:
                {
                alt3=1;
                }
                break;
            case LPAREN:
                {
                alt3=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // grammar\\Maths.g:56:4: NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    NUMBER10=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_baseExpr239); 
                    NUMBER10_tree = 
                    (Object)adaptor.create(NUMBER10)
                    ;
                    adaptor.addChild(root_0, NUMBER10_tree);


                    }
                    break;
                case 2 :
                    // grammar\\Maths.g:59:3: ( LPAREN ^ expression RPAREN )
                    {
                    root_0 = (Object)adaptor.nil();


                    // grammar\\Maths.g:59:3: ( LPAREN ^ expression RPAREN )
                    // grammar\\Maths.g:60:4: LPAREN ^ expression RPAREN
                    {
                    LPAREN11=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_baseExpr252); 
                    LPAREN11_tree = 
                    (Object)adaptor.create(LPAREN11)
                    ;
                    root_0 = (Object)adaptor.becomeRoot(LPAREN11_tree, root_0);


                    pushFollow(FOLLOW_expression_in_baseExpr255);
                    expression12=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression12.getTree());

                    RPAREN13=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_baseExpr257); 
                    RPAREN13_tree = 
                    (Object)adaptor.create(RPAREN13)
                    ;
                    adaptor.addChild(root_0, RPAREN13_tree);


                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "baseExpr"

    // Delegated rules


 

    public static final BitSet FOLLOW_expression_in_mathExpr172 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_mathExpr174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sumExpression_in_expression187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multExpr_in_sumExpression198 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_ADD_OP_in_sumExpression201 = new BitSet(new long[]{0x0000000000000140L});
    public static final BitSet FOLLOW_multExpr_in_sumExpression204 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_baseExpr_in_multExpr219 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_MULT_OP_in_multExpr222 = new BitSet(new long[]{0x0000000000000140L});
    public static final BitSet FOLLOW_baseExpr_in_multExpr225 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_NUMBER_in_baseExpr239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_baseExpr252 = new BitSet(new long[]{0x0000000000000140L});
    public static final BitSet FOLLOW_expression_in_baseExpr255 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_RPAREN_in_baseExpr257 = new BitSet(new long[]{0x0000000000000002L});

}