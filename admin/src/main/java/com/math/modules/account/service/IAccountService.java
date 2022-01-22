package com.math.modules.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.math.modules.account.entity.AccountEntity;
import com.yph.util.R;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2021-12-27
 */
public interface IAccountService extends IService<AccountEntity> {


    R getAllName();
}
