package com.aaa.system.service.Impl;

import com.aaa.core.entity.DictData;
import com.aaa.system.dao.DictDataDao;
import com.aaa.system.service.DictDataService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DictDataServiceimpl implements DictDataService {
    @Autowired
    private DictDataDao dictDataDao;
    @Override
    public List<DictData> findByType(String type) {
        QueryWrapper<DictData> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_type",type);
        List<DictData> list =dictDataDao.selectList(wrapper);
        return list;
    }

    @Override
    public List<DictData> getall() {

        return dictDataDao.selectList(null);
    }
}
