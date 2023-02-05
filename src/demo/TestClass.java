package demo;

public class TestClass {

    void someMethod() {
        System.out.println("Without anno");
    }

    @AfterSuite
    void afterMethod() {
        System.out.println("Method after");
    }

    @BeforeSuite
    void beforeMethod() {
        System.out.println("Method before");
    }

//    @BeforeSuite
//    void secondBeforeMethod() {
//        System.out.println("Method before x2");
//    }

    @Test(priority = 6)
    void testAnno6() {
        System.out.println("Test, priority is 6");
    }

    @Test(priority = 2)
    void testAnno2() {
        System.out.println("Test, priority is 2");
    }

    @Test(priority = 7)
    void testAnno7() {
        System.out.println("Test, priority is 7");
    }

//    @Test(priority = 11)
//    void testAnno11() {
//        System.out.println("Test, priority is 11?");
//    }
}
