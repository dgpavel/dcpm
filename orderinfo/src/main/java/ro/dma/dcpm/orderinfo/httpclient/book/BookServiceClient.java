package ro.dma.dcpm.orderinfo.httpclient.book;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ro.dma.dcpm.orderinfo.dto.Book;


@FeignClient(name = "book", url = "${dcpm.book-api-path}", fallbackFactory = BookServiceClientFallbackFactory.class)
public interface BookServiceClient {
    @GetMapping(path = "/{idBook}")
    Book getBookDetailsForView(@PathVariable("idBook") Long idBook);
}
