package hei.school.gasstation.repository;

import hei.school.gasstation.model.Product;
import hei.school.gasstation.model.ProductTemplate;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
                productTemplate.setQuantitySold(resultSet.getDouble("quantity_sold"));
                productTemplate.setRemainingQuantity(resultSet.getDouble("remaining_quantity"));
                productTemplate.setProductAmount(resultSet.getDouble("product_amount"));
                productTemplate.setEvaporationRate(resultSet.getFloat("evaporation_rate"));
                productTemplate.setSuppliedQuantity(resultSet.getDouble("supplied_quantity"));
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
                    productTemplate.setQuantitySold(resultSet.getDouble("quantity_sold"));
                    productTemplate.setRemainingQuantity(resultSet.getDouble("remaining_quantity"));
                    productTemplate.setProductAmount(resultSet.getDouble("product_amount"));
                    productTemplate.setEvaporationRate(resultSet.getFloat("evaporation_rate"));
                    productTemplate.setSuppliedQuantity(resultSet.getDouble("supplied_quantity"));
                    allProductTemplateById.add(productTemplate);
                }
            }
        }
        return allProductTemplateById;
    }

    @Override
    public ProductTemplate save(ProductTemplate toSave) throws SQLException {
        String sql = "INSERT INTO product_template (product_template_id, product_name, product_price, quantity_sold, remaining_quantity, product_amount, evaporation_rate, supplied_quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setObject(1, toSave.getProductTemplateId());
            insertStatement.setString(2, toSave.getProductName());
            insertStatement.setDouble(3, toSave.getProductPrice());
            insertStatement.setDouble(4, toSave.getQuantitySold());
            insertStatement.setDouble(5, toSave.getRemainingQuantity());
            insertStatement.setDouble(6, toSave.getProductAmount());
            insertStatement.setFloat(7, toSave.getEvaporationRate());
            insertStatement.setDouble(8, toSave.getSuppliedQuantity());
            insertStatement.executeUpdate();
        }
        return toSave;
    }

    @Override
    public ProductTemplate update(UUID id, ProductTemplate toUpdate) throws SQLException {
        String sql = "UPDATE product_template SET product_name=?, product_price=?, quantity_sold=?, remaining_quantity=?, product_amount=?, evaporation_rate=?, supplied_quantity=? WHERE product_template_id = ?";

        try (PreparedStatement updateSql = connection.prepareStatement(sql)) {
            updateSql.setString(1, toUpdate.getProductName());
            updateSql.setDouble(2, toUpdate.getProductPrice());
            updateSql.setDouble(3, toUpdate.getQuantitySold());
            updateSql.setDouble(4, toUpdate.getRemainingQuantity());
            updateSql.setDouble(5, toUpdate.getProductAmount());
            updateSql.setFloat(6, toUpdate.getEvaporationRate());
            updateSql.setDouble(7, toUpdate.getSuppliedQuantity());
            updateSql.setObject(8, id);
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
