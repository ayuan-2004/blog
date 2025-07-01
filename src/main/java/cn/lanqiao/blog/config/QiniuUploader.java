package cn.lanqiao.blog.config;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Data
@AllArgsConstructor
@Component
@Slf4j
public class QiniuUploader {
    private final String accessKey = "AptTKFqo5b7cnLIVo_GuDLifdUWaEiT0upgBTr8V";
    private final String secretKey = "mHfxR0WFpFUid80QMjhgTsArcsJlzOhCkKoTDfMq";
    private final String bucketName = "niyablog";
    private final String domain = "sxkuv2a2t.hn-bkt.clouddn.com";
    // 例如 "http://qiniu.example.com" 或 "https://qiniu.example.com"

    private final UploadManager uploadManager;
    private final Auth auth;

    public QiniuUploader() {
        // 1. 创建配置对象（根据存储区域选择）
        Configuration cfg = new Configuration(Zone.zone2()); // zone0-华东, zone1-华北, zone2-华南

        // 2. 创建上传管理器
        this.uploadManager = new UploadManager(cfg);

        // 3. 创建认证对象
        this.auth = Auth.create(accessKey, secretKey);
    }

    /**
     * 上传文件到七牛云
     * @param localFilePath 本地文件路径
     * @param key 存储在七牛云的文件名（可为null，由七牛自动生成）
     * @return 可公开访问的URL
     */
    /**
     * 处理 MultipartFile 上传
     */
    public String upload(MultipartFile multipartFile, String key) throws IOException, QiniuException {
        // 1. 验证文件
        if (multipartFile.isEmpty()) {
            throw new IOException("上传文件为空");
        }

        // 2. 创建临时文件
        File tempFile = null;
        try {
            tempFile = convertMultipartFileToFile(multipartFile);
            log.debug("创建临时文件: {}", tempFile.getAbsolutePath());

            // 3. 执行上传
            return uploadFile(tempFile, key);
        } finally {
            // 4. 清理临时文件
            if (tempFile != null && tempFile.exists()) {
                boolean deleted = tempFile.delete();
                log.debug("删除临时文件{}: {}", tempFile.getAbsolutePath(), deleted ? "成功" : "失败");
            }
        }
    }

    private String uploadFile(File file, String key) throws QiniuException {
        // 生成上传凭证
        String upToken = auth.uploadToken(bucketName, key, 3600, new StringMap());
        log.debug("生成上传Token: {}", upToken);

        // 执行上传
        Response response = uploadManager.put(file, key, upToken);
        log.debug("七牛云响应: {}", response.bodyString());

        // 解析结果
        String actualKey = response.jsonToMap().get("key").toString();
        String url = getPublicUrl(actualKey);
        log.info("文件上传成功: {}", url);

        return url;
    }
    /**
     * 处理本地文件上传
     */
    public String upload(String filePath, String key) throws QiniuException {
        File file = new File(filePath);
        return uploadFile(file, key);
    }

    /**
     * 转换 MultipartFile 为临时文件
     */
    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        String suffix = multipartFile.getOriginalFilename().substring(
                multipartFile.getOriginalFilename().lastIndexOf(".")
        );
        File tempFile = File.createTempFile("qiniu_", suffix);
        multipartFile.transferTo(tempFile);
        return tempFile;
    }

    /**
     * 清理临时文件
     */
    private void cleanupTempFile(File tempFile) {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    /**
     * 获取公开访问URL
     * @param key 文件在七牛云的key
     * @return 可访问的URL
     */
    public String getPublicUrl(String key) {
        // 如果是HTTPS域名，请确保使用https://开头
        return String.format("%s/%s", domain, key);
    }

    public static void main(String[] args) {
        QiniuUploader uploader = new QiniuUploader();
        try {
            String filePath = "/path/to/your/file.jpg";
            String url = uploader.upload(filePath, "custom-filename.jpg");
            System.out.println("文件访问URL: " + url);
        } catch (QiniuException e) {
            e.printStackTrace();
            System.err.println("上传失败: " + e.response.toString());
        }
    }
}