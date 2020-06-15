package br.com.curso.mc.service;

import br.com.curso.mc.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Currency;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService{
    @Value("${default.sender}")
    private String remetente;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        SimpleMailMessage simpleMailMessage = prepareSimpleMailMesseFromPedido(pedido);
        sendEmail(simpleMailMessage);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido pedido){
        MimeMessage mimeMessage = null;
        try {
            mimeMessage = prepareMimeMessageFromPedido(pedido);
            sendHtmlEmail(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            sendOrderConfirmationEmail(pedido);
        }
    }

    private   MimeMessage prepareMimeMessageFromPedido(Pedido pedido) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setTo(pedido.getCliente().getEmail());
        mimeMessageHelper.setFrom(remetente);
        mimeMessageHelper.setSubject("Pedido Confirmado: " + pedido.getId());
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(htmlFromTemplatePedido(pedido),true);
        return mimeMessage;
    }

    private  SimpleMailMessage prepareSimpleMailMesseFromPedido(Pedido pedido){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(pedido.getCliente().getEmail());
        simpleMailMessage.setFrom(remetente);
        simpleMailMessage.setSubject("Pedido Confirmado código: " + pedido.getId());
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText(pedido.toString());
        return  simpleMailMessage;
    }

    private String htmlFromTemplatePedido(Pedido obj){
        Context context = new Context();
        context.setVariable("pedido",obj);
        return templateEngine.process("email/confirmacaoPedido",context);
    }
}
