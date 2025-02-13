package ro.dma.dcpm.book.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.dma.dcpm.book.ViewBookDetailsUC;
import ro.dma.dcpm.book.dto.BookDetailsForView;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "/api/v1/books")
public class BookController {

    private final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final ViewBookDetailsUC viewBookDetails;

    public BookController(ViewBookDetailsUC viewBookDetails) {
        this.viewBookDetails = viewBookDetails;
    }

    @GetMapping(path = "/{idBook}")
    public BookDetailsForView getBookDetailsForView(@PathVariable Long idBook) throws ExecutionException, InterruptedException {
        logger.info("Done ... view book details");
        return viewBookDetails.getBookDetailsForView(idBook);
    }
}