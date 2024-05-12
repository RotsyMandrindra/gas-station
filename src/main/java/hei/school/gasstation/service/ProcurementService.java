package hei.school.gasstation.service;

import hei.school.gasstation.model.Procurement;
import hei.school.gasstation.repository.ProcurementCrudOperation;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
public class ProcurementService {
    private ProcurementCrudOperation procurementCrudOperation;
    private JdbcTemplate  jdbcTemplate;

    public List<Procurement> getAllProcurement() throws SQLException {
        return procurementCrudOperation.findAll();
    }
    public Procurement createProcurement(Procurement toSave) throws SQLException {
        return procurementCrudOperation.save(toSave);
    }
    public Procurement updateProcurement(UUID id, Procurement toUpdate) throws SQLException {
        return procurementCrudOperation.update(id, toUpdate);
    }
    public void deleteProcurement(UUID id) throws SQLException {
        procurementCrudOperation.delete(id);
    }
    public List<Procurement> getProcurementById(UUID id) throws SQLException {
        return procurementCrudOperation.findById(id);
    }
    public double getSumRemainingQuantityBetweenDates(Instant startDate, Instant endDate) throws SQLException{
        return procurementCrudOperation.getSumRemainingQuantityBetweenDates(startDate, endDate);
    }
    public void updateRemainingQuantity() {
        Instant now = Instant.now();
        try {
            String sql = "SELECT date FROM movement ORDER BY date DESC LIMIT 1 WHERE type = 'entry'";
            Instant lastUpdated = jdbcTemplate.queryForObject(sql, Instant.class);

            long hoursSinceLastUpdate = ChronoUnit.HOURS.between(lastUpdated, now);

            if (hoursSinceLastUpdate >= 24) {
                String updateSql = "UPDATE movement SET remaining_quantity = remaining_quantity - CASE WHEN product_type = 'essence' THEN 100 ELSE IF(product_type = 'gasoil', 50, 10) END WHERE remaining_quantity IS NOT NULL";
                jdbcTemplate.update(updateSql);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
