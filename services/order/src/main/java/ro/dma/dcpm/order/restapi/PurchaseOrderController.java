package ro.dma.dcpm.order.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ro.dma.dcpm.order.PlacePurchaseOrderContent;
import ro.dma.dcpm.order.ViewPurchaseOrderContentUC;
import ro.dma.dcpm.order.dto.PurchaseOrderDto;


@RestController
@RequestMapping(path = "/api/v1/orders")
public class PurchaseOrderController {
    private final Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);
    private final ViewPurchaseOrderContentUC viewPurchaseOrderContent;
    private final PlacePurchaseOrderContent placePurchaseOrderContent;

    public PurchaseOrderController(
            ViewPurchaseOrderContentUC viewPurchaseOrderContent,
            PlacePurchaseOrderContent placePurchaseOrderContent
    ) {
        this.viewPurchaseOrderContent = viewPurchaseOrderContent;
        this.placePurchaseOrderContent = placePurchaseOrderContent;
    }

    @GetMapping(path = "/{idPurchaseOrder}")
    public PurchaseOrderDto getPurchaseOrder(@PathVariable("idPurchaseOrder") Long idPurchaseOrder) {
        logger.info("Done ... returning purchase order now");
        return viewPurchaseOrderContent.getPurchaseOrderContent(idPurchaseOrder);
    }

    @PostMapping
    public PurchaseOrderDto placeOrder(@RequestBody PurchaseOrderDto order) {
        return placePurchaseOrderContent.placeOrder(order);
    }
}