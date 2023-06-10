package com.bolsadeideas.springboot.backend.chat.socio;

import com.bolsadeideas.springboot.backend.chat.socio.Socio;

import java.util.List;

public interface ISocioService {
    List<Socio> findAll();
    Socio save(Socio socio);
    Socio findbyEmail(String email);
    Socio verificarCredenciales(Socio socio);

}
