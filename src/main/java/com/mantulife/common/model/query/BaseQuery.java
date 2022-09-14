package com.mantulife.common.model.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * author W_wang
 * WorkOrder 工单
 * email 1352255400@qq.com
 * date 2021-04-13 09:27:01
 */
@Data
@ToString
public class BaseQuery implements Serializable {

    @ApiModelProperty(value = "id集合")
    private List<Long> ids;

    @ApiModelProperty(value = "当前页:1、2、3")
    private Long pageNumber;

    @ApiModelProperty(value = "每页显示条数:10,20,30,50")
    private Long pageSize;

    @ApiModelProperty(value = "排序字段：name、userName")
    private String orderField;

    @ApiModelProperty(value = "排序方式:升序：asc，降序：desc")
    private String orderType;

    public Long getPageNumber() {
        return pageNumber != null && pageNumber > 0 ? pageNumber : 1;
    }

    public Long getPageSize() {
        return pageSize != null && pageSize > 0 ? pageSize : 10;
    }
}
