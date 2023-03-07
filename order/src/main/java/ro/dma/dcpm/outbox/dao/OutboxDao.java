package ro.dma.dcpm.outbox.dao;

import ro.dma.dcpm.order.domain.Operation;
import ro.dma.dcpm.order.dto.PurchaseOrderDto;

public interface OutboxDao {
    void writeToOutbox(PurchaseOrderDto order, Operation op);
}
