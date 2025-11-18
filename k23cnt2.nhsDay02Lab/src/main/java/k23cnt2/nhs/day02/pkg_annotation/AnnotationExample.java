package k23cnt2.nhs.day02.pkg_annotation;

import java.lang.annotation.*;

// Định nghĩa Annotation tùy chỉnh
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface DevInfo {
    String author();
    String date();
    String version() default "1.0";
}

// Áp dụng Annotation
public class AnnotationExample {

    @DevInfo(author = "Nguyễn Hải Sơn", date = "2025-10-21", version = "2.1")
    public void display() {
        System.out.println("Phương thức display() đang được gọi...");
    }

    public static void main(String[] args) throws Exception {
        AnnotationExample obj = new AnnotationExample();

        // Gọi phương thức
        obj.display();

        // Đọc thông tin Annotation bằng Reflection
        DevInfo info = obj.getClass()
                .getMethod("display")
                .getAnnotation(DevInfo.class);

        System.out.println("Tác giả: " + info.author());
        System.out.println("Ngày tạo: " + info.date());
        System.out.println("Phiên bản: " + info.version());
    }
}
