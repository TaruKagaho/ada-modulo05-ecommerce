package ada.santander.coders.mod05.ecommerce02.services;

import ada.santander.coders.mod05.ecommerce02.entities.Compra;
import ada.santander.coders.mod05.ecommerce02.entities.ItemProduto;
import ada.santander.coders.mod05.ecommerce02.repositories.CompraRepository;
import ada.santander.coders.mod05.ecommerce02.repositories.ItemProdutoRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final ItemProdutoRepository itemProdutoRepository;

    public CompraService(
            CompraRepository compraRepository,
            ItemProdutoRepository itemProdutoRepository
    ) {
        this.compraRepository = compraRepository;
        this.itemProdutoRepository = itemProdutoRepository;
    }

    public Compra buscarCompra(Long id) {
        Optional<Compra> compra = compraRepository.findById(id);

        return compra.orElseThrow(() -> new RuntimeException("Compra nao encontrada"));
    }

    public void salvarCompra(Compra compra, ItemProduto itemProduto) {
        /*ItemProduto itemProduto = new ItemProduto();
        compra.getItens().add(itemProduto);*/
        Compra savedCompra = compraRepository.save(compra);

        itemProduto.setCompra(savedCompra);
        itemProdutoRepository.save(itemProduto);
    }
}
