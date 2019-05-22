package com.lee.service.admin;


import com.lee.dao.mysql.admin.MenuGroup;
import com.lee.dao.mysql.vo.admin.MenuGroupReqVo;
import com.lee.dao.repository.admin.MainMenuRepository;
import com.lee.dao.repository.admin.MenuGroupRepository;
import org.codelogger.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by defei on 3/9/16.
 */
@Service
public class MenuGroupService {

    private static final Logger logger = LoggerFactory.getLogger(MenuGroupService.class);

    @Autowired private MainMenuRepository mainMenuRepository;

    @Autowired private MenuGroupRepository menuGroupRepository;


    public List<MenuGroup> listAllMenuGroups() {
        return menuGroupRepository.findByOrderByIdxAsc();
    }

    public MenuGroup getMenuGroup(Long id) {
        return id == null ? null : menuGroupRepository.findOne(id);
    }

    @Transactional
    public MenuGroup save(MenuGroupReqVo reqVo) {

        MenuGroup menuGroup = reqVo.getId() != null ? getMenuGroup(reqVo.getId()) : new MenuGroup();
        menuGroup.setName(reqVo.getName());
        menuGroup.setIdx(reqVo.getIdx());
        menuGroup.setDescription(reqVo.getDescription());
        if(CollectionUtils.isNotEmpty(reqVo.getMainMenuIds())) {
            menuGroup.setMenus(mainMenuRepository.findAll(reqVo.getMainMenuIds()));
        }
        return menuGroupRepository.save(menuGroup);
    }

    public void deleteMenuGroup(Long id) {
        menuGroupRepository.delete(id);
    }


}
