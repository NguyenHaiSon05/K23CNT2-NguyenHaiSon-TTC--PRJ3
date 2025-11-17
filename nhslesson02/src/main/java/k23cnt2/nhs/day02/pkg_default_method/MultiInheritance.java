package k23cnt2.nhs.day02.pkg_default_method;

public class MultiInheritance implements Interface1, Interface2 {

    @Override
    public void method1() {
        System.out.println("MultiInheritance_method1");
    }

    // Trùng với default method trong Interface2
    @Override
    public void method2() {
        System.out.println("MultiInheritance_method2");
    }

    public static void main(String[] args) {
        MultiInheritance mi = new MultiInheritance();
        mi.method1();
        mi.method2();
    }
}
