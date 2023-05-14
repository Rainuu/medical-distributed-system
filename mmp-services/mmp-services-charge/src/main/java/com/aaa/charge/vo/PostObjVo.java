package com.aaa.charge.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostObjVo {

    private List<PostObjVo1> orderChargeItemDtoList;
    private PostObjVo2 orderChargeDto;
}
