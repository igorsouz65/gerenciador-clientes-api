package igor.gerenciadorclientes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import igor.gerenciadorclientes.model.Cliente;
import igor.gerenciadorclientes.model.Cliente.Status;
import igor.gerenciadorclientes.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Page<Cliente> findAll(Pageable pageable){
		return clienteRepository.findByStatus(Status.AVAILABLE, pageable);
	}
	
	public Cliente findByIdOrThrowBadRequestException(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente not Found"));
	}

	public Optional<Cliente> findByCpfCnpj(String cpfCnpj ) throws NotFoundException {
		Optional<Cliente> cliente = Optional.of(clienteRepository.findByCpfCnpjAndStatus(cpfCnpj, Status.AVAILABLE));
		if(cliente.isPresent()) {
			return cliente;
		}else {
			throw new NotFoundException();
		}
	}
	
	public Optional<Cliente> findById(Long id) throws NotFoundException {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if(cliente.isPresent() || cliente.get().isAvailable()) {
			return cliente;
		} else {
			throw new NotFoundException();
		}
	}
	
	public Cliente save(Cliente cliente) {
		cliente.setStatus(Status.AVAILABLE);
		return clienteRepository.save(cliente);
	}
	
	public Cliente update(Cliente cliente, Long id) throws NotFoundException {
		Cliente savedCliente = findByIdOrThrowBadRequestException(id);
		if(savedCliente.isAvailable()) {
			return clienteRepository.save(cliente);
		}else {
			throw new NotFoundException();
		}
	}
	
	
	public void status(Long id) {
		Cliente cliente = findByIdOrThrowBadRequestException(id);
		if(cliente.isAvailable()) {
			cliente.setStatus(Status.NOT_AVAILABLE);
			clienteRepository.save(cliente);
		}else {
			cliente.setStatus(Status.AVAILABLE);
			clienteRepository.save(cliente);
		}
	}
	
	

	
}
