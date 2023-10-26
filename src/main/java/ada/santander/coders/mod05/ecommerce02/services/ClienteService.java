package ada.santander.coders.mod05.ecommerce02.services;

import ada.santander.coders.mod05.ecommerce02.dtos.ClienteEnderecoDto;
import ada.santander.coders.mod05.ecommerce02.dtos.ClienteRequestPostDto;
import ada.santander.coders.mod05.ecommerce02.dtos.ClienteRequestPutDto;
import ada.santander.coders.mod05.ecommerce02.dtos.ClienteResponseDto;
import ada.santander.coders.mod05.ecommerce02.entities.Cliente;
import ada.santander.coders.mod05.ecommerce02.entities.ClienteEndereco;
import ada.santander.coders.mod05.ecommerce02.entities.Endereco;
import ada.santander.coders.mod05.ecommerce02.repositories.ClienteEnderecoRepository;
import ada.santander.coders.mod05.ecommerce02.repositories.ClienteRepository;
import ada.santander.coders.mod05.ecommerce02.repositories.EnderecoRepository;
import ada.santander.coders.mod05.ecommerce02.services.contract.ServiceContract;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
// import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements ServiceContract<ClienteResponseDto, ClienteRequestPostDto, ClienteRequestPutDto> {
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final ClienteEnderecoRepository clienteEnderecoRepository;
    // private final PasswordEncoder passwordEncoder;

    public ClienteService(
            ClienteRepository clienteRepository,
            EnderecoRepository enderecoRepository,
            ClienteEnderecoRepository clienteEnderecoRepository/*,
            PasswordEncoder passwordEncoder*/
    ) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.clienteEnderecoRepository = clienteEnderecoRepository;
        // this.passwordEncoder = passwordEncoder;
    }

    // private ClienteRequestPostDto criarClienteDto(Cliente cliente) {}
    private Cliente obterCliente(Long id) {
        return this.clienteRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Não existe cliente com esse id"
                        )
                );
    }

    @Override
    public ClienteResponseDto salvar(ClienteRequestPostDto novoCliente) {
        Cliente cliente = new Cliente();

        BeanUtils.copyProperties(novoCliente, cliente);

        Cliente clienteSalvo = this.clienteRepository.save(cliente);

        return new ClienteResponseDto(clienteSalvo);
    }

    @Override
    public List<ClienteResponseDto> buscarTodos() {
        return this.clienteRepository
                .findAll()
                .stream()
                .map(ClienteResponseDto::new)
                .toList();
    }

    public List<ClienteResponseDto> buscarPorNome(String nome) {
        return this.clienteRepository
                .findByNomeCompletoCustom(nome)
                .stream()
                .map(ClienteResponseDto::new)
                .toList();
    }

    public List<ClienteResponseDto> buscarClientesAtivos() {
        return this.clienteRepository
                .findAllByAtivo(true)
                .stream()
                .map(ClienteResponseDto::new)
                .toList();
    }

    @Override
    public ClienteResponseDto buscarPorId(Long id) {
        Cliente clienteEncontrado = this.obterCliente(id);

        return new ClienteResponseDto(clienteEncontrado);
    }

    public void ativarDesativarCliente(boolean ativo, Long id) {
        this.clienteRepository.ativarUsuario(ativo, id);
    }

    @Override
    public ClienteResponseDto atualizar(Long id, ClienteRequestPutDto clienteParaAtualizar) {
        Cliente clienteEncontrado = this.obterCliente(id);
        /*
         * Pode atualizar:
         * email ?????
         * nomeCompleto
         * dataNascimento
         * */
        // clienteEncontrado.setEmail(clienteParaAtualizar.email());
        clienteEncontrado.setNomeCompleto(clienteParaAtualizar.nomeCompleto());
        clienteEncontrado.setDataNascimento(clienteParaAtualizar.dataNascimento());

        Cliente clienteSalvo = this.clienteRepository.save(clienteEncontrado);

        return new ClienteResponseDto(clienteSalvo);
    }

    @Override
    public void excluir(Long id) {
        Cliente clienteEncontrado = this.obterCliente(id);

        this.clienteRepository.delete(clienteEncontrado);
    }

    public void adicionarEndereco(ClienteEnderecoDto clienteEnderecoDTO) {
        Cliente clienteEncontrado = this.obterCliente(clienteEnderecoDTO.clienteId());

        Endereco endereco = this.enderecoRepository
                .findById(
                        clienteEnderecoDTO.enderecoId()
                )
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Não existe endereco com esse id"
                        )
                );

        ClienteEndereco clienteEndereco = new ClienteEndereco();

        clienteEndereco.setEndereco(endereco);
        clienteEndereco.setCliente(clienteEncontrado);
        clienteEndereco.setTipo(clienteEnderecoDTO.tipo());
        clienteEndereco.setNomeRecebedor(clienteEnderecoDTO.nomeRecebedor());

        this.clienteEnderecoRepository.save(clienteEndereco);
    }

    public ResponseEntity<String> alterarSenha(
            Long id,
            HashMap<String, String> senhas
    ) {
        String senhaAtual = senhas.get("atual");
        String novaSenha = senhas.get("nova");
        String confirmarSenha = senhas.get("confirmar");

        Cliente clienteEncontrado = this.obterCliente(id);

        // if (passwordEncoder.matches(senhaAtual, clienteEncontrado.getSenha())) {
        if (senhaAtual.equals(clienteEncontrado.getSenha())) {
            if (novaSenha.equals(confirmarSenha)) {
                // clienteEncontrado.setSenha(passwordEncoder.encode(novaSenha));
                clienteEncontrado.setSenha(novaSenha);

                this.clienteRepository.save(clienteEncontrado);

                return new ResponseEntity<>(
                        "Senha atualizada com sucesso!",
                        HttpStatus.OK
                );
            } else {
                return new ResponseEntity<>(
                        "Nova senha e confirmar senha não são idênticas!",
                        HttpStatus.UNPROCESSABLE_ENTITY
                );
            }
        } else {
            return new ResponseEntity<>(
                    "Senha atual inválida!",
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
