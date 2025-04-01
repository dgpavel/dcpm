package ro.dma.dcpm.review.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.dma.dcpm.review.dao.ReviewQueryDao;
import ro.dma.dcpm.review.dto.BookReview;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewQueryDao reviewQueryDao;

    public ReviewQueryServiceImpl(ReviewQueryDao reviewQueryDao) {
        this.reviewQueryDao = reviewQueryDao;
    }

    @Override
    public List<BookReview> getBookReview(Long idBook) {
        return reviewQueryDao.getBookReview(idBook);
    }
}
