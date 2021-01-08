package org.example.repository.admin;

import org.example.entity.tertiary.Admin;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(transactionManager = "tertiaryTransactionManager")
public interface AdminRepository {
    Admin getAdminByID(int id);

    boolean insertAdmin(Admin admin);

    boolean deleteAdminByID(int id);

    boolean updateAdminByID(Admin admin, int id);
}
