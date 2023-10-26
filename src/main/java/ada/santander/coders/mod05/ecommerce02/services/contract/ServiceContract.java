package ada.santander.coders.mod05.ecommerce02.services.contract;

import java.util.List;
/*
* T - tipo de resposta dos métodos
* G - Tipo de parâmetro do salvar
* R - Tipo de parâmetro do atualizar
* */
public interface ServiceContract<T, G, R> {
    T salvar(G object);

    List<T> buscarTodos();

    T buscarPorId(Long id);

    T atualizar(Long id, R object);

    void excluir(Long id);
}
