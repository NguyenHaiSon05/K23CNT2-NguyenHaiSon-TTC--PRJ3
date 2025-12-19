package com.freshfruit.nhscontroller.admin;

import com.freshfruit.nhsentity.OrderEntity;
import com.freshfruit.nhsentity.OrderStatus;
import com.freshfruit.nhsrepository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final OrderRepository orderRepository;

    // ======================
    // DANH SÁCH ĐƠN HÀNG
    // ======================
    @GetMapping
    public String orderList(Model model) {

        List<OrderEntity> orders =
                orderRepository.findAllByOrderByCreatedAtDesc();

        model.addAttribute("orders", orders);
        return "admin/order/list";

    }

    // ======================
    // CHI TIẾT ĐƠN
    // ======================
    @GetMapping("/{id}")
    public String orderDetail(@PathVariable Integer id, Model model) {

        OrderEntity order =
                orderRepository.findById(id).orElse(null);

        if (order == null) {
            return "redirect:/admin/orders";
        }

        model.addAttribute("order", order);
        model.addAttribute("items", order.getItems());
        return "admin/order/detail";

    }

    // ======================
    // XÁC NHẬN THANH TOÁN QR
    // ======================
    @PostMapping("/confirm-payment/{id}")
    public String confirmPayment(@PathVariable Integer id) {

        OrderEntity order =
                orderRepository.findById(id).orElse(null);

        if (order != null &&
                order.getStatus() == OrderStatus.WAIT_PAYMENT) {

            order.setStatus(OrderStatus.PAID);
            orderRepository.save(order);
        }

        return "redirect:/admin/orders/" + id;
    }

    // ======================
    // XÁC NHẬN XỬ LÝ COD
    // ======================
    @PostMapping("/confirm-order/{id}")
    public String confirmOrder(@PathVariable Integer id) {

        OrderEntity order =
                orderRepository.findById(id).orElse(null);

        if (order != null &&
                order.getStatus() == OrderStatus.PENDING) {

            order.setStatus(OrderStatus.PAID);
            orderRepository.save(order);
        }

        return "redirect:/admin/orders/" + id;
    }

    // ======================
// CẬP NHẬT TRẠNG THÁI (DÙNG CHUNG)
// ======================
    @PostMapping("/update-status")
    public String updateStatus(
            @RequestParam Integer orderId,
            @RequestParam OrderStatus status
    ) {
        OrderEntity order = orderRepository.findById(orderId).orElse(null);

        if (order != null) {
            order.setStatus(status);
            orderRepository.save(order);
        }

        return "redirect:/admin/orders";
    }

}
