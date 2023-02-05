package ro.dma.dcpm.orderinfo;

import org.springframework.stereotype.Component;
import ro.dma.dcpm.orderinfo.dto.*;
import ro.dma.dcpm.orderinfo.httpclient.book.BookServiceClient;
import ro.dma.dcpm.orderinfo.httpclient.inventory.InventoryServiceClient;
import ro.dma.dcpm.orderinfo.httpclient.order.OrderServiceClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class ViewAggregateOrderInformation implements ViewAggregateOrderInformationUC {
    private final BookServiceClient bookServiceClient;
    private final InventoryServiceClient inventoryServiceClient;
    private final OrderServiceClient orderServiceClient;

    public ViewAggregateOrderInformation(
            BookServiceClient bookServiceClient,
            InventoryServiceClient inventoryServiceClient,
            OrderServiceClient orderServiceClient) {
        this.bookServiceClient = bookServiceClient;
        this.inventoryServiceClient = inventoryServiceClient;
        this.orderServiceClient = orderServiceClient;
    }

    @Override
    public AggregateOrderInformation getAggregateOrderInformation(Long idOrder) {
        if (idOrder == null) {
            throw new IllegalArgumentException("idOrder must be not null");
        }
        PurchaseOrder order = orderServiceClient.getOrder(idOrder);
        AggregateOrderInformation orderInfo = transform(order);
        List<AggregateOrderPositionInformation> positions = new ArrayList<>();
        if (order.getPositions() != null) {
            for (PurchaseOrderPosition p : order.getPositions()) {
                AggregateOrderPositionInformation position = transform(p);
                positions.add(position);
            }
        }
        orderInfo.addOrderPositions(positions);
        return orderInfo;
    }

    private AggregateOrderInformation transform(PurchaseOrder purchaseOrder) {
        return AggregateOrderInformation.builder()
                .id(purchaseOrder.getId())
                .customerName(purchaseOrder.getCustomerName())
                .state(purchaseOrder.getState())
                .build();
    }

    private AggregateOrderPositionInformation transform(PurchaseOrderPosition p) {
        Book book = bookServiceClient.getBookDetailsForView(p.getIdBook());
        String titleAuthors = book.getTitle() != null ? book.getTitle() : "";
        if (book.getAuthors() != null) {
            titleAuthors = titleAuthors + " - " + book.getAuthors();
        }
        return AggregateOrderPositionInformation.builder()
                .id(p.getId())
                .quantity(p.getQuantity())
                .bookAggregateInformation(titleAuthors)
                .available(inventoryServiceClient.getInventory(book.getId()) >= p.getQuantity())
                .build();
    }

}
