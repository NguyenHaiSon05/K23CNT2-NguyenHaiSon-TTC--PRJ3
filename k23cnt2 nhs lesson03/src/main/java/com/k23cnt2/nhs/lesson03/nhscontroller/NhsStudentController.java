package com.k23cnt2.nhs.lesson03.nhscontroller;

import com.k23cnt2.nhs.lesson03.nhsdto.NhsStudentDto;
import com.k23cnt2.nhs.lesson03.nhsentity.NhsStudent;
import com.k23cnt2.nhs.lesson03.nhsmapper.NhsStudentMapper;
import com.k23cnt2.nhs.lesson03.nhsservice.NhsStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NhsStudentController {

    @Autowired
    private NhsStudentService nhsStudentService;

    // ================== API gốc (Entity) ==================

    @GetMapping("/nhsstudents")
    public List<NhsStudent> getAllStudents() {
        return nhsStudentService.getAllStudents();
    }

    @GetMapping("/nhsstudents/{id}")
    public NhsStudent getStudentById(@PathVariable String id) {
        Long param = Long.parseLong(id);
        return nhsStudentService.getStudentById(param);
    }

    @PostMapping("/nhsstudents-add")
    public NhsStudent addStudent(@RequestBody NhsStudent student) {
        return nhsStudentService.addStudent(student);
    }

    @PutMapping("/nhsstudents/{id}")
    public NhsStudent updateStudent(@PathVariable String id, @RequestBody NhsStudent student) {
        Long param = Long.parseLong(id);
        return nhsStudentService.updateStudent(param, student);
    }

    @DeleteMapping("/nhsstudents/{id}")
    public boolean deleteStudent(@PathVariable String id) {
        Long param = Long.parseLong(id);
        return nhsStudentService.deleteStudent(param);
    }

    // ================== API nâng cao dùng DTO ==================

    // Trả danh sách dưới dạng DTO (ẩn bớt field)
    @GetMapping("/nhs-dto/students")
    public List<NhsStudentDto> getAllStudentsDto() {
        List<NhsStudent> list = nhsStudentService.getAllStudents();
        return NhsStudentMapper.toDtoList(list);
    }

    // Trả 1 sinh viên dạng DTO
    @GetMapping("/nhs-dto/students/{id}")
    public NhsStudentDto getStudentDto(@PathVariable String id) {
        Long param = Long.parseLong(id);
        NhsStudent student = nhsStudentService.getStudentById(param);
        return NhsStudentMapper.toDto(student);
    }

    // Nhận DTO từ client rồi convert sang Entity để lưu
    @PostMapping("/nhs-dto/students")
    public NhsStudentDto addStudentByDto(@RequestBody NhsStudentDto dto) {
        NhsStudent entity = NhsStudentMapper.toEntity(dto);
        nhsStudentService.addStudent(entity);
        return NhsStudentMapper.toDto(entity);
    }
}
