package ro.dma.dcpm.orderinfo.httpclient.inventory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InventoryServiceClientFallbackWithFactory implements InventoryServiceClient {

    @Override
    public int getInventory(Long bookId) {
        log.warn("Circuit Breaker - OrderInfo call Inventory#getInventoryOfBook");
        return 0;
    }

}
