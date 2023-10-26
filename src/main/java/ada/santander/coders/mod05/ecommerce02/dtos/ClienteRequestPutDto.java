package ada.santander.coders.mod05.ecommerce02.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClienteRequestPutDto(
        @NotBlank(message = "O campo nomeCompleto é obrigatório!")
        String nomeCompleto,

        @NotNull(message = "O campo dataNascimento é obrigatório!")
        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "dd/MM/yyyy",
                locale = "pt-BR",
                timezone = "Brazil/East"
        )
        LocalDate dataNascimento/*,

        @NotBlank(message = "O campo email é obrigatório!")
        String email*/
) {
}
