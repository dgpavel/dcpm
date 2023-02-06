package ro.dma.dcpm.book.httpclient.review;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ro.dma.dcpm.book.dto.BookReview;


import java.util.List;

@FeignClient(name = "review", fallbackFactory = ReviewServiceClientFallbackFactory.class)
public interface ReviewServiceClient {

    @GetMapping(path = "/api/v1/reviews/book/{idBook}")
    List<BookReview> getReviewsForBook(@PathVariable("idBook") Long idBook);
}
