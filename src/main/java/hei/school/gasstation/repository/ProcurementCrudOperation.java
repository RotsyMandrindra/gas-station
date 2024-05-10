package hei.school.gasstation.repository;

import hei.school.gasstation.model.Procurement;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Repository
@AllArgsConstructor
public class ProcurementCrudOperation implements CrudOperation<Procurement>{
    private Connection connection;
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Procurement> findAll() throws SQLException {
        List<Procurement> allProcurement = new ArrayList<>();
        String sql = "SELECT movement_id, type, remaining_quantity, supply_quantity, date FROM movement WHERE type = 'entry'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                Procurement procurement = new Procurement();
                procurement.setMovementId(resultSet.getObject("movement_id", UUID.class));
                procurement.setType(resultSet.getString("type"));
                procurement.setRemainingQuantity(resultSet.getDouble("remaining_quantity"));
                procurement.setSupplyQuantity(resultSet.getDouble("supply_quantity"));
                procurement.setDate(resultSet.getTimestamp("date").toInstant());
                allProcurement.add(procurement);
            }
        }
        return allProcurement;
    }

    @Override
    public List<Procurement> findById(UUID id) throws SQLException {
        List<Procurement> allProcurementById = new ArrayList<>();
        String sql = "SELECT movement_id, type, remaining_quantity, supply_quantity, date FROM movement WHERE type = 'entry' AND movement_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Procurement procurement = new Procurement();
                    procurement.setMovementId(resultSet.getObject("movement_id", UUID.class));
                    procurement.setType(resultSet.getString("type"));
                    procurement.setRemainingQuantity(resultSet.getDouble("remaining_quantity"));
                    procurement.setSupplyQuantity(resultSet.getDouble("supply_quantity"));
                    procurement.setDate(resultSet.getTimestamp("date").toInstant());
                    allProcurementById.add(procurement);
                }
            }
        }
        return allProcurementById;
    }

    @Override
    public Procurement save(Procurement toSave) throws SQLException {
        String sql = "INSERT INTO movement (movement_id, type, remaining_quantity, supply_quantity, date) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setObject(1, toSave.getMovementId());
            insertStatement.setString(2, toSave.getType());
            insertStatement.setDouble(3, toSave.getRemainingQuantity());
            insertStatement.setDouble(4, toSave.getSupplyQuantity());
            insertStatement.setTimestamp(5, Timestamp.from(toSave.getDate()));
            insertStatement.executeUpdate();
        }
        return toSave;
    }

    @Override
    public Procurement update(UUID id, Procurement toUpdate) throws SQLException {
        String sql = "UPDATE movement SET type=?, remaining_quantity=?, supply_quantity=?, date=? WHERE movement_id=?";

        try (PreparedStatement updateSql = connection.prepareStatement(sql)) {
            updateSql.setString(1, toUpdate.getType());
            updateSql.setDouble(2, toUpdate.getRemainingQuantity());
            updateSql.setDouble(3, toUpdate.getSupplyQuantity());
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
    public double getSumRemainingQuantity() throws SQLException{
        String sql = "SELECT SUM(remaining_quantity) AS total_remaining_quantity FROM movement WHERE date = ?";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }
}
