package com.algaworks.algalog.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.assembler.ClienteAssembler;
import com.algaworks.algalog.api.assembler.ClienteInputDisassembler;
import com.algaworks.algalog.api.model.ClienteModel;
import com.algaworks.algalog.api.model.input.ClienteInput;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalogoClienteService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/clientes")
public class ClientesController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CatalogoClienteService catalogoClienteService;
	
	@Autowired
	private ClienteAssembler clienteAssembler;
	
	@Autowired
	private ClienteInputDisassembler clienteInputDisassembler;
	
	@GetMapping
	public List<ClienteModel> listar() {
		
		List<Cliente> clientes = clienteRepository.findAll();
		return clienteAssembler.toCollectionModel(clientes);
	}
	
	@GetMapping("/buscaPorNome")
	public List<ClienteModel> buscarPorNome(@RequestParam String nome) {
		List<Cliente> clientes = clienteRepository.findByNomeContaining(nome);
		return clienteAssembler.toCollectionModel(clientes);
	}
	
	@GetMapping("/{idCliente}")
	public ResponseEntity<ClienteModel> buscarPorId(@PathVariable Long idCliente) {
		Optional<Cliente> cliente =  clienteRepository.findById(idCliente);
		
		if(cliente.isPresent()) {
			return ResponseEntity.ok(clienteAssembler.toModel(cliente.get()));
		}
		
		return ResponseEntity.notFound().build();	
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteModel adicionar(@Valid @RequestBody ClienteInput clienteInput) {
		
		Cliente cliente = clienteInputDisassembler.toDomainObject(clienteInput);
		Cliente clienteSalvo = catalogoClienteService.salvar(cliente);
		return clienteAssembler.toModel(clienteSalvo);
	}
	
	// TODO Renomear a assinatura do método e refatorar
	@PutMapping("/{idCliente}")
	public ResponseEntity<ClienteModel> buscarPorId(@PathVariable Long idCliente, @Valid @RequestBody ClienteInput clienteInput) {
		
		if(!clienteRepository.existsById(idCliente)) {
			return ResponseEntity.notFound().build();
		}
		
		Cliente cliente = clienteInputDisassembler.toDomainObject(clienteInput);
		cliente.setId(idCliente);
		cliente = catalogoClienteService.salvar(cliente);
		ClienteModel clienteModel = clienteAssembler.toModel(cliente);
		
		return ResponseEntity.ok(clienteModel);
				
	}
	
	@DeleteMapping("/{idCliente}")
	public ResponseEntity<Void> excluir(@PathVariable Long idCliente) {
		
		if(!clienteRepository.existsById(idCliente)) {
			 return ResponseEntity.notFound().build();
		}
		
		catalogoClienteService.excluir(idCliente);
		return ResponseEntity.noContent().build();
				
	}
	
	
	
}
