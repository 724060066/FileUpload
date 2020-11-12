package com.buba.bigdata.tools;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.region.Region;

import java.net.URL;
import java.util.Date;

/**
 * @Author: chenrui
 * @Description:
 * @Date: Created in 16:32 2020/11/9.
 */
public class getUrl {
    public static void main(String[] args) {
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(Constants.COS_SECRET_ID, Constants.COS_SECRET_KEY);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(Constants.COS_REGION);
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);

        // 存储桶的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
        String key = Constants.COS_IMG_PATH + "ccc.jpg";
        GeneratePresignedUrlRequest req =
                new GeneratePresignedUrlRequest(Constants.COS_BUCKET_NAME, key, HttpMethodName.GET);
        // 设置签名过期时间(可选), 若未进行设置, 则默认使用 ClientConfig 中的签名过期时间(1小时)
        // 这里设置签名在半个小时后过期
        Date expirationDate = new Date(System.currentTimeMillis() + 30L * 60L * 1000L);
        req.setExpiration(expirationDate);
        URL url = cosClient.generatePresignedUrl(req);
        System.out.println(url.toString());
        cosClient.shutdown();
    }
}
