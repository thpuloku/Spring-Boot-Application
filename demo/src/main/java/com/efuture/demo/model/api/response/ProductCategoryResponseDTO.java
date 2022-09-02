package com.efuture.demo.model.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class ProductCategoryResponseDTO {
    private Integer id;
    private String name;
    private String description;
}
