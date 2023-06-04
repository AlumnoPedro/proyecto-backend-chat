package com.bolsadeideas.springboot.backend.chat.models;

import com.bolsadeideas.springboot.backend.chat.entity.CodigoActivacion;

public interface ICodigoActivacionService {

    CodigoActivacion findByCodigo(String codigo);

    CodigoActivacion guardarCodigo(CodigoActivacion codigo);

}
