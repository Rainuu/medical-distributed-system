package com.aaa.stock.service.impl;

import com.aaa.core.entity.DictData;
import com.aaa.core.entity.Provider;
import com.aaa.core.vo.Result;
import com.aaa.stock.dao.ProviderDao;
import com.aaa.stock.feign.Feign;
import com.aaa.stock.service.ProviderService;
import com.aaa.stock.vo.ProviderVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/26 15:48
 */
@Service
public class ProviderServiceImpl extends ServiceImpl<ProviderDao, Provider> implements ProviderService {
    //装配注入Dao层接口
    @Autowired
    private ProviderDao providerDao;

    //查询页面所有数据，并实现分页
    @Override
    public Result<IPage<Provider>> getAll(Integer current, Integer size, ProviderVo providerVo) {
        //分页对象
        IPage<Provider> page = new Page<>(current,size);
        //条件构造器
        QueryWrapper<Provider> wrapper = new QueryWrapper();
        if (StringUtils.hasText(providerVo.getProviderName())){
            wrapper.like("provider_name",providerVo.getProviderName());
        }
        if (StringUtils.hasText(providerVo.getContactName())){
            wrapper.like("contact_name",providerVo.getContactName());
        }
        if (StringUtils.hasText(providerVo.getContactTel())){
            wrapper.like("contact_tel",providerVo.getContactTel());
        }
        if (Objects.nonNull(providerVo.getStatus())){
            wrapper.eq("status",providerVo.getStatus());
        }
        IPage<Provider> page1 = providerDao.selectPage(page, wrapper);
        return new Result<IPage<Provider>>(200,"查询供应商成功",page1);
    }

    @Override
    public boolean delById(Long id) {
        // 如果执行方法返回true
        int i = providerDao.deleteById(id);
        if (i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean saveAndUpdate(Provider provider) {
        int i = -1;
        if (provider.getCreateTime()==null){
            provider.setCreateTime(new Date());
            provider.setUpdateTime(new Date());
        }else {
            provider.setUpdateTime(new Date());
        }

        if (provider.getProviderId()==null){
            i = providerDao.insert(provider);
        }else {
            i = providerDao.updById(provider);
        }

        if (i>0){
            return true;
        }
        return false;
    }

}
