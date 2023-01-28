package ro.dma.dcpm.book.dao.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
@Getter
@Setter
public class AuthorJpaEntity {

    @Id
    @SequenceGenerator(name = "author_seq_generator", sequenceName = "author_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_seq_generator")
    private Long id;
    private String firstname;
    private String lastname;
    @ManyToMany(mappedBy = "authors")
    private Set<BookJpaEntity> books = new HashSet<>();
    @Version
    private int version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorJpaEntity)) return false;
        return id != null && id.equals(((AuthorJpaEntity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
