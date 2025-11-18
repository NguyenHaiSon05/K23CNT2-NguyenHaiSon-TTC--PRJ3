package k23cnt2.nhs.day02.pkg_annotation;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimeExample {
    public static void main(String[] args) {
        // Lấy ngày giờ hiện tại
        LocalDate today = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();

        System.out.println("Hôm nay là: " + today);
        System.out.println("Giờ hiện tại: " + time);
        System.out.println("Ngày giờ hiện tại: " + dateTime);

        // Tạo một ngày cụ thể
        LocalDate birthday = LocalDate.of(2005, Month.MAY, 30);
        System.out.println("Ngày sinh: " + birthday);

        // Cộng trừ ngày
        LocalDate nextWeek = today.plusWeeks(1);
        System.out.println("Tuần sau là: " + nextWeek);

        // Định dạng ngày giờ
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("Định dạng ngày giờ: " + dateTime.format(formatter));

        // Tính khoảng cách giữa hai ngày
        Period period = Period.between(birthday, today);
        System.out.println("Tuổi: " + period.getYears() + " năm " +
                period.getMonths() + " tháng " +
                period.getDays() + " ngày");
    }
}
