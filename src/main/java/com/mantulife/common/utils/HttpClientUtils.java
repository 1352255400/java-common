package com.mantulife.common.utils;


import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Author W_wang
 * Description HttpClientUtils 工具类
 * Date 2019-07-29 10:14
 **/
public class HttpClientUtils {

    private HttpClientUtils() {
    }

    /**
     *
     * Author W_wang
     * Description get请求
     * Date 2019-07-29 10:14
     * @param url URL
     * @return  return string
     */
    public static String doGet(String url) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取服务端返回的数据
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 相当于关闭浏览器
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     *
     * Author W_wang
     * Description get请求
     * Date 2019-07-29 10:14
     * @param url 地址
     * @param headerMap header头
     * @return  return string
     */
    public static String doGet(String url, Map<String, String> headerMap) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);

        /*
         * 设置头信息
         */
        if (headerMap != null) {
            Set<String> keySet = headerMap.keySet();
            for (String key : keySet) {
                httpGet.addHeader(key, headerMap.get(key));
            }
        }

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取服务端返回的数据
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 相当于关闭浏览器
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     *
     * Author W_wang
     * Description post 请求 json格式
     * Date 2019-07-29 10:14
     * @param url url
     * @param params params
     * @return  return params
     */
    public static String doPost(String url, Map<String, Object> params) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost method = new HttpPost(url);

        if (params != null) {
            StringEntity entity = new StringEntity(JSON.toJSONString(params), "utf-8");//解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            method.setEntity(entity);
        }

        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(method);
            // 请求结束，返回结果
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 相当于关闭浏览器
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     *
     * Author W_wang
     * Description post 请求 json格式
     * Date 2019-07-29 10:14
     * @param url url
     * @param json url
     * @return  return url
     */
    public static String doPost(String url, String json) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost method = new HttpPost(url);

        if (json != null) {
            //解决中文乱码问题
            StringEntity entity = new StringEntity(json, "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            method.setEntity(entity);
        }

        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(method);
            // 请求结束，返回结果
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 相当于关闭浏览器
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
