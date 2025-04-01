package ro.dma.dcpm.book.services;

import ro.dma.dcpm.book.dto.BookDetailsForView;

public interface BookQueryService {
    BookDetailsForView getBookDetailsForView(Long idBook);
}
