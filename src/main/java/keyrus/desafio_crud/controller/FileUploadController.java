package keyrus.desafio_crud.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import keyrus.desafio_crud.model.entities.Log;
import keyrus.desafio_crud.model.repositories.LogRepository;
import keyrus.desafio_crud.service.FileUploadService;

@RestController
@RequestMapping("/file")
public class FileUploadController {
	
	@Autowired
	FileUploadService fileUploadService;
	
	@Autowired
	LogRepository logRepository;
	
	@PostMapping("/justUpload")
	public String uploadFile(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		fileUploadService.uploadFile(file);
		return file.getOriginalFilename() + " upload!";
	}
	
	@PostMapping
	public String uploadData(@RequestParam("file") MultipartFile file) throws IOException {
		List<Log> logList = new ArrayList<>();
		InputStream inputStream = file.getInputStream();
		CsvParserSettings setting = new CsvParserSettings();
		setting.setHeaderExtractionEnabled(true);
		CsvParser parser = new CsvParser(setting);
		
		List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
		
		parseAllRecords.forEach(record -> {
			Log log = new Log();
			log.setDate(record.getDate("Data", "dd-M-yyyy hh:mm:ss.SSS"));
			log.setIp(record.getString("Ip"));
			log.setRequest(record.getString("Request"));
			log.setStatus(record.getInt("Status"));
			log.setUserAgent(record.getString("UserAgent"));
			
			logList.add(log);
		});
		
		logRepository.saveAll(logList);
		
		return "Upload complete!";
	}
}
