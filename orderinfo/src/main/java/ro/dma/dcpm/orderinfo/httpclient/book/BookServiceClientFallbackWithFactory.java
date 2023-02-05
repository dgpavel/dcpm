package ro.dma.dcpm.orderinfo.httpclient.book;

import lombok.extern.slf4j.Slf4j;
import ro.dma.dcpm.orderinfo.dto.Book;

@Slf4j
public class BookServiceClientFallbackWithFactory implements BookServiceClient {

    @Override
    public Book getBookDetailsForView(Long bookId) {
        log.warn("Circuit Breaker - OrderInfo call Book#getBookDetailsForView");
        return Book.builder().build();
    }

}
