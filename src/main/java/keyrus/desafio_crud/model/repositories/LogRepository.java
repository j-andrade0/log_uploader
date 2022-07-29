package keyrus.desafio_crud.model.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import keyrus.desafio_crud.model.entities.Log;

public interface LogRepository extends CrudRepository<Log, Integer> {

	@Query("SELECT l FROM Log l WHERE l.userAgent LIKE %:userAgent%")
	public Iterable<Log> searchByUserAgentLike(@Param("userAgent") String userAgent);
	
	@Query("SELECT l FROM Log l WHERE l.ip LIKE %:ip%")
	public Iterable<Log> searchByIp(@Param("ip") String ip);
}
