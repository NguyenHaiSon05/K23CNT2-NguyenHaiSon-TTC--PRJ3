package com.k23cnt2.nhs.lesson06.Nhscontroller;

import com.k23cnt2.nhs.lesson06.Nhsdto.NhsStudentDTO;
import com.k23cnt2.nhs.lesson06.Nhsentity.NhsStudent;
import com.k23cnt2.nhs.lesson06.Nhsservice.NhsStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nhs/students")
@RequiredArgsConstructor
public class NhsStudentController {

    @Autowired
    private final NhsStudentService nhsStudentService;

    // 1. Hiển thị danh sách
    @GetMapping("")
    public String getStudents(Model model) {
        model.addAttribute("students", nhsStudentService.findAll());
        return "nhs-student-list";
    }

    // 2. Hiển thị form thêm mới
    @GetMapping("/add-new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new NhsStudent());
        return "nhs-student-add";
    }

    // 3. Hiển thị form sửa
    @GetMapping("/edit/{id}")
    public String showFormForUpdate(
            @PathVariable("id") Long id,
            Model model) {

        NhsStudentDTO student = nhsStudentService.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid student id: " + id));

        model.addAttribute("student", student);
        return "nhs-student-edit";
    }

    // 4. Lưu mới
    @PostMapping("/save")
    public String saveStudent(
            @ModelAttribute("student") NhsStudentDTO studentDTO) {

        nhsStudentService.save(studentDTO);
        return "redirect:/nhs/students";
    }

    // 5. Cập nhật
    @PostMapping("/update/{id}")
    public String updateStudent(
            @PathVariable("id") Long id,
            @ModelAttribute("student") NhsStudentDTO updatedStudent) {

        nhsStudentService.updateStudentById(id, updatedStudent);
        return "redirect:/nhs/students";
    }

    // 6. Xóa
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        nhsStudentService.deleteStudent(id);
        return "redirect:/nhs/students";
    }
}
