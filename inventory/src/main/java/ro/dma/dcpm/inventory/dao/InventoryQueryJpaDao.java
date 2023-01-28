package ro.dma.dcpm.inventory.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryQueryJpaDao implements InventoryQueryDao {
    private final EntityManager em;

    public InventoryQueryJpaDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public int findQuantityByBookId(Long idBook) {
        TypedQuery<Integer> query = em.createQuery("SELECT i.quantity FROM InventoryJpaEntity i WHERE i.id = ?1", Integer.class);
        return query.setParameter(1, idBook).getSingleResult();
    }
}
