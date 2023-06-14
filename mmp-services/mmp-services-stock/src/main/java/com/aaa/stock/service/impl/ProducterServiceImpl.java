package com.aaa.stock.service.impl;

import com.aaa.core.entity.Producer;
import com.aaa.core.vo.Result;
import com.aaa.stock.dao.ProducterDao;
import com.aaa.stock.service.ProducterService;
import com.aaa.stock.vo.ProducterVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
@Service
public class ProducterServiceImpl extends ServiceImpl<ProducterDao, Producer> implements ProducterService {
    // 注入dao层方法
    @Autowired
    private ProducterDao producterDao;

    // 查询 & 分页 & 模糊查询（厂家名称、关键字、厂家电话、状态、创建时间）
    @Override
    public Result<IPage<Producer>> getAll(Integer current, Integer size, ProducterVo producterVo) {
        IPage<Producer> page = new Page(current, size);
        QueryWrapper<Producer> wrapper = new QueryWrapper();
        if (StringUtils.hasText(producterVo.getProducerName())) {
            wrapper.like("producer_name", producterVo.getProducerName());
        }
        if (StringUtils.hasText(producterVo.getKeywords())) {
            wrapper.like("keywords", producterVo.getKeywords());
        }
        if (StringUtils.hasText(producterVo.getProducerTel())) {
            wrapper.like("producer_tel", producterVo.getProducerTel());
        }
        if (Objects.nonNull(producterVo.getStatus())) {
            wrapper.like("status", producterVo.getStatus());
        }
        if (Objects.nonNull(producterVo.getDateRange()) && producterVo.getDateRange().length == 2) {
            wrapper.between("create_time", producterVo.getDateRange()[0], producterVo.getDateRange()[1]);
        }
        IPage<Producer> page1 = producterDao.selectPage(page, wrapper);
        return new Result(200, "查询查询厂家信息", page1);
    }

    // 删除————根据id查询
    @Override
    public boolean delById(Long id) {
        int i = producterDao.deleteById(id);
        return i > 0;
    }

    //修改/插入
    @Override
    public boolean saveAndUpdate(Producer producer) {
        // 初始化id
        int i = -1;
        // 获取更新和创建时间
        if (producer.getCreateTime() == null) {
            producer.setCreateTime(new Date());
            producer.setUpdateTime(new Date());
        } else {
            producer.setUpdateTime(new Date());
        }
        // 根据有无id分别执行新增\修改
        if (producer.getProducerId() == null) {
            i = producterDao.insert(producer);
        } else {
            i = producterDao.updById(producer);
        }

        return i > 0;
    }

}
