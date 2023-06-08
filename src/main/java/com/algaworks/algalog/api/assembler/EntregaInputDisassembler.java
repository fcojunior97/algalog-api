package com.algaworks.algalog.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.api.model.input.EntregaInput;
import com.algaworks.algalog.domain.model.Entrega;

@Component
public class EntregaInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Entrega toDomainObject(EntregaInput entregaInput) {
		return modelMapper.map(entregaInput, Entrega.class);
	}
	
	public void copyToDomainObject(EntregaInput entregaInput, Entrega entrega) {
		modelMapper.map(entregaInput, entrega);
	}

}
