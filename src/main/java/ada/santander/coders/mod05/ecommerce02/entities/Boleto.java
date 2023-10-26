package ada.santander.coders.mod05.ecommerce02.entities;

import ada.santander.coders.mod05.ecommerce02.entities.contract.FormaPagamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"codigoBoleto"}, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Boleto extends FormaPagamento {
    @Column(unique = true, nullable = false)
    private String codigoBoleto;
}
