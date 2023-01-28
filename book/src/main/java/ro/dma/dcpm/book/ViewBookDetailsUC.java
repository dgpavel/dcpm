package ro.dma.dcpm.book;

import ro.dma.dcpm.book.dto.BookDetailsForView;

public interface ViewBookDetailsUC {
    BookDetailsForView getBookDetailsForView(Long idBook);
}
