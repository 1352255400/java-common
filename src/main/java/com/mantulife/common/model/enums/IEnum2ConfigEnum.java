package com.mantulife.common.model.enums;

/**
 * author W_wang
 * IEnum2ConfigEnum 枚举转map
 * email 1352255400@qq.com
 * date 2021-04-15 09:19:33
 */
public interface IEnum2ConfigEnum<T1 extends Integer, T2 extends String> {

    /**
     * code
     *
     * @return  return return
     */
    T1 getCode();

    /**
     * msg
     *
     * @return  return  return
     */
    T2 getMsg();

}
