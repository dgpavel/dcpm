package ro.dma.dcpm.orderinfo;

import ro.dma.dcpm.orderinfo.domain.AggregateOrderInformation;

public interface ViewAggregateOrderInformationUC {
    AggregateOrderInformation getAggregateOrderInformation(Long idOrder);
}
