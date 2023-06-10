package com.bolsadeideas.springboot.backend.chat.socio;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Document(collection="socios")
public class Socio implements Serializable {

    @Id
    private Long id;
    private String email;
    private LocalDate fechaAlta;
    private String nombre;
    private String apellido;
    private String password;
    private Boolean admin;
    private Boolean activo;


    private static final long serialVersionUID = 1L;

}
