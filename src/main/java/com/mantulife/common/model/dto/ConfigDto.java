package com.mantulife.common.model.dto;

import com.mantulife.common.utils.Enum2Config;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * author W_wang
 * ConfigDto 配置文件dto
 * email 1352255400@qq.com
 * date 2021-04-13 09:27:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfigDto {


    /**
     * 名称
     */
    private String name;

    /**
     * 配置列表
     */
    private List<Enum2Config> list;
}
