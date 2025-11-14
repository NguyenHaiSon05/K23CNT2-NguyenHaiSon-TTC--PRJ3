package com.k23cnt2.nhs.lesson05.Nhscontroller;

import com.k23cnt2.nhs.lesson05.Nhsentity.NhsStudent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class NhsStudentController {

    // tạm thời lưu trong bộ nhớ (không dùng DB)
    private List<NhsStudent> students = new ArrayList<>();
    private Long autoId = 1L;

    public NhsStudentController() {
        // dữ liệu mẫu
        students.add(new NhsStudent(autoId++, "Nguyễn Văn A",
                "a@ntu.edu.vn", "K23CNT2", "0912345678", "Hà Nội"));
        students.add(new NhsStudent(autoId++, "Trần Thị B",
                "b@ntu.edu.vn", "K23CNT2", "0987654321", "Hà Nội"));
    }

    // Trang dashboard admin đơn giản
    @GetMapping
    public String adminHome() {
        return "admin/admin-home";
    }

    // ====== LIST ======
    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", students);
        return "admin/student-list";   // -> templates/admin/student-list.html
    }

    // ====== ADD FORM ======
    @GetMapping("/students/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new NhsStudent());
        model.addAttribute("formTitle", "Thêm sinh viên");
        return "admin/student-form";
    }

    // ====== EDIT FORM ======
    @GetMapping("/students/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        NhsStudent found = students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (found == null) {
            return "redirect:/admin/students";
        }

        model.addAttribute("student", found);
        model.addAttribute("formTitle", "Sửa thông tin sinh viên");
        return "admin/student-form";
    }

    // ====== SAVE (ADD + EDIT) ======
    @PostMapping("/students/save")
    public String saveStudent(@ModelAttribute("student") NhsStudent student) {

        if (student.getId() == null) { // ADD
            student.setId(autoId++);
            students.add(student);
        } else { // EDIT
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getId().equals(student.getId())) {
                    students.set(i, student);
                    break;
                }
            }
        }

        return "redirect:/admin/students";
    }

    // ====== DELETE ======
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        students.removeIf(s -> s.getId().equals(id));
        return "redirect:/admin/students";
    }
}
