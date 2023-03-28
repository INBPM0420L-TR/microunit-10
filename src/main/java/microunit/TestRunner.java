package microunit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TestRunner {
    protected Class<?> testClass;

    public TestRunner(Class<?> testClass) {
        this.testClass = testClass;
    }

    public abstract void runTestMethods();

    protected List<Method> getAnnotatedMethods(
            Class<? extends Annotation> annotationClass) {
        return Arrays.stream(testClass.getDeclaredMethods())
                .filter(method -> method
                        .isAnnotationPresent(annotationClass))
                //.toList();
                .collect(Collectors.toList());

    }
}
