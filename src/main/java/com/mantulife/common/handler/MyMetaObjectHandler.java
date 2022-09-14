package com.mantulife.common.handler;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.mantulife.common.model.vo.UserVo;
import com.mantulife.common.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author W_wang
 * version V1.0
 * Package
 * remark mybatisplus 入库拦截
 * email 1352255400@qq.com
 * date 2020/8/13 16:00
 * Copyright www.dx.com
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Resource
    private UserUtil userUtil;

    @Override
    public void insertFill(MetaObject metaObject) {
        //生成日期格式
        Long time = System.currentTimeMillis();  //获取当前时间戳
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date(Long.parseLong(String.valueOf(time))));      // 时间戳转换成时间

        this.setFieldValByName("createTime", date, metaObject);
        this.setFieldValByName("updateTime", date, metaObject);

        // 设置创建、编辑人信息
        UserVo cacheUser = userUtil.getCacheUser();
        if (ObjectUtil.isNotEmpty(cacheUser)) {
            this.setFieldValByName("companyId", cacheUser.getCompanyId(), metaObject);
            Object userId = this.getFieldValByName("userId", metaObject);
            if (ObjectUtil.isEmpty(userId) || userId.equals(0L)) {
                this.setFieldValByName("userId", cacheUser.getId(), metaObject);
            }
            this.setFieldValByName("createrId", cacheUser.getId(), metaObject);
            this.setFieldValByName("createrName", cacheUser.getName(), metaObject);
            this.setFieldValByName("updaterId", cacheUser.getId(), metaObject);
            this.setFieldValByName("updaterName", cacheUser.getName(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //生成日期格式
        Long time = System.currentTimeMillis();  //获取当前时间戳
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date(Long.parseLong(String.valueOf(time))));      // 时间戳转换成时间

        this.setFieldValByName("updateTime", date, metaObject);

        // 设置编辑人信息
        UserVo cacheUser = userUtil.getCacheUser();
        if (ObjectUtil.isNotEmpty(cacheUser)) {
            this.setFieldValByName("updaterId", cacheUser.getId(), metaObject);
            this.setFieldValByName("updaterName", cacheUser.getName(), metaObject);
        }
    }

}
