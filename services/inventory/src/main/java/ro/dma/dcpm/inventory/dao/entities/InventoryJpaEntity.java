package ro.dma.dcpm.inventory.dao.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "inventory")
@Getter
@Setter
public class InventoryJpaEntity {

    @Id
    @SequenceGenerator(name = "inventory_seq_generator", sequenceName = "inventory_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_seq_generator")
    private Long id;

    @Version
    private int version;

    @Column(name = "book_id")
    private Long bookId;

    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventoryJpaEntity)) return false;
        return id != null && id.equals(((InventoryJpaEntity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

}