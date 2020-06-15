package br.com.curso.mc.service;

import br.com.curso.mc.entity.Pedido;
import org.springframework.mail.SimpleMailMessage;
import javax.mail.internet.MimeMessage;


public interface EmailService {
    void sendOrderConfirmationEmail(Pedido pedido);
    void sendEmail(SimpleMailMessage simpleMailMessage);
    void sendOrderConfirmationHtmlEmail(Pedido obj);
    void sendHtmlEmail(MimeMessage msg);
}
