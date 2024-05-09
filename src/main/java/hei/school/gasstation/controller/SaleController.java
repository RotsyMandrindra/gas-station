package hei.school.gasstation.controller;

import hei.school.gasstation.model.Sale;
import hei.school.gasstation.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class SaleController {
    private SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/sale")
    public List<Sale> getAllSale() throws SQLException {
        return saleService.getAllSale();
    }

    @GetMapping("/sale/{id}")
    public ResponseEntity<List<Sale>> getSaleById(@PathVariable("id") UUID id) throws SQLException {
        List<Sale> sales = saleService.getSaleById(id);
        if (sales == null || sales.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sales);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sale")
    public Sale createSale(@RequestBody Sale toSave) throws SQLException {
        return saleService.createSale(toSave);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/sale/{id}")
    public void updateSale(@PathVariable("id") UUID id, @RequestBody Sale toUpdate) throws SQLException {
        try {
            saleService.updateSale(id, toUpdate);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/sale/{id}")
    public void deleteSale(@PathVariable("id") UUID id) throws SQLException {
        saleService.deleteSale(id);
    }
}
