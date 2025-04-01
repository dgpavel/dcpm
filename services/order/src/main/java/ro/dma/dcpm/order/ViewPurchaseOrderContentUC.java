package ro.dma.dcpm.order;

import ro.dma.dcpm.order.dto.PurchaseOrderDto;

public interface ViewPurchaseOrderContentUC {
    PurchaseOrderDto getPurchaseOrderContent(Long idPurchaseOrder);
}
