package ro.dma.dcpm.review.dao;

import ro.dma.dcpm.review.dto.BookReview;

import java.util.List;

public interface ReviewQueryDao {
    List<BookReview> getBookReview(Long idBook);
}
