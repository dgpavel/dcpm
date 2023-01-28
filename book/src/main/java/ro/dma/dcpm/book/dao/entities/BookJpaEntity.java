package ro.dma.dcpm.book.dao.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@Getter
@Setter
public class BookJpaEntity {
    @Id
    @SequenceGenerator(name = "book_seq_generator", sequenceName = "book_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq_generator")
    private Long id;
    private String title;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<AuthorJpaEntity> authors = new HashSet<>();
    @Version
    private int version;

    public void addAuthor(AuthorJpaEntity authorJpaEntity) {
        authors.add(authorJpaEntity);
    }

    public void removeAuthor(AuthorJpaEntity authorJpaEntity) {
        authors.remove(authorJpaEntity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookJpaEntity)) return false;
        return id != null && id.equals(((BookJpaEntity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
