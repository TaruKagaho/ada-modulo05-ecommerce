package ada.santander.coders.mod05.ecommerce02.dtos;

import jakarta.validation.constraints.NotBlank;

public record EnderecoRequestPostDto(
        @NotBlank(message = "O campo logradouro é obrigatório!")
        String logradouro,

        @NotBlank(message = "O campo numero é obrigatório!")
        String numero,

        String complemento,

        @NotBlank(message = "O campo bairro é obrigatório!")
        String bairro,

        @NotBlank(message = "O campo cep é obrigatório!")
        String cep,

        @NotBlank(message = "O campo cidade é obrigatório!")
        String cidade,

        @NotBlank(message = "O campo uf é obrigatório!")
        String uf
) {
}
