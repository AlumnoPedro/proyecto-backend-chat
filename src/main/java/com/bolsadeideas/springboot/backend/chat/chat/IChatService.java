package com.bolsadeideas.springboot.backend.chat.chat;

import com.bolsadeideas.springboot.backend.chat.chat.Mensaje;

import java.util.List;

public interface IChatService {

    List<Mensaje> recuperarHistorial();
    Mensaje guardarMensaje(Mensaje mensaje);

}
