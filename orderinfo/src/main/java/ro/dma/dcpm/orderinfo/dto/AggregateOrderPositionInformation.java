package ro.dma.dcpm.orderinfo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AggregateOrderPositionInformation {
    private Long id;
    private String bookAggregateInformation;
    private int quantity;
    private Boolean available;

}