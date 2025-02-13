package ro.dma.dcpm.book;

import org.springframework.stereotype.Component;
import ro.dma.dcpm.book.dto.BookDetailsForView;
import ro.dma.dcpm.book.dto.BookReview;
import ro.dma.dcpm.book.httpclient.review.ReviewServiceClient;
import ro.dma.dcpm.book.services.BookQueryService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class ViewBookDetailsImpl implements ViewBookDetails {
    private final BookQueryService bookQueryService;
    private final ReviewServiceClient reviewServiceClient;

    public ViewBookDetailsImpl(BookQueryService bookQueryService, ReviewServiceClient reviewServiceClient) {
        this.bookQueryService = bookQueryService;
        this.reviewServiceClient = reviewServiceClient;
    }

    @Override
    public BookDetailsForView getBookDetailsForView(Long idBook) throws ExecutionException, InterruptedException {
        if (idBook == null) {
            throw new IllegalArgumentException("idBook must be not null");
        }
        BookDetailsForView bookDetails = bookQueryService.getBookDetailsForView(idBook);
        CompletableFuture<List<BookReview>> reviewsFuture = reviewServiceClient.readReviewsForBook(idBook);
        bookDetails.setReviews(reviewsFuture.get());
        return bookDetails;
    }
}
