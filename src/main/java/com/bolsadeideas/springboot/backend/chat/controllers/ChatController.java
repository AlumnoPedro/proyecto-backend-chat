package com.bolsadeideas.springboot.backend.chat.controllers;

import com.bolsadeideas.springboot.backend.chat.entity.Mensaje;
import com.bolsadeideas.springboot.backend.chat.entity.Socio;
import com.bolsadeideas.springboot.backend.chat.models.IChatService;
import com.bolsadeideas.springboot.backend.chat.models.ISocioService;
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

    @MessageMapping("/mensaje")
    @SendTo("/chat/public")
    public Mensaje recibeMensaje(Mensaje mensaje){
        Socio socioMensaje = socioService.findbyEmail(mensaje.getSocio().getEmail());
        mensaje.setFecha(LocalDateTime.now());
        mensaje.setSocio(socioMensaje);
        System.out.println(mensaje);
        chatService.guardar(mensaje);
        return mensaje;
    }

    @MessageMapping("/mensaje-privado")
    public Mensaje privMensaje(Mensaje mensaje){
        Socio socioMensaje = socioService.findbyEmail(mensaje.getSocio().getEmail());
        mensaje.setFecha(LocalDateTime.now());
        mensaje.setSocio(socioMensaje);
        Socio socioReceptor = socioService.findbyEmail(mensaje.getReceptor().getEmail());
        mensaje.setReceptor(socioReceptor);
        simpMessagingTemplate.convertAndSend("/chat/"+mensaje.getReceptor().getEmail()+"/private",mensaje);

        return mensaje;
    }

    @MessageMapping("/escribiendo")
    @SendTo("/chat/escribiendo")
    public Socio estaEscribiendo(Socio socio){
        Socio socioMensaje = socioService.findbyEmail(socio.getEmail());
        return socioMensaje;
    }

    @MessageMapping("/historial")
    public void historial(String email) {
        simpMessagingTemplate.convertAndSend("/chat/historial/" + email, chatService.obtenerUltimos10Mensajes());
    }

}
