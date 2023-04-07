package com.aaa.stock.service.impl;

import com.aaa.core.entity.Medicines;
import com.aaa.core.vo.Result;
import com.aaa.stock.dao.MedicinalDao;
import com.aaa.stock.service.MedicinalService;
import com.aaa.stock.vo.MedicinalVo;
import com.aaa.stock.vo.NewCareVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
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
    public Result<IPage<Medicines>> getAll(Integer current1, Integer size1, MedicinalVo medicinalVo) {
        // IPage<Medicines> medById = medicinalDao.getMedById(medicinalVo, new Page(current, size));
        IPage<Medicines> page = new Page(current1, size1);
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
            wrapper.eq("producter_id",medicinalVo.getProducerId());
        }
        if (Objects.nonNull(medicinalVo.getPrescriptionType())){
            wrapper.like("prescription_type",medicinalVo.getPrescriptionType());
        }
        if (Objects.nonNull(medicinalVo.getStatus())){
            wrapper.like("status",medicinalVo.getStatus());
        }
        if (Objects.nonNull(medicinalVo.getDateRange())&&medicinalVo.getDateRange().length==2){
            wrapper.between("create_time",medicinalVo.getDateRange()[0],medicinalVo.getDateRange()[1]);
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
            System.out.println(medicines.getProducterId());
            i = medicinalDao.updById(medicines);
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

    @Override
    public Result<Page<Medicines>> MeDicinesController(NewCareVo newCareVo) {
        Page<Medicines> medicinesPage = new Page<>(newCareVo.getPageNum(), newCareVo.getPageSize());

        QueryWrapper<Medicines> queryWrapper = new QueryWrapper<Medicines>();
        if (Objects.nonNull(newCareVo.getKeywords())){
            queryWrapper.like("keywords",newCareVo.getKeywords());
        }

        Page<Medicines> medicinesPage1 = medicinalDao.selectPage(medicinesPage, queryWrapper);
        return new Result<>(2000,"查询成功",medicinesPage1);
    }

    @Transactional
    @Override
    //收费减库存接口
    public Boolean num(String num, String itemName) {
        BigDecimal bigDecimal = new BigDecimal(num);
        QueryWrapper<Medicines> wrapper1=new QueryWrapper<>();
        wrapper1.eq("medicines_name",itemName);
        Medicines medicines = medicinalDao.selectOne(wrapper1);
        BigDecimal medicinesStockNum = new BigDecimal(medicines.getMedicinesStockNum());
        BigDecimal a=medicinesStockNum.subtract(bigDecimal);
        if(a.compareTo(BigDecimal.valueOf(0))> -1 ){
            BigDecimal i= medicinesStockNum.subtract(bigDecimal);
            Medicines medicines1=new Medicines();
            Integer z=i.intValue();
            medicines1.setMedicinesStockNum(z);
            QueryWrapper<Medicines> wrapper2=new QueryWrapper<>();
            wrapper2.eq("medicines_name",itemName);
            int update = medicinalDao.update(medicines1, wrapper2);
            return update>0?true:false;
        }
    return false;
    }

    @Transactional
    @Override
    public Boolean AddNum(String num, String itemName) {
        BigDecimal bigDecimal = new BigDecimal(num);
        QueryWrapper<Medicines> wrapper1=new QueryWrapper<>();
        wrapper1.eq("medicines_name",itemName);
        Medicines medicines = medicinalDao.selectOne(wrapper1);
        BigDecimal medicinesStockNum = new BigDecimal(medicines.getMedicinesStockNum());
        BigDecimal a=medicinesStockNum.add(bigDecimal);
        if(a.compareTo(BigDecimal.valueOf(0))> -1 ){
            BigDecimal i= medicinesStockNum.add(bigDecimal);
            Medicines medicines1=new Medicines();
            Integer z=i.intValue();
            medicines1.setMedicinesStockNum(z);
            QueryWrapper<Medicines> wrapper2=new QueryWrapper<>();
            wrapper2.eq("medicines_name",itemName);
            int update = medicinalDao.update(medicines1, wrapper2);
            return update>0?true:false;
        }
        return false;
    }


}
