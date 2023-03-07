package ro.dma.dcpm.outbox.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import ro.dma.dcpm.order.domain.Operation;
import ro.dma.dcpm.order.dto.PurchaseOrderDto;
import ro.dma.dcpm.order.dto.PurchaseOrderPositionDto;
import ro.dma.dcpm.shared.MyRuntimeException;
import ro.dma.dcpm.shared.TsidUtil;

import java.time.LocalDateTime;

@Repository
public class OutboxJpaDao implements OutboxDao {
    private final EntityManager em;
    private final ObjectMapper mapper;

    public OutboxJpaDao(EntityManager em, ObjectMapper mapper) {
        this.em = em;
        this.mapper = mapper;
    }

    @Override
    public void writeToOutbox(PurchaseOrderDto order, Operation op) {
        try {
            Query q = this.em.createNativeQuery(
                    "INSERT INTO outbox (id, type, aggregate_type, aggregate_id, payload, created_date, published) " +
                            "VALUES (:id, :type, :aggregateType, :aggregateId, CAST(:payload as jsonb), :createdDate, :published)");
            q.setParameter("id", TsidUtil.TSID_FACTORY.generate().toLong());
            q.setParameter("type", op.toString());
            q.setParameter("aggregateType", "Order");
            q.setParameter("aggregateId", order.getId());
            q.setParameter("payload", buildPayload(order));
            q.setParameter("createdDate", LocalDateTime.now());
            q.setParameter("published", Boolean.FALSE);
            q.executeUpdate();
        } catch (JsonProcessingException e) {
            throw new MyRuntimeException(e.getMessage(), e);
        }

    }

    private String buildPayload(PurchaseOrderDto order) throws JsonProcessingException {
        ObjectNode json = mapper.createObjectNode()
                .put("id", order.getId())
                .put("customerName", order.getCustomerName())
                .put("amount", order.getAmount());

        ArrayNode positions = json.putArray("positions");
        for (PurchaseOrderPositionDto p : order.getPositions()) {
            positions.add(mapper.createObjectNode()
                    .put("id", p.getId())
                    .put("bookId", p.getIdBook())
                    .put("quantity", p.getQuantity()));
        }
        return mapper.writeValueAsString(json);
    }
}
