package ro.dma.dcpm.review.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.dma.dcpm.review.ViewBookReviewsUC;
import ro.dma.dcpm.review.dto.BookReview;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/reviews")
public class ReviewController {

    private final Logger logger = LoggerFactory.getLogger(ReviewController.class);
    private final ViewBookReviewsUC viewBookReviews;

    public ReviewController(ViewBookReviewsUC viewBookReviews) {
        this.viewBookReviews = viewBookReviews;
    }

    @GetMapping(path = "/book/{idBook}")
    public List<BookReview> getBookReviews(@PathVariable Long idBook) {
        logger.info("Done ... view book reviews");
        return viewBookReviews.getBookReviews(idBook);
    }
}