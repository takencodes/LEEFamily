package com.lee.dao.repository.admin;

import com.lee.dao.mysql.admin.MainMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository public interface MainMenuRepository extends JpaRepository<MainMenu, Long> {

    List<MainMenu> findByOrderByCodeAscNameAsc();

    @Modifying
    @Query(value = "update adm_menuitem set mainMenu_id=:mainMenu_id where id=:id", nativeQuery = true)
    void updateItemParentId(@Param("mainMenu_id") Long mainMenu_id, @Param("id") Long id);
}
