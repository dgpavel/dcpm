package ro.dma.dcpm.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookReview {
    private Long id;
    private String message;
    private String writtenBy;
}