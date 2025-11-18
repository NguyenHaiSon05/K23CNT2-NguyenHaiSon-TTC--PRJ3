package com.k23cnt2.nhs.lesson05.Nhsentity;

public class NhsInfo {
    private String name;
    private String role;
    private String studentId;
    private String email;
    private String website;

    public NhsInfo() {
    }

    public NhsInfo(String name, String role, String studentId, String email, String website) {
        this.name = name;
        this.role = role;
        this.studentId = studentId;
        this.email = email;
        this.website = website;
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
