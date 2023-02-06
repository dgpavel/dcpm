package ro.dma.dcpm.review.dao.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "review")
@Getter
@Setter
public class ReviewJpaEntity {

    @Id
    @SequenceGenerator(name = "review_seq_generator", sequenceName = "review_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq_generator")
    private Long id;

    @Column(name = "book_id")
    private Long idBook;

    private String message;

    @Column(name = "written_by")
    private String writtenBy;

    @Version
    private int version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewJpaEntity)) return false;
        return id != null && id.equals(((ReviewJpaEntity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}