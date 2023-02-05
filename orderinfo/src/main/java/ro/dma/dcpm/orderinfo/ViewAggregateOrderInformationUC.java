package ro.dma.dcpm.orderinfo;

import ro.dma.dcpm.orderinfo.dto.AggregateOrderInformation;

public interface ViewAggregateOrderInformationUC {
    AggregateOrderInformation getAggregateOrderInformation(Long idOrder);
}
