package expression.exceptions;

import java.util.Arrays;

import static expression.Util.op;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ExceptionsAbsTest extends ExceptionsTest {
    public static final Reason SQRT = new Reason("sqrt");

    protected ExceptionsAbsTest() {
        unary.add(op("abs", Math::abs));
        unary.add(op("sqrt", this::sqrt));

        tests.addAll(Arrays.asList(
                op("abs -4", (x, y, z) -> 4L),
                op("abs " + Integer.MIN_VALUE, (x, y, z) -> error(OVERFLOW)),
                op("sqrt 4", (x, y, z) -> 2L),
                op("sqrt 8", (x, y, z) -> 2L),
                op("sqrt x * y * z", (x, y, z) -> sqrt(x) * y * z),
                op("sqrt(x * y * z)", (x, y, z) -> sqrt(x * y * z))
        ));
        parsingTest.addAll(Arrays.asList(
                op("hello", "hello"),
                op("abs", "abs"),
                op("abs()", "abs()"),
                op("abs(1, 2)", "abs(1, 2)"),
                op("abb 1", "abb 1"),
                op("abs *", "abs *"),
                op("absx", "absx")
        ));
    }

    private long sqrt(final long a) {
        return lift(a >= 0 ? (long) Math.sqrt(a) : error(SQRT)).getRight();
    }

    public static void main(final String[] args) {
        new ExceptionsAbsTest().run();
    }
}
