package com.yph.util.utli;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.yph.util.ID;
import com.yph.util.R;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

public class AliyunOSSUtil {
    //容器地域节点
    static String ENDPOINT;
    //RAM用户keyid
    static String ACCESS_KEY_ID;
    //RAM用户secert
    static String ACCESS_KEY_SECERT;
    //oss容器名
    static String BUCKET_NAME;
    //上传文件夹
    static String FOLDER_NAME;

    static {
        ENDPOINT = "oss-cn-hangzhou.aliyuncs.com";
        ACCESS_KEY_ID = "LTAI5tGMUAPVzX9WFHckF6j8";
        ACCESS_KEY_SECERT = "KZNbpWwRIv7uohvjxmnjcsTpdR9cl6";
        BUCKET_NAME = "tran-text";
        FOLDER_NAME = "";
    }


    public static R deleteImage(String imageName) throws Exception{
        OSSClient client = new OSSClient(ENDPOINT,
              ACCESS_KEY_ID,ACCESS_KEY_SECERT);
        client.deleteObject(BUCKET_NAME, imageName);
        client.shutdown();
        return R.success();
    }

    /**
     * 获取阿里云OSS客户端对象
     * @return oss
     */
    public static OSS getOSSClient() throws Exception{

        return new OSSClientBuilder().build(ENDPOINT,ACCESS_KEY_ID,ACCESS_KEY_SECERT);
    }


    /**
     * 上传图片至OSS
     *
     * @param ossClient     oss连接
     * @param bucketName    存储空间
     * @param folder        模拟文件夹名 如"test-kaka/headImage//"
     * @param afterFileName 上传后的文件名
     * @return String 返回的唯一MD5数字签名（在获取图片链接时会追加在链接后面）
     */
    public static String  uploadImg2OSS(OSSClient ossClient, BufferedImage bufImg, String bucketName, String folder, String beforeFileName, String afterFileName) {
        String resultStr = null;
        try {
            // 存储图片文件byte数组
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            // 图片写入到 ImageOutputStream  jpg
            ImageIO.write(bufImg,getFileType(beforeFileName), bos);
            //以输入流的形式上传文件
            InputStream is = new ByteArrayInputStream(bos.toByteArray());
            //文件名
            String fileName = beforeFileName;
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(is.available());
            //指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            //指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
//            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件   (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(bucketName, folder + afterFileName, is, metadata);
            //解析结果
            resultStr = putResult.getETag();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr;
    }

    public static String getFileType(String fileName){
        //文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
        //后缀名转小写
        String fileType = fileExtension.toLowerCase();
        return fileType;
    }

    /**
     * 上传视频至OSS
     *
     * @param ossClient     oss连接
     * @param bucketName    存储空间
     * @param folder        模拟文件夹名 如"test-kaka/headImage//"
     * @param afterFileName 上传后的文件名
     * @return String 返回的唯一MD5数字签名（在获取图片链接时会追加在链接后面）
     */
    public static String uploadVideo2OSS(OSSClient ossClient, InputStream is, String bucketName, String folder, String beforeFileName, String afterFileName) {
        String resultStr = null;
        try {
            //文件名
            String fileName = beforeFileName;
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(is.available());
            //指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            //指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
//            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件   (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(bucketName, folder + afterFileName, is, metadata);
            //解析结果
            resultStr = putResult.getETag();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr;
    }


    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static String getContentType(String fileName) {
        //文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        //后缀名转小写
        String fileType = fileExtension.toLowerCase();
        //返回类型
        String contentType = "";
        switch (fileType) {
            case ".bmp":
                contentType = "image/bmp";
                break;
            case ".gif":
                contentType = "image/gif";
                break;
            case ".png":
            case ".jpeg":
            case ".jpg":
                contentType = "image/jpg";
                break;
            case ".html":
                contentType = "text/html";
                break;
            case ".txt":
                contentType = "text/plain";
                break;
            case ".vsd":
                contentType = "application/vnd.visio";
                break;
            case ".ppt":
            case ".pptx":
                contentType = "application/vnd.ms-powerpoint";
                break;
            case ".doc":
            case ".docx":
                contentType = "application/msword";
                break;
            case ".xml":
                contentType = "text/xml";
                break;
            case ".mp4":
                contentType = "video/mp4";
                break;
            default:
                contentType = "application/octet-stream";
                break;
        }
        return contentType;
    }

    /**
     * 获得url链接
     * @param key 上传图片的路径+名称（如：test-kaka/headImage/1546404670068899.jpg）
     * @return 图片链接：
     * http://xxxxx.oss-cn-beijing.aliyuncs.com/test/headImage/1546404670068899.jpg?
     * Expires=1861774699&OSSAccessKeyId=****=p%2BuzEEp%2F3JzcHzm%2FtAYA9U5JM4I%3D
     * （Expires=1861774699&OSSAccessKeyId=LTAISWCu15mkrjRw&Signature=p%2BuzEEp%2F3JzcHzm%2FtAYA9U5JM4I%3D
     *     分别为：有前期、keyID、签名）
     */
    public static String getUrl(String key) throws Exception{
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        OSSClient ossClient = (OSSClient) AliyunOSSUtil.getOSSClient();
        // 生成URL
        URL url = ossClient.generatePresignedUrl(AliyunOSSUtil.BUCKET_NAME, key, expiration);
        return url.toString().substring(0, url.toString().lastIndexOf("?"));
    }

    //店铺上传视频
    public static String uploadVideo(MultipartFile file,Integer shopId,String paper) throws Exception {
        String filename = file.getOriginalFilename();
        String prefix = filename.substring(file.getOriginalFilename().lastIndexOf("."));
        String beforeFileName = filename;
        String afterFileName = ID.id() + prefix;
        InputStream input = file.getInputStream();
        String floder;
        if(shopId!=null){
            floder = AliyunOSSUtil.FOLDER_NAME+"shop/"+shopId+"/";
        }else{
            if (paper!=null){
                floder = AliyunOSSUtil.FOLDER_NAME+"comm/"+paper+"/";
            }else {
                floder = AliyunOSSUtil.FOLDER_NAME+"comm/";
            }
        }
        OSSClient ossClient = (OSSClient) AliyunOSSUtil.getOSSClient();
        uploadVideo2OSS(ossClient, input,AliyunOSSUtil.BUCKET_NAME, floder, beforeFileName, afterFileName);
        return getUrl(floder+afterFileName);
    }
    //公共上传视频
    public static String uploadVideo(MultipartFile file) throws Exception {
        return uploadVideo(file,null,null);
    }
    public static String uploadVideo(MultipartFile file,String paper) throws Exception {
        return uploadVideo(file,null,paper);
    }

    //店铺上传图片
    public static String uploadShopGoodsImg(MultipartFile file, Integer shopId,String paper) throws Exception {
        String filename = file.getOriginalFilename();
        String prefix = filename.substring(file.getOriginalFilename().lastIndexOf("."));
        String beforeFileName = filename;
        String afterFileName = ID.id() + prefix;
        InputStream input = file.getInputStream();
        Image image = Toolkit.getDefaultToolkit().createImage(file.getBytes());
        BufferedImage bufImg = AliyunOSSUtil.toBufferedImage(image);;
//        BufferedImage bufImg = ImageIO.read(input);
        String floder;
        if(shopId!=null){
            floder = AliyunOSSUtil.FOLDER_NAME+"shop/"+shopId+"/";
        }else{
            if (paper!=null){
                floder = AliyunOSSUtil.FOLDER_NAME+"comm/"+paper+"/";
            }else {
                floder = AliyunOSSUtil.FOLDER_NAME+"comm/";
            }

        }
        OSSClient ossClient = (OSSClient) AliyunOSSUtil.getOSSClient();
        uploadImg2OSS(ossClient, bufImg, AliyunOSSUtil.BUCKET_NAME, floder, beforeFileName, afterFileName);
        return getUrl(floder+afterFileName);
    }




    //上传图片,公共上传方法
    public static String uploadShopGoodsImg(MultipartFile file) throws Exception {
        return uploadShopGoodsImg(file,null,null);
    }
    public static String uploadShopGoodsImg(MultipartFile file,String paper) throws Exception {
        return uploadShopGoodsImg(file,null,paper);
    }





    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null),
                    image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }
        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            bimage = new BufferedImage(image.getWidth(null),
                    image.getHeight(null), type);
        }
        // Copy image to buffered image
        Graphics g = bimage.createGraphics();
        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;
    }


}
