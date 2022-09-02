package com.efuture.demo.model.api.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class ProductRequestDTO {

    private double price;
    private String name;
    private String description;
    private Integer categoryId;
}
