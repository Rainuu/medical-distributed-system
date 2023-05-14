package com.aaa.charge.dao;

import com.aaa.core.entity.CareOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 药用处方表(HisCareOrder)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-27 15:41:16
 */
@Repository
public interface HisCareOrderMapper extends BaseMapper<CareOrder> {

}
