package hei.school.gasstation.controller;

import hei.school.gasstation.model.Procurement;
import hei.school.gasstation.service.ProcurementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
@RestController
@Controller
@AllArgsConstructor
public class ProcurementController {
    private ProcurementService procurementService;

    @GetMapping("/procurement")
    public List<Procurement> getAllProcurement() throws SQLException {
        return procurementService.getAllProcurement();
    }

    @GetMapping("/procurement/{id}")
    public ResponseEntity<List<Procurement>> getProcurementById(@PathVariable("id") UUID id) throws SQLException {
        List<Procurement> procurements = procurementService.getProcurementById(id);
        if (procurements == null || procurements.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(procurements);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/procurement")
    public Procurement createProcurement(@RequestBody Procurement toSave) throws SQLException {
        return procurementService.createProcurement(toSave);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/procurement/{id}")
    public void updateProcurement(@PathVariable("id") UUID id, @RequestBody Procurement toUpdate) throws SQLException {
        try {
            procurementService.updateProcurement(id, toUpdate);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/procurement/{id}")
    public void deleteProcurement(@PathVariable("id") UUID id) throws SQLException {
        procurementService.deleteProcurement(id);
    }
    @GetMapping("/sum_remaining_quantity")
    public double getSumRemainingQuantityBetweenDates(Instant startDate, Instant endDate) throws SQLException{
        return procurementService.getSumRemainingQuantityBetweenDates(startDate, endDate);
    }
    @Scheduled(fixedRate = 86400000)
    public void updateQuantities() {
        procurementService.updateRemainingQuantity();
    }
}
