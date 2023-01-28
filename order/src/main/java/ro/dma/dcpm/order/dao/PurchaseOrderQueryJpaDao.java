package ro.dma.dcpm.order.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import ro.dma.dcpm.order.dao.entities.PurchaseOrderJpaEntity;
import ro.dma.dcpm.order.dao.entities.PurchaseOrderJpaEntity_;
import ro.dma.dcpm.order.dao.entities.PurchaseOrderPositionJpaEntity;
import ro.dma.dcpm.order.dao.entities.PurchaseOrderPositionJpaEntity_;
import ro.dma.dcpm.order.domain.PurchaseOrderState;
import ro.dma.dcpm.order.dto.PurchaseOrderDto;
import ro.dma.dcpm.order.dto.PurchaseOrderPositionDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PurchaseOrderQueryJpaDao implements PurchaseOrderQueryDao {
    private final EntityManager em;

    public PurchaseOrderQueryJpaDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public PurchaseOrderDto findPurchaseOrderContentById(Long idPurchaseOrder) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
        Root<PurchaseOrderPositionJpaEntity> positionRoot = cq.from(PurchaseOrderPositionJpaEntity.class);
        Join<PurchaseOrderPositionJpaEntity, PurchaseOrderJpaEntity> orderJoin = positionRoot.join(PurchaseOrderPositionJpaEntity_.order);
        cq.multiselect(
                orderJoin.get(PurchaseOrderJpaEntity_.id).alias(PurchaseOrderJpaEntity_.ID),
                orderJoin.get(PurchaseOrderJpaEntity_.amount).alias(PurchaseOrderJpaEntity_.AMOUNT),
                orderJoin.get(PurchaseOrderJpaEntity_.customerName).alias(PurchaseOrderJpaEntity_.CUSTOMER_NAME),
                orderJoin.get(PurchaseOrderJpaEntity_.state).alias(PurchaseOrderJpaEntity_.STATE),
                positionRoot.get(PurchaseOrderPositionJpaEntity_.bookId).alias(PurchaseOrderPositionJpaEntity_.BOOK_ID),
                positionRoot.get(PurchaseOrderPositionJpaEntity_.quantity).alias(PurchaseOrderPositionJpaEntity_.QUANTITY)
        );
        cq.where(cb.equal(orderJoin.get(PurchaseOrderJpaEntity_.id), idPurchaseOrder));
        List<Tuple> tupleLst = em.createQuery(cq).getResultList();
        return fromTupleToDto(tupleLst, idPurchaseOrder);
    }

    private PurchaseOrderDto fromTupleToDto(List<Tuple> tupleLst, Long idPurchaseOrder) {
        PurchaseOrderDto order = PurchaseOrderDto.builder()
                .id(idPurchaseOrder)
                .build();
        if (!tupleLst.isEmpty()) {
            Tuple tuple = tupleLst.get(0);
            order.setAmount(tuple.get(PurchaseOrderJpaEntity_.AMOUNT, BigDecimal.class));
            order.setState(tuple.get(PurchaseOrderJpaEntity_.STATE, PurchaseOrderState.class));
            order.setCustomerName(tuple.get(PurchaseOrderJpaEntity_.CUSTOMER_NAME, String.class));
            List<PurchaseOrderPositionDto> positions = new ArrayList<>();
            for (Tuple item : tupleLst) {
                PurchaseOrderPositionDto position = PurchaseOrderPositionDto.builder()
                        .bookId(item.get(PurchaseOrderPositionJpaEntity_.BOOK_ID, Long.class))
                        .quantity(item.get(PurchaseOrderPositionJpaEntity_.QUANTITY, Integer.class))
                        .build();
                positions.add(position);
            }
            order.setPositions(positions);
        }
        return order;
    }
}
