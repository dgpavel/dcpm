package ro.dma.dcpm.book;

import org.springframework.stereotype.Service;
import ro.dma.dcpm.book.dao.BookQueryDao;
import ro.dma.dcpm.book.dto.BookDetailsForView;

@Service
public class ViewBookDetails implements ViewBookDetailsUC {
    private final BookQueryDao carteQueryDao;

    public ViewBookDetails(BookQueryDao carteQueryDao) {
        this.carteQueryDao = carteQueryDao;
    }

    @Override
    public BookDetailsForView getBookDetailsForView(Long idBook) {
        if (idBook == null) {
            throw new IllegalArgumentException("idBook must be not null");
        }
        return carteQueryDao.getBookDetailsForView(idBook);
    }
}
