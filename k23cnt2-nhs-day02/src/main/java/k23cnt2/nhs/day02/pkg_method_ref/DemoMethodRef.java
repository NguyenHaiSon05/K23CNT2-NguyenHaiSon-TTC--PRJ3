package k23cnt2.nhs.day02.pkg_method_ref;

import java.util.Arrays;

@FunctionalInterface
interface ExecuteFunction {
    public int execute(int a, int b);
}

class MathUtils {
    public MathUtils() {}
    public MathUtils(String s) {
        System.out.println("MathUtils: " + s);
    }

    static int sum(int a, int b) {
        return a + b;
    }

    static int minus(int a, int b) {
        return a - b;
    }

    int multiply(int a, int b) {
        return a * b;
    }
}

public class DemoMethodRef {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        // Tham chiếu đến static method
        int sum = doAction(a, b, MathUtils::sum);
        System.out.println(a + " + " + b + " = " + sum);

        int minus = doAction(a, b, MathUtils::minus);
        System.out.println(a + " - " + b + " = " + minus);

        // Tham chiếu đến instance method của đối tượng cụ thể
        MathUtils mathUtils = new MathUtils();
        int multiply = doAction(a, b, mathUtils::multiply);
        System.out.println(a + " * " + b + " = " + multiply);

        // Tham chiếu đến instance method của một kiểu cụ thể
        String[] stringArray = { "Java", "C++", "PHP", "C#", "Javascript" };
        Arrays.sort(stringArray, String::compareToIgnoreCase);
        for (String str : stringArray) {
            System.out.println(str);
        }
    }

    static int doAction(int a, int b, ExecuteFunction func) {
        return func.execute(a, b);
    }
}
