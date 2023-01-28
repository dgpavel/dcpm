package ro.dma.dcpm.order.dao.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ro.dma.dcpm.order.domain.PurchaseOrderState;


import java.math.BigDecimal;

@Entity
@Table(name = "purchase_order")
@Getter
@Setter
public class PurchaseOrderJpaEntity {

    @Id
    @SequenceGenerator(name = "purchase_order_seq_generator", sequenceName = "purchase_order_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_order_seq_generator")
    private Long id;
    @Version
    private int version;
    @Column(name = "customer_name")
    private String customerName;
    private BigDecimal amount;
    @Enumerated
    private PurchaseOrderState state;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PurchaseOrderJpaEntity)) return false;
        return id != null && id.equals(((PurchaseOrderJpaEntity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

}