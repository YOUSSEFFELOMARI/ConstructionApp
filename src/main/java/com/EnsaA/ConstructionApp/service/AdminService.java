package com.EnsaA.ConstructionApp.service;

import com.EnsaA.ConstructionApp.model.Admin;
import com.EnsaA.ConstructionApp.repository.AdminRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    public Page<Admin> showAllAdmin(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return adminRepository.findAll(pageable);
    }

    public void create(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        if (adminRepository.existsById(admin.getAdminId()))
            throw new EntityExistsException("Admin already stored in database - ID : "+admin.getAdminId()) {};
        adminRepository.save(admin);
    }

    public void delete(int id) {
        if (!adminRepository.existsById(id))
            throw new EntityNotFoundException("Admin not found - ID : "+id) {};
        adminRepository.deleteById(id);
    }

    public void update(Admin admin) {
        if (!adminRepository.existsById(admin.getAdminId()))
            throw new EntityNotFoundException("Admin not found - ID : "+admin.getAdminId()) {};
        adminRepository.save(admin);
    }

    public Admin find(int id) {
        return adminRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Admin not found - ID : "+id) {});
    }

    public long count() {
        return adminRepository.count();
    }
}
