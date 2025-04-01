package ro.dma.dcpm.book.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import ro.dma.dcpm.book.dao.entities.AuthorJpaEntity;
import ro.dma.dcpm.book.dao.entities.AuthorJpaEntity_;
import ro.dma.dcpm.book.dao.entities.BookJpaEntity;
import ro.dma.dcpm.book.dao.entities.BookJpaEntity_;
import ro.dma.dcpm.book.dto.BookDetailsForView;

import java.util.List;

@Repository
public class BookQueryJpaDao implements BookQueryDao {
    private final EntityManager em;

    public BookQueryJpaDao(EntityManager em) {
        this.em = em;
    }

    public BookDetailsForView getBookDetailsForView(Long idBook) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
        Root<BookJpaEntity> bookRoot = cq.from(BookJpaEntity.class);
        SetJoin<BookJpaEntity, AuthorJpaEntity> authorJoin = bookRoot.join(BookJpaEntity_.authors);
        cq.multiselect(
                bookRoot.get(BookJpaEntity_.id).alias(BookJpaEntity_.ID),
                bookRoot.get(BookJpaEntity_.title).alias(BookJpaEntity_.TITLE),
                authorJoin.get(AuthorJpaEntity_.firstname).alias(AuthorJpaEntity_.FIRSTNAME),
                authorJoin.get(AuthorJpaEntity_.lastname).alias(AuthorJpaEntity_.LASTNAME)
        );
        cq.where(cb.equal(bookRoot.get(BookJpaEntity_.id), idBook));
        List<Tuple> tupleLst = em.createQuery(cq).getResultList();
        return fromTupleToDto(tupleLst, idBook);
    }

    private BookDetailsForView fromTupleToDto(List<Tuple> tupleLst, Long idBook) {
        BookDetailsForView detalii = BookDetailsForView.builder()
                .id(idBook)
                .build();
        if (!tupleLst.isEmpty()) {
            Tuple tuple = tupleLst.get(0);
            detalii.setTitle(tuple.get(BookJpaEntity_.TITLE, String.class));
            StringBuilder autori = new StringBuilder();
            for (Tuple item : tupleLst) {
                autori
                        .append(item.get(AuthorJpaEntity_.FIRSTNAME, String.class)).append(" ")
                        .append(item.get(AuthorJpaEntity_.LASTNAME, String.class))
                        .append(", ");
            }
            detalii.setAuthors(autori.substring(0, autori.length() - 2));
        }
        return detalii;
    }

}
