package cn.iwannnn.blog.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IBlogUploadService {
	String uploadFile(MultipartFile file) throws IOException;
}
