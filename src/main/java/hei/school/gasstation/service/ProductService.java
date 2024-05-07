package hei.school.gasstation.service;

import hei.school.gasstation.model.Product;
import hei.school.gasstation.model.Station;
import hei.school.gasstation.repository.ProductCrudOperation;
import hei.school.gasstation.repository.StationCrudOperation;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ProductService {
    private ProductCrudOperation productCrudOperation;

    public ProductService(ProductCrudOperation productCrudOperation) {
        this.productCrudOperation = productCrudOperation;
    }

    public List<Product> getAllProduct() throws SQLException {
        return productCrudOperation.findAll();
    }
    public Product createProduct(Product toSave) throws SQLException {
        return productCrudOperation.save(toSave);
    }
    public Product updateProduct(UUID id, Product toUpdate) throws SQLException {
        return productCrudOperation.update(id, toUpdate);
    }
    public void deleteProduct(UUID id) throws SQLException {
        productCrudOperation.delete(id);
    }
}
