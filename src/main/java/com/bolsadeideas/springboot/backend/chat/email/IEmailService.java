package com.bolsadeideas.springboot.backend.chat.email;

import com.bolsadeideas.springboot.backend.chat.codigoActivacion.CodigoActivacion;

public interface IEmailService {
    void enviarEmail(CodigoActivacion codigoActivacion);

}
