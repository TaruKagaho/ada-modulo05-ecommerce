package ada.santander.coders.mod05.ecommerce02.services;

import ada.santander.coders.mod05.ecommerce02.dtos.CepDto;
import ada.santander.coders.mod05.ecommerce02.dtos.EnderecoRequestPostDto;
import ada.santander.coders.mod05.ecommerce02.dtos.EnderecoRequestPutDto;
import ada.santander.coders.mod05.ecommerce02.dtos.EnderecoResponseDto;
import ada.santander.coders.mod05.ecommerce02.entities.Endereco;
import ada.santander.coders.mod05.ecommerce02.repositories.EnderecoRepository;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public CepDto buscaPorCep01(String cep) {
        String url = "https://viacep.com.br/ws/";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CepDto> response = restTemplate.getForEntity(url, CepDto.class);

        return response.getBody();
    }

    public CepDto buscaPorCep(String cep) {
        String url = "https://viacep.com.br/ws/";

        WebClient webClient = WebClient
                .builder()
                .baseUrl(url)
                .defaultHeader(
                        HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE
                )
                .build();

        return webClient
                .get()
                .uri(cep + "/json")
                .retrieve()
                .bodyToMono(CepDto.class)
                .block();
    }

    public EnderecoResponseDto salvarEndereco(EnderecoRequestPostDto novoEndereco) {
        CepDto cep = this.buscaPorCep(novoEndereco.cep());

        Endereco endereco = new Endereco();

        endereco.setUf(cep.uf());
        endereco.setCidade(cep.localidade());
        endereco.setBairro(cep.bairro());
        endereco.setLogradouro(cep.logradouro());
        endereco.setCep(cep.cep());
        endereco.setNumero(novoEndereco.numero());
        endereco.setComplemento(novoEndereco.complemento());

        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        return new EnderecoResponseDto(enderecoSalvo);
    }

    public EnderecoResponseDto atualizarEndereco(EnderecoRequestPutDto enderecoParaAtualizar) {
        Endereco endereco = this.enderecoRepository
                .findById(enderecoParaAtualizar.id())
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "O id do endereço é inválido!"
                        )
                );

        CepDto cep = this.buscaPorCep(enderecoParaAtualizar.cep());

        endereco.setUf(cep.uf());
        endereco.setCidade(cep.localidade());
        endereco.setBairro(cep.bairro());
        endereco.setLogradouro(cep.logradouro());
        endereco.setCep(cep.cep());
        endereco.setNumero(enderecoParaAtualizar.numero());
        endereco.setComplemento(enderecoParaAtualizar.complemento());

        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        return new EnderecoResponseDto(enderecoSalvo);
    }
}
