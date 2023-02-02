package ro.dma.dcpm.book.httpclient.review;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReviewServiceClientFallbackFactory implements FallbackFactory<ReviewServiceClientFallbackWithFactory> {
    @Override
    public ReviewServiceClientFallbackWithFactory create(Throwable cause) {
        log.warn(cause.getMessage());
        return new ReviewServiceClientFallbackWithFactory();
    }
}
