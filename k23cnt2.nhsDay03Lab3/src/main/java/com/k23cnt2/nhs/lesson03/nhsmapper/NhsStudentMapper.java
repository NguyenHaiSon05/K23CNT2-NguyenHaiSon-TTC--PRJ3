package com.k23cnt2.nhs.lesson03.nhsmapper;

import com.k23cnt2.nhs.lesson03.nhsdto.NhsStudentDto;
import com.k23cnt2.nhs.lesson03.nhsentity.NhsStudent;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper chuyển đổi giữa NhsStudent (Entity) và NhsStudentDto (DTO)
 */
public class NhsStudentMapper {

    // Entity -> DTO
    public static NhsStudentDto toDto(NhsStudent student) {
        if (student == null) {
            return null;
        }
        return new NhsStudentDto(
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getEmail()
        );
    }

    // DTO -> Entity (dùng khi nhận dữ liệu từ client)
    public static NhsStudent toEntity(NhsStudentDto dto) {
        if (dto == null) {
            return null;
        }
        NhsStudent student = new NhsStudent();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        // Các field còn lại có thể set sau hoặc mặc định
        student.setGender(null);
        student.setAddress(null);
        student.setPhone(null);
        student.setEmail(dto.getEmail());
        return student;
    }

    // List<Entity> -> List<DTO>
    public static List<NhsStudentDto> toDtoList(List<NhsStudent> list) {
        List<NhsStudentDto> result = new ArrayList<>();
        if (list != null) {
            for (NhsStudent st : list) {
                result.add(toDto(st));
            }
        }
        return result;
    }
}
