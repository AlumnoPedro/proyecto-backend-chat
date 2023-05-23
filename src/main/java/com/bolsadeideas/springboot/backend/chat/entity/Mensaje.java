package com.bolsadeideas.springboot.backend.chat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection="mensajes")
public class Mensaje implements Serializable {
    @Id
    private String id;
    private String texto;
    private LocalDateTime fecha;
    private Socio socio;
    private Socio receptor;

    private static final long serialVersionUID = 1L;

}
