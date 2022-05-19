package team.project.WhatToEatToday.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.project.WhatToEatToday.domain.member.Admin;
import team.project.WhatToEatToday.repository.member.AdminRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {
    private final EntityManager em;
    private final AdminRepository adminRepository;


    @Transactional
    public String join(Admin admin) {
        validateDuplicateAdmin(admin);
        adminRepository.save(admin);
        return admin.getId();
    }

    private void validateDuplicateAdmin(Admin admin) {
        List<Admin> findAdmins = adminRepository.findById(admin.getId());
        if(!findAdmins.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    //회원 전체 조회
    public List<Admin> findAdmins() {
        return adminRepository.findAll();
    }

    public Admin findOne(String adminId) {
        return adminRepository.findOne(adminId);
    }

    @Transactional
    public String delete(Admin admin) {
        String deletedAdminId = admin.getId();
        adminRepository.delete(admin);
        return deletedAdminId;
    }

}
