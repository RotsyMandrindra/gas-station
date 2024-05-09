package hei.school.gasstation.repository;

import hei.school.gasstation.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductCrudOperation implements CrudOperation<Product>{
    private Connection connection;

    public ProductCrudOperation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> allProduct = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
            Product product = new Product();
            product.setProductId(resultSet.getObject("product_id", UUID.class));
            product.setProductTemplateId(resultSet.getObject("product_template_id", UUID.class));
            product.setStationId(resultSet.getObject("station_id", UUID.class));
            allProduct.add(product);
            }
        }
        return allProduct;
    }

    @Override
    public List<Product> findById(UUID id) throws SQLException {
        List<Product> productById = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE product_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setProductId(resultSet.getObject("product_id", UUID.class));
                    product.setProductTemplateId(resultSet.getObject("product_template_id", UUID.class));
                    product.setStationId(resultSet.getObject("station_id", UUID.class));
                    productById.add(product);
                }
            }
        }
        return productById;
    }

    @Override
    public Product save(Product toSave) throws SQLException {
        String sql = "INSERT INTO product (product_id, product_template_id, station_id) VALUES (?, ?, ?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setObject(1, toSave.getProductId());
            insertStatement.setObject(2, toSave.getProductTemplateId());
            insertStatement.setObject(3, toSave.getStationId());
            insertStatement.executeUpdate();
        }
        return toSave;
    }

    @Override
    public Product update(UUID id, Product toUpdate) throws SQLException {
        String sql = "UPDATE product SET product_template_id = ?, station_id=? WHERE product_id = ?";

        try (PreparedStatement updateSql = connection.prepareStatement(sql)) {
            updateSql.setObject(1, toUpdate.getProductTemplateId());
            updateSql.setObject(2, toUpdate.getStationId());
            updateSql.setObject(3, id);
            updateSql.executeUpdate();
        }

        return toUpdate;
    }

    @Override
    public void delete(UUID id) throws SQLException {
        String sql = "DELETE FROM product WHERE product_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
