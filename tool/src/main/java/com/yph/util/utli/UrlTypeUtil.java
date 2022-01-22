package com.yph.util.utli;

/**
 * @ClassName : UrlTypeUtil
 * @Description : 判断url属于什么类型
 * @Author : LC  //作者
 * @Date: 2020-09-02 14:40
 */
public class UrlTypeUtil {

    /**
     * 判断url是否为视频
     * @param url
     * @return
     */
    public static boolean getUrlTypeIsVideo(String url) {
        String type = url.substring(url.indexOf(".") + 1);
        boolean i;
        switch (type) {
            case "mp4":
            case "avi":
                i = true;
                break;
            default:
                i = false;
                break;
        }
        return i;
    }

    /**
     * 判断url是否为图片
     * @param url
     * @return
     */
    public static boolean getUrlTypeIsImg(String url) {
        String type = url.substring(url.indexOf(".") + 1);
        boolean i;
        switch (type) {
            case "png":
            case "jpg":
                i = true;
                break;
            default:
                i = false;
                break;
        }
        return i;
    }

}
