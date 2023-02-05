package ro.dma.dcpm.orderinfo.httpclient.book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookServiceClientFallbackFactory implements FallbackFactory<BookServiceClientFallbackWithFactory> {
    @Override
    public BookServiceClientFallbackWithFactory create(Throwable cause) {
        log.warn(cause.getMessage());
        return new BookServiceClientFallbackWithFactory();
    }
}
