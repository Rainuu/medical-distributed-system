package com.aaa.stock.service.impl;

import com.aaa.core.entity.DictData;
import com.aaa.core.entity.Producer;
import com.aaa.core.vo.Result;
import com.aaa.stock.dao.ProducterDao;
import com.aaa.stock.feign.Feign;
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
import java.util.List;
import java.util.Objects;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/24 10:55
 */
@Service
public class ProducterServiceImpl extends ServiceImpl<ProducterDao,Producer> implements ProducterService {

    @Autowired
    private ProducterDao producterDao;

    //注入feign
    @Autowired
    private Feign feign;

    // 根据查询厂家信息
    @Override
    public Result<IPage<Producer>> getAll(Integer current, Integer size, ProducterVo producterVo) {
        IPage<Producer> page = new Page(current,size);  //分页
        //模糊查询，调用数据库的字段
        QueryWrapper<Producer> wrapper = new QueryWrapper();
        //模糊查询————厂家名称
        if (StringUtils.hasText(producterVo.getProducerName())){
            wrapper.like("producer_name",producterVo.getProducerName());
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
        if (Objects.nonNull(producterVo.getDateRange())&&producterVo.getDateRange().length==2){
            wrapper.between("create_time",producterVo.getDateRange()[0],producterVo.getDateRange()[1]);
        }
        IPage<Producer> page1 = producterDao.selectPage(page, wrapper);
//        System.out.println(page1.getTotal());  //测试有无拿到页码
        return new Result(200, "查询查询厂家信息", page1);
    }

    // 删除
    @Override
    public boolean delById(Long id) {
        // 如果执行方法返回true
        int i = producterDao.deleteById(id);
        if (i > 0){
            return true;
        }else {
            return false;
        }
    }

    //修改/插入
    @Override
    public boolean saveAndUpdate(Producer producer) {
        int i=-1;
        if (producer.getCreateTime()==null){
            producer.setCreateTime(new Date());
            producer.setUpdateTime(new Date());
        }else {
            producer.setUpdateTime(new Date());
        }

        if (producer.getProducerId()==null){
            i = producterDao.insert(producer);
            System.out.println("==================="+producer.getStatus());
        }else {
            i = producterDao.updById(producer);
            System.out.println("==================="+producer.getStatus());
        }

        if (i>0){
            return true;
        }
        return false;
    }

}
