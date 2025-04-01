package ro.dma.dcpm.orderinfo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ro.dma.dcpm.orderinfo.dao.entities.OrderInfoJpaEntity;
import ro.dma.dcpm.orderinfo.dao.entities.OrderInfoJpaEntity_;
import ro.dma.dcpm.orderinfo.dao.entities.OrderPositionJpaEntity;
import ro.dma.dcpm.orderinfo.dao.entities.OrderPositionJpaEntity_;
import ro.dma.dcpm.orderinfo.dto.AggregateOrderInformation;
import ro.dma.dcpm.orderinfo.dto.AggregateOrderPositionInformation;
import ro.dma.dcpm.orderinfo.dto.OrderState;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderInfoQueryJpaDao implements OrderInfoQueryDao {
    private final EntityManager em;
    private static final String ORDER_POSITION_ID = "orderPositionId";
    public OrderInfoQueryJpaDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public AggregateOrderInformation findOrderContentById(Long idOrder) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
        Root<OrderPositionJpaEntity> positionRoot = cq.from(OrderPositionJpaEntity.class);
        Join<OrderPositionJpaEntity, OrderInfoJpaEntity> orderJoin = positionRoot.join(OrderPositionJpaEntity_.order);
        cq.multiselect(
                orderJoin.get(OrderInfoJpaEntity_.id).alias(OrderInfoJpaEntity_.ID),
                orderJoin.get(OrderInfoJpaEntity_.customerName).alias(OrderInfoJpaEntity_.CUSTOMER_NAME),
                orderJoin.get(OrderInfoJpaEntity_.state).alias(OrderInfoJpaEntity_.STATE),
                positionRoot.get(OrderPositionJpaEntity_.id).alias(ORDER_POSITION_ID),
                positionRoot.get(OrderPositionJpaEntity_.bookId).alias(OrderPositionJpaEntity_.BOOK_ID),
                positionRoot.get(OrderPositionJpaEntity_.quantity).alias(OrderPositionJpaEntity_.QUANTITY),
                positionRoot.get(OrderPositionJpaEntity_.available).alias(OrderPositionJpaEntity_.AVAILABLE),
                positionRoot.get(OrderPositionJpaEntity_.book).alias(OrderPositionJpaEntity_.BOOK)
        );
        cq.where(cb.equal(orderJoin.get(OrderInfoJpaEntity_.id), idOrder));
        List<Tuple> tupleLst = em.createQuery(cq).getResultList();
        return fromTupleToDto(tupleLst, idOrder);
    }

    private AggregateOrderInformation fromTupleToDto(List<Tuple> tupleLst, Long idOrder) {
        AggregateOrderInformation aggregateOrderInformation = AggregateOrderInformation.builder().id(idOrder).build();
        if (!tupleLst.isEmpty()) {
            Tuple tuple = tupleLst.get(0);
            aggregateOrderInformation.setCustomerName(tuple.get(OrderInfoJpaEntity_.CUSTOMER_NAME, String.class));
            aggregateOrderInformation.setState(tuple.get(OrderInfoJpaEntity_.STATE, OrderState.class));
        }
        List<AggregateOrderPositionInformation> positions = new ArrayList<>();
        for (Tuple tuple : tupleLst) {
            AggregateOrderPositionInformation position = transform(tuple);
            positions.add(position);
        }
        aggregateOrderInformation.addOrderPositions(positions);
        return aggregateOrderInformation;
    }

    private AggregateOrderPositionInformation transform(Tuple tuple) {
        return AggregateOrderPositionInformation.builder()
                .id(tuple.get(ORDER_POSITION_ID, Long.class))
                .quantity(tuple.get(OrderPositionJpaEntity_.QUANTITY, Integer.class))
                .bookAggregateInformation(tuple.get(OrderPositionJpaEntity_.BOOK, String.class))
                .available(tuple.get(OrderPositionJpaEntity_.AVAILABLE, Boolean.class))
                .build();
    }
}
