package com.bolsadeideas.springboot.backend.chat.socio;

import com.bolsadeideas.springboot.backend.chat.socio.Socio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ISocioDao extends MongoRepository<Socio, Long> {
    @Query("{email: ?0}")
    List<Socio> findbyEmail(String email);

}
