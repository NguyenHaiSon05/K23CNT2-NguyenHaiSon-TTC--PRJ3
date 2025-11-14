package com.k23cnt2.nhs.lesson05.Nhsentity;

public class NhsStudent {
    private Long id;
    private String fullName;
    private String email;
    private String lop;      // lớp học
    private String phone;
    private String address;

    public NhsStudent() {
    }

    public NhsStudent(Long id, String fullName, String email,
                      String lop, String phone, String address) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.lop = lop;
        this.phone = phone;
        this.address = address;
    }

    // getter / setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
