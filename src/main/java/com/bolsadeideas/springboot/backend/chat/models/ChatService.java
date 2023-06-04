package com.bolsadeideas.springboot.backend.chat.models;

import com.bolsadeideas.springboot.backend.chat.entity.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChatService implements IChatService{

    @Autowired
    private IChatDao chatDao;

    @Override
    public List<Mensaje> recuperarHistorial() {
        return chatDao.findFirst5ByOrderByFechaDesc();
    }

    @Override
    public Mensaje guardarMensaje(Mensaje mensaje) {
        return chatDao.save(mensaje);
    }

}
