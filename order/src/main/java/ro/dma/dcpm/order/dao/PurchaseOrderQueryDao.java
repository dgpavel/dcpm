package ro.dma.dcpm.order.dao;

import ro.dma.dcpm.order.dto.PurchaseOrderDto;

public interface PurchaseOrderQueryDao {
    PurchaseOrderDto findPurchaseOrderContentById(Long idPurchaseOrder);
}
