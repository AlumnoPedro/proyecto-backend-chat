package com.bolsadeideas.springboot.backend.chat.codigoActivacion;

import com.bolsadeideas.springboot.backend.chat.codigoActivacion.CodigoActivacion;

public interface ICodigoActivacionService {

    CodigoActivacion findByCodigo(String codigo);

    CodigoActivacion guardarCodigo(CodigoActivacion codigo);

}
