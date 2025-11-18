package com.k23cnt2.nhs.lesson06.Nhscontroller;

import com.k23cnt2.nhs.lesson06.Nhsdto.NhsCustomerDTO;
import com.k23cnt2.nhs.lesson06.Nhsentity.NhsCustomer;
import com.k23cnt2.nhs.lesson06.Nhsservice.NhsCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/nhs/customers")
public class NhsCustomerController {

    private final NhsCustomerService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("customers", service.findAll());
        return "nhs-customer-list";
    }

    @GetMapping("/add-new")
    public String addForm(Model model) {
        model.addAttribute("customer", new NhsCustomerDTO());
        return "nhs-customer-add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("customer") NhsCustomerDTO dto) {
        service.save(dto);
        return "redirect:/nhs/customers";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        NhsCustomer customer = service.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        model.addAttribute("customer", customer);
        return "nhs-customer-edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("customer") NhsCustomerDTO dto) {
        service.update(id, dto);
        return "redirect:/nhs/customers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/nhs/customers";
    }
}
