package example;

import microunit.Assert;
import microunit.BasicTestRunner;
import microunit.Test;

import java.io.IOException;

public class ExampleTest {
    @Test
    public void a() {
        Assert.assertTrue(1 + 1 == 2,
                "This should always be true");
    }

    @Test
    public void b() {
        Assert.assertTrue(1 + 1 == 3,
                "This should always be false");
    }

    @Test(expected = IOException.class)
    public void c() {
    }

    @Test(expected = IOException.class)
    public void d() throws IOException {
        throw new IOException("A random I/O exception");
    }

    @Test(expected = IOException.class)
    public void e() {
        throw new RuntimeException("Oopps");
    }

    @Test
    public void f() {
    }

    public static void main(String[] args) {
        new BasicTestRunner(ExampleTest.class)
                .runTestMethods();
    }
}
