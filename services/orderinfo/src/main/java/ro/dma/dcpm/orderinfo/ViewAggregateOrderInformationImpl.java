package ro.dma.dcpm.orderinfo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.dma.dcpm.orderinfo.dao.OrderInfoQueryDao;
import ro.dma.dcpm.orderinfo.dto.AggregateOrderInformation;

@Service
public class ViewAggregateOrderInformationImpl implements ViewAggregateOrderInformation {
    private final OrderInfoQueryDao orderInfoQueryDao;

    public ViewAggregateOrderInformationImpl(OrderInfoQueryDao orderInfoQueryDao) {
        this.orderInfoQueryDao = orderInfoQueryDao;
    }

    @Override
    @Transactional(readOnly = true)
    public AggregateOrderInformation getAggregateOrderInformation(Long idOrder) {
        if (idOrder == null) {
            throw new IllegalArgumentException("idOrder must be not null");
        }
        return orderInfoQueryDao.findOrderContentById(idOrder);
    }
}
