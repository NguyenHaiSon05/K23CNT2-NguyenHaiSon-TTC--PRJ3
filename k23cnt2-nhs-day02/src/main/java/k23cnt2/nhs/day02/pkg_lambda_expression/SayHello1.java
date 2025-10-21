package k23cnt2.nhs.day02.pkg_lambda_expression;

@FunctionalInterface
interface SayHello1Interface {
    void sayHello(String name);
}

public class SayHello1 {
    public static void main(String[] args) {

        SayHello1Interface hello = (name) -> {
            System.out.println("Xin chào " + name + " từ Lambda Expression!");
        };

        hello.sayHello("Nguyen Hai Son");
    }
}
