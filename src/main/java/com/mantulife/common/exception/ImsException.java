package com.mantulife.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author W_wang
 * @version V1.0
 * @Package com.xinchao.ims.common.exception
 * @remark 自定义异常
 * @email 1352255400@qq.com
 * @date 2020/8/4 17:22
 * @Copyright www.mantulife.com
 * @demo: throw  new ImsException(1000,"自定义异常");
 */
@Data
@AllArgsConstructor  //生成有参数构造方法
@NoArgsConstructor   //生成无参数构造
public class ImsException extends RuntimeException {
    private Integer code;//状态码
    private String msg;//异常信息
}
