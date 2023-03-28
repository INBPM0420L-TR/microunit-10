package microunit;

public class Assert {
    public static void fail(String message) {
        if (message == null) {
            throw new AssertionError();
        } else {
            throw new AssertionError(message);
        }
    }

    public static void assertTrue(boolean condition) {
        /*
        if (!condition) {
            throw new AssertionError();
        }

         */
        assertTrue(condition, null);
    }

    public static void assertTrue(
            boolean condition,
            String message) {

        if (!condition) {
            // throw new AssertionError(message);
            fail(message);
        }
    }
}
