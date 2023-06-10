package com.bolsadeideas.springboot.backend.chat.chat;

import com.bolsadeideas.springboot.backend.chat.chat.Mensaje;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IChatDao extends MongoRepository<Mensaje, String> {

    public List<Mensaje> findFirst5ByOrderByFechaDesc();

}