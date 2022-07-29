package keyrus.desafio_crud.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import keyrus.desafio_crud.model.entities.Log;
import keyrus.desafio_crud.model.repositories.LogRepository;

@RestController
@RequestMapping("/log")
public class LogController {

	@Autowired
	private LogRepository logRepository;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public @ResponseBody Log newLog(@Valid Log log) {
		logRepository.save(log);
		return log;
	}

	// @PutMapping
	// public @ResponseBody Log updateLog(@Valid Log existingLog) {
	// 	Log teste = logRepository.findById(existingLog.getId()).get();
		
	// 	return teste;
	// }
	
	@GetMapping
	public Iterable<Log> getLogs() {
		return logRepository.findAll();
	}

	@GetMapping(path = "/{id}")
	public Optional<Log> getLogById(@PathVariable int id) {
		return logRepository.findById(id);
	}

	@GetMapping(path = "/userAgentLike/{userAgent}")
	public Iterable<Log> getLogByUserAgentLike(@PathVariable String userAgent) {
		return logRepository.searchByUserAgentLike(userAgent);
	}

	@GetMapping(path = "/ipLike/{ip}")
	public Iterable<Log> getLogByIp(@PathVariable String ip) {
		return logRepository.searchByIp(ip);
	}

	@DeleteMapping(path = "/{id}")
	public Optional<Log> deleteLogById(@PathVariable int id) {
		Optional<Log> found = logRepository.findById(id);
		logRepository.deleteById(id);
		return found;
	}
}
