package com.bolsadeideas.springboot.backend.chat.codigoActivacion;

import com.bolsadeideas.springboot.backend.chat.codigoActivacion.CodigoActivacion;
import com.bolsadeideas.springboot.backend.chat.socio.Socio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICodigoActivacionDao extends MongoRepository<CodigoActivacion, Integer> {

    CodigoActivacion findByCodigo(String codigo);

    CodigoActivacion findBySocio(Socio socio);
}
