package ro.dma.dcpm.orderinfo.dao.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_position")
@Getter
@Setter
public class OrderPositionJpaEntity {
    @Id
    private Long id;
    @Version
    private int version;
    @Column(name = "book_id")
    private Long bookId;
    private String book;
    private int quantity;
    private Boolean available;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderInfoJpaEntity order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderPositionJpaEntity)) return false;
        return id != null && id.equals(((OrderPositionJpaEntity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
