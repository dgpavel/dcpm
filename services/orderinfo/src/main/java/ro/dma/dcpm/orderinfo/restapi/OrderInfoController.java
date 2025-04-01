package ro.dma.dcpm.orderinfo.restapi;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.dma.dcpm.orderinfo.ViewAggregateOrderInformation;
import ro.dma.dcpm.orderinfo.dto.AggregateOrderInformation;

@RestController
@RequestMapping(path = "/api/v1/orderinfo")
@RequiredArgsConstructor
public class OrderInfoController {

    private final Logger logger = LoggerFactory.getLogger(OrderInfoController.class);
    private final ViewAggregateOrderInformation viewAggregateOrderInformation;

    @GetMapping(path = "/{idOrder}")
    public AggregateOrderInformation getOrderInformation(@PathVariable("idOrder") Long idOrder) {
        if (logger.isInfoEnabled()) {
            logger.info("Return information for order with id {}", idOrder);
        }
        return viewAggregateOrderInformation.getAggregateOrderInformation(idOrder);

    }
}