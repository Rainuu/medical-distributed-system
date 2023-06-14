package com.aaa.stock.service;

import com.aaa.core.entity.Medicines;
import com.aaa.core.vo.Result;
import com.aaa.stock.vo.MedicinalVo;
import com.aaa.stock.vo.NewCareVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
public interface MedicinalService extends IService<Medicines> {

    //全查+分页+模糊
    Result<IPage<Medicines>> getAll(Integer current1, Integer size1, MedicinalVo medicinalVo);
    // Result<IPage<Medicines>> getMedById(Integer current, Integer size, MedicinalVo medicinalVo);

    // 修改 & 新增
    boolean saveAndUpdate(Medicines medicines);

    // 删除
    boolean delById(Long medicinesId);

    // 远程调用
    Result<Page<Medicines>> MeDicinesController(NewCareVo newCareVo);

    Boolean num(String num, String itemName);

    Boolean AddNum(String num, String itemName);
}
