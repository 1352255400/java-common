package com.mantulife.common.model.map;

import com.mantulife.common.model.enums.FileCateEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * author W_wang
 * CloudDiskMap 网盘类型映射
 * email 1352255400@qq.com
 * date 2022-03-10 17:15:14
 */
public class FileTypeMap {
    private FileTypeMap() {
    }

    public static Map<String, Integer> cateMap() {
        Map<String, Integer> map = new HashMap<>();
        // 1.图片格式 jpg、.png、.jpeg、.gif、.bmp、.webp psd
        map.put("jpg", FileCateEnum.PICTURE.getCode());
        map.put("jpeg", FileCateEnum.PICTURE.getCode());
        map.put("png", FileCateEnum.PICTURE.getCode());
        map.put("gif", FileCateEnum.PICTURE.getCode());
        map.put("bmp", FileCateEnum.PICTURE.getCode());
        map.put("psd", FileCateEnum.PICTURE.getCode());
        // 2.视频格式
        // 微软视频 ：wmv、asf、asx
        map.put("wmv", FileCateEnum.VIDEO.getCode());
        map.put("asf", FileCateEnum.VIDEO.getCode());
        map.put("asx", FileCateEnum.VIDEO.getCode());
        // Real Player ：rm、 rmvb
        map.put("rm", FileCateEnum.VIDEO.getCode());
        map.put("rmvb", FileCateEnum.VIDEO.getCode());
        // MPEG视频 ：mp4 手机视频 ：3gp
        map.put("mp4", FileCateEnum.VIDEO.getCode());
        map.put("3gp", FileCateEnum.VIDEO.getCode());
        // Apple视频 ：mov、m4v
        map.put("mov", FileCateEnum.VIDEO.getCode());
        map.put("m4v", FileCateEnum.VIDEO.getCode());
        // 其他常见视频：avi、dat、mkv、flv、vob 等
        map.put("avi", FileCateEnum.VIDEO.getCode());
        map.put("dat", FileCateEnum.VIDEO.getCode());
        map.put("mkv", FileCateEnum.VIDEO.getCode());
        map.put("flv", FileCateEnum.VIDEO.getCode());
        map.put("vob", FileCateEnum.VIDEO.getCode());
        // 3.文档 ：txt、doc、wps、docx、xls、xlsx、pdf
        map.put("txt", FileCateEnum.DOCUMENT.getCode());
        map.put("wps", FileCateEnum.DOCUMENT.getCode());
        map.put("doc", FileCateEnum.DOCUMENT.getCode());
        map.put("docx", FileCateEnum.DOCUMENT.getCode());
        map.put("xls", FileCateEnum.DOCUMENT.getCode());
        map.put("xlsx", FileCateEnum.DOCUMENT.getCode());
        map.put("ppt", FileCateEnum.DOCUMENT.getCode());
        map.put("pptx", FileCateEnum.DOCUMENT.getCode());
        map.put("pdf", FileCateEnum.DOCUMENT.getCode());
        // 4.声音文件：wav、mp3
        map.put("mp3", FileCateEnum.MUSIC.getCode());
        map.put("wav", FileCateEnum.MUSIC.getCode());
        return map;
    }
}
