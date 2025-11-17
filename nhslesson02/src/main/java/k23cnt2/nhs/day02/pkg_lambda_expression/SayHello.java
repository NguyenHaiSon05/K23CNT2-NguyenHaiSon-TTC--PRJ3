package k23cnt2.nhs.day02.pkg_lambda_expression;

@FunctionalInterface
interface SayHelloInterface {
    void sayHello();
}

public class SayHello {
    public static void main(String[] args) {

        SayHelloInterface hello = () -> {
            System.out.println("Hello World!");
        };

        hello.sayHello();
    }
}
