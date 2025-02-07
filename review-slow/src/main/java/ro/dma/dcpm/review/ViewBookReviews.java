package ro.dma.dcpm.review;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.dma.dcpm.review.dao.ReviewQueryDao;
import ro.dma.dcpm.review.dto.BookReview;

import java.util.List;

@Service
public class ViewBookReviews implements ViewBookReviewsUC {
    private final ReviewQueryDao reviewQueryDao;

    public ViewBookReviews(ReviewQueryDao reviewQueryDao) {
        this.reviewQueryDao = reviewQueryDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookReview> getBookReviews(Long idBook) {
        if (idBook == null) {
            throw new IllegalArgumentException("idBook must be not null");
        }
        return reviewQueryDao.getBookReview(idBook);
    }
}
