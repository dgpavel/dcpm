package ro.dma.dcpm.orderinfo.httpclient.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ro.dma.dcpm.orderinfo.dto.PurchaseOrder;


@FeignClient(name = "order", fallbackFactory = OrderServiceClientFallbackFactory.class)
public interface OrderServiceClient {
    @GetMapping(path = "/api/v1/orders/{idOrder}")
    PurchaseOrder getOrder(@PathVariable("idOrder") Long idOrder);
}
