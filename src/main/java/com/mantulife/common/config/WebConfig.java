package com.mantulife.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.spring.web.SpringfoxWebMvcConfiguration;

/**
 * WebConfig 配置
 *
 * author W_wang
 * email 1352255400@qq.com
 * date 2021-04-15 09:19:33
 */
@Configuration
@ConditionalOnClass(SpringfoxWebMvcConfiguration.class)
public class WebConfig implements WebMvcConfigurer {

    /**
     * 防止@EnableMvc把默认的静态资源路径覆盖
     *
     * @param registry registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决swagger无法访问
        registry.addResourceHandler("doc.html", "favicon.ico", "/favicon").addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/favicon").setViewName("favicon");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/websocket.html").setViewName("websocket");
        registry.addViewController("/error.html").setViewName("error");
        registry.addViewController("/file.html").setViewName("file");
        registry.addViewController("/favicon.ico").setViewName("favicon");
    }

}
