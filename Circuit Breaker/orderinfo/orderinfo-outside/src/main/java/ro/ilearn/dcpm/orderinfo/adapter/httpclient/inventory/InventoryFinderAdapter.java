package ro.ilearn.dcpm.orderinfo.adapter.httpclient.inventory;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.ilearn.dcpm.orderinfo.core.port.InventoryFinderPort;


@Component
@RequiredArgsConstructor
public class InventoryFinderAdapter implements InventoryFinderPort {
    private final Logger logger = LoggerFactory.getLogger(InventoryFinderAdapter.class);
    private final InventoryServiceClient inventoryServiceClient;


    @Override
    public int getInventory(Long bookId) {
        logger.info("Calling REST endpoint of inventory service ...");
        return inventoryServiceClient.getinventory(bookId);
    }
}
