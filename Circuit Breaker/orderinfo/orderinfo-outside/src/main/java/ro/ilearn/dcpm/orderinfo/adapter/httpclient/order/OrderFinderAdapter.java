package ro.ilearn.dcpm.orderinfo.adapter.httpclient.order;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.ilearn.dcpm.orderinfo.core.domain.PurchaseOrder;
import ro.ilearn.dcpm.orderinfo.core.port.OrderFinderPort;


@Component
@RequiredArgsConstructor
public class OrderFinderAdapter implements OrderFinderPort {
    private final Logger logger = LoggerFactory.getLogger(OrderFinderAdapter.class);
    private final OrderServiceClient orderServiceClient;


    @Override
    public PurchaseOrder getOrder(Long orderId) {
        logger.info("Calling REST endpoint of order service ...");
        return orderServiceClient.getOrder(orderId);
    }
}
