package com.bolsadeideas.springboot.backend.chat.models;

import com.bolsadeideas.springboot.backend.chat.entity.Mensaje;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IChatDao extends MongoRepository<Mensaje, String> {

    public List<Mensaje> findFirst5ByOrderByFechaDesc();

}