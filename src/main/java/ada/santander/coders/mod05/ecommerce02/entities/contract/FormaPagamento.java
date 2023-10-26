package ada.santander.coders.mod05.ecommerce02.entities.contract;

import ada.santander.coders.mod05.ecommerce02.entities.Compra;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "compra"})
@NoArgsConstructor
@AllArgsConstructor
// @MappedSuperclass
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) - Não funciona GenerationType.IDENTITY
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class FormaPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Compra compra;
}
