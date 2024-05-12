package hei.school.gasstation.repository;

import hei.school.gasstation.model.Sale;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Repository
@AllArgsConstructor
public class SaleCrudOperation implements CrudOperation<Sale>{
    private Connection connection;
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Sale> findAll() throws SQLException {
        List<Sale> allSale = new ArrayList<>();
        String sql = "SELECT movement_id, type, sell_quantity, remaining_quantity, date FROM movement WHERE type = 'outlet'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                Sale sale = new Sale();
                sale.setMovementId(resultSet.getObject("movement_id", UUID.class));
                sale.setType(resultSet.getString("type"));
                sale.setSellQuantity(resultSet.getDouble("sell_quantity"));
                sale.setRemainingQuantity(resultSet.getDouble("remaining_quantity"));
                sale.setDate(resultSet.getTimestamp("date").toInstant());
                allSale.add(sale);
            }
        }
        return allSale;
    }

    @Override
    public List<Sale> findById(UUID id) throws SQLException {
        List<Sale> allSaleById = new ArrayList<>();
        String sql = "SELECT movement_id, type, sell_quantity, remaining_quantity, date FROM movement WHERE type = 'outlet' AND movement_id =?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Sale sale = new Sale();
                    sale.setMovementId(resultSet.getObject("movement_id", UUID.class));
                    sale.setType(resultSet.getString("type"));
                    sale.setSellQuantity(resultSet.getDouble("sell_quantity"));
                    sale.setRemainingQuantity(resultSet.getDouble("remaining_quantity"));
                    sale.setDate(resultSet.getTimestamp("date").toInstant());
                    allSaleById.add(sale);
                }
            }
            return allSaleById;
        }
    }

    @Override
    public Sale save(Sale toSave) throws SQLException {
        String sql = "INSERT INTO movement (movement_id, type, sell_quantity, remaining_quantity, date) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setObject(1, toSave.getMovementId());
            insertStatement.setString(2, toSave.getType());
            insertStatement.setDouble(3, toSave.getSellQuantity());
            insertStatement.setDouble(4, toSave.getRemainingQuantity());
            insertStatement.setTimestamp(5, Timestamp.from(toSave.getDate()));
            insertStatement.executeUpdate();
        }
        return toSave;
    }

    @Override
    public Sale update(UUID id, Sale toUpdate) throws SQLException {
        String sql = "UPDATE movement SET type=?, sell_quantity=?, remaining_quantity=?, date=? WHERE movement_id=?";

        try (PreparedStatement updateSql = connection.prepareStatement(sql)) {
            updateSql.setString(1, toUpdate.getType());
            updateSql.setDouble(2, toUpdate.getSellQuantity());
            updateSql.setDouble(3, toUpdate.getRemainingQuantity());
            updateSql.setTimestamp(4, Timestamp.from(toUpdate.getDate()));
            updateSql.setObject(5, id);
            updateSql.executeUpdate();
        }

        return toUpdate;
    }

    @Override
    public void delete(UUID id) throws SQLException {
        String sql = "DELETE FROM movement WHERE movement_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        }
    }
    public double getSumQuantitySellBetweenDates(Instant startDate, Instant endDate) throws SQLException {
        String sql = "SELECT SUM(sell_quantity) AS total_sell_quantity FROM movement WHERE date BETWEEN? AND? AND type='outlet'";
        return jdbcTemplate.queryForObject(sql, Double.class, startDate, endDate);
    }
}
