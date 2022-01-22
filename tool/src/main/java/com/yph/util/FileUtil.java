package com.yph.util;

import java.io.*;

public class FileUtil {

    // 文件写监听
    private FileWriterListener fileWriterListener;

    /**
     * 写文件
     *
     * @param outFile     保存路径文件
     * @param is          读流
     * @param fileSize    文件大小
     * @param writeLength 控制文件写入速度
     * @return File
     */
    public File write(File outFile, InputStream is, long fileSize, int writeLength) {
        boolean hasError = false;
        BufferedInputStream bis = null;
        OutputStream os = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            os = new FileOutputStream(outFile);
            bos = new BufferedOutputStream(os);
            long written = 0;
            byte bytes[] = new byte[writeLength];
            while (written < fileSize) {
                if (written + writeLength > fileSize) {
                    writeLength = (int) (fileSize - written);
                    bytes = new byte[writeLength];
                }
                bis.read(bytes);
                bos.write(bytes);
                bos.flush();
                written += writeLength;
                if (fileWriterListener != null) {
                    fileWriterListener.writing((float) written / fileSize);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (fileWriterListener != null) {
                fileWriterListener.error();
                hasError = true;
            }
        }
        try {
            if (bis != null) {
                bis.close();
                is.close();
            }
            if (bos != null) {
                bos.close();
                os.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!hasError && fileWriterListener != null) {
            fileWriterListener.success();
        }

        return outFile;
    }

    public void setFileWriterListener(FileWriterListener fileWriterListener) {
        this.fileWriterListener = fileWriterListener;
    }

    /**
     * 文件写出监听器
     */
    public interface FileWriterListener {

        void writing(float percent);

        void error();

        void success();

    }
}
