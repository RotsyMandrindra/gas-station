package hei.school.gasstation.service;

import hei.school.gasstation.model.ProductTemplate;
import hei.school.gasstation.repository.ProductTemplateCrudOperation;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
@Service
public class ProductTemplateService {
    private ProductTemplateCrudOperation productTemplateCrudOperation;

    public ProductTemplateService(ProductTemplateCrudOperation productTemplateCrudOperation) {
        this.productTemplateCrudOperation = productTemplateCrudOperation;
    }

    public List<ProductTemplate> getAllProductTemplate() throws SQLException {
        return productTemplateCrudOperation.findAll();
    }
    public ProductTemplate createProductTemplate(ProductTemplate toSave) throws SQLException {
        return productTemplateCrudOperation.save(toSave);
    }
    public ProductTemplate updateProductTemplate(UUID id, ProductTemplate toUpdate) throws SQLException {
        return productTemplateCrudOperation.update(id, toUpdate);
    }
    public void deleteProductTemplate(UUID id) throws SQLException {
        productTemplateCrudOperation.delete(id);
    }
    public List<ProductTemplate> getProductTemplateById(UUID id) throws SQLException {
        return productTemplateCrudOperation.findById(id);
    }
    public ProductTemplate updatePrice(UUID id, double newPrice){
        return productTemplateCrudOperation.updateProductPrice(id, newPrice);
    }
}
