package com.k23cnt2.nhs.lesson03.nhsdto;

/**
 * NhsStudentDto:
 *  - Dùng để trả dữ liệu ra API (ẩn bớt/gom bớt field nếu muốn)
 *  - Ở đây demo: chỉ lấy id, name, age, email
 */
public class NhsStudentDto {
    private Long id;
    private String name;
    private int age;
    private String email;

    public NhsStudentDto() {
    }

    public NhsStudentDto(Long id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
