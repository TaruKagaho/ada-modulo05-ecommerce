package ada.santander.coders.mod05.ecommerce02.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record ClienteRequestPostDto(
        @NotBlank(message = "O campo nomeCompleto é obrigatório!")
        String nomeCompleto,

        @NotNull(message = "O campo dataNascimento é obrigatório!")
        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "dd/MM/yyyy",
                locale = "pt-BR",
                timezone = "Brazil/East"
        )
        LocalDate dataNascimento,

        @NotBlank(message = "O campo nomeCompleto é obrigatório!")
        // @CPF(message = "O campo cpf é inválido!")
        String cpf,

        @NotBlank(message = "O campo email é obrigatório!")
        @Email(message = "O campo email é inválido!")
        String email,

        @NotBlank(message = "O campo senha é obrigatório!")
        String senha
) {
}
