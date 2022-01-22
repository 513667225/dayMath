package com.math.modules.account.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.math.modules.account.entity.AccountEntity;
import com.math.modules.account.mapper.AccountMapper;
import com.math.modules.account.service.IAccountService;
import com.yph.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2021-12-27
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountEntity> implements IAccountService {


    @Autowired
    AccountMapper mapper;

    @Override
    public R getAllName() {
        return R.success().data(mapper.getAllName());
    }
}
