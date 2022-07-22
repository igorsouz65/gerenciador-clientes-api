package igor.gerenciadorclientes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import igor.gerenciadorclientes.model.Cliente;
import igor.gerenciadorclientes.service.ClienteService;

@RestController
@RequestMapping("v1")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(path = "/clientes")
	public List<Cliente> listAll(){
		return clienteService.findAll();
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
	
	@PostMapping("/cadastrarClientes")
	public Cliente save(@RequestBody Cliente cliente) {
		return save(cliente);
	}
	
	@PutMapping("/atualizarClientes")
	public Cliente update(@RequestBody Cliente cliente) throws NotFoundException {
		return clienteService.update(cliente);
	}
	
	@PutMapping("/inativar-cliente")
	public void inativar(@RequestBody Cliente cliente) {
		clienteService.inativar(cliente);
	}
	
	@PutMapping("/ativar-cliente")
	public void ativar(@RequestBody Cliente cliente) {
		clienteService.ativar(cliente);
	}
	
	
	
	
}
