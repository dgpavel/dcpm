package ro.dma.dcpm.orderinfo.httpclient.book;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ro.dma.dcpm.orderinfo.dto.Book;


@FeignClient(name = "book", fallbackFactory = BookServiceClientFallbackFactory.class)
public interface BookServiceClient {
    @GetMapping(path = "/api/v1/books/{idBook}")
    Book getBookDetailsForView(@PathVariable("idBook") Long idBook);
}
