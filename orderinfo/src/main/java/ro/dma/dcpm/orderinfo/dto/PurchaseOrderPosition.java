package ro.dma.dcpm.orderinfo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PurchaseOrderPosition {
    private Long id;
    private int version;
    private Long idBook;
    private int quantity;
}