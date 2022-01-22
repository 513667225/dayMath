package com.math.modules.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.math.modules.account.entity.AccountEntity;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2021-12-27
 */
public interface AccountMapper extends BaseMapper<AccountEntity> {


    List<String > getAllName();

}
