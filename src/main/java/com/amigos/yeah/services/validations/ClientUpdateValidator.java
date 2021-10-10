package com.amigos.yeah.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import com.amigos.yeah.domain.Cliente;
import com.amigos.yeah.dto.ClienteDTO;
import com.amigos.yeah.repositories.ClienteRepository;
import com.amigos.yeah.resources.exceptions.FieldMessage;


public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClienteDTO> {
    
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClientUpdate ann) {

    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriID = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
        if (aux != null && !aux.getId().equals(uriID)) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
