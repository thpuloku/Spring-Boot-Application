package com.efuture.demo.controller;

import com.efuture.demo.model.api.request.ProductRequestDTO;
import com.efuture.demo.model.api.response.ProductResponseDTO;
import com.efuture.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createProduct(@Valid @RequestBody ProductRequestDTO request) {
        log.info("Creating a product. name ={}" , request.getName());
        productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/{categoryName}")
    @ResponseStatus(HttpStatus.FOUND)
    public @ResponseBody
    ResponseEntity<List<ProductResponseDTO>>
   // List<ProductResponseDTO>
    getAllByProductCategory(@PathVariable String categoryName) {
        log.info("Retriving a products by categoryName. categoryName ={}" , categoryName);

        List<ProductResponseDTO> products=  productService.getByProgramCategory(categoryName);
    //     return  new ResponseEntity<List<ProductResponseDTO>>(products,HttpStatus.CREATED);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(products);

    }

    @GetMapping(value = "/premium")
    public @ResponseBody
    List<ProductResponseDTO> getPremiumProducts() {
        return productService.findPremiumProducts();
    }


//    @PutMapping("/{id}")
//    public ResponseEntity updateProduct(@PathVariable("id") final Integer id,
//                                                 @RequestBody final ProductRequestDTO request) {
//        log.info("Updating reseller id={}", id);
//        return productService.updateProduct(id, request);
//    }
}
