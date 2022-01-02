package com.rafael.estudos.springboot.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.rafael.estudos.springboot.domain.Cliente;
import com.rafael.estudos.springboot.dto.ClienteDTO;
import com.rafael.estudos.springboot.exception.FieldMessage;
import com.rafael.estudos.springboot.repository.ClienteRepository;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private HttpServletRequest request;

	@Override
	public void initialize(ClienteUpdate constraintAnnotation) {
	}

	@Override
	public boolean isValid(ClienteDTO obj, ConstraintValidatorContext context) {

		List<FieldMessage> messages = new ArrayList<>();

		if (!permiteAlterarEmailNaoExistente(obj)) {
			messages.add(new FieldMessage("email", "Email já cadastrado"));
		}
		for (FieldMessage f : messages) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(f.getMessage()).addPropertyNode(f.getFieldName())
					.addConstraintViolation();

		}
		return messages.isEmpty();
	}

	private boolean permiteAlterarEmailNaoExistente(ClienteDTO obj) {
		var idDaChamadaHttp = obterIdDaChamadaHttp();

		var cliente = repository.findByEmail(obj.getEmail());

		return ehMesmoIdParaPermitirAlterarEmail(cliente, idDaChamadaHttp);
	}

	private int obterIdDaChamadaHttp() {
		@SuppressWarnings("unchecked")
		Map<String, String> attribute = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		return Integer.parseInt(attribute.get("id"));
	}

	private boolean ehMesmoIdParaPermitirAlterarEmail(Cliente clienteExiste, int uriId) {
		return clienteExiste != null && !clienteExiste.getId().equals(uriId);
	}

}
