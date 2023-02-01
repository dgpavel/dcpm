package ro.dma.dcpm.order.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PurchaseOrderPositionDto {
    private Long idBook;
    private int quantity;
}
