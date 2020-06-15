package br.com.curso.mc.service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class MockEmailService extends AbstractEmailService{
    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
    @Override
    public void sendEmail(SimpleMailMessage simpleMailMessage) {
        LOG.info("Simulando envio de email");
        LOG.info(simpleMailMessage.toString());
        LOG.info("E-mail enviado");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("Simulando envio de email HTML");
        LOG.info(msg.toString());
        LOG.info("E-mail HTML enviado");
    }
}
