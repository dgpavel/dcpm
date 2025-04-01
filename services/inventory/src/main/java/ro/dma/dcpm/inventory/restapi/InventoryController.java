package ro.dma.dcpm.inventory.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.dma.dcpm.inventory.ViewCurrentInventoryOfTheBookUC;

@RestController
@RequestMapping(path = "/api/v1/inventories")
public class InventoryController {
    private final Logger logger = LoggerFactory.getLogger(InventoryController.class);
    private final ViewCurrentInventoryOfTheBookUC viewCurrentInventoryOfTheBook;

    public InventoryController(ViewCurrentInventoryOfTheBookUC viewCurrentInventoryOfTheBook) {
        this.viewCurrentInventoryOfTheBook = viewCurrentInventoryOfTheBook;
    }

    @GetMapping("/book/{idBook}")
    public int getInventoryOfBook(@PathVariable Long idBook) {
        logger.info("Return inventory for book {}", idBook);
        return viewCurrentInventoryOfTheBook.getInventoryOfBook(idBook);
    }
}