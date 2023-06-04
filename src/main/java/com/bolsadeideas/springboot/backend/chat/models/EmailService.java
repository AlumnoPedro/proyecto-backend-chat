package com.bolsadeideas.springboot.backend.chat.models;

import com.bolsadeideas.springboot.backend.chat.entity.CodigoActivacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService{

    @Autowired
    private ICodigoActivacionService codigoActivacionService;

    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void enviarEmail(CodigoActivacion codigoActivacion) {
        if(codigoActivacion != null){
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setFrom("username");
                mail.setTo(codigoActivacion.getSocio().getEmail());
                mail.setSubject("Codigo de Verificacion Chat");
                mail.setText("http://localhost:8080/api/verificacion?codigo=" + codigoActivacion.getCodigo());
                mailSender.send(mail);
        }
    }



}
