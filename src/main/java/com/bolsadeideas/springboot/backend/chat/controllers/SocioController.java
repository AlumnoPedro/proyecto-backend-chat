package com.bolsadeideas.springboot.backend.chat.controllers;

import com.bolsadeideas.springboot.backend.chat.codigoActivacion.CodigoActivacion;
import com.bolsadeideas.springboot.backend.chat.socio.Socio;
import com.bolsadeideas.springboot.backend.chat.codigoActivacion.ICodigoActivacionService;
import com.bolsadeideas.springboot.backend.chat.email.IEmailService;
import com.bolsadeideas.springboot.backend.chat.socio.ISocioService;
import com.bolsadeideas.springboot.backend.chat.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SocioController {

    @Autowired
    private ISocioService socioService;
    @Autowired
    private ICodigoActivacionService codigoService;
    @Autowired
    private IEmailService emailService;
    @Autowired
    private JWTUtil jwtUtil;


    @GetMapping("/socios")
    public List<Socio> index() {
        return socioService.findAll();
    }

    @PostMapping("/registro")
    public ResponseEntity<?> create(@Valid @RequestBody Socio socio, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if(socioService.findbyEmail(socio.getEmail()) == null) {
            try {
                //encripta la contraseña del socio y lo guarda
                Argon2 argon2 = Argon2Factory.create();
                String hash = argon2.hash(1, 1024, 1, socio.getPassword().toCharArray());
                socio.setPassword(hash);
                socio.setFechaAlta(LocalDate.now());
                socio.setAdmin(false);
                socio.setActivo(false);
                CodigoActivacion codigoActivacion = new CodigoActivacion();
                codigoActivacion.setSocio(socio);
                codigoActivacion.setId(LocalDateTime.now().getNano());
                codigoActivacion.setCodigo("codigodeejemplo");
                codigoActivacion.setFechaCreacion(LocalDate.now());
                emailService.enviarEmail(codigoActivacion);
                codigoService.guardarCodigo(codigoActivacion);
                socioService.save(socio);
            } catch (DataAccessException e) {
                response.put("mensaje", "Error al realizar la insercion a base de datos");
                response.put("error", Objects.requireNonNull(e.getMessage()).concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.put("mensaje", "El socio ha sido creado con exito!!");
            response.put("socio", socio);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        } else {
            response.put("mensaje","El socio ya existe");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/verificacion")
    public String activarSocio(@RequestParam("codigo") String codigo){
        System.out.println(codigo);
        CodigoActivacion codigoActivacion = codigoService.findByCodigo(codigo);
        if(codigoActivacion == null){
            return "Codigo de Activacion no valido";
        } else {
            System.out.println(codigoActivacion.getSocio());
            codigoActivacion.getSocio().setActivo(true);
            System.out.println(codigoActivacion.getSocio());
            socioService.save(codigoActivacion.getSocio());
            return "Cuenta Activada";
        }
    }

}
