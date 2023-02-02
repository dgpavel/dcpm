package ro.dma.dcpm.book;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.dma.dcpm.book.dao.BookQueryDao;
import ro.dma.dcpm.book.dto.BookDetailsForView;
import ro.dma.dcpm.book.dto.BookReview;
import ro.dma.dcpm.book.httpclient.review.ReviewServiceClient;

import java.util.List;

@Service
public class ViewBookDetails implements ViewBookDetailsUC {
    private final BookQueryDao carteQueryDao;
    private final ReviewServiceClient reviewServiceClient;

    public ViewBookDetails(BookQueryDao carteQueryDao, ReviewServiceClient reviewServiceClient) {
        this.carteQueryDao = carteQueryDao;
        this.reviewServiceClient = reviewServiceClient;
    }

    @Override
    @Transactional(readOnly = true)
    public BookDetailsForView getBookDetailsForView(Long idBook) {
        if (idBook == null) {
            throw new IllegalArgumentException("idBook must be not null");
        }
        BookDetailsForView bookDetails = carteQueryDao.getBookDetailsForView(idBook);
        List<BookReview> reviews = reviewServiceClient.getReviewsForBook(idBook);
        bookDetails.setReviews(reviews);
        return bookDetails;
    }
}
