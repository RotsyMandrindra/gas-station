package hei.school.gasstation.repository;

import hei.school.gasstation.model.ProductTemplate;
import hei.school.gasstation.model.Station;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StationCrudOperation implements CrudOperation<Station>{
    private Connection connection;

    public StationCrudOperation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Station> findAll() throws SQLException {
        List<Station> allStation = new ArrayList<>();
        String sql = "SELECT * FROM station";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                Station station = new Station();
                station.setStationId(resultSet.getObject("station_id", UUID.class));
                station.setStationName(resultSet.getString("station_name"));
                station.setLocalisation(resultSet.getString("localisation"));
                station.setContact(resultSet.getString("contact"));
                station.setEmployeeNumber(resultSet.getInt("employee_number"));
                station.setTotalAmountStation(resultSet.getDouble("total_amount_station"));
                station.setDate((Instant) resultSet.getObject("date"));
                station.setProductId(resultSet.getObject("product_id", UUID.class));
                allStation.add(station);
            }
        }
        return allStation;
    }

    @Override
    public List<Station> findById(UUID id) throws SQLException {
        List<Station> allStationById = new ArrayList<>();
        String sql = "SELECT * FROM station WHERE station_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Station station = new Station();
                    station.setStationId(resultSet.getObject("station_id", UUID.class));
                    station.setStationName(resultSet.getString("station_name"));
                    station.setLocalisation(resultSet.getString("localisation"));
                    station.setContact(resultSet.getString("contact"));
                    station.setEmployeeNumber(resultSet.getInt("employee_number"));
                    station.setTotalAmountStation(resultSet.getDouble("total_amount_station"));
                    station.setDate((Instant) resultSet.getObject("date"));
                    station.setProductId(resultSet.getObject("product_id", UUID.class));
                    allStationById.add(station);
                }
            }
        }
        return allStationById;
    }

    @Override
    public Station save(Station toSave) throws SQLException {
        String sql = "INSERT INTO station (station_id, station_name, localisation, contact, employee_number, total_amount_station, date, product_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setObject(1, toSave.getStationId());
            insertStatement.setString(2, toSave.getStationName());
            insertStatement.setString(3, toSave.getLocalisation());
            insertStatement.setString(4, toSave.getContact());
            insertStatement.setInt(5, toSave.getEmployeeNumber());
            insertStatement.setDouble(6, toSave.getTotalAmountStation());
            insertStatement.setTimestamp(7, Timestamp.from(toSave.getDate()));
            insertStatement.setObject(8, toSave.getProductId());
            insertStatement.executeUpdate();
        }
        return toSave;
    }

    @Override
    public Station update(UUID id, Station toUpdate) throws SQLException {
        String sql = "UPDATE station SET station_name=?, localisation=?, contact=?, employee_number=?, total_amount_station=?, date=?, product_id=? WHERE product_template_id = ?";

        try (PreparedStatement updateSql = connection.prepareStatement(sql)) {
            updateSql.setString(1, toUpdate.getStationName());
            updateSql.setString(2, toUpdate.getLocalisation());
            updateSql.setString(3, toUpdate.getContact());
            updateSql.setInt(4, toUpdate.getEmployeeNumber());
            updateSql.setDouble(5, toUpdate.getTotalAmountStation());
            updateSql.setTimestamp(6, Timestamp.from(toUpdate.getDate()));
            updateSql.setObject(7, toUpdate.getProductId());
            updateSql.setObject(8, id);
            updateSql.executeUpdate();
        }

        return toUpdate;
    }

    @Override
    public void delete(UUID id) throws SQLException {
        String sql = "DELETE FROM station WHERE station_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        }
    }
    }
}
