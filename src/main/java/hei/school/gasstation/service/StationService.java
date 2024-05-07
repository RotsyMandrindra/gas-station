package hei.school.gasstation.service;

import hei.school.gasstation.model.Station;
import hei.school.gasstation.repository.StationCrudOperation;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class StationService {
    private StationCrudOperation stationCrudOperation;

    public StationService(StationCrudOperation stationCrudOperation) {
        this.stationCrudOperation = stationCrudOperation;
    }
    public List<Station> getAllStation() throws SQLException {
        return stationCrudOperation.findAll();
    }
    public Station createStation(Station toSave) throws SQLException {
        return stationCrudOperation.save(toSave);
    }
    public Station updateStation(UUID id, Station toUpdate) throws SQLException {
        return stationCrudOperation.update(id, toUpdate);
    }
    public void deleteStation(UUID id) throws SQLException {
        stationCrudOperation.delete(id);
    }
}
