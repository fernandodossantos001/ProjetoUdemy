package br.com.curso.mc.service.validation;

import br.com.curso.mc.dto.ClienteDTO;
import br.com.curso.mc.entity.Cliente;
import br.com.curso.mc.resource.HandlerException.FieldMessage;
import br.com.curso.mc.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClienteDTO> {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteService service;

    @Override
    public void initialize(ClientUpdate clientUpdate) {
    }
    @Override
    public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        Map<String,String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer idClient = Integer.parseInt(map.get("id"));

        // inclua os testes aqui, inserindo erros na lista

        Cliente client = service.findByEmail(clienteDTO.getEmail());
        if(null != client && client.getId() != idClient){
            list.add(new FieldMessage("templates/email","Já existe um cliente com esse email"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
        }

    }
