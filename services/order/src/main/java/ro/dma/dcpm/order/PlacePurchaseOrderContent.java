package ro.dma.dcpm.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.dma.dcpm.outbox.dao.OutboxDao;
import ro.dma.dcpm.order.dao.PurchaseOrderPositionRepository;
import ro.dma.dcpm.order.dao.PurchaseOrderRepository;
import ro.dma.dcpm.order.dao.entities.PurchaseOrderJpaEntity;
import ro.dma.dcpm.order.dao.entities.PurchaseOrderPositionJpaEntity;
import ro.dma.dcpm.order.domain.Operation;
import ro.dma.dcpm.order.domain.PurchaseOrderState;
import ro.dma.dcpm.order.dto.PurchaseOrderDto;
import ro.dma.dcpm.order.dto.PurchaseOrderPositionDto;

@Service
public class PlacePurchaseOrderContent implements PlacePurchaseOrderContentUC {
    private final OutboxDao outboxDao;
    private final PurchaseOrderPositionRepository orderPositionRepository;
    private final PurchaseOrderRepository orderRepository;

    public PlacePurchaseOrderContent(OutboxDao outboxDao,
                                     PurchaseOrderPositionRepository orderPositionRepository,
                                     PurchaseOrderRepository orderRepository) {
        this.outboxDao = outboxDao;
        this.orderPositionRepository = orderPositionRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public PurchaseOrderDto placeOrder(PurchaseOrderDto orderDto) {
        PurchaseOrderJpaEntity order = fromDtoToEntity(orderDto);
        order = orderRepository.save(order);
        orderDto.setId(order.getId());
        //
        for (PurchaseOrderPositionDto positionDto : orderDto.getPositions()) {
            PurchaseOrderPositionJpaEntity orderPosition = fromDtoToEntity(positionDto);
            orderPosition.setOrder(order);
            orderPosition = orderPositionRepository.save(orderPosition);
            positionDto.setId(orderPosition.getId());
        }
        //
        outboxDao.writeToOutbox(orderDto, Operation.CREATE);
        //
        return orderDto;
    }

    private PurchaseOrderJpaEntity fromDtoToEntity(PurchaseOrderDto order) {
        PurchaseOrderJpaEntity entity = new PurchaseOrderJpaEntity();
        entity.setAmount(order.getAmount());
        entity.setState(PurchaseOrderState.PENDING);
        entity.setCustomerName(order.getCustomerName());
        return entity;
    }

    private PurchaseOrderPositionJpaEntity fromDtoToEntity(PurchaseOrderPositionDto orderPosition) {
        PurchaseOrderPositionJpaEntity entity = new PurchaseOrderPositionJpaEntity();
        entity.setQuantity(orderPosition.getQuantity());
        entity.setIdBook(orderPosition.getIdBook());
        return entity;
    }
}
