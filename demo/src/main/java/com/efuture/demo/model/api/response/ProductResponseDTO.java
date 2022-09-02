package com.efuture.demo.model.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@Setter
@Builder
@Jacksonized
public class ProductResponseDTO {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private ProductCategoryResponseDTO productCategory;
    private List<ProductCommentResponseDTO> productComments;

}
