package com.mantulife.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration//配置类
@EnableSwagger2 //swagger注解
public class SwaggerConfig {

    @Value("${swagger.show:false}")
    private boolean swaggerShow;

    @Bean
    public Docket webApiConfig() {

        //设置header头参数
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("authorization").description("user token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerShow) //自定义是否开启swagger
                .groupName("webApi")
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .apiInfo(webApiInfo());
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("信息化-DEMO-API文档")
                .description("本文档描述了课程中心微服务接口定义")
                .version("1.0")
                .contact(new Contact("java", "https://www.mantulife.com", "1352255400@qq.com"))
                .build();
    }
}
