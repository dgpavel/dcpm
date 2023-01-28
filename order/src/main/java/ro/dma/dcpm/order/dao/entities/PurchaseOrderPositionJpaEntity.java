package ro.dma.dcpm.order.dao.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "purchase_order_position")
@Getter
@Setter
public class PurchaseOrderPositionJpaEntity {

    @Id
    @SequenceGenerator(name = "purchase_order_position_seq_generator", sequenceName = "purchase_order_position_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_order_position_seq_generator")
    private Long id;
    @Version
    private int version;
    @Column(name = "book_id")
    private Long bookId;
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrderJpaEntity order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PurchaseOrderPositionJpaEntity)) return false;
        return id != null && id.equals(((PurchaseOrderPositionJpaEntity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}