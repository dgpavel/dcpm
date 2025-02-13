package ro.dma.dcpm.book.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.dma.dcpm.book.dao.BookQueryDao;
import ro.dma.dcpm.book.dto.BookDetailsForView;

@Service
@Transactional(readOnly = true)
public class BookQueryServiceImpl implements BookQueryService {
    private final BookQueryDao bookQueryDao;

    public BookQueryServiceImpl(BookQueryDao bookQueryDao) {
        this.bookQueryDao = bookQueryDao;
    }

    @Override
    public BookDetailsForView getBookDetailsForView(Long idBook) {
        return bookQueryDao.getBookDetailsForView(idBook);
    }
}
