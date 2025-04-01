package ro.dma.dcpm.inventory.dao;

public interface InventoryQueryDao {
    int findQuantityByBookId(Long idBook);
}

