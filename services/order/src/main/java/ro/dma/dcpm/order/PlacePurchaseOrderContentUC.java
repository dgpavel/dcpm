package ro.dma.dcpm.order;

import ro.dma.dcpm.order.dto.PurchaseOrderDto;

public interface PlacePurchaseOrderContentUC {
    PurchaseOrderDto placeOrder(PurchaseOrderDto order);
}
