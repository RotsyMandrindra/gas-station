package hei.school.gasstation.controller;

import hei.school.gasstation.model.ProductTemplate;
import hei.school.gasstation.service.ProductTemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
@RestController
@Controller
public class ProductTemplateController {
    private ProductTemplateService productTemplateService;

    public ProductTemplateController(ProductTemplateService productTemplateService) {
        this.productTemplateService = productTemplateService;
    }
    @GetMapping("/product_template")
    public List<ProductTemplate> getAllProductTemplate() throws SQLException {
        return productTemplateService.getAllProductTemplate();
    }

    @GetMapping("/product_template/{id}")
    public ResponseEntity<List<ProductTemplate>> getProductTemplateById(@PathVariable("id") UUID id) throws SQLException {
        List<ProductTemplate> productTemplates = productTemplateService.getProductTemplateById(id);
        if (productTemplates == null || productTemplates.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productTemplates);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/product_template")
    public ProductTemplate createProductTemplate(@RequestBody ProductTemplate toSave) throws SQLException {
        return productTemplateService.createProductTemplate(toSave);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/product_template/{id}")
    public void updateProductTemplate(@PathVariable("id") UUID id, @RequestBody ProductTemplate toUpdate) throws SQLException {
        try {
            productTemplateService.updateProductTemplate(id, toUpdate);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/product_template/{id}")
    public void deleteProductTemplate(@PathVariable("id") UUID id) throws SQLException {
        productTemplateService.deleteProductTemplate(id);
    }
    @PutMapping("/product_template/update_price/{id}")
    public ResponseEntity<ProductTemplate> updateProductTemplatePrice(@PathVariable UUID id, @RequestParam("price") Double newPrice) throws SQLException {
        ProductTemplate updatedProductTemplate = productTemplateService.updatePrice(id, newPrice);
        return ResponseEntity.ok(updatedProductTemplate);
    }
}
