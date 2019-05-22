package com.lee.dao.repository.admin;


import com.lee.dao.mysql.admin.MenuGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuGroupRepository extends JpaRepository<MenuGroup, Long> {

    List<MenuGroup> findByOrderByIdxAsc();
}
