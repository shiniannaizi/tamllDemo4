package com.tamll.learn.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 图片上传工具类
 */
public class UploadImageUtils {

    /**
     * 图片上传
     * @param files 图片文件
     * @return 返回图片路径
     * @throws IOException 抛出读写异常
     */
    public static String uploadImage(List<MultipartFile> files) throws IOException {
        String imgurl = null;
        for (int i = 0; i < files.size(); i++) {
            if (!files.get(i).isEmpty()) {
                byte[] bytes = files.get(i).getBytes();
                String srcFileName = files.get(i).getOriginalFilename();
                String fileExt = srcFileName.substring(srcFileName
                        .lastIndexOf('.'));
                String fileName = IDUtils.getImageName() + fileExt;
                String fullFilePath =
                        "E:/Ideaprojects/tamllDemo/src/main/webapp/WEB-INF/uploads/" + fileName;
                FileOutputStream fos = new FileOutputStream(fullFilePath); // 写入文件
                fos.write(bytes);
                fos.close();
                imgurl = fileName;
            }
        }
        return imgurl;
    }

}
