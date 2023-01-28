package ro.dma.dcpm.order.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ro.dma.dcpm.order.domain.PurchaseOrderState;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Builder
public class PurchaseOrderDto {
    private Long id;
    private String customerName;
    private BigDecimal amount;
    private PurchaseOrderState state;
    private List<PurchaseOrderPositionDto> positions;
}
