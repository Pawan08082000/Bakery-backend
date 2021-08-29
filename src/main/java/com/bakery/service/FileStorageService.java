package com.bakery.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

	Path fileStoragePath;
	
	private String fileStorageLocation;
	
	public FileStorageService(@Value("${file.upload-dir}") String fileStorageLocation) {
		
		this.fileStorageLocation = fileStorageLocation;
		fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
		
		try {
			Files.createDirectories(fileStoragePath);
		} catch (IOException e) {
			throw new RuntimeException("Issue in creating file directry");
		}
		
	}
	
	public String storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());	
		
		Path filePath = Paths.get(fileStoragePath+"\\"+ fileName);
		
		try {
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException("Issue in file uploading");
		}
		
		
		return fileName;
	}
}
