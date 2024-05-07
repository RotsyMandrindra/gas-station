package hei.school.gasstation.controller;

import hei.school.gasstation.model.Station;
import hei.school.gasstation.service.StationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class StationController {
    private StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }
    @GetMapping("/station")
    public List<Station> getAllStation() throws SQLException {
        return stationService.getAllStation();
    }

    @GetMapping("/station/{id}")
    public ResponseEntity<List<Station>> getStationById(@PathVariable("id") UUID id) throws SQLException {
        List<Station> stations = stationService.getStationById(id);
        if (stations == null || stations.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stations);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/station")
    public Station createStation(@RequestBody Station toSave) throws SQLException {
        return stationService.createStation(toSave);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/station/{id}")
    public void updateStation(@PathVariable("id") UUID id, @RequestBody Station toUpdate) throws SQLException {
        try {
            stationService.updateStation(id, toUpdate);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/station/{id}")
    public void deleteStation(@PathVariable("id") UUID id) throws SQLException {
        stationService.deleteStation(id);
    }
}
