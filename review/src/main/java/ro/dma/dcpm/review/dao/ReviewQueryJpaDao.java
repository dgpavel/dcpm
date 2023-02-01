package ro.dma.dcpm.review.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ro.dma.dcpm.review.dao.entities.ReviewJpaEntity;
import ro.dma.dcpm.review.dao.entities.ReviewJpaEntity_;
import ro.dma.dcpm.review.dto.BookReview;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewQueryJpaDao implements ReviewQueryDao {
    private final EntityManager em;

    public ReviewQueryJpaDao(EntityManager em) {
        this.em = em;
    }

    public List<BookReview> getBookReview(Long idBook) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
        Root<ReviewJpaEntity> reviewRoot = cq.from(ReviewJpaEntity.class);
        cq.multiselect(
                reviewRoot.get(ReviewJpaEntity_.id).alias(ReviewJpaEntity_.ID),
                reviewRoot.get(ReviewJpaEntity_.message).alias(ReviewJpaEntity_.MESSAGE),
                reviewRoot.get(ReviewJpaEntity_.writtenBy).alias(ReviewJpaEntity_.WRITTEN_BY)
        );
        cq.where(cb.equal(reviewRoot.get(ReviewJpaEntity_.idBook), idBook));
        List<Tuple> tupleLst = em.createQuery(cq).getResultList();
        return fromTupleToDto(tupleLst);
    }

    private List<BookReview> fromTupleToDto(List<Tuple> tupleLst) {
        List<BookReview> reviews = new ArrayList<>();
        for (Tuple tuple : tupleLst) {
            BookReview review = BookReview.builder()
                    .id(tuple.get(ReviewJpaEntity_.ID, Long.class))
                    .message(tuple.get(ReviewJpaEntity_.MESSAGE, String.class))
                    .writtenBy(tuple.get(ReviewJpaEntity_.WRITTEN_BY, String.class))
                    .build();
            reviews.add(review);
        }
        return reviews;
    }

}
