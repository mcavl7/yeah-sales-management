package com.amigos.yeah.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.amigos.yeah.domain.Cliente;
import com.amigos.yeah.domain.enums.TipoCliente;
import com.amigos.yeah.dto.ClienteNewDTO;
import com.amigos.yeah.repositories.ClienteRepository;
import com.amigos.yeah.resources.exceptions.FieldMessage;
import com.amigos.yeah.services.validations.utils.BR;

import org.springframework.beans.factory.annotation.Autowired;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClienteNewDTO> {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClientInsert ann) {

    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
        }

        if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
        }

        Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
        if (aux != null) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
