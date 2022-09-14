package com.mantulife.common.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * author W_wang
 * DeleteDto 删除dto
 * email 1352255400@qq.com
 * date 2021-04-15 09:19:33
 */
@Data
public class DeleteDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id集合")
    private List<Long> ids;

    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;
}
