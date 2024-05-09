package hei.school.gasstation.repository;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

public class GetSumSellQuantityRepository {
    private JdbcTemplate jdbcTemplate;

    public GetSumSellQuantityRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public double getSumQuantitySell() throws SQLException{
        String sql = "SELECT SUM(sell_quantity) AS total_sell_quantity FROM movement WHERE date = ? AND type='outlet'";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }
}
