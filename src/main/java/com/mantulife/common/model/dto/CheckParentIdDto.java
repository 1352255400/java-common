package com.mantulife.common.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CheckParentIdDto {


    private Long id;

    private Long parentId;

    private Integer level;

    private List<CheckParentIdDto> children;

}
