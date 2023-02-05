package ro.dma.dcpm.orderinfo.httpclient.order;

import lombok.extern.slf4j.Slf4j;
import ro.dma.dcpm.orderinfo.dto.PurchaseOrder;

@Slf4j
public class OrderServiceClientFallbackWithFactory implements OrderServiceClient {

    @Override
    public PurchaseOrder getOrder(Long orderId) {
        log.warn("Circuit Breaker - OrderInfo call Order#getPurchaseOrder");
        return PurchaseOrder.builder()
                .id(orderId)
                .build();
    }

}
