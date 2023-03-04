package ro.dma.dcpm.orderinfo.dao.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ro.dma.dcpm.orderinfo.dto.OrderState;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order_info")
@Getter
@Setter
public class OrderInfoJpaEntity {
    @Id
    private Long id;
    @Version
    private int version;
    @Column(name = "customer_name")
    private String customerName;
    @Enumerated(EnumType.STRING)
    private OrderState state;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderPositionJpaEntity> positions = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderInfoJpaEntity)) return false;
        return id != null && id.equals(((OrderInfoJpaEntity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
