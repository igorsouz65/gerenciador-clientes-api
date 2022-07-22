package igor.gerenciadorclientes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import igor.gerenciadorclientes.model.Cliente;
import igor.gerenciadorclientes.model.Cliente.Status;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	Cliente findByCpfCnpjAndStatus(String cpfCnpj, Status status);
	
	Page<Cliente> findByStatus(Status status, Pageable pageable);
	
	
	
	
}
