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
    public List<Mensaje> obtenerUltimos10Mensajes() {
        return chatDao.findFirst1ByOrderByFechaDesc();
    }

    @Override
    public Mensaje guardar(Mensaje mensaje) {
        return chatDao.save(mensaje);
    }

}
