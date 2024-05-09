package hei.school.gasstation.service;

import hei.school.gasstation.model.Sale;
import hei.school.gasstation.repository.SaleCrudOperation;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class SaleService {
    private SaleCrudOperation saleCrudOperation;

    public SaleService(SaleCrudOperation saleCrudOperation) {
        this.saleCrudOperation = saleCrudOperation;
    }
    public List<Sale> getAllSale() throws SQLException {
        return saleCrudOperation.findAll();
    }
    public Sale createSale(Sale toSave) throws SQLException {
        return saleCrudOperation.save(toSave);
    }
    public Sale updateSale(UUID id, Sale toUpdate) throws SQLException {
        return saleCrudOperation.update(id, toUpdate);
    }
    public void deleteSale(UUID id) throws SQLException {
        saleCrudOperation.delete(id);
    }
    public List<Sale> getSaleById(UUID id) throws SQLException {
        return saleCrudOperation.findById(id);
    }
}
