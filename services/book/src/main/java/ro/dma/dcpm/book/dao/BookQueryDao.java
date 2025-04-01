package ro.dma.dcpm.book.dao;

import ro.dma.dcpm.book.dto.BookDetailsForView;

public interface BookQueryDao {
    BookDetailsForView getBookDetailsForView(Long idBook);
}
