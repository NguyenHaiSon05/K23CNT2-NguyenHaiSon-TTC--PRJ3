package k23cnt2.nhs.day02.pkg_lambda_expression;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class SortedSample {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("Java SpringBoot");
        list.add("C# .NETCore");
        list.add("PHP - Laravel");

        // Sắp xếp bằng lambda expression
        Collections.sort(list, (String s1, String s2) -> {
            return s1.compareTo(s2);
        });

        for (String str : list) {
            System.out.println(str);
        }
    }
}
