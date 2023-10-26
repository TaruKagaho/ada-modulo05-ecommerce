package ada.santander.coders.mod05.ecommerce02.repositories;

import ada.santander.coders.mod05.ecommerce02.entities.ItemProduto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemProdutoRepository extends JpaRepository<ItemProduto, Long> {
}
