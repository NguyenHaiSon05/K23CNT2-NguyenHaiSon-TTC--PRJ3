package com.k23cnt2.nhs.lesson03.nhsservice;

import com.k23cnt2.nhs.lesson03.nhsentity.NhsStudent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NhsStudentService {
    private List<NhsStudent> students = new ArrayList<>();

    // Khởi tạo danh sách sinh viên mẫu
    public NhsStudentService() {
        students.add(new NhsStudent(1L, "Nguyễn Hải Sơn", 20, "Nam", "Hà Nội", ")388604819", "sonnguyennr111@gmail.com"));
        students.add(new NhsStudent(2L, "Trần Thị B", 21, "Nữ", "Đồng Nai", "0988223344", "thib@gmail.com"));
        students.add(new NhsStudent(3L, "Phạm Văn C", 19, "Nam", "Bình Dương", "0911223344", "vanc@gmail.com"));
        students.add(new NhsStudent(4L, "Lê Thị D", 22, "Nữ", "Cần Thơ", "0909998888", "thid@gmail.com"));
    }

    // Lấy toàn bộ sinh viên
    public List<NhsStudent> getAllStudents() {
        return students;
    }

    // Lấy sinh viên theo ID
    public NhsStudent getStudentById(Long id) {
        return students.stream()
                .filter(stu -> stu.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Thêm sinh viên mới
    public NhsStudent addStudent(NhsStudent student) {
        students.add(student);
        return student;
    }

    // Cập nhật thông tin sinh viên
    public NhsStudent updateStudent(Long id, NhsStudent student) {
        for (NhsStudent item : students) {
            if (item.getId().equals(id)) {
                item.setName(student.getName());
                item.setAge(student.getAge());
                item.setGender(student.getGender());
                item.setAddress(student.getAddress());
                item.setPhone(student.getPhone());
                item.setEmail(student.getEmail());
                return item;
            }
        }
        return null;
    }

    // Xóa sinh viên theo ID
    public boolean deleteStudent(Long id) {
        NhsStudent check = getStudentById(id);
        return students.remove(check);
    }
}
