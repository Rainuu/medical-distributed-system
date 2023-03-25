package com.aaa.charge.dao;
import com.aaa.core.entity.OrderBackfee;
import com.aaa.core.entity.OrderBackfeeItem;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 退费主表(HisOrderBackfee)表数据库访问层
 * 
 * @author makejava
 * @since 2023-03-25 11:09:15
 */

public interface HisOrderBackfeeMapper extends BaseMapper<OrderBackfee> {


     List<OrderBackfeeItem>  selectAll(String backId);
}
