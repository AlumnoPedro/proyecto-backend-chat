package com.bolsadeideas.springboot.backend.chat.models;

import com.bolsadeideas.springboot.backend.chat.entity.Mensaje;

import java.util.List;

public interface IChatService {

    List<Mensaje> recuperarHistorial();
    Mensaje guardarMensaje(Mensaje mensaje);

}
