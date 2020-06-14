package br.com.curso.mc.service;

import br.com.curso.mc.entity.Pedido;
import org.springframework.mail.SimpleMailMessage;


public interface EmailService {
    void sendOrderConfirmationEmail(Pedido pedido);
    void sendEmail(SimpleMailMessage simpleMailMessage);
}
