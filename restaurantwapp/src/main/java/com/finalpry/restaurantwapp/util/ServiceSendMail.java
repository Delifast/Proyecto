package com.finalpry.restaurantwapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.finalpry.restaurantwapp.model.Cliente;

@Service
public class ServiceSendMail {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Async
    public void enviarCorreo(Cliente cliente){
            System.out.println("enviando correo.. ");
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            //mailMessage.setFrom("pruebasdev.yachi");
            mailMessage.setTo(cliente.getCorreo());
            mailMessage.setSubject("Recomendación de Página Web");
            mailMessage.setText("Según los datos ingresados en el formulario, se le recomienda la siguiente URL:"
            		+ "http://localhost:8090/restaurant");

            mailSender.send(mailMessage);
            System.out.println("correo enviado");
	}
}
