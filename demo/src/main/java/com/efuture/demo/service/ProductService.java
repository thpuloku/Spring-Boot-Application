package com.efuture.demo.service;

import com.efuture.demo.model.api.request.ProductRequestDTO;
import com.efuture.demo.model.api.response.ProductCategoryResponseDTO;
import com.efuture.demo.model.api.response.ProductCommentResponseDTO;
import com.efuture.demo.model.api.response.ProductResponseDTO;
import com.efuture.demo.model.entity.Product;
import com.efuture.demo.model.entity.ProductCategory;
import com.efuture.demo.model.entity.ProductComment;
import com.efuture.demo.repository.ProductCategoryRepository;
import com.efuture.demo.repository.ProductRepository;
import com.efuture.demo.validator.ProductValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductValidator productValidator;
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    //private final ProductCategoryValidator productCategoryValidator;

    @Transactional
    public void createProduct(ProductRequestDTO requestDTO){
        productValidator.validate(requestDTO);
        productRepository.save(buildProductData(requestDTO));

    }
    public List<ProductResponseDTO> findPremiumProducts() {
        List<Product> productList = productRepository.findPremiumProducts();
        List<ProductResponseDTO> dtoList = mapToProductDTOList(productList);
        return dtoList;
    }

    @Transactional
    public void updateProduct(Integer id ,ProductRequestDTO requestDTO){
        final var existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

      Product pc = Product.builder()
                  .name(requestDTO.getName())
                  .description(requestDTO.getDescription())
                  .price(requestDTO.getPrice())
                  .id(existingProduct.getId())
                  .productCategory(existingProduct.getProductCategory())
                  .build();
         existingProduct.setName(requestDTO.getName())



    }



    private Product buildProductData(ProductRequestDTO requestDTO) {
        return Product.builder()
                .name(requestDTO.getName())
                .description(requestDTO.getDescription())
                .price(requestDTO.getPrice())
                .status(null)
                .launchDate(new Date())
               .productCategory(productCategoryRepository.findById(requestDTO.getCategoryId()).get())
                .build();
    }

    public List<ProductResponseDTO> getByProgramCategory(String categoryName) {
        List<Product> product = productRepository.findByCategoryName(categoryName);
        return mapToProductDTOList(product);

    }

    private List<ProductResponseDTO> mapToProductDTOList(List<Product> productList) {
        if (CollectionUtils.isEmpty(productList)) {
            return new ArrayList<>();
        }
        return productList.stream()
                .map(pr -> {
                    return mapToProductDTO(pr);
                })
                .collect(Collectors.toList());
    }

    private ProductResponseDTO mapToProductDTO(final Product product) {
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product entity not found.");
        }

        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .productCategory(mapToProductCategory(product.getProductCategory()))
                .productComments(mapToCommentDTOList(product.getCommentList()))
                .build();
    }


 private ProductCategoryResponseDTO mapToProductCategory(ProductCategory productCategory){
     if (productCategory == null) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "productCategory entity not found.");
     }

     return ProductCategoryResponseDTO.builder()
             .id(productCategory.getId())
             .name(productCategory.getName())
             .description(productCategory.getDescription())
             .build();

 }

    private List<ProductCommentResponseDTO> mapToCommentDTOList(List<ProductComment> commentsList) {
        if (CollectionUtils.isEmpty(commentsList)) {
            return new ArrayList<>();
        }
        return commentsList.stream()
                .map(pc -> {
                    return mapToCommentDTO(pc);
                })
                .collect(Collectors.toList());

    }

    private ProductCommentResponseDTO mapToCommentDTO(final ProductComment comment) {
        if (comment == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment entity not found.");
        }

        return ProductCommentResponseDTO.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
