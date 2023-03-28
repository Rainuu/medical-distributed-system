package com.aaa.stock.service;

import com.aaa.core.entity.DictData;
import com.aaa.core.entity.Medicines;
import com.aaa.core.entity.Producer;
import com.aaa.core.vo.Result;
import com.aaa.stock.vo.MedicinalVo;
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
    Result<IPage<Medicines>> getMedById(Integer current, Integer size, MedicinalVo medicinalVo);

    //模糊生产厂家数据
    Result<String> getProducerName();

    boolean delById(Long medicinesId);


}
