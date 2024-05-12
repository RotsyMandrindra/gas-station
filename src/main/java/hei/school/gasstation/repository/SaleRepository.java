package hei.school.gasstation.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Data
@Repository
public class SaleRepository {
    private Connection connection;

    public String sale(double sellQuantity) {
        String lastMovementPerform = "SELECT movement_id FROM movement ORDER BY date DESC LIMIT 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(lastMovementPerform)) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                if (!resultSet.next()) {
                    throw new SQLException("No movement found");
                }
                UUID lastMovementId = resultSet.getObject("movement_id", UUID.class);

                String checkRemainingQuantity = "SELECT remaining_quantity FROM movement WHERE movement_id =?";
                try (PreparedStatement ps = connection.prepareStatement(checkRemainingQuantity)) {
                    ps.setObject(1, lastMovementId);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) {
                            throw new SQLException("Movement not found");
                        }
                        double remainingQuantity = rs.getDouble("remaining_quantity");

                        if (remainingQuantity < sellQuantity) {
                            throw new SQLException("Insufficient remaining quantity for the sale");
                        }

                        if (sellQuantity > 200) {
                            throw new SQLException("Exceeded maximum allowed quantity per sale (200 litres)");
                        }

                        String updateStock = "UPDATE movement SET remaining_quantity =? WHERE movement_id =?";
                        try (PreparedStatement updatePs = connection.prepareStatement(updateStock)) {
                            updatePs.setDouble(1, remainingQuantity - sellQuantity);
                            updatePs.setObject(2, lastMovementId);
                            updatePs.executeUpdate();
                        }

                        String getUpdatedRemainingQuantity = "SELECT remaining_quantity FROM movement WHERE movement_id =?";
                        try (PreparedStatement getUpdatedPs = connection.prepareStatement(getUpdatedRemainingQuantity)) {
                            getUpdatedPs.setObject(1, lastMovementId);
                            try (ResultSet updatedRs = getUpdatedPs.executeQuery()) {
                                if (updatedRs.next()) {
                                    double updatedRemainingQuantity = updatedRs.getDouble("remaining_quantity");
                                    return "Sale successful. New remaining quantity: " + updatedRemainingQuantity;
                                } else {
                                    throw new SQLException("Failed to retrieve updated remaining quantity");
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error during sale operation: " + e.getMessage(), e);
        }
    }
}
