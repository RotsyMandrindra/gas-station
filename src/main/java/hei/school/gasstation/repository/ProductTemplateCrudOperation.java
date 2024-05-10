package hei.school.gasstation.repository;

import hei.school.gasstation.model.ProductTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Repository
public class ProductTemplateCrudOperation implements CrudOperation<ProductTemplate>{
    private Connection connection;

    public ProductTemplateCrudOperation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ProductTemplate> findAll() throws SQLException {
        List<ProductTemplate> allProductTemplate = new ArrayList<>();
        String sql = "SELECT * FROM product_template";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                ProductTemplate productTemplate = new ProductTemplate();
                productTemplate.setProductTemplateId(resultSet.getObject("product_template_id", UUID.class));
                productTemplate.setProductName(resultSet.getString("product_name"));
                productTemplate.setProductPrice(resultSet.getDouble("product_price"));
                productTemplate.setMovementId(resultSet.getObject("movement_id", UUID.class));
                allProductTemplate.add(productTemplate);
            }
        }
        return allProductTemplate;
    }

    @Override
    public List<ProductTemplate> findById(UUID id) throws SQLException {
        List<ProductTemplate> allProductTemplateById = new ArrayList<>();
        String sql = "SELECT * FROM product_template WHERE product_template_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ProductTemplate productTemplate = new ProductTemplate();
                    productTemplate.setProductTemplateId(resultSet.getObject("product_template_id", UUID.class));
                    productTemplate.setProductName(resultSet.getString("product_name"));
                    productTemplate.setProductPrice(resultSet.getDouble("product_price"));
                    productTemplate.setMovementId(resultSet.getObject("movement_id", UUID.class));
                    allProductTemplateById.add(productTemplate);
                }
            }
        }
        return allProductTemplateById;
    }

    @Override
    public ProductTemplate save(ProductTemplate toSave) throws SQLException {
        String sql = "INSERT INTO product_template (product_template_id, product_name, product_price, evaporation_rate, movement_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setObject(1, toSave.getProductTemplateId());
            insertStatement.setString(2, toSave.getProductName());
            insertStatement.setDouble(3, toSave.getProductPrice());
            insertStatement.setObject(4, toSave.getMovementId());
            insertStatement.executeUpdate();
        }
        return toSave;
    }

    @Override
    public ProductTemplate update(UUID id, ProductTemplate toUpdate) throws SQLException {
        String sql = "UPDATE product_template SET product_name=?, product_price=?, movement_id=? WHERE product_template_id = ?";

        try (PreparedStatement updateSql = connection.prepareStatement(sql)) {
            updateSql.setString(1, toUpdate.getProductName());
            updateSql.setDouble(2, toUpdate.getProductPrice());
            updateSql.setObject(4, toUpdate.getMovementId());
            updateSql.setObject(5, id);
            updateSql.executeUpdate();
        }

        return toUpdate;
    }

    @Override
    public void delete(UUID id) throws SQLException {
        String sql = "DELETE FROM product_template WHERE product_template_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
