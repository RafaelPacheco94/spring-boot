package org.example.service;

import org.example.entity.tertiary.Admin;
import org.example.repository.admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin getAdminByID(int id) {
        return adminRepository.getAdminByID(id);
    }

    public boolean insertAdmin(Admin admin) {
        return adminRepository.insertAdmin(admin);
    }

    public boolean deleteAdminByID(int id) {
        return adminRepository.deleteAdminByID(id);
    }

    public boolean updateAdminByID(Admin admin, int id) {
        return adminRepository.updateAdminByID(admin, id);
    }

}
