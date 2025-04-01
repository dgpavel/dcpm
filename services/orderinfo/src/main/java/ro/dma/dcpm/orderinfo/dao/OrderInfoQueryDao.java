package ro.dma.dcpm.orderinfo.dao;

import ro.dma.dcpm.orderinfo.dto.AggregateOrderInformation;

public interface OrderInfoQueryDao {
    AggregateOrderInformation findOrderContentById(Long idOrder);
}
