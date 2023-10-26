package ada.santander.coders.mod05.ecommerce02.repositories;

import ada.santander.coders.mod05.ecommerce02.entities.Cliente;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNomeCompleto(String nome);

    List<Cliente> findByNomeCompletoLike(String nome);

    Cliente findByDataNascimentoBetween(LocalDate data1, LocalDate data2);

    @Query("SELECT c FROM Cliente c WHERE c.nomeCompleto ILIKE concat('%', :nome, '%') ORDER BY c.nomeCompleto")
    List<Cliente> findByNomeCompletoCustom(@Param("nome") String nome);

    @Query(value = "SELECT * FROM cliente ORDER BY NOME_COMPLETO", nativeQuery = true)
    List<Cliente> findByCustom();

    @Query(value = "SELECT * FROM cliente ORDER BY NOME_COMPLETO", nativeQuery = true)
    Page<Cliente> findByCustom(Pageable pageable);

    @Modifying
    @Query("UPDATE Cliente c SET c.ativo = :ativo WHERE c.id = :id")
    void ativarUsuario(@Param("ativo") boolean ativo, @Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE cliente SET ativo = :ativo WHERE id = :id", nativeQuery = true)
    void ativarUsuario2(@Param("ativo") boolean ativo, @Param("id") Long id);

    List<Cliente> findAllByAtivo(boolean ativo);

    @Query("SELECT c FROM Cliente c WHERE c.ativo = true")
    List<Cliente> findAllAtivos();

    Optional<Cliente> findByEmail(String email);
}
