package ada.santander.coders.mod05.ecommerce02.repositories;

import ada.santander.coders.mod05.ecommerce02.entities.ClienteEndereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteEnderecoRepository extends JpaRepository<ClienteEndereco, Long> {
}
