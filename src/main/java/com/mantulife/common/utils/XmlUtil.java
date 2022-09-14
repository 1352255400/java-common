package com.mantulife.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.*;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chengsheng on 2015/8/19.
 */
@Slf4j
public class XmlUtil {

    public static void main(String[] args) throws Exception {
        //1.创建Reader对象
        //SAXReader reader = new SAXReader();
        //2.加载xml
        //Document document = reader.read(new File("D:\\xml\\diagram.xml"));
        String xmlStr = readFile("D:\\xml\\diagram.xml");
        Document document = DocumentHelper.parseText(xmlStr);

        //3.获取根节点
        Element rootElement = document.getRootElement();
        Iterator iterator = rootElement.elementIterator();
        while (iterator.hasNext()) {
            Element stu = (Element) iterator.next();
            if ("process".equals(stu.getName())) {
                //System.out.println("节点名称" + stu.getName());
                // 节点属性
                List<Attribute> attributes = stu.attributes();
                for (Attribute attribute : attributes) {
                    //System.out.println("属性：" + attribute.getValue() + "===" + attribute.getName());
                }

                // 子节点
                Iterator iterator1 = stu.elementIterator();
                while (iterator1.hasNext()) {
                    Element stuChild = (Element) iterator1.next();

                    System.out.println("节点名：" + stuChild.getName());
                    // 当前节点属性
                    List<Attribute> stuChildattributes = stuChild.attributes();
                    for (Attribute attribute : stuChildattributes) {
                        System.out.println("属性" + attribute.getName() + attribute.getValue());
                    }

                    //getStringValue 获取当前节点的子孙节点中的所有文本内容连接成的字符串.
                    // 节点属性
                    Iterator iterator2 = stuChild.elementIterator();
                    while (iterator2.hasNext()) {
                        Element stuChild1 = (Element) iterator2.next();
                         System.out.println("节点名999："+stuChild1.getName()+"==="+stuChild1.getText());
                        // TODO 继续往下迭代
                    }
                }
            }
        }

//        String xmlStr = readFile("D:\\xml\\diagram.xml");
//        Document doc = DocumentHelper.parseText(xmlStr);
//
//        JSONObject json = new JSONObject();
//        Map<Object, Object> map = new LinkedHashMap<>();
//        Element rootElement = doc.getRootElement();
//        dom4j2Json(rootElement, json, map);
//        log.info(map.toString());
//        log.info("xml2Json:" + json.toJSONString());
    }

    public static String readFile(String path) {
        File file = new File(path);
        FileInputStream fis = null;
        try (FileInputStream fileInputStream = fis = new FileInputStream(file)) {
            FileChannel fc = fis.getChannel();
            int i = Integer.parseInt(String.valueOf(file.length()));
            ByteBuffer bb = ByteBuffer.allocate(i);
            //fc向buffer中读入数据
            fc.read(bb);
            bb.flip();
            String str = new String(bb.array(), StandardCharsets.UTF_8);
            fc.close();
            fis.close();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";

    }

    /**
     * xml转json
     *
     * @param xmlStr xmlStr
     * @return  return return
     * @throws DocumentException throws
     */
    public static JSONObject xml2Json(String xmlStr) throws DocumentException {
        Document doc = DocumentHelper.parseText(xmlStr);
        JSONObject json = new JSONObject();
        Map<Object, Object> map = new LinkedHashMap<>();
        dom4j2Json(doc.getRootElement(), json, map);
        return json;
    }

    /**
     * xml转json
     *
     * @param element element
     * @param json json
     * @param map map
     */
    public static void dom4j2Json(Element element, JSONObject json, Map<Object, Object> map) {
        //如果是属性
        for (Object o : element.attributes()) {
            Attribute attr = (Attribute) o;
            if (!isEmpty(attr.getValue())) {
                json.put(attr.getName(), attr.getValue());
                map.put("@" + attr.getName(), attr.getValue());
            }
        }


        List<Element> chdEl = element.elements();
        if (chdEl.isEmpty() && !isEmpty(element.getText())) {//如果没有子元素,只有一个值
            json.put(element.getName(), element.getText());
            map.put(element.getName(), element.getText());
        }

        for (Element e : chdEl) {//有子元素
            if (!e.elements().isEmpty()) {//子元素也有子元素
                JSONObject chdjson = new JSONObject();
                Map<Object, Object> chimap = new LinkedHashMap<>();
                dom4j2Json(e, chdjson, chimap);
                Object o = json.get(e.getName());
                if (o != null) {
                    JSONArray jsona = null;
                    if (o instanceof JSONObject) {//如果此元素已存在,则转为jsonArray
                        JSONObject jsono = (JSONObject) o;
                        json.remove(e.getName());
                        jsona = new JSONArray();
                        jsona.add(jsono);
                        jsona.add(chdjson);
                    }
                    if (o instanceof JSONArray) {
                        jsona = (JSONArray) o;
                        jsona.add(chdjson);
                    }
                    json.put(e.getName(), jsona);
                    map.put(e.getName(), jsona);
                } else {
                    if (!chdjson.isEmpty()) {
                        json.put(e.getName(), chdjson);
                        map.put(e.getName(), chdjson);
                    }
                }
            } else {//子元素没有子元素
                for (Object o : element.attributes()) {
                    Attribute attr = (Attribute) o;
                    if (!isEmpty(attr.getValue())) {
                        json.put(attr.getName(), attr.getValue());
                        map.put("@" + attr.getName(), attr.getValue());
                    }
                }
                if (!e.getText().isEmpty()) {
                    log.info(e.getName() + "==========" + e.getText());
                    json.put("@" + e.getName(), e.getText());
                    map.put(e.getName(), e.getText());
                }
            }
        }
    }

    public static boolean isEmpty(String str) {

        return (str == null || str.trim().isEmpty() || "null".equals(str));
    }
}

