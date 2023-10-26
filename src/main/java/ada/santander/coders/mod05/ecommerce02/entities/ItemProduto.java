package ada.santander.coders.mod05.ecommerce02.entities;

import ada.santander.coders.mod05.ecommerce02.entities.Compra;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "compra", "produto"})
@NoArgsConstructor
@AllArgsConstructor
public class ItemProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    // @JoinColumn(name = "compra_valor_produtos", referencedColumnName = "valor_produtos")
    private Compra compra;

    @ManyToOne
    private Produto produto;

    private long qtd;

    private double valorTotalProduto;
}
