package ro.dma.dcpm.order.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.dma.dcpm.order.ViewPurchaseOrderContentUC;
import ro.dma.dcpm.order.dto.PurchaseOrderDto;


@RestController
@RequestMapping(path = "/api/v1/orders")
public class PurchaseOrderController {
    private final Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);
    private final ViewPurchaseOrderContentUC viewPurchaseOrderContent;

    public PurchaseOrderController(ViewPurchaseOrderContentUC viewPurchaseOrderContent) {
        this.viewPurchaseOrderContent = viewPurchaseOrderContent;
    }

    @GetMapping(path = "/{idPurchaseOrder}")
    public PurchaseOrderDto getPurchaseOrder(@PathVariable("idPurchaseOrder") Long idPurchaseOrder) {
        logger.info("Done ... returning purchase order now");
        return viewPurchaseOrderContent.getPurchaseOrderContent(idPurchaseOrder);
    }
}