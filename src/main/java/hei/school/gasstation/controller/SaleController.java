package hei.school.gasstation.controller;

import hei.school.gasstation.model.Sale;
import hei.school.gasstation.service.SaleActionService;
import hei.school.gasstation.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
@RestController
@Controller
public class SaleController {
    private SaleService saleService;
    private SaleActionService saleActionService;

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
    @PostMapping("/sale/create")
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
    @GetMapping("/sum_sell_quantity")
    public double getSumSellQuantityBetweenDates(Instant startDate, Instant endDate) throws SQLException{
        return saleService.getSumSellQuantityBetweenDates(startDate, endDate);
    }
    @PostMapping("/sale")
    public String sale(double sellQuantity) throws SQLException{
        return saleActionService.getSaleRepository().sale(sellQuantity);
    }
}
