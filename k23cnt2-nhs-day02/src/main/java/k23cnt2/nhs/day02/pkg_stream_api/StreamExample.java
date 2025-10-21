package k23cnt2.nhs.day02.pkg_stream_api;

import java.util.Arrays;
import java.util.List;

public class StreamExample {

    List<Integer> integerList = Arrays.asList(11, 22, 35, 33, 44, 66);

    // Đếm các số chẵn
    // Không dùng Stream
    public void withoutStream() {
        int count = 0;
        for (Integer integer : integerList) {
            if (integer % 2 == 0)
                count++;
        }
        System.out.println("withoutStream => Số phần tử chẵn: " + count);
    }

    // Dùng Stream
    public void withStream() {
        long count = integerList.stream()
                .filter(num -> num % 2 == 0)
                .count();
        System.out.println("withStream => Số phần tử chẵn: " + count);
    }

    public static void main(String[] args) {
        StreamExample streamExample = new StreamExample();
        streamExample.withoutStream();
        streamExample.withStream();
    }
}
