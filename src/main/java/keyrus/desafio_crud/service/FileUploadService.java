package keyrus.desafio_crud.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	
	public void uploadFile(MultipartFile file) throws IllegalStateException, IOException {
		file.transferTo(new File("C:\\Users\\Jose Antonio\\Desktop\\desafio\\logFiles\\" + file.getOriginalFilename()));
	}
}
