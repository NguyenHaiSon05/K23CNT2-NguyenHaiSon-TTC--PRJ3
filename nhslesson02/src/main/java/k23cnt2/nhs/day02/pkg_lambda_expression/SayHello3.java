package k23cnt2.nhs.day02.pkg_lambda_expression;

import java.util.Arrays;
import java.util.List;

public class SayHello3 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java SpringBoot", "C# NetCore", "PHP", "JavaScript");

        // Using Lambda expression
        list.forEach(item -> System.out.println(item));

        System.out.println("====================");

        list.forEach(System.out::println);
    }
}
