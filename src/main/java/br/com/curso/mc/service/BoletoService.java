package br.com.curso.mc.service;

import br.com.curso.mc.entity.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
@Service
public class BoletoService {
    public void preencherPagamentoComBoleto(PagamentoComBoleto pagamentoComBoleto, Date instante) {
        Calendar date = Calendar.getInstance();
        date.setTime(instante);
        date.add(Calendar.DAY_OF_MONTH,7);
        pagamentoComBoleto.setDataVencimento(date.getTime());
    }
}
