package com.bolsadeideas.springboot.backend.chat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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


    private static final long serialVersionUID = 1L;

}
