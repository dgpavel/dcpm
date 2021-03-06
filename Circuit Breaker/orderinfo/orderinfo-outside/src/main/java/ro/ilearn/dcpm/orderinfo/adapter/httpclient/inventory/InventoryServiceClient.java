package ro.ilearn.dcpm.orderinfo.adapter.httpclient.inventory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory", url = "${dcpm.inventory-api-path}", fallbackFactory = InventoryServiceClientFallbackFactory.class)
public interface InventoryServiceClient {
    @GetMapping(path = "/book/{bookId}")
    int getinventory(@PathVariable("bookId") Long bookId);
}
