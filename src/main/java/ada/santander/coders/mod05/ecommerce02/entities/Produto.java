package ada.santander.coders.mod05.ecommerce02.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "sku"})
@NoArgsConstructor
@AllArgsConstructor
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(unique = true, name = "SKU")
    private String sku;

    @Column(nullable = false)
    private double preco;

    @Column(nullable = false)
    private int qtdEstoque;
}
