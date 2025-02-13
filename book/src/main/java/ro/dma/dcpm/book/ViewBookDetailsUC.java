package ro.dma.dcpm.book;

import ro.dma.dcpm.book.dto.BookDetailsForView;

import java.util.concurrent.ExecutionException;

public interface ViewBookDetailsUC {
    BookDetailsForView getBookDetailsForView(Long idBook) throws ExecutionException, InterruptedException;
}
