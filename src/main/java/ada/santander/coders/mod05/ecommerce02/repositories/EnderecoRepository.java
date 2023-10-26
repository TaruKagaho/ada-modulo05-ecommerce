package ada.santander.coders.mod05.ecommerce02.repositories;

import ada.santander.coders.mod05.ecommerce02.entities.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
