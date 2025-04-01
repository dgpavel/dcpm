package ro.dma.dcpm.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.dma.dcpm.order.dao.PurchaseOrderQueryDao;
import ro.dma.dcpm.order.dto.PurchaseOrderDto;

@Service
public class ViewPurchaseOrderContent implements ViewPurchaseOrderContentUC {
    private final PurchaseOrderQueryDao purchaseOrderQueryDao;

    public ViewPurchaseOrderContent(PurchaseOrderQueryDao purchaseOrderQueryDao) {
        this.purchaseOrderQueryDao = purchaseOrderQueryDao;
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseOrderDto getPurchaseOrderContent(Long idPurchaseOrder) {
        return purchaseOrderQueryDao.findPurchaseOrderContentById(idPurchaseOrder);
    }
}
