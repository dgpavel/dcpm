package ro.ilearn.dcpm.review.adapter.web;

import org.mapstruct.Mapper;
import ro.ilearn.dcpm.review.core.domain.Review;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewDtoMapper {

    ReviewDto toReviewDto(Review review);

    List<ReviewDto> toReviewDto(List<Review> review);
}
