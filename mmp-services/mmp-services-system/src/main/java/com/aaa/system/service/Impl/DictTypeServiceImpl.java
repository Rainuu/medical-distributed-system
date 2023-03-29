package com.aaa.system.service.Impl;

import com.aaa.core.entity.DictData;
import com.aaa.core.entity.DictType;
import com.aaa.core.util.JwtUtil;
import com.aaa.core.util.WebUtil;
import com.aaa.core.vo.Result;
import com.aaa.system.dao.DictDataDao;
import com.aaa.system.dao.DictTypeDao;
import com.aaa.system.service.DictTypeService;
import com.aaa.system.vo.DictTypeVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Service
public class DictTypeServiceImpl implements DictTypeService {
    @Autowired
    private DictTypeDao dao;
    @Autowired
    private DictDataDao dataDao;
    @Override
    public Result getAll(Page page, DictTypeVo dictTypeVo) {
        QueryWrapper queryWrapper =new QueryWrapper<>();
        if (dictTypeVo.getDictName()!=null){
            queryWrapper.like("dict_name",dictTypeVo.getDictName());
        }
        if (dictTypeVo.getDictType()!=null){
            queryWrapper.like("dict_type",dictTypeVo.getDictType());
        }
        if (dictTypeVo.getStatus()!=null){
            queryWrapper.eq("status",dictTypeVo.getStatus());
        }
        if (dictTypeVo.getCreateTime() != null){
            queryWrapper.between("create_time",dictTypeVo.getCreateTime()[0],dictTypeVo.getCreateTime()[1]);
        }
        Page page1 = dao.selectPage(page, queryWrapper);
        return new Result(200,"获取成功",page1);
    }

    @Override
    public Result saveAndUp(DictType dictType) {
        String token = WebUtil.getRequest().getHeader("token");
        Map<String, Object> tokenChaim = JwtUtil.getTokenChaim(token);
        if (dictType.getDictId()==null){
            dictType.setCreateBy((String) tokenChaim.get("username"));
            dictType.setCreateTime(new Date());
            dictType.setUpdateBy((String) tokenChaim.get("username"));
            dictType.setUpdateTime(new Date());
            int insert = dao.insert(dictType);
            if (insert>0){
                return new Result(200,null,true);
            }
            return new Result(200,null,false);
        }else {
            dictType.setUpdateBy((String) tokenChaim.get("username"));
            dictType.setUpdateTime(new Date());
            DictType dictType1 = dao.selectById(dictType.getDictId());
            if (!dictType1.getDictType().equals(dictType.getDictType())){
                QueryWrapper queryWrapper=new QueryWrapper();
                queryWrapper.eq("dict_type",dictType1.getDictType());
                DictData dictData = new DictData();
                dictData.setDictType(dictType.getDictType());
                dataDao.update(dictData,queryWrapper);
            }

            int insert = dao.updateById(dictType);
            if (insert>0){
                return new Result(200,null,true);
            }
            return new Result(200,null,false);
        }

    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Result delDictType(Long id) {
        DictType dictType = dao.selectById(id);
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("dict_type",dictType.getDictType());
        dataDao.delete(queryWrapper);
        int i = dao.deleteById(id);
        if (i>0){
            return new Result(200,null,true);
        }
        return new Result(200,null,false);
    }

    @Override
    public Result getInfo() {
        return new Result(200,null,dao.selectList(null));
    }
}
