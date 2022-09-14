package com.mantulife.common.utils;

import cn.hutool.core.util.StrUtil;
import com.mantulife.common.exception.BusinessException;
import com.mantulife.common.model.enums.ErrorCodeEnum;
import com.mantulife.common.model.enums.FileCateEnum;
import com.mantulife.common.model.map.FileTypeMap;
import com.mantulife.common.model.vo.FileVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.UUID;

/**
 * Author W_wang
 * Description 文件工具类
 * Date 2019-07-29 10:14
 **/
@Slf4j
@Component
public class FileUtil {

    private FileUtil() {
    }

    private static String domain;

    @Value("${file.domain:''}")
    public static void setUrl(String url) {
        domain = url;
    }

    /**
     * Author W_wang
     * Description 上传文件
     * Date 2019-07-29 10:14
     *
     * @param file file
     * @param path path
     * @return  return path
     */
    public static FileVo upfile(MultipartFile file, String path) {
        FileVo fileVo = new FileVo();
        String fileName = file.getOriginalFilename();
        if (StrUtil.isEmpty(fileName) || fileName == null) {
            throw new BusinessException(ErrorCodeEnum.DATA_NOT_EXIST);
        }

        // 文件名
        String[] fileNamePart = fileName.split("\\.");
        // 文件后缀
        String fileSuffix = fileNamePart[fileNamePart.length - 1];
        // 文件根路径
        String dir = "/upfile/";

        // 保存文件路径
        String filePath = TimeUtil.getDate() + "/";
        if (StrUtil.isNotEmpty(path)) {
            filePath = path + "/" + TimeUtil.getDate() + "/";
        }
        filePath = filePath.replace("//", "/");
        checkFilePath(dir + filePath);
        log.info("文件上传保存的硬盘路径：" + filePath);

        // 附件完整路径
        String fileNameWithPath = filePath + UUID.randomUUID().toString() + "." + fileSuffix;
        try {
            InputStream input = file.getInputStream();
            FileOutputStream out = new FileOutputStream(new File(dir + fileNameWithPath));
            IOUtils.copy(input, out);
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
            throw new BusinessException("上传异常");
        }

        fileVo.setName(fileName);
        fileVo.setSize(file.getSize());
        fileVo.setSizeFormet(formetFileSize(file.getSize()));
        fileVo.setType(file.getContentType());
        fileVo.setTypeSuffix(fileSuffix);
        fileVo.setUrl(domain + fileNameWithPath);
        return fileVo;
    }


    /**
     * Author W_wang
     * Description 检查文件路径是否存在，不存在则创建
     * Date 2019-07-29 10:14
     *
     * @param filePath filePath
     */
    public static void checkFilePath(String filePath) {
        // 检查路径是否存在（创建）
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }


    /**
     * Author W_wang
     * Description 格式化文件大小
     * Date 2019-07-29 10:14
     *
     * @param fileLength fileLength
     * @return  return return
     */
    public static String formetFileSize(Long fileLength) {
        String fileSizeString = "";
        if (fileLength == null) {
            return fileSizeString;
        }
        DecimalFormat df = new DecimalFormat("#.00");
        if (fileLength < 1024) {
            fileSizeString = df.format((double) fileLength) + "B";
        } else if (fileLength < 1048576) {
            fileSizeString = df.format((double) fileLength / 1024) + "K";
        } else if (fileLength < 1073741824) {
            fileSizeString = df.format((double) fileLength / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileLength / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * Author W_wang
     * Description 获取文件大类
     * Date 2019-07-29 10:14
     *
     * @param fileName fileName
     * @return  return return
     */
    public static Integer getType(String fileName) {
        // 替换文件格式
        Integer fileType = FileCateEnum.OTHER.getCode();
        Map<String, Integer> cateMap = FileTypeMap.cateMap();
        String[] split = fileName.split("\\.");
        String type = split.length > 0 ? split[split.length - 1].toLowerCase() : "";
        System.out.println("文件类型" + type);
        if (cateMap.containsKey(type)) {
            fileType = cateMap.get(type);
        }
        return fileType;
    }
}
