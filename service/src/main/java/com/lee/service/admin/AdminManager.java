package com.lee.service.admin;



import com.lee.dao.mysql.admin.*;

import java.util.List;

/**
 * Created by defei on 3/9/16.
 */
public interface AdminManager {

    void createAdmin(Admin admin, Admin creator, List<Integer> provinceIds);

    boolean checkLogin(String userName, String password);

    Admin getAdmin(String id);

    List<Admin> listEnableAdmins();

    List<Admin> listDisableAdmins();

    void deleteAdmin(String id);

    void deleteAdmin(Admin admin);

    void updateAdmin(Admin admin);

    void updateAdmin(Admin admin, List<Integer> provinceIds);

    boolean checkAdminExists(String id);

    List<Role> listAllRoles();

    Role getRole(Long id);

    void createRole(Role role);

    void updateRole(Role role);

    void deleteRole(Long id);

    void deleteRole(Role role);

    List<MainMenu> listAllMainMenu();

    MainMenu getMainMenu(Long id);

    void createMainMenu(MainMenu mainMenu);

    void deleteMainMenu(Long id);

    void deleteMainMenu(MainMenu mainMenu);

    void updateMainMenu(MainMenu mainMenu);


    List<MenuItem> listMenuItemByMainMenuId(Long mainMenuId);

    MenuItem getMenuItem(Long id);

    void createMenuItem(MenuItem menuItem);

    void deleteMenuItem(Long id);

    void deleteMenuItem(MenuItem menuItem);

    void updateMenuItem(MenuItem menuItem);

    List<Menu> initAdminMenu(Admin admin);

//    List<MenuGroupVo> initAdminMenuGroup(Admin admin);

    Resource getResource(Long id);

    void createResource(Resource resource);

    void deleteResource(Long id);

    void deleteResource(Resource resource);

    void updateResource(Resource resource);

    void updateItemParentId(Long parentId, Long itemId);
}
