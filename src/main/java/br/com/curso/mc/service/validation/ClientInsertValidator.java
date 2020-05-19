package br.com.curso.mc.service.validation;

import br.com.curso.mc.dto.ClienteNewDTO;
import br.com.curso.mc.entity.enums.TipoCliente;
import br.com.curso.mc.resource.HandlerException.FieldMessage;
import br.com.curso.mc.service.validation.utils.ValidationCpfCnpj;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClienteNewDTO> {
    @Override
    public void initialize(ClientInsert clientInsert) {
    }
    @Override
    public boolean isValid(ClienteNewDTO clienteNewDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        // inclua os testes aqui, inserindo erros na lista
        if(clienteNewDTO.getTipoCliente().equals(TipoCliente.PESSOAFISICA) && !ValidationCpfCnpj.isValidCpf(clienteNewDTO.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj","Cpf inválido"));
        }

        if(clienteNewDTO.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA) && !ValidationCpfCnpj.isValidCnpj(clienteNewDTO.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj","Cnpj inválido"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
        }

    }
