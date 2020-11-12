package com.buba.bigdata.tools;

import com.buba.bigdata.pojo.Files;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传解析util
 *
 * @Author: chenrui
 * @Description:
 * @Date: Created in 19:09 2020/11/9.
 */
public class FileUtil {

    /**
     * 上传base64格式的文件到本地服务器，并返回新创建文件的相对路径
     *
     * @param fileBase64
     * @param request
     * @return
     */
    public String fileUploadForBase64(String fileBase64, HttpServletRequest request) {
        // 临时文件的相对路径+文件名
        String newFilePath = "";
        if (!fileBase64.isEmpty()) {
            // 由于前端没有预编码，字符串中的"+"会变为" "，这里进行替换
            fileBase64 = fileBase64.replaceAll(" ", "+");
            // 截取base64中文件
            String[] arr = fileBase64.split("base64,");

            // 取得上传文件的扩展名
            String extensionName = this.getFilePrefix(arr[0]);

            // 临时文件的文件名,使用UUID.randomUUID()取得随机数，防止文件名重复
            String fileName = UUID.randomUUID().toString() + extensionName;

            // 取得上传文件的绝对路径
            String path = request.getSession().getServletContext().getRealPath(Constants.FILE_PATH);

            // 判断临时文件夹是否存在，不存在则创建
            File filePath = new File(path);
            if (!filePath.exists()) {
                filePath.mkdir();
            }

            // 临时文件的完整路径+文件名
            String picPath = filePath + "/" + fileName;

            try {
                BASE64Decoder decoder = new BASE64Decoder();
                // Base64解码
                byte[] bytes = decoder.decodeBuffer(arr[1]);
                for (int i = 0; i < bytes.length; ++i) {
                    // 调整异常数据
                    if (bytes[i] < 0) {
                        bytes[i] += 256;
                    }
                }

                OutputStream os = new FileOutputStream(picPath);
                os.write(bytes);
                os.flush();
                os.close();
                // 临时文件相对路径
                newFilePath = request.getSession().getServletContext().getContextPath() + File.separator + Constants.FILE_PATH + fileName;
            } catch (IOException e) {
                newFilePath = "error";
                throw new RuntimeException();
            }
        }
        return newFilePath;
    }

    /**
     * 删除临时文件
     *
     * @param image
     * @param request
     * @return
     */
    public String fileDelete(String image, HttpServletRequest request) {

        // 取得要删除的文件名
        int strIndex = image.lastIndexOf("/");
        String fileName = image.substring(strIndex + 1);

        // 取得服务器的绝对路径
        String path = request.getSession().getServletContext().getRealPath(Constants.FILE_PATH);

        File file = new File(path + fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return "删除成功";
            } else {
                return "error";
            }
        } else {
            return "error";
        }
    }

    /**
     * 上传文件到COS
     * @param images
     * @param request
     * @return
     */
    public List<String> uplodeFileToCOS(String[] images, HttpServletRequest request) {
        List<String> fileNames = new ArrayList<>(images.length);
        if(images.length > 0) {
            // 取得cosClient
            COSClient cosClient = CosFile.getCosClient();
            for (String image : images) {
                // 取得要删除的文件名
                int strIndex = image.lastIndexOf("/");
                String fileName = image.substring(strIndex + 1);

                // 取得服务器的绝对路径
                String path = request.getSession().getServletContext().getRealPath(Constants.FILE_PATH);

                // 本地文件上传
                File file = new File(path + fileName);

                ObjectMetadata objectMetadata = new ObjectMetadata();

                // 指定要上传到 COS 上对象键
                String key = Constants.COS_IMG_PATH  + fileName;
                PutObjectResult putObjectResult = cosClient.putObject(Constants.COS_BUCKET_NAME, key, file);
                // 获取文件的 etag
                fileNames.add(fileName);
            }
            // 关闭cosClient
            cosClient.shutdown();
        }

        return fileNames;
    }

    public List<Files> getFileUrlList(List<Files> fileList) {
        List<Files> fileUrlList = new ArrayList<>(fileList.size());
        if (!fileList.isEmpty()) {
            // 取得cosClient
            COSClient cosClient = CosFile.getCosClient();
            for (Files file : fileList) {
                // 存储桶的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
                String key = Constants.COS_IMG_PATH + file.getUrl();
                GeneratePresignedUrlRequest req =
                        new GeneratePresignedUrlRequest(Constants.COS_BUCKET_NAME, key, HttpMethodName.GET);
                file.setUrl(cosClient.generatePresignedUrl(req).toString());
                fileUrlList.add(file);
            }
            // 关闭cosClient
            cosClient.shutdown();
        }

        return fileUrlList;
    }

    /**
     * 取出base64字符串中的文件扩展名
     *
     * @param base64ImgName
     * @return
     */
    private String getFilePrefix(String base64ImgName) {
        int strStartIndex = base64ImgName.indexOf("/");
        int strEndIndex = base64ImgName.indexOf(";");

        return "." + base64ImgName.substring(strStartIndex + 1, strEndIndex);
    }


}
