package com.efuture.demo.model.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

@Getter
@Setter
@Builder
@Jacksonized
public class ProductCommentResponseDTO {
    private Integer id;
    private String comment;
    private Date createdAt;
}
