package com.mantulife.common.utils;

import cn.hutool.core.util.ObjectUtil;
import com.mantulife.common.model.dto.ApiDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.util.ObjectUtil.isNotEmpty;

/**
 * author W_wang
 * version V1.0
 * remark 公共函数
 * email 1352255400@qq.com
 * date 2020/8/4 17:22
 * Copyright www.dx.com
 */
@Slf4j
@Component
public class CommonUtil {


    // 环境配置
    @Value("${spring.application.name:company}")
    private String appName;


    //把构造方法私有
    private CommonUtil() {
    }

    /**
     * author W_wang
     * version V1.0
     * remark 获取域名
     * email 1352255400@qq.com
     * date 2020/8/4 17:22
     * @return return
     */
    public static String getDomain() {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjectUtil.isNull(requestAttributes) || requestAttributes == null) {
            return "";
        }
        HttpServletRequest request = requestAttributes.getRequest();
        // x-forwarded-proto:http  x-forwarded-host:dev.ifunsms.com  x-forwarded-port:80
        String http = request.getHeader("x-forwarded-proto");
        String host = request.getHeader("x-forwarded-host");
        String port = request.getHeader("x-forwarded-port");
        String domain = http + "://" + host;
        StringBuilder strBui = new StringBuilder();
        strBui.append(domain);
        if (!port.equals("80")) {
            strBui.append(":" + port);
        }
        return strBui.toString();
    }


    /**
     * author W_wang
     * version V1.0
     * remark 获取域名
     * email 1352255400@qq.com
     * date 2020/8/4 17:22
     * @return return
     */
    public List<ApiDto> getApiList() {
        RequestMappingHandlerMapping mapping = (RequestMappingHandlerMapping) SpringContextUtil.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        List<ApiDto> list = new ArrayList<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();

            // 初始化实体
            ApiDto apiDto = new ApiDto();
            apiDto.setAppName(appName);

            //获取当前方法所在类名
            Class<?> bean = method.getBeanType();
            //使用反射获取当前类注解内容
            Api api = bean.getAnnotation(Api.class);
            String controllerName = "";
            if (isNotEmpty(api)) {
                String[] tags = api.tags();
                controllerName = tags[0];
            }
            apiDto.setControllerName(controllerName);

            // 获取控制器前缀
            RequestMapping requestMapping = bean.getAnnotation(RequestMapping.class);
            String controllerPath = "";
            if (isNotEmpty(requestMapping)) {
                String[] value = requestMapping.value();
                controllerPath = value[0].replace("/", "");
            }
            apiDto.setControllerPath(appName + "-" + controllerPath);


            //获取方法上注解以及注解值
            ApiOperation methodAnnotation = method.getMethodAnnotation(ApiOperation.class);
            String methodName = methodAnnotation != null ? methodAnnotation.value() : "";
            apiDto.setMethodName(methodName);


            PatternsRequestCondition p = info.getPatternsCondition();
            for (String url : p.getPatterns()) {
                apiDto.setApi(url);
            }
            // map1.put("className", method.getMethod().getDeclaringClass().getName()); // 类名
            // map1.put("method", method.getMethod().getName()); // 方法名

            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
            for (RequestMethod requestMethod : methodsCondition.getMethods()) {
                apiDto.setType(requestMethod.toString());
            }
            if (!filderApi(apiDto.getApi())) {
                apiDto.setApi(appName + apiDto.getApi());
                list.add(apiDto);
            }
        }
        return list;
    }

    /**
     * author W_wang
     * version V1.0
     * remark 过滤其他不用接口
     * email 1352255400@qq.com
     * date 2020/8/4 17:22
     * @param api api
     * @return return
     */
    private boolean filderApi(String api) {
        return (api.startsWith("/swagger-resources")
                || api.startsWith("/v2/api-docs")
                || api.startsWith("/v3/api-docs")
                || api.startsWith("/error")
                || api.endsWith("/getApiList")
        );
    }


}
