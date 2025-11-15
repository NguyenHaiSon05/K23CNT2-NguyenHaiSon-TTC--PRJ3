package com.k23cnt2.nhs.lesson06.Nhsservice;

import com.k23cnt2.nhs.lesson06.Nhsdto.NhsCustomerDTO;
import com.k23cnt2.nhs.lesson06.Nhsentity.NhsCustomer;
import com.k23cnt2.nhs.lesson06.Nhsrepository.NhsCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NhsCustomerService {

    private final NhsCustomerRepository repo;

    public List<NhsCustomer> findAll() {
        return repo.findAll();
    }

    public Optional<NhsCustomer> findById(Long id) {
        return repo.findById(id);
    }

    public boolean save(NhsCustomerDTO dto) {
        try {
            NhsCustomer c = NhsCustomer.builder()
                    .nhsUsername(dto.getNhsUsername())
                    .nhsPassword(dto.getNhsPassword())
                    .nhsFullName(dto.getNhsFullName())
                    .nhsAddress(dto.getNhsAddress())
                    .nhsPhone(dto.getNhsPhone())
                    .nhsEmail(dto.getNhsEmail())
                    .nhsBirthDay(dto.getNhsBirthDay())
                    .nhsActive(dto.getNhsActive())
                    .build();

            repo.save(c);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(Long id, NhsCustomerDTO dto) {
        return repo.findById(id).map(c -> {

            c.setNhsUsername(dto.getNhsUsername());
            c.setNhsPassword(dto.getNhsPassword());
            c.setNhsFullName(dto.getNhsFullName());
            c.setNhsAddress(dto.getNhsAddress());
            c.setNhsPhone(dto.getNhsPhone());
            c.setNhsEmail(dto.getNhsEmail());
            c.setNhsBirthDay(dto.getNhsBirthDay());
            c.setNhsActive(dto.getNhsActive());

            repo.save(c);
            return true;

        }).orElse(false);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
