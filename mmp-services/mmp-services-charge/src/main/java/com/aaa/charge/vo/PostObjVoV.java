package com.aaa.charge.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostObjVoV {

    private List<PostObjVo1> orderBackfeeItemDtoList;
    private PostObjVo3 orderBackfeeDto;
}
