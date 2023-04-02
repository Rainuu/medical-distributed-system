package com.aaa.stock.service;

import com.aaa.core.entity.DictData;
import com.aaa.core.entity.Medicines;
import com.aaa.core.entity.Producer;
import com.aaa.core.vo.Result;
import com.aaa.stock.vo.MedicinalVo;
import com.aaa.stock.vo.NewCareVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/27 15:51
 */
public interface MedicinalService extends IService<Medicines> {

    //全查+分页+模糊
    Result<IPage<Medicines>> getAll(Integer current, Integer size, MedicinalVo medicinalVo);
    // Result<IPage<Medicines>> getMedById(Integer current, Integer size, MedicinalVo medicinalVo);

    // 修改 & 新增
    boolean saveAndUpdate(Medicines medicines);

    // 删除
    boolean delById(Long medicinesId);

    // 远程调用
    Result<Page<Medicines>> MeDicinesController(NewCareVo newCareVo);

}
