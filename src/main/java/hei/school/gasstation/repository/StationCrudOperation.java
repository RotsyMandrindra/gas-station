package hei.school.gasstation.repository;

import hei.school.gasstation.model.Station;

import java.sql.*;
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
                station.setLocation(resultSet.getString("location"));
                station.setContact(resultSet.getString("contact"));
                station.setTotalAmountStation(resultSet.getDouble("total_amount_station"));
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
                    station.setLocation(resultSet.getString("location"));
                    station.setContact(resultSet.getString("contact"));
                    station.setTotalAmountStation(resultSet.getDouble("total_amount_station"));
                    allStationById.add(station);
                }
            }
        }
        return allStationById;
    }

    @Override
    public Station save(Station toSave) throws SQLException {
        String sql = "INSERT INTO station (station_id, station_name, location, contact, total_amount_station) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setObject(1, toSave.getStationId());
            insertStatement.setString(2, toSave.getStationName());
            insertStatement.setString(3, toSave.getLocation());
            insertStatement.setString(4, toSave.getContact());
            insertStatement.setDouble(5, toSave.getTotalAmountStation());
            insertStatement.executeUpdate();
        }
        return toSave;
    }

    @Override
    public Station update(UUID id, Station toUpdate) throws SQLException {
        String sql = "UPDATE station SET station_name=?, location=?, contact=?, total_amount_station=? WHERE station_id = ?";

        try (PreparedStatement updateSql = connection.prepareStatement(sql)) {
            updateSql.setString(1, toUpdate.getStationName());
            updateSql.setString(2, toUpdate.getLocation());
            updateSql.setString(3, toUpdate.getContact());
            updateSql.setDouble(4, toUpdate.getTotalAmountStation());
            updateSql.setObject(5, id);
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
