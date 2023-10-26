package ada.santander.coders.mod05.ecommerce02.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CompraStatusEnum {
    EM_ABERTO("em aberto"),
    PGTO_APROVADO("pagamento aprovado"),
    PGTO_PROCESSAMENTO("pagamento em processamento"),
    PGTO_REJEITADO("pagamento_rejeitado"),
    CANCELADA("compra cancelada"),
    EM_TRANSPORTE("em transporte"),
    FINALIZADO("compra finalizada");

    private final String statusDaCompra;
}
