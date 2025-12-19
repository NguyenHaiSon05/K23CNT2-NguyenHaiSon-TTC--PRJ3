package com.freshfruit.nhsentity;

public enum OrderStatus {
    WAIT_PAYMENT("Chờ xác nhận thanh toán"),
    PENDING("Chờ xử lý"),
    PAID("Đã thanh toán"),
    SHIPPING("Đang giao hàng"),
    COMPLETED("Giao hàng thành công"),
    CANCELLED("Đã hủy");

    private final String label;

    OrderStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
