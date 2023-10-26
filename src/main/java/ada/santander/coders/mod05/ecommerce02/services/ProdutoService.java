package ada.santander.coders.mod05.ecommerce02.services;

import ada.santander.coders.mod05.ecommerce02.entities.Produto;
import ada.santander.coders.mod05.ecommerce02.repositories.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> buscarProdutosPorPrecos(double preco1, double preco2) {
        List<Produto> produtos = produtoRepository.findByPrecoBetween(preco1, preco2);

        int page = 0;
        int total = 100;

        Pageable pageable = PageRequest.of(page, total, Sort.Direction.DESC, "preco");
        Sort sort = Sort.by(Sort.Direction.DESC);

        Page<Produto> produtos2 = produtoRepository.findAll(pageable);
        List<Produto> produtos3 = produtoRepository.findAll(sort);

        return produtos;
    }
}
