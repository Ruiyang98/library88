package com.example.wj.service;

import com.example.wj.dao.AdminMenuDAO;
import com.example.wj.pojo.AdminMenu;
import com.example.wj.pojo.AdminRoleMenu;
import com.example.wj.pojo.AdminUserRole;
import com.example.wj.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminMenuService {
    @Autowired
    AdminMenuDAO adminMenuDAO;
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminRoleMenuService adminRoleMenuService;

    public List<AdminMenu> getAllByParentId(int parentId) {
        return adminMenuDAO.findAllByParentId(parentId);
    }

    public List<AdminMenu> getMenusByCurrentUser() {
        // 测试，发现不是没有获取到用户。
        //String username = "admin";
        // 从数据库中获取当前用户
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(username);

        // 获得当前用户对应的所有角色的 id 列表
        List<Integer> rids = adminUserRoleService.listAllByUid(user.getId())
                .stream().map(AdminUserRole::getRid).collect(Collectors.toList());

        // 查询出这些角色对应的所有菜单项
        List<Integer> menuIds = adminRoleMenuService.findAllByRidIn(rids)
                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        List<AdminMenu> menus = adminMenuDAO.findAllById(menuIds).stream().distinct().collect(Collectors.toList());

        // 处理菜单项的结构
        handleMenus(menus);
        return menus;
    }

    // 实现一个根据当前用户查询出所有菜单项的方法
    public List<AdminMenu> getMenusByRoleId(int rid) {
        List<Integer> menuIds = adminRoleMenuService.findAllByRid(rid)
                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        List<AdminMenu> menus = adminMenuDAO.findAllById(menuIds);

        handleMenus(menus);
        return menus;
    }

    // 整合
    // lambda表达式 简化版
    public void handleMenus(List<AdminMenu> menus) {
        menus.forEach(m -> {
            List<AdminMenu> children = getAllByParentId(m.getId());
            m.setChildren(children);
        });

        menus.removeIf(m -> m.getParentId() != 0);
    }
}

    //未简化版
    /*
    public void handleMenus(List<AdminMenu> menus) {
	for (AdminMenu menu : menus) {
	    List<AdminMenu> children = getAllByParentId(menu.getId());
	    menu.setChildren(children);
	}

	Iterator<AdminMenu> iterator = menus.iterator();
	while (iterator.hasNext()) {
	    AdminMenu menu = iterator.next();
	    if (menu.getParentId() != 0) {
	        iterator.remove();
	        }
	    }
    }
     */
