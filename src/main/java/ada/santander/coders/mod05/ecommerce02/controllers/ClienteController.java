package ada.santander.coders.mod05.ecommerce02.controllers;

import ada.santander.coders.mod05.ecommerce02.controllers.contract.ControllerContract;
import ada.santander.coders.mod05.ecommerce02.dtos.ClienteEnderecoDto;
import ada.santander.coders.mod05.ecommerce02.dtos.ClienteRequestPostDto;
import ada.santander.coders.mod05.ecommerce02.dtos.ClienteRequestPutDto;
import ada.santander.coders.mod05.ecommerce02.dtos.ClienteResponseDto;
import ada.santander.coders.mod05.ecommerce02.services.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("cliente")
@Tag(name = "Cliente")
public class ClienteController implements ControllerContract<
        ClienteResponseDto,
        ClienteRequestPostDto,
        ClienteRequestPutDto
        > {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public ResponseEntity<ClienteResponseDto> salvar(ClienteRequestPostDto novoCliente) {
        try {
            ClienteResponseDto savedCliente = this.clienteService.salvar(novoCliente);

            if (savedCliente != null) {
                return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception exception) {
            // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getLocalizedMessage());
            // throw new NotFounException("Não existe nada!");
        }
    }

    @Override
    public ResponseEntity<List<ClienteResponseDto>> listar() {
        return new ResponseEntity<>(this.clienteService.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("/nome")
    public ResponseEntity<List<ClienteResponseDto>> getClienteByNome(
            @RequestParam("nome") String nome
    ) {
        return new ResponseEntity<>(
                this.clienteService.buscarPorNome(nome),
                HttpStatus.OK
        );
    }


    @GetMapping("/ativos")
    public ResponseEntity<List<ClienteResponseDto>> getClientesAtivos() {
        return new ResponseEntity<>(
                this.clienteService.buscarClientesAtivos(),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<ClienteResponseDto> obterUm(Long idCliente) {
        return new ResponseEntity<>(this.clienteService.buscarPorId(idCliente), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClienteResponseDto> atualizar(Long idCliente, ClienteRequestPutDto clienteParaAtualizar) {
        try {
            ClienteResponseDto savedCliente = this.clienteService.atualizar(idCliente, clienteParaAtualizar);

            if (savedCliente != null) {
                return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception exception) {
            // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getLocalizedMessage());
            // throw new NotFounException("Não existe nada!");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> ativarDesativarCliente(
            @PathVariable("id") Long id,
            @RequestParam("ativo") boolean ativo
    ) {
        this.clienteService.ativarDesativarCliente(ativo, id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public void excluir(Long idCliente) {
        this.clienteService.excluir(idCliente);
    }

    @PutMapping("/endereco")
    public ResponseEntity<String> adicionarEndereco(
            @RequestBody @Valid ClienteEnderecoDto clienteEndereco
    ) {
        this.clienteService.adicionarEndereco(clienteEndereco);

        return new ResponseEntity<>(
                "Endereco adicionado com sucesso",
                HttpStatus.OK
        );
    }

    @PatchMapping("/senha/{id}")
    public ResponseEntity<String> alterarSenha(
            @PathVariable("id") Long id,
            @RequestBody HashMap<String, String> senhas
    ) {
        try {
            return this.clienteService.alterarSenha(id, senhas);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
