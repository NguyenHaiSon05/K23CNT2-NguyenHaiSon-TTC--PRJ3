package k23cnt2.nhs.day02.pkg_default_method;

public interface Shape {
    void draw();

    default void setColor(String color) {
        System.out.println("Vẽ hình với màu: " + color);
    }
}
