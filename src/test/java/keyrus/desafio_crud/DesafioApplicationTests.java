package keyrus.desafio_crud;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import keyrus.desafio_crud.model.entities.Log;
import keyrus.desafio_crud.model.repositories.LogRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class DesafioApplicationTests {

	@Autowired
	LogRepository logRepository;

	@Test
	@Order(1)
	public void testCreate() {
		Log log = new Log();
		Calendar c = Calendar.getInstance();
		Date data = c.getTime();
		log.setDate(data);
		log.setIp("192.168.0.1");
		log.setRequest("post");
		log.setStatus(201);
		log.setUserAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0\n");

		logRepository.save(log);
		assertNotNull(logRepository.findById(1));
	}

	@Test
	@Order(2)
	public void testGetAll() {
		List<Log> list = (List<Log>) logRepository.findAll();
		assertThat(list).size().isGreaterThan(0);
	}

	@Test
	@Order(3)
	public void testGetById() {
		assertEquals("post", logRepository.findById(1).get().getRequest());
	}

	@Test
	@Order(4)
	public void testGetAllByUserAgent() {
		List<Log> list = (List<Log>) logRepository.searchByUserAgentLike("Windows");
		assertThat(list).size().isGreaterThan(0);
	}
	
	@Test
	@Order(5)
	public void testGetAllByIp() {
		List<Log> list = (List<Log>) logRepository.searchByIp("192");
		assertThat(list).size().isGreaterThan(0);
	}

	@Test
	@Order(6)
	public void testUpdate() {
		Log log = logRepository.findById(1).get();
		log.setStatus(202);
		logRepository.save(log);
		assertNotEquals(201, logRepository.findById(1).get().getStatus());
	}

	@Test
	@Order(7)
	public void testDelete() {
		logRepository.deleteById(1);
		assertThat(!logRepository.existsById(1));
	}
}
