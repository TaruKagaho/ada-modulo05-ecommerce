package ada.santander.coders.mod05.ecommerce02.controllers.contract;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ControllerContract<T, G, R> {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    ResponseEntity<T> salvar(@RequestBody @Valid G object);

    @GetMapping
    ResponseEntity<List<T>> listar();

    @GetMapping("/{id}")
    ResponseEntity<T> obterUm(@PathVariable("id") Long idObject);

    @PutMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<T> atualizar(
            @PathVariable("id") Long idObject,
            @RequestBody @Valid R object
    );

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void excluir(@PathVariable("id") Long idObject);
}
