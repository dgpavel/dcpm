package ro.dma.dcpm.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.dma.dcpm.order.dao.entities.PurchaseOrderPositionJpaEntity;

public interface PurchaseOrderPositionRepository extends JpaRepository<PurchaseOrderPositionJpaEntity, Long> {
}
