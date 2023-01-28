package ro.dma.dcpm.book.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookDetailsForView {
    private Long id;
    private String title;
    private String authors;
}
