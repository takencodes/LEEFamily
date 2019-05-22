package com.lee.dao.repository.admin;


import com.lee.dao.mysql.admin.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findBymainMenu_idOrderByCodeAsc(Long mainMenuId);

}
