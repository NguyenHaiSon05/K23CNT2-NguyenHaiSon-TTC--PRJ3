package k23cnt2.nhs.day02.pkg_collection_api_enhencements;

import java.util.HashMap;
import java.util.Map;

public class ForEachMapExample {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Java Spring");
        map.put(2, "PHP Laravel");
        map.put(3, "Javascript");

        System.out.println("Duyệt Map bằng forEach với Lambda:");
        map.forEach((key, value) -> System.out.println(key + " = " + value));
    }
}