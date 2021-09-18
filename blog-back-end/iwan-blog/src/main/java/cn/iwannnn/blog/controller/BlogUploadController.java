package cn.iwannnn.blog.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.iwannnn.blog.service.IBlogUploadService;
import cn.iwannnn.common.core.domain.AjaxResult;

/**
 * 博客图片上传Controller
 * 
 * @author iwan
 * @date 2021-09-17
 */
@RestController
@RequestMapping("/api/upload")
public class BlogUploadController {

	@Autowired
	private IBlogUploadService blogUploadSerive;

	@RequestMapping("/file")
	public AjaxResult uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
		System.out.println(multipartFile.toString());
		String fileUrl = blogUploadSerive.uploadFile(multipartFile);
		return AjaxResult.success("success", fileUrl);
	}
}
