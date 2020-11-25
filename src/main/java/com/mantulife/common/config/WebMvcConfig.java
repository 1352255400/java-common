package com.mantulife.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author W_wang
 * @version V1.0
 * @Package com.xinchao.ims.rbac.config
 * @remark
 * @email 1352255400@qq.com
 * @date 2020/8/13 13:45
 * @Copyright www.mantulife.com
 */
@Configuration
public class WebMvcConfig implements  WebMvcConfigurer{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/websocket.html").setViewName("websocket");
        registry.addViewController("/error.html").setViewName("error");
        registry.addViewController("/file.html").setViewName("file");
        registry.addViewController("/favicon.ico").setViewName("favicon");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //swagger 配置
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
