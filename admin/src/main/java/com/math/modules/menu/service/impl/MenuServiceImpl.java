package com.math.modules.menu.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.math.modules.menu.entity.Menu;
import com.math.modules.menu.entity.MenuTree;
import com.math.modules.menu.mapper.MenuMapper;
import com.math.modules.menu.service.IMenuService;
import com.yph.util.P;
import com.yph.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.together.modules.admin.feginService.OrderServiceClient;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2019-05-23
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    MenuMapper menuMapper;

//    @Autowired
//    OrderServiceClient orderServiceClient;

    @Override
    public R getMenu (P p) throws Exception {
        return R.success("success", getRootMenu(p));
    }

    @Override
    public R getOrder(Map<String, Object> map) {
        return null;
    }

//    @Override
//    public R getOrder(Map<String,Object> map) {
//        return orderServiceClient.getOrder(map.get("page"),map.get("limit"));
//    }

    public List<MenuTree> getRootMenu(P p) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("pid", "0");
        List<Menu> menus = menuMapper.selectByMap(map);
        List<MenuTree> list = new ArrayList<>();
        for (Menu menu : menus) {
            MenuTree menuTree1 = new MenuTree();
            menuTree1.setId(menu.getId() + "");
            menuTree1.setHref(menu.getUrl());
            menuTree1.setName(menu.getName());
            list.add(menuTree1);
            getChildMenu(menuTree1, p);
        }
        return list;
    }


    public void getChildMenu(MenuTree menuTree, P p) throws Exception{
        String id = menuTree.getId();
        Map<String, Object> map = new HashMap<>();
        map.put("pid", id);
        List<Menu> menus = menuMapper.selectByMap(map);
        for (Menu menu : menus) {
            menuTree.initChild();
            MenuTree menuTree1 = new MenuTree();
            menuTree1.setId(menu.getId() + "");
            menuTree1.setHref(menu.getUrl());
            menuTree1.setName(menu.getName());
            if (p.getUserDo().getUsername().equals("采购")){
                if (!menuTree1.getName().equals("采购列表")) {
                    continue;
                }
            }
            menuTree.getChildren().add(menuTree1);
            getChildMenu(menuTree1, p);
        }
    }

}
