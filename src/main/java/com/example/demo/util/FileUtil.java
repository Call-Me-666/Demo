package com.example.demo.util;

import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @Description:
 * @Program: demo
 * @Author: zjx
 * @Create: 2022-02-16 11:19:39
 * @Version: 1.0
 **/
@Component
public class FileUtil {
    public void saveFile(InputStream inputStream, String path, String name) {
        OutputStream os = null;
        String fileName = path + File.separator + name;
        System.out.println("---文件" + fileName + "开始落盘---");
        try {
            byte[] bytes = new byte[1024];
            int len;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            os = new FileOutputStream(fileName);
            while ((len = inputStream.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            try {
                System.out.println("---文件" + fileName + "落盘完成---");
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
