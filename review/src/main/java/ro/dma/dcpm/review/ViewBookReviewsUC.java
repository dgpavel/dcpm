package ro.dma.dcpm.review;


import ro.dma.dcpm.review.dto.BookReview;

import java.util.List;

public interface ViewBookReviewsUC {
    List<BookReview> getBookReviews(Long idBook);
}
