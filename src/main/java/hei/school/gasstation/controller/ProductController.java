package hei.school.gasstation.controller;

import hei.school.gasstation.model.Product;
import hei.school.gasstation.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/product")
    public List<Product> getAllProduct() throws SQLException {
        return productService.getAllProduct();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<Product>> getProductById(@PathVariable("id") UUID id) throws SQLException {
        List<Product> products = productService.getProductById(id);
        if (products == null || products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/product")
    public Product createProduct(@RequestBody Product toSave) throws SQLException {
        return productService.createProduct(toSave);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/product/{id}")
    public void updateProduct(@PathVariable("id") UUID id, @RequestBody Product toUpdate) throws SQLException {
        try {
            productService.updateProduct(id, toUpdate);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable("id") UUID id) throws SQLException {
        productService.deleteProduct(id);
    }
}
