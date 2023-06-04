package com.bolsadeideas.springboot.backend.chat.models;

import com.bolsadeideas.springboot.backend.chat.entity.CodigoActivacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodigoActivacionService implements ICodigoActivacionService {

    @Autowired
    private ICodigoActivacionDao codigoActivacionDao;
    @Override
    public CodigoActivacion findByCodigo(String codigo) {
        return codigoActivacionDao.findByCodigo(codigo);
    }

    @Override
    public CodigoActivacion guardarCodigo(CodigoActivacion codigo) {
        return codigoActivacionDao.save(codigo);
    }



}