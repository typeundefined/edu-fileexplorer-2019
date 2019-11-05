import org.junit.Test;
import ru.amm.fileexplorer.server.MathEvaluator;

import static java.lang.Math.abs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MathEvaluatorTest {
    private static final double EPS = 1e-9;

    @Test
    public void evaluatorWorks() {
        MathEvaluator calc = new MathEvaluator();
        calc.eval("12");
    }

    @Test
    public void numberParsesIntoNumber() {
        MathEvaluator calc = new MathEvaluator();
        double result = calc.eval("12");
        assertTrue(abs(12.0 - result) < EPS);
    }

    @Test
    public void addOperationWorks() {
        MathEvaluator calc = new MathEvaluator();
        double result = calc.eval("12+3");
        assertTrue(abs(15.0 - result) < EPS);
    }

    @Test
    public void spacesIgnored() {
        MathEvaluator calc = new MathEvaluator();
        double result = calc.eval("12   + 3");
        assertTrue(abs(15.0 - result) < EPS);
    }
}
