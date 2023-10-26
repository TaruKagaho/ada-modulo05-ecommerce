package ada.santander.coders.mod05.ecommerce02.dtos;

import ada.santander.coders.mod05.ecommerce02.entities.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ClienteResponseDto(
        Long id,

        String nomeCompleto,

        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "dd/MM/yyyy",
                locale = "pt-BR",
                timezone = "Brazil/East"
        )
        LocalDate dataNascimento,

        String cpf,

        String email
) {
    public ClienteResponseDto(Cliente clienteSalvo) {
        this(
                clienteSalvo.getId(),
                clienteSalvo.getNomeCompleto(),
                clienteSalvo.getDataNascimento(),
                clienteSalvo.getCpf(),
                clienteSalvo.getEmail()
        );
    }
}
