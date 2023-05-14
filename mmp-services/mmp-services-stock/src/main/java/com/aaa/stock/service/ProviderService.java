package com.aaa.stock.service;

import com.aaa.core.entity.Provider;
import com.aaa.core.vo.Result;
import com.aaa.stock.vo.ProviderVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/26 15:46
 */
public interface ProviderService extends IService<Provider> {
    //查询页面所有数据，并实现分页
    Result<IPage<Provider>> getAll(Integer current, Integer size, ProviderVo providerVo);

    boolean delById(Long id);

    boolean saveAndUpdate(Provider provider);

}
