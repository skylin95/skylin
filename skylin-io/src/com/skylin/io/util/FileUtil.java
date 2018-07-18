package com.skylin.io.util;

import com.skylin.io.enums.Utf8BomChar;
import com.sun.corba.se.spi.orbutil.fsm.Input;

import java.io.*;
import java.util.Arrays;

/**
 * @author skylin
 * <P>CreateTime:2018-07-18 10:00:01</P>
 * <p>文件常用工具</p>
 */
public class FileUtil {
    /**
     * 文件是否包含UTF-8的BOM头
     * @param fileBytes
     * @return
     */
    public static boolean hasUtf8Bom(byte[] fileBytes) {
        if (fileBytes == null || fileBytes.length < 3) {
            return false;
        }

        boolean hasUtfBom = (fileBytes[0] == Utf8BomChar.FirstChar.getDecValue())
                         && (fileBytes[1] == Utf8BomChar.SecondChar.getDecValue())
                         && (fileBytes[2] == Utf8BomChar.ThirdChar.getDecValue());

        return hasUtfBom;
    }

    /**
     * 文件是否包含UTF-8BOM头
     * @param file
     * @return
     * @throws IOException
     */
    public static boolean hasUtf8Bom(File file) throws IOException {
        if (file == null || !file.exists() || !file.isFile()) {
            return false;
        }

        byte[] fileBytes = getFileBytes(file);
        return hasUtf8Bom(fileBytes);
    }

    /**
     * 移除文件中的UTF-8BOM头
     * @param fileBytes
     * @return
     * @throws IOException
     */
    public static byte[] removeUtf8Bom(byte[] fileBytes) throws IOException {
        if (fileBytes == null || !hasUtf8Bom(fileBytes)) {
            return fileBytes;
        }

        byte[] trgtBytes = Arrays.copyOf(fileBytes, fileBytes.length - 3);

        return trgtBytes;
    }

    /**
     * 移除文件中的UTF-8BOM头
     * @param in
     * @return
     * @throws IOException
     */
    public static byte[] removeUtf8Bom(InputStream in) throws IOException {
        byte[] fileBytes = getFileBytes(in);
        return removeUtf8Bom(fileBytes);
    }

    /**
     * 移除文件中的UTF-8BOM头
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] removeUtf8Bom(String filePath) throws IOException {
        byte[] fileBytes = getFileBytes(filePath);
        return removeUtf8Bom(fileBytes);
    }

    /**
     * 移除文件夹下所有文件的UTF-8BOM头
     * @param srcDir
     * @param trgtDir
     * @return
     * @throws IOException
     */
    public static String removeUtf8BomOfDir(File srcDir, File trgtDir) throws IOException {
        if (srcDir == null || !srcDir.exists() || !srcDir.isDirectory()) {
            return "";
        }


        if (trgtDir == null) {
            String userHome = System.getProperty("user.home");
            String workDir = "skylin";
            trgtDir = new File(userHome, workDir);
        }

        File[] childFiles = srcDir.listFiles();
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                File newDir = new File(trgtDir, childFile.getName());
                if (!newDir.exists()) {
                    newDir.mkdirs();
                }

                removeUtf8BomOfDir(childFile, newDir);
            } else {
                byte[] srcFileBytes = getFileBytes(childFile);
                if (!hasUtf8Bom(srcFileBytes)) {
                    continue;
                }

                File newFile = new File(trgtDir, childFile.getName());
                byte[] trgtFileBytes = removeUtf8Bom(srcFileBytes);
                saveBytes2File(trgtFileBytes, newFile, true);

                System.out.println("|FilUtil 处理文件" + childFile.getAbsolutePath());
            }
        }

        return trgtDir.getAbsolutePath();
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
        return getFileBytes(file);
    }

    /**
     * 获取文件字节数组
     * @param file
     * @return
     * @throws IOException
     */
    public static byte[] getFileBytes(File file) throws IOException {
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

    /**
     * 保存文件
     * @param fileByets
     * @param file
     * @param createNew
     * @throws IOException
     */
    public static void saveBytes2File(byte[] fileByets, File file, boolean createNew) throws IOException {
        if (fileByets == null) {
            return;
        }

        if (!file.exists()) {
            if (!createNew) {
                throw new IOException("file does not exist");
            }

            boolean success = file.createNewFile();
            if (!success) {
                throw new IOException("create new file failed");
            }
        }

        OutputStream out = null;

        try {
            out = new FileOutputStream(file);
            out.write(fileByets);
        } catch (IOException e) {
            throw e;
        } finally {
            if (out != null) {
                out.close();;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String srcDir = "D:/Work/WorkCvs/20.SrcCode/1190.Pansoft.ptc.adv/02.BIS";
        String trgtDir = "D:/ReactSpace/NOBom";

        removeUtf8BomOfDir(new File(srcDir), new File(trgtDir));
    }
}
