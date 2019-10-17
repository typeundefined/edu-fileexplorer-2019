package ru.amm.fileexplorer.server;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import ru.amm.maths.grammar.MathsParser;
import ru.amm.maths.grammar.MathsLexer;

public class MathEvaluator {
    public double eval(String s) {
        try {
            MathsLexer tokenSource = new MathsLexer(new ANTLRStringStream(s));
            MathsParser parser = new MathsParser(new CommonTokenStream(tokenSource));
            MathsParser.mathExpr_return result = parser.mathExpr();
            return evaluateTree((CommonTree) result.getTree());
        } catch (RecognitionException e) {
            throw new RuntimeException(e);
        }

    }

    private double evaluateTree(CommonTree tree) {
        Token token = tree.getToken();
        switch (token.getType()) {
            case MathsLexer.NUMBER:
                return Double.parseDouble(token.getText());
            case MathsLexer.ADD_OP:
                return evaluateTree((CommonTree) tree.getChild(0)) + evaluateTree((CommonTree) tree.getChild(1));
            default:
                throw new RuntimeException("Unsupported token or operation given");
        }
    }
}
