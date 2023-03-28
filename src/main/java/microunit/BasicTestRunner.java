package microunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BasicTestRunner extends TestRunner {
    public BasicTestRunner(Class<?> testClass) {
        super(testClass);
    }

    @Override
    public void runTestMethods() {
        try {
            int numberOfTests = 0;
            int numberOfFailures = 0;
            int numberOfErrors = 0;

            for (Method method : getAnnotatedMethods(Test.class)) {
                System.out.println(method);
                Object instance = testClass
                        .getConstructor().newInstance();
                try {
                    method.invoke(instance);
                    if (method.getAnnotation(Test.class).expected() != Test.None.class) {
                        numberOfFailures++;
                    }
                } catch (InvocationTargetException e) {
                    e.getCause().printStackTrace(System.out);
                    if (e.getCause() instanceof AssertionError) {
                        numberOfFailures++;
                    } else {
                        var testAnnotation = method.getAnnotation(Test.class);
                        if (testAnnotation.expected() != e.getCause().getClass()) {
                            numberOfErrors++;
                        }
                    }
                }
                numberOfTests++;
            }
            System.out.printf("Executed: %d\n", numberOfTests);
            System.out.printf("Failures: %d\n", numberOfFailures);
            System.out.printf("Errors: %d\n", numberOfErrors);
        } catch (ReflectiveOperationException | IllegalArgumentException e) {
            throw new InvalidTestClassException(e);
        }
    }
}
