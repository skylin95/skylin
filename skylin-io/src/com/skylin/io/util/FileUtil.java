package com.skylin.io.util;

import java.io.*;

/**
 * @author skylin
 * <P>CreateTime:2018-07-18 10:00:01</P>
 * <p>文件常用工具</p>
 */
public class FileUtil {
    public static boolean hasUtf8Bom(byte[] fileBytes) {
        if (fileBytes == null || fileBytes.length < 3) {
            return false;
        }

        return false;
    }

    public static byte[] removeUtf8Bom(byte[] fileBytes) throws IOException {
        return null;
    }

    /**
     * 获取文件字节数组
     * @param in
     * @return
     * @throws IOException
     */
    public static byte[] getFileBytes(InputStream in) throws IOException {
        if (in == null) {
            return new byte[0];
        }

        byte[] fileBytes = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            byte[] buffer = new byte[512];
            int bytes = -1;

            while((bytes = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytes);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            fileBytes = out.toByteArray();
            out.close();
        }

        return fileBytes;
    }

    /**
     * 获取文件字节数组
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] getFileBytes(String filePath) throws IOException {
        if (filePath == null || "".equals(filePath)) {
            return new byte[0];
        }

        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            return new byte[0];
        }

        byte[] fileBytes = null;
        InputStream in = null;

        try {
            in = new FileInputStream(file);
            fileBytes = getFileBytes(in);
        } catch (IOException e) {
            throw e;
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return fileBytes;
    }

    public static void main(String[] args) throws IOException {
        byte[] fileBytes = getFileBytes("utf8.txt");
        for (byte b : fileBytes) {
            System.out.println(b);
        }
    }
}
