package com.aaa.charge.dao;

import com.aaa.core.entity.CareOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 药用处方表(HisCareOrder)表数据库访问层
 */
@Repository
public interface HisCareOrderMapper extends BaseMapper<CareOrder> {

}
