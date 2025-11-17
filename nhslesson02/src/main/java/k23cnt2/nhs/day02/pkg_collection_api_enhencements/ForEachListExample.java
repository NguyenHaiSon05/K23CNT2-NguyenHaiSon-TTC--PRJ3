package k23cnt2.nhs.day02.pkg_collection_api_enhencements;

import java.util.Arrays;
import java.util.List;

public class ForEachListExample {
    public static void main(String[] args) {
        List<String> languages = Arrays.asList("Java Spring", "C#", "NetCore API", "PHP Laravel", "Javascript");

        System.out.println("Sử dụng biểu thức lambda:");
        languages.forEach(lang -> System.out.println(lang));

        System.out.println("\nSử dụng method reference:");
        languages.forEach(System.out::println);
    }
}
