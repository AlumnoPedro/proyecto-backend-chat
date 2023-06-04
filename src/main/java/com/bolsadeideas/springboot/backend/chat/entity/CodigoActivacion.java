package com.bolsadeideas.springboot.backend.chat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Document(collection="codigoActivacion")
public class CodigoActivacion implements Serializable {

    @Id
    private int id;
    private String codigo;
    private LocalDate fechaCreacion;
    private Socio socio;

    private static final long serialVersionUID = 1L;

}
