package com.samgyeobsal.domain.fame;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "명예의 전당 페이징 객체")
public class FameCriteria {
    @Schema(description = "음식 카테고리")
    private String type;
    @Schema(description = "페이지 수")
    private int page;
}
