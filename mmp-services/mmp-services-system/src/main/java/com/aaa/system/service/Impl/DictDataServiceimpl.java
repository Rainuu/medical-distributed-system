package com.aaa.system.service.Impl;

import com.aaa.core.entity.DictData;
import com.aaa.core.entity.DictType;
import com.aaa.core.vo.Result;
import com.aaa.system.dao.DictDataDao;
import com.aaa.system.dao.DictTypeDao;
import com.aaa.system.service.DictDataService;
import com.aaa.system.vo.DictDataVo;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DictDataServiceimpl implements DictDataService {
    @Autowired
    private DictDataDao dictDataDao;
    @Autowired
    private DictTypeDao dictTypeDao;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<DictData> findByType(String type) {
        QueryWrapper<DictData> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_type", type);
        List<DictData> list = dictDataDao.selectList(wrapper);
        return list;
    }

    @Override
    public List<DictData> getall() {
        String dictListJson = redisTemplate.opsForValue().get("dictList");
        List<DictData> parse = (List<DictData>) JSON.parse(dictListJson);
        if (parse == null) {
            huancun();
            return dictDataDao.selectList(null);
        }
        return parse;
    }

    @Override
    public Result getInfoById(DictDataVo dictDataVo, Page page, Long id) {
        DictType dictType = dictTypeDao.selectById(id);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (id != null) {
            queryWrapper.eq("dict_type", dictType.getDictType());
        }
        if (dictDataVo.getDictType() != null) {
            queryWrapper.like("dict_type", dictDataVo.getDictType());
        }
        if (dictDataVo.getDictLabel() != null) {
            queryWrapper.like("dict_label", dictDataVo.getDictLabel());
        }
        if (dictDataVo.getStatus() != null) {
            queryWrapper.eq("status", dictDataVo.getStatus());
        }


        return new Result(200, null, dictDataDao.selectPage(page, queryWrapper));
    }

    @Override
    public Result saveAndUp(DictData dictData) {

        if (dictData.getDictCode() == null) {
            dictData.setCreateTime(new Date());
            dictData.setUpdateTime(new Date());
            int insert = dictDataDao.insert(dictData);
            if (insert > 0) {
                return new Result(200, null, true);
            }
            return new Result(200, null, false);
        } else {
            dictData.setUpdateTime(new Date());
            int i = dictDataDao.updateById(dictData);
            if (i > 0) {
                return new Result(200, null, true);
            }
            return new Result(200, null, false);
        }
    }

    @Override
    public Result delInfo(Long id) {
        int i = dictDataDao.deleteById(id);
        if (i > 0) {
            return new Result(200, null, true);
        }
        return new Result(200, null, false);
    }

    @Override
    public Result selectById(Long id) {
        return new Result(200, null, dictTypeDao.selectById(id));
    }

    @Override
    public Result huancun() {
        List<DictData> dictData = dictDataDao.selectList(null);
        String s = JSON.toJSONString(dictData);
        redisTemplate.opsForValue().set("dictList", s);
        return new Result(200, "同步成功", true);
    }
}
