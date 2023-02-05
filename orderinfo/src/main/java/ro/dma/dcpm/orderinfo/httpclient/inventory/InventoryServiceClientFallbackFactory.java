package ro.dma.dcpm.orderinfo.httpclient.inventory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InventoryServiceClientFallbackFactory
        implements FallbackFactory<InventoryServiceClientFallbackWithFactory> {
    @Override
    public InventoryServiceClientFallbackWithFactory create(Throwable cause) {
        log.warn(cause.getMessage());
        return new InventoryServiceClientFallbackWithFactory();
    }
}
