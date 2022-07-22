package igor.gerenciadorclientes.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import igor.gerenciadorclientes.model.Cliente;
import igor.gerenciadorclientes.service.ClienteService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(path = "/clientes")
	public Page<Cliente> listAll(Pageable pageable){
		return clienteService.findAll(pageable);
	}
	
	@GetMapping("/inativos/{id}")
	public Cliente findByIdAndNotAvailables(@PathVariable Long id) {
		return clienteService.findByIdOrThrowBadRequestException(id);
	}
	
	@GetMapping("/{id}")
	Optional<Cliente> findById(@PathVariable Long id) throws NotFoundException {
		return clienteService.findById(id);
	}
	
	@GetMapping("/clientes/{cpfCnpj}")
	public Optional<Cliente> findByCpfCnpj(@PathVariable String cpfCnpj) throws NotFoundException {
		return clienteService.findByCpfCnpj(cpfCnpj);
	}
	
	@PostMapping("/clientes")
	public Cliente save(@RequestBody Cliente cliente) {
		return clienteService.save(cliente);
	}
	
	@PutMapping("/atualizarClientes/{id}")
	public Cliente update(@RequestBody Cliente cliente, @PathVariable Long id) throws NotFoundException {
		return clienteService.update(cliente, id);
	}
	
	
	@PutMapping("/statusCliente/{id}")
	public void ativar( @PathVariable Long id) {
		clienteService.status(id);
	}
	
	
	
	
	
}
