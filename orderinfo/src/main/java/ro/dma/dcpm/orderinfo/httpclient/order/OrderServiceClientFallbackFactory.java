package ro.dma.dcpm.orderinfo.httpclient.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderServiceClientFallbackFactory implements FallbackFactory<OrderServiceClientFallbackWithFactory> {
    @Override
    public OrderServiceClientFallbackWithFactory create(Throwable cause) {
        log.warn(cause.getMessage());
        return new OrderServiceClientFallbackWithFactory();
    }
}
