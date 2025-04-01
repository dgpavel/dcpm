package ro.dma.dcpm.orderinfo;

import ro.dma.dcpm.orderinfo.dto.AggregateOrderInformation;

public interface ViewAggregateOrderInformation {
    AggregateOrderInformation getAggregateOrderInformation(Long idOrder);
}
