package com.bolsadeideas.springboot.backend.chat.models;

import com.bolsadeideas.springboot.backend.chat.entity.CodigoActivacion;

public interface IEmailService {
    void enviarEmail(CodigoActivacion codigoActivacion);

}
