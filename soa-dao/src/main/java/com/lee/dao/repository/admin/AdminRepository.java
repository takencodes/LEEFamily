package com.lee.dao.repository.admin;

import com.lee.dao.mysql.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by defei on 3/9/16.
 */
@Repository public interface AdminRepository extends JpaRepository<Admin, String> {

    List<Admin> findByEnabled(boolean enabled);

}
