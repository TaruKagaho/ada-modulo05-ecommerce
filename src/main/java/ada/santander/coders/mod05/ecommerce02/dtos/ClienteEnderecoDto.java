package ada.santander.coders.mod05.ecommerce02.dtos;

import jakarta.validation.constraints.NotNull;

public record ClienteEnderecoDto(
        @NotNull(message = "O campo clientId é obrigatório!")
        Long clienteId,

        @NotNull(message = "O campo enderecoId é obrigatório!")
        Long enderecoId,

        String tipo,

        String nomeRecebedor
) {
}
