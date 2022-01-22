package com.math.modules.menu.service;




import com.baomidou.mybatisplus.extension.service.IService;
import com.math.modules.menu.entity.Menu;
import com.yph.util.P;
import com.yph.util.R;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2019-05-23
 */
public interface IMenuService extends IService<Menu> {

    public R getMenu (P p) throws Exception;

    public R getOrder(Map<String,Object> map) throws Exception;

}
