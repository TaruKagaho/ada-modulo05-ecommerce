package ada.santander.coders.mod05.ecommerce02.repositories;

import ada.santander.coders.mod05.ecommerce02.entities.Compra;
import ada.santander.coders.mod05.ecommerce02.entities.enums.CompraStatusEnum;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CompraRepository extends JpaRepository<Compra, Long> {
    @Modifying
    @Query("UPDATE Compra c SET c.status = :status")
    void atualizarStatus(@Param("status") CompraStatusEnum status);

    @Query(value = "UPDATE compra SET status = :status", nativeQuery = true)
    void atualizarStatus01(@Param("status") String status);

    @Query("SELECT c FROM Compra c INNER JOIN FETCH c.itens WHERE c.id = :id")
    Compra buscarCompraPorIdComProdutos(@Param("id") Long id);
}
