package ro.dma.dcpm.orderinfo.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PurchaseOrder {
    private Long id;
    private String customerName;
    private List<PurchaseOrderPosition> positions;
    private OrderState state;

}