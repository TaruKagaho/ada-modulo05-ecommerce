package ada.santander.coders.mod05.ecommerce02.entities;

import ada.santander.coders.mod05.ecommerce02.entities.contract.FormaPagamento;
import ada.santander.coders.mod05.ecommerce02.entities.enums.CompraStatusEnum;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "compras")
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    private LocalDate dataCompra;

    private double valorProdutos;

    private double desconto;

    private double valorFrete;

    private double valorTotal;

    // LAZY => não vem com os itens de produtos
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "compra")
    // @OneToMany => vem com os itens de produtos
    private List<ItemProduto> itens;

    @Enumerated(value = EnumType.STRING)
    private CompraStatusEnum status;

    /*@OneToOne
    private FormaPagamentoModel formaPagamento;*/
    @OneToOne
    private FormaPagamento formaPagamento;
}
