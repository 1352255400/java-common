package com.mantulife.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author W_wang
 * @version V1.0
 * @Package com.xinchao.ims.rbac.handler
 * @remark
 * @email 1352255400@qq.com
 * @date 2020/8/13 16:00
 * @Copyright www.mantulife.com
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //生成日期格式
        Long time = System.currentTimeMillis();  //获取当前时间戳
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date(Long.parseLong(String.valueOf(time))));      // 时间戳转换成时间

        this.setFieldValByName("createTime", date, metaObject);
        this.setFieldValByName("updateTime", date, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //生成日期格式
        Long time = System.currentTimeMillis();  //获取当前时间戳
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date(Long.parseLong(String.valueOf(time))));      // 时间戳转换成时间

        this.setFieldValByName("updateTime", date, metaObject);
    }
}
