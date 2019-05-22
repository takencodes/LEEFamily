//package com.lee.service.admin;
//
//import org.apache.commons.lang3.StringUtils;
//import org.codelogger.utils.CollectionUtils;
//import org.hibernate.Hibernate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.security.core.userdetails.UserCache;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import static com.google.common.collect.Lists.newArrayList;
//import static com.google.common.collect.Maps.newHashMap;
//import static com.google.common.collect.Sets.newTreeSet;
//
///**
// * Created by defei on 3/9/16.
// */
//@Transactional
//public class AdminManagerImpl implements AdminManager, UserDetailsService {
//
//    private static final Logger log = LoggerFactory.getLogger(AdminManagerImpl.class);
//
//    @Autowired private MainMenuRepository mainMenuRepository;
//
//    @Autowired private MenuItemRepository menuItemRepository;
//
//    @Autowired private RoleRepository roleRepository;
//
//    @Autowired private AdminRepository adminRepository;
//
//    @Autowired private ResourceRepository resourceRepository;
//
//    @Autowired private DepotProvinceRepository depotProvinceRepository;
//
//    @Autowired
//    private LogRepository logRepository;
//
//    //@Autowired
//    private UserCache userCache;
//
//
//
//    public AdminManagerImpl() {
//
//    }
//
//    public void createAdmin(Admin admin, Admin creator,List<Integer> provinceIds) {
//        admin.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
//        if (creator != null)
//            admin.setCreateBy(creator.getUsername());
//        if(CollectionUtils.isNotEmpty(provinceIds)){
//            boolean province=false;
//            for(Integer id:provinceIds){
//                if (id == 9999) {
//                    province=true;
//                }
//            }
//            if(!province){
//                admin.setProvincePo(depotProvinceRepository.findAll(provinceIds));
//            }else {
//                admin.setProvincePo(depotProvinceRepository.findAll());
//            }
//
//        }
//        adminRepository.save(admin);
//    }
//
//
//    public boolean checkLogin(String userName, String password) {
//        Admin admin = adminRepository.findOne(userName);
//        return admin != null && StringUtils.equalsIgnoreCase(admin.getPassword(), password);
//    }
//
//    public Admin getAdmin(String id) {
//        return adminRepository.findOne(id);
//    }
//
//    public List<Admin> listEnableAdmins() {
//        return adminRepository.findByEnabled(true);
//    }
//
//    public List<Admin> listDisableAdmins() {
//        return adminRepository.findByEnabled(false);
//    }
//
//    public void deleteAdmin(String id) {
//        adminRepository.delete(id);
//    }
//
//
//    public void deleteAdmin(Admin admin) {
//        adminRepository.delete(admin);
//    }
//
//    public void updateAdmin(Admin admin) {
//        adminRepository.save(admin);
//    }
//
//    public void updateAdmin(Admin admin,List<Integer> provinceIds) {
//        if(CollectionUtils.isNotEmpty(provinceIds)){
//            admin.setProvincePo(depotProvinceRepository.findAll(provinceIds));
//        }
//        adminRepository.save(admin);
//    }
//
//    public boolean checkAdminExists(String id) {
//        return adminRepository.exists(id);
//    }
//
//    public List<Role> listAllRoles() {
//        return roleRepository.findAll();
//    }
//
//    public Role getRole(Long id) {
//        return roleRepository.findOne(id);
//    }
//
//    public void createRole(Role role) {
//        roleRepository.save(role);
//    }
//
//    public void updateRole(Role role) {
//        roleRepository.save(role);
//    }
//
//    public void deleteRole(Long id) {
//        roleRepository.delete(id);
//    }
//
//    public void deleteRole(Role role) {
//        roleRepository.delete(role);
//    }
//
//    public List<MainMenu> listAllMainMenu() {
//        return mainMenuRepository.findByOrderByCodeAscNameAsc();
//    }
//
//
//    public MainMenu getMainMenu(Long id) {
//        return mainMenuRepository.findOne(id);
//    }
//
//    public void createMainMenu(MainMenu mainMenu) {
//        mainMenuRepository.save(mainMenu);
//    }
//
//    public void deleteMainMenu(Long id) {
//        mainMenuRepository.delete(id);
//    }
//
//
//    public void updateMainMenu(MainMenu mainMenu) {
//        mainMenuRepository.save(mainMenu);
//    }
//
//    public void deleteMainMenu(MainMenu mainMenu) {
//        mainMenuRepository.delete(mainMenu);
//    }
//
//    public List<MenuItem> listMenuItemByMainMenuId(Long mainMenuId) {
//        return menuItemRepository.findBymainMenu_idOrderByCodeAsc(mainMenuId);
//    }
//
//    public MenuItem getMenuItem(Long id) {
//        return menuItemRepository.findOne(id);
//    }
//
//    public void createMenuItem(MenuItem menuItem) {
//        menuItemRepository.save(menuItem);
//    }
//
//    public void deleteMenuItem(Long id) {
//        menuItemRepository.delete(id);
//    }
//
//    public void deleteMenuItem(MenuItem menuItem) {
//        menuItemRepository.delete(menuItem);
//    }
//
//    public void updateMenuItem(MenuItem menuItem) {
//        menuItemRepository.save(menuItem);
//    }
//
//    public Resource getResource(Long id) {
//        return resourceRepository.findOne(id);
//    }
//
//    public void createResource(Resource resource) {
//        resourceRepository.save(resource);
//    }
//
//    public void deleteResource(Long id) {
//        resourceRepository.delete(id);
//    }
//
//    public void deleteResource(Resource resource) {
//        resourceRepository.delete(resource);
//    }
//
//    public void updateResource(Resource resource) {
//        resourceRepository.save(resource);
//    }
//
//    public List<Menu> initAdminMenu(Admin admin) {
//        List<Menu> result = new ArrayList<Menu>();
//        List<Role> roles = admin.getRoles();
//        List<MenuItem> menuItems = new ArrayList<MenuItem>();
//        for (Role role : roles) {
//            menuItems.addAll(role.getMenuItems());
//        }
//
//        for (MainMenu mainMenu : this.listAllMainMenu()) {
//            List<Menu> children = new ArrayList<Menu>();
//            for (int j = 0; j < mainMenu.getMenuItems().size(); j++) {
//                MenuItem menuItem = mainMenu.getMenuItems().get(j);
//                if (menuItems.contains(menuItem)) {
//                    children.add(new Menu(menuItem.getName(), menuItem.getLink()));
//                }
//            }
//            if (!children.isEmpty()) {
//                result.add(new Menu(mainMenu.getName(), "#", children));
//            }
//        }
//        return result;
//    }
//
//    @Override public List<MenuGroupVo> initAdminMenuGroup(Admin admin) {
//
//        Map<MenuGroup, List<Menu>> menuGroupNameToMenu = newHashMap();
//
//        Set<MenuGroupVo> result = newTreeSet();
//        List<Role> roles = admin.getRoles();
//        List<MenuItem> menuItems = newArrayList();
//        for (Role role : roles) {
//            menuItems.addAll(role.getMenuItems());
//        }
//
//        for (MainMenu mainMenu : this.listAllMainMenu()) {
//            List<Menu> children = newArrayList();
//            for (int j = 0; j < mainMenu.getMenuItems().size(); j++) {
//                MenuItem menuItem = mainMenu.getMenuItems().get(j);
//                if (menuItems.contains(menuItem)) {
//                    children.add(new Menu(menuItem.getName(), menuItem.getLink()));
//                }
//            }
//            if(CollectionUtils.isNotEmpty(children)) {
//                Menu menu = new Menu(mainMenu.getName(), "#", children);
//                if (CollectionUtils.isNotEmpty(mainMenu.getMenuGroups())) {
//                    for (MenuGroup menuGroup : mainMenu.getMenuGroups()) {
//                        List<Menu> menus = menuGroupNameToMenu.get(menuGroup);
//                        if (menus == null) {
//                            menus = newArrayList();
//                            menuGroupNameToMenu.put(menuGroup, menus);
//                        }
//                        menus.add(menu);
//                    }
//                }
//            }
//        }
//        for (Map.Entry<MenuGroup, List<Menu>> menuGroupListEntry : menuGroupNameToMenu.entrySet()) {
//            result.add(new MenuGroupVo(menuGroupListEntry.getKey().getId(), menuGroupListEntry.getKey().getName(),
//                menuGroupListEntry.getKey().getIdx(), menuGroupListEntry.getValue()));
//        }
//
//        return newArrayList(result);
//    }
//
//    public UserDetails loadUserByUsername(String username)
//        throws UsernameNotFoundException, DataAccessException {
//        Admin admin = adminRepository.findOne(username);
//        if (admin != null) {
//            admin.setMenus(this.initAdminMenu(admin));
//            admin.setMenuGroups(this.initAdminMenuGroup(admin));
//            Hibernate.initialize(admin.getRoles());
//            Hibernate.initialize(admin.getCompanies());
//            log.info("loadUserByUsername {}", username);
//            LogPo logPo = new LogPo();
//            logPo.setUri("login");
//            logPo.setHttp_method("");
//            logPo.setUser(username);
//            logPo.setTimestamp(DateTool.nowTimestamp());
//            logPo.setRequestParams("");
//            logRepository.save(logPo);
//        } else {
//            throw new UsernameNotFoundException("username:" + username + " is not exists");
//        }
//        return admin;
//    }
//
//    //设置用户缓存功能。????
//    public void setUserCache(UserCache userCache) {
//        this.userCache = userCache;
//    }
//
//    public UserCache getUserCache() {
//        return this.userCache;
//    }
//
//    public void updateItemParentId(Long parentId, Long itemId) {
//
//        mainMenuRepository.updateItemParentId(parentId, itemId);
//
//    }
//}
