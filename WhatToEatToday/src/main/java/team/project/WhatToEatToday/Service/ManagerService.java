package team.project.WhatToEatToday.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.project.WhatToEatToday.domain.member.Customer;
import team.project.WhatToEatToday.domain.member.Manager;
import team.project.WhatToEatToday.repository.member.ManagerRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagerService {
    private final EntityManager em;
    private final ManagerRepository managerRepository;

    @Transactional
    public String join(Manager manager) {
        validateDuplicateAdmin(manager);
        managerRepository.save(manager);
        return manager.getId();
    }

    private void validateDuplicateAdmin(Manager manager) {
        List<Manager> findAdmins = managerRepository.findById(manager.getId());
        if(!findAdmins.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    //회원 전체 조회
    public List<Manager> findManagers() {
        return managerRepository.findAll();
    }

    public Manager findOne(String managerId) {
        return managerRepository.findOne(managerId);
    }

    @Transactional
    public String delete(Manager manager) {
        String deletedManagerId = manager.getId();
        managerRepository.delete(manager);
        return deletedManagerId;
    }
}
