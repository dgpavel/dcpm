package ro.dma.dcpm.inventory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.dma.dcpm.inventory.dao.InventoryQueryDao;

@Service
public class ViewCurrentInventoryOfTheBook implements ViewCurrentInventoryOfTheBookUC {
    private final InventoryQueryDao inventoryQueryDao;

    public ViewCurrentInventoryOfTheBook(InventoryQueryDao inventoryQueryDao) {
        this.inventoryQueryDao = inventoryQueryDao;
    }

    @Override
    @Transactional(readOnly = true)
    public int getInventoryOfBook(Long idBook) {
        return inventoryQueryDao.findQuantityByBookId(idBook);
    }
}
