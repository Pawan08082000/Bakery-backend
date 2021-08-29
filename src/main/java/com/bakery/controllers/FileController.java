package com.bakery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bakery.payload.FileUploadResponse;
import com.bakery.service.FileStorageService;

@RestController
public class FileController {

	@Autowired
	FileStorageService fileStorageService;
	
	public FileController(FileStorageService fileStorageService) {
		this.fileStorageService = fileStorageService;
	}
	
	@PostMapping(path = "/upload")
	FileUploadResponse singleFileUpload(@RequestParam("file") MultipartFile file){
		String fileName = fileStorageService.storeFile(file);
		System.out.println(fileName);
		String url = ServletUriComponentsBuilder.fromCurrentContextPath()
		.path("/download")
		.path(fileName)
		.toUriString();
		
		String contentType = file.getContentType();
		FileUploadResponse response = new FileUploadResponse(fileName, contentType, url);
//		ResponseEntity<FileUploadResponse> response = ResponseEntity<FileUploadResponse>(fileUpload, HttpStatus.ACCEPTED);
		
		return response;
	}
}
