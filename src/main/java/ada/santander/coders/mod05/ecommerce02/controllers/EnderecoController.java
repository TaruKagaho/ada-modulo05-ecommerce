package ada.santander.coders.mod05.ecommerce02.controllers;

import ada.santander.coders.mod05.ecommerce02.dtos.CepDto;
import ada.santander.coders.mod05.ecommerce02.dtos.EnderecoRequestPostDto;
import ada.santander.coders.mod05.ecommerce02.dtos.EnderecoRequestPutDto;
import ada.santander.coders.mod05.ecommerce02.dtos.EnderecoResponseDto;
import ada.santander.coders.mod05.ecommerce02.services.EnderecoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("endereco")
@Tag(name = "Endereço")
public class EnderecoController {
    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("{cep}")
    public ResponseEntity<CepDto> getByCep(
            @PathVariable("cep") String cep
    ) {
        return new ResponseEntity<>(this.enderecoService.buscaPorCep(cep), HttpStatus.OK);
    }

    // @RequestMapping(value = "", method = { RequestMethod.POST, RequestMethod.PUT })
    @PostMapping
    public ResponseEntity<EnderecoResponseDto> salvarEndereco(
            @RequestBody EnderecoRequestPostDto endereco
    ) {
        try {
            EnderecoResponseDto savedEndereco = this.enderecoService.salvarEndereco(endereco);

            if (savedEndereco != null) {
                return new ResponseEntity<>(savedEndereco, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<EnderecoResponseDto> atualizarEndereco(
            @RequestBody EnderecoRequestPutDto endereco
    ) {
        try {
            EnderecoResponseDto savedEndereco = this.enderecoService.atualizarEndereco(endereco);

            if (savedEndereco != null) {
                return new ResponseEntity<>(savedEndereco, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
