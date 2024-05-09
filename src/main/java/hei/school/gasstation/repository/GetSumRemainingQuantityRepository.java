package hei.school.gasstation.repository;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

public class GetSumRemainingQuantityRepository {
    private JdbcTemplate jdbcTemplate;

    public GetSumRemainingQuantityRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public double getSumRemainingQuantity() throws SQLException{
        String sql = "SELECT SUM(remaining_quantity) AS total_remaining_quantity FROM movement WHERE date = ?";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }
}
