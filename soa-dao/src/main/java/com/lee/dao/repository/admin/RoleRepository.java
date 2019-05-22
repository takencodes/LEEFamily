package com.lee.dao.repository.admin;

import com.lee.dao.mysql.admin.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by defei on 3/9/16.
 */
@Repository public interface RoleRepository extends JpaRepository<Role, Long> {

}
