package ro.dma.dcpm.review;

import org.springframework.stereotype.Component;
import ro.dma.dcpm.review.dto.BookReview;
import ro.dma.dcpm.review.service.ReviewQueryService;

import java.util.List;

@Component
public class ViewBookReviewsImpl implements ViewBookReviews {
    private final ReviewQueryService reviewQueryService;

    public ViewBookReviewsImpl(ReviewQueryService reviewQueryService) {
        this.reviewQueryService = reviewQueryService;
    }

    @Override
    public List<BookReview> getBookReviews(Long idBook) {
        if (idBook == null) {
            throw new IllegalArgumentException("idBook must be not null");
        }
        return reviewQueryService.getBookReview(idBook);
    }
}
