package ro.dma.dcpm.orderinfo.httpclient.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ro.dma.dcpm.orderinfo.dto.PurchaseOrder;


@FeignClient(name = "order", url = "${dcpm.order-api-path}", fallbackFactory = OrderServiceClientFallbackFactory.class)
public interface OrderServiceClient {
    @GetMapping(path = "/{idOrder}")
    PurchaseOrder getOrder(@PathVariable("idOrder") Long idOrder);
}
