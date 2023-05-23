package com.bolsadeideas.springboot.backend.chat.models;

import com.bolsadeideas.springboot.backend.chat.entity.Socio;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ISocioService {
    List<Socio> findAll();
    Socio save(Socio socio);
    Socio findbyEmail(String email);
    Socio verificarCredenciales(Socio socio);

}
