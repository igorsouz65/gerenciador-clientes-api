package igor.gerenciadorclientes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import igor.gerenciadorclientes.model.Cliente;
import igor.gerenciadorclientes.model.Cliente.Status;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	Cliente findByCpfCnpjAndStatus(String cpfCnpj, Status status);
	
	List<Cliente> findByStatus(Status status);
	
	
	
	
}
