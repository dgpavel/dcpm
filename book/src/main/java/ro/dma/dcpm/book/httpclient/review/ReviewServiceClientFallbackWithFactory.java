package ro.dma.dcpm.book.httpclient.review;

import lombok.extern.slf4j.Slf4j;
import ro.dma.dcpm.book.dto.BookReview;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ReviewServiceClientFallbackWithFactory implements ReviewServiceClient {

    @Override
    public List<BookReview> getReviewsForBook(Long bookId) {
        log.warn("Circuit Breaker - Book call Review#getReviewsForBook");
        return new ArrayList<>();
    }

}
