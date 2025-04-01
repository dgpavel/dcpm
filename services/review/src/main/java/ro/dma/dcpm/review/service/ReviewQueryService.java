package ro.dma.dcpm.review.service;

import ro.dma.dcpm.review.dto.BookReview;

import java.util.List;

public interface ReviewQueryService {
    List<BookReview> getBookReview(Long idBook);
}
