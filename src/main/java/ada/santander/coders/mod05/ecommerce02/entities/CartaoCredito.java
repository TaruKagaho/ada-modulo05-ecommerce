package ada.santander.coders.mod05.ecommerce02.entities;

import ada.santander.coders.mod05.ecommerce02.entities.contract.FormaPagamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"numeroCartao", "validade", "cvv"}, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CartaoCredito extends FormaPagamento {
    @Column(unique = true, nullable = false, length = 16)
    private String numeroCartao;

    @Column(nullable = false)
    private String nomeTitular;

    @Column(nullable = false)
    private LocalDate validade; // mmaaaa

    @Column(nullable = false, length = 3)
    private String cvv;

    @Column(nullable = false)
    private int qtdParcelas;

    @Column(nullable = false)
    private String cpfTitular;
}
