package com.bolsadeideas.springboot.backend.chat.models;

import com.bolsadeideas.springboot.backend.chat.entity.CodigoActivacion;
import com.bolsadeideas.springboot.backend.chat.entity.Socio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICodigoActivacionDao extends MongoRepository<CodigoActivacion, Integer> {

    CodigoActivacion findByCodigo(String codigo);

    CodigoActivacion findBySocio(Socio socio);
}
