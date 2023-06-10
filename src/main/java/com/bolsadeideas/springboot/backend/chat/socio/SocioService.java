package com.bolsadeideas.springboot.backend.chat.socio;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SocioService implements ISocioService {
    @Autowired
    private ISocioDao SocioDao;

    @Override
    @Transactional(readOnly = true)
    public List<Socio> findAll() {
        return (List<Socio>) SocioDao.findAll();
    }

    @Override
    @Transactional
    public Socio save(Socio socio) {
        return SocioDao.save(socio);
    }

    @Override
    public Socio findbyEmail(String email) {
        List<Socio> socios = SocioDao.findbyEmail(email);
        if(!socios.isEmpty()){
            return socios.get(0);
        }
        return null;
    }

    @Override
    public Socio verificarCredenciales(Socio socio) {
        List<Socio> lista = SocioDao.findbyEmail(socio.getEmail());
        if(lista.isEmpty()){
            System.out.println("Lista vacia");
            return null;
        }
        String passwordHashed = lista.get(0).getPassword();
        System.out.println(passwordHashed);
        System.out.println(lista);
        System.out.println(socio.getPassword());
        Argon2 argon2 = Argon2Factory.create();

        /*String hash = argon2.hash(1, 1024, 1, socio.getPassword().toCharArray());
        if(hash.equals(passwordHashed)){
            return lista.get(0);
        }*/

        if(argon2.verify(passwordHashed, socio.getPassword().toCharArray())){
            System.out.println("Contraseña correcta. Acceso al sistema");
            return lista.get(0);
        }

        System.out.println("Contraseña incorrecta");
        return null;

    }

}
