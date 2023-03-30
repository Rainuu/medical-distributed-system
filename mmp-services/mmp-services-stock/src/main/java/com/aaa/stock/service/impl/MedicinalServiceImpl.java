package com.aaa.stock.service.impl;

import com.aaa.core.entity.DictData;
import com.aaa.core.entity.Medicines;
import com.aaa.core.entity.Producer;
import com.aaa.core.vo.Result;
import com.aaa.stock.dao.MedicinalDao;
import com.aaa.stock.dao.ProducterDao;
import com.aaa.stock.feign.Feign;
import com.aaa.stock.service.MedicinalService;
import com.aaa.stock.vo.MedicinalVo;
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
 * @DATE: 2023/3/27 16:27
 */
@Service
public class MedicinalServiceImpl extends ServiceImpl<MedicinalDao, Medicines> implements MedicinalService {
    @Autowired
    private MedicinalDao medicinalDao;

    // 查询 & 分页 & 模糊查询
    @Override
    public Result<IPage<Medicines>> getAll(Integer current, Integer size, MedicinalVo medicinalVo) {
        // IPage<Medicines> medById = medicinalDao.getMedById(medicinalVo, new Page(current, size));
        IPage<Medicines> page = new Page(current, size);
        QueryWrapper<Medicines> wrapper = new QueryWrapper();
        if (StringUtils.hasText(medicinalVo.getMedicinesName())){
            wrapper.like("medicines_name",medicinalVo.getMedicinesName());
        }
        if (StringUtils.hasText(medicinalVo.getKeywords())){
            wrapper.like("keywords",medicinalVo.getKeywords());
        }
        if (Objects.nonNull(medicinalVo.getMedicinesType())){
            wrapper.like("medicines_type",medicinalVo.getMedicinesType());
        }
        if (Objects.nonNull(medicinalVo.getProducerId())){
            wrapper.like("producter_id",medicinalVo.getProducterId());
        }
        if (Objects.nonNull(medicinalVo.getPrescriptionType())){
            wrapper.like("prescription_type",medicinalVo.getPrescriptionType());
        }
        if (Objects.nonNull(medicinalVo.getStatus())){
            wrapper.like("status",medicinalVo.getStatus());
        }
        IPage<Medicines> page1 = medicinalDao.selectPage(page, wrapper);
        return new Result(200, "查询药品信息信息", page1);
    }

    @Override
    public boolean saveAndUpdate(Medicines medicines) {
        int i = -1;
        if (medicines.getCreateTime()==null){
            medicines.setCreateTime(new Date());
            medicines.setUpdateTime(new Date());
        }else {
            medicines.setUpdateTime(new Date());
        }

        if (medicines.getMedicinesId()==null){
            i = medicinalDao.insert(medicines);
        }else {
            i = medicinalDao.updateById(medicines);
        }

        if (i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delById(Long medicinesId) {
        // 如果执行方法返回true
        int i = medicinalDao.deleteById(medicinesId);
        if (i > 0){
            return true;
        }else {
            return false;
        }
    }

}
