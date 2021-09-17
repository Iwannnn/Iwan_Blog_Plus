package cn.iwannnn.blog.service.impl;

import java.io.IOException;
import java.util.Random;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.iwannnn.blog.service.IBlogUploadService;

@Service
public class BlogUploadServiceImpl implements IBlogUploadService {
	private static final String accessKey = "w9uwfJ-Wz6JIPeWyQl8iTPe577wybVgZmSJ8ShjL";
	private static final String secretKey = "26kSxtoZcRCLJzKAmabCMcBv3lOF1Q_ZGh4poNBo";
	private static final String baseUrl = "http://cdn.iwannnn.cn/";
	private static final String bucket = "iwannnn";

	@Override
	public String uploadPicture(MultipartFile file) throws IOException {
		String res = "";
		Configuration configuration = new Configuration(Region.region0());
		UploadManager uploadManager = new UploadManager(configuration);
		String key = getRandomCharacterAndNumber(10) + ".png";
		Auth auth = Auth.create(accessKey, secretKey);
		String uptoken = auth.uploadToken(bucket);
		try {
			byte[] localFile = file.getBytes();
			Response response = uploadManager.put(localFile, key, uptoken);
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			res = baseUrl + putRet.key;
		} catch (QiniuException e) {
		}
		return res;
	}

	public static String getRandomCharacterAndNumber(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

			if ("char".equalsIgnoreCase(charOrNum)) // 字符串
			{
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
				val += (char) (choice + random.nextInt(26));
				// int choice = 97; // 指定字符串为小写字母
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) // 数字
			{
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}
}
