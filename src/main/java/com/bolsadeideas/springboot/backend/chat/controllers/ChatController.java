package com.bolsadeideas.springboot.backend.chat.controllers;

import com.bolsadeideas.springboot.backend.chat.chat.Mensaje;
import com.bolsadeideas.springboot.backend.chat.socio.Socio;
import com.bolsadeideas.springboot.backend.chat.chat.IChatService;
import com.bolsadeideas.springboot.backend.chat.socio.ISocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;

@Controller
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private IChatService chatService;

    @Autowired
    private ISocioService socioService;

    @MessageMapping("/mensaje-publico")
    @SendTo("/chat/publico")
    public Mensaje recibeMensaje(Mensaje mensaje){
        //Rellenar informacion del socio emisor
        Socio socioMensaje = socioService.findbyEmail(mensaje.getSocio().getEmail());
        mensaje.setFecha(LocalDateTime.now());
        mensaje.setSocio(socioMensaje);
        //Guardar mensaje en MongoDB
        chatService.guardarMensaje(mensaje);
        return mensaje;
    }

    @MessageMapping("/mensaje-privado")
    public Mensaje privMensaje(Mensaje mensaje){
        //Rellenar informacion del socio emisor
        Socio socioMensaje = socioService.findbyEmail(mensaje.getSocio().getEmail());
        mensaje.setFecha(LocalDateTime.now());
        mensaje.setSocio(socioMensaje);
        //Rellenar informacion del socio receptor
        Socio socioReceptor = socioService.findbyEmail(mensaje.getReceptor().getEmail());
        mensaje.setReceptor(socioReceptor);
        //Guardar mensaje en MongoDB
        chatService.guardarMensaje(mensaje);
        //Enviar mensaje al receptor en concreto
        simpMessagingTemplate.convertAndSend("/chat/"+mensaje.getReceptor().getEmail()+"/private",mensaje);

        return mensaje;
    }

    @MessageMapping("/historial")
    public void historial(String email) {
        //Enviar lista de mensajes al socio conectado en concreto
        simpMessagingTemplate.convertAndSend("/chat/historial/" + email, chatService.recuperarHistorial());
    }

}
