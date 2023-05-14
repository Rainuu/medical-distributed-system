package com.aaa.system.service;

import com.aaa.core.entity.DictType;
import com.aaa.core.vo.Result;
import com.aaa.system.vo.DictTypeVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface DictTypeService {
    /*获取全部信息*/
    Result<DictType> getAll(Page page, DictTypeVo dictTypeVo);

    Result saveAndUp(DictType dictType);

    Result delDictType(Long id);

    Result getInfo();

}
