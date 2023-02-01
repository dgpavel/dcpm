package ro.dma.dcpm.orderinfo.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Book {
    private Long id;
    private String title;
    private String authors;
}