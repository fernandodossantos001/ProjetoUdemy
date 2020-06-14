package br.com.curso.mc.config;

import br.com.curso.mc.PopulaBancoService;
import br.com.curso.mc.service.EmailService;
import br.com.curso.mc.service.MockEmailService;
import br.com.curso.mc.service.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class ProfileConfigDev {

    @Autowired
    private PopulaBancoService populaBancoService;
    @Bean
    public Boolean instantiateDataBase() throws ParseException {
//        populaBancoService.createCategoriaAndProduto();
//        populaBancoService.createEsdadoAndCidade();
//        populaBancoService.createClienteAndEndereco();
//        populaBancoService.createPedido();
//        populaBancoService.createItemPedido();
        return Boolean.TRUE;
    }

    @Bean
    public SmtpEmailService emailService(){
        return new SmtpEmailService();
    }
}
