package ro.dma.dcpm.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.dma.dcpm.order.dao.entities.PurchaseOrderJpaEntity;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrderJpaEntity, Long> {
}
