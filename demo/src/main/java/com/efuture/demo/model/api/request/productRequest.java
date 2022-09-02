package com.efuture.demo.model.api.request;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;

@Value
@Builder
@Jacksonized
public class productRequest {

    private double price;
    @NotBlank
    private String name;
    private String description;
    private Integer categoryId;
}
