package ro.dma.dcpm.book.httpclient.review;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import ro.dma.dcpm.book.dto.BookReview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@HttpExchange
public interface ReviewServiceClient {
    String REVIEW_SERVICE_NAME = "review";
    Logger logger = LoggerFactory.getLogger(ReviewServiceClient.class);

    @GetExchange("/api/v1/reviews/book/{idBook}")
    @CircuitBreaker(name = REVIEW_SERVICE_NAME, fallbackMethod = "fallbackMethodGetReviewsForBook")
    List<BookReview> getReviewsForBook(@PathVariable("idBook") Long idBook);

    @TimeLimiter(name = REVIEW_SERVICE_NAME, fallbackMethod = "fallbackMethodReadReviewsForBook")
    default CompletableFuture<List<BookReview>> readReviewsForBook(Long idBook) {
        return CompletableFuture.supplyAsync(() -> getReviewsForBook(idBook));
    }


    default List<BookReview> fallbackMethodGetReviewsForBook(Long idBook, Throwable throwable) {
        logger.warn("Circuit Breaker - Book call Review#getReviewsForBook for idBook {}, failure reason: {}", idBook, throwable.getMessage());
        return new ArrayList<>();
    }

    default CompletableFuture<List<BookReview>> fallbackMethodReadReviewsForBook(Long idBook, Throwable throwable) {
        logger.warn("Time Limiter - Book call Review#getReviewsForBook for idBook {}, failure reason: {}", idBook, throwable.getMessage());
        return CompletableFuture.supplyAsync(ArrayList::new);
    }
}
