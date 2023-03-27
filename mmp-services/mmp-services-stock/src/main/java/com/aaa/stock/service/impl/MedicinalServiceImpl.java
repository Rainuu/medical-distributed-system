package com.aaa.stock.service.impl;

import com.aaa.core.entity.DictData;
import com.aaa.core.entity.Medicines;
import com.aaa.core.vo.Result;
import com.aaa.stock.dao.MedicinalDao;
import com.aaa.stock.feign.Feign;
import com.aaa.stock.service.MedicinalService;
import com.aaa.stock.vo.MedicinalVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //注入feign
    @Autowired
    private Feign feign;

    @Override
    public Result<IPage<Medicines>> getMedById(Integer current, Integer size, MedicinalVo medicinalVo) {
        IPage<Medicines> medById = medicinalDao.getMedById(medicinalVo, new Page(current, size));
        return new Result(200, "查询查询厂家信息", medById);
    }

    //处理字典 调用system注入的方法
    @Override
    public Result<List<DictData>> getOpenFeign() {
        List<DictData> all = feign.getAll();
        return new Result(200, "成功", all);
    }
}
