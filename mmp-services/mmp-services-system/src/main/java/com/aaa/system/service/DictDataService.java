package com.aaa.system.service;

import com.aaa.core.entity.DictData;
import com.aaa.core.entity.DictType;
import com.aaa.core.vo.Result;
import com.aaa.system.vo.DictDataVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface DictDataService {
    List<DictData> findByType(String type);

    List<DictData> getall();

    Result getInfoById(DictDataVo dictDataVo,Page page, Long id);

    Result saveAndUp(DictData dictData);

    Result delInfo(Long id);

    Result selectById(Long id);

    Result huancun();
}
