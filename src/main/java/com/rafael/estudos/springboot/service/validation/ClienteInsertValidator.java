package com.rafael.estudos.springboot.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.rafael.estudos.springboot.domain.enums.TipoCliente;
import com.rafael.estudos.springboot.dto.ClienteNewDTO;
import com.rafael.estudos.springboot.exception.FieldMessage;
import com.rafael.estudos.springboot.repository.ClienteRepository;
import com.rafael.estudos.springboot.utils.ValidatorCpfCnpj;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository repository;

	@Override
	public void initialize(ClienteInsert constraintAnnotation) {
	}

	@Override
	public boolean isValid(ClienteNewDTO obj, ConstraintValidatorContext context) {

		List<FieldMessage> messages = new ArrayList<>();

		if (obj.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !ValidatorCpfCnpj.isCPF(obj.getCpfOuCnpj())) {
			messages.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));

		}

		if (obj.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo())
				&& !ValidatorCpfCnpj.isCNPJ(obj.getCpfOuCnpj())) {
			messages.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));

		}

		var clienteExiste = repository.findByEmail(obj.getEmail());

		if (clienteExiste != null) {
			messages.add(new FieldMessage("email", "Email já cadastrado"));
		}
		for (FieldMessage f : messages) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(f.getMessage()).addPropertyNode(f.getFieldName())
					.addConstraintViolation();

		}
		return messages.isEmpty();
	}

}
