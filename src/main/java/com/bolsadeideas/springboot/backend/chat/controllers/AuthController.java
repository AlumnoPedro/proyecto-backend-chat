package com.bolsadeideas.springboot.backend.chat.controllers;

import com.bolsadeideas.springboot.backend.chat.socio.Socio;
import com.bolsadeideas.springboot.backend.chat.socio.ISocioDao;
import com.bolsadeideas.springboot.backend.chat.socio.ISocioService;
import com.bolsadeideas.springboot.backend.chat.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private ISocioService socioService;
    @Autowired
    private ISocioDao socioDao;
    @Autowired
    private JWTUtil jwtUtil;


    @RequestMapping(value="api/login", method = RequestMethod.POST)
    public String login(@RequestBody Socio socio) {
        Socio socioLogueado = socioService.verificarCredenciales(socio);
        System.out.println(socioLogueado);
        if (socioLogueado != null && socioLogueado.getActivo() == true) {

            String tokenJwt = jwtUtil.create(String.valueOf(socioLogueado.getId()), socioLogueado.getEmail(), socioLogueado.getAdmin());

            return tokenJwt;
        }
        return "fail";
    }

}
