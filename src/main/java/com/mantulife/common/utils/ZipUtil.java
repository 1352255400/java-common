package com.mantulife.common.utils;


import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Author W_wang
 * Description 压缩文件
 * Date 2019-07-29 10:14
 **/
@Slf4j
public class ZipUtil {

    private ZipUtil() {
    }

    private static Random random;

    /**
     * 批量打包
     *
     * @param fileSavePath 项目根目录
     * @param list list
     * @param fileSavePath fileSavePath
     * @return  return return
     */
    public static String createZipAndReturnPath(List<String> list, String fileSavePath) {
        try {
            //生成打包下载后的zip文件:Papers.zip
            int randomNum = random.nextInt(99999999);
            String papersZipName = randomNum + ".zip";

            //zip文件保存路径
            String zipPath = fileSavePath + "/" + papersZipName;

            //创建压缩文件
            try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipPath))) {

                //遍历数据追加到压缩包中
                for (int i = 0; i < list.size(); i++) {
                    //获得下载文件完整路径
                    String downloadPath = list.get(i);
                    //获得文件名
                    String fileName = downloadPath.substring(downloadPath.lastIndexOf("/") + 1);

                    //以论文标题为每个文件命名
                    try (FileInputStream fis = new FileInputStream(downloadPath)) {
                        out.putNextEntry(new ZipEntry(fileName));

                        //写入压缩包
                        int len;
                        byte[] buffer = new byte[1024];
                        while ((len = fis.read(buffer)) > 0) {
                            out.write(buffer, 0, len);
                        }
                        out.closeEntry();
                    }
                }

                out.flush();
            }
            return zipPath;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("13");
        }
        return null;
    }
}
