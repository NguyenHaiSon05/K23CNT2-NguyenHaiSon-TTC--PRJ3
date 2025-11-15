package com.k23cnt2.nhs.lesson06.Nhsservice;

import com.k23cnt2.nhs.lesson06.Nhsdto.NhsStudentDTO;
import com.k23cnt2.nhs.lesson06.Nhsentity.NhsStudent;
import com.k23cnt2.nhs.lesson06.Nhsrepository.NhsStudentRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class NhsStudentService {

    private NhsStudentRepository nhsStudentRepository;

    @Autowired
    public NhsStudentService(NhsStudentRepository nhsStudentRepository) {
        this.nhsStudentRepository = nhsStudentRepository;
    }

    // Lấy toàn bộ danh sách
    public List<NhsStudent> findAll() {
        return nhsStudentRepository.findAll();
    }

    // Tìm theo ID → trả DTO
    public Optional<NhsStudentDTO> findById(long nhsId) {
        return nhsStudentRepository.findById(nhsId)
                .map(student -> {
                    NhsStudentDTO dto = new NhsStudentDTO();
                    dto.setNhsId(student.getNhsId());
                    dto.setNhsName(student.getNhsName());
                    dto.setNhsEmail(student.getNhsEmail());
                    dto.setNhsAge(student.getNhsAge());
                    return dto;
                });
    }

    // Thêm mới
    public Boolean save(NhsStudentDTO dto) {
        try {
            NhsStudent student = new NhsStudent();
            student.setNhsName(dto.getNhsName());
            student.setNhsEmail(dto.getNhsEmail());
            student.setNhsAge(dto.getNhsAge());

            nhsStudentRepository.save(student);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    // Cập nhật
    public NhsStudent updateStudentById(long id, NhsStudentDTO updatedStudent) {
        return nhsStudentRepository.findById(id)
                .map(student -> {
                    student.setNhsName(updatedStudent.getNhsName());
                    student.setNhsEmail(updatedStudent.getNhsEmail());
                    student.setNhsAge(updatedStudent.getNhsAge());

                    return nhsStudentRepository.save(student);
                })
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid Student ID: " + id));
    }

    // Xóa
    public void deleteStudent(long id) {
        nhsStudentRepository.deleteById(id);
    }
}
