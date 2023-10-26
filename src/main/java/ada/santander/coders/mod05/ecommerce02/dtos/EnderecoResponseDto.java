package ada.santander.coders.mod05.ecommerce02.dtos;

import ada.santander.coders.mod05.ecommerce02.entities.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoResponseDto(
        Long id,

        String logradouro,

        String numero,

        String complemento,

        String bairro,

        String cep,

        String cidade,

        String uf
) {
    public EnderecoResponseDto(Endereco enderecoSalvo) {
        this(
                enderecoSalvo.getId(),
                enderecoSalvo.getLogradouro(),
                enderecoSalvo.getNumero(),
                enderecoSalvo.getComplemento(),
                enderecoSalvo.getBairro(),
                enderecoSalvo.getCep(),
                enderecoSalvo.getCidade(),
                enderecoSalvo.getUf()
        );
    }
}
