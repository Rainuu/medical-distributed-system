package com.aaa.stock.service.impl;

import com.aaa.core.entity.Producer;
import com.aaa.core.vo.Result;
import com.aaa.stock.dao.ProducterDao;
import com.aaa.stock.service.ProducterService;
import com.aaa.stock.vo.ProducterVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/24 10:55
 */
@Service
public class ProducterServiceImpl implements ProducterService {

    @Autowired
    private ProducterDao producterDao;

    // 根据查询厂家信息
    @Override
    public Result<IPage<Producer>> getAll(Integer current, Integer size, ProducterVo producterVo) {
        //分页
        IPage<Producer> page = new Page(current,size);
        //模糊查询，调用数据库的字段
        QueryWrapper<Producer> wrapper = new QueryWrapper();
        //模糊查询————厂家名称
        if (StringUtils.hasText(producterVo.getProducerName())){
            wrapper.like("producer_id",producterVo.getProducerName());
        }
        //模糊查询————关键字
        if (StringUtils.hasText(producterVo.getKeywords())){
            wrapper.like("keywords",producterVo.getKeywords());
        }
        //模糊查询————厂家电话
        if (StringUtils.hasText(producterVo.getProducerTel())){
            wrapper.like("producer_tel",producterVo.getProducerTel());
        }
        //模糊查询————状态
        if (Objects.nonNull(producterVo.getStatus())){
            wrapper.like("status",producterVo.getStatus());
        }
        //模糊查询————创建时间
        if (Objects.nonNull(producterVo.getCreateTime())&&Objects.nonNull(producterVo.getCreateTime().length==2)){
            wrapper.like("create_time",producterVo.getCreateTime());
        }
        IPage<Producer> page1 = producterDao.selectPage(page, wrapper);
//        System.out.println(page1.getTotal());  //测试有无拿到页码
        return new Result(200, "查询查询厂家信息", page1);
    }
}
